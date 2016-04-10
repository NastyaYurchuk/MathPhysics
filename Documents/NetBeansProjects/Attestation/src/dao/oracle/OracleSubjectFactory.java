/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import dao.SubjectDao;
import dao.entities.Subject;
import dao.entities.User;
import static dao.oracle.OracleUserFactory.id;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author nastja
 */
public class OracleSubjectFactory implements SubjectDao{

    public OracleSubjectFactory() {
    }

    @Override
    public Subject find(String name) {
        Subject result = new Subject();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           String que = "SELECT * FROM SUBJECT where NAME_SUBJECT=?";
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(que);
           pst.setString(1,name);
            //System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            while( rs.next() ){
                System.out.println("succes");
              System.out.println(rs.getString(1));
                result = new Subject(  rs.getString(1));
            }
        }catch(Exception ex){
            
            throw new RuntimeException(ex);
        }
        return result;
    
    }

    @Override
    public boolean delete(Subject subject) {
         boolean delete = true;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           String que = "DELETE FROM SUBJECTS WHERE NAME_SUBJECT=?" ;
            //query.executeUpdate(que);
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(que);
           pst.setString(1,subject.getName());
           // System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
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
        JOptionPane.showMessageDialog(null, "Succes deleted subject ");
        return delete;
    }

    @Override
    public boolean update(Subject oldSubject, Subject newsubject) {
         boolean update = false;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){

           String que = "UPDATE SUBJECT SET NAME_SUBJECT='"+ newsubject.getName()+ 
                  "' WHERE NAME_SUBJECT= '" + oldSubject.getName() + "'";
           // String que = "UPDATE USERS SET LOGIN_USERS='user3', NAME_USERS='userhh', PASSWORD_USERS='hdhd6h' WHERE ID_USERS=2";
            //System.out.println(que);
            update = query.execute(que);
            
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
        JOptionPane.showMessageDialog(null, "Subject succes updated ");
        return update;
    }

    @Override
    public Subject create(Subject subject) {
        Subject result = new Subject();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           String que = "INSERT INTO SUBJECT(NAME_SUBJECT) VALUES('"
                    + subject.getName() + "')";
           
            query.executeUpdate(que);
            
         connection.commit();
         result = new Subject(subject.getName());
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Subject succes updated ");
        return result;
    }

    @Override
    public List<Subject> findAll() {
       
        ArrayList<Subject> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            ResultSet rs = query.executeQuery("SELECT * FROM SUBJECT");
            while( rs.next() ){
//                System.out.println(rs.getInt(1) +" "+rs.getString(2) + " "
//                                   +rs.getDouble(3) + " " +rs.getString(4));
                result.add( new Subject(rs.getString(1)));
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    
    
}
