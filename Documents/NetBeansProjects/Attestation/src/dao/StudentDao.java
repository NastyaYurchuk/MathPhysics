
package dao;

import dao.entities.Group;
import dao.entities.Student;
import java.util.List;

/**
 *
 * @author nastja
 */
public interface StudentDao {
    Student find(int id);
    
    boolean delete(Student student);
    
    boolean update(Student student);
    
    boolean updateByGroup(Group oldGroup, Group newGroup);
    
    Student create(String name, String groupCode);
    
    List<Student> findAll();
    
    List<Student> findGroupList(Group groupCode);
    
}
