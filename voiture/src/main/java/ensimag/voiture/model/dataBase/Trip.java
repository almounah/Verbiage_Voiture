/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model.dataBase;

import ensimag.voiture.model.QueriesRunner;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author almounah
 */
public class Trip {
    private Integer tripId;
    private Double price;
    private List<TrajectoryChunck> listChuncks;
    private Boolean correspondanceBool = false;
    private String driverFirstName;

    public Trip(Integer tripId) {
        this.tripId = tripId;
        listChuncks = new ArrayList<>();
        price = 0.0;
    }
    
    public void addTripToDB() {
        String query = "INSERT INTO carPool (tripId, mailUser) VALUES (?, ?)";
        List param = Arrays.asList(tripId, User.getEmail());
        List<String> paramType = Arrays.asList("Integer", "String");
        
        QueriesRunner.QuerySetter(query, param, paramType, false);
        query = "INSERT INTO tripPlan (tripId, trajectId, sectionId) VALUES (?, ?, ?)";
        paramType = Arrays.asList("Integer", "Integer", "Integer");
        String query2 = "UPDATE sections SET availableSeats = availableSeats - 1 WHERE trajectId = ? AND sectionId = ?";
        List<String> paramType2 = Arrays.asList("Integer", "Integer");
        List param2;
        for (TrajectoryChunck tc : listChuncks) {
            param = Arrays.asList(tripId, tc.getTrajectoryId(), tc.getSectionId());
            param2 = Arrays.asList(tc.getTrajectoryId(), tc.getSectionId());
            QueriesRunner.QuerySetter(query, param, paramType, false);
            QueriesRunner.QuerySetter(query2, param2, paramType2, false);
        }
        QueriesRunner.commit();
    }
    
    public void addChunckToTrip(TrajectoryChunck tc) {
        listChuncks.add(tc);
    }
    
    public void addChunckToTrip(TrajectoryChunck tc, Integer pos) {
        listChuncks.add(pos, tc);
    }

    public void setPrice(Double price) {
        
        this.price = price;
    }

    public Integer getTripId() {
        return tripId;
    }

    public Double getPrice() {
        return price;
    }

    public List<TrajectoryChunck> getListChuncks() {
        return listChuncks;
    }

    public Boolean getCorrespondanceBool() {
        return correspondanceBool;
    }

    public void setTripId(Integer tripId) {
        this.tripId = tripId;
    }

    public void setCorrespondanceBool(Boolean correspondanceBool) {
        this.correspondanceBool = correspondanceBool;
    }

    @Override
    public String toString() {
        return listChuncks.toString();
    }
    
    
    
    
}
