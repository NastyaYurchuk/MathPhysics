/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import dao.StudentAttestationDao;
import dao.entities.DateAttestation;
import dao.entities.Group;
import dao.entities.LecturerWorkload;
import dao.entities.StudentAttestation;
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
class OracleStudentAttestationFactory implements StudentAttestationDao {

    /*private static final String SELECT_BY_GROUP = "SELECT  "
     + "s.ID_ATTEST, s.ID_STUDENT,s.NAME_SUBJECT, s.ATTESTATION " +
     "FROM (SELECT s.ID_ATTEST, s.ID_STUDENT,s.NAME_SUBJECT, s.ATTESTATION " +
     "FROM STUDENT_ATTESTATION s JOIN LECTURER_WORKLOAD l " +
     "ON  s.NAME_SUBJECT = l.NAME_SUBJECT" +
     "WHERE l.NAME_SUBJECT = ? and l.GROUP_CODE = ? " +
     "and s.ID_ATTEST= ? ) s " +
     "JOIN STUDENT  ON s.ID_STUDENT = STUDENT.ID_STUDENT " +
     "ORDER BY STUDENT.NAME_STUDENT";*/
    private static final String SELECT_BY_GROUP = "SELECT  s.ID_ATTEST, s.ID_STUDENT,s.NAME_SUBJECT, s.ATTESTATION "
            + "FROM (SELECT s.ID_ATTEST, s.ID_STUDENT,s.NAME_SUBJECT, s.ATTESTATION "
            + "              FROM STUDENT_ATTESTATION s JOIN LECTURER_WORKLOAD l "
            + "            ON  s.NAME_SUBJECT = l.NAME_SUBJECT"
            + "            WHERE l.NAME_SUBJECT = ? and l.GROUP_CODE = ?"
            + "            and s.ID_ATTEST= ? ) s "
            + "JOIN STUDENT  ON s.ID_STUDENT = STUDENT.ID_STUDENT "
            + "ORDER BY STUDENT.NAME_STUDENT";

    private static final String INSERT = "INSERT INTO STUDENT_ATTESTATION ( "
            + "ID_STUDENT, ID_ATTEST, NAME_SUBJECT, ATTESTATION ) "
            + "VALUES(?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE STUDENT_ATTESTATION s "
            + "SET s.ATTESTATION = ? "
            + "WHERE s.ID_ATTEST = ? and s.ID_STUDENT = ? and s.NAME_SUBJECT = ?";

    @Override
    public List<StudentAttestation> findByGroup(LecturerWorkload lw, DateAttestation da) {
        ArrayList<StudentAttestation> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_BY_GROUP);
           pst.setString(1,lw.getNameSubject());
           pst.setString(2, lw.getGroupCode());
           pst.setInt(3,da.getId());
           
            System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            while( rs.next() ){
                result.add( new StudentAttestation( rs.getInt(1) , rs.getInt(2) ,
                                   rs.getString(3) , rs.getString(4)));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
        
        return result;
    }

    @Override
    public void setListAttestation(List<StudentAttestation> attestations) {

        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           for(StudentAttestation sa : attestations){
               System.out.println("new s");
           OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(INSERT);
           pst.setInt(1, sa.getIdStud());
           
           pst.setInt(2, sa.getIdAttest());
           pst.setString(3, sa.getNameSubject());
               //System.out.println("id" + newId);
           pst.setString(4,sa.getAttestation());

            System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
               System.out.println("execute");
           }
         connection.commit();
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Sorry, " + " doesn't saved./n" + ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Students attestation was created");
        
    }

    @Override
    public void updateAttestation(List<StudentAttestation> attestations) {
       
        OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){
           for(StudentAttestation sa : attestations){
               System.out.println("sa" + sa.getIdStud());
           OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(UPDATE);
           pst.setString(1, sa.getAttestation());
           pst.setInt(2, sa.getIdAttest());
           pst.setInt(3, sa.getIdStud());                    
           pst.setString(4, sa.getNameSubject());
               //System.out.println("id" + newId);
           //pst.setString(4,sa.getAttestation());
               System.out.println("");
           int rs =  pst.executeUpdate();
               System.out.println("rs " + rs + " " + sa.getIdStud());
               
           if(rs == 0){
               System.out.println("not update. insert");
               setAttestation(sa);
           }
               System.out.println("execute");
           }
         connection.commit();
            System.out.println("commit");
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Sorry, " + " doesn't saved./n" + ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Students attestation was updated");
    }

    @Override
    public void setAttestation(StudentAttestation attestation) {
               OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        //OraclePreparedStatement pst;
        try( Statement query = connection.createStatement(); ){

           OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(INSERT);
           pst.setString(1, Integer.toString(attestation.getIdStud()));
           
           pst.setString(2, Integer.toString(attestation.getIdAttest()));
           pst.setString(3, attestation.getNameSubject());
               //System.out.println("id" + newId);
           pst.setString(4,attestation.getAttestation());

            System.out.println(pst.toString());
           int rs =  pst.executeUpdate();
           
         connection.commit();
        }catch(Exception ex){
            try {
                connection.rollback();
                JOptionPane.showMessageDialog(null, "Sorry, " + " doesn't saved./n" + ex);
            } catch (SQLException ex1) {
                Logger.getLogger(OracleUserFactory.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new RuntimeException(ex);
        }
        JOptionPane.showMessageDialog(null, "Students attestation was created");
    }

    @Override
    public List<StudentAttestation> findByGroup(Subject name, Group group, DateAttestation da) {
        ArrayList<StudentAttestation> result = new ArrayList<>();
       OracleConnection oracleConHolder = OracleConnection.getInstance();
        Connection connection = oracleConHolder.getConnection();
        try( Statement query = connection.createStatement(); ){
            
            OraclePreparedStatement pst = (OraclePreparedStatement) connection.
                                           prepareStatement(SELECT_BY_GROUP);
           pst.setString(1,name.getName());
           pst.setString(2, group.getGroupCode());
           pst.setInt(3,da.getId());
           
            System.out.println(pst.toString());
           OracleResultSet rs = (OracleResultSet) pst.executeQuery();
            while( rs.next() ){
                result.add( new StudentAttestation( rs.getInt(1) , rs.getInt(2) ,
                                   rs.getString(3) , rs.getString(4)));
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            throw new RuntimeException(ex);
        }
        
        return result;
    }

    

 
    
}
