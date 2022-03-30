/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

/**
 *
 * @author almounah
 */
public class buttonHandler {
    public static boolean notifyLogin(String email, String pass) {
        return User.login(email, pass);
    }
}
