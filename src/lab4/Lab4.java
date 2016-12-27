/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;

import chart.DynamicChart;
import func.Function;
import static lab1.Tridiagonal.H;
import static lab3.ConvTransfer.R;


/**
 *
 * @author nastja
 */
public class Lab4 {
      //  Function f = new Function(1);
        Function f = new Function(2);
     //   ConvTransfer convTransfer = new ConvTransfer(2, 0.5, f, 4);
        ConvDifReact conv = new ConvDifReact(3, 1, f, 4);
        double[][] yCenImCr = conv.centralImplicitCrunk();
       double[][] yCen = conv.centralExplicit();
       int m = yCen.length;
       int n = yCen[0].length;
       double[][] yAnalit = new double[m][n];
        double x;
        double t;
        double[] xi = new double[n];
        
   /*     for (int j = 0; j < m; j++) {
            for (int k = 0; k < n; k++) {
                x = k * H;
                t = j * R;
                xi[k] = k * H;
                if ( x >= conv.a * t){
                    yAnalit[j][k] = 0;        
                }
                else {
                    yAnalit[j][k] = conv.a;
                }
            }
        }
       DynamicChart dc = new DynamicChart();
       dc.buildAndGo4(xi, yAnalit, yCenEx ,yCenImCr);
   */
}
