public class ElectricUsage {
/**
 * This class sets all getter methods for electricity usage information.
 * month = month of the year which usage occurs
 * day = day of the month which usage occurs
 * usage = total usage in one day, unit is kilowatt-hour unit of energy (kwh)
 * cost = total cost in one day, unit is US dollar
 *  
 */
	private int month;
	private int day;
	private int usage;
	private double cost;
	/**
	 * Constructor for the class
	 * @param month 
	 * @param day
	 * @param usage
	 * @param cost
	 */
	public ElectricUsage(int month, int day, int usage, double cost) {
		this.month = month;
		this.day = day;
		this.usage = usage;
		this.cost = cost;
	}
	/**
	 * This method retrieves the month that the electric usage occurred.
	*/
	public int getMonth() {
		return month;
	}
	/**
	 * This method retrieves the day in the month that electric usage occurred.
	*/
	public int getDay() {
		return day;
	}
	/**
	 * This method retrieves the electric usage in kwh units of energy.
	*/
	public int getUsage() {
		return usage;
	}
	/**
	 * This method retrieves the cost of electricity utilized.
	*/
	public double getCost() {
		return cost;
	}
}
