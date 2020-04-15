package team6Final; 

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
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
	void TestEachMonthlyCost() {
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
	void TestmonthofPlot() {

		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator factor = new RateCalculator();
		TeslaUsage.list();
		NonTeslaUsage.list();
		TeslaUsage.eachMonthlyCost();
		NonTeslaUsage.eachMonthlyCost();
		factor.monthofPlot("sep");
		factor.monthofPlot("oct");
		factor.monthofPlot("nov");
		factor.monthofPlot("dec");
		factor.monthofPlot("jan");
		factor.monthofPlot("feb");

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
}
