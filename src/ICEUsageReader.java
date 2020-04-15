package team6Final; 

import java.util.*;
import java.io.*;

public class ICEUsageReader {
	/**
	 * Initializes several instance variables, which can be used in other classes.
	 * City and highway MPG of both Mercedes GLC and Lexus LX vehicles defined.
	 * Regular and premium gas prices for three different cities are defined.
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
	 * create one HashMap to store data through csv files, and convert data to int
	 * or double.
	 */
	private HashMap<Integer, ICEUsage> gasMap;

	/**
	 * Constructor reads in the given file, parses the data and populates the
	 * ICEUsage class with values.
	 * 
	 * @param filename
	 */
	public ICEUsageReader(String filename) {
		File file = new File(filename);
		gasMap = new HashMap<Integer, ICEUsage>();
		try {
			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(file); // create scanner object
			scanner.nextLine(); // read and discard the first line (header of csv)
			while (scanner.hasNextLine()) { // while there is a next line
				String ICERow = scanner.nextLine(); // store the current line in a String
				String[] columnData = ICERow.split(","); // parse by the comma
				String model = columnData[2];
				String city = columnData[4];
				double regularGas = Double.parseDouble(columnData[6]);
				double premiumGas = Double.parseDouble(columnData[8]);
				double cityMPG = Double.parseDouble(columnData[9]);
				double hwyMPG = Double.parseDouble(columnData[10]);
				// Create an ICEUsage object and initialize the values
				ICEUsage ice = new ICEUsage(model, city, regularGas, premiumGas, cityMPG, hwyMPG);
				gasMap.put(Integer.parseInt(columnData[0]), ice); // populate the gasMap hasmap
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			
		}
	}

	/**
	 * This method iterates over the map one key at a time and uses getter methods
	 * to retrieve values for each instance variable to: 1. assign city and highway
	 * MPG to both Lexus LX and Mercedes GLC ICE vehicles 2. assign major city's
	 * (Charlotte, New York and Miami) average regular and premium gasoline prices.
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

			/**
			 * If vehicle model is Lexus LX, then find the MPG of both City and Hwy.
			 */
			if (currentmodel.equals("LEXUS LX")) {
				LexusCityMPG = currentcityMPG;
				LexusHwyMPG = currenthwyMPG;
			}
			/**
			 * If vehicle model is Mercedes-Benz GLC, then find the MPG of both City and
			 * Hwy.
			 */
			if (currentmodel.equals("MERCEDES-BENZ GLC")) {
				MercedesCityMPG = currentcityMPG;
				MercedesHwyMPG = currenthwyMPG;
			}
			/**
			 * If city is Charlotte, then find the regular and premium gas price for
			 * Charlotte.
			 */
			if (currentcity.equals("Charlotte")) {
				CharlotteRegularGas = currentregularGas;
				CharlottePremiumGas = currentpremiumGas;
			}
			/**
			 * If city is New York, then find the regular and premium gas price for New
			 * York.
			 */
			if (currentcity.equals("New York")) {
				NewYorkRegularGas = currentregularGas;
				NewYorkPremiumGas = currentpremiumGas;
			}
			/**
			 * If city is Miami, then find the regular and premium gas price for Miami.
			 */
			if (currentcity.equals("Miami")) {
				MiamiRegularGas = currentregularGas;
				MiamiPremiumGas = currentpremiumGas;
			}
		}
	}

}
