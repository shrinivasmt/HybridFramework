package com.org.config;

public class Constants {
	
	public static final String URL = "https://l505032.emea.epicor.net/ERP102500/Apps/ERP/Home/#/login";
	public static final String Path_TestData = System.getProperty("user.dir") + "//src//com//org//dataengine//DataEngineUsingOR.xlsx";
	public static final String Path_OR = System.getProperty("user.dir") + "//src//com//org//config//OR";
	public static final String File_TestData = "DataEngine.xlsx";
	
	public static final int Col_TestCaseID = 0;	
	public static final int Col_TestScenarioID =1 ;
	public static final int Col_PageObject = 4;
	public static final int Col_ActionKeyword = 5 ;
	public static final int Col_Runmode=2;
	
	public static final int Col_Result =3;
	public static final int Col_DataSet =6 ;
	public static final int Col_TestStepResult =7 ;
	
	public static final String Sheet_TestSteps = "Test Steps";
	public static final String Sheet_TestCases = "Test Cases";
	
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	

	public static final String UserName = "epicor";
	public static final String Password = "epicor";
	
	
}
