/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

public abstract class DaoFactory {
    
 public abstract UserDao createUserDao();
    
 public abstract SubjectDao createSubjectDao();
 
 public abstract GroupDao createGroupDao();
 
  public abstract StudentDao createStudentDao();
 
  public abstract LecturerWorkloadDao createLecturerWorkloadDao();
  
 public abstract ResultLectureDao createResultLectureDao();
  
 public abstract DateAttestationDao createDateAttestationDao();
 
 public abstract StudentAttestationDao createStudentAttestationDao();
    
    public enum DaoType {
        ORACLE
    }
    
    public static DaoFactory getInstance(DaoType type){
        switch( type ){
            case ORACLE:
                //new MySqlDaoFactory
                try {
                    Class clazz = Class.forName("dao.oracle.OracleDaoFactory");
                    return (DaoFactory) clazz.newInstance();
                } catch (Exception ex) {
                        return null;
                }
            
        };
                
      return null;  
        
    }
    
}
