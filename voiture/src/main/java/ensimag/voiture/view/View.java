/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.view;

import ensimag.voiture.controller.ButtonHandler;

/**
 *
 * @author almounah
 */
public class View {
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private UserWelcomePage userWelcomePage;

    public View() {
        this.loginPage = new LoginPage();
        this.registerPage = new RegisterPage();
        this.userWelcomePage = new UserWelcomePage();
    }
    
    

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public RegisterPage getRegisterPage() {
        return registerPage;
    }

    public UserWelcomePage getUserWelcomePage() {
        return userWelcomePage;
    }
    
    
}
