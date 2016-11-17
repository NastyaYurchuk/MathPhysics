/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chart;

/**
 *
 * @author nastja
 */
import java.awt.Color;
import java.util.List;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * A simple demonstration application showing how to create a dual axis chart based on data
 * from two {@link CategoryDataset} instances.
 *
 */
public class DualAxis extends ApplicationFrame {

    /**
     * Creates a new demo instance.
     *
     * @param title  the frame title.
     */
     
     
    public DualAxis(final String title, double[] xData, 
            double[] YDataAnalitic, double[] YDataNumerical ) {

        super(title);

        final CategoryDataset dataset1 = createDataset1(xData, YDataAnalitic);

        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            "",        // chart title
            "x",               // domain axis label
            "y",                  // range axis label
            dataset1,                 // data
            PlotOrientation.VERTICAL,
            true,                     // include legend
            true,                     // tooltips?
            false                     // URL generator?  Not required...
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
        chart.setBackgroundPaint(Color.white);
//        chart.getLegend().setAnchor(Legend.SOUTH);

        // get a reference to the plot for further customisation...
        final CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);

        final CategoryDataset dataset2 = createDataset2(xData, YDataNumerical);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);

        final CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
       //final ValueAxis axis2 = new NumberAxis("");
        final ValueAxis axis2 = new NumberAxis(" ");
        plot.setRangeAxis(1,axis2);
       
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
        renderer2.setToolTipGenerator(new StandardCategoryToolTipGenerator());
        plot.setRenderer(1, renderer2);
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.REVERSE);
        // OPTIONAL CUSTOMISATION COMPLETED.

        // add the chart to a panel...
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(chartPanel);
       //  panel.removeAll();
   //   panel.add(chartPanel);
   //   panel.validate();

    }

    // ****************************************************************************
    // * JFREECHART DEVELOPER GUIDE                                               *
    // * The JFreeChart Developer Guide, written by David Gilbert, is available   *
    // * to purchase from Object Refinery Limited:                                *
    // *                                                                          *
    // * http://www.object-refinery.com/jfreechart/guide.html                     *
    // *                                                                          *
    // * Sales are used to provide funding for the JFreeChart project - please    * 
    // * support us so that we can continue developing free software.             *
    // ****************************************************************************
    
    /**
     * Creates a sample dataset.
     *
     * @return  The dataset.
     */
    private CategoryDataset createDataset1(double[] xData, 
            double[] YDataActual) {

        // row keys...
        final String series1 = "Computed";

        System.out.println(YDataActual.toString());
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
         for (int i = 0; i < xData.length; i++) {
             if(i >= YDataActual.length){
                 dataset.addValue(0, series1, Double.toString(xData[i]));
             }else{
                 dataset.addValue((Number)YDataActual[i], series1, xData[i]);
             }
            
             
            
        }
        /*dataset.addValue(1.0, series1, category1);
        dataset.addValue(4.0, series1, category2);
        dataset.addValue(3.0, series1, category3);
        dataset.addValue(5.0, series1, category4);
        dataset.addValue(5.0, series1, category5);
        dataset.addValue(7.0, series1, category6);
        dataset.addValue(7.0, series1, category7);
        dataset.addValue(8.0, series1, category8);*/
        return dataset;

    }

    /**
     * Creates a sample dataset.
     *
     * @return  The dataset.
     */
    private CategoryDataset  createDataset2(double[] xData, 
            double[] YDataProgn) {

        // row keys...
        final String series1 = "Analytical";

        // column keys...
        final String category1 = "Category 1";
        final String category2 = "Category 2";
        final String category3 = "Category 3";
        final String category4 = "Category 4";
        final String category5 = "Category 5";
        final String category6 = "Category 6";
        final String category7 = "Category 7";
        final String category8 = "Category 8";
       
        System.out.println("progn " + YDataProgn.toString());
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

         for (int i = 0; i < YDataProgn.length; i++) {
            
            dataset.addValue((Number)YDataProgn[i], series1, xData[i]);
             
            
        }
        /*dataset.addValue(15.0, series1, category1);
        dataset.addValue(24.0, series1, category2);
        dataset.addValue(31.0, series1, category3);
        dataset.addValue(25.0, series1, category4);
        dataset.addValue(56.0, series1, category5);
        dataset.addValue(37.0, series1, category6);
        dataset.addValue(77.0, series1, category7);
        dataset.addValue(18.0, series1, category8);*/

        return dataset;

    }

   
}
