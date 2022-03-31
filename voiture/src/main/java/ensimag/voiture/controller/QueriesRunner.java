package ensimag.voiture.controller;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    
    private static final String url = "jdbc:mysql://sql11.freemysqlhosting.net";
    private static final String port = "3306";
    private static final String dbName = "sql11482296";
    private static final String username = "sql11482296";
    private static final String password = "UehqPxJDnW";
    private static final String driverName = "com.mysql.cj.jdbc.Driver";

    public QueriesRunner() {
    }
    
    
    public static List QueryGetter(String query) {
        System.out.println(query);
        Connection connection = null;
        ResultSet rslt;
        
        List resultList = new ArrayList<>();
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url + ":" + port + "/" + dbName, 
                                                     username, password);
            Statement statement = connection.createStatement();
            rslt = statement.executeQuery(query);
            int columnCount = rslt.getMetaData().getColumnCount();
            while (rslt.next()) {
                int columnNumber = 1;
                while (columnNumber <= columnCount)
                    resultList.add(rslt.getObject(columnNumber++));
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
        return resultList;
    }
    
    public static boolean QuerySetter(String query) {
        System.out.println(query);
        Connection connection = null;
        boolean rslt = false;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url + ":" + port + "/" + dbName, 
                                                     username, password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            rslt = true;
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