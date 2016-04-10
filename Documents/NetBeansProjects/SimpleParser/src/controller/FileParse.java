/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CompositeFileContent;

/**
 *
 * @author nastja
 */
public class FileParse {
   
    /**
     * Method parse all text in file
     * @param fileName - file name
     * @return compositeFileContent - contents list of parsed object
     */
    public CompositeFileContent parseAllText(String fileName) {
    
        String text = read(fileName);
        CompositeFileContent compositeFileContent = new CompositeFileContent(text);
        
       return compositeFileContent;
    }

   /**
    * Method read all text in file 
    * @param fileName
    * @return  string with all text
    */
    private String read(String fileName)  {
        CharBuffer cbuf = null;
        try {
            FileInputStream input = new FileInputStream(fileName);
            FileChannel channel = input.getChannel();
            ByteBuffer bbuf = channel.map(FileChannel.MapMode.READ_ONLY, 0, (int) channel.size());
            cbuf = Charset.forName("8859_1").newDecoder().decode(bbuf);
            
        } catch (CharacterCodingException ex ) {
            Logger.getLogger(FileParse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileParse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileParse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cbuf.toString();
    }
    
}
