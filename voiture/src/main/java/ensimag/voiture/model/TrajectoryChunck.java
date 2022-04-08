/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model;

import java.nio.charset.Charset;
import java.security.MessageDigest;
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
    
    public static void addChunckToDB(Integer chunckIndex,
                                    String startDate,
                                    String startTime,
                                    String depCity,
                                    String arrCity,
                                    String travDist,
                                    String travDuration,
                                    String waitDelay,
                                    String depLat,
                                    String depLong,
                                    String arrLat,
                                    String arrLong,
                                    String selectetCarLicense) {
        Integer trajectId = User.getEmail().hashCode() + startDate.hashCode();
        Integer availableSeats = Car.getAvailableSeatsFromLicence(selectetCarLicense, User.getCarOwned());
        String query = "INSERT INTO sections " +
                       "(trajectoryId, sectionId, sectionWaitingDelay, availableSeats,"+
                       " travelDistance, travelDuration, cityArrival, cityDeparture, latArrival," +
                       " longArrival, latDeparture, longDeparture, sectionStartDate) " +
                       "VALUES " +
                       "(" + trajectId + "," + chunckIndex + "," + waitDelay +
                         availableSeats + "," + travDist + "," + travDuration +
                       ",\"" + arrCity + "\",\"" + depCity + "\"," + arrLat + "," +
                         arrLong + "," + depLat + "," + depLong;
    }
}
