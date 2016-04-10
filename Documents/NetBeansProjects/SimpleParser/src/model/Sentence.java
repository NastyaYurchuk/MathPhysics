/***********************************************************************
 * Module:  Sentence.java
 * Author:  nastja
 * Purpose: Defines the Class Sentence
 ***********************************************************************/

package model;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Class describe object Sentence*/
public class Sentence implements Text, Composite {
    
   /**Regex for parse word  */
   private static final String PARSE_WORD_= "([A-Za-zа-яА-Я])\\w*(-\\w)?";
   
    /**Regex for parse word with sign */
   private static final String PARSE_WORD_AND_SIGN = "([\\\"(])?([A-Za-zа-яА-Я])\\w*([,:;)\\\".?!]*|-\\w)?";
   
    /**Regex for parse sign  */
   private static final String PARSE_SIGN = "\\W";
   
   /*List of word and punctuation mark that contai sentence*/
   private ArrayList<Text> contentSentence = new ArrayList<>();
    
    /**
     * Constructor parse input string and add element into list contentSentence
     * @param text 
     */
    public  Sentence(String text) {
        Pattern patternWordSign = Pattern.compile(PARSE_WORD_AND_SIGN);
        Matcher matcher = patternWordSign.matcher(text);
        
        while(matcher.find()){
            String match = matcher.group();
            parseWordSign(match);
        }             
    }

    @Override
    public Text getElement(int index) {
        return contentSentence.get(index);
    }

    @Override
    public void addElement(Text text) {
       contentSentence.add(text);
    }
    
     @Override
    public void setElement(int index, Text element) {
        this.contentSentence.set(index, element);
    }
   /**
    * Method parse sentence at word with sign that lay near that word
    * @param text 
    */
    public void parseWordSign(String text) {
          Pattern patternSign = Pattern.compile(PARSE_SIGN);
          Matcher matcherSign = patternSign.matcher(text);
          Pattern patternWord = Pattern.compile(PARSE_WORD_);
          Matcher matcherWord = patternWord.matcher(text);
           WordFactory wordFactory = new WordFactory();
          if(matcherWord.find()){
              this.contentSentence.add(wordFactory.getWord(matcherWord.group()));
            }
          while(matcherSign.find()){
              PunctuationMarkFactory PunctuationMarkFactory = new PunctuationMarkFactory();
                if((matcherSign.start()== 0)){
                     this.contentSentence.add( contentSentence.size() - 1,PunctuationMarkFactory.getPunctuationMark(matcherSign.group()));
                } else{
                     this.contentSentence.add(PunctuationMarkFactory.getPunctuationMark(matcherSign.group()));
                }       
            
            }

        }


    @Override
    public String toString() {
       StringBuilder sentence = new StringBuilder();
        for(int i = 0; i < this.contentSentence.size(); i++){
           sentence.append(getElement(i));
            if(getElement(i).getStringClass().equals(PunctuationMark.class.getName())){
                sentence.append(" ");
            }      
        }
        return sentence.toString(); 
       
    }

    @Override
    public String getStringClass() {
        return this.getClass().toString();
    }
    
    /**
     * Method that swap first and last word in sentence
     * @return 
     */
     public String replaceFirstLastWordReg(){
          Pattern patternWord = Pattern.compile(PARSE_WORD_);
          Matcher matcherWord = patternWord.matcher(this.toString());
          String firstWord = matcherWord.group();
          String lastWord = "";
          while(matcherWord.find()){
               lastWord = matcherWord.group();
          }
          String newSentence = matcherWord.replaceFirst(lastWord);
          
          return newSentence;
    }
    
     /**
     * Method that swap first and last word in sentence
     *  
     */
    public void replaceFirstLastWord(){
        int indexFirstWord = findIndexFirstWord();
        int indexLastWord = findIndexLastWord();
        
        Text tempWord = getElement(indexFirstWord);
        setElement(indexFirstWord, getElement(indexLastWord));
        setElement(indexLastWord, tempWord);       
    }

    /**
     * Method find index of first word in sentence
     * @return index of first word in sentence
     */
    private int findIndexFirstWord() {
        int index = 0;
        for (int i = 0; i < contentSentence.size(); i++) {
             if(getElement(i).getStringClass().equals(Word.class.getName())){
                 index = i;
                 break;
                }   
            }
       return index;    
    }

    /**
     * Method find index of last word in sentence
     * @return index of last word in sentence
     */
    private int findIndexLastWord() {
         int index = contentSentence.size() - 1;
        for (int i = contentSentence.size() - 1; i > 0; i--) {
             if(getElement(i).getStringClass().equals(Word.class.getName())){
                 index = i;
                 break;
                }   
            }
       return index;  
    }

   
}  