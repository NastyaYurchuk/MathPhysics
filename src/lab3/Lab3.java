/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import chart.DynamicChart;
import func.Function;
import java.util.Arrays;
import static lab1.Tridiagonal.H;
import static lab3.ConvTransfer.R;

/**
 *
 * @author okurylo
 */
public class Lab3 {
    public static void main(String[] args) {
      //  Function f = new Function(1);
        Function f = new Function(2);
     //   ConvTransfer convTransfer = new ConvTransfer(2, 0.5, f, 4);
        ConvTransfer convTransfer = new ConvTransfer(3, 1, f, 4);
        double[][] yEx = convTransfer.rightExplicit();
        double[][] yIm = convTransfer.rightImplicit();
        double[][] yCenEx = convTransfer.centralExplicit();
        double[][] yCenIm = convTransfer.centralImplicit();
        double[][] yCenImCr = convTransfer.centralImplicitCrunk();
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
                if ( x >= convTransfer.a * t){
                    yAnalit[j][k] = f.getGi(x - convTransfer.a * t);        
                }
                else {
                    yAnalit[j][k] = f.getMui(t - x / convTransfer.a);
                }
            }
        System.out.println( "yAnalit " + Arrays.toString(yAnalit[j]));  
        System.out.println("yEx " + Arrays.toString(yEx[j]));   
        System.out.println("yIm " + Arrays.toString(yIm[j]));
        System.out.println("yCenIm " + Arrays.toString(yCenIm[j]));
        System.out.println("yCenImCr " + Arrays.toString(yCenImCr[j])); 
            System.out.println("");
        }
         System.out.println("x    Analytical  Right Explicit  Right Implicit  Central Impicit Central Impicit Crunk   Central Explicit");
        for (int i = 0; i < n; i++) {
          // number = xFormat.format(x[i]);
         //  System.out.println(number +"       " +yFormat.format(yAnalit1[i]) + "      " +
           //        yFormat.format(ySample[i]) + "      " + yFormat.format(ySamars[i]) + "     " + yFormat.format(yReynolds[i]));
           System.out.println(String.format("%.2f",xi[i])  + "    " +String.format("%.10f",yAnalit[3][i])+"   "+String.format("%.10f",yEx[3][i])
            + "    " +String.format("%.10f",yIm[3][i]) +"     "+String.format("%.10f",yCenIm[3][i])  +"     "+String.format("%.10f",yCenImCr[3][i])
           +"    "+String.format("%.10f",yCenEx[3][i]));
       //         System.out.println(number +"       " +yFormat.format(yAnalit[i]) + "      " + yFormat.format(yFourier[i]));
        }
       // System.out.println( "yAnalit " + Arrays.toString(yAnalit[0]));  
        DynamicChart dc = new DynamicChart();
        dc.buildAndGo(xi, yAnalit, yEx, yIm, yCenEx, yCenIm, yCenImCr);
    }
}
