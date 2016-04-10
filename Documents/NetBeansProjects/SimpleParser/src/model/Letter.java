/***********************************************************************
 * Module:  Letter.java
 * Author:  nastja
 * Purpose: Defines the Class Letter
 ***********************************************************************/

package model;

import java.util.*;

/** Class describe object Letter */
public class Letter implements Text{
    
   /* Attribute keep char of letter*/
   private char letter;

    Letter(Character symbol) {
        letter = symbol;
    }

   @Override
    public String getStringClass() {
        return this.getStringClass().toString();
    }

    @Override
    public String toString() {
        return Character.toString(letter); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

}