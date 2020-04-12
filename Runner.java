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
		RateCalculator factor = new RateCalculator();

		/**
		 * ask user for input the 3 parameters: mile, year and % check whether or not
		 * the input is invalid.
		 */
		boolean check = true;
		double mileage = 0;
		double totalYear = 0;
		double percentage = 0.0;
		String month = null;
		Scanner in = new Scanner(System.in);
		while (check == true) {
			System.out.println("Please enter the mileages you normal drive in one year:");
			mileage = in.nextDouble();
			if (mileage < 0) {
				check = true;
				System.out.println("Please enter the mileages above 0");
			} else {
				check = false;
			}
		}
		check = true;
		while (check == true) {
			System.out.println("Please enter the total years you would like to drive your car:");
			totalYear = in.nextDouble();
			if (totalYear < 0) {
				check = true;
				System.out.println("Please enter the year above 0");
			} else {
				check = false;
			}
		}
		check = true;
		while (check == true) {
			System.out.println("Please enter the percentage you drive in Hwy: 0.3 means 30%");
			percentage = in.nextDouble();
			if ((percentage > 1.0) || (percentage < 0)) {
				check = true;
				System.out.println("Please enter the percentage between 0 and 1");
			} else {
				check = false;
			}
		}
		Scanner plot = new Scanner(System.in);
		System.out.println("Please enter the month that you would like to see the cost on the plot");
		System.out.println("Range from Sep to Feb, please enter (Sep, Oct, Nov, Dec, Jan, Feb)");
		while (check == false) {
		month = plot.nextLine();
			if ((month.toLowerCase().contentEquals("sep")) || (month.toLowerCase().contentEquals("oct"))
					|| (month.toLowerCase().contentEquals("nov")) || (month.toLowerCase().contentEquals("dec"))
					|| (month.toLowerCase().contentEquals("jan")) || (month.toLowerCase().contentEquals("feb"))) {
				check = true;
				
			} else {

				check = false;
				System.out.println("Please enter the month between Sep and Feb");
			}
		}

		/**
		 * call the methods and output the result.
		 */
		usageWithoutEV.list();
		usageWithEV.list();
		usageWithoutEV.eachMonthlyCost();
		usageWithEV.eachMonthlyCost();
		factor.EV_MPkwh(usageWithoutEV.usageofdata, usageWithEV.usageofdata);
		factor.CharlotteElectrcityRate(usageWithEV.costofdata, usageWithEV.usageofdata);
		factor.NewYorkElectrcityRate();
		factor.MiamiElectrcityRate();
		factor.CostComparsion(mileage, totalYear, percentage);	
		factor.monthofPlot(month);
//		System.out.println(factor.EVPlot);
//		System.out.println(factor.nonEVPlot);
}
}
