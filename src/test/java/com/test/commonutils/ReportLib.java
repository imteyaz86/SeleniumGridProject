package com.test.commonutils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ReportLib  {

	ExtentTest logger = null;
	ScreenShotCapture screenShotCapture = null;
	PropertyReader properties = null;
	Generic generic = null;
	public static String path ;
	
	public ReportLib(){
	}
	
	public ReportLib( ExtentTest logger, ScreenShotCapture screenShotCapture, Generic generic){
		this.logger = logger;
		this.screenShotCapture = screenShotCapture;
		this.generic = generic;
	}
	
	
	/**
	 Extent Report Utility
	 */
	public  ExtentReports createExtentReportsInstance() throws Throwable {
		ExtentReports extent;
		try {
			String extentReportsLocation;
			String extentReportHtmlName;
			String dtmCurrentTimeInfo;
			String dtmCurrentDateInfo;
			
			//Current Date Is Generated
			DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			dtmCurrentDateInfo = dateformat.format(date);
			//Current Time Is Generated
			DateFormat timeformat = new SimpleDateFormat("hhmmss");
			dtmCurrentTimeInfo = timeformat.format(date);

			/*dtmCurrentDateInfo = generic.currentDateInfoUtility();
			dtmCurrentTimeInfo = generic.currentTimeInfoUtility();*/
			properties = new PropertyReader("utilities.properties");
			extentReportHtmlName = properties.getPropertyValue("ExtentReportsHtmlName");
			extentReportsLocation = properties.getPropertyValue("ExtentReportsLocation");
			extentReportsLocation = extentReportsLocation + properties.getPropertyValue("BrowserName") + "_"
					+ extentReportHtmlName + "_" + dtmCurrentDateInfo + "_" + dtmCurrentTimeInfo + ".html";		
			extent = new ExtentReports(extentReportsLocation, true);
            path=extentReportsLocation;
		} catch (Exception | Error e) {
			System.out.println("Error Occurred In extentReportsInstanceUtility method is"+e.toString());
			throw (e);
		}
		return extent;
	}

	/**
	 Failure Reporting in extent
	 */
	public void extentFailureReport(String finalErrorMessage) throws Exception, Error {
		try {
				logger.log(LogStatus.FAIL,
						"Following Error Occurred  ::: " + generic.changeColorUtility(finalErrorMessage, 1)
								+ " "
								+ "And Hence The Test Case Is A Fail");
				logger.log(LogStatus.INFO, logger.addScreenCapture(
						screenShotCapture.captureScreenShots()));
			
		} catch (Exception | Error e) {
			throw (e);
		}
	}


	/**
	 To Get Failure Message
	 */
	public String getFailureMesssage(Throwable e) throws Exception, Error {
		String errorMessage = null;
		try {
			String errorMessageArray[];
			String exceptionRecorded = e.toString();
			if (exceptionRecorded.contains("Command")) {
				errorMessageArray = exceptionRecorded.split("Command");
				errorMessage = errorMessageArray[0];
			} else {
				errorMessage = exceptionRecorded;
			}

			for (StackTraceElement ObjectName : e.getStackTrace()) {
				if (ObjectName.toString().contains("testscript")) {
					errorMessage = errorMessage + " " + "And Error Occured In :" + " " + ObjectName.toString();
					break;
				}
			}

		} catch (Exception | Error m)

		{
			throw (m);
		}

		return errorMessage;

	}

}
