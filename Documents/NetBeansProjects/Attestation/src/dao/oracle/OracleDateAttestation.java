/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import dao.DateAttestationDao;
import dao.entities.DateAttestation;
import dao.entities.Group;
import dao.entities.User;
import java.sql.Connection;
import java.sql.Date;
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
public class OracleDateAttestation implements DateAttestationDao {
    
    private static int id;
    
    private static final String SELECT_CURRENT = "SELECT * FROM DATE_ATTESTATION "
            + "WHERE BEGIN_DATE <= ? AND END_DATE >= ?";
    
    private static final String SELECT_PREVIOUS = "SELECT MAX(END_DATE) " +
                "FROM (SELECT * " +
            "FROM DATE_ATTESTATION " +
            "WHERE BEGIN_DATE < ? )";
    
    private static final String SELECT_ALL = "SELECT * FROM DATE_ATTESTATION "
            + "ORDER BY BEGIN_DATE DESC";
    
    private static final String FIND_ID = "SELECT MAX(ID_ATTEST)FROM DATE_ATTESTATION";
    
    private static final String INSERT = "INSERT INTO DATE_ATTESTATION (ID_ATTEST, "
        + "BEGIN_DATE, END_DATE ) "
          + "VALUES(?, ?, ?)";
    
    private static final String UPDATE = "UPDATE DATE_ATTESTATION SET "
            + "BEGIN_DATE = ?, END_DATE = ? WHERE ID_ATTEST=? ";
    
    private static final String DELETE = "DELETE FROM DATE_ATTESTATION " +
"WHERE ID_ATTEST=?";
    
    @Override
    public DateAttestation findCurrent(Date date) {
       DateAttestation result = new DateAttestation();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_CURRENT);
            System.out.println(date);
           pst.setDate(1, date);
           pst.setDate(2, date);
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            if( rs.next() ){
             // System.out.println(rs.getString(1));
                result = new DateAttestation(  rs.getInt(1), rs.getDate(2), rs.getDate(3));
            }
        }catch(Exception ex){
            
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public Date lastAttestation(DateAttestation da) {
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        Date result = da.getBeginDate();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
             
           OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_PREVIOUS);
           pst.setDate(1, da.getBeginDate());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            if( rs.next() ){
                result = rs.getDate(1);
            }
        }catch(Exception ex){
            
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public List<DateAttestation> findAll() {
             List<DateAttestation> result = new ArrayList<DateAttestation>();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
             
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_ALL);

           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
            while( rs.next() ){
                result.add(new DateAttestation(  rs.getInt(1), rs.getDate(2), rs.getDate(3)));
            }
        }catch(Exception ex){
            
            throw new RuntimeException(ex);
        }
        return result;
    }

    @Override
    public void create(DateAttestation dateAttestation) {
         id = getLastId();
        Integer newId = (id + 1);
        User result = new User();
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){

           OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(INSERT);
           pst.setInt(1, newId);
           pst.setDate(2, dateAttestation.getBeginDate());
           pst.setDate(3, dateAttestation.getEndDate());

           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            
         connection.commit();
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Sorry, " + dateAttestation.toString() + " doesn't save./n" + ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Date Attestation was create");
    }
    
    
     private int getLastId() {
         OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
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
    public void update(DateAttestation oldDate, DateAttestation newDate) {
                 boolean update = true;
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){

            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(UPDATE);    
           pst.setDate(1, newDate.getBeginDate());
           pst.setDate(2, newDate.getEndDate());
           pst.setInt(3, oldDate.getId());
           int rs = pst.executeUpdate();
            
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
        JOptionPane.showMessageDialog(null, "Succes update");
     
    }

    @Override
    public void delete(DateAttestation da) {
     
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){

            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(DELETE);
           pst.setInt(1, da.getId());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
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
        JOptionPane.showMessageDialog(null, "Date Attestation was delete");
    }
    
    
    
    
}
