package ensimag.voiture.model.dataBase;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import ensimag.voiture.model.QueriesRunner;
import ensimag.voiture.model.CarEnergy;
import ensimag.voiture.model.dataBase.Car;
import ensimag.voiture.view.TrajectoryHomePage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import oracle.sql.TIMESTAMP;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    private static List<Trip> listTrip = new ArrayList<>();
    
    private static User userInstance = new User();


    private User() {
    }
    
    public static User getInstance() {
        return userInstance;
    }
    
    
    public static boolean login(String email, String password) {
        boolean result;
        String query = "SELECT * FROM userInfo WHERE " +
                "mailUser=? and userPassword =?";
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
            User.userWallet = ((BigDecimal) rslt.get(5)).floatValue();
        }
        return result;
    }
    
    public static boolean createAccount(String email, String password,
                              String city, String firstName, String lastName) {
        String query = "INSERT INTO userInfo " +
                       "(mailUser, userLastName, userFirstName, userCity, userPassword, userWallet) " +
                       "VALUES " +
                       "(?, ?, ?, ?, ?, 0)";
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
                + "ci.licensePlate=co.licensePlate";
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
                    ((BigDecimal) rsltList.get(4)).floatValue(), ((BigDecimal) rsltList.get(5)).intValue());
            carOwned.add(c);
        }

    }
    
    public static boolean addCarOwnership(String licensePlate) {
        String query = 
                "INSERT INTO carOwnership "
                + "(licensePlate, mailUser) "
                + "VALUES "
                + "(?, ?)";
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
                + "driverMail= ?";
        List param = new ArrayList<>();
        param.add(email);
        
        List<String> paramType = new ArrayList<>(
            List.of("String"));
        Map<Integer, List> trajList = QueriesRunner.QueryGetter(query, param, paramType);
        User.listProposedTraj = new ArrayList<>();
        for (Map.Entry<Integer, List> entry : trajList.entrySet()) {
            Integer trajectoryId = ((BigDecimal) entry.getValue().get(0)).intValue();
            String driverLicenseCar = (String) entry.getValue().get(1);
            Trajectory trajectory = new Trajectory(trajectoryId, User.email,
                                                   driverLicenseCar);
            List<TrajectoryChunck> listChunck = trajectory.getTrajectoryInforFromDB(trajectoryId);
            
            trajectory.setTrajectoryChunckList(listChunck);
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

    public static Float getUserWallet() {
        return userWallet;
    }

    public static void setFirstName(String firstName) {
        User.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        User.lastName = lastName;
    }

    public static void setWalletl(float Walletl) {
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
    
    public static void getUserListOfTripsDB() {
        listTrip = new ArrayList<>();
        String query = 
                "Select tp.tripId, tp.trajectId, max(tp.sectionId), min(tp.sectionId), t.drivenLicenseCar, u.USERFIRSTNAME\n" +
                "FROM\n" +
                "TRIPPLAN tp, carPool cp, Trajectory t, userInfo u\n" +
                "WHERE\n" +
                "tp.tripId = cp.tripId AND\n" +
                "cp.mailUser = ? AND\n" +
                "tp.trajectId = t.trajectId AND\n" +
                "u.mailUser = t.driverMail\n" +
                "GROUP BY tp.tripId, tp.trajectId, t.drivenLicenseCar, u.USERFIRSTNAME\n";
        List param = Arrays.asList(User.getEmail());
        List<String> paramType = Arrays.asList("String");

        List<Trip> lt = new ArrayList<>();
        Map<Integer, List> rsltMap = QueriesRunner.QueryGetter(query, param, paramType);
        for (Map.Entry<Integer, List> tripInfo : rsltMap.entrySet()) {
            Integer tripId = ((BigDecimal) tripInfo.getValue().get(0)).intValue();
            Integer trajectId = ((BigDecimal) tripInfo.getValue().get(1)).intValue();
            Integer maxChunck = ((BigDecimal) tripInfo.getValue().get(2)).intValue();
            Integer minChunck = ((BigDecimal) tripInfo.getValue().get(3)).intValue();
            String drivenCarLicense = (String) tripInfo.getValue().get(4);
            String userFirstName = (String) tripInfo.getValue().get(5);

            Trajectory traj1 = new Trajectory(trajectId, null, drivenCarLicense);
            List<TrajectoryChunck> chunckList = traj1.getTrajectoryInforFromDB(trajectId);

            Trip t = new Trip(tripId);
            for (Trip temp:listTrip) {
                if (temp.getTripId().equals(t.getTripId())) {
                    t = temp;
                    t.setCorrespondanceBool(true);
                    listTrip.remove(temp);
                    break;
                }
            }
            Integer addIndex = t.getListChuncks().size();
            if (!t.getListChuncks().isEmpty()) {
                String arrCity1 = t.getListChuncks().get(t.getListChuncks().size()-1).getCityArrival().getCityName();
                String depCity2 = chunckList.get(minChunck-1).getCityDeparture().getCityName();
                if (!arrCity1.equals(depCity2)) {
                    addIndex = 0;
                }
            }
            for (int i = maxChunck; i >= minChunck; i--) {
                t.addChunckToTrip(chunckList.get(i-1), addIndex);
            }
            User.listTrip.add(t);

        }
        System.out.println(User.listTrip);
    }

    public static List<Trip> getListTrip() {
        return listTrip;
    }
    
    public static void cancelTraj(int trajIndex) {
        listProposedTraj.get(trajIndex).cancel();
        listProposedTraj.remove(trajIndex);
    }
    
    public static void updateWallet(float newWallet) {
        String query = "Update userInfo set userWallet = ? where mailUser = ?";
        List param = new ArrayList<>();
        param.add(newWallet);
        param.add(email);
        
        List<String> paramType = new ArrayList<>(
            List.of("Float", "String"));
        
        QueriesRunner.QuerySetter(query, param, paramType, true);
        userWallet = newWallet;
    }
    
    public static void validateTrip(Integer tripIndex) {
        listTrip.get(tripIndex).validatePay();
        if (listTrip.get(tripIndex).getListChuncks().isEmpty())
            listTrip.remove(tripIndex);
    }
}
