/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

import ensimag.voiture.view.LoginPage;
import ensimag.voiture.view.UserProfile;
import ensimag.voiture.view.UserWelcomePage;
import ensimag.voiture.view.View;

/**
 *
 * @author almounah
 */
public class ViewUpdater {
    private View view;

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
    
}
