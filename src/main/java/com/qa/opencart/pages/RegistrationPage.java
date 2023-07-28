package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegistrationPage {
	public ElementUtil eleutil;
	public WebDriver driver;
private By firstname=By.name("firstname");
private By lastname=By.name("lastname");
private By email=By.name("email");
private By telephone=By.name("telephone");
private By password=By.name("password");
private By passwordconfirm=By.name("confirm");
private By subscribeYes=By.xpath("(//label[@class='radio-inline'])[1]//input");
private By subscribeNo=By.xpath("(//label[@class='radio-inline'])[2]//input");
private By privacypolicy=By.xpath("//input[@type='checkbox']");
private By continuebutton=By.xpath("//input[@type='submit']");
private By successmsgtext=By.xpath("//div[@id='content']/h1");
private By Logoutlink=By.linkText("Logout");
private By Registerlink=By.linkText("Register");
public RegistrationPage(WebDriver driver) {
	this.driver=driver;
	eleutil=new ElementUtil(driver);
}
private long email() {
	return System.currentTimeMillis();
}
public String registertUser(String firstname,String lastname,String email,String telephone,String password,String subscribe) {
	eleutil.waitForElementVisible(this.firstname,AppConstants.LONG_DEFAULT_WAIT);
	eleutil.doSendkeys(this.firstname,firstname);
	eleutil.doSendkeys(this.lastname,lastname);
	eleutil.doSendkeys(this.email,email);
	eleutil.doSendkeys(this.telephone,telephone);
	eleutil.doSendkeys(this.password,password);
	eleutil.doSendkeys(this.passwordconfirm,password);
	doSubscribe(subscribe);
	eleutil.doClick(privacypolicy);
	eleutil.doClick(continuebutton);
	String UserRegSuccessmsg=eleutil.waitForElementVisible(successmsgtext,AppConstants.MEDIUM_DEFAULT_WAIT).getText();
	System.out.println(UserRegSuccessmsg);
	eleutil.doClick(Logoutlink);
	eleutil.doClick(Registerlink);
	return UserRegSuccessmsg;
	}
private void doSubscribe(String subscribe) {
	if(subscribe.equals("yes")) {
		eleutil.doClick(subscribeYes);
	}
		else {
			eleutil.doClick(subscribeNo);
			}
}
}
