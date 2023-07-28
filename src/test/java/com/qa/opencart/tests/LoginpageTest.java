package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic100:LoginPage Design")
@Story("US 101:Design loginpage for opencart app with title,url,forgot pwd links,user is able to login")
public class LoginpageTest extends BaseTest {
private WebDriver driver;
//LoginPage loginpage;
@Severity(SeverityLevel.NORMAL)
@Description("checking login page title test")
@Feature("login page titletest")
@Test
public void loginPageTitleTest() {
	String acttitle=loginpage.getLoginPageTitle();
	Assert.assertEquals(acttitle,AppConstants.LOGIN_PAGE_TITLE_VALUE);
}
@Severity(SeverityLevel.NORMAL)
@Description("checking login page url test")
@Feature("URL test")
@Test(priority=1)
public void loginpageUrlTest() {
	String acturl=loginpage.getLoginPageURL();
	Assert.assertTrue(acturl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE));
}
@Severity(SeverityLevel.CRITICAL)
@Description("checking forgot password link test")
@Feature("Forgot password link test")
@Test(priority=2)
public void forgotpwdLinkExistTest() {
	Assert.assertTrue(loginpage.isForgotPwdLinkExist());
}
@Severity(SeverityLevel.NORMAL)
@Description("checking login page footerlink test")
@Feature("Footerlink test")
@Test(priority=3)
public void footerLinkListTest() {
	Assert.assertTrue(loginpage.getFooterLinksList().contains("About Us"));
}
@Severity(SeverityLevel.BLOCKER)
@Description("checking user is able to login or not with correct username/password")
@Feature("login test")
@Test(priority=4)

public void loginTest() {
	accpage=loginpage.dologin(prop.getProperty("username"),prop.getProperty("password"));
	Assert.assertTrue(accpage.islogoutLinkExist());
//	Assert.assertTrue(accpage.getAccountPageTitle().equals("My Account"));

}




}
