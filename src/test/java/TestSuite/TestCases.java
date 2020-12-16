package TestSuite;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import Helpers.ExcelHelpers;
import ProjectPages.WebTable;
import TestSuite.TestBase;

public class TestCases extends TestBase
{
	

	@Test
	public void TC01_AddNewAdmin() throws Exception
	{
		try
		{
			ProjectPages.WebTable AddAdmin = new WebTable(driver);
			AddAdmin.AddNewAdmin();
			test.get().pass("Add Admin Test execution Completed"); 
		}
		catch(Exception e)
		{
			test.get().fail("Test Failed while Adding Admin"+e); 
		}
	}



	@Test
	public void TC02_AddNewCustomer() throws Exception
	{
		try
		{
			ProjectPages.WebTable AddCust = new WebTable(driver);
			AddCust.AddNewCustomer();
			test.get().pass("Add Customer Test execution Completed"); 
		}
		catch(Exception e)
		{
			test.get().fail("Test Failed while Adding Customer"+e); 
		}
	}
	
	@Test
	public void TC03_AddNewCustomerKeyword() throws Exception
	{
		try
		{
			ProjectPages.WebTable AddCust = new WebTable(driver);
			AddCust.AddNewCustomerDetails();
			test.get().pass("Add Customer Test execution Completed"); 
		}
		catch(Exception e)
		{
			test.get().fail("Test Failed while Adding Customer"+e); 
		}
	}
}
