package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
private WebDriver driver;
private ElementUtil eleUtil;

public ProductInfoPage(WebDriver driver) {
	this.driver=driver;
	 eleUtil = new ElementUtil(driver);
}
private By productHeaderName=By.xpath("//div[@id='content']//h1");
private By productImageCount=By.cssSelector("ul.thumbnails li");
private By productMetaData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
private By productPriceData=By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
private By quantity=By.id("input-quantity");
private By addToCartBtn=By.id("button-cart");
private Map<String,String>productInfoMap;
public String getProductHeaderName() {
	return eleUtil.getElementText(productHeaderName);
}
public int getProductImageCount() {
	return eleUtil.waitForAllElementsVisible(productImageCount,AppConstants.MEDIUM_DEFAULT_WAIT).size();
}
public Map<String, String> getProductInfo() {
//	productInfoMap=new HashMap<String,String>();
	//productInfoMap=new LinkedHashMap<String,String>();
	productInfoMap=new TreeMap<String,String>();
	getProductMetaData();
	getProductPricingData();
	productInfoMap.put("productname", getProductHeaderName());
	return productInfoMap;
	}
//Brand: Apple
//Product Code: Product 18
//Reward Points: 800
//Availability: In Stock

public void getProductMetaData() {
	List<WebElement>metaDataList=eleUtil.getElements(productMetaData);
  for(WebElement e:metaDataList) {
	 String metatext= e.getText();
	 String metaInfo[]=metatext.split(":");
	 String key=metaInfo[0].trim();
	 String value=metaInfo[1].trim();
	 productInfoMap.put(key,value);
  }
	 
  }
//$2,000.00
//Ex Tax: $2,000.00
public void getProductPricingData() {
		List<WebElement>metaDataList=eleUtil.getElements(productPriceData);
		String pricevalue=metaDataList.get(0).getText();
		String extaxPrice=metaDataList.get(1).getText();
		String extaxPricevalue=extaxPrice.split(":")[1].trim();
		productInfoMap.put("productprice",pricevalue);
		productInfoMap.put("extaxprice",extaxPricevalue);
	  }
	
}



