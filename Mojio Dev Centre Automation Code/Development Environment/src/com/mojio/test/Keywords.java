package com.mojio.test;
import static com.mojio.test.DriverScript.APP_LOGS;
import static com.mojio.test.DriverScript.CONFIG;
import static com.mojio.test.DriverScript.OR;











import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;


public class Keywords {

	public WebDriver driver;

	int i=0;
	String CDT2 = null;
	String winHandleBefore = null;
	String text = null;

	//-------------------------------------Browser Keywords-----------------------------------------

	//Open the browser
	public String openBrowser(String object,String data){		
		APP_LOGS.debug("Opening browser");
		if(CONFIG.getProperty(data).equals("Mozilla"))
			driver=new FirefoxDriver();
		else if(CONFIG.getProperty(data).equals("IE"))
			driver=new InternetExplorerDriver();
		else if(CONFIG.getProperty(data).equals("Chrome"))
			driver=new ChromeDriver();

		long implicitWaitTime=Long.parseLong(CONFIG.getProperty("implicitwait"));
		driver.manage().timeouts().implicitlyWait(implicitWaitTime, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return Constants.KEYWORD_PASS;

	}

	//Navigate to a URL
	public String navigate(String object,String data){		
		APP_LOGS.debug("Navigating to URL");
		try{
			driver.navigate().to(CONFIG.getProperty(data));
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- Not able to navigate";
		}
		return Constants.KEYWORD_PASS;
	}


	//Close the browser
	public  String closeBroswer(String object, String data){
		APP_LOGS.debug("Closing the browser");
		try{
			driver.quit();
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+"Unable to close browser. Check if its open"+e.getMessage();
		}
		return Constants.KEYWORD_PASS;

	}

	//---------------------------------------Click--------------------------------------------------

	//Click using the link text
	public String clickLinkByLinkText(String object,String data){
		APP_LOGS.debug("Click using the link text");
		try{
			driver.findElement(By.linkText(OR.getProperty(object))).click();
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- Not able to click on link"+e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	//clicking using ID attribute
	public  String clickByID(String object,String data){
		APP_LOGS.debug("clicking using ID attribute");
		try{
			driver.findElement(By.id(OR.getProperty(object))).click();
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" -- Not able to click on Button"+e.getMessage();
		}

		return Constants.KEYWORD_PASS;
	}

	//clicking using xpath attribute
	public  String clickByXpath(String object,String data){
		APP_LOGS.debug("Clicking using xpath attribute");
		try{
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		}catch(Exception e){
			e.printStackTrace();
			return Constants.KEYWORD_FAIL+" -- Not able to click on Button"+e.getMessage();
		}

		return Constants.KEYWORD_PASS;
	}

	//clicking using xpath attribute and then accepting the alert
	public  String acceptAlertOnBtnByXpath(String object,String data){
		APP_LOGS.debug("clicking using xpath attribute and then accepting the alert");
		try{
			driver.findElement(By.xpath(OR.getProperty(object))).click();
			Alert alert= driver.switchTo().alert();
			alert.accept();

		}catch(Exception e){
			e.printStackTrace();
			return Constants.KEYWORD_FAIL+" -- Not able to click on Button"+e.getMessage();
		}

		return Constants.KEYWORD_PASS;
	}


	//-------------------------------------Verify---------------------------------------------------


	//Verify if the text is present in page
	public String IsTextPresent(String Object, String data){
		APP_LOGS.debug("Verify if the text is present in page");
		try{
			boolean b = driver.getPageSource().contains(data);
			if(b==true)
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL;
		}
		catch(Exception e){
			return e.getMessage();
		}
	}


	//Verify the text is not present in the page
	public String TextNotPresent(String object, String data){
		APP_LOGS.debug("Verify the text is not present in the page");
		try{
			boolean b = driver.getPageSource().contains(data);
			if(b==true)
				return Constants.KEYWORD_FAIL;
			else
				return Constants.KEYWORD_PASS;
		}
		catch(Exception e){
			return e.getMessage();
		}
	}

	//Verify the current Date Time Text
	public String verifyCurrentDateByXpath(String object, String data){
		APP_LOGS.debug("Verify the current Date Time Text");
		try{
			String expected=CurrentDate1();
			String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
			if(actual.contains(expected))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL;
		}
		catch(Exception e){
			return e.getMessage();
		}
	}

	//Verify the current Date Time Text
	public String verifyCurrentDatePresent(String Object, String data){
		APP_LOGS.debug("Verify the current Date Time Text");
		try{
			String data1=CurrentDate1();
			boolean b = driver.getPageSource().contains(data1);
			if(b==true)
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL;
		}
		catch(Exception e){
			return e.getMessage();
		}
	}

	//Verify the Current url
	public  String VerifyCurrentUrl(String object,String data){
		APP_LOGS.debug("Verify the Current url");
		String currenturl=null;
		String url=null;
		try{
			currenturl = driver.getCurrentUrl();
			url=OR.getProperty(object);
			if(currenturl.equals(url))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL;
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Not able get the URL";
		}
	}


	//Function to verify if element is present  -- By xpath
	public String VerifyElementPresentXpath(String object, String data){
		APP_LOGS.debug("Function to verify if element is present  -- By xpath");
		try{
			driver.findElement(By.xpath(OR.getProperty(object)));
		}catch(Exception e){
			return e.getMessage();				
		}
		return Constants.KEYWORD_PASS;
	}

	//Function to verify if element is present  -- By Id
	public String VerifyElementPresentId(String object, String data){
		APP_LOGS.debug("Function to verify if element is present  -- By xpath");
		try{
			driver.findElement(By.id(OR.getProperty(object)));
		}catch(Exception e){
			return e.getMessage();				
		}
		return Constants.KEYWORD_PASS;
	}

	//Function to verify if element is not present  -- By xpath
	public String ElementNotPresentXpath(String object, String data){
		APP_LOGS.debug("Function to verify if element is present  -- By xpath");
		try{
			Boolean b=driver.findElement(By.xpath(OR.getProperty(object))).isDisplayed();
			if(b==false)
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL;
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+e.getMessage();								
		}
	}


	//Verify values selected in the  dropdown
	public String verifyDropdownValueByXpath(String object, String data){
		APP_LOGS.debug("Verify values selected in the  dropdown");
		try{
			Select select = new Select(driver.findElement(By.xpath(OR.getProperty(object))));   
			String SelectedValue=select.getFirstSelectedOption( ).getText( );
			if(SelectedValue.equals(data))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL;
		}catch (Exception e){
			return Constants.KEYWORD_FAIL + e.getMessage();
		}
	}

	//Verify if the text is present in a object
	public String verifyTextByXpath(String object, String data){
		APP_LOGS.debug("Verifying the text");
		try{
			String actual=driver.findElement(By.xpath(OR.getProperty(object))).getText();
			String expected=data;
			if(actual.equals(expected))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL+" -- text not verified "+actual+" -- "+expected;
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Object not found "+e.getMessage();
		}
	}



	//----------------------------------------------Input--------------------------------------------

	public String writeCurrentDateEmailById(String object, String data){
		APP_LOGS.debug("Writing in text box");
		try{
			String data1=CurrentDate1();
			String data2=data1+"@test.com";
			driver.findElement(By.id(OR.getProperty(object))).sendKeys(data2);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}


	//Input the current date 
	public String writeCurrentDateById(String object, String data){
		APP_LOGS.debug("Writing in text box");
		try{
			String data1=CurrentDate1();
			driver.findElement(By.id(OR.getProperty(object))).sendKeys(data1);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}

	//function to return current date and time
	public String CurrentDate1() {
		SimpleDateFormat sdfDate = new SimpleDateFormat("MMMddHHmmss");   
		Date now = new Date();
		String CDT1 = sdfDate.format(now);
		if(i==0){
			CDT2=CDT1;
			i=i+1;
			return CDT2;
		}
		else
			return CDT2;

	}

	//Write data in text box, text area
	public  String writeInInput(String object,String data){
		APP_LOGS.debug("Writing in text box");

		try{
			driver.findElement(By.id(OR.getProperty(object))).sendKeys(data);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Unable to write "+e.getMessage();

		}
		return Constants.KEYWORD_PASS;

	}



	//------------------------------------------Dropdown Functions-----------------------------------------------

	//Choose value from dropdown based on value
	public String selectDropdownValueByValueId(String object, String data){
		APP_LOGS.debug("Choose value from dropdown based on value");
		try{
			Select select = new Select(driver.findElement(By.id(OR.getProperty(object))));
			select.selectByValue(data);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Not able to choose value based on value attrobute";
		}
		return Constants.KEYWORD_PASS;
	}  

	//Choose value from dropdown based on visible text
	public String selectDropdownValueByVisibleTextId(String object, String data){
		APP_LOGS.debug("Choose value from dropdown based on visible text");
		try{
			Select select = new Select(driver.findElement(By.id(OR.getProperty(object))));
			select.selectByVisibleText(data);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Not able choose value based on visible text";
		}
		return Constants.KEYWORD_PASS;
	}  

	//Choose value from dropdown based on visible text using Xpath
	public String selectDropdownValueByVisibleTextXpath(String object, String data){
		APP_LOGS.debug("Choose value from dropdown based on visible text using Xpath");
		try{
			Select select = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
			select.selectByVisibleText(data);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Not able choose value based on visible text";
		}
		return Constants.KEYWORD_PASS;
	}  

	//------------------------------------------Get Text of Web element and verify It------------------------------------------

	//Function to retrieve text from read-only field-- By ID
	public String getTextId(String object, String data){
		APP_LOGS.debug("Function to retrieve text from read-only field- By ID");
		try{
			text =driver.findElement(By.id(OR.getProperty(object))).getText();
		}catch(Exception e){
			return Constants.KEYWORD_PASS+e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	//Function to verify the Text of the acquired text
	public String verifyAcquiredTextId(String object, String data){
		APP_LOGS.debug("Function to verify the Text of the acquired text");
		String text1=null;
		try{
			text1 =driver.findElement(By.id(OR.getProperty(object))).getText();
			if(text.equals(text1))
				return Constants.KEYWORD_PASS;
			else
				return Constants.KEYWORD_FAIL;
		}catch(Exception e){
			return Constants.KEYWORD_PASS+e.getMessage();
		}
	}



	//----------------------------------------Clear Field--------------------------------------------


	//Function to clear text using ID
	public String clearFieldById(String object, String data){
		APP_LOGS.debug("Function to clear text using ID");
		try{
			driver.findElement(By.id(OR.getProperty(object))).clear();
		}catch(Exception e){
			return Constants.KEYWORD_FAIL +e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	//---------------------------------------------New Window----------------------------------------------
	//Click on a new window
	public String ClickNewWindow(String object, String data){
		APP_LOGS.debug("Click on a new window");
		try{
			winHandleBefore = driver.getWindowHandle();
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}		
			driver.findElement(By.id(OR.getProperty(object))).click();
			driver.switchTo().window(winHandleBefore);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Not able to accept the alert";
		}
		return Constants.KEYWORD_PASS;
	}

	//Function to accept the alert
	public String acceptAlert(String object, String data){
		APP_LOGS.debug("Function to accept the alert");
		try{
			driver.switchTo().alert().accept();
		}catch(Exception e){
			e.printStackTrace();
			return Constants.KEYWORD_FAIL+ "Not able to accept the alert"+e.getMessage();
		}
		return Constants.KEYWORD_PASS;
	}

	//Navigate to new Window
	public String NavigateNewWindow(String object, String data){
		APP_LOGS.debug("Navigate to new Window");
		try{
			winHandleBefore = driver.getWindowHandle();

			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}					
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Not able to accept the alert";
		}
		return Constants.KEYWORD_PASS;
	}
	//Return to Old Window
	public String ReturnOldWindow(String object, String data){
		APP_LOGS.debug("Click on a new window");
		try{
			driver.close();
			driver.switchTo().window(winHandleBefore);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Not able to accept the alert";
		}
		return Constants.KEYWORD_PASS;
	}

	//Pause the execution for 5 seconds
	public String pause(String object, String data){
		APP_LOGS.debug("Pause the execution for 5 seconds");
		try{
			Thread.sleep(5000);
		}catch(Exception e){
			return Constants.KEYWORD_FAIL+" Not able pause the execution";
		}
		return Constants.KEYWORD_PASS;
	}

	//-----------------------------Capture Screenshot----------------------------------

	public void captureScreenShot(String Filename) throws IOException {
		APP_LOGS.debug("taking screen shot according to config");

		try{
			File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"//Screenshots//"+Filename+".jpg"));
		}
		catch(Exception e)
		{
			APP_LOGS.debug("not able to take screen shots"+e.getMessage());
		}
	}




}


