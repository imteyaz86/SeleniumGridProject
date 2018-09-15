package com.test.module1testscripts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.test.basesetup.BaseSetup;
import com.test.basesetup.ObjectCreation;

public class TestClass1 extends BaseSetup {
	
	
	@Test
	public void testsample1() throws Throwable {
		ObjectCreation initiateObject = null;
	    ExtentTest logger = null;
		
		try {
			
			logger = reports.startTest("testsample1", "Sample Test Is Validated");
			logger.assignCategory("Test1");
			System.out.println("Hello Test");
			initiateObject = new ObjectCreation(logger);
			System.out.println("Property Value is"+initiateObject.properties.getPropertyValue("Execution"));
			WebElement el = initiateObject.getDriver().findElement(By.id("identifierId"));
			WebElement e2 = initiateObject.getDriver().findElement(By.id("identifierNext"));
			
			initiateObject.generic.highlightElement(el);
			initiateObject.input.setValueUtility(el, "Email TextBox On Login Page","imteyaz.jmi@gmail.com");
			initiateObject.generic.highlightElement(e2);
			
			/*Robot robot = new Robot();
			robot.mouseMove(e2.getSize().getWidth(), e2.getSize().getHeight());
			robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
			Thread.sleep(5000);
			robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);*/
			
			initiateObject.navigation.objectClick(e2, "Next Button");
		        Thread.sleep(5000);
		        
			logger.log(LogStatus.PASS, "Test Executed On "+initiateObject.properties.getPropertyValue("BrowserName"));
			logger.log(LogStatus.PASS, "Test Is Passed Successfully");
			logger.log(LogStatus.INFO, logger.addScreenCapture(initiateObject.screenShotCapture.captureScreenShots()));
			System.out.println("My string is "+initiateObject.generic.random_stringgeneration_utility(4, "Automat"));
			
			//Assert.fail("Failed Explicitly");
		}catch (Throwable t) {
			initiateObject.reportLib.extentFailureReport(initiateObject.reportLib.getFailureMesssage(t));
			throw t;
		}
		
		finally {
			initiateObject.testExit();
			
		}
	}

}
