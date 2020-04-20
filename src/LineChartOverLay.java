/**
 * This class displays electricity data in a line chart
 */

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 * This method stores data from electricity files
 */
public class LineChartOverLay extends JFrame {
	ArrayList<HashMap<Integer, Double>> data;
	private static final long serialVersionUID = 1L;
/**
 * This method creates the line chart with data from electricity files
 */
	public LineChartOverLay (String title, ArrayList<HashMap<Integer, Double>> data) {
		super(title);
		// Create dataset
		this.data = data;
		DefaultCategoryDataset dataset = createDataset();
		// Create chart
		JFreeChart chart = ChartFactory.createLineChart("Electricity Usage", // Chart title
				"Day", // X-Axis Label
				"Electricity Cost ($)", // Y-Axis Label
				dataset);

		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}
/**
 * This method creates a dataset from electricity files
 */
	private DefaultCategoryDataset createDataset() {

		String series1 = "With EV";
		String series2 = "Without EV";

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Integer key : data.get(0).keySet()) {
			dataset.addValue(data.get(0).get(key), series1, key.toString());
//			series1.add(key, data.get(0).get(key)); // add adds double!
		}

		for (Integer key : data.get(1).keySet()) {
//			series1wo.add(key, data.get(1).get(key)); // add adds double!
			dataset.addValue(data.get(1).get(key), series2, key.toString());
		}

		return dataset;
	}


}