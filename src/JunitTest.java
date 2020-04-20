import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

class JunitTest {

	@Test
	void TestMonthlyUsage() {
		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ArrayList<Integer> monthlyusage = new ArrayList<Integer>();
		monthlyusage.add(1027);
		monthlyusage.add(694);
		monthlyusage.add(706);
		monthlyusage.add(1123);
		monthlyusage.add(689);
		monthlyusage.add(734);
		TeslaUsage.list();
		TeslaUsage.MonthlyUsage();
		assertEquals(monthlyusage, TeslaUsage.getMonthlyusage());
	}

	@Test
	void TestMonthlyCost() {
		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ArrayList<Double> monthlycost = new ArrayList<Double>();
		monthlycost.add(94.63);
		monthlycost.add(63.92);
		monthlycost.add(65.38);
		monthlycost.add(103.55);
		monthlycost.add(63.44);
		monthlycost.add(67.67);
		TeslaUsage.list();
		TeslaUsage.MonthlyCost();
		assertEquals(monthlycost, TeslaUsage.getMonthlycost());
	}

	@Test
	void TestEachMonthlyCostFeb() {
		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		double[] monthofFebdouble = { 0.46, 0.92, 2.03, 2.03, 2.31, 1.38, 2.58, 2.58, 4.24, 2.21, 1.38, 1.75, 1.75, 3.6,
				3.96, 3.87, 1.29, 1.84, 3.04, 2.31, 2.21, 3.13, 2.4, 2.4, 1.94, 1.94, 4.89, 3.23 };
		ArrayList<Double> monthofFeb = new ArrayList<Double>();
		for (int i = 0; i < monthofFebdouble.length; i++) {
			monthofFeb.add(monthofFebdouble[i]);
		}
		TeslaUsage.list();
		TeslaUsage.eachMonthlyCost();

		assertEquals(monthofFeb, TeslaUsage.getFebCost());
	}

	@Test
	void TestEachMonthlyCostSep() {
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		double[] monthofSepdouble = { 1.49, 1.4, 6.88, 1.95, 2.6, 2.79, 3.16, 1.86, 1.86, 4.46, 2.79, 2.14, 1.3, 1.3,
				1.49, 1.49, 3.63, 1.77, 2.33, 2.33, 3.07, 1.21, 1.12, 2.05, 0.47, 0.47, 2.23, 0.56, 1.67, 1.58 };
		ArrayList<Double> monthofSep = new ArrayList<Double>();
		for (int i = 0; i < monthofSepdouble.length; i++) {
			monthofSep.add(monthofSepdouble[i]);
		}
		NonTeslaUsage.list();
		NonTeslaUsage.eachMonthlyCost();

		assertEquals(monthofSep, NonTeslaUsage.getSepCost());
	}

	@Test
	void TestEV_MPkwh() {
		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator factor = new RateCalculator();

		TeslaUsage.list();
		NonTeslaUsage.list();
		double TeslaEVM = factor.EV_MPkwh(NonTeslaUsage.usageofdata, TeslaUsage.usageofdata);
		double CharlotteElectricityRate = factor.CharlotteElectrcityRate(TeslaUsage.costofdata, TeslaUsage.usageofdata);

		assertEquals(3.625242718446602, TeslaEVM);
		assertEquals(0.09221596621757489, CharlotteElectricityRate);
	}

	@Test
	void TestCharlotteElectrcityRate() {
		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator factor = new RateCalculator();

		TeslaUsage.list();
		NonTeslaUsage.list();
		double CharlotteElectricityRate = factor.CharlotteElectrcityRate(TeslaUsage.costofdata, TeslaUsage.usageofdata);

		assertEquals(0.09221596621757489, CharlotteElectricityRate);
	}

	@Test
	void TestNewYorkElectrcityRate() {
		RateCalculator factor = new RateCalculator();
		assertEquals(0.19709999999999997, factor.NewYorkElectrcityRate());
	}

	@Test
	void TestMiamiElectrcityRate() {
		RateCalculator factor = new RateCalculator();
		assertEquals(0.11966666666666666, factor.MiamiElectrcityRate());
	}

	@Test
	void TestCostComparsion() {

		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator factor = new RateCalculator();
		TeslaUsage.list();
		NonTeslaUsage.list();
		factor.EV_MPkwh(NonTeslaUsage.usageofdata, TeslaUsage.usageofdata);
		factor.CharlotteElectrcityRate(TeslaUsage.costofdata, TeslaUsage.usageofdata);
		factor.NewYorkElectrcityRate();
		factor.MiamiElectrcityRate();
		factor.CostComparsion(15000, 7, 0.75);

		assertEquals(381.55776059494696, factor.getCharlotteOneYearEV());
		assertEquals(2670.9043241646286, factor.getCharlotteUserYearEV());
		assertEquals(815.5316014997321, factor.getNewYorkOneYearEV());
		assertEquals(5708.7212104981245, factor.getNewYorkUserYearEV());
		assertEquals(495.1392608462774, factor.getMiamiOneYearEV());
		assertEquals(3465.974825923942, factor.getMiamiUserYearEV());
	}

	@Test
	void TestmonthToPlotSep() {

		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator vehicleSpecification = new RateCalculator();
		TeslaUsage.list();
		NonTeslaUsage.list();
		TeslaUsage.eachMonthlyCost();
		NonTeslaUsage.eachMonthlyCost();
		vehicleSpecification.monthToPlot("sep");

		double[] NmonthofSep = { 1.49, 1.4, 6.88, 1.95, 2.6, 2.79, 3.16, 1.86, 1.86, 4.46, 2.79, 2.14, 1.3, 1.3, 1.49,
				1.49, 3.63, 1.77, 2.33, 2.33, 3.07, 1.21, 1.12, 2.05, 0.47, 0.47, 2.23, 0.56, 1.67, 1.58 };
		ArrayList<Double> NMO = new ArrayList<Double>();
		for (int i = 0; i < NmonthofSep.length; i++) {
			NMO.add(NmonthofSep[i]);
		}
		double[] EmonthofSep = { 2.51, 1.75, 3.97, 3.32, 3.5, 3.96, 3.87, 3.87, 3.87, 2.67, 2.49, 2.4, 2.4, 2.95, 2.95,
				2.95, 4.61, 3.05, 2.12, 3.41, 2.67, 3.5, 2.76, 3.23, 2.67, 3.12, 3.21, 3.96, 2.67, 4.22 };
		ArrayList<Double> EMO = new ArrayList<Double>();
		for (int i = 0; i < EmonthofSep.length; i++) {
			EMO.add(EmonthofSep[i]);
		}
		assertEquals(NMO, NonTeslaUsage.getSepCost());
		assertEquals(EMO, TeslaUsage.getSepCost());

	}

	@Test
	void TestmonthToPlotOct() {

		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator vehicleSpecification = new RateCalculator();
		TeslaUsage.list();
		NonTeslaUsage.list();
		TeslaUsage.eachMonthlyCost();
		NonTeslaUsage.eachMonthlyCost();
		vehicleSpecification.monthToPlot("oct");

		double[] NmonthofOct = { 2.42, 2.7, 0.56, 0.47, 0.47, 0.37, 0.47, 0.93, 3.16, 0.37, 0.56, 0.56, 1.02, 0.93,
				3.35, 1.86, 0.56, 1.58, 1.12, 0.56, 0.56, 2.05, 1.21, 0.56, 1.3, 0.37, 0.84, 0.74, 0.74, 0.28, 0.28 };
		ArrayList<Double> NMO = new ArrayList<Double>();
		for (int i = 0; i < NmonthofOct.length; i++) {
			NMO.add(NmonthofOct[i]);
		}
		double[] EmonthofOct = { 0.46, 0.74, 0.83, 0.92, 2.67, 2.58, 3.96, 3.22, 1.47, 1.47, 2.67, 4.33, 4.33, 0.37,
				1.11, 1.29, 1.38, 3.41, 3.22, 0.92, 1.93, 2.86, 3.68, 2.12, 2.12, 0.83, 0.83, 0.83, 1.57, 2.12, 3.68 };
		ArrayList<Double> EMO = new ArrayList<Double>();
		for (int i = 0; i < EmonthofOct.length; i++) {
			EMO.add(EmonthofOct[i]);
		}
		assertEquals(NMO, NonTeslaUsage.getOctCost());
		assertEquals(EMO, TeslaUsage.getOctCost());

	}

	@Test
	void TestmonthToPlotNov() {

		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator vehicleSpecification = new RateCalculator();
		TeslaUsage.list();
		NonTeslaUsage.list();
		TeslaUsage.eachMonthlyCost();
		NonTeslaUsage.eachMonthlyCost();
		vehicleSpecification.monthToPlot("nov");

		double[] NmonthofNov = { 1.67, 0.65, 1.67, 1.12, 1.12, 2.05, 0.74, 0.56, 1.12, 1.67, 1.67, 3.07, 1.67, 1.02,
				0.37, 0.37, 0.37, 0.28, 0.37, 0.37, 0.65, 0.47, 0.37, 0.28, 0.28, 0.65, 0.37, 1.02, 1.49, 1.77 };
		ArrayList<Double> NMO = new ArrayList<Double>();
		for (int i = 0; i < NmonthofNov.length; i++) {
			NMO.add(NmonthofNov[i]);
		}
		double[] EmonthofNov = { 0.65, 0.28, 0.28, 0.74, 0.65, 0.37, 0.28, 0.46, 0.55, 0.55, 3.6, 3.5, 4.98, 3.5, 3.23,
				3.13, 3.13, 3.13, 2.86, 3.6, 2.86, 2.77, 1.75, 1.75, 3.23, 0.55, 3.04, 3.69, 3.04, 3.23 };
		ArrayList<Double> EMO = new ArrayList<Double>();
		for (int i = 0; i < EmonthofNov.length; i++) {
			EMO.add(EmonthofNov[i]);
		}
		assertEquals(NMO, NonTeslaUsage.getNovCost());
		assertEquals(EMO, TeslaUsage.getNovCost());

	}

	@Test
	void TestmonthToPlotDec() {

		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator vehicleSpecification = new RateCalculator();
		TeslaUsage.list();
		NonTeslaUsage.list();
		TeslaUsage.eachMonthlyCost();
		NonTeslaUsage.eachMonthlyCost();
		vehicleSpecification.monthToPlot("dec");

		double[] NmonthofDec = { 0.93, 0.93, 2.05, 2.05, 1.86, 2.05, 2.14, 1.12, 1.02, 3.35, 1.67, 1.86, 0.93, 0.84,
				4.28, 2.33, 1.58, 1.3, 2.14, 1.77, 1.21, 1.67, 1.58, 4.37, 1.95, 2.23, 3.04, 5.26, 1.94, 2.4, 3.04 };
		ArrayList<Double> NMO = new ArrayList<Double>();
		for (int i = 0; i < NmonthofDec.length; i++) {
			NMO.add(NmonthofDec[i]);
		}
		double[] EmonthofDec = { 3.23, 2.77, 4.43, 1.84, 2.95, 1.57, 0.28, 0.18, 5.53, 0.0, 3.32, 8.3, 3.13, 3.04, 3.04,
				2.77, 0.74, 2.12, 2.31, 2.21, 5.9, 8.85, 4.43, 2.49, 2.49, 2.31, 4.15, 3.41, 7.28, 3.32, 5.16 };
		ArrayList<Double> EMO = new ArrayList<Double>();
		for (int i = 0; i < EmonthofDec.length; i++) {
			EMO.add(EmonthofDec[i]);
		}
		assertEquals(NMO, NonTeslaUsage.getDecCost());
		assertEquals(EMO, TeslaUsage.getDecCost());

	}

	@Test
	void TestmonthToPlotJan() {

		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator vehicleSpecification = new RateCalculator();
		TeslaUsage.list();
		NonTeslaUsage.list();
		TeslaUsage.eachMonthlyCost();
		NonTeslaUsage.eachMonthlyCost();
		vehicleSpecification.monthToPlot("jan");

		double[] NmonthofJan = { 1.29, 0.46, 0.37, 0.28, 0.18, 0.46, 0.65, 0.46, 0.46, 0.28, 0.37, 0.37, 3.69, 1.48,
				1.57, 1.38, 1.29, 1.48, 1.38, 1.94, 2.58, 0.92, 0.65, 0.55, 3.04, 2.31, 2.31, 3.78, 2.21, 1.38, 2.12 };
		ArrayList<Double> NMO = new ArrayList<Double>();
		for (int i = 0; i < NmonthofJan.length; i++) {
			NMO.add(NmonthofJan[i]);
		}
		double[] EmonthofJan = { 1.38, 0.55, 0.55, 4.51, 4.42, 5.71, 2.21, 1.47, 0.37, 2.76, 2.12, 2.58, 2.76, 1.11,
				1.29, 1.38, 1.66, 1.2, 0.46, 1.66, 0.64, 1.38, 1.84, 2.3, 3.32, 4.14, 2.12, 0.83, 3.13, 1.75, 1.84 };
		ArrayList<Double> EMO = new ArrayList<Double>();
		for (int i = 0; i < EmonthofJan.length; i++) {
			EMO.add(EmonthofJan[i]);
		}
		assertEquals(NMO, NonTeslaUsage.getJanCost());
		assertEquals(EMO, TeslaUsage.getJanCost());

	}

	@Test
	void TestmonthToPlotFeb() {

		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator vehicleSpecification = new RateCalculator();
		TeslaUsage.list();
		NonTeslaUsage.list();
		TeslaUsage.eachMonthlyCost();
		NonTeslaUsage.eachMonthlyCost();
		vehicleSpecification.monthToPlot("feb");

		double[] NmonthofFeb = { 1.38, 2.77, 2.67, 4.15, 3.04, 0.83, 0.83, 2.86, 1.57, 1.48, 1.75, 1.75, 2.03, 1.94,
				2.86, 0.65, 0.65, 0.83, 0.28, 0.28, 0.74, 0.37, 0.46, 0.37, 0.46, 0.46, 0.28, 0.18 };
		ArrayList<Double> NMO = new ArrayList<Double>();
		for (int i = 0; i < NmonthofFeb.length; i++) {
			NMO.add(NmonthofFeb[i]);
		}
		double[] EmonthofFeb = { 0.46, 0.92, 2.03, 2.03, 2.31, 1.38, 2.58, 2.58, 4.24, 2.21, 1.38, 1.75, 1.75, 3.6,
				3.96, 3.87, 1.29, 1.84, 3.04, 2.31, 2.21, 3.13, 2.4, 2.4, 1.94, 1.94, 4.89, 3.23 };
		ArrayList<Double> EMO = new ArrayList<Double>();
		for (int i = 0; i < EmonthofFeb.length; i++) {
			EMO.add(EmonthofFeb[i]);
		}
		assertEquals(NMO, NonTeslaUsage.getFebCost());
		assertEquals(EMO, TeslaUsage.getFebCost());

	}
}