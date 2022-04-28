package ensimag.voiture.model;

import java.util.List;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author almounah
 */
public class QueriesRunner {
    
    private static String url = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
    private static String username = "almounah";
    private static String password = "almounah";
    private static OracleConnection connection;

    public QueriesRunner() {
    }
    
    
    private static void setParameters(PreparedStatement p, List param, List<String> paramType) {
        try {
            for (int i = 0; i < param.size(); i++) {
                switch (paramType.get(i)) {
                    case "String":
                        p.setString(i+1, (String) param.get(i));
                        break;
                    case "Float":
                        p.setFloat(i+1, (float) param.get(i));
                        break;
                    case "Date":
                        p.setTimestamp(i+1, (Timestamp) param.get(i));
                        break;
                    default:
                        p.setInt(i+1, (Integer) param.get(i));
                        break;
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(QueriesRunner.class.getName()).log(Level.SEVERE, null, e);
        }
    }
   
    
    public static void getConnection() {
        try {
            System.out.println("Attempting connection ....");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Driver Found");
            connection = (OracleConnection) DriverManager.getConnection(url, "almounah", "almounah");
            System.out.println("Connection Established");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public static Map<Integer, List> QueryGetter(String query, List param, 
                                                 List<String> paramType) {
        ResultSet rslt;
        
        Map resultMap = new HashMap<Integer, List>();
        try {
            if (connection==null || connection.isClosed()) {
                getConnection();
            }
            PreparedStatement pstatement = connection.prepareStatement(query);
            setParameters(pstatement, param, paramType);
            rslt = pstatement.executeQuery();
            int columnCount = rslt.getMetaData().getColumnCount();
            
            int rowIndex = 0;
            while (rslt.next()) {
                int columnNumber = 1;
                List rowContent = new ArrayList<>();
                while (columnNumber <= columnCount)
                    rowContent.add(rslt.getObject(columnNumber++));
                resultMap.put(rowIndex, rowContent);
                rowIndex++;
            }
            System.out.println(resultMap);
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                connection.close();
                System.out.println("Connection Closed");
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return resultMap;
    }
    
    public static boolean QuerySetter(String query,  List param, 
                                      List<String> paramType, boolean close) {
        boolean rslt = false;
        try {
            if (connection==null || connection.isClosed()) {
                getConnection();
            }
            PreparedStatement pstatement = connection.prepareStatement(query);
            setParameters(pstatement, param, paramType);
            System.out.println(pstatement.toString());
            pstatement.executeUpdate();
            rslt = true;
        } catch (SQLException e) {
            rollback();
            System.out.println(e);
        } finally {
            try {
                if (close && !connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection Closed");
                }
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return rslt;
    }
    
    public static void commit() {
        try {
            if (!connection.isClosed()) {
                connection.commit();
            }

        } catch (SQLException ex) {
            Logger.getLogger(QueriesRunner.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection Closed");
                }
            } catch (SQLException ex) {
                Logger.getLogger(QueriesRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    
    
    public static void rollback() {
        try {
            System.out.println("Rolling Back");
            if (!connection.isClosed()) {
                connection.rollback();
            }
        } catch (SQLException ex) {
            Logger.getLogger(QueriesRunner.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                    System.out.println("Connection Closed");
                }
            } catch (SQLException ex) {
                Logger.getLogger(QueriesRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}