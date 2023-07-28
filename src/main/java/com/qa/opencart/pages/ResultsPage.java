package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
private ElementUtil eleUtil;
private WebDriver driver;
private By resultsProduct=By.xpath("//div[contains(@class,'product-layout product-grid')]");
	public ResultsPage(WebDriver driver) {
		this.driver=driver;
		eleUtil=new ElementUtil(this.driver);
	}
	//page actions
	public String getResultsPageTitle(String searchkey) {
	return	eleUtil.waitfortitlecontains(searchkey,AppConstants.SHORT_DEFAULT_WAIT);
	}
	public int getProductResultsCount() {
		int resultCount= eleUtil.waitForAllElementsVisible(resultsProduct,AppConstants.MEDIUM_DEFAULT_WAIT).size();
		System.out.println("product selectedresult count==>"+resultCount);
		return resultCount;
	}
	public ProductInfoPage selectProduct(String productName) {
		By productNameLocator=By.linkText(productName);
		eleUtil.doClick(productNameLocator);
		return new ProductInfoPage(driver);
	}
}
