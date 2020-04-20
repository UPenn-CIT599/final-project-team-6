import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Runner {

	public static void main(String[] args) {
		/**
		 * input file names and create new Object.
		 */
		ElectricUsageReader usageWithoutEV = new ElectricUsageReader("09-2018--03-2019.csv");
		ElectricUsageReader usageWithEV = new ElectricUsageReader("09-2019--03-2020.csv");
		RateCalculator vehicleSpecification = new RateCalculator();

		/**
		 * ask user for input the 3 parameters: mile, year and % check whether or not
		 * the input is invalid.
		 */
		double mileage = 0.0;
		double totalYear = 0.0;
		double percentage = 0.0;

		Scanner in = new Scanner(System.in);
		// prompt user to input mileage info ------------------------------------
		System.out.println("Please enter the mileages you normal drive in one year:");
		mileage = in.nextDouble();	
		while (mileage <= 0) { // check if mileage input is valid
			System.out.println("Invalid input: Mileage should be above 0. "
					+ "Please try again: enter the mileages you normal drive in one year");
		mileage = in.nextDouble();
		}
		// prompt user to input totalYear info-------------------------------------
		System.out.println("Please enter the total years you would like to drive your car:");
		totalYear = in.nextDouble();
		while (totalYear <= 0) { // check if totalYear input is valid
			System.out.println("Invalid input: Total year should be at least 0. "
					+ "Please try again: enter the total years you would like to drive your car");
			totalYear = in.nextDouble();
		}
		// prompt user to input highway percentage info----------------------------
		System.out.println("Please enter the percentage you drive in Hwy: 0.3 means 30%");
		percentage = in.nextDouble();

		while ((percentage > 1.0) || (percentage < 0)) { // check if percentage input is valid
			System.out.println("Invalid input. Percentage need to be >0 and <1."
					+ "Please try again: enter the percentage you drive in Hwy: 0.3 means 30%");
			percentage = in.nextDouble();
		}

		// prompt user to enter the month for electricity usage data to plot---------

		String month = " ";
		System.out.println("Please enter the month that you would like to see daily cost of electricity "
				+ "on the plot \n" + "Range from Sep to Feb, please enter 3 character month selected "
				+ "from: Sep, Oct, Nov, Dec, Jan, Feb");

		month = in.next().toLowerCase();
		// check if input falls in the data base
		while (!month.equals("sep") && !month.equals("oct")
				&& !month.equals("nov") && !month.equals("dec")
				&& !month.equals("jan") && !month.equals("feb")) {
			System.out.println("Invalid input. There are only useage data from Sep to Feb. Please type"
					+ "a 3-character month that is within the data base.");
			month = in.next();
		}
		in.close(); // close the scanner

		/**
		 * call the methods and output the result.
		 */
		usageWithoutEV.list();
		usageWithEV.list();
		usageWithoutEV.eachMonthlyCost();
		usageWithEV.eachMonthlyCost();
		vehicleSpecification.EV_MPkwh(usageWithoutEV.usageofdata, usageWithEV.usageofdata);
		vehicleSpecification.CharlotteElectrcityRate(usageWithEV.costofdata, usageWithEV.usageofdata);
		vehicleSpecification.NewYorkElectrcityRate();
		vehicleSpecification.MiamiElectrcityRate();
		vehicleSpecification.CostComparsion(mileage, totalYear, percentage);
		/**
		 * plot 2 curves in one graph per user's instruction.
		 */
				
		ArrayList<HashMap<Integer, Double>> data = vehicleSpecification.monthToPlot(month);

		SwingUtilities.invokeLater(() -> {

			LineChartOverLay dailyElectricityCost = new LineChartOverLay("Electricity Usage", data);
			dailyElectricityCost.setAlwaysOnTop(true);
			dailyElectricityCost.pack();
			dailyElectricityCost.setSize(600, 400);
			dailyElectricityCost.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			dailyElectricityCost.setVisible(true);
		});

	}
}
