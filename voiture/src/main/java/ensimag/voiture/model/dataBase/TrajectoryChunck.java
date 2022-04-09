package ensimag.voiture.model.dataBase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import ensimag.voiture.model.QueriesRunner;
import ensimag.voiture.model.dataBase.Car;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Arrays;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    
    public static boolean addChunckToDB(Integer chunckIndex,
                                    LocalDateTime startDate,
                                    String depCity,
                                    String arrCity,
                                    Integer travDist,
                                    Integer travDuration,
                                    Integer waitDelay,
                                    float depLat,
                                    float depLong,
                                    float arrLat,
                                    float arrLong,
                                    String selectetCarLicense) {
        Integer trajectId = User.getEmail().hashCode() + startDate.hashCode();
        Integer availableSeats = Car.getAvailableSeatsFromLicence(selectetCarLicense, User.getCarOwned());
        Timestamp sectionStartDate = Timestamp.valueOf(startDate);
        String query = "INSERT INTO sections " +
                       "(trajectId, sectionId, sectionWaitingDelay, availableSeats,"+
                       " travelDistance, travelDuration, cityArrival, cityDeparture, latArrival," +
                       " longArrival, latDeparture, longDeparture, sectionStartDate) " +
                       "VALUES " +
                       "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        List param = Arrays.asList(trajectId, chunckIndex, waitDelay, availableSeats,
                                   travDist, travDuration, arrCity, depCity, arrLat,
                                   arrLong, depLat, depLong, sectionStartDate);
        List<String> paramType = Arrays.asList("Integer", "Integer", "Integer", "Integer",
                                               "Integer", "Integer", "String", "String",
                                               "Float", "Float", "Float", "Float", "Date");
        return QueriesRunner.QuerySetter(query, param, paramType, false);
    }
}
