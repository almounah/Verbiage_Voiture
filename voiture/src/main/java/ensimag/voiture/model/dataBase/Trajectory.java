package ensimag.voiture.model.dataBase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import ensimag.voiture.model.QueriesRunner;
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
public class Trajectory {
    private Integer trajectoryId;
    private String driverMail;
    private String drivenLicenseCar;
    private List<TrajectoryChunck> trajectoryChunckList;

    public Trajectory(Integer trajectoryId, String driverMail, String drivenLicenseCar) {
        this.trajectoryId = trajectoryId;
        this.driverMail = driverMail;
        this.drivenLicenseCar = drivenLicenseCar;
        this.trajectoryChunckList = new ArrayList<>();
    }

    public static boolean addTrajDB(Integer trajId, String driverMail, String drivenLicenseCar) {
        String query = "Insert INTO trajectory" +
                       "(trajectId, driverMail, drivenLicenseCar) " +
                       "VALUES " +
                       "(?, ?, ?)";
        List param = Arrays.asList(trajId, driverMail, drivenLicenseCar);
        List<String> paramType = Arrays.asList("Integer", "String", "String");
        return QueriesRunner.QuerySetter(query, param, paramType, false); 
    }
    
    public List<TrajectoryChunck> getTrajectoryInforFromDB(Integer trajectoryId) {      
        List<TrajectoryChunck> listChunck = new ArrayList<>();
        String query = 
            "SELECT "
            + "sectionId, "
            + "sectionWaitingDelay, "
            + "availableSeats, "
            + "travelDistance, "
            + "travelDuration, "
            + "cityArrival, "
            + "cityDeparture, "
            + "latArrival, " 
            + "longArrival, "
            + "latDeparture, " 
            + "longDeparture, "
            + "sectionStartDate "
            + "FROM sections WHERE "
            + "trajectId=?"
            + "ORDER BY sectionId";
        List param = new ArrayList<>();
        param.add(trajectoryId);
        List<String> paramType = new ArrayList<>();
        paramType.add("Integer");

        Map<Integer, List> chunckList = QueriesRunner.QueryGetter(query, param, paramType);
        for (Map.Entry<Integer, List> chunckInfo : chunckList.entrySet()) {
            Integer sectionId = ((BigDecimal) chunckInfo.getValue().get(0)).intValue();
            Integer sectionWaitingDelay = ((BigDecimal) chunckInfo.getValue().get(1)).intValue();
            Integer availableSeats = ((BigDecimal) chunckInfo.getValue().get(2)).intValue();
            Integer travelDistance = ((BigDecimal) chunckInfo.getValue().get(3)).intValue();
            Integer travelDuration = ((BigDecimal) chunckInfo.getValue().get(4)).intValue();
            City cityArrival = new City((String) chunckInfo.getValue().get(5),
                                        ((BigDecimal) chunckInfo.getValue().get(7)).floatValue(),
                                        ((BigDecimal) chunckInfo.getValue().get(8)).floatValue());
            City cityDeparture = new City((String) chunckInfo.getValue().get(6),
                                        ((BigDecimal) chunckInfo.getValue().get(9)).floatValue(),
                                        ((BigDecimal) chunckInfo.getValue().get(10)).floatValue());
            TIMESTAMP sectionStartDateO = (TIMESTAMP) chunckInfo.getValue().get(11);
            LocalDate ld;
            LocalDateTime sectionStartDate = null;
            try {
                ld = sectionStartDateO.dateValue().toLocalDate();
                sectionStartDate = ld.atTime(sectionStartDateO.timeValue().toLocalTime());
            } catch (SQLException ex) {
                Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
            }


            TrajectoryChunck trajectoryChunck = new TrajectoryChunck(trajectoryId,
                    sectionId, sectionWaitingDelay, travelDistance,
                    travelDuration, availableSeats, cityArrival, cityDeparture, sectionStartDate);
            listChunck.add(trajectoryChunck);
        }
        
        return listChunck;
    }

    public Integer getTrajectoryId() {
        return trajectoryId;
    }

    public String getDriverMail() {
        return driverMail;
    }

    public String getDrivenLicenseCar() {
        return drivenLicenseCar;
    }

    public List<TrajectoryChunck> getTrajectoryChunckList() {
        return trajectoryChunckList;
    }

    public void setTrajectoryChunckList(List<TrajectoryChunck> trajectoryChunckList) {
        this.trajectoryChunckList = trajectoryChunckList;
    }
    
    
    
}
