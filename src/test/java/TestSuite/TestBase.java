package TestSuite;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class TestBase {

	//Declaring all Variable forProperties, WebDriver and Extent Report
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	//Declaring all Variable WebDriver
	public WebDriver driver;
	//Declaring all Variable properties file
	Properties prop = new Properties();
	//setting up sDefault project level Timeouts for Page load and implicit waits.
	public static long PAGE_LOAD_TIMEOUT=20;
	public static long IMPLICIT_WAIT=20;

	/*
	 * before suite level initilizing Extent Report and Capabilities  and property file
	 */
	@BeforeSuite(alwaysRun = true)
	public void beforeSuite() throws IOException {
		extent = ExtentManager.createInstance(getReport()+"GKQA.html");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(getReport()+"GKQA.html");
		extent.setSystemInfo("Host Environment", "GK QA");
		extent.setSystemInfo("Host Name", "Venkat");
		extent.setSystemInfo("Company", "GK");
		extent.setSystemInfo("Machine Name", "LocalHost");
		extent.attachReporter(htmlReporter);
		
	}

	//Before Every class triggering My test will get the class name and append to to the report
	@BeforeClass(alwaysRun = true)
	public synchronized void beforeClass() {
		ExtentTest parent = extent.createTest(getClass().getName());
		parentTest.set(parent);
	}
	//Before Every class triggering My test will get the Test name and append to to the report
	@BeforeMethod(alwaysRun = true)
	public synchronized void beforeMethod(Method method) throws IOException {
		ExtentTest child = parentTest.get().createNode(method.getName());
		test.set(child);
		
		FileInputStream fis = new FileInputStream("src\\main\\resources\\Resources\\Config.properties");
		prop.load(fis);
		Initialization();
	}

	/*
	 * After test method depends on the test results  Status will append to the test then driver will exit from test
	 */

	@AfterMethod(alwaysRun = true)
	public synchronized void afterMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE)
			test.get().fail(result.getThrowable());
		else if (result.getStatus() == ITestResult.SKIP)
			test.get().skip(result.getThrowable());
		else
			test.get().pass("Test passed");

		;
		extent.flush();
		driver.close();
	}
	
	/*
	 * This folder save the reports  location 
	 */
	protected String getReport() { 
		return "TestReport/";
	}

	/*
	 * 
	 * Depends on the properties configured in the config file browser will invoke and run the tests
	 */
	public void Initialization()
	{

		String browserName = prop.getProperty("browser");
		if(browserName.equals("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver", prop.getProperty("Chromedriverpath"));
			driver= new ChromeDriver();
		}else
			if(browserName.equals("FireFox"))
			{
				System.setProperty("webdriver.gecko.driver", prop.getProperty("Firefoxdriverpath"));
				driver= new ChromeDriver();
			}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);		
		driver.get(prop.getProperty("URL"));
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		System.out.println("page getting launched");
	}



}

