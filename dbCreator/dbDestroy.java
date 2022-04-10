import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;

/**
 * dbCreator
 */
public class dbDestroy {


    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
        OracleConnection connection = null;

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = (OracleConnection) DriverManager.getConnection(url, "almounah", "almounah");
            System.out.println("Connection Established");
            Statement statement = connection.createStatement();
            connection.setAutoCommit(false);

            statement.executeUpdate("DROP TABLE carOwnership");
            System.out.println("carOwnership table deleted");

            statement.executeUpdate("DROP TABLE tripPlan");
            System.out.println("tripPlan table deleted");

            statement.executeUpdate("DROP TABLE sections");
            System.out.println("sections table deleted");
          
            statement.executeUpdate("DROP TABLE trajectory");
            System.out.println("trajectory table deleted");

          
            statement.executeUpdate("DROP TABLE carPool");
            System.out.println("carPool table deleted");
            
            statement.executeUpdate("DROP TABLE userInfo");
            System.out.println("userInfo table deleted");

            statement.executeUpdate("DROP TABLE carInfo");
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
