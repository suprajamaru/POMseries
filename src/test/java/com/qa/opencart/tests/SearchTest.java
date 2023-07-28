package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;

public class SearchTest extends BaseTest {
@BeforeClass
public void SearchSetup() {
	accpage=loginpage.dologin(prop.getProperty("username"),prop.getProperty("password"));
}
@DataProvider
public Object[][] getProductNameData() {
	return new Object[][] {
		{"Macbook"},
		{"imac"}, 
		{"samsung"}
	};
}

@Test(dataProvider="productsearch",dataProviderClass=ProductDataProvider.class)
public void SearchProductResultCountTest(String searchkey) {
	resultspage=accpage.dosearch(searchkey);
	Assert.assertTrue(resultspage.getProductResultsCount()>0);
}

@Test(dataProvider="productsearch",dataProviderClass=ProductDataProvider.class)
public void searchPageTitleTest(String searchkey) {
	resultspage=accpage.dosearch(searchkey);
	String actSearchtitle=resultspage.getResultsPageTitle(searchkey);
	System.out.println("Search page title"+actSearchtitle);
Assert.assertEquals(actSearchtitle,"Search - " +searchkey);
}

@Test(dataProvider="productdatawithproductname",dataProviderClass=ProductDataProvider.class)
public void SelectProductTest(String searchkey,String productname) {
	resultspage=accpage.dosearch(searchkey);
	productInfoPage=resultspage.selectProduct(productname);
	String actProductHeaderName =productInfoPage.getProductHeaderName();
	System.out.println("actual product name"+actProductHeaderName);
	Assert.assertEquals(actProductHeaderName,productname);
}    

@Test(dataProvider="productdatawithImageCount",dataProviderClass=ProductDataProvider.class)
public void productImagesTest(String searchkey,String productname,int imagecount) {
	resultspage=accpage.dosearch(searchkey);
	productInfoPage=resultspage.selectProduct(productname);
	int actproductImageCount=productInfoPage.getProductImageCount();
	System.out.println("actual product images count"+actproductImageCount);
	Assert.assertEquals(actproductImageCount,imagecount);
}
}
