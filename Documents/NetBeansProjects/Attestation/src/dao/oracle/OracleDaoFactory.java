/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.oracle;

import dao.DaoFactory;
import dao.DateAttestationDao;
import dao.GroupDao;
import dao.LecturerWorkloadDao;
import dao.ResultLectureDao;
import dao.StudentAttestationDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.UserDao;

/**
 *
 * @author nastja
 */
public class OracleDaoFactory extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new OracleUserFactory();
    }

    @Override
    public SubjectDao createSubjectDao() {
        
        return new OracleSubjectFactory();
    }

    @Override
    public GroupDao createGroupDao() {
        return new OracleGroupFactory();
    }

    @Override
    public StudentDao createStudentDao() {
        
        return new OracleStudentFactory();
    }

    @Override
    public LecturerWorkloadDao createLecturerWorkloadDao() {
        return new OracleLectWorkloadFactory();
    }

    @Override
    public ResultLectureDao createResultLectureDao() {
        return new OracleResultLectureFactory();
    }

    @Override
    public DateAttestationDao createDateAttestationDao() {
        return new OracleDateAttestation();
    }

    @Override
    public StudentAttestationDao createStudentAttestationDao() {
       return new OracleStudentAttestationFactory();
    }
    
    
   

    
   
    
}
