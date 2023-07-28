package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
WebDriver driver;
private ElementUtil eleUtil;
//1.const.of the page class
public AccountsPage(WebDriver driver) {
	this.driver=driver;
	eleUtil=new ElementUtil(this.driver);
}
//2.by locators
private By logoutlink=By.linkText("Logout");
private By MyAccountLink=By.linkText("My Account");
private By AccountHeaders=By.cssSelector("div#content h2");
private By search=By.name("search");
private By searchicon=By.xpath("//button/i[@class='fa fa-search']");
//3.page actions
public String getAccountPageTitle(){
	return eleUtil.waitfortitleandCapture(AppConstants.ACCOUNT_PAGE_TITLE_VALUE,AppConstants.SHORT_DEFAULT_WAIT);
}
public String getAccountPageURL() {
	return eleUtil.waitforUrlcontains(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE,AppConstants.MEDIUM_DEFAULT_WAIT);
}
public Boolean islogoutLinkExist() {
	return eleUtil.CheckElementIsDisplayed(logoutlink);
}
public Boolean isMyAccountLinkExist() {
	return eleUtil.CheckElementIsDisplayed(MyAccountLink);
}
public List<String> getAccountPageHeadersList() {
	List<WebElement>headerlist=eleUtil.waitForAllElementsVisible(AccountHeaders,AppConstants.MEDIUM_DEFAULT_WAIT);
	List<String>headertextlist=new ArrayList<String>();
	for(WebElement e:headerlist) {
		String text=e.getText();
		headertextlist.add(text);
}
return headertextlist;
}
public ResultsPage dosearch(String searchterm) {
	eleUtil.waitForElementVisible(search,AppConstants.MEDIUM_DEFAULT_WAIT);
	eleUtil.doSendkeys(search, searchterm);
	eleUtil.doClick(searchicon);
	return new ResultsPage(driver);
}
}
