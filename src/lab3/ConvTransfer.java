/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import func.Function;
import java.util.Arrays;

/**
 *
 * @author okurylo
 */
public class ConvTransfer {
    
   public static final double H = 0.1;
   public static final double R = 0.02;
   
   public double l;
   public Function f;
   public double t;
   public double a;

    public ConvTransfer(double l , double t, Function f, double a) {
        this.l = l;
        this.f = f;
        this.t = t;
        this.a = a;
    }
    
    public double[][] rightExplicit (){
        int n =  Math.round((float) (this.l/H)) + 1;
        int m = Math.round((float) (this.t/R)) + 1;
        double [][] y = new double[m][n];
        
        y[0][0] = f.getMui(0);
        y[0][1] = f.getGi(H);
        
        for (int k = 0; k < n; k++) {
            y[0][k] = f.getGi(k * H);
            
        }
        System.out.println("y " + Arrays.toString(y[0]));
        for (int j = 0; j < m - 1; j++) {
            for (int k = 0; k < n; k++) {
                if (k != 0){
                     y[j + 1][k] = (1 - a * R / H) * y[j][k] + a * R * y[j][k - 1] / H; 
                }
                else {
                     y[j + 1][k] = f.getMui((j + 1) * R);
                }  
              
            }
            System.out.println("y " + Arrays.toString(y[j + 1])); 
        }
        
        
    return y;
    }
   
    public double[][] rightImplicit(){
        int n =  Math.round((float) (this.l/H)) + 1;
        int m = Math.round((float) (this.t/R)) + 1;
        double [][] y = new double[m][n];
        
        y[0][0] = f.getMui(0);
        y[0][1] = f.getGi(H);
         for (int k = 0; k < n; k++) {
            y[0][k] = f.getGi(k * H);
            
        }
        double kh = a * R / H;
        for (int j = 0; j < m - 1; j++) {
            for (int k = 0; k < n; k++) {
                if (k != 0){
                     y[j + 1][k] = (y[j][k] + y[j][k - 1] * kh) / (1 + kh); 
                }
                else {
                     y[j + 1][k] = f.getMui((j + 1) * R);
                }  
              
            }
            System.out.println("yIm " + Arrays.toString(y[j + 1])); 
        }
        return y;
    }
    
     public double[][] centralExplicit(){
        int n =  Math.round((float) (this.l/H)) + 1;
        int m = Math.round((float) (this.t/R)) + 1;
        double [][] y = new double[m][n];
        
        y[0][0] = f.getMui(0);
        y[0][1] = f.getGi(H);
        double kh = a * R / H;
         for (int k = 0; k < n; k++) {
            y[0][k] = f.getGi(k * H);
            
        }
        double cons = a * R / (2 * H);
        for (int j = 0; j < m - 1; j++) {
            for (int k = 0; k < n; k++) {              
                if (k == n - 1){
                    y[j + 1][k] = (y[j][k] + y[j][k - 1] * kh) / (1 + kh); 
                } else if (k != 0){
                     y[j + 1][k] = (y[j][k - 1] - y[j][k + 1] ) / cons + y[j][k]; 
                }
                else {
                     y[j + 1][k] = f.getMui((j + 1) * R);
                }  
               
            }
            System.out.println("yCenEx " + Arrays.toString(y[j + 1])); 
        }
        return y;
    }
     
      public double[][] centralImplicit(){
        int n =  Math.round((float) (this.l/H)) + 1;
        int m = Math.round((float) (this.t/R)) + 1;
        double [][] y = new double[m][n];
        
        y[0][0] = f.getMui(0);
        y[0][1] = f.getGi(H);
        double kh = a * R / H;
         for (int k = 0; k < n; k++) {
            y[0][k] = f.getGi(k * H);
            
        }
        double cons = a * R / (2 * H);
        for (int j = 0; j < m - 1; j++) {
            for (int k = 0; k < n; k++) {              
                if (k == n - 1){
                    y[j + 1][k] = (y[j][k] + y[j][k - 1] * kh) / (1 + kh); 
                } else if (k != 0){
                     y[j + 1][k] = (y[j + 1][k - 1] - y[j][k + 1] ) / cons + y[j][k]; 
                }
                else {
                     y[j + 1][k] = f.getMui((j + 1) * R);
                }  
               
            }
            System.out.println("yCenEx " + Arrays.toString(y[j + 1])); 
        }
        return y;
    }
   
}
