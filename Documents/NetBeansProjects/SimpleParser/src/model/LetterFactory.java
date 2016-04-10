/***********************************************************************
 * Module:  LetterFactory.java
 * Author:  nastja
 * Purpose: Defines the Class LetterFactory
 ***********************************************************************/

package model;

import java.util.*;

/** Factory for Letter */
public class LetterFactory {
    /*HashMap with all object of Letter*/
    private static HashMap<Character, Letter> letterMap = new HashMap<>();
    
    /**
     * Method that check if letter is already created and if - exit
     * return letter from Map, else - create new and add it into Map.
     * @param text
     * @return 
     */
     Text getLetter(String text) {
        Character symbol = text.charAt(0); 
        Letter letter = (Letter)letterMap.get(symbol);
        
        if(letter == null){
            letter = new Letter(symbol);
            letterMap.put(symbol, letter);
        }
        return letter;
    }
}