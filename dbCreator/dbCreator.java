import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;

/**
 * dbCreator
 */
public class dbCreator {


    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
        dbQueries queryGetter = new dbQueries();
        OracleConnection connection = null;

        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = (OracleConnection) DriverManager.getConnection(url, "almounah", "almounah");
            System.out.println("Connection Established");
            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            statement.executeUpdate(queryGetter.carCreationQuery());
            System.out.println("carInfo table created");
            
            statement.executeUpdate(queryGetter.userCreationQuery());
            System.out.println("userInfo table created");


            statement.executeUpdate(queryGetter.trajectoryCreationQuery());
            System.out.println("trajectory table created");

            statement.executeUpdate(queryGetter.sectionsCreationQuery());
            System.out.println("section table created");
            
            statement.executeUpdate(queryGetter.carPoolCreationQuery());
            System.out.println("carPool table created");

            statement.executeUpdate(queryGetter.carOwnerSHipQuery());
            System.out.println("carOwnership table created");

            statement.executeUpdate(queryGetter.tripPlanCreationQuery());
            System.out.println("tripPlan table created");

            System.out.println("------Everything works--------");
            System.out.println("------Now Commiting--------");
            connection.commit();

        } catch (Exception e) {
            System.out.println("------Error--------");
            System.out.println("------Now Rolling Back--------");
            try {
                connection.rollback();
                System.out.println(e);
            } catch(SQLException esq) {
                System.out.println(esq);
            }
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
