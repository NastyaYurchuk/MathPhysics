/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nastja
 */
public class OracleConnection {
    
  private static OracleConnection instance;
  
    public static OracleConnection getInstance(){
        if( instance == null ){
            instance = new OracleConnection();
        }
        return instance;
    }
    
    private Connection connection;

    private OracleConnection() {
        Properties dbProp = new Properties();
        try {
           
            dbProp.load(new FileInputStream(".\\src\\jdbc\\db.properties"));
             Class.forName(dbProp.getProperty("db.driver"));
            connection = DriverManager.getConnection(dbProp.getProperty("db.url"),
                                                 dbProp.getProperty("db.user"),
                                                 dbProp.getProperty("db.pass"));         
        } catch (Exception ex) {
            Logger.getLogger(OracleConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }  
}
