/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.controller;


import java.util.List;


/**
 *
 * @author almounah
 */
public class User {
    private static String email;
    private static String password;
    private static String city;
    private static String firstName;
    private static String lastName;
    private static int userWallet;



    public User() {
    }
    
    
    public static boolean login(String email, String password) {
        boolean result;
        String query = "SELECT * FROM userInfo WHERE " +
                "mailUser=\"" + email + "\" AND " +
                "userPassword=\"" + password + "\";";
        List rslt = QueriesRunner.QueryGetter(query);
        System.out.println(rslt);
        result = !rslt.isEmpty();
        if (result) {
            User.email = (String) rslt.get(0);
        }
        return result;
    }
    
    public static boolean createAccount(String email, String password,
                              String city, String firstName, String lastName) {
        String query = "INSERT INTO userInfo " +
                       "(mailUser, userLastName, userFirstName, userCity, userPassword, userWallet) " +
                       "VALUES " +
                       "(\"" + email + "\",\"" + lastName + "\",\"" +
                        firstName + "\",\"" + city + "\",\"" + password +
                       "\",0);";
        return QueriesRunner.QuerySetter(query);
    }

    public void setPassword(String password) {
        User.password = password;
    }

    public void setEmail(String email) {
        User.email = email;
    }
    
        public static void setCity(String city) {
        User.city = city;
    }

    public static String getCity() {
        return city;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static int getUserWallet() {
        return userWallet;
    }

    public static void setFirstName(String firstName) {
        User.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        User.lastName = lastName;
    }

    public static void setWalletl(int Walletl) {
        User.userWallet = Walletl;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
