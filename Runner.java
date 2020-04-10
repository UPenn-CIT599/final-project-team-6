import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

		/**
		 * call the methods and output the result.
		 */
		usageWithoutEV.list();
		usageWithEV.list();
		factor.EV_MPkwh(usageWithoutEV.usageofdata, usageWithEV.usageofdata);
		factor.CharlotteElectrcityRate(usageWithEV.costofdata, usageWithEV.usageofdata);
		factor.NewYorkElectrcityRate();
		factor.MiamiElectrcityRate();
		factor.CostComparsion(mileage, totalYear, percentage);	
	}
}
