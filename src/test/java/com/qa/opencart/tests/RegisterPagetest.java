package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPagetest extends BaseTest {
@BeforeClass
public void regSetup() {
	registerpage=loginpage.navigateToRegisterPage();
}
public String getRandomEmailId() {
	return "testautomation"+System.currentTimeMillis()+"@gmail.com";
}
//@DataProvider
//public Object[][] userdata() {
//	return new Object[][] {
//		{"Shanvi@777555","regulakunta","9356562564","ella@123","yes"},
//		{"Mksingh@123456","thirupathi","9123456780","govin@123","yes"},
//		{"Durga@789456","ktl","9123456678","sai@123","yes"},
//		
//	};
//}
@DataProvider(name="regExceldata")
public Object[][] getRegExcelDataTest() {
	 Object regData[][]=ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
	 return regData;
}


@Test(dataProvider="regExceldata")
public void userRegisterTest(String firstname,String lastname,String telephone,String password,String subscribe) {
	String actRegSuccessMsg=registerpage.registertUser(firstname, lastname,getRandomEmailId(),telephone, password,subscribe);
	Assert.assertEquals(actRegSuccessMsg,AppConstants.USER_RESG_SUCCESS_MESSG);
}
}
