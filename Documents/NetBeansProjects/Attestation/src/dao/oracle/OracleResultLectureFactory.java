/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import dao.ResultLectureDao;
import dao.entities.LecturerWorkload;
import dao.entities.ResultLecture;
import dao.entities.Student;
import dao.entities.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;

/**
 *
 * @author nastja
 */
public class OracleResultLectureFactory implements ResultLectureDao{
    
    private static int id;
    
 private static final String SELECT_BY_LECT_WORKLOAD = "SELECT * FROM RESULT_LECTURE"
         + " WHERE ID_WORKLOAD=? and DATE_LECTURE > ?"; 
 
 private static final String SELECT_BY_LECT_WORKLOAD_DATE = "SELECT DISTINCT DATE_LECTURE"
         + " FROM RESULT_LECTURE WHERE ID_WORKLOAD=? and DATE_LECTURE > ?"
         + "ORDER BY DATE_LECTURE"; 
 
 private static final String SELECT_BY_LECT_WORKLOAD_AND_DATE = "SELECT * FROM "
         + "STUDENT s LEFT OUTER JOIN ( SELECT * FROM RESULT_LECTURE" 
         +" WHERE DATE_LECTURE =?) r ON s.ID_STUDENT = r.ID_STUDENT "
         + "WHERE  (r.ID_WORKLOAD =? OR r.ID_WORKLOAD IS NULL) AND s.GROUP_CODE=? "
         + "ORDER BY s.NAME_STUDENT"; 
 
  private static final String FIND_MAX = "SELECT SUM(MAX_RATE)FROM( "
          + "SELECT DISTINCT DATE_LECTURE, MAX_RATE FROM RESULT_LECTURE "
          + "WHERE ID_WORKLOAD=? and DATE_LECTURE > ? )";
  
  private static final String FIND_ALL_MAX = "SELECT DISTINCT DATE_LECTURE, MAX_RATE FROM RESULT_LECTURE "
          + "WHERE ID_WORKLOAD=? and DATE_LECTURE > ? ";
  
  private static final String FIND_ID = "SELECT MAX(ID_RESULT_LEC)FROM RESULT_LECTURE";
  
  private static final String INSERT = "INSERT INTO RESULT_LECTURE (STUDENT_RATE, "
        + "ID_STUDENT, ID_WORKLOAD, ID_RESULT_LEC, DATE_LECTURE, MAX_RATE ) "
          + "VALUES(?, ?, ?, ?, to_date(?, 'YYYY-MM-DD'), ?)";
  
  private static final String DELETE_BY_DATE = "DELETE FROM RESULT_LECTURE"
          + " WHERE DATE_LECTURE=to_date(?, 'YYYY-MM-DD') AND ID_WORKLOAD=?";
  
  private static final String UPDATE = "UPDATE RESULT_LECTURE SET DATE_LECTURE=to_date(?, 'YYYY-MM-DD'),"
          + "MAX_RATE =?  WHERE ID_WORKLOAD=? AND DATE_LECTURE=to_date(?, 'YYYY-MM-DD')";
  
  private static final String UPDATE_MARK = "UPDATE RESULT_LECTURE SET STUDENT_RATE =?"
          + "WHERE ID_WORKLOAD=? AND DATE_LECTURE=? AND ID_STUDENT = ?";
  
  private static final String UPDATE_MARK_TEST = "UPDATE RESULT_LECTURE SET STUDENT_RATE =10"
          + "WHERE ID_WORKLOAD=4 AND DATE_LECTURE=? AND ID_STUDENT = ?";
 
 
    @Override
    public List<ResultLecture> findByLecWork(LecturerWorkload lw, Date date) {
        ArrayList<ResultLecture> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_BY_LECT_WORKLOAD);
           pst.setInt(1,lw.getId());
           pst.setDate(2, date);
            System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            while( rs.next() ){
                result.add( new ResultLecture( rs.getInt(1) , rs.getInt(2) ,
                                   rs.getInt(3) , rs.getInt(4), rs.getDate(5),rs.getInt(6) ));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public ResultLecture create(ResultLecture rl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Date> findDateByAttest(LecturerWorkload lw, Date date) {
         ArrayList<Date> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_BY_LECT_WORKLOAD_DATE);
           pst.setInt(1,lw.getId());
           pst.setDate(2, date);
           // System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            while( rs.next() ){
                System.out.println(rs.getDate(1) );
                result.add( rs.getDate(1));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<Integer> findByDateLecWork(LecturerWorkload lw, Date date) {
        ArrayList<Integer> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_BY_LECT_WORKLOAD_AND_DATE);
           pst.setDate(1,date);
            pst.setString(2,Integer.toString(lw.getId()));          
           pst.setString(3, lw.getGroupCode());
          //  System.out.println("pst");
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            while( rs.next() ){
               //rs.getInt(4);
               //if(rs.wasNull()){
                 //  result.add(null);
               //}
                System.out.println(rs.getString(4));
                result.add(rs.getInt(4));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public int findSumMaxMark(LecturerWorkload lw, Date date) {
            int result = 0;
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(FIND_MAX);

            pst.setInt(1,lw.getId());
            pst.setDate(2, date);
           // System.out.println("pst");
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            if( rs.next() ){

                result = rs.getInt(1);
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Map<Date, Integer> findAllMaxMark(LecturerWorkload lw, Date date) {
        Map<Date, Integer> result = new TreeMap<Date, Integer>();

       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(FIND_ALL_MAX);

            pst.setInt(1,lw.getId());
            pst.setDate(2, date);
          //  System.out.println("pst");
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            while( rs.next() ){

                result.put(rs.getDate(1), rs.getInt(2));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void createEmpty(ResultLecture rl, List<Student> students) {
       id = getLastId();
        Integer newId = (id + 1);
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           for(Student s : students){
               System.out.println("new s");
           OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(INSERT);
           pst.setString(1, Integer.toString(rl.getStudentRate()));
               System.out.println(s.getName());
           pst.setString(2, Integer.toString(s.getId()));
           pst.setString(3, Integer.toString(rl.getIdWorkload()));
               //System.out.println("id" + newId);
           pst.setString(4,Integer.toString(newId++));
           pst.setString(5, rl.getDateLecture().toString());          
           pst.setString(6, Integer.toString(rl.getMaxRate()));

            System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
               System.out.println("execute");
           }
         connection.commit();
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Sorry, " + rl.toString() + " doesn't saved./n" + ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Result lecture was created");
        
    }

    @Override
    public void deleteByDate(Date date, LecturerWorkload l) {
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
 
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(DELETE_BY_DATE);
            pst.setString(1,date.toString());
            pst.setString(2,Integer.toString(l.getId()));
            System.out.println(pst.toString());
            OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            System.out.println("delete new");
            connection.commit();
        }catch(Exception ex){
            try {
                
                connection.rollback();
                
                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Result lecture was deleted");
      
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
    public void update(Date oldDate, LecturerWorkload lecturerWorkload, int newMark, Date newDate) {
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){


        OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                    prepareStatement(UPDATE);
            pst.setString(1, newDate.toString());
            pst.setString(2, Integer.toString(newMark));
            pst.setString(3, Integer.toString(lecturerWorkload.getId()));
            pst.setString(4, oldDate.toString());
            System.out.println(pst.toString());
            ResultSet rs = (OracleResultSet) pst.executeQuery();
            System.out.println("update student new");

            connection.commit();
        }catch(Exception ex){
            try {
                
                connection.rollback();

                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Result lecture was updated");
        
    }

    @Override
    public void updateMark(List<ResultLecture> changeList) {
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        for( ResultLecture rl : changeList){
        try( Statement query = connection.createStatement(); ){


        OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                    prepareStatement(UPDATE_MARK);
            System.out.println(rl.getStudentRate());
            pst.setString(1, Integer.toString(rl.getStudentRate()));
            pst.setString(2, Integer.toString(rl.getIdWorkload()));
            pst.setDate(3, rl.getDateLecture());
            pst.setInt(4, rl.getIdStudent());
            ResultSet rs = (OracleResultSet) pst.executeQuery();

            connection.commit();
        }catch(Exception ex){
            try {
                
                connection.rollback();

                JOptionPane.showMessageDialog(null, ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        }
        JOptionPane.showMessageDialog(null, "Result lecture was updated");
    }
    
    
    
    
}
