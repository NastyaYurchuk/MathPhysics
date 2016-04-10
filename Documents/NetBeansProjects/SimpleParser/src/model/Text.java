/***********************************************************************
 * Module:  Text.java
 * Author:  nastja
 * Purpose: Defines the Interface Text
 ***********************************************************************/

package model;

import java.util.*;

/** Interface show that object are text  */
public interface Text {
    
    /**
     * Method find name of class which object are instance
     * @return 
     */
    String getStringClass();

    @Override
    public String toString();
    
    
    
}