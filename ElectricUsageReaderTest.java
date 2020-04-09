import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ElectricUsageReaderTest {

	@Test
	void testMonthlyUsage() {
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
	void testMonthlyCost() {
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
}