/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

import ensimag.voiture.model.dataBase.Car;
import ensimag.voiture.model.CarEnergy;
import ensimag.voiture.model.QueriesRunner;
import ensimag.voiture.model.Search;
import ensimag.voiture.model.TimeCalculator;
import ensimag.voiture.model.dataBase.Trajectory;
import ensimag.voiture.model.dataBase.TrajectoryChunck;
import ensimag.voiture.model.dataBase.Trip;
import ensimag.voiture.model.dataBase.User;
import ensimag.voiture.view.AddTrajPage;
import ensimag.voiture.view.CarHomePage;
import ensimag.voiture.view.LoginPage;
import ensimag.voiture.view.SearchPage;
import ensimag.voiture.view.TrajectoryHomePage;
import ensimag.voiture.view.ViewTripPage;
import java.sql.Savepoint;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    
    public static void notifyUpdateWallet(String walletText) {
        User.updateWallet(Float.parseFloat(walletText));
        ViewUpdater.viewProfile();
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
    
    public static void notifyAddTrajectory(TrajectoryHomePage thp) {
        User.getListOfCarDB();
        ViewUpdater.showNewTrajPage();
        thp.dispose();
    }
    
    public static void notifyViewNextChunck(TrajectoryHomePage thp, boolean next) {
        ViewUpdater.showTrajectory(true, thp);
    }
    
    public static void notifyAddAnotherChunck(Integer chunckIndex,
                                              LocalDate startDate,
                                              LocalTime startTime,
                                              String depCity,
                                              String arrCity,
                                              String travDist,
                                              String travDuration,
                                              String waitDelay,
                                              String depLat,
                                              String depLong,
                                              String arrLat,
                                              String arrLong,
                                              String selectedCarLicense,
                                              AddTrajPage atp,
                                              boolean lastChunck) {
        LocalDateTime startDateTime = startDate.atTime(startTime);
        if (chunckIndex == 1) {
            Trajectory.addTrajDB(User.getEmail().hashCode() + startDateTime.hashCode(),
                                 User.getEmail(), selectedCarLicense);
            TimeCalculator.setPreviousChunckEndDate(startDateTime);
        }
        TrajectoryChunck.addChunckToDB(chunckIndex,
                                       startDateTime,
                                       depCity, arrCity,
                                       Integer.parseInt(travDist),
                                       Integer.parseInt(travDuration),
                                       Integer.parseInt(waitDelay),
                                       Float.parseFloat(depLat),
                                       Float.parseFloat(depLong),
                                       Float.parseFloat(arrLat),
                                       Float.parseFloat(arrLong),
                                       selectedCarLicense);
        if (lastChunck) {
            atp.dispose();
            QueriesRunner.commit();
            User.getListOfTrajectoryDB();
        } else {
            ViewUpdater.showAddNextChunck(atp);
        }
    }
    
    public static void notifyViewSearchTripPage() {
        ViewUpdater.showSearchTripPage();
    }
    
    public static void notifyClosedBeforeFinish() {
        QueriesRunner.rollback();
    }
    
    public static void notifySearchForTrips(String depCity, String arrCity,
                                            LocalDate startDate, LocalTime startTime,
                                            SearchPage sp, boolean next) {
        LocalDateTime ldt = startDate.atTime(startTime);
        Search.searchForTripNoCorresp(depCity, arrCity, ldt);
        Search.searchForTripOneCorresp(depCity, arrCity, ldt);
        System.out.println("lt:" + Search.getLt());
        ViewUpdater.showSearchTripResults(Search.getLt(), sp, next);
    }
    
    public static void notifyShowNextTrip(SearchPage sp, boolean next) {
        ViewUpdater.showSearchTripResults(Search.getLt(), sp, next);
    }
    
    public static void notifyBooktrip(SearchPage sp) {
        Trip tr = Search.getLt().get(Integer.parseInt(sp.getTripIndex().getText())-1);
        tr.addTripToDB();
        sp.dispose();
    }
    
    public static void notifyShowUpcomingTrip() {
        User.getUserListOfTripsDB();
        ViewUpdater.showBookedTrip(User.getListTrip());
    }
    
    public static void notifyShowNextUpcomingTrip(boolean next, ViewTripPage vtp) {
        ViewUpdater.showBookedTrip(User.getListTrip(), vtp, next);
    }
    
    public static void notifyCancelTrajectory(int trajIndex) {
        User.cancelTraj(trajIndex);
        ViewUpdater.showTrajectory();
    }
    
    public static void notifyValidateAndPay(String tripIndex) {
        User.validateTrip(Integer.parseInt(tripIndex)-1);
        notifyShowUpcomingTrip();
    }
}
