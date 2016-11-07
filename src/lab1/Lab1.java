/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import chart.DualAxis;
import chart.XYChart;
import java.text.DecimalFormat;
import java.util.Arrays;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author nastja
 */
public class Lab1 {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         Fourier fourier = new Fourier(2);
       System.out.println("begin");
      /*double[] y =  fourier.solve();
        System.out.println("end");
        System.out.println(Arrays.toString(y));*/
        double h = 0.1;
        double[] yAnalit = new double [Math.round((float) (2 / h)) + 1];
        double[] x  = new double[Math.round((float) (2 / h)) + 1];
        double[] yFourier = fourier.solve();
        for (int i = 0; i < yAnalit.length; i++) {
            x[i] = i * h;
            yAnalit[i] = Function.solveSecond1(x[i]); 
            //yAnalit[i] = Function.solveFirst(x[i]);
        }
      
       // Tridiagonal tridiagonal = new Tridiagonal(1, 1, 1, 1, 2);
        Tridiagonal tridiagonal = new Tridiagonal(2, 1, 1, 1, 1);
        
        double[] y1 = tridiagonal.solve();
        
       double[] y2 = tridiagonal.solveFictNode();
       System.out.println(Arrays.toString(yAnalit));
        System.out.println(Arrays.toString(y1));
        System.out.println(Arrays.toString(y2));
        DecimalFormat  xFormat = new DecimalFormat("0.##");
        DecimalFormat  yFormat = new DecimalFormat("0.######");
      String  number;

        System.out.println("x       Analytical    Numerical    Numerical with approximation ");
        for (int i = 0; i < yAnalit.length; i++) {
           number = xFormat.format(x[i]);
            System.out.println(number +"       " +yFormat.format(yAnalit[i]) + "      " + yFormat.format(y1[i]) + "      " + yFormat.format(y2[i]));
            
        }
      //  System.out.println(Arrays.toString(y2));
       final XYChart demo = new XYChart("Dual","",x, yAnalit, y1, y2);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
