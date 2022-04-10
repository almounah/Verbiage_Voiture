package ensimag.voiture.model.dataBase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import ensimag.voiture.model.QueriesRunner;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author almounah
 */
public class Trajectory {
    private Integer trajectoryId;
    private String driverMail;
    private String drivenLicenseCar;
    private List<TrajectoryChunck> trajectoryChunckList;

    public Trajectory(Integer trajectoryId, String driverMail, String drivenLicenseCar, List<TrajectoryChunck> trajectoryChunckList) {
        this.trajectoryId = trajectoryId;
        this.driverMail = driverMail;
        this.drivenLicenseCar = drivenLicenseCar;
        this.trajectoryChunckList = trajectoryChunckList;
    }

    public static boolean addTrajDB(Integer trajId, String driverMail, String drivenLicenseCar) {
        String query = "Insert INTO trajectory" +
                       "(trajectId, driverMail, drivenLicenseCar) " +
                       "VALUES " +
                       "(?, ?, ?)";
        List param = Arrays.asList(trajId, driverMail, drivenLicenseCar);
        List<String> paramType = Arrays.asList("Integer", "String", "String");
        return QueriesRunner.QuerySetter(query, param, paramType, true); 
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
    
    
    
}
