/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chart;

/**
 *
 * @author okurylo
 */
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DynamicChart {

    public void build(String title, double[] x, double[][] y) {

        JFrame frame = new JFrame(); //створюємо каркас вікна
        frame.setTitle("Графік");    //заголовок вікна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        XYSeries series ;
        XYSeriesCollection data = new XYSeriesCollection();
        JFreeChart chart = null;
        ChartPanel chartPanel;
       
        for (int j = 0; j < 10; j++) {
            try {
                System.out.println("begin new series " + j);
                series = new XYSeries("Ряд1" + j);
                for (int k = 0; k < y.length; k++) {
                    series.add(x[k], y[k][j]);
                  //  System.out.println("x " + x[k] + " y " +  y[k][j]);
                }
                System.out.println("remove");
                data.removeAllSeries();
                System.out.println("add");
                data.addSeries(series);
                
                if (j == 0){
                    chart = ChartFactory.createXYLineChart(
                        title,
                        "X",
                        "Y",
                        data,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                );
                    
                }
                chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
                if (j > 0){
                    System.out.println("remove frame");
                   frame.removeAll();
                }
                System.out.println("0 sec");
                frame.setContentPane(chartPanel);
                if (j == 0){
                    frame.pack();
                }
                frame.setVisible(true);
                sleep(3000);
                System.out.println("3 sec");
            }
            //встановлюємо розмір графіка
            catch (InterruptedException ex) {
               
            }
        }
        
      

    }
}
