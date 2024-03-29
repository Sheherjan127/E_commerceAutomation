package com.tutorialsninja.qa.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class utilities {
	
	//public static final int IMPLICIT_WAIT_TIME=10;
	//public static final int PAGE_WAIT_TIME=5;
	
	public static String generateEmailWithTimeStamp() {
		  Date date= new Date();
		  String timestamp = date.toString().replace(" ","_").replace(":", "_");
		  return "sheher"+timestamp+"@gmail.com";
		  
	}
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		XSSFWorkbook workbook = null; 
		File excelfile = new File(System.getProperty("user.dir")+"\\Test_Id_Password.xlsx");
		try {
		FileInputStream fisExcel = new FileInputStream(excelfile);
		 workbook = new XSSFWorkbook(fisExcel); 
		}catch(Throwable e){
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int i = 0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
		
		      for(int j=0;j<cols;j++) {
			       XSSFCell cell= row.getCell(j);
			        CellType cellType = cell.getCellType();
			        
			        switch (cellType) { 
			        case STRING:
			        	data[i][j] = cell.getStringCellValue();
			        	break;
			        
			        case NUMERIC:
			        	data[i][j] =  Integer.toString((int)cell.getNumericCellValue());
			            break;
			            
			        case BOOLEAN:
			        	data[i][j] = cell.getBooleanCellValue();
			        	//data[i][j]=cell.getBooleamCellValue();//
			        	//fdata[ [i;sness   in//fdata[i][j];//fdata[ [i;sness inn//fdata[i][j];//fdata]
			        	//fdata[ [i;sness in//fdata[i][j];//fdata[[i;sness inn//]]//fdata[[i;sness inn//
			        }
		      }
		}
		
		return data;
	}

}

