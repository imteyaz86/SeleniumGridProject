package com.test.basesetup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.test.commonutils.ExcelLib;
import com.test.commonutils.Generic;
import com.test.commonutils.Input;
import com.test.commonutils.Navigation;
import com.test.commonutils.NegativeVerification;
import com.test.commonutils.PropertyReader;
import com.test.commonutils.ReportLib;
import com.test.commonutils.ScreenShotCapture;
import com.test.commonutils.Verification;

public class ObjectCreation extends BaseSetup {
	public  ExcelLib testDataExcel;
	public  ExcelLib webElementExcel;
	public Input input = null;
	public Generic generic = null;
	public Navigation navigation = null;
	public NegativeVerification negativeVerification = null;
	public Verification verification = null;
	public WebDriverWait globalWait = null;
	public WebDriver driver = null;
	public ReportLib reportLib = null;
	public PropertyReader properties = null;
	public ScreenShotCapture screenShotCapture = null;
	private ExtentTest logger = null;
	
	public ObjectCreation(ExtentTest logger)
			throws Throwable {
		
		try {
		this.logger = logger;	
		testDataExcel = new ExcelLib(
				System.getProperty("user.dir") + "/src/test/resources/sample/testData/TestData.xlsx");
		/*webElementExcel = new ExcelLib(
				System.getProperty("user.dir") + "/src/test/resources/sample/testData/WebElementData.xlsx");*/
		properties = new PropertyReader("utilities.properties");
		driver = new InitiateDriver(properties).getDriver();
		globalWait = new WebDriverWait(driver, Integer.valueOf(properties.getPropertyValue("GlobalWaitValue")).intValue());
		generic = new Generic(driver, logger, globalWait,properties,webElementExcel);
		screenShotCapture = new ScreenShotCapture(driver, generic, properties);
		negativeVerification = new NegativeVerification(driver, generic,logger, globalWait);
		verification = new Verification(driver, generic, logger, globalWait);
		navigation = new Navigation(driver, generic, logger, globalWait);
		input = new Input(driver, generic, verification, logger, globalWait);
		reportLib = new ReportLib(logger, screenShotCapture,generic);
		
		} catch (Throwable t) {
			System.out.println("Error Occurred In Object Creation Contructor Is: "+t.toString());
		}
	}
	
	public WebDriver getDriver() throws Throwable {
		return driver;
	}
	
	public void killObject() throws Throwable {
		input = null;
		generic = null;
		navigation = null;
		negativeVerification = null;
		verification = null;
		globalWait = null;
		driver = null;
		reportLib = null;
		properties = null;
	}

	public void testExit() throws Throwable {
		reports.endTest(logger);
		generic.closeApplication();
		killObject();
	}
}
