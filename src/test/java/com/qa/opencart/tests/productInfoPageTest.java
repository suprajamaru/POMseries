package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.dataproviders.ProductDataProvider;

public class productInfoPageTest extends BaseTest {

	@BeforeClass
	public  void accpageSetUp() {
		accpage=loginpage.dologin(prop.getProperty("username"),prop.getProperty("password"));
		toString();
	}
	
	@Test(dataProvider="productdatawithproductname",dataProviderClass=ProductDataProvider.class)
	public void productInfoTest(String Searchkey,String productname) {
		resultspage=accpage.dosearch(Searchkey);
		productInfoPage=resultspage.selectProduct(productname);
		Map<String, String> productInfoMap=productInfoPage.getProductInfo();
		System.out.println(productInfoMap);
		
//{Brand=Apple, Availability=In Stock, extaxprice=$2,000.00, Product Code=Product 18, productname=MacBook Pro, Reward Points=800, productprice=$2,000.00}-->hashmap it will not maintain order
//{Brand=Apple, Product Code=Product 18, Reward Points=800, Availability=In Stock, productprice=$2,000.00, extaxprice=$2,000.00, productname=MacBook Pro}-->Linked hashmap it will maintain the order
//{Availability=In Stock, Brand=Apple, Product Code=Product 18, Reward Points=800, extaxprice=$2,000.00, productname=MacBook Pro, productprice=$2,000.00}-->TreeMap it will sort the list 
	softassert.assertEquals(productInfoMap.get("Brand"),"Apple");
	softassert.assertEquals(productInfoMap.get("Availability"),"In Stock");
	softassert.assertEquals(productInfoMap.get("extaxprice"),"$2,000.00");
	softassert.assertEquals(productInfoMap.get("Product Code"),"Product 18");
	softassert.assertEquals(productInfoMap.get("productname"),"MacBook Pro");
	softassert.assertEquals(productInfoMap.get("Reward Points"),"800");
	softassert.assertEquals(productInfoMap.get("productprice"),"$2,000.00");
	softassert.assertAll();
		
		
		
		
	}
	
	
	
	
}
