import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * dbCreator
 */
public class dbDestroy {


    public static void main(String[] args) {
        String url = "jdbc:mysql://sql11.freemysqlhosting.net";
        String port = "3306";
        String dbName = "sql11482296";
        String username = "sql11482296";
        String password = "UehqPxJDnW";
        Connection connection = null;
        String driverName = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url + ":" + port + "/" + dbName, 
                                                     username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE UserInfo;");
            System.out.println("UserInfo table deleted");

            statement.executeUpdate("DROP TABLE Sections;");
            System.out.println("Sections table deleted");

            statement.executeUpdate("DROP TABLE Car;");
            System.out.println("Car table deleted");
            
            statement.executeUpdate("DROP TABLE CarPool;");
            System.out.println("CarPool table deleted");

            statement.executeUpdate("DROP TABLE Trajectory;");
            System.out.println("Trajectory table deleted");

            statement.executeUpdate("DROP TABLE tripPlan;");
            System.out.println("tripPlan table deleted");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try{
                connection.close(); // throws exception here
            } catch(SQLException e) {
                System.out.println(e);
            }
        }
    }
}
