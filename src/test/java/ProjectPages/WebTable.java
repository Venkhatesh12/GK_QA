package ProjectPages;
import static org.testng.Assert.assertEquals;

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
import TestSuite.TestBase;

public class WebTable extends TestBase
{
	public WebTable(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Objects Mapping
	@FindBy(how = How.XPATH, using = "//div[@class='modal ng-scope']") 
	private WebElement userForm;
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-link pull-right']") 
	private WebElement AddUsersbtn;
	@FindBy(how = How.NAME, using = "FirstName") 
	private WebElement firstnName;
	@FindBy(how = How.NAME, using = "LastName") 
	private WebElement lastName;
	@FindBy(how = How.NAME, using = "UserName") 
	private WebElement userName;
	@FindBy(how = How.NAME, using = "Password") 
	private WebElement password;
	@FindBy(how = How.XPATH, using = "//label[text()='Company AAA']//input[@name='optionsRadios']") 
	private WebElement companyAAA;
	@FindBy(how = How.XPATH, using = "//label[text()='Company BBB']//input[@name='optionsRadios']") 
	private WebElement companyBBB;	
	@FindBy(how = How.NAME, using = "RoleId") 
	private WebElement roles;
	@FindBy(how = How.NAME, using = "Email") 
	private WebElement email;
	@FindBy(how = How.NAME, using = "Mobilephone") 
	private WebElement phoneNumber;
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-success']") 
	private WebElement Submitbtn;
	@FindBy(how = How.XPATH, using = "//table//tbody//tr[1]//td[3]") 
	private WebElement AddedUserDetails;

	public void AddNewAdmin() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		Helpers.ExcelHelpers.setExcelFile(ExcelHelpers.Path_TestData+ExcelHelpers.File_TestData, "Users");
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(AddUsersbtn));
			AddUsersbtn.click();
			wait.until(ExpectedConditions.elementToBeClickable(userForm));
			int RowCount = Helpers.ExcelHelpers.getRowCount(ExcelHelpers.Path_TestData+ExcelHelpers.File_TestData, "Users");
			for(int i=1;i<=RowCount;i++)
			{			
				String NameF=ExcelHelpers.getCellData(i,0);
				firstnName.sendKeys(NameF);
				String NameL=ExcelHelpers.getCellData(i,1);
				lastName.sendKeys(NameL);				
				String NameU=ExcelHelpers.getCellData(i,2);
				userName.sendKeys(NameU);				
				String pwd=ExcelHelpers.getCellData(i,3);
				password.sendKeys(pwd);
				companyAAA.click();
				Select roleBase = new Select(driver.findElement(By.name("RoleId")));
				roleBase.selectByVisibleText("Admin");


				String emailadmin=ExcelHelpers.getCellData(i,4);
				email.sendKeys(emailadmin);
				String phoneN=ExcelHelpers.getCellData(i,5);
				phoneNumber.sendKeys(phoneN);
				Submitbtn.click();

				List<WebElement> userlist = driver.findElements(By.xpath("//table//tbody//tr"));
				int SizeOfusers = userlist.size();
				test.get().info("Size Of the Users are :"+SizeOfusers); 				
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				MediaEntityModelProvider AdminPage = MediaEntityBuilder.createScreenCaptureFromPath(TakeScrenShot(driver)).build();					
				test.get().log(Status.INFO,"Added Admin : ", AdminPage);
				Thread.sleep(5000);
				String getCustomerDetails = AddedUserDetails.getText();
				test.get().log(Status.INFO,"Added Admin Details are : "+getCustomerDetails);
				assertEquals(getCustomerDetails, "User1");
			}

		}
		catch(Exception e)
		{
			test.get().pass("Test FailedUser while Adding Admin"); 
		}

	}
	
	public void AddNewCustomer() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		Helpers.ExcelHelpers.setExcelFile(ExcelHelpers.Path_TestData+ExcelHelpers.File_TestData, "Users");
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(AddUsersbtn));
			AddUsersbtn.click();
			wait.until(ExpectedConditions.elementToBeClickable(userForm));
			int RowCount = Helpers.ExcelHelpers.getRowCount(ExcelHelpers.Path_TestData+ExcelHelpers.File_TestData, "Users");
			for(int i=2;i<=RowCount;i++)
			{			
				String NameF=ExcelHelpers.getCellData(i,0);
				firstnName.sendKeys(NameF);
				String NameL=ExcelHelpers.getCellData(i,1);
				lastName.sendKeys(NameL);				
				String NameU=ExcelHelpers.getCellData(i,2);
				userName.sendKeys(NameU);				
				String pwd=ExcelHelpers.getCellData(i,3);
				password.sendKeys(pwd);
				companyBBB.click();
				Select roleBase = new Select(driver.findElement(By.name("RoleId")));
				roleBase.selectByVisibleText("Customer");


				String emailadmin=ExcelHelpers.getCellData(i,4);
				email.sendKeys(emailadmin);
				String phoneN=ExcelHelpers.getCellData(i,5);
				phoneNumber.sendKeys(phoneN);
				Submitbtn.click();

				List<WebElement> userlist = driver.findElements(By.xpath("//table//tbody//tr"));
				int SizeOfusers = userlist.size();
				test.get().info("Size Of the Users are :"+SizeOfusers); 				
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				MediaEntityModelProvider AdminPage = MediaEntityBuilder.createScreenCaptureFromPath(TakeScrenShot(driver)).build();					
				test.get().log(Status.INFO,"Added Admin : ", AdminPage);
				Thread.sleep(5000);
				String getCustomerDetails = AddedUserDetails.getText();
				test.get().log(Status.INFO,"Added Customer Details are : "+getCustomerDetails);
				assertEquals(getCustomerDetails, "User2");
			}

		}
		catch(Exception e)
		{
			test.get().pass("Test FailedUser while Adding Admin"); 
		}

	}

	public void AddNewCustomerDetails() throws Exception
	{
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver,10);
		try
		{
			wait.until(ExpectedConditions.elementToBeClickable(AddUsersbtn));
			AddUsersbtn.click();
			wait.until(ExpectedConditions.elementToBeClickable(userForm));		
				firstnName.sendKeys("FName2");
				lastName.sendKeys("LName2");				
				userName.sendKeys("User2");				
				password.sendKeys("Pass2");
				companyBBB.click();
				Select roleBase = new Select(driver.findElement(By.name("RoleId")));
				roleBase.selectByVisibleText("Customer");
				email.sendKeys("Customer@Gmail.com");
				phoneNumber.sendKeys("A123");
				Submitbtn.click();

				List<WebElement> userlist = driver.findElements(By.xpath("//table//tbody//tr"));
				int SizeOfusers = userlist.size();
				test.get().info("Size Of the Users are :"+SizeOfusers); 				
				driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
				MediaEntityModelProvider AdminPage = MediaEntityBuilder.createScreenCaptureFromPath(TakeScrenShot(driver)).build();					
				test.get().log(Status.INFO,"Added Admin : ", AdminPage);
				Thread.sleep(5000);
				String getCustomerDetails = AddedUserDetails.getText();
				test.get().log(Status.INFO,"Added Customer Details are : "+getCustomerDetails);
				assertEquals(getCustomerDetails, "User2");

		}
		catch(Exception e)
		{
			test.get().pass("Test FailedUser while Adding Admin"); 
		}

	}

	public String TakeScrenShot(WebDriver driver) throws Exception 
	{
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File(getReport()+"ScreenShots/" + System.currentTimeMillis() + ".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		return errflpath;

	}

	
}
