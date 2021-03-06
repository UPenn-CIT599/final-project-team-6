public class ElectricUsage {
/**
 * This class set all getter methods for electricity usage information
 * month = month of the year which usage occurs.
 * day = day of the month which usage occurs.
 * usage = total usage in one day, unit is kwh.
 * cost = total cost in one day, unit is $. 
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
 * This method retrieves the month.  
 */
	public int getMonth() {
		return month;
	}
/**
 * This method retrieves the day.  
 */
	public int getDay() {
		return day;
/**
 * This method retrieves the electricity usage.  
 */
	}
	public int getUsage() {
		return usage;
	}
/**
 * This method retrieves the cost.  
 */
	public double getCost() {
		return cost;
	}
}
