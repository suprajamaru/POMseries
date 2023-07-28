package com.qa.opencart.pojo;

public class product {
private String SearchKey; 
private String productName;
private int productcount;
public product(String searchKey, String productName, int productcount) {
	super();
	SearchKey = searchKey;
	this.productName = productName;
	this.productcount = productcount;
}
public String getSearchKey() {
	return SearchKey;
}
public void setSearchKey(String searchKey) {
	SearchKey = searchKey;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getProductcount() {
	return productcount;
}
public void setProductcount(int productcount) {
	this.productcount = productcount;
}
@Override
public String toString() {
	return "product [SearchKey=" + SearchKey + ", productName=" + productName + ", productcount=" + productcount + "]";
}



}
