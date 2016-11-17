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
public class laba2 {

    private static double h = 0.1;
            
    private static int l = 2;
    private static double m0 = 0;
     private static double m1 = 1;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        double  N = l / h; 
        
        double[] x = new double [(int)N+1];
      
        for (int i=0; i<N+1; i++){
            x[i] = (i-1)*h;
        }
       
        double [] u = new double [(int)N+1];
        
        for (int i=0; i<N+1; i++){
            u[i] = u(x[i]);
            //System.out.println(u[i] + "||"+y[i]);
        }
        scheme8(l,h);
  /*       XYChart chart = QuickChart.getChart("Графіки", "X", "Y", "аналітичний", x, u);
        chart.addSeries("схема 8", x, scheme8(l,h));
        chart.addSeries("схема 13", x, scheme13(l,h));
        chart.addSeries("схема 16", x, scheme16(l,h));
        /*chart.getStyler().setXAxisMin(0.1);
        chart.getStyler().setXAxisMax(0.2);*/
         
      //  new SwingWrapper(chart).displayChart();
       
    }
    
    public static double [] scheme16(int l, double h)
    {
         double  N = l / h; 
     
       double[] v1 = new double[(int)N+1];
       double[] v2 = new double[(int)N+1];
       double[] bi = new double[(int)N+1];
       double[] di = new double[(int)N+1];
       double[] ci = new double[(int)N+1];
       double[] ai = new double[(int)N+1];
       double[] ri = new double[(int)N+1];
       double[] x = new double[(int)N+1];
               
       for  (int i=1; i<N; i++){
           x[i] = (i-1)*h;
           di[i] = -f(x[i]);
       }
       
        for  (int i=2; i<N+1; i++)
        {
            
            v1[i] = (v(x[i]) - Math.abs(v(x[i])))/2;
            v2[i] = (v(x[i]) + Math.abs(v(x[i])))/2;
                     
            ri[i]= h * Math.abs(v(x[i]))/(2*k(x[i]));
            
            bi[i] = q(x[i]) + (((k((x[i])-(h/2)) +k((x[i])+(h/2)))/Math.pow(h, 2))/(1+ri[i])) +
                    ((v2[i]*k((x[i])+(h/2))) - (v1[i]*k((x[i])-(h/2))))/(k(x[i])*h);
            
            ci[i] = - k((x[i])+(h/2))*(1/h/(1+ri[i]) + v2[i]/k(x[i]))/h;    
           
        }
        
       bi[1] = 1;
       bi[(int)N] = 1;   
       
       di[1] = m0;
       di[(int)N] = m1;
        
       for (int i=2; i<N; i++)
        {
            ai[i] = k((x[i])- h/2) * (v1[i]/k(x[i]) - 1/h/(1+ri[i]))/h;
        }
       
      return progonka(ai,ci,bi,di);
       
    }
    
    public static double[] scheme13(int l, double h)
    {
        
       double  N = l / h; 
                
       double[] v1 = new double[(int)N+1];
       double[] v2 = new double[(int)N+1];
       double[] k_i = new double[(int)N+1];
       double[] bi = new double[(int)N+1];
       double[] di = new double[(int)N+1];
       double[] ci = new double[(int)N+1];
       double[] ai = new double[(int)N+1];
       double[] x = new double[(int)N+1];
       
        for  (int i=1; i<N; i++)
       {
           x[i] = (i-1)*h;
           di[i] = -f(x[i]);
       }
        
        for  (int i=2; i<N+1; i++)
        {
            v1[i] = (v(x[i]) - Math.abs(v(x[i])))/2;
            v2[i] = (v(x[i]) + Math.abs(v(x[i])))/2;
            k_i[i] = k(x[i]);
            bi[i] = q(x[i]) + (k((x[i])-(h/2)) +k((x[i])+(h/2)))/Math.pow(h, 2) +
                    (v2[i]*k((x[i])+(h/2)) - v1[i]*k((x[i])-(h/2)))/(k_i[i]*h);
            ci[i] = - k((x[i])+(h/2))*(1/h + v2[i]/k_i[i])/h; 
                       
        }
        
       bi[1] = 1;
       bi[(int)N] = 1;   
       
       di[1] = m0;
       di[(int)N] = m1;
        
       for (int i=2; i<N; i++)
        {
            ai[i] = k((x[i])- h/2) * (v1[i]/k_i[i] - 1/h)/h;
           
        }
               
         return progonka(ai,ci,bi,di);
    }
    
    public static double [] scheme8(int l, double h)
    {
       double  N = l / h; 
   
       double[] ai = new double[(int)N+1];
       double[] bi = new double[(int)N+1];
       double[] ci = new double[(int)N+1];
       double[] di = new double[(int)N+1];
       double[] x = new double[(int)N+1];
             
       for  (int i=1; i<N; i++)
       {
           x[i] = (i-1)*h;
       }
            
       for (int i=1; i<N+1; i++  ){
           ai[i] = -(-v(x[i])/(2*h) + k((x[i])-(h/2))/Math.pow(h,2)); // коеф A
           bi[i] = -(v(x[i])/(2*h) +k(x[i]+(h/2))/Math.pow(h,2));  // Коеф B      
           di[i] = -f(x[i]); // f      
       }
       
        for (int i=2; i<N+1; i++  ){
              ci[i] = q(x[i])+ ((k(x[i]-(h/2))+k(x[i]+(h/2)))/Math.pow(h,2));//Коеф С
        }
       
       di[1] = m0;
       di[(int)N] = m1;
       ci[1] =1 ;
       ci[(int)N] = 1;       
       bi[1] = 0;
        System.out.println("a " + Arrays.toString(ai));
         System.out.println("b " + Arrays.toString(bi));
          System.out.println("c " + Arrays.toString(ci));
      return progonka(ai,bi,ci,di);

    }

    
    public static double[] progonka(double[] A, double[] C,double[] D,double[] B){ 
        
        int N = B.length;
               
        for (int i=2; i<N; i++){
                  
         //    D[i] = D[i] – (A[i]/D[i-1])*C[i-1];
             B[i] = B[i] - (A[i]/D[i-1])*B[i-1];    
         }
        
           double y[] = new double [N];
           y[N-1]= B[N-1];
        
            for (int i=N-2; i>1; i--){
                 y[i] = (B[i]- C[i]*y[i+1])/D[i];
                // System.out.println(y[i]);
            }
                 
        return y;
        
    }
    
    public static double f(double x){
        double f = 0;   
        return f;
    }
    
    public static double k(double x){
        double k = 1;
        return k;
    }
    
    public static double v(double x){
        double v = 1;
        return v;
    }
    
    public static double q(double x){
        double q = 1;
        return q;
    }
    
    public static double u(double x){
     //Приклад 1
        //double u = 3.843*Math.exp(0.04*x)-2.843*Math.exp(-24.04*x);
        /*double u = (-Math.exp(x/(9+Math.sqrt(82)))- 4*Math.exp((-9-Math.sqrt(82))*x+Math.sqrt(82)+9)
                +Math.exp((-9-Math.sqrt(82))*x+(1/(9+Math.sqrt(82)))+Math.sqrt(82)+9)+4*Math.exp((x/(9+Math.sqrt(82)))+Math.sqrt(82)+9))/
                (Math.exp(9+Math.sqrt(82)+(1/(9+Math.sqrt(82))))-1);
       //Приклад 2*/
        double u = (Math.exp(-0.5*(1+Math.sqrt(5))*(x-2))*(Math.exp(Math.sqrt(5)*x)-1))/(Math.exp(2*Math.sqrt(5))-1);
         return u;
    }
  
}
