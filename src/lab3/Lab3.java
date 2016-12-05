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
        double[][] y = convTransfer.rightExplicit();
        
        int n = y.length;
        int m = y[0].length;
        double[][] yAnalit = new double[n][m];
        double x;
        double t;
        double[] xi = new double[n];
        for (int j = 0; j < yAnalit.length; j++) {
            for (int k = 0; k < yAnalit.length; k++) {
                x = k * H;
                t = j * R;
                xi[k] = k * H;
                if ( x >= convTransfer.a * t){
                    System.out.println("x >= at");
                    yAnalit[k][j] = f.getGi(x - convTransfer.a * t);
                   
                }
                else {
                    System.out.println("x < at " + (t - x / convTransfer.a));
                    yAnalit[k][j] = f.getMui(t - x / convTransfer.a);
                }
                 System.out.println(yAnalit[k][j]);
               // System.out.println(yAnalit[k][j]);
            }
            //System.out.println("");
            
        }
        System.out.println(Arrays.toString(yAnalit[0]));  
        DynamicChart dc = new DynamicChart();
        dc.build("Lab3", xi, yAnalit);
    }
}
