/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

/**
 *
 * @author nastja
 */
public abstract class DifEquation {
    public static final double STEP = 0.2;
    public double l;
    
    public abstract double[] solve (Function f);
}
