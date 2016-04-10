/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import dao.UserDao;
import dao.entities.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.*;
/**
 *
 * @author nastja
 */
class OracleUserFactory implements UserDao {
    static int id;
    
     private static final String DELETE_WORKLOAD = "DELETE FROM LECTURER_WORKLOAD WHERE ID_USERS=?";
     
    public OracleUserFactory() {
    }


    @Override
    public User find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<User> findAll() {
        ArrayList<User> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            ResultSet rs = query.executeQuery("SELECT * FROM USERS WHERE IS_ADMIN = 'n'");
            while( rs.next() ){
//                System.out.println(rs.getInt(1) +" "+rs.getString(2) + " "
//                                   +rs.getDouble(3) + " " +rs.getString(4));
                result.add( new User( rs.getInt(1) , rs.getString(2) ,
                                   rs.getString(3) , rs.getString(4), rs.getString(5) ));
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public boolean update(User user) {
      boolean update = true;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           // System.out.println("id " + user.getID());
            //System.out.println("login " + user.getLogin() );
            //System.out.println("name " + user.getName());
            //System.out.println("pass " + user.getPassword());
           String que = "UPDATE USERS SET LOGIN_USERS='"+ user.getLogin() + "', NAME_USERS='"
                 +user.getName() + "', PASSWORD_USERS='"+ user.getPassword() +
                  "' WHERE ID_USERS=" + user.getID() ;
           // String que = "UPDATE USERS SET LOGIN_USERS='user3', NAME_USERS='userhh', PASSWORD_USERS='hdhd6h' WHERE ID_USERS=2";
            //System.out.println(que);
            query.execute(que);
            
         connection.commit();
        }catch(Exception ex){
            try {
                connection.rollback();
                update = false;
                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        return update;
    }

    @Override
    public boolean delete(User user) {
         boolean delete = true;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
            //System.out.println(user.getID());
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(DELETE_WORKLOAD);

           pst.setInt(1,user.getID());
           int rs = pst.executeUpdate();
           String que = "DELETE FROM USERS WHERE ID_USERS= " + user.getID() ;
            query.executeUpdate(que);
            
         connection.commit();
        }catch(Exception ex){
            try {
                connection.rollback();
                delete = false;
                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Succes delete user" + user.getLogin() );
        return delete;
    }

    @Override
    public User find(String password, String login) {
        
         User result = new User();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           String que = "SELECT * FROM USERS where LOGIN_USERS=? and"
                   + " PASSWORD_USERS=?";
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(que);
           pst.setString(1,login);
            pst.setString(2,password);
           // System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            while( rs.next() ){
                System.out.println("succes");
             // System.out.println(rs.getInt(1) +" "+rs.getString(2) + " "
              //                   +rs.getDouble(3) + " " +rs.getString(4));
              result = new User( rs.getInt(1) , rs.getString(2),
                                   rs.getString(3) , rs.getString(4), rs.getString(5));
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public User create(String login, String password, String name) {
        id = getLastId();
        Integer newId = (id + 1);
        User result = new User();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           String que = "INSERT INTO USERS(ID_USERS, LOGIN_USERS, NAME_USERS,"
                   + " PASSWORD_USERS, IS_ADMIN) VALUES('" + newId.toString() + "', '" 
                   + login + "', '" + name + "', '" + password + "', 'n')";
           
            query.executeUpdate(que);
            
         connection.commit();
         result = new User(newId, login, password, name, "n");
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "User is registrate");
        return result;
    }

    @Override
    public boolean findByLogin(String login) {
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           String que = "SELECT * FROM USERS where LOGIN_USERS=?";
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(que);
           pst.setString(1,login);
            //System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            if( rs.next() ){
                System.out.println("succes");
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return false;
    }

    @Override
    public boolean findByPassword(char[] password) {
          OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           String que = "SELECT * FROM USERS where PASSWORD_USERS=?";
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(que);
           pst.setString(1, new String(password));
            System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            if( rs.next() ){
                System.out.println("succes");
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return false;
    }

    private int getLastId() {
         OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        int maxId = 0;
        try( Statement query = connection.createStatement(); ){
           String que = "SELECT MAX(ID_USERS)FROM USERS";
            ResultSet rs = query.executeQuery(que);
            
            if( rs.next() ){
                maxId = rs.getInt(1);
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return maxId;
    }
    
    
}
