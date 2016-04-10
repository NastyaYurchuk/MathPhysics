/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package validators;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author nastja
 */

public class JTextFieldLimit extends PlainDocument {
  private int limit;
  
  
  public JTextFieldLimit(int limit) {
   
   this.limit = limit;
   }
   
  public void insertString
    (int offset, String  str, AttributeSet attr)
      throws BadLocationException {
   if (str == null) return;

   else if ((getLength() + str.length()) <= limit) {
     
     super.insertString(offset, str, attr);
     }
   }
}