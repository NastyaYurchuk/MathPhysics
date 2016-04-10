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
public class User {
    private int id;
    private String login;
    private String password;
    private String name;
    private String isAdmin;
    

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  boolean isAdmin(){
        return "y".equals(isAdmin);
    }
    
    public  boolean isEmpty(){
        return login.isEmpty();
    }
    public User(int ID, String login, String name, String password,  String isAdmin) {
        this.id = ID;
        this.login = login;
        this.password = password;
        this.name = name;
        this.isAdmin = isAdmin;
    }
    public  User(){
        
    }
    
    
}
