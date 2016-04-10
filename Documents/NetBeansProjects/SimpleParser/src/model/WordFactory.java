/***********************************************************************
 * Module:  WordFactory.java
 * Author:  nastja
 * Purpose: Defines the Class WordFactory
 ***********************************************************************/

package model;

import java.util.*;

/** Factory of the Word */
public class WordFactory {
    
   /**HashMap with all object of Word*/
   private static HashMap<String, Word> wordMap = new HashMap<>();
    
   /**
     * Method that check if Word is already created and if - exit
     * return Word from Map, else - create new and add it into Map.
     * @param text
     * @return 
     */
    Text getWord(String text) {
        Word word = (Word)wordMap.get(text);
        
        if(word == null){
            word = new Word(text);
            wordMap.put(text, word);
        }
        return word;
    }

}