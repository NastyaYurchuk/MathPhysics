/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTextField;

/**
 *
 * @author nastja
 */
public class CheckInput {
    
    public boolean checkPassword(char[] password){
        //System.out.println(password.toString());
        String pass = new String(password);
        System.out.println(pass);
        Pattern p = Pattern.compile("^[a-z0-9_-]{6,18}$");  
        Matcher m = p.matcher(pass);  
        return m.matches(); 
    }

    public boolean checkLogin(String login) {
       
        Pattern p = Pattern.compile("^[a-z0-9_-]{3,15}$");  
        Matcher m = p.matcher(login);  
        return m.matches(); 
    }

    public boolean checkMark(String mark) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean checkName(String name) {
        Pattern p = Pattern.compile("(([A-Za-zА-Яа-я]+|\\s){6,15})");  
        Matcher m = p.matcher(name);  
        return m.matches();
    }
}
