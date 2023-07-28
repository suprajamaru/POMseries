package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class LoginPageNegativeTest extends BaseTest {
//@DataProvider
//public Object[][] incorrectLoginTestData() {
//	return new Object[][] {
//		{"dupoioij12345","1234465"},
//		{"test@gmail.com","abcd"},
//		{"@#$%%^Y%$%#$^&"," "} 
//	};
//}
	@DataProvider(name="loginExcelData")
public Object[][] incorrectLoginTestData(){
		Object logindata[][]=ExcelUtil.getTestData(AppConstants.LOGINPAGE_DATS_SHEET_NAME);
		return logindata;
	}
	@Test(dataProvider="loginExcelData")
public void incorrectloginTest(String username,String password) {
	Assert.assertTrue(loginpage.dologinwithWrongCredentials(username,password));
	}
}
