/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.entities.Group;
import dao.entities.Subject;
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
