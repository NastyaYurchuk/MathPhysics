/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import chart.DynamicChart;
import func.Function;
import java.util.Arrays;
import static lab3.ConvTransfer.H;
import static lab3.ConvTransfer.R;

/**
 *
 * @author okurylo
 */
public class Lab3 {
    public static void main(String[] args) {
        Function f = new Function(1);
        ConvTransfer convTransfer = new ConvTransfer(2, 1, f, 4);
        double[][] yEx = convTransfer.rightExplicit();
        double[][] yIm = convTransfer.rightImplicit();
        double[][] yCenEx = convTransfer.centralExplicit();
        int m = yEx.length;
        int n = yEx[0].length;
        System.out.println("n = " + n + " m = " + m);
        double[][] yAnalit = new double[m][n];
        double x;
        double t;
        double[] xi = new double[n];
        for (int j = 0; j < m; j++) {
            for (int k = 0; k < n; k++) {
                x = k * H;
                t = j * R;
                xi[k] = k * H;
               // System.out.println("t " + t);
                if ( x >= convTransfer.a * t){
                   // System.out.println("x >= at " + (x));
                    yAnalit[j][k] = f.getGi(x - convTransfer.a * t);
                   
                }
                else {
                    //System.out.println("x < at " + x);
                    yAnalit[j][k] = f.getMui(t - x / convTransfer.a);
                }
               
                 System.out.println(yAnalit[j][k]);
                 
               // System.out.println(yAnalit[k][j]);
            }
            //System.out.println("");
        //    System.out.println("j" + yAnalit[][0]);
        System.out.println( "yAnalit " + Arrays.toString(yAnalit[j]));  
            System.out.println("yEx " + Arrays.toString(yEx[j]));   
              System.out.println("yIm " + Arrays.toString(yIm[j]));
              System.out.println("yCenEx " + Arrays.toString(yCenEx[j])); 
        }
       // System.out.println( "yAnalit " + Arrays.toString(yAnalit[0]));  
        DynamicChart dc = new DynamicChart();
        dc.buildAndGo(xi, yAnalit);
    }
}
