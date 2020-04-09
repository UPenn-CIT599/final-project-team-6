
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.HashMap;

/**
 * This class takes in data and plots them into line charts 
 */
public class LineChartEx extends JFrame {
	HashMap<Integer, Double> data = new HashMap<>();
	
	//constructor
    public LineChartEx(HashMap<Integer, Double> data) {
    	this.data = data;
        initUI();
        
    }

    /**
     * This method sets up the chart structure
     */
    private void initUI() {

        XYDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method creates dataset
     * @return
     */
    private XYDataset createDataset() {

        var series = new XYSeries("2018");

        for(Integer key: data.keySet()) {
        	series.add(key, data.get(key)); //add adds double!
        }
        
        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    /**
     * This method takes in the dataset from the previous method and creat the chart
     * @param dataset
     * @return chart
     */
    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Electricity Usage per month",
                "Month",
                "Cost ($)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));

        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("electricity cost per Month",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

    
   
    public static void main(String[] args) {
    	ElectricUsageReader usageWithoutEV = new ElectricUsageReader("09-2018--03-2019.csv");
		ElectricUsageReader usageWithEV = new ElectricUsageReader("09-2019--03-2020.csv");
		usageWithoutEV.list();
		usageWithEV.list();
		
		
//		System.out.println("cost of data size: "+ usageWithEV.costofdata.size());
        EventQueue.invokeLater(() -> {
        	
        	HashMap<Integer, Double> data = new HashMap<>();
        	
        	for(int i=0; i<usageWithEV.costofdata.size();i++) {
        		System.out.println(usageWithEV.costofdata.get(i));
        		data.put(i, usageWithEV.costofdata.get(i));
        	}
        	
        	//Two things to figure out:
        	//1. lay charts over each other
        	//2. what to do with the X axis
        	
            var ex = new LineChartEx(data);
            ex.setVisible(true);
        });
    }
}