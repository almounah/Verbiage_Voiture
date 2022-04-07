/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model;

import java.util.Date;

/**
 *
 * @author almounah
 */
public class TrajectoryChunck {
    
    private String trajectoryId;
    
    private int sectionId;
    private int sectionWaitingDelay;
    private int travelDistance;
    private int travelDuration;
    private int availableSeats;
    private City cityArrival;
    private City cityDeparture;
    private Date sectionStartDate;

    public TrajectoryChunck(String trajectoryId, int sectionId, int sectionWaitingDelay, int travelDistance, int travelDuration, int availableSeats, City cityArrival, City cityDeparture, Date sectionStartDate) {
        this.trajectoryId = trajectoryId;
        this.sectionId = sectionId;
        this.sectionWaitingDelay = sectionWaitingDelay;
        this.travelDistance = travelDistance;
        this.travelDuration = travelDuration;
        this.availableSeats = availableSeats;
        this.cityArrival = cityArrival;
        this.cityDeparture = cityDeparture;
        this.sectionStartDate = sectionStartDate;
    }
  
}
