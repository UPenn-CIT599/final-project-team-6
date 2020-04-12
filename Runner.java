import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * /**
* This Tesla program implements an application that
* compares the annual cost of electricity usage while driving the electric vehicle Tesla 
* to the cost of gasoline while driving a Lexus LX or Mercedes GLC on the east coast. Output to
* select prompts is displayed in a text file as well as a chart.
*
* @authors  Dewei Zhou <zdw466@seas.upenn.edu>, Kelly Jackson Charles <kcharl@useas.upenn.edu>, Bingqian Lu <bingqian@seas.upenn.edu>

 */

public class Runner {
	/**
	 * Inputs file names and creates new objects.
	 */
	public static void main(String[] args) {
		
		ElectricUsageReader usageWithoutEV = new ElectricUsageReader("09-2018--03-2019.csv");
		ElectricUsageReader usageWithEV = new ElectricUsageReader("09-2019--03-2020.csv");
		RateCalculator factor = new RateCalculator();
		/**
		 * Asks user for input of 3 parameters: miles driven annually, desired years to drive this vehicle, and percentage of highway driving. Checks whether 
		 * the input is valid.
		 */
		boolean check = true;
		double mileage = 0;
		double totalYear = 0;
		double percentage = 0.0;
		Scanner in = new Scanner(System.in);
		while (check == true) {
			System.out.println("Please enter the total number of mile that you normally drive in one year.");
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
			System.out.println("Please enter the total number of years that you would like to drive your car.");
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
			System.out.println("Please enter the percentage of time that you drive on the Hwy: 0.3 means 30%.");
			percentage = in.nextDouble();
			if ((percentage > 1.0) || (percentage < 0)) {
				check = true;
				System.out.println("Please enter the percentage between 0 and 1.");
			} else {
				check = false;
			}
		}

		/**
		 * Calls the methods and outputs the results.
		 */
		usageWithoutEV.list();
		usageWithEV.list();
		factor.EV_MPkwh(usageWithoutEV.usageofdata, usageWithEV.usageofdata);
		factor.CharlotteElectricityRate(usageWithEV.costofdata, usageWithEV.usageofdata);
		factor.NewYorkElectricityRate();
		factor.MiamiElectricityRate();
		factor.CostComparsion(mileage, totalYear, percentage);	
	}
}