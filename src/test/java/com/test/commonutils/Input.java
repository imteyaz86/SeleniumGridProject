package com.test.commonutils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Input extends com.test.basesetup.BaseSetup {

	String strValue;
	WebDriver driver = null;
	ExtentTest logger = null;
	WebDriverWait globalWait = null;
	Generic generic = null;
	Verification verification = null;

	public Input(WebDriver driver, Generic generic, Verification verification, ExtentTest logger,
			 WebDriverWait globalWait) {
		this.driver = driver;
		this.generic = generic;
		this.logger = logger;
		this.globalWait = globalWait;
		this.verification = verification;
	}

	
	/**
	 * This sets value in text box and tab out
	 */
	public void setTextValueAndTabOut(WebElement element, String objDesc, String text) throws Exception, Error {
		try {

			if (!element.isEnabled()) {
				logger.log(LogStatus.FAIL,
						generic.changeColorUtility("Text Box", 1) + " " + generic.changeColorUtility(objDesc, 4) + " "
								+ generic.changeColorUtility("Is Disabled And Hence The Provided Text", 1) + " "
								+ generic.changeColorUtility(text.toUpperCase(), 3) + " " + generic.changeColorUtility(
										"CanNot Be Set And Method Name Is : SetTextValueandTabOut_Utility ", 1));
				Assert.fail();
			}
			element.clear(); // To clear the text value if available already
			element.sendKeys(text);
			element.sendKeys(Keys.TAB);
			logger.log(LogStatus.INFO, "Provided Text :" + " " + generic.changeColorUtility(text.toUpperCase(), 3) + " "
					+ "Is Set Successfully In WebElement :" + " " + generic.changeColorUtility(objDesc, 4));

		} catch (Exception | Error e) {
			throw (e);
		}
	}

	/**
	 This sets value in text box
	 */
	public void setValueUtility(WebElement element, String objDesc, String text) throws Exception, Error {
		try {
			if (!element.isEnabled()) {
				logger.log(LogStatus.FAIL,
						generic.changeColorUtility(objDesc, 4) + " "
								+ generic.changeColorUtility("Is Disabled Hence The Provided Text :", 1) + " "
								+ generic.changeColorUtility(text.toUpperCase(), 3) + " "
								+ generic.changeColorUtility("Cannot Be Set", 1));

				Assert.fail();
			}
			element.clear();// To clear text value if available already
			element.sendKeys(text);
			logger.log(LogStatus.INFO, "Provided Text :" + " " + generic.changeColorUtility(text.toUpperCase(), 3) + " "
					+ "Is Set Successfully In WebElement :" + " " + generic.changeColorUtility(objDesc, 4));

		} catch (Exception | Error e) {
			throw (e);
		}
	}

	/**
	 This selects and un select check box

	 */
	public void selectCheckBox(WebElement element, String state, String controlName) throws Exception, Error {
		try {
			String attributeValue;
			controlName = generic.changeColorUtility(controlName, 4);
			switch (state) {
			case "check":
				attributeValue = verification.attributeFetchUtility(element, "checked");
				if (attributeValue != null) {
					logger.log(LogStatus.INFO, controlName + "Is Already Checked");

				}
				if (attributeValue == null) {
					attributeValue = verification.attributeFetchUtility(element, "disabled");
					if (attributeValue == null) {
						element.click();
						logger.log(LogStatus.INFO, controlName + "Is Successfully Checked");

					} else {
						logger.log(LogStatus.FAIL, controlName + " "
								+ generic.changeColorUtility("Cannot Be Checked As It Is In Disabled State", 1));

					}

				}
				break;
			case "uncheck":
				attributeValue = verification.attributeFetchUtility(element, "checked");
				if (attributeValue == null) {
					logger.log(LogStatus.INFO, controlName + "Is Already Unchecked");

				}

				if (attributeValue != null) {
					attributeValue = verification.attributeFetchUtility(element, "disabled");
					if (attributeValue == null) {
						element.click();
						logger.log(LogStatus.INFO, controlName + "Is Successfully UnChecked");

					} else {
						logger.log(LogStatus.FAIL, controlName
								+ generic.changeColorUtility("Cannot Be Unchecked As It Is In Disabled State", 1));

					}
				}
			}
		} catch (Exception | Error e) {
			throw (e);
		}
	}

	/**
	 This selects radio button
	 */
	public void radioButtonSelectUtility(WebElement element, String controlName) throws Exception, Error {
		try {
			String attributeValue;
			controlName = generic.changeColorUtility(controlName, 4);
			attributeValue = verification.attributeFetchUtility(element, "disabled");
			if (attributeValue != null) {
				logger.log(LogStatus.FAIL,
						controlName + " " + generic.changeColorUtility("is Disabled Hence Selection Can Not Be Done", 1));

			}

			else {
				element.click();// Radio Button Is Selected
				logger.log(LogStatus.INFO, controlName + " " + "is Selected Successfully");

			}
		} catch (Exception | Error e) {
			throw (e);
		}
	}

	/**
	 This sets value in text box using Java Scripts
	 */
	public void javaScriptSetValue(WebElement element, String objDesc, String text) throws Exception, Error

	{
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].setAttribute('value', '" + text + "')", element);
			Thread.sleep(1000);
			logger.log(LogStatus.INFO, "Provided Text :" + " " + generic.changeColorUtility(text.toUpperCase(), 3) + " "
					+ "Is Set Successfully In WebElement :" + " " + generic.changeColorUtility(objDesc, 4));

		} catch (Exception | Error e)

		{
			throw (e);
		}
	}
}
