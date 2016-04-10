/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
