/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import dao.LecturerWorkloadDao;
import dao.entities.Group;
import dao.entities.LecturerWorkload;
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
public class OracleLectWorkloadFactory implements LecturerWorkloadDao{
    
    private static int id;

    private static final String SELECT_FREE_GROUP = "SELECT ACADEMIC_GROUP.GROUP_CODE FROM ACADEMIC_GROUP "
            + "LEFT OUTER JOIN LECTURER_WORKLOAD ON "
            + "LECTURER_WORKLOAD.GROUP_CODE = ACADEMIC_GROUP.GROUP_CODE "
            + "WHERE NAME_SUBJECT != ? OR NAME_SUBJECT IS NULL";

    private static final String FIND_ID = "SELECT MAX(ID_WORKLOAD)FROM LECTURER_WORKLOAD";

    private static final String INSERT = "INSERT INTO LECTURER_WORKLOAD(ID_WORKLOAD, "
            + "NAME_SUBJECT, GROUP_CODE, ID_USERS) VALUES(?, ?, ?, ?)";

    private static final String SELECT_ALL_SUBJECT = "SELECT DISTINCT NAME_SUBJECT FROM LECTURER_WORKLOAD where ID_USERS=?";

    private static final String SELECT_ALL_GROUP = "SELECT DISTINCT GROUP_CODE FROM LECTURER_WORKLOAD where ID_USERS=? AND "
            + "NAME_SUBJECT = ?";

    private static final String SELECT_WORKLOAD = "SELECT * FROM LECTURER_WORKLOAD where ID_USERS=?";

    private static final String SELECT_SUBJECT_GROUP = "SELECT DISTINCT name_subject "
            + "from LECTURER_WORKLOAD "
            + "WHERE GROUP_CODE = ?";
    
    @Override
    public LecturerWorkload find(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(LecturerWorkload lecturerWorkload) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(LecturerWorkload lecturerWorkload) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LecturerWorkload create(int id, String name, String groupCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LecturerWorkload> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<LecturerWorkload> findGroupList(String name, String groupCode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Group> findFreeGroup(Subject subject) {
       ArrayList<Group> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                          prepareStatement(SELECT_FREE_GROUP);
           pst.setString(1,subject.getName());

           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            while( rs.next() ){

                result.add( new Group(rs.getString(1)));
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void create(LecturerWorkload l) {
       id = getLastId();
        Integer newId = (id + 1);
        User result = new User();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){

           OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(INSERT);
           pst.setString(1,Integer.toString(newId));
           pst.setString(2, l.getNameSubject());
           pst.setString(3, l.getGroupCode());
           pst.setString(4, Integer.toString(l.getIdUser()));

           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
         connection.commit();
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Sorry, " + l.toString() + " doesn't save./n" + ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Workload was created");
        
    }
    
    private int getLastId() {
         OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        int maxId = 0;
        try( Statement query = connection.createStatement(); ){
           
            ResultSet rs = query.executeQuery(FIND_ID);
            
            if( rs.next() ){
                maxId = rs.getInt(1);
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return maxId;
    }

    @Override
    public List<Subject> findSubjList(User user) {
        List<Subject> result = new ArrayList<>();
         OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_ALL_SUBJECT);
           pst.setString(1, Integer.toString(user.getID()));
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            while ( rs.next() ){
                result.add(new Subject(rs.getString(1)));
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<Group> findGroupList(User user, Subject subject) {
        List<Group> result = new ArrayList<>();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_ALL_GROUP);
           pst.setString(1, Integer.toString(user.getID()));
           pst.setString(2, subject.getName());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            while( rs.next() ){
                result.add(new Group(rs.getString(1)));
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<LecturerWorkload> findWorkload(User  user) {
        List<LecturerWorkload> result = new ArrayList<>();
         OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_WORKLOAD);
           pst.setString(1, Integer.toString(user.getID()));
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            while( rs.next() ){
                result.add(new LecturerWorkload(rs.getInt(1), rs.getString(2),
                                rs.getString(3), rs.getInt(4)));
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<Subject> findSubjectsOfGroup(Group group) {
        List<Subject> result = new ArrayList<>();
         OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_SUBJECT_GROUP);
           pst.setString(1, group.getGroupCode());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            while( rs.next() ){
                result.add(new Subject(rs.getString(1)));
            }
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }
        return result;
    }
    
    
    
}
    

