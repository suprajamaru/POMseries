package com.qa.opencart.dataproviders;

import org.testng.annotations.DataProvider;

import com.qa.opencart.pojo.product;

public class ProductDataProvider {

	@DataProvider(name="productdata")
	public Object[][] getProductTestData() {
		return new Object[][] {

				{ new product("Macbook", "MacBook Pro", 4) },
				{ new product("imac", "iMac", 3)},
				{ new product("Samsung", "Samsung SyncMaster 941BW", 1) },
				{ new product("Samsung", "Samsung Galaxy Tab 10.1", 7) } };
	}
	@DataProvider(name="productsearch")
	public Object[][] getProductNameData() {
		return new Object[][] {
			{"Macbook"},
			{"imac"}, 
			{"samsung"}
		};
	}
	@DataProvider(name="productdatawithproductname")
	public  Object[][] getproductimagesTestData() {
		return new Object[][] {
			{"Macbook","MacBook Pro"},
			{"imac","iMac"},
			{"Samsung","Samsung SyncMaster 941BW"},
			{"Samsung","Samsung Galaxy Tab 10.1"}
				
			};
	}
	@DataProvider(name="productdatawithImageCount")
	public  Object[][] getproductimagescountTestData() {
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"imac","iMac",3},
			{"Samsung","Samsung SyncMaster 941BW",1},
			{"Samsung","Samsung Galaxy Tab 10.1",7}
				
			};
		}
}

