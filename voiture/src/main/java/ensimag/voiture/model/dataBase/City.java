package ensimag.voiture.model.dataBase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author almounah
 */
public class City {
    private String cityName;
    private float latetude;
    private float longetude;

    public City(String cityName, float latetude, float longetude) {
        this.cityName = cityName;
        this.latetude = latetude;
        this.longetude = longetude;
    }

    public String getCityName() {
        return cityName;
    }

    public float getLatetude() {
        return latetude;
    }

    public float getLongetude() {
        return longetude;
    }
    
}
