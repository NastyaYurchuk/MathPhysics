/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author nastja
 */
public class Function {
    
    public static double getValueF(double x){
        
      //  return (- 4) * x;
        //return - Math.exp(x);
        return  Math.sin(x);
      
    }
    public static double getValueF2(double x){
        
       return   2 * Math.pow(x, 2) + 2 * x;
      //  return - Math.exp(x);
    }
    public static double getValueG(double x){
        
       // return 1;
        return  9;
    }
    
    public static double solveFirst(double x){
        
    //    return  2 * Math.pow(x, 3) / 3 - 8 * x /3;
    return (2 * Math.sin(x) - x * Math.sin(2)  ) / 2;    
    }
    public static double solveSecond1(double x){
        
       // return  Math.exp(-x - 4) * ( 2 * Math.exp(2* x + 4) * x - Math.exp(2 * x) + 4 * Math.exp(2 * x + 2)- 4 * Math.exp(2 * x + 4) +3 *  Math.exp(4)) / 4;
    return - Math.exp(-3 * x) * (-36 * Math.exp(3 * x) * Math.pow(x, 2) - 72 * Math.exp(3 * x + 12) * Math.pow(x, 2) -
            36 * Math.exp(3 * x) * x - 72 *  Math.exp(3 * x + 12) * x  - 8 * Math.exp(3 * x) + 95 * Math.exp(6 * x)- 
             16 *  Math.exp(3 * x + 12) + 124 * Math.exp(6 * x + 6) - 95 * Math.exp(12) + 62 * Math.exp(6)) / (162 *(1 + 2 * Math.exp(12)));
    }
    
}
