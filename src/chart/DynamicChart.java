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
        XYSeries series;
        XYSeriesCollection data = new XYSeriesCollection();
        JFreeChart chart = null;
        ChartPanel chartPanel;

        for (int j = 0; j < 10; j++) {
            try {
                System.out.println("begin new series " + j);
                series = new XYSeries("Ряд1" + j);
                for (int k = 0; k < y.length; k++) {
                    series.addOrUpdate(x[k], y[k][j]);
                    System.out.println("x " + x[k] + " y " + y[k][j]);
                }
                // System.out.println("remove");
                // data.removeAllSeries();

                if (j == 0) {
                    System.out.println("add");
                    data.addSeries(series);
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

                    chartPanel = new ChartPanel(chart);
                    chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

                    System.out.println("0 sec");
                    frame.setContentPane(chartPanel);
                    if (j == 0) {
                        frame.pack();
                    }
                    frame.setVisible(true);
                }
                sleep(3000);
                System.out.println("3 sec");
            } //встановлюємо розмір графіка
            catch (InterruptedException ex) {

            }
        }

    }

    public void buildAndGo(double[] x, double[][] y, double[][] yEx, double[][] yIm, double[][] yCenEx, double[][] yCenIm, double[][] yCenImCr) {
        JFrame frame = new JFrame(); //створюємо каркас вікна
        frame.setTitle("Графік");    //заголовок вікна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //створюємо 1 ряд даних
        int m = y.length;
        int n = y[0].length;
        System.out.println("n = " + n + " m = " + m);
        XYSeries series = new XYSeries("Analitic");
        XYSeries series1 = new XYSeries("Right Explicit");
        XYSeries series2 = new XYSeries("Right Implicit");
        XYSeries series3 = new XYSeries("Central Impicit");
        XYSeries series4 = new XYSeries("Central Impicit Crunk");
        XYSeries series5 = new XYSeries("Central Explicit");
        //додаємо точки на графіку
       for (int k = 0; k < n; k++) {
            series.addOrUpdate(x[k], y[0][k]);
            series1.addOrUpdate(x[k], yEx[0][k]);
            series2.addOrUpdate(x[k], yIm[0][k]);
            series3.addOrUpdate(x[k], yCenIm[0][k]);
            series4.addOrUpdate(x[k], yCenImCr[0][k]);
          //  series5.addOrUpdate(x[k], yCenEx[0][k]);
            }
// зразу ж додаємо ряд в набір даних
        XYSeriesCollection data = new XYSeriesCollection(series);
        data.addSeries(series1);
        data.addSeries(series2);
        data.addSeries(series3);
        data.addSeries(series4);
      //  data.addSeries(series5);
        //   data.addSeries(series2); // додаємо другий ряд в набір
//створюємо діаграму
        JFreeChart chart = ChartFactory.createXYLineChart(
                "",
                "X",
                "Y",
                data,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        //створюємо панель для графіка
        ChartPanel chartPanel = new ChartPanel(chart);
        //встановлюємо розмір графіка
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        //додаємо панель на створений нами фрейм
        frame.setContentPane(chartPanel);
        //підганяємо розміри фрейму
        frame.pack();
        //робимо усе видимим
        frame.setVisible(true);
        System.out.println("0 sec");
        try {
            sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DynamicChart.class.getName()).log(Level.SEVERE, null, ex);
        }
       //System.out.println("4 sec");
        for (int j = 1; j < m; j++) {

            for (int k = 0; k < n; k++) {
                series.updateByIndex(k, y[j][k]);
                series1.updateByIndex(k, yEx[j][k]);
                series2.updateByIndex(k, yIm[j][k]);
                series3.updateByIndex(k, yCenIm[j][k]);
                series4.updateByIndex(k, yCenImCr[j][k]);
            }
          //  System.out.println("0 sec");
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(DynamicChart.class.getName()).log(Level.SEVERE, null, ex);
        }
     //   System.out.println("4 sec");
        }
        System.out.println("end");
    }
}
