/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.Arrays;


/**
 *
 * @author nastja
 */
public class Tridiagonal {
    
    public static final double STEP = 0.1;
    public double l;
    public double k;
    public double beta;
    public double gamma1;
    public double gamma2;
    public Function kF;
    public Function vF;
    public Function qF;
    public Function fF;

    public Tridiagonal(double l, double k, double beta, double gamma1, double gamma2) {
        this.k = k;
        this.beta = beta;
        this.gamma1 = gamma1;
        this.gamma2 = gamma2;
        this.l = l;
    }
    
    public Tridiagonal(Function kF, Function vF, Function qF, Function fF ){
        this.fF = fF;
        this.kF = kF;
        this.qF = qF;
        this.vF  = vF;
    }
   
    public double[] solveDiffusionEq(){
        int n = Math.round((float) (this.l/STEP));
        double y[] = new double[n + 1];
        
        return y;
        
    }
    public double[] solve() {
       int n = Math.round((float) (this.l/STEP));
       double a[] = new double[n+1];
       double c[] = new double[n+1];
       double g[] = new double[n+1];
       g[0] = gamma1;
       g[n] = gamma2;
       a[n] = 0;
       a[0] = (-1) * k / Math.pow(STEP, 2);
       c[0] =(-1) * k / STEP;
       c[n] = (-1) * k / Math.pow(STEP, 2);
       double[] b  = new double[n + 1];
       b[0] = (-1) * (beta + k / STEP);
       b[n] = -1;
       for (int i = 1; i < n; i++) {
            b[i] = (-1)* (( 2 * k) / Math.pow(STEP, 2) + Function.getValueG((i + 1) * STEP));
            a[i] = (-1) * k / Math.pow(STEP, 2);
            c[i] = (-1) * k / Math.pow(STEP, 2);  
            g[i] = Function.getValueF2(i * STEP);
       }
       double[] y = getSolution(n, c, b, a, g);
        return y;
        
    }
    public double[] solveFictNode() {
       int n = Math.round((float) (this.l/STEP));
       double a[] = new double[n+1];
       double c[] = new double[n+1];
       double g[] = new double[n+1];
       g[0] = gamma1 + Function.getValueF2(0) * STEP / 2;
       g[n] = gamma2;
       a[n] = 0;
       a[0] = (-1) * k / Math.pow(STEP, 2);
       c[0] =(-1) * k / STEP;
       c[n] = (-1) * k / Math.pow(STEP, 2);
       double[] b  = new double[n + 1];
       b[0] = (-1) * (beta + k / STEP + STEP * Function.getValueG(0) /2);
       b[n] = -1;
       for (int i = 1; i < n; i++) {
            b[i] = (-1)* (( 2 * k) / Math.pow(STEP, 2) + Function.getValueG((i + 1) * STEP));
            a[i] = (-1) * k / Math.pow(STEP, 2);
            c[i] = (-1) * k / Math.pow(STEP, 2);  
            g[i] = Function.getValueF2(i * STEP);
       }
       double[] y = getSolution(n, c, b, a, g);
        return y;
        
    }

    private double[] getSolution(int n, double[] c, double[] b, double[] a, double[] g) {
        double[] s = new double[n + 1];
        double[] t = new double[n + 1];
        s[0] = c[0] /b[0];
        t[0] = (- 1) * g[0] / b[0];
        for (int i = 1; i < t.length; i++) {
            s[i] = c[i] / (b[i] - s[i-1] * a[i]);
            t[i] = ( t[i-1] * a[i] - g[i] ) / (b[i] - s[i-1] * a[i]); 
        }
        double[] y = new double[n + 1];
        y[n ] = t[n];
        for (int i = n - 1; i >= 0; i--) {
            y[i] = s[i] * y[i + 1] + t[i]; 
        }
        return y;
    }

    
    
}
