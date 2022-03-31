/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

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
}
