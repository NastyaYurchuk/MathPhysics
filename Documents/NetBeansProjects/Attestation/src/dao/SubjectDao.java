
package dao;


import dao.entities.Subject;
import java.util.List;
/**
 *
 * @author nastja
 */
public interface SubjectDao {
    
    Subject find(String subject);
    
    boolean delete(Subject subject);
    
    boolean update(Subject oldSubject, Subject newSubject);
    
    Subject create( Subject subject);
    
    List<Subject> findAll();
    
    
    
}
