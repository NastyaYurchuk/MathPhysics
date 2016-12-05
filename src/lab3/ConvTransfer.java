/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import func.Function;

/**
 *
 * @author okurylo
 */
public class ConvTransfer {
    
   public static final double H = 0.2;
   public static final double R = 0.05;
   
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
        double [][] y = new double[n][m];
        
        y[0][0] = f.getMui(0);
        y[1][0] = f.getGi(H);
        
        for (int k = 0; k < n; k++) {
            y[k][0] = f.getGi(k * H);
            
        }
        for (int j = 0; j < m - 1; j++) {
            for (int k = 0; k < n; k++) {
                if (k != 0){
                     y[k][j + 1] = (1 - a) * y[k][j] + a * R * y[k - 1][j] / H; 
                }
                else {
                     y[k][j + 1] = f.getMui(j * R);
                }            
            }
            
        }
        
        
    return y;
    }
   
   
}
