package ensimag.voiture.model;

import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
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
    
    
    public static Map<Integer, List> QueryGetter(String query, List param, 
                                                 List<String> paramType) {
        Connection connection = null;
        ResultSet rslt;
        
        Map resultMap = new HashMap<Integer, List>();
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url + ":" + port + "/" + dbName, 
                                                     username, password);
            PreparedStatement pstatement = connection.prepareStatement(query);
            setParameters(pstatement, param, paramType);
            System.out.println(pstatement);
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
    
    public static boolean QuerySetter(String query,  List param, 
                                      List<String> paramType, boolean autocommit) {
        Connection connection = null;
        boolean rslt = false;
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url + ":" + port + "/" + dbName, 
                                                     username, password);
            connection.setAutoCommit(false);
            PreparedStatement pstatement = connection.prepareStatement(query);
            setParameters(pstatement, param, paramType);
            System.out.println(pstatement);
            pstatement.executeUpdate();
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