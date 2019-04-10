package com.org.utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.org.config.Constants;
import com.org.executionengine.MainScript;

public class ExcelUtils {
	
	 private static XSSFSheet ExcelWSheet;
     private static XSSFWorkbook ExcelWBook;
     private static XSSFCell Cell;
     private static XSSFRow Row;
     
    
     /*
     public static void setExcelFile(String Path,String SheetName) throws Exception 
     {
         FileInputStream ExcelFile = new FileInputStream(Path);
         ExcelWBook = new XSSFWorkbook(ExcelFile);
         ExcelWSheet = ExcelWBook.getSheet(SheetName);
     }
     */
     
     public static String getCellData(int RowNum, int ColNum) throws Exception
     {
	     try
	     {
	    	 Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		     String CellData = Cell.getStringCellValue();
		     return CellData;
	     }
	     catch(Exception e)
	     {
			Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
			MainScript.bResult = false;
			return "";
	     }
     }
     
     public static String getCellData(int RowNum, int ColNum, String SheetName ) throws Exception
     {
     	ExcelWSheet = ExcelWBook.getSheet(SheetName);
     	try
     	{
	    	Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
	        String CellData = Cell.getStringCellValue();
	        return CellData;
        }
	     catch(Exception e)
	     {
			Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
			MainScript.bResult = false;
			return "";
	     }
 	}

	public static void setExcelFile(String sPath, String sheetName) throws Exception 
	{
		// TODO Auto-generated method stub
		try
		{
			FileInputStream ExcelFile = new FileInputStream(sPath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheetName);
		}
		catch(Exception e)
		{
			Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
			MainScript.bResult = false;
		}
	}
	
    public static void setExcelFile(String Path) throws Exception 
    {
        try
        {
	    	FileInputStream ExcelFile = new FileInputStream(Path);
	        ExcelWBook = new XSSFWorkbook(ExcelFile);
        }
		catch(Exception e)
		{
			Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
			MainScript.bResult = false;
		}
	}
    
	public static int getRowCount(String SheetName)
	{
		try
		{
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int number=ExcelWSheet.getLastRowNum()+1;
			return number;
		}
		catch(Exception e)
		{
			Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
			MainScript.bResult = false;
			return 0;
		}
	}
	
	public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception
	{
		try
		{
			int i;	
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int rowCount = ExcelUtils.getRowCount(SheetName);
			for (i=0 ; i<rowCount; i++)
			{
				if  (ExcelUtils.getCellData(i,colNum,SheetName).equalsIgnoreCase(sTestCaseName))
				{
					break;
				}
			}
			return i;
		}
		catch(Exception e)
		{
			Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
			MainScript.bResult = false;
			return 0;
		}
	}
	
	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception
	{
		try
		{
			for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++){
				if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName))){
					int number = i;
					return number;
				}
			}
			
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int number=ExcelWSheet.getLastRowNum()+1;
			return number;
		}
		catch(Exception e)
		{
			Log.error("Class Utils | Method setExcelFile | Exception desc : "+e.getMessage());
			MainScript.bResult = false;
			return 0;
		}
	}
	
	public static void setCellData(String Result,  int RowNum, int ColNum, String SheetName) throws Exception    
	{
		try
		{
		
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Row  = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum, Row.RETURN_BLANK_AS_NULL);
			if (Cell == null) 
			{
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			} 
			else 
			{
				Cell.setCellValue(Result);
			}
			FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
			ExcelWBook.write(fileOut);
			//fileOut.flush();
			fileOut.close();
			ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));
		}
		catch(Exception e)
		{
			MainScript.bResult = false;
		}
	}

}
