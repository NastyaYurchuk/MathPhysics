/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import controller.Main;

/**
 *
 * @author nastja
 */
public class Output {
    
    /**
     * Method write in file input string
     * @param fileWriter - fileWriter
     * @param line - String that wrote
     * @throws IOException 
     */
    public void writesString(FileWriter fileWriter, String line) throws IOException{
        fileWriter.append(line);
    }
    
    /**
     * Write in file with that name, object
     * @param nameFile - name of output file
     * @param object - object that wrote in  file
     */
    public void writeFile(String nameFile, Object object){
         FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(nameFile);
                writesString(fileWriter, object.toString());          
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 
}
