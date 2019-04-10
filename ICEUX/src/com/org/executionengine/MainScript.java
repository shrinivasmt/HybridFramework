package com.org.executionengine;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.Test;
import org.junit.experimental.*;

import com.org.config.ActionKeywords;
import com.org.config.Constants;
import com.org.utility.ExcelUtils;
import com.org.utility.Log;

public class MainScript {
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	
	public static Method method[];
	public static Properties OR;
	public static String sPageObject;
	
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sData;
	
	public static  boolean bResult = true;

	public MainScript() throws NoSuchMethodException, SecurityException
	{
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
	}
	@Test
	public void demo() throws Exception
	{
		
		DOMConfigurator.configure("log4j.xml");
		ExcelUtils.setExcelFile(Constants.Path_TestData);
    	
    	String Path_OR = Constants.Path_OR;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR= new Properties(System.getProperties());
		OR.load(fs);
		
    	
		MainScript startEngine = new MainScript();
		startEngine.execute_TestCase();

	}
	
	private void execute_TestCase() throws Exception
	{
	
    	int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
    	
    	  			
		for(int iTestcase=1;iTestcase<iTotalTestCases;iTestcase++)
		{
			bResult = true;			
			sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases); 
			sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_Runmode,Constants.Sheet_TestCases);
			if (sRunMode.equals("Yes"))
			{
				iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
				bResult=true;
				Log.startTestCase(sTestCaseID);
				
				for (;iTestStep<iTestLastStep;iTestStep++)
				{
		    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
		    		sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
		    		sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);
		    		execute_Actions();
		    		
					if(bResult==false)
					{
					
						ExcelUtils.setCellData(Constants.KEYWORD_FAIL,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
					
						Log.endTestCase(sTestCaseID);
					
						break;
					}
		    	}
				
				if(bResult==true)
				{
			
					ExcelUtils.setCellData(Constants.KEYWORD_PASS,iTestcase,Constants.Col_Result,Constants.Sheet_TestCases);
					Log.endTestCase(sTestCaseID);	
				}
			}
    	}
	}
	
	private static void execute_Actions() throws Exception 
	{
		for(int i = 0;i < method.length;i++)
		{
			if(method[i].getName().equals(sActionKeyword))
			{
				method[i].invoke(actionKeywords, sPageObject, sData);
				if(bResult==true)
				{
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					break;
				}
				else
				{
					ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
					ActionKeywords.closeBrowser("", "");
					break;
				}
				
			}
		}
	}
	
	

}
