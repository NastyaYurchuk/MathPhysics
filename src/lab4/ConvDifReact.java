/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import func.Function;
import static lab1.Tridiagonal.H;
import static lab3.ConvTransfer.R;

/**
 *
 * @author nastja
 */
public class ConvDifReact {
     
   public static final double R = 0.01;
   
   public double l;
   public Function f;
   public double t;
   public double v;

    public ConvDifReact(double l , double t, Function f, double v) {
        this.l = l;
        this.f = f;
        this.t = t;
        this.v = v;
    }
    
    public double[][] centralExplicit(){
        int n =  Math.round((float) (this.l/H)) + 1;
        int m = Math.round((float) (this.t/R)) + 1;
        double [][] y = new double[m][n];
        
        y[0][0] = f.getGi(0);
        y[0][1] = f.getUi(H);
        double kh = v * R / H;
        for (int k = 0; k < n; k++) {
            y[0][k] = f.getUi(k * H);           
        }
        y[0][n - 1] = f.getGi(l);
        for (int j = 0; j < m - 1; j++) {
            for (int k = 0; k < n; k++) {              
                if (k == n - 1 || k == 0){
                    y[j + 1][k] = f.getGi(k * H);; 
                } else if (k != 0){
                     y[j + 1][k] = y[j][k + 1] *  (R * f.getMui(R)/ (H * H) - this.v * R / (2 * H)) + 
                             (1 - 2 * R * f.getMui(R)/ (H * H) - f.getKi(R)) * y[j][k] + 
                            (R * f.getMui(R)/ (H * H) + this.v * R / (2 * H)) * y[j][k-1] ;
                }  
               
            }
        }
        return y;
    }
     
}
