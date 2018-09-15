package com.test.commonutils;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShotCapture {
     private WebDriver driver;
     private PropertyReader properties;
     private Generic generic;
	
	public ScreenShotCapture (WebDriver driver, Generic generic,PropertyReader properties ) {
		this.driver = driver;
		this.properties = properties;
		this.generic = generic;
	}

	public String captureScreenShots()
			throws Exception, Error {
		
	    String destinationPath;
		String dtmCurrentDateInfo;
		String dtmCurrentTimeInfo;
		int position;
		dtmCurrentDateInfo = generic.currentDateInfoUtility();
		dtmCurrentTimeInfo = generic.currentTimeInfoUtility();

		try {
			TakesScreenshot screenShotCapture = (TakesScreenshot) driver;
			File fileSource = screenShotCapture.getScreenshotAs(OutputType.FILE);
			destinationPath = properties.getPropertyValue("FailureScreenShotLocation") +  "_"
					+  "_" + dtmCurrentDateInfo + "_" + dtmCurrentTimeInfo + ".jpg";
			File destinationLocation = new File(destinationPath);
			FileUtils.copyFile(fileSource, destinationLocation);
			FileUtils.forceDelete(fileSource);
			position = destinationPath.indexOf('/');
			destinationPath = destinationPath.substring(position);
			destinationPath = ".." + destinationPath;

		} catch (Exception | Error e) {
			throw (e);
		}
		return destinationPath;
	}
}
