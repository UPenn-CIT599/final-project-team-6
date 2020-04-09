
public class ICEUsage { //Internal Combustion Engine Usage class
	
	/**
	 * This class set all getter methods for ICE Vehicle information
	 * model = model of vehicles. (In our project, we have Mercedes Benz and Lexus)
	 * city = city in the US. (In our project, we will only select New York, Charlotte, and Miami)
	 * regularGas = Today's average regular gas price by city.
	 * premiumGas = Today's average premium gas price by city.
	 * cityMPG = Mile / Gallons in City (In our project, we will use Mercedes Benz 
	 * and Lexus drive in New York, Charlotte, and Miami)
	 * hwyMPG = Mile / Gallons in HighWay (In our project, we will use Mercedes Benz 
	 * and Lexus drive in New York, Charlotte, and Miami)
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
	
	public String getModel() {
		return model;
	}
	public String getCity() {
		return city;
	}
	public double getRegularGas() {
		return regularGas;
	}
	public double getPremiumGas() {
		return premiumGas;
	}
	public double getcityMPG() {
		return cityMPG;
	}
	public double gethwyMPG() {
		return hwyMPG;
	}

}
