/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import intefface.MainWindow;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nastja
 */
public class Jdbc {
  private static Connection connection;

    public Jdbc() {
    }
  
  public static Connection getInstance() throws SQLException{
      if (connection == null){

          try {
              Class.forName("oracle.jdbc.OracleDriver");
              connection = DriverManager.getConnection(
                      "jdbc:oracle:thin:@localhost:1521:orcl", "anastasiya",
                      "nastya");
          } catch (ClassNotFoundException ex) {
              Logger.getLogger(Jdbc.class.getName()).log(Level.SEVERE, null, ex);
          }

      }
      return connection;
  }

}