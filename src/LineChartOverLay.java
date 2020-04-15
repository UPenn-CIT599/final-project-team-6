package team6Final; 
/**
 * Creates chart file using user interactive input.
 */

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Creates chart title with x and y axis.
 *
 */
public class LineChartOverLay extends JFrame {
	ArrayList<HashMap<Integer, Double>> data;
	private static final long serialVersionUID = 1L;

	public LineChartOverLay (String title, ArrayList<HashMap<Integer, Double>> data) {
		super(title);
		
		this.data = data;
		DefaultCategoryDataset dataset = createDataset();
		
		JFreeChart chart = ChartFactory.createLineChart("Electricity Usage", 
				"Day", 
				"Electricity Cost ($)", 
				dataset);

		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}
	/**
	 * Stores and returns dataset.
	 */
	private DefaultCategoryDataset createDataset() {

		String series1 = "With EV";
		String series2 = "Without EV";

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Integer key : data.get(0).keySet()) {
			dataset.addValue(data.get(0).get(key), series1, key.toString());
		}

		for (Integer key : data.get(1).keySet()) {
			dataset.addValue(data.get(1).get(key), series2, key.toString());
		}
		return dataset;
	}
}