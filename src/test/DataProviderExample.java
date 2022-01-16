package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderExample {

	@DataProvider(name = "first-data-provider")
	public Object[][] dataProviderMethod()
	{
	return new Object[][] {{"One"}, {"Two"}};
	}

	@Test(dataProvider = "first-data-provider")
	public void dataProviderTest(String value)
	{
	System.out.println("Passed value is : " + value);
	}

}
