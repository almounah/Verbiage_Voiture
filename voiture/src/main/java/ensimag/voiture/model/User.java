/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ensimag.voiture.model;


import ensimag.voiture.controller.QueriesRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    private static float userWallet;
    private static List<Car> carOwned = new ArrayList<Car>();



    public User() {
    }
    
    
    public static boolean login(String email, String password) {
        boolean result;
        String query = "SELECT * FROM userInfo WHERE " +
                "mailUser=\"" + email + "\" AND " +
                "userPassword=\"" + password + "\";";
        Map<Integer, List> rsltMap = QueriesRunner.QueryGetter(query);
        System.out.println(rsltMap);
        result = !rsltMap.isEmpty();
        if (result) {
            List rslt = rsltMap.get(0);
            User.email = (String) rslt.get(0);
            User.lastName = (String) rslt.get(1);
            User.firstName = (String) rslt.get(2);
            User.city = (String) rslt.get(4);
            User.userWallet = (float) rslt.get(5);
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
        return QueriesRunner.QuerySetter(query, true);
    }
    
    public static void getListOfCarDB() {
        String query = 
                "SELECT "
                + "ci.licensePlate, "
                + "ci.carBrand, "
                + "ci.carModel, "
                + "ci.carEnergy, "
                + "ci.carFiscalPower, "
                + "ci.intialSeatsNumber "
                + "FROM carOwnership co, carInfo ci WHERE "
                + "mailUser=\"" + User.email + "\" AND "
                + "ci.licensePlate=co.licensePlate;";
        Map<Integer, List> rslt = QueriesRunner.QueryGetter(query);
        for (int i = 0; i < rslt.size(); i++) {
            List rsltList = rslt.get(i);
            Car c = new Car( (String) rsltList.get(0), (String) rsltList.get(1),
                    (String) rsltList.get(2),  CarEnergy.valueOf((String) rsltList.get(3)),
                    (float) rsltList.get(4), (int) rsltList.get(5));
            carOwned.add(c);
        }

    }
    
    public static boolean addCarOwnership(String licensePlate) {
        String query = 
                "INSERT INTO carOwnership "
                + "(licensePlate, mailUser) "
                + "VALUES "
                + "(\"" + licensePlate + "\", \"" + User.email + "\");";
        return QueriesRunner.QuerySetter(query, true);
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

    public static float getUserWallet() {
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

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static List<Car> getCarOwned() {
        return carOwned;
    }
}
