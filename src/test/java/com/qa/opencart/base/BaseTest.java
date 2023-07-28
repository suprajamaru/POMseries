package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	WebDriver driver;
	protected LoginPage loginpage;
	protected AccountsPage accpage;
	protected ResultsPage resultspage;
	protected DriverFactory df;
	protected Properties prop;
	protected ProductInfoPage productInfoPage;
	protected SoftAssert softassert;
	protected RegistrationPage registerpage;
	@Parameters({"browser"})
	@BeforeTest
	public void setup(String browserName) {
		df=new DriverFactory();
		prop=df.intiprop();
		if(browserName!=null) {
			prop.setProperty("browser",browserName);
		}
		driver=df.initDriver(prop);
		loginpage =new LoginPage(driver);
		softassert=new SoftAssert();
		}
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}
}
