/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import chart.XYChart;
import java.text.DecimalFormat;
import func.Function;
import lab1.Tridiagonal;
import static lab1.Tridiagonal.H;
import org.jfree.ui.RefineryUtilities;

/**
 *
 * @author nastja
 */
public class Lab2 {
    
    public static void main(String[] args) {
       //  Function f1 = new Function(1);
       // Function f1 = new Function(2);
        Function f1 = new Function(3);
     //   double l = 2;
      double l = 3;
         
         double[] yAnalit1 = new double [Math.round((float) (l / H)) + 1];
     //    double[] yAnalit2 = new double [Math.round((float) (2 / H)) + 1];
        double[] x  = new double[Math.round((float) (l / H)) + 1];
        for (int i = 0; i < yAnalit1.length; i++) {
            x[i] = i * H;
            yAnalit1[i] = f1.getAnalytic(x[i]); 
          //  yAnalit2[i] = f2.getAnalytic(x[i]); 
           // yAnalit[i] = Function.solveFirst(x[i]);
        }
        
         //Tridiagonal tridiagonal1 = new Tridiagonal(new Function(1), 4, 2, 2);
     //  Tridiagonal tridiagonal1 = new Tridiagonal(f1, 2, 3, Math.exp(2));
      Tridiagonal tridiagonal1 = new Tridiagonal(f1, 3, 3, 3);
         
        double[] ySample = tridiagonal1.solveDiffusionEqFirst();
        double[] ySamars =  tridiagonal1.solveDiffusionEqSamars();
        double[] yReynolds = tridiagonal1.solveDiffusionRein();
         /*
         Tridiagonal tridiagonal2 = new Tridiagonal(new Function(2), 2, 0, 1);
         tridiagonal2.solveDiffusionEqFirst();
         tridiagonal2.solveDiffusionEqSamars();
         tridiagonal2.solveDiffusionRein();*/
         
        DecimalFormat  xFormat = new DecimalFormat("0.##");
        DecimalFormat  yFormat = new DecimalFormat("0.######");
        String  number;
        System.out.println("x       Analytical    Sample    Samarskiy   Reynolds ");
        for (int i = 0; i < yAnalit1.length; i++) {
           number = xFormat.format(x[i]);
         //  System.out.println(number +"       " +yFormat.format(yAnalit1[i]) + "      " +
           //        yFormat.format(ySample[i]) + "      " + yFormat.format(ySamars[i]) + "     " + yFormat.format(yReynolds[i]));
           System.out.println(String.format("%.2f",x[i])  + "  " +String.format("%.10f",yAnalit1[i])+" "+String.format("%.10f",ySample[i])
            + "  " +String.format("%.10f",ySamars[i]) +"   "+String.format("%.10f",yReynolds[i]));
       //         System.out.println(number +"       " +yFormat.format(yAnalit[i]) + "      " + yFormat.format(yFourier[i]));
        }
      //  System.out.println(Arrays.toString(y2));
       final XYChart demo = new XYChart("Dual","",x, yAnalit1, ySample, ySamars, yReynolds) ;
      //   final XYChart demo = new XYChart("Dual","",x, yAnalit, yFourier);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
