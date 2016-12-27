/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import func.Function;
import java.util.Arrays;


/**
 *
 * @author nastja
 */
public class Tridiagonal {
    
    public static final double H = 0.05;
    public double l;
    public double k;
    public double beta;
    public double gamma1;
    public double gamma2;
 //   public Function kF;
 //   public Function vF;
//    public Function qF;
    public Function f;

    public Tridiagonal() {
    }

    public Tridiagonal(double l, double k, double beta, double gamma1, double gamma2) {
        this.k = k;
        this.beta = beta;
        this.gamma1 = gamma1;
        this.gamma2 = gamma2;
        this.l = l;
    }
    
    public Tridiagonal(Function fF, double l,  double gamma1, double gamma2 ){
        this.f = fF;
        this.gamma1 = gamma1;
        this.gamma2 = gamma2;
        this.l = l;
    }
     public double[] solveDiffusionEqFirst(){
        int n = Math.round((float) (this.l/H));
       // double y[] = new double[n + 1];
        double a[] = new double[n+1];
        double c[] = new double[n+1];
        double g[] = new double[n+1];
        double[] b  = new double[n + 1];
        g[0] = this.gamma1;;
        g[n] = this.gamma2;
        
        a[0] = 0;
      //  a[n] = 1;
        b[0] = - 1;
        c[0] = 0;
        b[n]= - this.gamma2;
        //c[n] = this.gamma2;
         for (int i = 1; i < n + 1; i++) {
            b[i] = (this.f.getQi(i * H) + (this.f.getKi(i - H * 0.5) + this.f.getKi(i + H * 0.5) ) / (Math.pow(H, 2)));
            a[i] = ((-1) * this.f.getVi(i * H) / (2 * H) + this.f.getKi(i - H * 0.5)/ (Math.pow(H, 2)));
            c[i] = (this.f.getVi(i * H) / (2 * H) + this.f.getKi(i +  H * 0.5)/ (Math.pow(H, 2)));  
            g[i] = this.f.getFi(i * H);
       } 

        double[] y = getSolution(n, c, b, a, g);
        return y;
        
    }
     
    public double[] solveDiffusionEqSamars(){
        int n = Math.round((float) (this.l/H));
       // double y[] = new double[n + 1];
        double a[] = new double[n+1];
        double c[] = new double[n+1];
        double g[] = new double[n+1];
        double[] b  = new double[n + 1];
        g[0] = this.gamma1;;
        g[n] = this.gamma2;
        
        a[0] = 0;
      //  a[n] = 1;
        b[0] = - 1;
        c[0] = 0;
        b[n]= - this.gamma2;
        //c[n] = this.gamma2;
         for (int i = 1; i < n + 1; i++) {
            b[i] = (f.getKi((i - 0.5) * H)*(- f.getKi(i * H) + f.getViMin(i * H) * H ) 
                    - f.getKi((i - 0.5) * H)*( f.getKi(i * H) + f.getViPlus(i * H) * H ))
                    / (H * H * f.getKi(i * H)) - f.getQi(i * H);  
            a[i] = (-1) * (f.getKi((i - 0.5) * H)*(f.getKi(i * H)  - f.getViMin(i * H) * H)) / (H * H * f.getKi(i * H));   ;
            c[i] = (-1) * (f.getKi((i + 0.5) * H)*(f.getKi(i * H)  + f.getViPlus(i * H) * H)) / (H * H * f.getKi(i * H));  
            g[i] = f.getFi(i * H);
       } 
        
         System.out.println("q " + this.f.getQi(1 * H));
         System.out.println("f" + this.f.getFi(1 * H));
         System.out.println("k" + this.f.getKi(1 * H));
         System.out.println("v" + this.f.getVi(1 * H));
         System.out.println("getValueViMin"+ f.getViMin(1 * H));
         System.out.println("getValueViPlus"+ f.getViPlus(1 * H));
         System.out.println("a " + Arrays.toString(a));
         System.out.println("b " + Arrays.toString(b));
         System.out.println("c " + Arrays.toString(c));
        double[] y = getSolution(n, c, b, a, g);
        return y;
        
    }
    
     public double[] solveDiffusionRein(){
        int n = Math.round((float) (this.l/H));
       // double y[] = new double[n + 1];
        double a[] = new double[n+1];
        double c[] = new double[n+1];
        double g[] = new double[n+1];
        double[] b  = new double[n + 1];
        g[0] = this.gamma1;;
        g[n] = this.gamma2;
        
        a[0] = 0;
      //  a[n] = 1;
        b[0] = - 1;
        c[0] = 0;
        b[n]= - this.gamma2;
        //c[n] = this.gamma2;
         for (int i = 1; i < n + 1; i++) {
            b[i] = ((f.getViMin(i * H) * f.getKi((i - 0.5) * H)/(H * f.getKi(i * H)) - f.getViPlus(i * H) * f.getKi(i) )/(H * f.getKi(i * H))
                    - ((f.getKi((i + 0.5) * H) + f.getKi((i - 0.5) * H))/( H * H * (1 + f.getRi(i  * H)))) 
                    - f.getQi(i * H));  
            a[i] = (-1) *( (f.getKi(( i - 0.5) * H)/( H * H * (1 + f.getRi(i  * H)) )) -
                    f.getViMin(i * H) * f.getKi(( i - 0.5) * H)/((f.getKi(i * H)) * H));   
            c[i] = (-1) * (f.getKi(( i + 0.5) * H)/ H) *
                    (1 / (H * (1 + f.getRi(i  * H))) + f.getViPlus(i * H)/f.getKi(i * H));  
            g[i] = f.getFi(i * H);
       } 
        
      double[] y = getSolution(n, c, b, a, g);
        return y;
        
    }
    
    
    public double[] solve() {
       int n = Math.round((float) (this.l/H));
       double a[] = new double[n+1];
       double c[] = new double[n+1];
       double g[] = new double[n+1];
       g[0] = gamma1;
       g[n] = gamma2;
       a[n] = 0;
       a[0] = (-1) * k / Math.pow(H, 2);
       c[0] =(-1) * k / H;
       c[n] = (-1) * k / Math.pow(H, 2);
       double[] b  = new double[n + 1];
       b[0] = (-1) * (beta + k / H);
       b[n] = -1;
       for (int i = 1; i < n; i++) {
            b[i] = (-1)* (( 2 * k) / Math.pow(H, 2) + Function.getValueG((i + 1) * H));
            a[i] = (-1) * k / Math.pow(H, 2);
            c[i] = (-1) * k / Math.pow(H, 2);  
            g[i] = Function.getValueF2(i * H);
       }
       double[] y = getSolution(n, c, b, a, g);
        return y;
        
    }
    public double[] solveFictNode() {
       int n = Math.round((float) (this.l/H));
       double a[] = new double[n+1];
       double c[] = new double[n+1];
       double g[] = new double[n+1];
       g[0] = gamma1 + Function.getValueF2(0) * H / 2;
       g[n] = gamma2;
       a[n] = 0;
       a[0] = (-1) * k / Math.pow(H, 2);
       c[0] =(-1) * k / H;
       c[n] = (-1) * k / Math.pow(H, 2);
       double[] b  = new double[n + 1];
       b[0] = (-1) * (beta + k / H + H * Function.getValueG(0) /2);
       b[n] = -1;
       for (int i = 1; i < n; i++) {
            b[i] = (-1)* (( 2 * k) / Math.pow(H, 2) + Function.getValueG((i + 1) * H));
            a[i] = (-1) * k / Math.pow(H, 2);
            c[i] = (-1) * k / Math.pow(H, 2);  
            g[i] = Function.getValueF2(i * H);
       }
       System.out.println("a " + Arrays.toString(a));
         System.out.println("b " + Arrays.toString(b));
         System.out.println("c " + Arrays.toString(c));
       double[] y = getSolution(n, c, b, a, g);
        return y;
        
    }

    public double[] getSolution(int n, double[] c, double[] b, double[] a, double[] g) {
        double[] s = new double[n + 1];
        double[] t = new double[n + 1];
        s[0] = c[0]/ b[0];
        t[0] = (- 1) * g[0] / b[0];
        
        for (int i = 1; i < t.length; i++) {
            s[i] = c[i] / (b[i] - s[i-1] * a[i]);
            t[i] = ( t[i-1] * a[i] - g[i] ) / (b[i] - s[i-1] * a[i]); 
        }
       // t[n] = this.gamma2;//for 8
        System.out.println("s " + Arrays.toString(s));
        System.out.println("t " + Arrays.toString(t));
        double[] y = new double[n + 1];
        y[n ] = t[n];
        for (int i = n - 1; i >= 0; i--) {
            y[i] = s[i] * y[i + 1] + t[i]; 
        }
        System.out.println("y " + Arrays.toString(y));
        return y;
    }

    
    
}
