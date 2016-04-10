/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.entities.Group;
import dao.entities.LecturerWorkload;
import dao.entities.Student;
import dao.entities.Subject;
import dao.entities.User;
import java.util.List;

/**
 *
 * @author nastja
 */
public interface LecturerWorkloadDao {
    LecturerWorkload find(int id);
    
    boolean delete(LecturerWorkload lecturerWorkload);
    
    boolean update(LecturerWorkload lecturerWorkload);
    
  //  boolean updateByGroup(Group oldGroup, Group newGroup);
    
    LecturerWorkload create(int id, String name, String groupCode);
    
    void create(LecturerWorkload lecturerWorkload);
    
    List<LecturerWorkload> findAll();
    
    List<LecturerWorkload> findGroupList(String name, String groupCode);
    
    List<Subject> findSubjList(User user);
    
    List<Group> findGroupList(User user, Subject subject );
    
    List<LecturerWorkload> findWorkload(User user);
    
    public List<Group> findFreeGroup(Subject subject);
    
    List<Subject> findSubjectsOfGroup(Group group);
}
