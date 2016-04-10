
package dao;

import dao.entities.Group;
import java.util.List;

/**
 *
 * @author nastja
 */
public interface GroupDao {
    
    Group find(Group groupCode);
    
    boolean delete(Group group);
    
    boolean update(Group oldGroup, Group newGroup);
    
    Group create( Group group);
    
    List<Group> findAll();
}
