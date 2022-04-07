/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

import ensimag.voiture.model.Car;
import ensimag.voiture.model.CarEnergy;
import ensimag.voiture.model.User;
import ensimag.voiture.view.CarHomePage;
import ensimag.voiture.view.LoginPage;

/**
 *
 * @author almounah
 */
public class ButtonHandler {
    private ViewUpdater viewUpdater;

    public ButtonHandler(ViewUpdater viewUpdater) {
        this.viewUpdater = viewUpdater;
    }
    
    public static boolean notifyLogin(String email, String pass, LoginPage loginPage) {
        boolean b = User.login(email, pass);
        System.out.println(b);
        if (b) {
            ViewUpdater.loginSuccess(loginPage, User.getFirstName());
            
        } else {
            ViewUpdater.loginFail(loginPage);
        }
        return false;
    }
    
    public static boolean notifyRegister(String email, String pass,
                                         String city, String firstName,
                                         String lastName) {
        return User.createAccount(email, pass, city, firstName, lastName);
    }
    
    public static void notifyViewProfile() {
        ViewUpdater.viewProfile();
    }
    
    public static void notifyViewCar(boolean next) {
        User.getListOfCarDB();
        ViewUpdater.showCar(next);
    }
    
    public static void notifyViewCar(boolean next, CarHomePage carHomePage) {
        ViewUpdater.showCar(next, carHomePage);
    }
    
    public static void notifyValidateNewCar(String licensePlate, String carBrand,
                                            String carModel, String carEnergy,
                                            String carfiscalPower,String initialSeatsNumber) {
        boolean b = Car.addCarToDB(licensePlate, carBrand, carModel, CarEnergy.valueOf(carEnergy),
                                   Float.parseFloat(carfiscalPower), Integer.parseInt(initialSeatsNumber));
        boolean b2 = User.addCarOwnership(licensePlate);
        User.getCarOwned().add(new Car(licensePlate, carBrand, carModel,
                               CarEnergy.valueOf(carEnergy),
                               Float.parseFloat(carfiscalPower),
                               Integer.parseInt(initialSeatsNumber)));
        
        
    }
    
    public static void notifyViewTrajectory() {
        User.getListOfTrajectoryDB();
        ViewUpdater.showTrajectory();
    }
    
    public static void notifyAddTrajectory() {
        User.getListOfCarDB();
        ViewUpdater.showNewTrajPage();
    }
}
