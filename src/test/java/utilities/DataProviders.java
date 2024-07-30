package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//dataproviders method will take the data and give to the test cases

	// DataProvider1
@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {

		// Taking data from Excel

		String path = ".\\testData\\TestData123.xlsx";

		ExcelUtility2 xlutil = new ExcelUtility2(path); // Creating an object for ExcelUtility

		int totalRows = xlutil.getRowCount("sheet1");
		int totalCols = xlutil.getCellCount("sheet1", 1);

		String loginData[][] = new String[totalRows][totalCols];// Created for 2d Array which can store data, it is an
																// empty array

		for(int i=0;i<totalRows;i++) {//reads the data from excel storing in 2d Array
			for(int j=1;j<totalCols;j++) {// i is rows and j is columns
				
				loginData[i-1][j]= xlutil.getCellData("sheet1", i, j); //row =1, column=0
				
			}
			
		}
		return loginData; //returns two Dimensional Array
	}
//Data provider 2

//Data provider 3 

//Data provider 4
}
