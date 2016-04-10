/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import dao.DaoFactory;
import dao.GroupDao;
import dao.SubjectDao;
import dao.entities.Group;
import dao.entities.Subject;
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
public class OracleGroupFactory implements GroupDao {
    DaoFactory daoFactory = DaoFactory.getInstance(DaoFactory.DaoType.ORACLE);
    
   private static final String  INSERT_QUE = "INSERT INTO ACADEMIC_GROUP(GROUP_CODE) VALUES(?)" ;
  
   private static final String SELECT_ALL_QUE = "SELECT * FROM ACADEMIC_GROUP";
   
   private static final String SELECT_ONE = "SELECT * FROM ACADEMIC_GROUP WHERE GROUP_CODE=?";
   
   private static final String  DELETE_QUE = "DELETE FROM ACADEMIC_GROUP WHERE GROUP_CODE = ?" ;
   
   private static final String UPDATE_BY_GROUP_QUE = "UPDATE STUDENT SET GROUP_CODE=? WHERE GROUP_CODE=?";
   
   private static final String DELETE_BY_GROUP_QUE = "DELETE FROM STUDENT WHERE GROUP_CODE=?";
   
    public OracleGroupFactory() {
    }

    @Override
    public Group find(Group groupCode) {
         Group result = new Group();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_ONE);
           pst.setString(1,groupCode.getGroupCode());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            while( rs.next() ){
              System.out.println(rs.getString(1));
                result = new Group(  rs.getString(1));
            }
        }catch(Exception ex){
            
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public boolean delete(Group group) {
       boolean delete = false;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
 
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(DELETE_BY_GROUP_QUE);
            pst.setString(1,group.getGroupCode());
            System.out.println(pst.toString());
            OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            pst = (OraclePreparedStatement) connection.
                                           prepareStatement(DELETE_QUE);
            pst.setString(1,group.getGroupCode());
            rs = (OracleResultSet) pst.executeQuery();
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
        JOptionPane.showMessageDialog(null, "Group was deleted");
        return delete;
    }

    @Override
    public boolean update(Group oldGroup, Group newGroup) {
        boolean update = false;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
 
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(INSERT_QUE);
            pst.setString(1,newGroup.getGroupCode());
            OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
             pst = (OraclePreparedStatement) connection.
                                           prepareStatement(UPDATE_BY_GROUP_QUE);
           pst.setString(1,newGroup.getGroupCode());
           pst.setString(2,oldGroup.getGroupCode());
            System.out.println(pst.toString());
            rs = (OracleResultSet) pst.executeQuery();
            
            pst = (OraclePreparedStatement) connection.
                                           prepareStatement(DELETE_QUE);
            pst.setString(1,oldGroup.getGroupCode());
            rs = (OracleResultSet) pst.executeQuery();
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
        JOptionPane.showMessageDialog(null, "Group was updated");
        return update;
    }

    @Override
    public Group create(Group group) {
        Group result = new Group();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
          
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(INSERT_QUE);
           pst.setString(1,group.getGroupCode());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
           // query.executeUpdate(que);
            
         connection.commit();
         result = new Group(group.getGroupCode());
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Group was created");
        return result;
    }

    @Override
    public List<Group> findAll() {
        ArrayList<Group> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            
            ResultSet rs = query.executeQuery(SELECT_ALL_QUE);
            while( rs.next() ){           
                result.add( new Group(rs.getString(1)));
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    }

    


