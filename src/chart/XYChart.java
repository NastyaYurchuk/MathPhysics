
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
//import org.apache.commons.math3.optimization.direct.PowellOptimizer;
import java.awt.Color; 
import java.awt.BasicStroke; 
import java.awt.BorderLayout;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class XYChart extends ApplicationFrame 
{  
    public XYChart( String applicationTitle, String chartTitle,  double[] xData, 
            double[] YDataAnalitic, double[] YDataNumerical1,double[] YDataNumerical2 )
   {
     
      super(applicationTitle);
      
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "" ,
         "" ,
         createDatasetForThree( xData, YDataAnalitic, YDataNumerical1, YDataNumerical2),
         PlotOrientation.VERTICAL ,
         true , true , false); 
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint(2, Color.YELLOW);
      renderer.setSeriesPaint(3, Color.BLUE);
      renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
       renderer.setSeriesStroke( 1 , new BasicStroke( 1.0f ) );
       renderer.setSeriesStroke( 2 , new BasicStroke( 1.0f ) );
       renderer.setSeriesStroke( 3 , new BasicStroke( 1.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
      
     // panel.removeAll();
    //  panel.add(chartPanel);
    //  panel.validate();
      
   }
   public XYChart( String applicationTitle, String chartTitle,  double[] xData, 
            double[] YDataAnalitic, double[] YDataNumerical1,double[] YDataNumerical2, double[] YDataNumerical3 )
   {
     
      super(applicationTitle);
      
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "" ,
         "" ,
         createDatasetForFour(xData, YDataAnalitic, YDataNumerical1, YDataNumerical2, YDataNumerical3),
         PlotOrientation.VERTICAL ,
         true , true , false); 
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint(2, Color.YELLOW);
      renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
       renderer.setSeriesStroke( 1 , new BasicStroke( 1.0f ) );
       renderer.setSeriesStroke( 2 , new BasicStroke( 1.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
      
     // panel.removeAll();
    //  panel.add(chartPanel);
    //  panel.validate();
      
   }
   public XYChart( String applicationTitle, String chartTitle,  double[] xData, 
            double[] YDataAnalitic, double[] YDataNumerical )
   {
     
      super(applicationTitle);
      
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "" ,
         "" ,
         createDataset( xData, YDataAnalitic, YDataNumerical) ,
         PlotOrientation.VERTICAL ,
         true , true , false);
       System.out.println("vvvv");  
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesStroke( 0 , new BasicStroke( 2.0f ) );
       renderer.setSeriesStroke( 1 , new BasicStroke( 2.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
      
     // panel.removeAll();
    //  panel.add(chartPanel);
    //  panel.validate();
      
   }
   
   private XYDataset createDataset(double[] xData, 
            double[] YDataAnalitic, double[] YDataNumerical )
   {
      final XYSeries analytical = new XYSeries( "Analytical" ); 
       final XYSeries numerical = new XYSeries( "Numerical" );
    for (int i = 0; i < xData.length; i++) {     
                 analytical.add( xData[i], (Number)YDataAnalitic[i]);
                 numerical.add( xData[i], (Number)YDataNumerical[i]);
             }                 
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries(analytical );          
      dataset.addSeries( numerical );          
     // dataset.addSeries( iexplorer );
      return dataset;
   }

    private double[] formatAxis(double[] xData) {
        DecimalFormat df2 = new DecimalFormat( "00.00" );
        double[] formData  = new double[xData.length];
        for (int i = 0; i < formData.length; i++) {
           
            formData[i] = new Double(df2.format(xData[i]));
            
        }
        
       return formData;
    }
    
    private XYDataset createDatasetForThree(double[] xData, 
            double[] YDataAnalitic, double[] YDataNumerical1,double[] YDataNumerical2)
   {
      final XYSeries analytical = new XYSeries( "Analytical" ); 
       final XYSeries numerical = new XYSeries( "Numerical" );
        final XYSeries numericalApp = new XYSeries( "Numerical with approximation" );
    for (int i = 0; i < xData.length; i++) {     
                 analytical.add( xData[i], (Number)YDataAnalitic[i]);
                 numerical.add( xData[i], (Number)YDataNumerical1[i]);
                 numericalApp.add( xData[i], (Number)YDataNumerical2[i]);
             }                 
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries(analytical );          
      dataset.addSeries( numerical ); 
      dataset.addSeries(numericalApp);
     // dataset.addSeries( iexplorer );
      return dataset;
   }
    
     private XYDataset createDatasetForFour(double[] xData, 
            double[] YDataAnalitic, double[] YDataNumerical1,double[] YDataNumerical2, double[] YDataNumerical3 )
   {
      final XYSeries analytical = new XYSeries( "Analytical" ); 
      final XYSeries numerical1= new XYSeries( "Sample" );
      final XYSeries numerical2 = new XYSeries( "Samarskiy" );
      final XYSeries numerical3  = new XYSeries( "Reynolds" );
    for (int i = 0; i < xData.length; i++) {     
                 analytical.add( xData[i], (Number)YDataAnalitic[i]);
                 numerical1.add( xData[i], (Number)YDataNumerical1[i]);
                 numerical2.add( xData[i], (Number)YDataNumerical2[i]);
                 numerical3.add(xData[i], (Number)YDataNumerical3[i]);
             }                 
      final XYSeriesCollection dataset = new XYSeriesCollection( );          
      dataset.addSeries(analytical );          
      dataset.addSeries( numerical1 ); 
      dataset.addSeries(numerical2);
       dataset.addSeries(numerical3);
     // dataset.addSeries( iexplorer );
      return dataset;
   }

    
}
