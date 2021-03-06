/**
 * Internal Combustion Engine Usage class
 */
public class ICEUsage { 
	
	/**
	 * This class set all getter methods for ICE Vehicle information
	 * model = model of vehicles (In our project, we have Mercedes GLC and Lexus LX as comparable vehicles to Tesla.)
	 * city = select city in the US east coast (i.e., New York, Charlotte, and Miami)
	 * regularGas = Today's average regular gas price by city
	 * premiumGas = Today's average premium gas price by city
	 * cityMPG = Mile / Gallons in City (i.e.,Mercedes GLC
	 * and Lexus LX drivers in New York, NY, Charlotte, NC, and Miami, FL)
	 * hwyMPG = Mile / Gallons in HighWay (i.e.,Mercedes GLC
	 * and Lexus LX drivers in New York, NY, Charlotte, NC, and Miami, FL)
	 */
	private String model; 
	private String city;
	private double regularGas;
	private double premiumGas;
	private double cityMPG;
	private double hwyMPG;
	
	/**
	 * Constructor for this class
	 * @param model
	 * @param city
	 * @param regularGas
	 * @param premiumGas
	 * @param cityMPG
	 * @param hwyMPG
	 */
	
	public ICEUsage(String model, String city, double regularGas, double premiumGas, double cityMPG, double hwyMPG) {
		this.model = model;
		this.city = city;
		this.regularGas = regularGas;
		this.premiumGas = premiumGas;
		this.cityMPG = cityMPG;
		this.hwyMPG = hwyMPG;
	}
	/**
	 * Retrieves the car model
	 */
	public String getModel() {
		return model;
	}
	/**
	 * Retrieves the city name
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Retrieves regular gas price
	 */
	public double getRegularGas() {
		return regularGas;
	}
	/**
	 * Retrieves premium gas price
	 */
	public double getPremiumGas() {
		return premiumGas;
	}
	/**
	 * Retrieves city miles per gallon
	 */
	public double getcityMPG() {
		return cityMPG;
	}
	/**
	 * Retrieves highway miles per gallon
	 */
	public double gethwyMPG() {
		return hwyMPG;
	}

}
