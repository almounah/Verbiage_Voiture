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
            
            statement.executeUpdate("DROP TABLE carOwnership;");
            System.out.println("carOwnership table deleted");

            statement.executeUpdate("DROP TABLE tripPlan;");
            System.out.println("tripPlan table deleted");

            statement.executeUpdate("DROP TABLE trajectory;");
            System.out.println("trajectory table deleted");

            statement.executeUpdate("DROP TABLE sections;");
            System.out.println("sections table deleted");
            
            statement.executeUpdate("DROP TABLE carPool;");
            System.out.println("carPool table deleted");
            
            statement.executeUpdate("DROP TABLE userInfo;");
            System.out.println("userInfo table deleted");

            statement.executeUpdate("DROP TABLE carInfo;");
            System.out.println("carInfo table deleted");


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
