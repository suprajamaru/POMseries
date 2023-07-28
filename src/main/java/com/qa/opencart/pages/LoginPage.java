package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
private WebDriver driver;
private ElementUtil eleutil;
//1.const.of the pageclass
public LoginPage(WebDriver driver) {
	this.driver=driver;
	eleutil=new ElementUtil(this.driver);
	
}
//2.private By locators:
private By emailid=By.id("input-email");
private By password=By.id("input-password");
private By forgotpasswordlink=By.linkText("Forgotten Password");
private By loginbtn=By.xpath("//input[@type='submit']");
private By footerlinks=By.xpath("//footer//a");
private By search=By.name("search");
private By searchbtn=By.xpath("//button/i[@class='fa fa-search']");
private By loginerrormsg=By.cssSelector("div.alert.alert-danger.alert-dismissible");
private By Registerlink=By.linkText("Register");
@Step("getting login page title")
//3.public page actions/methods
public String getLoginPageTitle() {
	return eleutil.waitfortitleandCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE,AppConstants.SHORT_DEFAULT_WAIT);
	}
@Step("getting login page url")
public String getLoginPageURL() {
	return eleutil.waitforUrlcontains(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE,AppConstants.MEDIUM_DEFAULT_WAIT);
}
@Step("checking forgot password link exist on the login page")
public Boolean isForgotPwdLinkExist() {
	return eleutil.CheckElementIsDisplayed(forgotpasswordlink);
}
@Step("getting footer links list")
public List<String> getFooterLinksList() {
	List<WebElement>footerlinkslist=eleutil.waitForAllElementsVisible(footerlinks,AppConstants.MEDIUM_DEFAULT_WAIT);
	List<String>footertextlist=new ArrayList<String>();
	for(WebElement e:footerlinkslist) {
		String text=e.getText();
		footertextlist.add(text);
	}
	return footertextlist;

}
@Step("login with username {0} and password {1} ")
public AccountsPage dologin(String userName,String pswrd) {
	eleutil.waitForElementVisible(emailid,AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(userName);
	eleutil.doSendkeys(password, pswrd);
	eleutil.doClick(loginbtn);
	
	//return the next landing page Accountspage by using page chaining model
	return new AccountsPage(driver);
}
@Step("login with wrong username {0} and password {1}")
public Boolean dologinwithWrongCredentials(String userName,String pswrd) {
	System.out.println("wrong credentials are:"+userName+":"+pswrd);
	eleutil.waitForElementVisible(emailid, AppConstants.MEDIUM_DEFAULT_WAIT);
	eleutil.doSendkeys(emailid, userName);
	eleutil.doSendkeys(password, pswrd);
	eleutil.doClick(loginbtn);
	String errormessage=eleutil.getElementText(loginerrormsg);
	System.out.println(errormessage);
	if(errormessage.contains(AppConstants.LOGINPAGE_ERRORMESSAGE)) {
		return true;
	}
	return false;
}
@Step("Navigate to Registration Page")
public RegistrationPage navigateToRegisterPage() {
	eleutil.doClick(Registerlink);
	return new RegistrationPage(driver);
}
}
	










