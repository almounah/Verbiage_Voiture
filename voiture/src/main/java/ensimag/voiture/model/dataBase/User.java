package ensimag.voiture.model.dataBase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import ensimag.voiture.model.QueriesRunner;
import ensimag.voiture.model.CarEnergy;
import ensimag.voiture.model.dataBase.Car;
import ensimag.voiture.view.TrajectoryHomePage;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


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
    private static List<Trajectory> listProposedTraj = new ArrayList<Trajectory>();



    public User() {
    }
    
    
    public static boolean login(String email, String password) {
        boolean result;
        String query = "SELECT * FROM userInfo WHERE " +
                "mailUser=? and userPassword =?;";
        List param = new ArrayList<>();
        param.add(email);
        param.add(password);
        
        List<String> paramType = new ArrayList<>(
            List.of("String",
                    "String"));
        Map<Integer, List> rsltMap = QueriesRunner.QueryGetter(query, param, paramType);
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
                       "(?, ?, ?, ?, ?, 0);";
        List param = Arrays.asList(email, lastName, firstName, city, password);
        List<String> paramType = Arrays.asList("String", "String", "String", "String", "String");
        return QueriesRunner.QuerySetter(query, param, paramType, true);
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
                + "mailUser=? AND "
                + "ci.licensePlate=co.licensePlate;";
        List param = new ArrayList<>();
        param.add(email);
        
        List<String> paramType = new ArrayList<>(
            List.of("String"));
        Map<Integer, List> rslt = QueriesRunner.QueryGetter(query, param, paramType);
        carOwned = new ArrayList<>();
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
                + "(?, ?);";
        List param = Arrays.asList(licensePlate, User.email);
        List<String> paramType = Arrays.asList("String", "String");
        return QueriesRunner.QuerySetter(query, param, paramType, true);
    }
    
    public static void getListOfTrajectoryDB() {
        String query = 
                "SELECT "
                + "trajectId, "
                + "drivenLicenseCar "
                + "FROM trajectory WHERE "
                + "driverMail= ? ;";
        List param = new ArrayList<>();
        param.add(email);
        
        List<String> paramType = new ArrayList<>(
            List.of("String"));
        Map<Integer, List> trajList = QueriesRunner.QueryGetter(query, param, paramType);
        User.listProposedTraj = new ArrayList<>();
        for (Map.Entry<Integer, List> entry : trajList.entrySet()) {
            Integer trajectoryId = (Integer) entry.getValue().get(0);
            String driverLicenseCar = (String) entry.getValue().get(1);
            
            List<TrajectoryChunck> listChunck = new ArrayList<>();
            query = 
                "SELECT "
                + "sectionId, "
                + "sectionWaitingDelay, "
                + "availableSeats, "
                + "travelDistance, "
                + "travelDuration, "
                + "cityArrival, "
                + "cityDeparture, "
                + "latArrival, " 
                + "longArrival, "
                + "latDeparture, " 
                + "longDeparture, "
                + "sectionStartDate "
                + "FROM sections WHERE "
                + "trajectId=?;";
            param = new ArrayList<>();
            param.add(trajectoryId);
            paramType = new ArrayList<>();
            paramType.add("Integer");
            
            Map<Integer, List> chunckList = QueriesRunner.QueryGetter(query, param, paramType);
            for (Map.Entry<Integer, List> chunckInfo : chunckList.entrySet()) {
                Integer sectionId = (Integer) chunckInfo.getValue().get(0);
                Integer sectionWaitingDelay = (Integer) chunckInfo.getValue().get(1);
                Integer availableSeats = (Integer) chunckInfo.getValue().get(2);
                float travelDistance = (float) chunckInfo.getValue().get(3);
                float travelDuration = (float) chunckInfo.getValue().get(4);
                City cityArrival = new City((String) chunckInfo.getValue().get(5),
                                            (float) chunckInfo.getValue().get(7),
                                            (float) chunckInfo.getValue().get(8));
                City cityDeparture = new City((String) chunckInfo.getValue().get(6),
                                            (float) chunckInfo.getValue().get(9),
                                            (float) chunckInfo.getValue().get(10));
                LocalDateTime sectionStartDate = (LocalDateTime) chunckInfo.getValue().get(11);
                
                TrajectoryChunck trajectoryChunck = new TrajectoryChunck(trajectoryId,
                        sectionId, sectionWaitingDelay, travelDistance,
                        travelDuration, availableSeats, cityArrival, cityDeparture, sectionStartDate);
                listChunck.add(trajectoryChunck);
            }
            
            Trajectory trajectory = new Trajectory(trajectoryId, User.email,
                    driverLicenseCar, listChunck);
            User.listProposedTraj.add(trajectory);
        }
        
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

    public static List<Trajectory> getListProposedTraj() {
        return listProposedTraj;
    }
}
