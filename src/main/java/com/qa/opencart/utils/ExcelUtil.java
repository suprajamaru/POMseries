package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	private static  Workbook book;
	private static String TEST_DATA_SHEET__PATH="src/main/resources/testdata/Book 2 (2).xlsx";
	private static Sheet sheet;
public static Object[][] getTestData(String SheetName) {
	
	System.out.println("reading the data from sheet"+SheetName);
	Object data[][]=null;
	
	try {
		FileInputStream ip = new FileInputStream(TEST_DATA_SHEET__PATH);
        book=WorkbookFactory.create(ip);
      sheet=book.getSheet(SheetName);
        data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
        for(int i=0;i<sheet.getLastRowNum();i++) {
        	for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
        		data[i][j]=sheet.getRow(i+1).getCell(j).toString();
        	}
        }
}
catch (FileNotFoundException e1) {
	
	e1.printStackTrace();
}
catch (EncryptedDocumentException | IOException e) {
	e.printStackTrace();
}
	return data;	
}
}
