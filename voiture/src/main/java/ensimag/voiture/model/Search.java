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
    public static List<Trip> searchForTripNoCorresp(String cityDep, String cityArr, LocalDateTime startTime) {
        String query = 
                    "Select " +
                    "  DISTINCT s1.trajectId, " +
                    "  s2.sectionId, " +
                    "  s1.sectionId, " +
                    "  mailUser," +
                    "  drivenLicenseCar" +
                    "FROM " +
                    "  sections s1, " +
                    "  sections s2, " +
                    "  trajectory t, " +
                    "  userInfo " +
                    "WHERE " +
                    "  s1.trajectId = s2.trajectId " +
                    "  AND t.trajectId = s1.trajectId " +
                    "  AND ( " +
                    "    s1.cityArrival = ? " +
                    "    AND s2.cityDeparture = ? " +
                    "  ) " +
                    "  AND driverMail = mailUser " +
                    "  AND 0 not IN ( " +
                    "    Select " +
                    "      availableSeats " +
                    "    FROM " +
                    "      sections s3 " +
                    "    WHERE " +
                    "      s3.trajectId = s1.trajectId " +
                    "      AND s2.sectionId <= s3.sectionId " +
                    "      AND s3.sectionId <= s1.sectionId " +
                    "  ) ";

        List param = Arrays.asList(cityArr, cityDep);
        List<String> paramType = Arrays.asList("String", "String");
        Map<Integer, List> trajRes = QueriesRunner.QueryGetter(query, param, paramType);
        for (Map.Entry<Integer, List> tripInfo : trajRes.entrySet()) {
            Integer trajectoryId = ((BigDecimal) tripInfo.getValue().get(0)).intValue();
            Integer startIndex = ((BigDecimal) tripInfo.getValue().get(1)).intValue();
            Integer endIndex = ((BigDecimal) tripInfo.getValue().get(2)).intValue();
            String mailUser = (String) tripInfo.getValue().get(3);
            String driverLicenseCar = (String) tripInfo.getValue().get(4);
            
            Trajectory traj = new Trajectory(trajectoryId, mailUser, driverLicenseCar);
            List<TrajectoryChunck> chunckList = traj.getTrajectoryInforFromDB(trajectoryId);
        }
        List<Trip> tripList = new ArrayList<>();
        return tripList;
    }
}
