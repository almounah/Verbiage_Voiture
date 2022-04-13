/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model;

import ensimag.voiture.model.dataBase.Trajectory;
import ensimag.voiture.model.dataBase.TrajectoryChunck;
import ensimag.voiture.model.dataBase.Trip;
import ensimag.voiture.model.dataBase.User;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.TIMESTAMP;

/**
 *
 * @author almounah
 */
public class Search {
    private static List<Trip> lt = new ArrayList<>();
    
    public static List<Trip> searchForTripNoCorresp(String cityDep, String cityArr, LocalDateTime startTime) {
        String query = 
                    "Select " +
                    "DISTINCT s1.trajectId, " +
                    "s2.sectionId, " +
                    "s1.sectionId, " +
                    "carFiscalPower, " +
                    "carEnergy, " +
                    "drivenLicenseCar, " +
                    "mailUser " +
                    "FROM " +
                    "sections s1, " +
                    "sections s2, " +
                    "trajectory t, " +
                    "userInfo, " +
                    "carInfo c " +
                    "WHERE " +
                    "s1.trajectId = s2.trajectId " +
                    "AND t.trajectId = s1.trajectId " +
                    "AND c.licensePlate = t.drivenLicenseCar " +
                    "AND s1.cityArrival=? " +
                    "AND s2.cityDeparture=? " +
                    "AND s2.sectionId <= s1.sectionId " +
                    "AND driverMail = mailUser " +
                    "AND 0 not IN " +
                    "(Select " +
                    "availableSeats " +
                    "FROM " +
                    "sections s3 " +
                    "WHERE " +
                    "s3.trajectId = s1.trajectId " +
                    "AND s2.sectionId <= s3.sectionId " +
                    "AND s3.sectionId <= s1.sectionId) " +
                    "AND s2.sectionStartDate - ? < NUMTODSINTERVAL(1, 'day') ";

        List param = Arrays.asList(cityArr, cityDep, Timestamp.valueOf(startTime));
        List<String> paramType = Arrays.asList("String", "String", "Date");
        
        Map<Integer, List> trajRes = QueriesRunner.QueryGetter(query, param, paramType);
        List<Trip> tripList = new ArrayList<>();
        System.out.println(query);
        for (Map.Entry<Integer, List> tripInfo : trajRes.entrySet()) {
            
            
            Integer trajectoryId = ((BigDecimal) tripInfo.getValue().get(0)).intValue();
            Integer startIndex = ((BigDecimal) tripInfo.getValue().get(1)).intValue();
            Integer endIndex = ((BigDecimal) tripInfo.getValue().get(2)).intValue();
            Float carFiscalPower = ((BigDecimal) tripInfo.getValue().get(3)).floatValue();
            CarEnergy carEnergy = CarEnergy.valueOf((String) tripInfo.getValue().get(4));
            String drivenCarLicense = (String) tripInfo.getValue().get(5);
            String mailUser = (String) tripInfo.getValue().get(6);
            
            
            Trajectory traj = new Trajectory(trajectoryId, mailUser, drivenCarLicense);
            List<TrajectoryChunck> chunckList = traj.getTrajectoryInforFromDB(trajectoryId);
            
            Integer tripId = mailUser.hashCode() + drivenCarLicense.hashCode() + chunckList.hashCode();
            Trip t = new Trip(tripId);
            System.out.println(endIndex + " " + startIndex + " " + trajectoryId + " No Corresp");
            for (int i = startIndex; i <= endIndex; i++) {
                t.addChunckToTrip(chunckList.get(i-1));
            }
            
            t.setPrice(calculateSingleTripPrice(chunckList, carEnergy, carFiscalPower));
            t.setCorrespondanceBool(false);
            tripList.add(t);
        }
        lt.addAll(tripList);
        return tripList;
    }
    
    public static Double calculateSingleTripPrice(List<TrajectoryChunck> tcl, CarEnergy e, Float carFiscalPower) {
        Double kmprice = e.getAlpha()*0.10*carFiscalPower;
        Double totDistance = 0.0;
        
        for (TrajectoryChunck tc : tcl) {
            totDistance += tc.getTravelDistance();
        }
        
        return kmprice*totDistance;
    }
    
    public static List<Trip> searchForTripOneCorresp(String cityDep, String cityArr, LocalDateTime startTime){
        lt = new ArrayList<>();
        String query =
                "Select DISTINCT s1.trajectId, s2.sectionId, s1.sectionId, c1.carFiscalPower, c1.carEnergy, t1.drivenLicenseCar, u1.mailUser," +
                " s4.trajectId, s4.sectionId, s5.sectionId, c2.carFiscalPower, c2.carEnergy, t2.drivenLicenseCar, u2.mailUser" +
                " FROM sections s1, sections s2, sections s4, sections s5, trajectory t1, userInfo u1, carInfo c1, trajectory t2, userInfo u2, carInfo c2" +
                " WHERE" +
                " s1.trajectId = s2.trajectId AND" +
                " t1.trajectId = s1.trajectId AND" +
                " c1.licensePlate = t1.drivenLicenseCar AND" +
                " s2.cityDeparture=? AND" +
                " (s1.cityArrival, s4.trajectId, s4.sectionId, s5.sectionId, c2.carFiscalPower, c2.carEnergy, t2.drivenLicenseCar, u2.mailUser)" +
                " IN (Select DISTINCT s4.cityDeparture, s4.trajectId, s4.sectionId, s5.sectionId,  c2.carFiscalPower, c2.carEnergy, t2.drivenLicenseCar, u2.mailUser" +
                " FROM sections s4, sections s5, trajectory t2, carInfo c2, userInfo u2" +
                " WHERE" +
                " t2.trajectId = s5.trajectId AND" +
                " s4.trajectId = s5.trajectId AND" +
                " NOT s4.trajectId = s1.trajectId AND" +
                " c2.licensePlate = t2.drivenLicenseCar AND" +
                " NOT c2.licensePlate = c1.licensePlate AND" +
                " t2.driverMail = u2.mailUser AND" +
                " s5.cityArrival=? AND" +
                " s4.sectionId <= s5.sectionId AND " +
                " 0 not IN (Select availableSeats " +
                " FROM sections s6 " +
                " WHERE s6.trajectId = s4.trajectId AND" +
                " s4.sectionId <= s6.sectionId AND" +
                " s6.sectionId <= s5.sectionId) AND" +
                " POWER(POWER(s4.latDeparture - s1.longArrival,2)+POWER(s1.latArrival - s4.longDeparture,2), 0.5)<100 AND" +
                " s4.sectionStartDate - s1.sectionStartDate + NUMTODSINTERVAL(s1.travelDuration * 60, 'SECOND') < NUMTODSINTERVAL(60, 'minute'))" +
                " AND" +
                " t1.driverMail = u1.mailUser AND" +
                " 0 not IN (Select availableSeats" +
                " FROM sections s3" +
                " WHERE s3.trajectId = s1.trajectId AND" +
                " s2.sectionId <= s3.sectionId AND" +
                " s3.sectionId <= s1.sectionId)" +
                " AND s2.sectionId <= s1.sectionId ";
        List param = Arrays.asList(cityDep, cityArr);
        List<String> paramType = Arrays.asList("String", "String");
        System.out.println(query);
        Map<Integer, List> trajRes = QueriesRunner.QueryGetter(query, param, paramType);
        List<Trip> tripList = new ArrayList<>();
        System.out.println(trajRes);
        for (Map.Entry<Integer, List> tripInfo : trajRes.entrySet()) {
            
            
            Integer trajectoryId1 = ((BigDecimal) tripInfo.getValue().get(0)).intValue();
            Integer startIndex1 = ((BigDecimal) tripInfo.getValue().get(1)).intValue();
            Integer endIndex1 = ((BigDecimal) tripInfo.getValue().get(2)).intValue();
            Float carFiscalPower1 = ((BigDecimal) tripInfo.getValue().get(3)).floatValue();
            CarEnergy carEnergy1 = CarEnergy.valueOf((String) tripInfo.getValue().get(4));
            String drivenCarLicense1 = (String) tripInfo.getValue().get(5);
            String mailUser1 = (String) tripInfo.getValue().get(6);
            
            Integer trajectoryId2 = ((BigDecimal) tripInfo.getValue().get(7)).intValue();
            Integer startIndex2 = ((BigDecimal) tripInfo.getValue().get(8)).intValue();
            Integer endIndex2 = ((BigDecimal) tripInfo.getValue().get(9)).intValue();
            Float carFiscalPower2 = ((BigDecimal) tripInfo.getValue().get(10)).floatValue();
            CarEnergy carEnergy2 = CarEnergy.valueOf((String) tripInfo.getValue().get(11));
            String drivenCarLicense2 = (String) tripInfo.getValue().get(12);
            String mailUser2 = (String) tripInfo.getValue().get(13);
            
            System.out.println(endIndex1 + " " + startIndex1 + " " + trajectoryId1 + " Corresp1");
            System.out.println(endIndex2 + " " + startIndex2 + " " + trajectoryId2 + " Corresp2");
            
            Trajectory traj1 = new Trajectory(trajectoryId1, mailUser1, drivenCarLicense1);
            List<TrajectoryChunck> chunckList1 = traj1.getTrajectoryInforFromDB(trajectoryId1);
            
            Trajectory traj2 = new Trajectory(trajectoryId2, mailUser2, drivenCarLicense2);
            List<TrajectoryChunck> chunckList2 = traj2.getTrajectoryInforFromDB(trajectoryId2);
            
            Integer tripId = mailUser1.hashCode() + drivenCarLicense1.hashCode() + chunckList1.hashCode() +
                             mailUser2.hashCode() + drivenCarLicense2.hashCode() + chunckList2.hashCode();
            
            Trip t = new Trip(tripId);
            for (int i = startIndex1; i <= endIndex1; i++) {
                t.addChunckToTrip(chunckList1.get(i-1));
            }
            t.setCorrespondanceBool(true);

            for (int i = startIndex2; i <= endIndex2; i++) {
                t.addChunckToTrip(chunckList2.get(i-1));
            }
            
            t.setPrice(calculateSingleTripPrice(chunckList1, carEnergy1, carFiscalPower1)
                       + calculateSingleTripPrice(chunckList2, carEnergy2, carFiscalPower2));
            tripList.add(t);
            
        }
        lt.addAll(tripList);
        return tripList;

    }


    public static List<Trip> getLt() {
        return lt;
    }
    
    
}
