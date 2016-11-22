/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import lab1.Function;
import lab1.Tridiagonal;
import static lab1.Tridiagonal.STEP;

/**
 *
 * @author nastja
 */
public class Lab2 {
    
    public static void main(String[] args) {
         Function f1 = new Function(1);
         Function f2 = new Function(2);
        Tridiagonal tridiagonal = new Tridiagonal(new Function(1), 4, 2, 2);
       //  Tridiagonal tridiagonal = new Tridiagonal(new Function(2), 2, 0, 1);
        // tridiagonal.solveDiffusionEqFirst();
        // tridiagonal.solveDiffusionEqSamars();
         tridiagonal.solveDiffusionRein();
         
         double[] yAnalit = new double [Math.round((float) (2 / STEP)) + 1];
        double[] x  = new double[Math.round((float) (2 / STEP)) + 1];
        for (int i = 0; i < yAnalit.length; i++) {
            x[i] = i * STEP;
            yAnalit[i] = Function.solveSecond1(x[i]); 
           // yAnalit[i] = Function.solveFirst(x[i]);
        }
    }
}
