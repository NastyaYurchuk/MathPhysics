/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab5;

import func.Function;

/**
 *
 * @author okurylo
 */
public class ParabolEquat {
     public static final double R = 0.01;
   
   public double l1;
   public double l2;
   public Function f;
   public double t;
   public double v;
   public double a;
 public static final double Hx = 0.01;
public static final double Hy = 0.01;
    public ParabolEquat(double l1, Function f, double t, double l2, double a) {
        this.l1 = l1;
        this.l2 = l2;
        this.f = f;
        this.t = t;
       // this.v = v;
        this.a = a;
    }
   
    public double[][][] implicitTwoDim(){
        int N1 =  Math.round((float) (this.l1/Hx)) + 1;
        int N2 =  Math.round((float) (this.l2/Hy)) + 1;
        int M = Math.round((float) (this.t/R)) + 1;
        double [][][] y = new double[M][N1][N2];

        for (int m = 0; m < N1; m++) {
            for (int n = 0; n < N2; n++) {
                y[0][m][n] = f.getGi(m * Hx, n * Hy);
                
            }         
        }
        for (int j = 0; j < 10; j++) {
            for (int n = 0; n < N2 - 1; n++) {
                for (int m = 0; m < N1 - 1; m++) {              
                   
               
                }
            }
        }
      return y;
    }
}
