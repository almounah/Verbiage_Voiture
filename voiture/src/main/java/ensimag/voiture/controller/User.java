/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author almounah
 */
public class User {
    private static String email;
    private static String password;

    public User() {
        email = null;
        password = null;
    }
    
    
    public static boolean login(String email, String password) {
        boolean result;
        String query = "SELECT * FROM userInfo WHERE " +
                "mailUser=\"" + email + "\" AND " +
                "userPassword=\"" + password + "\";";
        System.out.println(query);
        List rslt = QueriesRunner.QueryGetter(query);
        result = !rslt.isEmpty();
        return result;
    }
    
    public void createAccount(String email, String password,
                              String city, String firstName, String lastName) {
        String query = "INSERT INTO userInfo " +
                       "(mailUser, userLastName, userFirstName, userPassword, userWallet) " +
                       "VALUES " +
                       "(" + email + "," + lastName + "," + firstName + "," + password + ",0);";
        QueriesRunner.QuerySetter(query);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
