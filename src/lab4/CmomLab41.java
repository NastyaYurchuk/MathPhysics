/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab4;


import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.knowm.xchart.SwingWrapper;
//import org.knowm.xchart.XYChart;
//import org.knowm.xchart.XYChartBuilder;
public class CmomLab41 {
     public static double l =4;
    public static double h=0.1;
    public static double tau=0.05;
    //public static double a=1;
    public static double T=1;
    public static double initialCondition(double x){
         return x;
    }
    public static double boundaryCondition(double t){
         return 0;
    }
    public static double boundaryConditionRight(double t){
         return 4;
    }
    public static double muFunction(double t, double x){
         return 0;
    }
    public static double vFunction(double t, double x){
         return 1;
    }
    public static double kFunction(double t, double x){
         return 0;
    }
    public static double fFunction(double t, double x){
         return 0;
    }
    
    public static double[][] centralScheme(int tr ){
        int numOfIterations = (int)(l/h);
        int numOfTime = (int)(T/tau);
        int step = 0;
        double[][] Y = new double[numOfTime+1][numOfIterations+1];
        for(int j = 0 ; j<=numOfIterations;j++){
                Y[0][j]= initialCondition(j*h);
            }
        for(int i = 1 ; i<=numOfTime;i++){
            Y[i][0]=boundaryCondition(i*tau);
            Y[i][numOfIterations]=boundaryConditionRight(i*tau);
            for(int j = 1 ; j<numOfIterations;j++){
                Y[i][j]=(Y[i-1][j]*(1/tau-2*muFunction((i-1)*tau, j*h )/(h*h)-kFunction((i-1)*tau, j*h )) 
                        -Y[i-1][j+1]*(vFunction((i-1)*tau,j*h )/(2*h)-muFunction((i-1)*tau,j*h)/(h*h))
                        +Y[i-1][j-1]*(vFunction((i-1)*tau,j*h)/(2*h)+muFunction((i-1)*tau,j*h)/(h*h))
                        +fFunction( (i-1)*tau,j*h))*tau;
            } 
        }
        System.out.println(" centralScheme");
        for(int i = 0 ; i<=numOfTime;i++){
            for(int j = 0 ; j<=numOfIterations;j++){
                System.out.print(String.format("%.3f",Y[i][j]) + "  ");
            }  
            System.out.println(" ");
        }
        return Y; 
    } 
     public static double[] sweepMethod(int n,double[][] Y,int stepTime){
        int numOfIterations = (int)(l/h);
        double [] sweepMethodCoeficientA = new double [numOfIterations+1];
        double [] sweepMethodCoeficientB = new double [numOfIterations+1];
        double [] x = new double[numOfIterations+1]; 
        double [] solveOfTheDifEq = new double [numOfIterations+1]; 
       
            sweepMethodCoeficientA[1] =  -1*(vFunction(3*stepTime*tau/2, h)/(4*h) - muFunction(3*stepTime*tau/2, h)/(2*h*h))/(2*h)
                    /(1/tau + muFunction(3*stepTime*tau/2, h)/(h*h)+kFunction(3*stepTime*tau/2, h));
            sweepMethodCoeficientB[1] = (fFunction(3*stepTime*tau/2, h)+Y[stepTime][1]/tau - vFunction(3*stepTime*tau/2, h)*(Y[stepTime][2]-Y[stepTime][0])/(4*h) 
                        + muFunction(3*stepTime*tau/2, h)*(Y[stepTime][2]-2*Y[stepTime][1]+Y[stepTime][0])/(2*h*h) -  kFunction(3*stepTime*tau/2, h)*Y[stepTime][1])
                        /(1/tau + muFunction(3*stepTime*tau/2, h)/(h*h)+kFunction(3*stepTime*tau/2, h));
            for(int i = 2 ; i<numOfIterations;i++){
                sweepMethodCoeficientA[i] =  -1*(vFunction(3*stepTime*tau/2, i*h)/(4*h) - muFunction(3*stepTime*tau/2, i*h)/(2*h*h))
                        /((-1*vFunction(3*stepTime*tau/2, i*h)/(4*h) - muFunction(3*stepTime*tau/2, i*h)/(2*h*h))*sweepMethodCoeficientA[i-1] + 1/tau + muFunction(3*stepTime*tau/2, i*h)/(h*h)+kFunction(3*stepTime*tau/2, i*h)); 
                sweepMethodCoeficientB[i] = (fFunction(3*stepTime*tau/2, i*h)+Y[stepTime][i]/tau - vFunction(3*stepTime*tau/2, i*h)*(Y[stepTime][i+1]-Y[stepTime][i-1])/(4*h) 
                        + muFunction(3*stepTime*tau/2, i*h)*(Y[stepTime][i+1]-2*Y[stepTime][i]+Y[stepTime][i-1])/(2*h*h) -  kFunction(3*stepTime*tau/2, i*h)*Y[stepTime][i]-((-1*vFunction(3*stepTime*tau/2, i*h)/(4*h) - muFunction(3*stepTime*tau/2, i*h)/(2*h*h))*sweepMethodCoeficientB[i-1]) )
                        /((-1*vFunction(3*stepTime*tau/2, i*h)/(4*h) - muFunction(3*stepTime*tau/2, i*h)/(2*h*h))*sweepMethodCoeficientA[i-1] + 1/tau + muFunction(3*stepTime*tau/2, i*h)/(h*h)+kFunction(3*stepTime*tau/2, i*h));
         
            }

        x[numOfIterations] =l;
        solveOfTheDifEq[numOfIterations]  = boundaryConditionRight((stepTime+1)*tau);
        for(int i = numOfIterations-1 ;i>=0;i--){
            x[i]=i*h;
            solveOfTheDifEq[i] = sweepMethodCoeficientA[i]*solveOfTheDifEq[i+1] + sweepMethodCoeficientB[i];
        }
       return solveOfTheDifEq;
    } 
      public static double[][] krankNikolsonScheme(int tr ){
        int numOfIterations = (int)(l/h);
        int numOfTime = (int)(T/tau);
        double[][] Y = new double[numOfTime+1][numOfIterations+1];
        for(int j = 0 ; j<=numOfIterations;j++){
            Y[0][j]= initialCondition(j*h);
        }
        for(int i = 1 ; i<=numOfTime;i++){
            Y[i][0]=boundaryCondition(i*tau);
            Y[i][numOfIterations]=boundaryConditionRight(i*tau);
            Y[i] = sweepMethod(2,Y,i-1);
            Y[i][0]=boundaryCondition(i*tau);
        }
        System.out.println("krankNikolsonScheme ");
        for(int i = 0 ; i<=numOfTime;i++){
            for(int j = 0 ; j<=numOfIterations;j++){
                System.out.print(String.format("%.3f",Y[i][j]) + "  ");
            }  
            System.out.println(" ");
        }
        return Y;
    } 
    public static void main(String[] args) {
         int numOfIterations = (int)(l/h);
        int numOfTime = (int)(T/tau);
        double[] x = new double[numOfIterations+1];
        double[] Nulll = new double[numOfIterations+1];
        double[][] Y1 = new double[numOfTime+1][numOfIterations+1];
        double[][] Y2 = new double[numOfTime+1][numOfIterations+1];
        double[][] AnSolv = new double[numOfTime+1][numOfIterations+1];
        Y1 = centralScheme(3);
        Y2 = krankNikolsonScheme(3);
        //AnSolv = AnalitSolving(3);
        for(int i = numOfIterations ;i>=0;i--){
            x[i]=i*h; 
        }
      /*  XYChart chart = new XYChartBuilder().width(800).height(600).xAxisTitle("X").yAxisTitle("Y").build();
        chart.addSeries("Scheme 4", x, Y1[1]);
        chart.addSeries("krankNikolsonScheme", x, Y2[1]);
        chart.addSeries("AnalitSolving", x, AnSolv[1]); 
        SwingWrapper sw = new SwingWrapper(chart); 
        sw.displayChart();
        for(int i = 2 ; i<=numOfTime;i++){ 
            try {
                System.in.read();
            } catch (IOException ex) {
                Logger.getLogger(CmomLab3.class.getName()).log(Level.SEVERE, null, ex);
            }
            chart.updateXYSeries("Scheme 4", x, Y1[i],Nulll);
            chart.updateXYSeries("krankNikolsonScheme", x, Y2[i],Nulll);
            chart.updateXYSeries("AnalitSolving", x, AnSolv[i],Nulll); 
            sw.repaintChart();
            
            new SwingWrapper(chart).displayChart();
        } */
    }
}
