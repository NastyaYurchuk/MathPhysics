/***********************************************************************
 * Module:  Word.java
 * Author:  nastja
 * Purpose: Defines the Class Word
 ***********************************************************************/

package model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Class describe object Sentence*/
public class Word implements Text, Composite {
    
   /** Regex for parse word to symbol */ 
   private static final String PARSE_SYMBOL = "[\\w-?]";
    
   /** ArrayList with symbol that content in word*/
   private ArrayList<Text> contentWord = new ArrayList<>();

   /**
    * Constructor that parse input text into symbol and add it to contentWord
    * @param text 
    */
    Word(String text) {
       //System.out.println("WORD constr");
        Pattern patternWordSign = Pattern.compile(PARSE_SYMBOL);
        //Pattern patternWord = Pattern.compile(PARSE_WORD_);
        LetterFactory letterFactory = new LetterFactory();
        Matcher matcher = patternWordSign.matcher(text);
        
        while(matcher.find()){
            String match = matcher.group();
            //System.out.println(match);
            this.contentWord.add(letterFactory.getLetter(match));
        }  
    }

    @Override
    public void addElement(Text text) {
        contentWord.add(text);
    }

    @Override
    public Text getElement(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public String getStringClass() {
        return this.getClass().getName();
    }

    @Override
    public void setElement(int index, Text element) {
        contentWord.set(index, element);
    }

    @Override
    public String toString() {
        String word = "";
        for(Text text: contentWord){
            word += text.toString();
        }
        return " " + word;
    }
    
    
    
    

}