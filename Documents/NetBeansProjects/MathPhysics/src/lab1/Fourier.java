/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;

import java.util.Arrays;

/**
 *
 * @author nastja
 */
public class Fourier {
    
    public static final double STEP = 0.2;
    public double l;

    public Fourier(double l) {
        this.l = l;
    }
    
    public double[] solve (){
        int n = Math.round((float) (this.l/STEP));
        System.out.println("n = " + n);
        int k = n - 1;
        double[] eigenfunc = new double[k];
        double[] coefFourier = new double[k];
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                   coefFourier[i] += Function.getValueF((j + 1) * STEP ) * eigenFunc(j, i) * STEP;
                   
            }
            
        }
        
        double[] yi = new double[n + 1];
        
       yi[0] = 0;
       yi[n] = 0;
        for (int i = 1; i < n ; i++) {
            for (int j = 0; j < k; j++) {
            //    System.out.println(j + ". ck" + (coefFourier[j] / eigenValue(j)) );
                yi[i] += eigenFunc((i - 1) , j) * coefFourier[j] / eigenValue(j);
                
            }
        //   System.out.println(i + ". yi" + yi[i]);
        }
        
        return yi;
    }
        
    
    private double eigenFunc(int i, int k){
        ++i;
        ++k;
        return  Math.sqrt(2 / this.l) * Math.sin((Math.PI * k * (i * STEP)) / this.l );
    }
    
    private double eigenValue (int k){
        ++k;
        return 4 * Math.pow(Math.sin((Math.PI * k * STEP)/ (2 * this.l)),2) / Math.pow(STEP, 2);
    }
}
    

