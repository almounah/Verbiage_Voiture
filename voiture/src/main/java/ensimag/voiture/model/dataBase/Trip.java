/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model.dataBase;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author almounah
 */
public class Trip {
    private Integer tripId;
    private Double price;
    private List<TrajectoryChunck> listChuncks;

    public Trip(Integer tripId) {
        this.tripId = tripId;
        listChuncks = new ArrayList<>();
        price = 0.0;
    }
    
    public void addChunckToTrip(TrajectoryChunck tc) {
        listChuncks.add(tc);
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
    
    
    
}
