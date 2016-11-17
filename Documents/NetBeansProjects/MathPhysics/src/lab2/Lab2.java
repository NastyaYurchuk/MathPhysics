/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import lab1.Function;
import lab1.Tridiagonal;

/**
 *
 * @author nastja
 */
public class Lab2 {
    
    public static void main(String[] args) {
         
        Tridiagonal tridiagonal = new Tridiagonal(new Function(1), 4, 2, 2);
       //  Tridiagonal tridiagonal = new Tridiagonal(new Function(2), 2, 0, 1);
         tridiagonal.solveDiffusionEqFirst();
    }
}
