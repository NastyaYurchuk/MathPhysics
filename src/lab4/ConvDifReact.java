/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import func.Function;
import java.util.Arrays;
import static lab1.Tridiagonal.H;
import lab3.ConvTransfer;
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
   public double a;

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
    
        public double[][] centralImplicitCrunk(){
        int n =  Math.round((float) (this.l/H)) + 1;
        int m = Math.round((float) (this.t/R)) + 1;
        double [][] y = new double[m][n];
        double[] ai = new double[n + 1];
        double[] bi = new double[n + 1];
        double[] ci = new double[n + 1];
        double[] gi = new double[n + 1];
        y[0][0] = f.getMui(0);
        y[0][1] = f.getGi(H);
         for (int k = 0; k < n; k++) {
            y[0][k] = f.getGi(k * H);
        }
      
         for (int k = 0; k < n ; k++) {
            ai[k] = - v / (4 * H);
            bi[k] =  - 1 / R;
            ci[k] = v / (4 * H);            
        }
         gi =  freeMember(0, y, n);
        for (int j = 0; j < m - 1; j++) {
            y[j + 1] = tridiaonal(n - 1, ci, bi, ai, gi, ci[j] );
            y[j + 1][0] = f.getMui((j + 1) * R);
            gi = freeMember((j +1), y, n);
            
            System.out.println("gi = " + Arrays.toString(gi));
        }
        return y;
    }

    private double[] freeMember(int j, double[][] y, int n) {
        double[] gi = new double[n];
        for (int k = 0; k < n; k++) {
            if (k == 0) {
                gi[k] = f.getGi(k * H);
            } else if (k == n - 1) {
                gi[k] = f.getGi(k * H);
            } else {
                gi[k] = y[j][k - 1] * v / (4 * H) -  y[j][k + 1] * v / (4 * H)  + y[j][k] / R;
            }
        }
        return gi;
    }
    
     public double[] tridiaonal(int n, double[] c, double[] b, double[] a, double[] g, double yn) {
        double[] s = new double[n + 1];
        double[] z = new double[n + 1];
        s[0] = c[0]/ b[0];
        z[0] = (- 1) * g[0] / b[0];
        
        for (int i = 1; i < z.length; i++) {
            s[i] = c[i] / (b[i] - s[i-1] * a[i]);
            z[i] = ( z[i-1] * a[i] - g[i] ) / (b[i] - s[i-1] * a[i]); 
        }
        z[n] = yn;//for 8
        double[] y = new double[n + 1];
        y[n ] = z[n];
        for (int i = n - 1; i >= 0; i--) {
            y[i] = s[i] * y[i + 1] + z[i]; 
        }
        return y;
    }
}
