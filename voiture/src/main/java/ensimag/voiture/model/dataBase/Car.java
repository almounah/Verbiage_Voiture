package ensimag.voiture.model.dataBase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import ensimag.voiture.model.QueriesRunner;
import ensimag.voiture.model.CarEnergy;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author almounah
 */
public class Car {

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public CarEnergy getCarEnergy() {
        return carEnergy;
    }

    public float getCarFiscalPower() {
        return carFiscalPower;
    }

    public Integer getIntialSeatsNumber() {
        return intialSeatsNumber;
    }
    private String licensePlate;
    private String carModel;
    private String carBrand;
    private CarEnergy carEnergy;
    private float carFiscalPower;
    private Integer intialSeatsNumber;

    public Car(String licensePlate, String carBrand, String carModel, CarEnergy carEnergy, float carFiscalPower, int intialSeatsNumber) {
        this.licensePlate = licensePlate;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carEnergy = carEnergy;
        this.carFiscalPower = carFiscalPower;
        this.intialSeatsNumber = intialSeatsNumber;
    }
    
    public static boolean addCarToDB(String licensePlate, String carBrand,
                                     String carModel, CarEnergy carEnergy,
                                     float carFiscalPower, int intialSeatsNumber) {
        String query = "INSERT INTO carInfo " +
                       "(licensePlate, carBrand, carEnergy, carModel, carFiscalPower, intialSeatsNumber) " +
                       "VALUES " +
                       "(?, ?, ?, ?, ?, ?)";
        List param = Arrays.asList(licensePlate, carBrand, carEnergy.toString(), carModel, carFiscalPower, intialSeatsNumber);
        List<String> paramType = Arrays.asList("String", "String", "String", "String", "Float", "Integer");
        return QueriesRunner.QuerySetter(query, param, paramType, true);
    }
    
    public static Integer getAvailableSeatsFromLicence(String cl, List<Car> lc) {
        for (Car c : lc) {
            if (c.getLicensePlate().equals(cl)) {
                return c.getIntialSeatsNumber();
            }
        }
        
        return -1;
    }
     
    
}
