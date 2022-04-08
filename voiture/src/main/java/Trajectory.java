/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model;

import java.util.List;

/**
 *
 * @author almounah
 */
public class Trajectory {
    private String trajectoryId;
    private String driverMail;
    private String drivenLicenseCar;
    private List<TrajectoryChunck> trajectoryChunckList;

    public Trajectory(String trajectoryId, String driverMail, String drivenLicenseCar, List<TrajectoryChunck> trajectoryChunckList) {
        this.trajectoryId = trajectoryId;
        this.driverMail = driverMail;
        this.drivenLicenseCar = drivenLicenseCar;
        this.trajectoryChunckList = trajectoryChunckList;
    }
    
    
}
