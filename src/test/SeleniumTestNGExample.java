package test;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.HashMap;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTestNGExample {

	public RemoteWebDriver driver = null;
	String username = System.getenv("LT_USERNAME") == null ? "<lambdatest_username>" : System.getenv("LT_USERNAME");
   	String accessKey = System.getenv("LT_ACCESS_KEY") == null ? "<lambdatest_accesskey>" : System.getenv("LT_ACCESS_KEY");

	@BeforeSuite
	public void setUp() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPlatformName("Windows 10");
		chromeOptions.setBrowserVersion("121.0");
		
		HashMap<String, Object> ltOptions = new HashMap<String, Object>();
		ltOptions.put("username", username);
		ltOptions.put("accessKey", accessKey);
		ltOptions.put("project", "TestNG Selenium");
		ltOptions.put("build", "TestNG Selenium Demonstration");
		ltOptions.put("selenium_version", "4.0.0");
		chromeOptions.setCapability("LT:Options", ltOptions);

		try {
			driver = new RemoteWebDriver(
					new URL("https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub"), chromeOptions);
		} catch (MalformedURLException e) {
			System.out.println("Invalid grid URL");
		}
		System.out.println("The driver setup process is completed using BeforeSuite");
	}

	@BeforeTest
	public void browserProfile() {
		driver.manage().window().maximize();
		System.out.println("The browser profile is updated using BeforeTest");

	}

	@BeforeClass
	public void navigateToUrl() {
		driver.get("https://www.lambdatest.com/selenium-playground/simple-form-demo");
		System.out.println("Navigated to URL using BeforeClass");
	}

	@Test(description = "Test case with priority", priority = 1)
	public void testPriotity()
	{
		System.out.println("This case has priority 1");
	}
	
	@Test(description = "Test case with priority and invocation count", priority = 2, invocationCount = 2)
	public void testInvocationCount()
	{
		System.out.println("This case has priority 2 and invocation count");
	}
	
	
	@DataProvider(name = "SanityTestData")
	public Object[][] sanityTestDataProvider() {
		String[][] testData = { { "1", "4", "5" }, { "2", "4", "7" } };
		return testData;
	}
	
	@Test(dataProvider = "SanityTestData", description = "Test case with group and data provider but without priority", alwaysRun = true, groups = { "sanity" })
	public void testSumOfTwoValues(String firstValue, String secondValue, String expectedSum) {
		// to enter data and submit
		driver.findElement(By.id("sum1")).sendKeys(firstValue);
		driver.findElement(By.id("sum2")).sendKeys(secondValue);
		driver.findElement(By.xpath("//button[text()='Get Sum']")).click();

		// to fetch actual result
		String actualSum = driver.findElement(By.id("addmessage")).getText();

		// to assert actual and expected result
		Assert.assertEquals(actualSum, expectedSum,
				"Expected and actual results do not match. Expected : " + expectedSum + " and Actual : " + actualSum);

	}

	@AfterMethod()
	public void clearOldData() {
		driver.findElement(By.id("sum1")).clear();
		driver.findElement(By.id("sum2")).clear();
	}

	@AfterClass
	public void closeDriver() {
		driver.quit();
		System.out.println("Quit the driver using AfterClass");
	}

	@AfterTest
	public void testReporting() {
		System.out.println("Test Report generated using AfterTest");
	}
}
