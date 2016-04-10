/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.entities.Group;
import dao.entities.Student;
import dao.entities.Subject;
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
