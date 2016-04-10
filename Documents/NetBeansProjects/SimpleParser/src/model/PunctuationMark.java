/***********************************************************************
 * Module:  PunctuationMark.java
 * Author:  nastja
 * Purpose: Defines the Class PunctuationMark
 ***********************************************************************/

package model;

import java.util.*;

/** Class describe object Punctuation Mark */
public class PunctuationMark implements Text {
    
   /* Attribute keep char of punctuation mark*/
   private Character punctuationMark;
   

    PunctuationMark(Character symbol) {
        punctuationMark = symbol;
    }

    @Override
    public String getStringClass() {
        return this.getClass().getName();
    }

    @Override
    public String toString() {
        return Character.toString(punctuationMark);
    }
    
    
    
}