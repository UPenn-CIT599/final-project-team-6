import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class LineChartOverLay extends JFrame {
	ArrayList<HashMap<Integer, Double>> data;
	private static final long serialVersionUID = 1L;

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