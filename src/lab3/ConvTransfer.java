/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import func.Function;
import java.util.Arrays;
import lab1.Tridiagonal;
import static lab1.Tridiagonal.H;

/**
 *
 * @author okurylo
 */
public class ConvTransfer {
    
   public static final double R = 0.01;
   
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
           System.out.println("nr = " + n);
          System.out.println("mr = " + m);
        y[0][0] = f.getMui(0);
        y[0][1] = f.getGi(H);
        
        for (int k = 0; k < n; k++) {
            y[0][k] = f.getGi(k * H);
            
        }
        for (int j = 0; j < m - 1; j++) {
            for (int k = 0; k < n; k++) {
                if (k != 0){
                     y[j + 1][k] = (1 - a * R / H) * y[j][k] + a * R * y[j][k - 1] / H; 
                }
                else {
                     y[j + 1][k] = f.getMui((j + 1) * R);
                }  
              
            }
        }
      System.out.println("nr = " + n);
          System.out.println("mr = " + m);   
        
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
        }
        return y;
    }
     
      public double[][] centralImplicit(){
        int n =  Math.round((float) (this.l/H)) + 1;
        int m = Math.round((float) (this.t/R)) + 1;
        double [][] y = new double[m][n];
          System.out.println("n = " + n);
          System.out.println("m = " + m);
        double[] ai = new double[n + 1];
        double[] bi = new double[n + 1];
        double[] ci = new double[n + 1];
        double[] gi = new double[n + 1];
        y[0][0] = f.getMui(0);
        y[0][1] = f.getGi(H);
        double kh = a * R /  (2 * H);
         for (int k = 0; k < n; k++) {
            y[0][k] = f.getGi(k * H);
        }
         ConvTransfer convTransfer = new ConvTransfer(this.l, this.t, f, this.a);
        double[][] yEx = convTransfer.rightExplicit();
        System.out.println("n = " + yEx[0].length);
          System.out.println("m = " + yEx.length);
         for (int k = 0; k < n ; k++) {
            ai[k] = - a / (2 * H);
            bi[k] = - 1 / R;
            ci[k] = a / ( 2 * H);
            gi[k]= f.getGi(k * H) / R;
        }
        double cons = a * R / (2 * H);
        for (int j = 0; j < m - 1; j++) {
             y[j + 1] = tridiaonal(n - 1, ci, bi, ai, gi, yEx[j + 1][n - 1] );
            y[j + 1][0] = f.getMui((j + 1) * R);
             for (int k = 0; k < n ; k++) {
                gi[k] = y[j + 1][k] / R;
                
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
         ConvTransfer convTransfer = new ConvTransfer(this.l, this.t, f, this.a);
        double[][] yEx = convTransfer.rightExplicit();
        
         for (int k = 0; k < n ; k++) {
            ai[k] = - a / (4 * H);
            bi[k] =  - 1 / R;
            ci[k] = a / (4 * H);            
        }
         gi =  freeMember(0, y, n);
        for (int j = 0; j < m - 1; j++) {
            y[j + 1] = tridiaonal(n - 1, ci, bi, ai, gi, yEx[j + 1][n - 1] );
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
                gi[k] = y[j][k - 1] * a / (4 * H) -  y[j][k + 1] * a / (4 * H)  + y[j][k] / R;
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
