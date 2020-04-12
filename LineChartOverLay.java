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
 * This class is designed to display the output of select electricity usage calculations in a line chart. 
 * The overlay is used to illustrate the dynamics of comparison data over one year, as well as a specific interval of time.
 */

public class LineChartOverLay extends JFrame {
	ArrayList<HashMap<Integer, Double>> data;
	private static final long serialVersionUID = 1L;

	public LineChartOverLay (String title, ArrayList<HashMap<Integer, Double>> data) {
		super(title);
		/*
		 * Creates dataset.
		 */
		this.data = data;
		DefaultCategoryDataset dataset = createDataset();
		/*
		 * Creates chart, to include title, x-axis and y-axis.
		 */
		JFreeChart chart = ChartFactory.createLineChart("Electricity Usage", 
				"Day", 
				"Electricity Cost ($)", 
				dataset);

		ChartPanel panel = new ChartPanel(chart);
		setContentPane(panel);
	}
	/**
	 * Creates new dataset.
	 */
	private DefaultCategoryDataset createDataset() {

		String series1 = "With EV";
		String series2 = "Without EV";

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (Integer key : data.get(0).keySet()) {
			dataset.addValue(data.get(0).get(key), series1, key.toString());
			/*
			 *  Adds double.
			 */
			//series1.add(key, data.get(0).get(key)); 
		}

		for (Integer key : data.get(1).keySet()) {
			/*
			 *  Adds double.
			*/
			
			//series1wo.add(key, data.get(1).get(key)); 
			dataset.addValue(data.get(1).get(key), series2, key.toString());
		}

		return dataset;
	}
/**
 * This method generates a chart overlay using electricity usage data.
 * @param args
 */
	public static void main(String[] args) {
		ElectricUsageReader usageWithoutEV = new ElectricUsageReader("09-2018--03-2019.csv");
		ElectricUsageReader usageWithEV = new ElectricUsageReader("09-2019--03-2020.csv");
		usageWithoutEV.list();
		usageWithEV.list();

		SwingUtilities.invokeLater(() -> {

			ArrayList<HashMap<Integer, Double>> data = new ArrayList<>();
			HashMap<Integer, Double> EwithEV = new HashMap<>();
			HashMap<Integer, Double> EwithoutEV = new HashMap<>();
	

			for (int i = 0; i < usageWithEV.costofdata.size(); i++) {
				System.out.println("WithEV" + usageWithEV.costofdata.get(i));
				
				EwithEV.put(i, usageWithEV.costofdata.get(i));
			}

			for (int i = 0; i < usageWithoutEV.costofdata.size(); i++) {
				System.out.println("WithoutEV" + usageWithoutEV.costofdata.get(i));
				EwithoutEV.put(i, usageWithoutEV.costofdata.get(i));
			}
			data.add(EwithEV);
			data.add(EwithoutEV);
			LineChartOverLay  example = new LineChartOverLay ("Electricity Usage", data);
			example.setAlwaysOnTop(true);
			example.pack();
			example.setSize(600, 400);
			example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			example.setVisible(true);
		});
	}
}
