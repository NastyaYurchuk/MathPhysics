/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package func;

import static lab1.Tridiagonal.H;

/**
 *
 * @author nastja
 */
public class Function {
    public int variant;

    public Function(int variant) {
        this.variant = variant;
    }
    
    
    public static double getValueF(double x){
        
      //  return (- 4) * x;
        //return - Math.exp(x);
        return  Math.sin(x);
      
    }
    public static double getValueF2(double x){
        
    
    //   return - Math.exp(x);
         return   2 * Math.pow(x, 2) + 2 * x;
    }
    public static double getValueG(double x){
        double value;
     //  return 1;
        return  9;
    }
    
    public static double solveFirst(double x){
        
    //    return  2 * Math.pow(x, 3) / 3 - 8 * x /3;
    return (2 * Math.sin(x) - x * Math.sin(2)  ) / 2;    
    }
    public static double solveSecond1(double x){
        
   //    return  Math.exp(-x - 4) * ( 2 * Math.exp(2* x + 4) * x - Math.exp(2 * x) + 4 * Math.exp(2 * x + 2)- 4 * Math.exp(2 * x + 4) +3 *  Math.exp(4)) / 4;
   return - Math.exp(-3 * x) * (-36 * Math.exp(3 * x) * Math.pow(x, 2) - 72 * Math.exp(3 * x + 12) * Math.pow(x, 2) -
          36 * Math.exp(3 * x) * x - 72 *  Math.exp(3 * x + 12) * x  - 8 * Math.exp(3 * x) + 95 * Math.exp(6 * x)- 
            16 *  Math.exp(3 * x + 12) + 124 * Math.exp(6 * x + 6) - 95 * Math.exp(12) + 62 * Math.exp(6)) / (162 *(1 + 2 * Math.exp(12)));
    }
    
    public double getFi(double x){
        double value;
        switch (this.variant) {
            case 1:
                value = 0;
                break;
            case 2:
                value = 0;
                break;
            case 3:
                value = 0;
                break;
            default:
                throw new AssertionError();
        }
      return value;          
    }
    
    public double getVi(double x){
        double value;
        switch (this.variant) {
            case 1:
                value = 20;
                break;
            case 2:
                value = 4;
                break;
            case 3:
                value = 10;
                break;
            default:
                throw new AssertionError();
        }
      return value;          
    }
    public double getViPlus(double x){

      return 0.5 * (this.getVi(x) + Math.abs(this.getVi(x)));          
    }
    public double getViMin(double x){
     
      return 0.5 * (this.getVi(x) - Math.abs(this.getVi(x)));           
    }
    
     public double getQi(double x){
        double value;
        switch (this.variant) {
            case 1:
                value = 21;
                break;
            case 2:
                value = 5;
                break;
            case 3:
                value = 11;
                break;
            default:
                throw new AssertionError();
        }
      return value;          
    }
     public double getKi(double x){
        double value;
        switch (this.variant) {
            case 1:
                value = 1;
                break;
            case 2:
                value = 1;
                break;
            case 3:
                value = 1;
                break;
            default:
                throw new AssertionError();
        }
      return value;          
    }
     
    public double getRi(double x){
        
      return H * Math.abs(getVi(x))/ getKi(x);          
    }
    
    public double getAnalytic(double x){
        double value;
        switch (this.variant) {
            case 1:
                value = Math.exp(x) *(2-(2-2*Math.exp(4))/(Math.exp(-21*2)-Math.exp(4)))+Math.exp(-21*x)
                   * (2-2*Math.exp(4))/(Math.exp(-21*2)-Math.exp(4));
                break;
            case 2:
                value = Math.exp(x) *(3-(-2*Math.exp(2))/(Math.exp(-5*2)-Math.exp(2)))+Math.exp(-5*x)
                   * (-2*Math.exp(2))/(Math.exp(-5*2)-Math.exp(2));
                break;
            case 3:
                value = Math.exp(x) *(3-(3-3*Math.exp(3))/(Math.exp(-33)- Math.exp(3)))+Math.exp(-11*x)
                   * (3-3*Math.exp(3))/(Math.exp(-33)-Math.exp(3));
                break;
            default:
                throw new AssertionError();
        }
      return value;     
    }
    
    public double getGi(double x){
        double value;
        switch (this.variant) {
            case 1:
                value = x;
                break;
            case 2:
                value = x * x + 4;
                break;
            case 3:
                value = 1;
                break;
            default:
                throw new AssertionError();
        }
      return value;          
    }
    
    public double getMui(double x){
        double value;
        switch (this.variant) {
            case 1:
                value =  x;
                break;
            case 2:
                value = 4;
                break;
            case 3:
                value = 1;
                break;
            default:
                throw new AssertionError();
        }
      return value;          
    }
    
    public double getAi(double x){
        double value;
        switch (this.variant) {
            case 1:
                value = 2;
                break;
            case 2:
                value = 4;
                break;
            case 3:
                value = 1;
                break;
            default:
                throw new AssertionError();
        }
      return value;          
    }

    public double getUi(double H) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getGi(double d, double d0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
}
