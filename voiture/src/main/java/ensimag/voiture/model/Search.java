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
    private static List<Trip> lt;
    
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
                    "AND (s1.cityArrival=? " +
                    "AND s2.cityDeparture=?) " +
                    "AND driverMail = mailUser " +
                    "AND 0 not IN " +
                    "(Select " +
                    "availableSeats " +
                    "FROM " +
                    "sections s3 " +
                    "WHERE " +
                    "s3.trajectId = s1.trajectId " +
                    "AND s2.sectionId <= s3.sectionId " +
                    "AND s3.sectionId <= s1.sectionId) ";

        List param = Arrays.asList(cityArr, cityDep);
        List<String> paramType = Arrays.asList("String", "String");
        
        Map<Integer, List> trajRes = QueriesRunner.QueryGetter(query, param, paramType);
        List<Trip> tripList = new ArrayList<>();
        for (Map.Entry<Integer, List> tripInfo : trajRes.entrySet()) {
            
            
            Integer trajectoryId = ((BigDecimal) tripInfo.getValue().get(0)).intValue();
            Integer endIndex = ((BigDecimal) tripInfo.getValue().get(1)).intValue();
            Integer startIndex = ((BigDecimal) tripInfo.getValue().get(2)).intValue();
            Float carFiscalPower = ((BigDecimal) tripInfo.getValue().get(3)).floatValue();
            CarEnergy carEnergy = CarEnergy.valueOf((String) tripInfo.getValue().get(4));
            String drivenCarLicense = (String) tripInfo.getValue().get(5);
            String mailUser = (String) tripInfo.getValue().get(6);
            
            
            Trajectory traj = new Trajectory(trajectoryId, mailUser, drivenCarLicense);
            List<TrajectoryChunck> chunckList = traj.getTrajectoryInforFromDB(trajectoryId);
            
            Integer tripId = mailUser.hashCode() + drivenCarLicense.hashCode() + chunckList.hashCode();
            Trip t = new Trip(tripId);
            for (int i = startIndex; i <= endIndex; i++) {
                t.addChunckToTrip(chunckList.get(i-1));
            }
            
            t.setPrice(calculateSingleTripPrice(t, carEnergy, carFiscalPower));
            
            tripList.add(t);
        }
        lt = tripList;
        return tripList;
    }
    
    public static Double calculateSingleTripPrice(Trip t, CarEnergy e, Float carFiscalPower) {
        Double kmprice = e.getAlpha()*0.10*carFiscalPower;
        Double totDistance = 0.0;
        
        for (TrajectoryChunck tc : t.getListChuncks()) {
            totDistance += tc.getTravelDistance();
        }
        
        return kmprice*totDistance;
    }

    public static List<Trip> getLt() {
        return lt;
    }
    
    
}
