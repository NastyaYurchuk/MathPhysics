/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2;

import chart.XYChart;
import java.util.Arrays;

/**
 *
 * @author nastja
 */

public class f {
    
    public static double Function(double x){
        double func =0;
        func=0;
        //func=Math.exp(x);
        //func =-1*(x*x-3*x);
        //func=x*x*x-2*x*x-6*x+6;
        return func;
    }
    
    public static double KFunction(double x){
        double func =0;
        func =1;
        return func;
    }
    
    public static double VFunction(double x){
        double func =0;
        //func =4;
        func =20;
        return func;
    } 
    
    public static double QFunction(double x){
        double func =0;
        //func =1;
        func =21;
        return func;
    }
    
    public static double l =4;
    public static double mu0=2;
    public static double mu1=2;
    public static double h =0.2;
    
    public static double[][] AnalitSolving(int noit){
      
        double[][] ASolveXY = new double[2][noit+1];
        
        for(int i=0;i<noit+1;i++){
            ASolveXY[0][i]=i*h;
            ASolveXY[1][i] = Math.exp(ASolveXY[0][i])*(2-(2-2*Math.exp(l))/(Math.exp(-21*l)-Math.exp(l)))+Math.exp(-21*ASolveXY[0][i])*
                    (2-2*Math.exp(l))/(Math.exp(-21*l)-Math.exp(l));
        }
        return ASolveXY;
    }
    
    public static double[] sweepMethod(int n){
        int numOfIterations = (int)(l/h);
        double [] sweepMethodCoeficientA = new double [numOfIterations];
        double [] sweepMethodCoeficientB = new double [numOfIterations];
        double [] x = new double[numOfIterations+1];
        double vFuncMinus=0,vFuncPlus=0,reynaldsNum=0;
        double [] solveOfTheDifEq = new double [numOfIterations+1];
        sweepMethodCoeficientA[0] = 0;
        sweepMethodCoeficientB[0] = mu0;
        double b = VFunction(1*h)/(2*h) + (KFunction((1+0.5)*h))/(h*h);
        double c = QFunction(1*h)+(KFunction((1-0.5)*h) + KFunction((1+0.5)*h))/(h*h);
        double a = KFunction((1-0.5)*h)/(h*h)-VFunction(1*h)/(2*h);
        System.out.println("b " + b);
        System.out.println("c " + c);
        System.out.println("a " + a);
        if(n==1)
            for(int i = 1 ; i<numOfIterations;i++){
                sweepMethodCoeficientA[i] = (VFunction(i*h)/(2*h) + (KFunction((i+0.5)*h))/(h*h))/
                        (QFunction(i*h)+(KFunction((i-0.5)*h) + KFunction((i+0.5)*h))/(h*h)-
                        sweepMethodCoeficientA[i-1]*(KFunction((i-0.5)*h)/(h*h)-VFunction(i*h)/(2*h)));
                sweepMethodCoeficientB[i] = (Function(i*h)+sweepMethodCoeficientB[i-1]*(KFunction((i-0.5)*h)/(h*h)-VFunction(i*h)/(2*h)))/
                        (QFunction(i*h)+(KFunction((i-0.5)*h) + KFunction((i+0.5)*h))/(h*h)-
                        sweepMethodCoeficientA[i-1]*(KFunction((i-0.5)*h)/(h*h)-VFunction(i*h)/(2*h)));
            }
        if(n==2)
            for(int i = 1 ; i<numOfIterations;i++){
                vFuncMinus = (VFunction(i*h) - Math.abs(VFunction(i*h)))/2;
                vFuncPlus = (VFunction(i*h) + Math.abs(VFunction(i*h)))/2;
              
                sweepMethodCoeficientA[i] = -1*((KFunction((i+0.5)*h))/(h*h)+vFuncPlus*KFunction((i+0.5)*h)/(h*KFunction(i*h)))/
                        (sweepMethodCoeficientA[i-1]*((KFunction((i-0.5)*h))/(h*h) - (vFuncMinus*KFunction((i-0.5)*h))/(h*KFunction(i*h)))+
                        (vFuncMinus*KFunction((i-0.5)*h)/(h*KFunction(i*h)))-(vFuncPlus*KFunction((i+0.5)*h)/(h*KFunction(i*h)))-
                        (KFunction((i-0.5)*h) + KFunction((i+0.5)*h))/(h*h)-QFunction(i*h));
                sweepMethodCoeficientB[i] = (-1*Function(i*h)-sweepMethodCoeficientB[i-1]*((KFunction((i-0.5)*h))/(h*h) - vFuncMinus*KFunction((i-0.5)*h)/(h*KFunction(i*h))))/
                        (sweepMethodCoeficientA[i-1]*((KFunction((i-0.5)*h))/(h*h) - vFuncMinus*KFunction((i-0.5)*h)/(h*KFunction(i*h)))+
                        (vFuncMinus*KFunction((i-0.5)*h)/(h*KFunction(i*h)))-(vFuncPlus*KFunction((i+0.5)*h)/(h*KFunction(i*h)))-
                        (KFunction((i-0.5)*h) + KFunction((i+0.5)*h))/(h*h)-QFunction(i*h));
            }
        if(n==3)      
            for(int i = 1 ; i<numOfIterations;i++){
                reynaldsNum= 1/(1+ h*Math.abs(VFunction(i*h)));
                vFuncMinus = (VFunction(i*h) - Math.abs(VFunction(i*h)))/2;
                vFuncPlus = (VFunction(i*h) + Math.abs(VFunction(i*h)))/2;
                sweepMethodCoeficientA[i] = -1*(reynaldsNum*(KFunction((i+0.5)*h))/(h*h) +vFuncPlus*KFunction((i+0.5)*h)/(h*KFunction(i*h)))/
                        (sweepMethodCoeficientA[i-1]*(reynaldsNum*(KFunction((i-0.5)*h))/(h*h)- vFuncMinus*KFunction((i-0.5)*h)/(h*KFunction(i*h))) +
                        vFuncMinus*KFunction((i-0.5)*h)/(h*KFunction(i*h))-vFuncPlus*KFunction((i+0.5)*h)/(h*KFunction(i*h))-
                        reynaldsNum*(KFunction((i-0.5)*h) + KFunction((i+0.5)*h))/(h*h)-QFunction(i*h));
                sweepMethodCoeficientB[i] =(-1*Function(i*h) -sweepMethodCoeficientB[i-1]*(reynaldsNum*(KFunction((i-0.5)*h))/(h*h)- vFuncMinus*KFunction((i-0.5)*h)/(h*KFunction(i*h))))/
                        (sweepMethodCoeficientA[i-1]*(reynaldsNum*(KFunction((i-0.5)*h))/(h*h)- vFuncMinus*KFunction((i-0.5)*h)/(h*KFunction(i*h))) +
                        vFuncMinus*KFunction((i-0.5)*h)/(h*KFunction(i*h))-vFuncPlus*KFunction((i+0.5)*h)/(h*KFunction(i*h))-
                        reynaldsNum*(KFunction((i-0.5)*h) + KFunction((i+0.5)*h))/(h*h)-QFunction(i*h));
            }
//        for(int i = 1 ; i<numOfIterations;i++){
//            System.out.println(String.format("%.2f",i*h)  + "  " +String.format("%.3f",sweepMethodCoeficientA[i])+"     "+ String.format("%.2f",sweepMethodCoeficientB[i]) );
//        }
        System.out.println(n);
        System.out.println("s" + Arrays.toString(sweepMethodCoeficientA));
        System.out.println("t" + Arrays.toString(sweepMethodCoeficientB));
        x[numOfIterations] =l;
        solveOfTheDifEq[numOfIterations] =mu1;
        for(int i = numOfIterations-1 ;i>=0;i--){
            x[i]=i*h;
            solveOfTheDifEq[i] = sweepMethodCoeficientA[i]*solveOfTheDifEq[i+1] + sweepMethodCoeficientB[i];
        }     
       return solveOfTheDifEq;
    } 
   
  public static void main(String[] args) {
        int numOfIterations = (int)(l/h);
        double [] x = new double[numOfIterations+1];
        System.out.println("        sweep       Sam           Reyn          Analyth");
   //     XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("X").yAxisTitle("Y").build(); 
        double[] Aproximat8 =sweepMethod(1);
        double[] Aproximat13 =sweepMethod(2);
        double[] Aproximat16 =sweepMethod(3);
        double[][] an = AnalitSolving(numOfIterations);
        for(int i = 0 ; i<(int)(l/h)+1;i++){ 
            System.out.println(String.format("%.2f",i*h)  + "  " +String.format("%.10f",Aproximat8[i])+" "+String.format("%.10f",Aproximat13[i])
            + "  " +String.format("%.10f",Aproximat16[i]) +"   "+String.format("%.10f",an[1][i]));
        } 
        for(int i = numOfIterations ;i>=0;i--){
             x[i]=i*h;
        }
       // chart.addSeries("Scheme 8", x, Aproximat8);
       // chart.addSeries("Scheme 13", x, Aproximat13);
        //chart.addSeries("Scheme 16", x, Aproximat16);
        //chart.addSeries("AnalytSolutin", an[0], an[1]);
        //new SwingWrapper(chart).displayChart();
    }
}

