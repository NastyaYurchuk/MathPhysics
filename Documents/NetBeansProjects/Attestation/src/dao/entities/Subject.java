/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.entities;

/**
 *
 * @author nastja
 */
public class Subject {
    
   private String name;

    public Subject(String groupCode) {
        this.name = groupCode;
    }

    public Subject() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return  name ;
    }

    

    
    
    
    
}
