/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author nastja
 */
public class CompositeFileContent implements Text, Composite{
    /*Regexp for finding sentence*/
    private static final String PARSE_SENTENCE = "([А-ЯA-Z]([^?!.\\(]|\\([^\\)]*\\))*([.!?])+)" ;
    
    /*Regexp for finding paragraph that are not a code*/
    private static final String PARSE_PARAGRAPH_NOT_CODE = "[А-ЯA-Z].+[.!?]\\Z" ;
    
    /* list of composite-object*/
    private List<Text> contentFile = new ArrayList<Text>();

    private int size;
    
    /**
     * Constructor that parse input String to code and sentence
     * @param text 
     */
    public CompositeFileContent(String text) {
        Pattern pattern = Pattern.compile(PARSE_PARAGRAPH_NOT_CODE);
        String[] paragraphs = text.split("\n");
        List<String> setParagraphCode= new ArrayList<String>();
        
        for(String paragraph : paragraphs){
            Matcher  matcher = pattern.matcher(paragraph);
              if(matcher.find()){
                  parseSentence(paragraph);
                  createCode(setParagraphCode);
                }
              else{ 
                  setParagraphCode.add(paragraph);
                }              
        }
       createCode(setParagraphCode);
       
       size = contentFile.size();
    }

    /**
     * Method add element to list contentFile
     * @param text 
     */
    @Override
    public void addElement(Text text) {
         contentFile.add(text);
         ++size;
    }
    
    /**
     * Method get element from contentFile
     * @param index
     * @return 
     */
    @Override
    public Text getElement(int index){
        return contentFile.get(index);
    }
    
    @Override
    public void setElement(int index, Text element) {
        contentFile.set(index, element);
    }
    
    /**
     * Method get size of object
     * @return size
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Method parse paragraph to sentence and add sentence to contentFile
     * @param paragraph 
     */
    public void parseSentence(String paragraph) {
        Pattern pattern = Pattern.compile(PARSE_SENTENCE);
        Matcher matcher = pattern.matcher(paragraph);
        while (matcher.find()) {
            String match = matcher.group();
            this.contentFile.add(new Sentence(match));
        }
    }
    
    @Override
    public String getStringClass() {
        return this.getClass().toString();
    }

    @Override
    public String toString() {
        StringBuilder allText = new StringBuilder();
        for (int i = 0; i < this.contentFile.size(); i++) {
            //System.out.println(i + getElement(i).toString());
            allText.append(getElement(i).toString());
        }
        return allText.toString();
    }
    
    /**
     * Method swap first and last word in all sentence in contentFile
     */
    public void replaceAllFirstLastWord(){
        for(Text text : contentFile){
            System.out.println(text.getStringClass());
            System.out.println(text);
            if(text.getClass().getName().equals(Sentence.class.getName())){
                Sentence sentence = (Sentence)text;
                sentence.replaceFirstLastWord();
            }
        }
    }
    
    /**
     * Method create object of class code and add it to the contentFile
     * @param setParagraphCode 
     */
    private void createCode(List<String> setParagraphCode) {
        if(!setParagraphCode.isEmpty()){
            addElement(mergeCode(setParagraphCode));
            System.out.println(mergeCode(setParagraphCode).toString());
            setParagraphCode.clear();
        }
    }
    
    /**
     * Method concatenate all string with code and create object of Code class
     * @param setParagraphCode
     * @return Code
     */
    private Code mergeCode(List<String> setParagraphCode){
        
       StringBuilder allText = new StringBuilder();
        for(String code : setParagraphCode){
            allText.append(code);
        }
        return new Code(allText.toString());
    }
   
}
