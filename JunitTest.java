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
		
		assertEquals(monthlyusage,TeslaUsage.getMonthlyusage());		
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
		
		assertEquals(monthlycost,TeslaUsage.getMonthlycost());		
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
		
		assertEquals(3.625242718446602,TeslaEVM);	
		assertEquals(0.09221596621757489,CharlotteElectricityRate);
	}
	@Test
	void TestCharlotteElectrcityRate() {
		ElectricUsageReader TeslaUsage = new ElectricUsageReader("09-2019--03-2020.csv");
		ElectricUsageReader NonTeslaUsage = new ElectricUsageReader("09-2018--03-2019.csv");
		RateCalculator factor = new RateCalculator();
		
		TeslaUsage.list();
		NonTeslaUsage.list();
		double CharlotteElectricityRate = factor.CharlotteElectrcityRate(TeslaUsage.costofdata, TeslaUsage.usageofdata);
		
		assertEquals(0.09221596621757489,CharlotteElectricityRate);
	}
	@Test
	void TestNewYorkElectrcityRate() {
		RateCalculator factor = new RateCalculator();
		assertEquals(0.19709999999999997,factor.NewYorkElectrcityRate());
	}

	@Test
	void TestMiamiElectrcityRate() {
		RateCalculator factor = new RateCalculator();
		assertEquals(0.11966666666666666,factor.MiamiElectrcityRate());
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
		
		assertEquals(381.55776059494696,factor.getCharlotteOneYearEV());
		assertEquals(2670.9043241646286,factor.getCharlotteUserYearEV());
		assertEquals(815.5316014997321,factor.getNewYorkOneYearEV());
		assertEquals(5708.7212104981245,factor.getNewYorkUserYearEV());
		assertEquals(495.1392608462774,factor.getMiamiOneYearEV());
		assertEquals(3465.974825923942,factor.getMiamiUserYearEV());
	}
	
	
}