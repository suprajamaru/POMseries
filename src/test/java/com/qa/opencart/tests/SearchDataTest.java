package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;
import com.qa.opencart.pojo.product;

public class SearchDataTest extends BaseTest {
	@BeforeClass
	public void SearchSetup() {
		accpage=loginpage.dologin(prop.getProperty("username"),prop.getProperty("password"));
	}

    @Test(dataProvider="productdata",dataProviderClass=ProductDataProvider.class)
	public void SearchProductResultCountTest(product product) {
		resultspage=accpage.dosearch(product.getSearchKey());
		Assert.assertTrue(resultspage.getProductResultsCount()>0);
	}

    @Test(dataProvider="productsearch",dataProviderClass=ProductDataProvider.class)
	public void searchPageTitleTest(product product) {
		resultspage=accpage.dosearch(product.getSearchKey());
		String actSearchtitle=resultspage.getResultsPageTitle(product.getSearchKey());
		System.out.println("Search page title"+actSearchtitle);
	Assert.assertEquals(actSearchtitle,"Search - " +product.getSearchKey());
	}
    @Test(dataProvider="productdata",dataProviderClass=ProductDataProvider.class)
public void SelectProductTest(product product) {
		resultspage=accpage.dosearch(product.getSearchKey());
		productInfoPage=resultspage.selectProduct(product.getProductName());
		String actProductHeaderName =productInfoPage.getProductHeaderName();
		System.out.println("actual product name"+actProductHeaderName);
		Assert.assertEquals(actProductHeaderName,product.getProductName());
	}    
	@Test(dataProvider="productdata",dataProviderClass=ProductDataProvider.class)
public void productImagesTest(product product) {
		resultspage=accpage.dosearch(product.getSearchKey());
		productInfoPage=resultspage.selectProduct(product.getProductName());
		int actproductImageCount=productInfoPage.getProductImageCount();
		System.out.println("actual product images count"+actproductImageCount);
		Assert.assertEquals(actproductImageCount,product.getProductcount());
	}
	}
	

