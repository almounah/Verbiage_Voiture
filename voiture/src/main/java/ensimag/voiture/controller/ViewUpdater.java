/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

import ensimag.voiture.model.dataBase.Car;
import ensimag.voiture.model.dataBase.Trajectory;
import ensimag.voiture.model.dataBase.TrajectoryChunck;
import ensimag.voiture.model.dataBase.Trip;
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
import ensimag.voiture.view.ViewTripPage;
import java.time.LocalDateTime;
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
    
    public static void hideSearchResultPage(SearchPage sp, Boolean noCorresPart, Boolean correspPart) {
        
        if (noCorresPart) {
            sp.getArrCityLab1().hide();
            sp.getArrTime1().hide();
            sp.getDepCityLabel1().hide();
            sp.getDepTime1().hide();
            sp.getPassedByCityBox1().hide();
            sp.getPassesByLab1().hide();
             sp.getArrowLab1().hide();
            
        }
        
        if (correspPart) {
           
            sp.getArrCityLab2().hide();
            sp.getArrTime2().hide();
            sp.getDepCityLab2().hide();
            sp.getDepTime2().hide();
            sp.getPassedByCityBox2().hide();
            sp.getPassesByLab2().hide();
            sp.getArrowLab2().hide();
            sp.getCorrespondanceLab().hide();
        }
    }
    
    public static void showSearchResultPage(SearchPage sp, Boolean noCorresPart, Boolean correspPart) {
        
        if (noCorresPart) {
            sp.getArrCityLab1().show();
            sp.getArrTime1().show();
            sp.getDepCityLabel1().show();
            sp.getDepTime1().show();
            sp.getPassedByCityBox1().show();
            sp.getPassesByLab1().show();
            sp.getArrowLab1().show();
        }
        
        if (correspPart) {
            
            sp.getArrCityLab2().show();
            sp.getArrTime2().show();
            sp.getDepCityLab2().show();
            sp.getDepTime2().show();
            sp.getPassedByCityBox2().show();
            sp.getPassesByLab2().show();
            sp.getArrowLab2().show();
            sp.getCorrespondanceLab().show();
        }
    }
    
    public static void hideSearchResultPage(ViewTripPage sp, Boolean noCorresPart, Boolean correspPart) {
        
        if (noCorresPart) {
            sp.getArrCityLab1().hide();
            sp.getArrTime1().hide();
            sp.getDepCityLabel1().hide();
            sp.getDepTime1().hide();
            sp.getPassedByCityBox1().hide();
            sp.getPassesByLab1().hide();
            sp.getArrowLab1().hide();                        
            sp.getjButton1().hide();

            
        }
        
        if (correspPart) {
           
            sp.getArrCityLab2().hide();
            sp.getArrTime2().hide();
            sp.getDepCityLab2().hide();
            sp.getDepTime2().hide();
            sp.getPassedByCityBox2().hide();
            sp.getPassesByLab2().hide();
            sp.getArrowLab2().hide();
            sp.getCorrespondanceLab().hide();
            sp.getjButton2().hide();
        }
    }
    
    public static void showSearchResultPage(ViewTripPage sp, Boolean noCorresPart, Boolean correspPart) {
        
        if (noCorresPart) {
            sp.getArrCityLab1().show();
            sp.getArrTime1().show();
            sp.getDepCityLabel1().show();
            sp.getDepTime1().show();
            sp.getPassedByCityBox1().show();
            sp.getPassesByLab1().show();
            sp.getArrowLab1().show();
            sp.getjButton1().show();
        }
        
        if (correspPart) {
            
            sp.getArrCityLab2().show();
            sp.getArrTime2().show();
            sp.getDepCityLab2().show();
            sp.getDepTime2().show();
            sp.getPassedByCityBox2().show();
            sp.getPassesByLab2().show();
            sp.getArrowLab2().show();
            sp.getCorrespondanceLab().show();
            sp.getjButton2().show();
        }
    }
    
    public static void showSearchTripPage() {
        SearchPage sp = new SearchPage();
        
        hideSearchResultPage(sp, Boolean.TRUE, Boolean.TRUE);
        sp.getBookButton().setEnabled(false);
        
        sp.show();
    }
    
    public static void showSearchTripResults(List<Trip> lt, SearchPage sp, boolean next) {
        if (lt.isEmpty()) {
            hideSearchResultPage(sp, Boolean.TRUE, Boolean.TRUE);
            sp.getBookButton().setEnabled(false);
            return;
        }
        sp.getBookButton().setEnabled(true);
        showSearchResultPage(sp, Boolean.TRUE, Boolean.FALSE);
        Integer tripIndex = Integer.parseInt(sp.getTripIndex().getText()) - 1;
        if (next)
            tripIndex = (tripIndex + 1)%lt.size();
        else
            tripIndex = (tripIndex + lt.size() - 1)%lt.size();
        Integer newIndex = tripIndex + 1;
        sp.getTripIndex().setText(newIndex.toString());
        sp.getTotaltrips().setText("/" + lt.size());
        
        Trip tshow = lt.get(tripIndex);
        List<TrajectoryChunck> ltc = tshow.getListChuncks();
        Integer lastChunckCorresp = 0;
        if (tshow.getCorrespondanceBool()) {
            Integer trajectId = ltc.get(0).getTrajectoryId();
            while(trajectId.equals(ltc.get(lastChunckCorresp).getTrajectoryId())) {
                lastChunckCorresp++;
            }
        } else {
            lastChunckCorresp = ltc.size();
        }
        System.out.println(lastChunckCorresp + " lastChucnkCorresp");
        sp.getDepCityLabel1().setText(ltc.get(0).getCityDeparture().getCityName());
        sp.getArrCityLab1().setText(ltc.get(lastChunckCorresp-1).getCityArrival().getCityName());
        Integer passByCityNum = lastChunckCorresp-1;
        if (passByCityNum == 0) {
            sp.getPassesByLab1().setText("Direct Travel");
        } else {
            sp.getPassesByLab1().setText("Passes by " + passByCityNum + " cities");
        }
        
        sp.getPassedByCityBox1().removeAllItems();
        for (int i = 0; i < lastChunckCorresp -1; i++) {
            sp.getPassedByCityBox1().addItem(tshow.getListChuncks().get(i).getCityArrival().getCityName());
        }
        sp.getDepTime1().setText(ltc.get(0).getSectionStartDate().toString());
        LocalDateTime ldtLastChunck = ltc.get(lastChunckCorresp - 1).getSectionStartDate();
        ldtLastChunck = ldtLastChunck.plusMinutes(ltc.get(lastChunckCorresp-1).getTravelDuration());
        ldtLastChunck = ldtLastChunck.plusMinutes(ltc.get(lastChunckCorresp-1).getSectionWaitingDelay());
        sp.getArrTime1().setText(ldtLastChunck.toString());
        
        sp.getTripPriceLab().setText(tshow.getPrice().toString());
        
        if (lastChunckCorresp == tshow.getListChuncks().size()) {
            hideSearchResultPage(sp, Boolean.FALSE, Boolean.TRUE);
            return;
        }
        
        showSearchResultPage(sp, Boolean.FALSE, Boolean.TRUE);
        Integer lastChunck = tshow.getListChuncks().size()-1;
        
        sp.getDepCityLab2().setText(ltc.get(lastChunckCorresp).getCityDeparture().getCityName());
        sp.getArrCityLab2().setText(ltc.get(lastChunck).getCityArrival().getCityName());
        Integer passByCityNum2 = lastChunck - lastChunckCorresp;
        if (passByCityNum2 == 0) {
            sp.getPassesByLab2().setText("Direct Travel");
        } else {
            sp.getPassesByLab2().setText("Passes by " + passByCityNum2 + " cities");
        }
        
        sp.getPassedByCityBox2().removeAllItems();
        for (int i = lastChunckCorresp; i < lastChunck; i++) {
            sp.getPassedByCityBox2().addItem(tshow.getListChuncks().get(i).getCityArrival().getCityName());
        }
        sp.getDepTime2().setText(ltc.get(lastChunckCorresp).getSectionStartDate().toString());
        ldtLastChunck = ltc.get(lastChunck).getSectionStartDate();
        ldtLastChunck = ldtLastChunck.plusMinutes(ltc.get(lastChunck).getTravelDuration());
        ldtLastChunck = ldtLastChunck.plusMinutes(ltc.get(lastChunck).getSectionWaitingDelay());
        sp.getArrTime2().setText(ldtLastChunck.toString());
        
    }
    
    public static void showBookedTrip(List<Trip> lt) {
        ViewTripPage vtp = new ViewTripPage();
        showBookedTrip(lt, vtp, true);
        vtp.show();
    }
    
    public static void ShowValidateAndPay() {
        //ViewValidadedTrip vtrp = new ViewValidadedTrip(); 
    }
    
    public static void showBookedTrip(List<Trip> lt, ViewTripPage sp, boolean next) {
        if (lt.isEmpty()) {
            return;
        }
        sp.getBookButton().setEnabled(true);
        showSearchResultPage(sp, Boolean.TRUE, Boolean.FALSE);
        Integer tripIndex = Integer.parseInt(sp.getTripIndex().getText()) - 1;
        if (next)
            tripIndex = (tripIndex + 1)%lt.size();
        else
            tripIndex = (tripIndex + lt.size() - 1)%lt.size();
        Integer newIndex = tripIndex + 1;
        sp.getTripIndex().setText(newIndex.toString());
        sp.getTotaltrips().setText("/" + lt.size());
        
        Trip tshow = lt.get(tripIndex);
        List<TrajectoryChunck> ltc = tshow.getListChuncks();
        Integer lastChunckCorresp = 0;
        if (tshow.getCorrespondanceBool()) {
            Integer trajectId = ltc.get(0).getTrajectoryId();
            while(trajectId.equals(ltc.get(lastChunckCorresp).getTrajectoryId())) {
                lastChunckCorresp++;
            }
        } else {
            lastChunckCorresp = ltc.size();
        }
        System.out.println(lastChunckCorresp + " lastChucnkCorresp");
        sp.getDepCityLabel1().setText(ltc.get(0).getCityDeparture().getCityName());
        sp.getArrCityLab1().setText(ltc.get(lastChunckCorresp-1).getCityArrival().getCityName());
        Integer passByCityNum = lastChunckCorresp-1;
        if (passByCityNum == 0) {
            sp.getPassesByLab1().setText("Direct Travel");
        } else {
            sp.getPassesByLab1().setText("Passes by " + passByCityNum + " cities");
        }
        
        sp.getPassedByCityBox1().removeAllItems();
        for (int i = 0; i < lastChunckCorresp -1; i++) {
            sp.getPassedByCityBox1().addItem(tshow.getListChuncks().get(i).getCityArrival().getCityName());
        }
        sp.getDepTime1().setText(ltc.get(0).getSectionStartDate().toString());
        LocalDateTime ldtLastChunck = ltc.get(lastChunckCorresp - 1).getSectionStartDate();
        ldtLastChunck = ldtLastChunck.plusMinutes(ltc.get(lastChunckCorresp-1).getTravelDuration());
        ldtLastChunck = ldtLastChunck.plusMinutes(ltc.get(lastChunckCorresp-1).getSectionWaitingDelay());
        sp.getArrTime1().setText(ldtLastChunck.toString());
        
        sp.getTripPriceLab().setText(tshow.getPrice().toString());
        
        if (lastChunckCorresp == tshow.getListChuncks().size()) {
            hideSearchResultPage(sp, Boolean.FALSE, Boolean.TRUE);
            return;
        }
        
        showSearchResultPage(sp, Boolean.FALSE, Boolean.TRUE);
        Integer lastChunck = tshow.getListChuncks().size()-1;
        
        sp.getDepCityLab2().setText(ltc.get(lastChunckCorresp).getCityDeparture().getCityName());
        sp.getArrCityLab2().setText(ltc.get(lastChunck).getCityArrival().getCityName());
        Integer passByCityNum2 = lastChunck - lastChunckCorresp;
        if (passByCityNum2 == 0) {
            sp.getPassesByLab2().setText("Direct Travel");
        } else {
            sp.getPassesByLab2().setText("Passes by " + passByCityNum2 + " cities");
        }
        
        sp.getPassedByCityBox2().removeAllItems();
        for (int i = lastChunckCorresp; i < lastChunck; i++) {
            sp.getPassedByCityBox2().addItem(tshow.getListChuncks().get(i).getCityArrival().getCityName());
        }
        sp.getDepTime2().setText(ltc.get(lastChunckCorresp).getSectionStartDate().toString());
        ldtLastChunck = ltc.get(lastChunck).getSectionStartDate();
        ldtLastChunck = ldtLastChunck.plusMinutes(ltc.get(lastChunck).getTravelDuration());
        ldtLastChunck = ldtLastChunck.plusMinutes(ltc.get(lastChunck).getSectionWaitingDelay());
        sp.getArrTime2().setText(ldtLastChunck.toString());
    }
}
