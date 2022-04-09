package ensimag.voiture.model;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author almounah
 */
public class QueriesRunner {
    
    private static String url = "jdbc:mysql://sql11.freemysqlhosting.net";
    private static String port = "3306";
    private static String dbName = "sql11483800";
    private static String username = "sql11483800";
    private static String password = "LjgBLSmS6K";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";

    public QueriesRunner() {
    }
    
    
    public static Map<Integer, List> QueryGetter(String query) {
        System.out.println(query);
        Connection connection = null;
        ResultSet rslt;
        
        Map resultMap = new HashMap<Integer, List>();
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url + ":" + port + "/" + dbName, 
                                                     username, password);
            Statement statement = connection.createStatement();
            rslt = statement.executeQuery(query);
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
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueriesRunner.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return resultMap;
    }
    
    public static boolean QuerySetter(String query, boolean autocommit) {
        System.out.println(query);
        Connection connection = null;
        boolean rslt = false;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url + ":" + port + "/" + dbName, 
                                                     username, password);
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            rslt = true;
            if (autocommit)
                connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QueriesRunner.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                System.out.println(ex);
            }
        }
        return rslt;
    }
}