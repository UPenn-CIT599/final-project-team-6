import java.util.*;
import java.io.*;

public class ICEUsageReader {
	/**
	 * Initialize several instance variables, which can be used in other classes
	 * City and Hwy MPG of both Mercedes and Lexus
	 * Regular Gas and Premium Gas Prices for three different cities.
	 */
	double MercedesCityMPG = 0.0;
	double MercedesHwyMPG = 0.0;
	double LexusCityMPG = 0.0;
	double LexusHwyMPG = 0.0;
	double CharlotteRegularGas = 0.0;
	double CharlottePremiumGas = 0.0;
	double NewYorkRegularGas = 0.0;
	double NewYorkPremiumGas = 0.0;
	double MiamiRegularGas = 0.0;
	double MiamiPremiumGas = 0.0;

	/**
	 * create one HashMap to store data through csv files, and convert data to int or double.
	 */
	private HashMap<Integer, ICEUsage> gasMap;
	
	/**
	 * Constructor
	 * Read in the given file and parse the data and populate the ICEUsage class with the data
	 * @param filename
	 */
	public ICEUsageReader(String filename) {
		File file = new File(filename);
		gasMap = new HashMap<Integer, ICEUsage>();
		try {
			Scanner scanner = new Scanner(file); //create scanner object
			scanner.nextLine(); //read and discard the first line (header of csv)
			while (scanner.hasNextLine()) { //while there is a next line
				String ICERow = scanner.nextLine(); //store the current line in a String
				String[] columnData = ICERow.split(","); //parse by the comma

				String model = columnData[2];
				String city = columnData[4];
				double regularGas = Double.parseDouble(columnData[6]);
				double premiumGas = Double.parseDouble(columnData[8]);
				double cityMPG = Double.parseDouble(columnData[9]);
				double hwyMPG = Double.parseDouble(columnData[10]);
				//Create an ICEUsage object and initialize the values
				ICEUsage ice = new ICEUsage(model, city, regularGas, premiumGas, cityMPG, hwyMPG);
				gasMap.put(Integer.parseInt(columnData[0]), ice); //populate the gasMap hasmap
			}
		} catch (FileNotFoundException e) {

		}
	}
	
	 /**
	  * use getter methods to get value of each instant variables that we need from HashMap above	 
	  */
	
	
	/**
	 * This method read over the map one key at a time and use getter methods to get value of each 
	 * instant variables that we need from HashMap above to:
	 * 1. assign city and highway MPG to both Lexus and Mercedes
	 * 2. assign major city (Charlotte, New York and Miami) regular and premius price
	 */
	public void ICEMPG() {
		for (int index : gasMap.keySet()) {
			
			ICEUsage iceInformation = gasMap.get(index);
			String currentmodel = iceInformation.getModel();
			String currentcity = iceInformation.getCity();
			double currentregularGas = iceInformation.getRegularGas();
			double currentpremiumGas = iceInformation.getPremiumGas();
			double currentcityMPG = iceInformation.getcityMPG();
			double currenthwyMPG = iceInformation.gethwyMPG();
			
			//If vehicle model is Lexus LX, then find the MPG of both City and Hwy.
			if (currentmodel.equals("LEXUS LX")) {
				LexusCityMPG = currentcityMPG;
				LexusHwyMPG = currenthwyMPG;
			}
			//If vehicle model is Mercedes-Benz GLC, then find the MPG of both City and Hwy.
			if (currentmodel.equals("MERCEDES-BENZ GLC")) {
				MercedesCityMPG = currentcityMPG;
				MercedesHwyMPG = currenthwyMPG;
			}
			//If city is Charlotte, then find the regular and premium gas price for Charlotte.
			if (currentcity.equals("Charlotte")) {
				CharlotteRegularGas = currentregularGas;
				CharlottePremiumGas = currentpremiumGas;
			}
			//If city is New York, then find the regular and premium gas price for New York.
			if (currentcity.equals("New York")) {
				NewYorkRegularGas = currentregularGas;
				NewYorkPremiumGas = currentpremiumGas;
			}
			//If city is Miami, then find the regular and premium gas price for Miami.
			if (currentcity.equals("Miami")) {
				MiamiRegularGas = currentregularGas;
				MiamiPremiumGas = currentpremiumGas;
			}
		}
	}

}

