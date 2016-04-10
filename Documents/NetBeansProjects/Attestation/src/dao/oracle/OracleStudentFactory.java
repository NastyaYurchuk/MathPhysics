/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import dao.StudentDao;
import dao.entities.Group;
import dao.entities.Student;
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
public class OracleStudentFactory implements StudentDao{
    private  static int id;
    
    private static final String UPDATE_BY_GROUP_QUE = "UPDATE STUDENT SET GROUP_CODE=? WHERE GROUP_CODE=?";
    
    private static final String DELETE_BY_GROUP_QUE = "DELETE FROM STUDENT WHERE GROUP_CODE=?";
    
    private static final String DELETE = "DELETE FROM STUDENT WHERE ID_STUDENT=?";
    @Override
    public Student find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Student student) {
         boolean update = true;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){

           
           // String que = "UPDATE USERS SET LOGIN_USERS='user3', NAME_USERS='userhh', PASSWORD_USERS='hdhd6h' WHERE ID_USERS=2";
           OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(DELETE);
           pst.setString(1,Integer.toString(student.getId()));

            //System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
         //connection.commit();
        }catch(Exception ex){
            try {
              //  System.out.println("st error");
                connection.rollback();
                update = false;
               JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Student was deleted");
        return update;
    }

    @Override
    public List<Student> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> findGroupList(Group groupCode) {
         ArrayList<Student> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            //String que = "SELECT * FROM STUDENT JOIN ACADEMIC_GROUP" +
            //        "ON STUDENT.GROUP_CODE = ACADEMIC_GROUP.GROUP_CODE"
             //       + "WHERE STUDENT.GROUP_CODE=?";
            String que = "SELECT * FROM STUDENT JOIN ACADEMIC_GROUP ON "
                    + "STUDENT.GROUP_CODE = ACADEMIC_GROUP.GROUP_CODE "
                   + "WHERE STUDENT.GROUP_CODE = '"+groupCode.getGroupCode()+"' ORDER BY NAME_STUDENT";
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(que);
            System.out.println(groupCode);
          // pst.setString(1,groupCode);
            
         //  System.out.println(pst.toString());
           //OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            ResultSet rs = query.executeQuery(que);
            while( rs.next() ){
                System.out.println(rs.getInt(1) +" "+rs.getString(2) + " " +rs.getString(3));
                result.add( new Student(rs.getInt(1), rs.getString(2), rs.getString(3)));
                
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Student create(String name, String groupCode) {
        id = getLastId();
        System.out.println(id);
        Integer newId = (id + 1);
        Student result = new Student();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           String que = "INSERT INTO STUDENT(ID_STUDENT, NAME_STUDENT, GROUP_CODE)"
                   + " VALUES('" + newId.toString() + "', '" 
                   + name + "', '" + groupCode + "')";
            System.out.println(que);
            query.executeUpdate(que);
            
         connection.commit();
         result = new Student(newId, name, groupCode);
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Student was created");
        return result;
    }

    private int getLastId() {
         OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        int maxId = 0;
        try( Statement query = connection.createStatement(); ){
           String que = "SELECT MAX(ID_STUDENT)FROM STUDENT";
            ResultSet rs = query.executeQuery(que);
            
            if( rs.next() ){
                maxId = rs.getInt(1);
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return maxId;
    }

    @Override
    public boolean update(Student student) {
         boolean update = true;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){

           String que = "UPDATE STUDENT SET NAME_STUDENT='"+ student.getName() + "', GROUP_CODE='"
                 +student.getGroupCode() + 
                  "' WHERE ID_STUDENT=" + student.getId() ;
           // String que = "UPDATE USERS SET LOGIN_USERS='user3', NAME_USERS='userhh', PASSWORD_USERS='hdhd6h' WHERE ID_USERS=2";
            System.out.println(que);
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
        JOptionPane.showMessageDialog(null, "Student was updated");
        return update;
    }

    @Override
    public boolean updateByGroup(Group newGroup, Group oldGroup) {
        boolean update = true;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){

           
           // String que = "UPDATE USERS SET LOGIN_USERS='user3', NAME_USERS='userhh', PASSWORD_USERS='hdhd6h' WHERE ID_USERS=2";
           OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(UPDATE_BY_GROUP_QUE);
           pst.setString(1,newGroup.getGroupCode());
           pst.setString(2,oldGroup.getGroupCode());
            System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
         //connection.commit();
        }catch(Exception ex){
            try {
                System.out.println("st error");
                connection.rollback();
                update = false;
               JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Students was deleted");
        return update;
    }
}
    

