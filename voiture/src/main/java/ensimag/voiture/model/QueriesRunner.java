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
import oracle.jdbc.*;
import oracle.jdbc.dcn.*;

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
        OracleConnection connection = null;
        ResultSet rslt;
        
        Map resultMap = new HashMap<Integer, List>();
        try {
            System.out.println("Attempting connection");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("Driver Found");
            connection = (OracleConnection) DriverManager.getConnection(url, "almounah", "almounah");
            System.out.println("Connection Established");
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
            System.out.println(resultMap);
        } catch (SQLException e) {
            System.out.println(e);
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
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            connection = (OracleConnection) DriverManager.getConnection(url, "almounah", "almounah");
            connection.setAutoCommit(false);
            System.out.println("Connection Established");
            PreparedStatement pstatement = connection.prepareStatement(query);
            setParameters(pstatement, param, paramType);
            System.out.println(pstatement);
            pstatement.executeUpdate();
            rslt = true;
            if (autocommit)
                connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
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