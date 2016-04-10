/***********************************************************************
 * Module:  SignFactory.java
 * Author:  nastja
 * Purpose: Defines the Class SignFactory
 ***********************************************************************/

package model;

import java.util.*;

/** Factory for PunctuationMark */
public class PunctuationMarkFactory {
   /**HashMap with all object of Punctuation mark*/
   private static HashMap<Character, Text> punctuationMarkMap = new HashMap<>();

   /**
     * Method that check if punctuation mark is already created and if - exit
     * return punctuation mark from Map, else - create new and add it into Map.
     * @param text
     * @return 
     */
    Text getPunctuationMark(String text) {
         Character symbol = text.charAt(0); 
        PunctuationMark punctuationMark = (PunctuationMark)punctuationMarkMap.get(symbol);
        
        if(punctuationMark == null){
            punctuationMark = new PunctuationMark(symbol);
            punctuationMarkMap.put(symbol, punctuationMark);
        }
        return punctuationMark;
    }
}