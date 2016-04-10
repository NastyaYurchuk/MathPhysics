/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.CompositeFileContent;
import model.PunctuationMarkFactory;
import model.Sentence;
import model.Text;
import model.WordFactory;
import view.Output;


/**
 *
 * @author nastja
 */
public class Main {
    /*Name of file which is reading*/
    private static final String NAME_FILE = "D://a.txt";
    
    /*Name of file in which we write parsed text*/
    private static final String NAME_NEW_FILE = "D://new.txt";
    
    /*Name of file in which we write parsed text*/
    private static final String NAME_REPLACE_FILE = "D://newReplace.txt";
    
    public static void main(String[] args) {
        FileParse fileWork = new FileParse();
        CompositeFileContent compositeFileContent = fileWork.parseAllText(NAME_FILE);
        //System.out.println("bjbj" + compositeFileContent.toString());
        Output output = new Output(); 
        output.writeFile(NAME_NEW_FILE, compositeFileContent);
        
        compositeFileContent.replaceAllFirstLastWord();
        
        output.writeFile(NAME_REPLACE_FILE, compositeFileContent);
        
        
        
    }

}

