import java.util.*;
import java.io.*;

/*
 * This class will read electricity usage data from csv file and store each column in an
 * arraylist.
 */

public class ElectricUsageReader {

	/*
	 * Initializes arraylists as instance variables, which can be used in other classes. 
	 * listmonth = arraylist contains the months for each day
	 * monthofdata = arraylist contains only the unique months, in our project will be
	 * (Sep,Oct,Nov,Dec,Jan,Feb)
	 * dayofdata = arraylist contains the date of each month for 6 months
	 * usageofdata = arraylist contains the daily usage in kwh for 6 months 
	 * costofdata = arraylist contains the daily usage in dollar for 6 months
	 * monthlyusage = arraylist contains the monthly usage in kwh for 6 months 
	 * monthlycost = arraylist contains the monthly usage in dollar for 6 months
	 */

	ArrayList<Integer> listmonth = new ArrayList<Integer>();
	ArrayList<Integer> monthofdata = new ArrayList<Integer>();
	ArrayList<Integer> dayofdata = new ArrayList<Integer>();
	ArrayList<Integer> usageofdata = new ArrayList<Integer>();
	ArrayList<Double> costofdata = new ArrayList<Double>();
	ArrayList<Integer> monthlyusage = new ArrayList<Integer>();
	ArrayList<Double> monthlycost = new ArrayList<Double>();

	/*
	 * This method creates one HashMap to store data from the csv files, and converts data to int
	 * or double.
	 */
	private HashMap<Integer, ElectricUsage> Usage;

	/*
	 * Constructor for this class.
	 * It passes in the filename and distributes all data
	 * into corresponding fields for the ElectricUsage class
	 * @param filename
	 */
	public ElectricUsageReader(String filename) {
		File file = new File(filename);
		Usage = new HashMap<Integer, ElectricUsage>();
		try {
			Scanner scanner = new Scanner(file);
			scanner.nextLine(); //read and discard the first line (header of the file)
			while (scanner.hasNextLine()) { //read while there is a next line
				
				String electrityRow = scanner.nextLine();
				String[] columnData = electrityRow.split(","); //parse by comma

				int month = Integer.parseInt(columnData[4]);
				int day = Integer.parseInt(columnData[5]);
				int usage = Integer.parseInt(columnData[6]);
				double cost = Double.parseDouble(columnData[8]);
				
				ElectricUsage e = new ElectricUsage(month, day, usage, cost); 
				Usage.put(Integer.parseInt(columnData[0]), e);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This method reads through the HashMap, and stores each row of data into four
	 * arraylists of this class.
	 */
	public void list() {
		for (int nday : Usage.keySet()) {
			ElectricUsage thatDaysUseage = Usage.get(nday);
			int currentmonth = thatDaysUseage.getMonth();
			listmonth.add(currentmonth); //populate listmonth arraylist
			int currentday = thatDaysUseage.getDay();
			dayofdata.add(currentday);//populate currentday arraylist
			int currentUseage = thatDaysUseage.getUsage();
			usageofdata.add(currentUseage);//populate usageofdata arraylist
			double currentCost = thatDaysUseage.getCost();
			costofdata.add(currentCost);//populate costofdata arraylist
		}
		
		/*
		 * Uses LinkedHashSet to remove the duplicate month and maintain the unique
		 * month.
		 */
		LinkedHashSet<Integer> hashSet1 = new LinkedHashSet<>(listmonth);
		monthofdata = new ArrayList<>(hashSet1);
	}

	/*
	 * This method calculates the monthly usage by adding up all daily usage and saves
	 * into an arraylist resulting in monthly usage.
	 */
	public void MonthlyUsage() {
		int sumofmonthusage = 0; //initiate to 0
		for (int i = 0; i < monthofdata.size(); i++) {
			for (int j = 0; j < usageofdata.size(); j++) {
				if (monthofdata.get(i) == listmonth.get(j)) { //count only when month are the same
					sumofmonthusage = sumofmonthusage + usageofdata.get(j);
				}
			}
			monthlyusage.add(sumofmonthusage); //populate arraylist with month useage data
			sumofmonthusage = 0; //reset the sumofmonthusage to 0 and start again for next month
		}
	}

	/*
	 * This getter method retrieves the MonthlyUsage.
	 * 
	 * @return
	 */
	public ArrayList<Integer> getMonthlyusage() {
		return monthlyusage;
	}

	/*
	 * This method calculates the monthly cost by adding up all daily cost and saves
	 * into arraylist resulting in monthly cost.
	 */
	public void MonthlyCost() {
		double sumofmonthcost = 0.0;
		for (int i = 0; i < monthofdata.size(); i++) {
			for (int j = 0; j < costofdata.size(); j++) {
				if (monthofdata.get(i) == listmonth.get(j)) {
					//add only when the month is right
					sumofmonthcost = sumofmonthcost + costofdata.get(j); 
					sumofmonthcost = Math.round(sumofmonthcost * 100.0) / 100.0;
				}
			}
			monthlycost.add(sumofmonthcost); //populate the arraylist 
			sumofmonthcost = 0.0;//reset the sumofmonthusage to 0 and start again for next month
		}
	}

	/*
	 * This getter method retrieves MonthlyCost.
	 * 
	 * @return
	 */
	public ArrayList<Double> getMonthlycost() {
		return monthlycost;
	}
}
