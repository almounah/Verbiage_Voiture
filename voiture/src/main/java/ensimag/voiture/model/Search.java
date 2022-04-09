/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model;

import ensimag.voiture.model.dataBase.Trip;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author almounah
 */
public class Search {
    public static List<Trip> searchForTripNoCorresp(String cityDep, String cityArr, LocalDateTime startTime) {
        String query = "Select "+
                       "s1.cityArrival, " +
                       "s1.cityDeparture, " +
                       "s1.availableSeats, " +
                       "userFirstName, " +
                       "userLastName, " +
                       "s1.sectionStartDate, " +
                       "s1.travelDuration, " +
                       "s1.travelDistance, " +
                       "s1.sectionWaitingDelay" +
                       "FROM " +
                       "sections s1, sections s2, trajectory, userInfo" +
                       "WHERE " +
                       "s1.trajectId=s2.trajectId AND " +
                       "(s1.cityDeparture=? OR s2.cityArrival=?) AND " +
                       "< s1.sectionStartDate < ";
        return null;
    }
}
