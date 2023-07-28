package com.qa.opencart.tests;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPageTest extends BaseTest{
	WebDriver driver;
	@BeforeClass
	public void accpagesetup() {
		accpage=loginpage.dologin(prop.getProperty("username"),prop.getProperty("password"));
	
	}
	@Test
	public void accpageTitleTest() {
		String accTitle=accpage.getAccountPageTitle();
		System.out.println("Accountpage title is"+accTitle);
		Assert.assertEquals(accTitle,AppConstants.ACCOUNT_PAGE_TITLE_VALUE);
	}
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accpage.islogoutLinkExist());
	}
	@Test
	public void accpageURLTest() {
		String acturl=accpage.getAccountPageURL();
			Assert.assertTrue(acturl.contains(AppConstants.ACCOUNT_PAGE_URL_FRACTION_VALUE));
	}
	@Test
	public void isMyAccountLinkExistTest() {
		Assert.assertTrue(accpage.isMyAccountLinkExist());
	}
	@Test
	public void AccountPageHeadersListCountTest() {
		int headerscount=accpage.getAccountPageHeadersList().size();
		Assert.assertEquals(headerscount,AppConstants.ACCOUNT_PAGE_HEADERCOUNT);
	}
	@Test
	public void AccountPageHeadersListTest() {
		List<String>actaccheaderlist=accpage.getAccountPageHeadersList();
		List<String>expaccheaderlist= AppConstants.EXP_ACCOUNTS_HEADERS_LIST;
		Assert.assertEquals(actaccheaderlist,expaccheaderlist);
		}
	
	
	
	
}
