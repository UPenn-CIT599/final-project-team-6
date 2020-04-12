import java.util.*;
import java.io.*;

public class ICEUsageReader {
	/**
	 * Initializes several instance variables, which can be used in other classes.
	 * City and Highway MPG of both Mercedes GLC and Lexus LX vehicles defined.
	 * Regular Gas and Premium Gas Prices for three different cities defined.
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
	 * Creates one HashMap to store data through csv files, and convert data to integer or double.
	 */
	private HashMap<Integer, ICEUsagerev> gasMap;
	
	/**
	 * Constructor
	 * Reads in the given file, parses the data and populates the ICEUsagerev class with the data.
	 * @param file2
	 */
	public ICEUsageReader(String file2) {
		File file = new File(file2);
		gasMap = new HashMap<Integer, ICEUsagerev>();
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
				
				/**
				 * Creates an ICEUsagerev object and initializes the values.
				 */
				
				ICEUsagerev ice = new ICEUsagerev(model, city, regularGas, premiumGas, cityMPG, hwyMPG);
				/**
				 * Populates the gasMap hashmap.
				 */
				gasMap.put(Integer.parseInt(columnData[0]), ice); 
			}
		} catch (FileNotFoundException e) {
		}
	}
	/**
	 * Uses getter methods to retrieve values of each instant variable that we need from HashMap.
	 * @return 
	  */
	
	public void ICEUsageReader(File file) {
	}
	/**
	 * This method iterates over the map one key at a time and uses getter methods to retrieve values for each 
	 * instance variable to:
	 * 1. assign city and highway MPG to both Lexus LX and Mercedes GLC ICE vehicles, and
	 * 2. assign major city's (Charlotte, New York and Miami) average regular and premium gasoline prices.
	 */
	public void ICEMPG() {
		for (int index : gasMap.keySet()) {
			
			ICEUsagerev iceInformation = gasMap.get(index);
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
			 * If vehicle model is Mercedes GLC, then find the MPG of both City and Hwy.
			 */
			if (currentmodel.equals("MERCEDES-BENZ GLC")) {
				MercedesCityMPG = currentcityMPG;
				MercedesHwyMPG = currenthwyMPG;
			}
			/**
			 * If city is Charlotte, then find the regular and premium gas price for Charlotte NC.
			 */
			if (currentcity.equals("Charlotte")) {
				CharlotteRegularGas = currentregularGas;
				CharlottePremiumGas = currentpremiumGas;
			}
			/**
			 * If city is New York, then find the regular and premium gas price for New York, NY.
			 */
			if (currentcity.equals("New York")) {
				NewYorkRegularGas = currentregularGas;
				NewYorkPremiumGas = currentpremiumGas;
			}
			/**
			 * If city is Miami, then find the regular and premium gas price for Miami, FL.
			 */
			if (currentcity.equals("Miami")) {
				MiamiRegularGas = currentregularGas;
				MiamiPremiumGas = currentpremiumGas;
			}
		}
	}

}
