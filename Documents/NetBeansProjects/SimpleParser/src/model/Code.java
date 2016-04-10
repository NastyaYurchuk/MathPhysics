/***********************************************************************
 * Module:  Code.java
 * Author:  nastja
 * Purpose: Defines the Class Code
 ***********************************************************************/

package model;

import java.util.*;

/** @pdOid 0c56b63b-0449-401f-a610-04dd3bf1dc28 */
public class Code implements Text {
    /* String containes source code*/
    private String codeText;

    Code(String code) {
        codeText = code;
    }

    @Override
    public String toString() {
        return codeText;
    }

    @Override
    public String getStringClass() {
        return this.getClass().toString();
    }




}