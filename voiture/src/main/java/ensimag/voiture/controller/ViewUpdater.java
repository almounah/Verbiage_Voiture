/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

import ensimag.voiture.model.dataBase.Car;
import ensimag.voiture.model.dataBase.Trajectory;
import ensimag.voiture.model.dataBase.TrajectoryChunck;
import ensimag.voiture.model.dataBase.User;
import ensimag.voiture.view.AddCarPage;
import ensimag.voiture.view.AddTrajPage;
import ensimag.voiture.view.LoginPage;
import ensimag.voiture.view.UserProfile;
import ensimag.voiture.view.UserWelcomePage;
import ensimag.voiture.view.View;
import ensimag.voiture.view.CarHomePage;
import ensimag.voiture.view.SearchPage;
import ensimag.voiture.view.TrajectoryHomePage;
import java.util.List;

/**
 *
 * @author almounah
 */
public class ViewUpdater {
    private View view;
    private static int viewedCarIndex = -1; 

    public ViewUpdater(View view) {
        this.view = view;
    }

    public static void loginFail(LoginPage loginPage) {
        loginPage.getStatusLab().setText("Login Failed, Register");
    }
    
    public static void loginSuccess(LoginPage loginPage, String name) {
        loginPage.dispose();
        UserWelcomePage userWelcomePage = new UserWelcomePage();
        userWelcomePage.getWelcomeTextLab().setText("Welcome " + name);
        userWelcomePage.show();
    }
    
    public static void viewProfile() {
        UserProfile userProfile = new UserProfile();
        userProfile.getEnteredCity().setText(User.getCity());
        userProfile.getEnteredEmail().setText(User.getEmail());
        userProfile.getEnteredFName().setText(User.getFirstName());
        userProfile.getEnteredLastName().setText(User.getLastName());
        userProfile.show();
        
    }
    
    public static void showCar(boolean next) {
        CarHomePage carHomePage = new CarHomePage();
        showCar(next, carHomePage);
        carHomePage.show();
    }
    
    public static void showCar(boolean next, CarHomePage carHomePage) {
        List<Car> clist = User.getCarOwned();
        int totalCarNumber = clist.size();
        if (totalCarNumber == 0) {
            carHomePage.getNorCarLab().setText("No Car Yet ...");
            carHomePage.show();
            return;
        }
        if (next) {
            viewedCarIndex = (viewedCarIndex + 1)%totalCarNumber;   
        } else {
            viewedCarIndex = (viewedCarIndex +totalCarNumber - 1)%totalCarNumber;
        }
        Car car = clist.get(viewedCarIndex);
        carHomePage.getLicensePlateText().setText(car.getLicensePlate());
        carHomePage.getModelText().setText(car.getCarModel());
        carHomePage.getBrandText().setText(car.getCarBrand());
        carHomePage.getEnergyText().setText(car.getCarEnergy().toString());
        carHomePage.getInitialSeatsText().setText(car.getIntialSeatsNumber().toString());
        carHomePage.getFiscalPowerText().setText(String.valueOf(car.getCarFiscalPower()));
        
    }
    
    public static void showNewCarPage() {
        AddCarPage addCarPage = new AddCarPage();
        addCarPage.show();
    }
    
    public static void showTrajectory() {
        TrajectoryHomePage trajectoryHomePage = new TrajectoryHomePage();
        for (int i = 0; i < User.getListProposedTraj().size() ; i++) {
            trajectoryHomePage.getProposedTrajBox().addItem("Traj" + (i+1));
        }
        showTrajectory(true, trajectoryHomePage);
    }
    
    
    public static void showChunck(TrajectoryChunck c, TrajectoryHomePage thp) {
        thp.getAvailSeatsText().setText(c.getAvailableSeats().toString());
        thp.getDepCityText().setText(c.getCityDeparture().getCityName());
        thp.getArrCityText().setText(c.getCityArrival().getCityName());
        thp.getTravDistText().setText(c.getTravelDistance().toString());
        thp.getTravDurText().setText(c.getTravelDuration().toString());
        thp.getWaitDelayText().setText(c.getSectionWaitingDelay().toString());
        thp.getStartDateText().setText(c.getSectionStartDate().toString());
        thp.getDepLat().setText(c.getCityDeparture().getLatetude().toString());
        thp.getDepLong().setText(c.getCityDeparture().getLongetude().toString());
        thp.getArrLong().setText(c.getCityArrival().getLongetude().toString());
        thp.getArrLat().setText(c.getCityArrival().getLatetude().toString());
        
    }
    
    
    public static void showTrajectory(boolean next, TrajectoryHomePage trajectoryHomePage) {
        List<Trajectory> userTrajList = User.getListProposedTraj();
        if (userTrajList.isEmpty()) {
            trajectoryHomePage.getNotrajlab().setText("NO TRAJ YET");
            trajectoryHomePage.getChunckIndex().setText(null);
            trajectoryHomePage.getTotalChunck().setText("/");
        } else {
            
            Integer trajIndex = trajectoryHomePage.getProposedTrajBox().getSelectedIndex();
            Trajectory viewedTraj = userTrajList.get(trajIndex);
            List<TrajectoryChunck> chunckList = viewedTraj.getTrajectoryChunckList();
            
            Integer chunckIndex = Integer.parseInt(trajectoryHomePage.getChunckIndex().getText());
            if (next) {
                chunckIndex = (chunckIndex + 1)%chunckList.size();
            } else {
                chunckIndex = (chunckIndex + chunckList.size() - 1)%chunckList.size();
            }
            trajectoryHomePage.getChunckIndex().setText((chunckIndex).toString());
            trajectoryHomePage.getTotalChunck().setText("/" + (chunckList.size() - 1));
            trajectoryHomePage.getCarLicenceText().setText(viewedTraj.getDrivenLicenseCar());
            trajectoryHomePage.getGlobDepCityText().setText(chunckList.get(0).getCityDeparture().getCityName());
            trajectoryHomePage.getGlobArrCityText().setText(chunckList.get(chunckList.size()-1).getCityArrival().getCityName());
            
            showChunck(viewedTraj.getTrajectoryChunckList().get(chunckIndex), trajectoryHomePage);
            
        }
        trajectoryHomePage.show();
    }
    
    
    public static void showNewTrajPage() {
        List<Car> clist = User.getCarOwned();
        AddTrajPage addTrajPage = new AddTrajPage();
        for (Car car : clist) {
            addTrajPage.getCarSelectBox().addItem(car.getLicensePlate());
        }
        addTrajPage.show();
    }
    
    public static void showAddNextChunck(AddTrajPage atp) {
        Integer chunckIndex = Integer.parseInt(atp.getChunckIndex().getText()) + 1;
        atp.getChunckIndex().setText(chunckIndex.toString());
        
        atp.getSelectedDate().datePicker.disable();
        atp.getSelectedDate().timePicker.disable();
        atp.getCarSelectBox().disable();
        atp.getDepCityText().disable();
        atp.getDepLatText().disable();
        atp.getDepLongText().disable();
        
        atp.getDepCityText().setText(atp.getArrCityText().getText());
        atp.getDepLatText().setText(atp.getArrLatText().getText());
        atp.getDepLongText().setText(atp.getArrLongText().getText());
        
        atp.getArrCityText().setText(null);
        atp.getArrLatText().setText(null);
        atp.getArrLongText().setText(null);
        
        atp.getTravDistText().setText(null);
        atp.getTravDurText().setText(null);
        
        atp.getWaitDelayText().setText(null);
    }
    
    public static void showSearchTripPage() {
        SearchPage sp = new SearchPage();
        sp.show();
    }
}
