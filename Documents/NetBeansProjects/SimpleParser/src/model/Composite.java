/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author nastja
 */
public interface Composite {
    /**
     * Method add element to list in composite-object 
     * @param text 
     */
    public void addElement(Text text);
    
    /**
     * Method get element from composite-object
     * @param index - index of element in list
     * @return  element of list composite object
     */
    public Text getElement(int index);
    
    /**
     * Method set element to list in composite-object 
     * @param index - index of element
     * @param element 
     */
    public void setElement(int index, Text element);
}
