import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * dbCreator
 */
public class dbCreator {


    public static void main(String[] args) {
        String url = "jdbc:mysql://sql11.freemysqlhosting.net";
        String port = "3306";
        String dbName = "sql11482296";
        String username = "sql11482296";
        String password = "UehqPxJDnW";
        dbQueries queryGetter = new dbQueries();
        Connection connection = null;
        String driverName = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url + ":" + port + "/" + dbName, 
                                                     username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(queryGetter.userCreationQuery());
            System.out.println("userInfo table created");

            statement.executeUpdate(queryGetter.sectionsCreationQuery());
            System.out.println("section table created");

            statement.executeUpdate(queryGetter.carCreationQuery());
            System.out.println("car table created");
            
            statement.executeUpdate(queryGetter.carPoolCreationQuery());
            System.out.println("carpool table created");

            statement.executeUpdate(queryGetter.trajectoryCreationQuery());
            System.out.println("trajectory table created");

            statement.executeUpdate(queryGetter.tripPlanCreationQuery());
            System.out.println("tripPlan table created");

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
