/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

import ensimag.voiture.model.Car;
import ensimag.voiture.model.User;
import ensimag.voiture.view.AddCarPage;
import ensimag.voiture.view.LoginPage;
import ensimag.voiture.view.UserProfile;
import ensimag.voiture.view.UserWelcomePage;
import ensimag.voiture.view.View;
import ensimag.voiture.view.CarHomePage;
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
        trajectoryHomePage.show();
    }
}
