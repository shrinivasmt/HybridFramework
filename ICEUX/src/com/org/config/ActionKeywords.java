package com.org.config;

import static com.org.executionengine.MainScript.OR;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import com.org.executionengine.MainScript;
import com.org.utility.Log;

public class ActionKeywords {
	
	public static WebDriver driver;
	
	public static void openBrowser(String object,String data){		
		Log.info("Opening Browser");
		try{
			if(data.equals("Firefox")){
				//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\ExternalLib\\Browser\\geckodriver");
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\ExternalLib\\geckodriver.exe");
				/*ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("abcProfile");*/
				
				
				driver=new FirefoxDriver();
				driver.manage().window().maximize();
				Log.info("Firefox browser started");
				System.out.println("Firefox browser started");
				}
			else if(data.equals("IE")){
				driver=new InternetExplorerDriver();
				Log.info("IE browser started");
				}
			else if(data.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\ExternalLib\\Browser\\chromedriver.exe");
				System.out.println();
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				Log.info("Chrome browser started");
				System.out.println("Chrome browser started");
				}
 
			int implicitWaitTime=(10);
			driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		}catch (Exception e){
			Log.info("Not able to open the Browser --- " + e.getMessage());
			System.out.println("Not able to open the Browser --- " + e.getMessage());
			MainScript.bResult = false;
		}
	}
 
	public static void navigate(String object, String data){
		try{
			Log.info("Navigating to URL");
			System.out.println("Navigating to URL");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(Constants.URL);
		}catch(Exception e){
			Log.info("Not able to navigate --- " + e.getMessage());
			System.out.println("Not able to navigate --- " + e.getMessage());
			MainScript.bResult = false;
			}
		}
 
	public static void click(String object, String data){
		try{
			Log.info("Clicking on Webelement "+ object);
			System.out.println("Clicking on Webelement "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		 }catch(Exception e){
 			Log.error("Not able to click --- " + e.getMessage());
 			System.out.println("Not able to click --- " + e.getMessage());
 			MainScript.bResult = false;
         	}
		}
	
	public static void input(String object, String data){
		try{
			Log.info("Entering the text in " + object);
			System.out.println("Entering the text in " + object);
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		 }catch(Exception e){
			 Log.error("Not able to Input --- " + e.getMessage());
			 System.out.println("Not able to Input --- " + e.getMessage());
			 MainScript.bResult = false;
		 	}
		}
 
	public static void waitFor(String object, String data) throws Exception{
		try{
			Log.info("Wait for 3 seconds");
			System.out.println("Wait for 3 seconds");
			Thread.sleep(3000);
		 }catch(Exception e){
			 Log.error("Not able to Wait --- " + e.getMessage());
			 System.out.println("Not able to Wait --- " + e.getMessage());
			 MainScript.bResult = false;
         	}
		}
	
	public static void validateText(String object, String data){
		try{
			Log.info("validating  a text "+ object);
			System.out.println("validating a text "+ object);
			WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
			String strng = element.getText();			
			System.out.println(strng);
			Log.info("The text"  +  strng  +"validated successfully");
		
		 }catch(Exception e){
 			Log.error("Not able to validate --- " + e.getMessage());
 			System.out.println("Not able to validate --- " + e.getMessage());
 			MainScript.bResult = false;
         	}
		}
	
	
	public void WindowHandleTabs(String object, String data) throws InterruptedException {
	    
		assertHomePage();

	    // considering that there is only one tab opened in that point.
	    String oldTab = driver.getWindowHandle();
	    driver.findElement(By.xpath("//span[@class='ng-tns-c40-3' and contains(.,'Sales Pick List')]")).click();
	    ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	    newTab.remove(oldTab);
	    // change focus to new tab
	    driver.switchTo().window(newTab.get(0));
	    assertMetaFXPage();

	    // Do what you want here, you are in the new tab
	    Thread.sleep(4000);
	    driver.close();
	    // change focus back to old tab
	    driver.switchTo().window(oldTab);
	    assertHomePage();

	    // Do what you want here, you are in the old tab
	}

	private void assertHomePage() {
	    assertEquals("Epicor Kinetic Homepage", driver.getTitle());
	    System.out.println("Title details validated --- " + driver.getTitle());
	}

	private void assertEquals(String string, String title) {
		// TODO Auto-generated method stub
		
	}

	private void assertMetaFXPage() {
	    assertEquals("Metafx", driver.getTitle());
	    System.out.println("Title details validated --- " + driver.getTitle());
	}
	
	
	
/*	public static void SelctDropdown(String object, String data){
		try{
			Log.info("Selecting from drop down "+ object);
			System.out.println("Selected dropdown is "+ object);
			WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
			String strng = element.getText();			
			System.out.println(strng);
			Log.info("The text"  +  strng  +"validated successfully");
		
		 }catch(Exception e){
 			Log.error("Not able to validate --- " + e.getMessage());
 			System.out.println("Not able to validate --- " + e.getMessage());
 			MainScript.bResult = false;
         	}
		}*/
	
	

	public static void closeBrowser(String object, String data){
		try{
			Log.info("Closing the browser");
			System.out.println("Closing the browser");
			driver.quit();
		 }catch(Exception e){
			 Log.error("Not able to Close the Browser --- " + e.getMessage());
			 System.out.println("Not able to Close the Browser --- " + e.getMessage());
			 MainScript.bResult = false;
         	}
		}
	

}
