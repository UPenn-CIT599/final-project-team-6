/**
* This class calculates the values associated with electricity and gasoline costs in three cities using defined formulae
*/
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.*;

public class RateCalculator {

	// set the output to print 2 digits only
	private static DecimalFormat df2 = new DecimalFormat("#.##");
	/**
	 * Initialize several instance variables, which can be used in other classes
	 * EVMPKwh = EV's miles / kwh. CharlotteERate = 6 months Charlotte's average
	 * electricity rate in dollar. NewYorkERate = 3 year New York's average
	 * electricity rate in dollar. MiamiERate = 3 year Miami's average electricity
	 * rate in dollar. mile = user's driving mileage in one year year = the total
	 * year that user wants to drive. hwyPercentage = % of hwy driving. If mile =
	 * 10,000, hwyPercentage = 70%, mean user will drive 7,000 miles hwy and 3,000
	 * miles local in one year.
	 */
	double EVMPkwh = 0.0;
	double CharlotteERate = 0.0;
	double NewYorkERate = 0.0;
	double MiamiERate = 0.0;
	double miles = 0;
	double year = 0;
	double hwyPercentage = 0.0;

	/**
	 * This method will take 2 arraylists (before EV bill and after EV bill) to find
	 * out how many electricity will consume in 6 months Then use total driving
	 * miles in 6 months / total consume electricity and return mile / kwh
	 * 
	 * @param ElectricityUsageBeforeEV
	 * @param ElectricityUsageAfterEV
	 * @return mile / kwh
	 */
	public double EV_MPkwh(ArrayList<Integer> ElectricityUsageBeforeEV, ArrayList<Integer> ElectricityUsageAfterEV) {
		double totalMilesForSixMonths = 7468.0;
		int sumOfDifference = 0;
		for (int i = 0; i < ElectricityUsageBeforeEV.size(); i++) {
			sumOfDifference = sumOfDifference + ElectricityUsageAfterEV.get(i) - ElectricityUsageBeforeEV.get(i);
		}
		EVMPkwh = totalMilesForSixMonths / sumOfDifference;
		return EVMPkwh;
	}

	/**
	 * This method will take 2 arraylists (after EV usage and after EV cost) to find
	 * out how many electricity will consume in 6 months and how much dollar cost.
	 * Then return Charlotte average $ / kwh.
	 * 
	 * @param ElectricityUsageCost
	 * @param ElectricityUsageAfterEV
	 * @return Charlotte average $ / kwh
	 */
	public double CharlotteElectrcityRate(ArrayList<Double> ElectricityUsageCost,
			ArrayList<Integer> ElectricityUsageAfterEV) {
		double sumOfCost = 0.0;
		int sumOfUsage = 0;
		for (int i = 0; i < ElectricityUsageAfterEV.size(); i++) {
			sumOfUsage = sumOfUsage + ElectricityUsageAfterEV.get(i);
			sumOfCost = sumOfCost + ElectricityUsageCost.get(i);
		}
		CharlotteERate = sumOfCost / sumOfUsage;
		return CharlotteERate;
	}

	/**
	 * @return average electricity cost for 3 years for New York
	 */
	public double NewYorkElectrcityRate() {
		double NewYorkERate2017 = 0.1803;
		double NewYorkERate2018 = 0.21;
		double NewYorkERate2019 = 0.201;
		NewYorkERate = (NewYorkERate2017 + NewYorkERate2018 + NewYorkERate2019) / 3;
		return NewYorkERate;
	}

	/**
	 * @return average electricity cost for 3 years for Miami.
	 */
	public double MiamiElectrcityRate() {
		double MiamiERate2017 = 0.119;
		double MiamiERate2018 = 0.119;
		double MiamiERate2019 = 0.121;
		MiamiERate = (MiamiERate2017 + MiamiERate2018 + MiamiERate2019) / 3;
		return MiamiERate;
	}

	/**
	 * This method get inputs from user and return the data comparison between
	 * Tesla, Mercedes,and Lexus in Charlotte, New York, Miami.
	 * 
	 * @param miles
	 * @param year
	 * @param hwyPercentage
	 */
	double CharlotteOneYearEV;
	double CharlotteUserYearEV;
	double NewYorkOneYearEV;
	double NewYorkUserYearEV;
	double MiamiOneYearEV;
	double MiamiUserYearEV;

	public void CostComparsion(double miles, double year, double hwyPercentage) {
		this.miles = miles;
		this.year = year;
		this.hwyPercentage = hwyPercentage;

		CharlotteOneYearEV = miles / EVMPkwh * CharlotteERate;
		CharlotteUserYearEV = CharlotteOneYearEV * year;
		NewYorkOneYearEV = miles / EVMPkwh * NewYorkERate;
		NewYorkUserYearEV = NewYorkOneYearEV * year;
		MiamiOneYearEV = miles / EVMPkwh * MiamiERate;
		MiamiUserYearEV = MiamiOneYearEV * year;

		ICEUsageReader icedata = new ICEUsageReader("Gas_Data_EastCoastOnly.csv");
		icedata.ICEMPG();
		double CharlotteOneYearMercedes = (miles * hwyPercentage) / icedata.MercedesHwyMPG * icedata.CharlottePremiumGas
				+ (miles * (1 - hwyPercentage)) / icedata.MercedesCityMPG * icedata.CharlottePremiumGas;
		double CharlotteUserYearMercedes = CharlotteOneYearMercedes * year;
		double NewYorkOneYearMercedes = (miles * hwyPercentage) / icedata.MercedesHwyMPG * icedata.NewYorkPremiumGas
				+ (miles * (1 - hwyPercentage)) / icedata.MercedesCityMPG * icedata.NewYorkPremiumGas;
		double NewYorkUserYearMercedes = NewYorkOneYearMercedes * year;
		double MiamiOneYearMercedes = (miles * hwyPercentage) / icedata.MercedesHwyMPG * icedata.MiamiPremiumGas
				+ (miles * (1 - hwyPercentage)) / icedata.MercedesCityMPG * icedata.MiamiPremiumGas;
		double MiamiUserYearMercedes = MiamiOneYearMercedes * year;

		double CharlotteOneYearLexus = (miles * hwyPercentage) / icedata.LexusHwyMPG * icedata.CharlotteRegularGas
				+ (miles * (1 - hwyPercentage)) / icedata.LexusCityMPG * icedata.CharlotteRegularGas;
		double CharlotteUserYearLexus = CharlotteOneYearLexus * year;
		double NewYorkOneYearLexus = (miles * hwyPercentage) / icedata.LexusHwyMPG * icedata.NewYorkRegularGas
				+ (miles * (1 - hwyPercentage)) / icedata.LexusCityMPG * icedata.NewYorkRegularGas;
		double NewYorkUserYearLexus = NewYorkOneYearLexus * year;
		double MiamiOneYearLexus = (miles * hwyPercentage) / icedata.LexusHwyMPG * icedata.MiamiRegularGas
				+ (miles * (1 - hwyPercentage)) / icedata.LexusCityMPG * icedata.MiamiRegularGas;
		double MiamiUserYearLexus = MiamiOneYearLexus * year;

		// calculates the max saving and city compares driving EV and ICE.
		String[] citylist = { "Charlotte", "New York", "Miami" };
		double[][] comparelist = { { CharlotteOneYearEV, CharlotteOneYearMercedes, CharlotteOneYearLexus },
				{ NewYorkOneYearEV, NewYorkOneYearMercedes, NewYorkOneYearLexus },
				{ MiamiOneYearEV, MiamiOneYearMercedes, MiamiOneYearLexus } };

		double temp = 0.0;
		double maxSaving = 0.0;
		int maxIndex = 0;
		for (int i = 0; i < 3; i++) {
			temp = Math.max(comparelist[i][1] - comparelist[i][0], comparelist[i][2] - comparelist[i][0]);
			maxSaving = temp;
			maxIndex = i;
		}

		/**
		 * Uses national average data to compare Tesla, Mercedes and Lexus cost for 15,000 annual
		 * miles/
		 */
		double threeYearAverageCostOfPremium = 3.07;
		double currentNationalCostOfRegular = 2.24;
		double combinedMpg = 14;
		double averageMilesDrivenAnnually = 15000;
		double currentNationalCostOfElectricity = 0.1279;
		double teslAverageUsage = 3.84;

		double TeslaCost = averageMilesDrivenAnnually / teslAverageUsage * currentNationalCostOfElectricity;
		double MercedesCost = averageMilesDrivenAnnually / combinedMpg * threeYearAverageCostOfPremium;
		double LexusCost = averageMilesDrivenAnnually / combinedMpg * currentNationalCostOfRegular;

		/**
		 * Answers select questions and outputs answers in one txt file.
		 */

		ArrayList<String> answers = new ArrayList<>();

		answers.add("If you live in Charlotte, Tesla Model 3 will cost you $" + df2.format(CharlotteOneYearEV)
				+ " in 1 year; $" + df2.format(CharlotteUserYearEV) + " for " + year + " years.");
		answers.add("If you live in Charlotte, Lexus will cost you $" + df2.format(CharlotteOneYearLexus)
				+ " in 1 year; $" + df2.format(CharlotteUserYearLexus) + " for " + year + " years.");
		answers.add("If you live in Charlotte, Mercedes will cost you $" + df2.format(CharlotteOneYearMercedes)
				+ " in 1 year; $" + df2.format(CharlotteUserYearMercedes) + " for " + year + " years.");
		answers.add("If you live in New York, Tesla Model 3 will cost you $" + df2.format(NewYorkOneYearEV)
				+ " in 1 year; $" + df2.format(NewYorkUserYearEV) + " for " + year + " years.");
		answers.add("If you live in New York, Lexus will cost you $" + df2.format(NewYorkOneYearLexus) + " in 1 year; $"
				+ df2.format(NewYorkUserYearLexus) + " for " + year + " years.");
		answers.add("If you live in New York, Mercedes will cost you $" + df2.format(NewYorkOneYearMercedes)
				+ " in 1 year; $" + df2.format(NewYorkUserYearMercedes) + " for " + year + " years.");
		answers.add("If you live in Miami, Tesla Model 3 will cost you $" + df2.format(MiamiOneYearEV) + " in 1 year; $"
				+ df2.format(MiamiUserYearEV) + " for " + year + " years.");
		answers.add("If you live in Miami, Lexus will cost you $" + df2.format(MiamiOneYearLexus) + " in 1 year; $"
				+ df2.format(MiamiUserYearLexus) + " for " + year + " years.");
		answers.add("If you live in Miami, Mercedes will cost you $" + df2.format(MiamiOneYearMercedes) + " in 1 year; $"
				+ df2.format(MiamiUserYearMercedes) + " for " + year + " years.");
		answers.add("\n");
		answers.add("If you drive Tesla, you will save the most in " + citylist[maxIndex] + " city, total saving is $"
				+ df2.format(maxSaving) + " in 1 year.");

		answers.add("\n");
		answers.add("In comparsion, the national data show the average cost in 1 year:");
		answers.add("Tesla Model 3 will cost $" + df2.format(TeslaCost) + ".");
		answers.add("Mercedes Benz GLC will cost $" + df2.format(MercedesCost) + ".");
		answers.add("Lexus LX will cost $" + df2.format(LexusCost) + ".");
		answers.add("Tesla will save " + df2.format(100 * (1 - (TeslaCost / MercedesCost)))
				+ "% compare with Mercedes Benz.");
		answers.add("Tesla will save " + df2.format(100 * (1 - (TeslaCost / LexusCost))) + "% compare with Lexus.");

		FormattedOutput outputFile = new FormattedOutput(answers);
		outputFile.writeAnswers();
	}

	/**
	 * Retrieves all EV rates for One Year in Charlotte.
	 * @return
	 */
	public double getCharlotteOneYearEV() {
		return CharlotteOneYearEV;
	}
	/**
	 * Retrieves all EV rates for One User in Charlotte.
	 * @return
	 */
	public double getCharlotteUserYearEV() {
		return CharlotteUserYearEV;
	}
	/**
	 * Retrieves all EV rates for One Year in New York.
	 * @return
	 */
	public double getNewYorkOneYearEV() {
		return NewYorkOneYearEV;
	}
	/**
	 * Retrieves all EV rates for One User in New York.
	 * @return
	 */
	public double getNewYorkUserYearEV() {
		return NewYorkUserYearEV;
	}
	/**
	 * Retrieves all EV rates for One Year in Miami.
	 * @return
	 */
	public double getMiamiOneYearEV() {
		return MiamiOneYearEV;
	}
	/**
	 * Retrieves all EV rates for One User in Miami.
	 * @return
	 */
	public double getMiamiUserYearEV() {
		return MiamiUserYearEV;
	}

	/**
	 * This method will take user's input for a month, and return 2 arraylists which
	 * contain all daily cost of that particular month before and after EV charge.
	 * @param month = user's input of month
	 * @return array list of HashMaps: daily electricity cost with and without EV
	 */
	public ArrayList<HashMap<Integer, Double>> monthToPlot(String month) {

		ElectricUsageReader usageWithoutEV = new ElectricUsageReader("09-2018--03-2019.csv");
		ElectricUsageReader usageWithEV = new ElectricUsageReader("09-2019--03-2020.csv");
		usageWithoutEV.list();
		usageWithEV.list();
		usageWithoutEV.eachMonthlyCost();
		usageWithEV.eachMonthlyCost();

		String lowerCasemonth = month.toLowerCase(); // make sure that the input month is in lower case

		ArrayList<Double> nonEVPlot = new ArrayList<Double>();
		ArrayList<Double> EVPlot = new ArrayList<Double>();
		if (lowerCasemonth.equals("sep")) {
			nonEVPlot = usageWithoutEV.getSepCost();
			EVPlot = usageWithEV.getSepCost();
		}
		if (lowerCasemonth.equals("oct")) {
			nonEVPlot = usageWithoutEV.getOctCost();
			EVPlot = usageWithEV.getOctCost();
		}
		if (lowerCasemonth.equals("nov")) {
			nonEVPlot = usageWithoutEV.getNovCost();
			EVPlot = usageWithEV.getNovCost();
		}
		if (lowerCasemonth.equals("dec")) {
			nonEVPlot = usageWithoutEV.getDecCost();
			EVPlot = usageWithEV.getDecCost();
		}
		if (lowerCasemonth.equals("jan")) {
			nonEVPlot = usageWithoutEV.getJanCost();
			EVPlot = usageWithEV.getJanCost();
		}
		if (lowerCasemonth.equals("feb")) {
			nonEVPlot = usageWithoutEV.getFebCost();
			EVPlot = usageWithEV.getFebCost();
		}

		HashMap<Integer, Double> electricityCostWithEV = new HashMap<>();// Map of daily electricity cost
		// in a selected month, where key is the day and value is the electricity usage
		// cost that day with electric vehicle
		HashMap<Integer, Double> electricityCostWithoutEV = new HashMap<>();// Map of daily electricity cost
		// in a selected month, where key is the day and value is the electricity usage
		// cost that day without electric vehicle

		ArrayList<HashMap<Integer, Double>> data = new ArrayList<>(); // array list to contain the two
		// HashMaps: electricityCostWithEV and electricityCostWithoutEV

		for (int i = 0; i < EVPlot.size(); i++) {
			electricityCostWithEV.put(i + 1, EVPlot.get(i)); // create the map
			// key is day, so should be 1 indexed
		}

		for (int i = 0; i < nonEVPlot.size(); i++) {
			electricityCostWithoutEV.put(i + 1, nonEVPlot.get(i)); // create the map
			// key is day, so should be 1 indexed
		}

		data.add(electricityCostWithEV); // adding daily usage map to the array list
		data.add(electricityCostWithoutEV); // adding daily usage map to the array list
		return data;
	}

}

