package com.test.commonutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.swing.JLabel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Generic extends com.test.basesetup.BaseSetup {

	int intRandomNum;
	String strGeneratedUcaseRandomString;
	String strAlertMessage;
	WebDriver driver = null;
	ExtentTest logger = null;
	WebDriverWait globalWait = null;
	PropertyReader properties = null;
	ExcelLib webElementExcel = null;
	final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public Generic() {

	}

	public Generic(WebDriver driver, ExtentTest logger, WebDriverWait globalWait, PropertyReader properties,
			ExcelLib webElementExcel) {
		this.driver = driver;
		this.logger = logger;
		this.globalWait = globalWait;
		this.properties = properties;
		this.webElementExcel = webElementExcel;
	}

	// ============================================================================================
	// FunctionName : current_dateinfo_utility
	// Description : To Retrieve The Current System Date in "yyyyMMdd"
	// Input Parameter : None
	// Revision : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================
	public String currentDateInfoUtility() throws Exception, Error {
		String Strdated = null;
		try {
			DateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
			Date date = new Date();
			Strdated = dateformat.format(date);

		} catch (Exception | Error e) {
			throw (e);
		}
		return Strdated;
	}

	// ============================================================================================
	// FunctionName : current_timeinfo_utility
	// Description : To Retrieve The Current System Time in "hhmmss" format
	// Input Parameter : None
	// Revision : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================
	public String currentTimeInfoUtility() throws Exception, Error {
		String Strdated = null;
		try {
			DateFormat dateformat = new SimpleDateFormat("hhmmss");
			Date date = new Date();
			Strdated = dateformat.format(date);
		} catch (Exception | Error e) {
			throw (e);
		}
		return Strdated;
	}

	// ============================================================================================
	// FunctionName : windows_messagehandler_utility
	// Description : Handles Any Type Of Windows Pop Up And Accepts Or Rejects
	// The Same (Say Clicking On Yes Or No Button)
	// Input Parameter : Instance Of The WebDriver Class 'Driver' And value Of
	// The String 'StrAccept'
	// // Revision : 0.1 - ImteyazAhmad-13-10-2016 - Added try catch specific to
	// NoAlertPresentException exception
	// : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================
	public static void windowsMessageHandlerUtility(WebDriver driver, String strAccept) throws Exception, Error {
		Alert alert;
		try {
			alert = driver.switchTo().alert();
			if (strAccept == "Yes") {

				alert.accept();

			} else {
				alert.dismiss();
			}
		} catch (NoAlertPresentException ex) {

		} catch (Exception | Error e) {
			throw (e);
		}

	}

	// ============================================================================================
	// FunctionName : random_intgeneration_utility
	// Description : To Generate A Random Number Based On The Maximum And
	// Minimum Integer Limit Provided
	// Input Parameter : IntMinRange, IntMaxRange Of The Type Integer To Specify
	// The Range Of The Generated Random Number
	// Revision : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================
	public int random_intgeneration_utility(int intMinRange, int intMaxRange) throws Exception, Error {
		try {
			Random rand = new Random();
			intRandomNum = rand.nextInt((intMaxRange - intMinRange) + 1) + intMinRange;
		} catch (Exception | Error e) {
			throw (e);
		}
		return intRandomNum;
	}

	// ============================================================================================
	// FunctionName : random_stringgeneration_utility
	// Description : To Generate A Random String
	// Input Parameter : IntStringLength Of The Type Integer Which Specifies
	// Character Length Of The Generated Random String, AppendString Of Type
	// String Which Is Appended To The Generated Random String
	// Revision : 0.1 - ImteyazAhmad-13-10-2016 - Added Code To Append Any
	// String Provided By The User To The Generated Random String
	// : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================
	public String random_stringgeneration_utility(int intStringLength, String appendString) throws Exception, Error {
		try {
			String GeneratedRandomString = RandomStringUtils.random(intStringLength, true, false);
			strGeneratedUcaseRandomString = GeneratedRandomString.toUpperCase();
			strGeneratedUcaseRandomString = appendString + strGeneratedUcaseRandomString;

		} catch (Exception | Error e) {
			throw (e);
		}
		return strGeneratedUcaseRandomString;
	}

	// ============================================================================================
	// FunctionName : webpage_refresh_utility
	// Description : Refreshes The WebPage
	// Input Parameter : None
	// Revision : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================
	public void webPageRefreshUtility() throws Exception, Error {
		try {

			driver.navigate().refresh(); // Web page Is Refreshed

			logger.log(LogStatus.INFO, "Web Page Is Refreshed");

		} catch (Exception | Error e) {

			logger.log(LogStatus.FAIL, "Web Page Was Not Refreshed Due To Following Reason:" + " "
					+ changeColorUtility(e.getMessage(), 1));
			throw (e);
		}
	}

	// ============================================================================================
	// FunctionName : windowswitching_utility
	// Description : To Switch The Multiple Windows That Are Opened In
	// Application
	// Input Parameter : None
	// Revision : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================
	public void windowSwitchingUtility() throws Exception, Error {
		try {

			globalWait.until(ExpectedConditions.numberOfWindowsToBe(2));

			String StrPrimaryWindowHandle = driver.getWindowHandle();
			Set<String> strWindowHandles = driver.getWindowHandles();
			Iterator<String> windowiterator = strWindowHandles.iterator();
			while (windowiterator.hasNext()) {
				String ChildWindow = windowiterator.next();
				if (!StrPrimaryWindowHandle.equalsIgnoreCase(ChildWindow)) {
					driver.switchTo().window(ChildWindow);
				}
			}
		} catch (Exception | Error e) {
			throw (e);
		}
	}

	// ============================================================================================
	// FunctionName : changecolor_utility
	// Description : Changes Text Color, Parameter 1 Is For Red, 2 For Green, 3
	// For Blue, 4 For Orange
	// Input Parameter : None
	// Revision : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================
	public String changeColorUtility(String text, int num) throws Exception, Error {
		String colouredText = null;
		JLabel label = new JLabel();
		try {
			switch (num) {
			case 1: // text color is changed to Red
				label.setText("<span style='color:red'><b>" + text + "</b></script></span>");
				colouredText = label.getText();
				break;
			case 2: // text color is changed to Green
				label.setText("<span style='color:green'><b>" + text + "</b></script></span>");
				colouredText = label.getText();
				break;
			case 3: // text color is changed to Blue
				label.setText("<span style='color:blue'><b>" + text + "</b></script></span>");
				colouredText = label.getText();
				break;
			case 4: // text color is changed to Orange
				label.setText("<span style='color:orange'><b>" + text + "</b></script></span>");
				colouredText = label.getText();
				break;
			}
		} catch (Exception | Error e) {
			throw (e);
		}
		return colouredText;
	}

	// ============================================================================================
	// FunctionName : getdata_fromdatabase_utility
	// Description : To Update,Delete,Match,Match Partially,Fetch Record From
	// DataBase
	// Input Parameter : Provide
	// DsnName,sqlQuery,OperationType,expectedvalue,ColumnName,logVal
	// Revision : 0.0 - ImteyazAhmad-13-10-2016
	// ============================================================================================

	public String getDataFromDataBaseUtility(String sqlQuery, String dataBaseName, String connectionString,
			String userName, String password, String operationType, String expectedvalue, String columnName)
			throws Exception, Error {
		Connection conn = null;
		try {
			ResultSet results;

			String value;
			int recordNumber;
			boolean found;
			found = false;
			// sqlQuery = sqlQuery.trim().toUpperCase();
			operationType = operationType.trim().toUpperCase();
			expectedvalue = expectedvalue.trim().toUpperCase();
			columnName = columnName.trim().toUpperCase();

			// Loading the required JDBC Driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// Creating a connection to the database
			conn = DriverManager.getConnection(connectionString, userName, password);
			if (conn == null) {
				logger.log(LogStatus.FAIL, changeColorUtility("The Connection To Database Is Not Established", 1));
			}
			Statement stat = conn.createStatement();
			switch (operationType) {
			case "UPDATE":

				recordNumber = stat.executeUpdate(sqlQuery);
				logger.log(LogStatus.PASS,
						"The Update Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
								+ "Is Executed Successfully On DataBase :" + " " + dataBaseName + ",  "
								+ "And Number Of Rows Updated Is :" + " "
								+ changeColorUtility(String.valueOf(recordNumber), 2));
				break;

			case "DELETE":

				recordNumber = stat.executeUpdate(sqlQuery);
				logger.log(LogStatus.PASS,
						"The Delete Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
								+ "Is Executed Successfully On DataBase :" + " " + dataBaseName + ",  "
								+ "And Number Of Rows Deleted Is :" + " "
								+ changeColorUtility(String.valueOf(recordNumber), 2));
				break;

			case "MATCH":

				results = stat.executeQuery(sqlQuery);
				if (results.next()) {
					do {
						value = results.getString(columnName);
						if (value.trim().equalsIgnoreCase(expectedvalue)) {
							logger.log(LogStatus.PASS, "The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
									+ "Is Executed Successfully On Dsn :" + " " + dataBaseName + ",  "
									+ "And Expected value : " + " " + changeColorUtility(expectedvalue, 3) + ",  "
									+ "Is Matched With Actual value :" + " " + changeColorUtility(value, 2));
						}
						found = true;
						break;

					} while (results.next());

					if (found == false) {
						logger.log(LogStatus.FAIL,
								"The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
										+ "Is Executed Successfully On Dsn :" + " " + dataBaseName + ",  "
										+ "But Expected value : " + " " + changeColorUtility(expectedvalue, 3) + "  "
										+ changeColorUtility(
												", Does Not Exists In ResultSet Hence Verification Can Not Be Done",
												1));
					}
				} else {
					logger.log(LogStatus.FAIL, "The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
							+ "Is Executed Successfully On Dsn :" + " " + dataBaseName + ",  "
							+ changeColorUtility("But ResultSet Has No Record Hence Verification Can Not Be Done", 1));

				}

				break;

			case "MATCH PARTIALLY":

				results = stat.executeQuery(sqlQuery);
				if (results.next()) {
					do {
						value = results.getString(columnName);
						if (value.trim().toUpperCase().contains(expectedvalue)) {
							logger.log(LogStatus.PASS, "The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
									+ "Is Executed Successfully On Dsn :" + " " + dataBaseName + ",  "
									+ "And Expected value : " + " " + changeColorUtility(expectedvalue, 3) + ",  "
									+ "Is Available In Actual value :" + " " + changeColorUtility(value, 2));

							found = true;
							break;
						}

					} while (results.next());

					if (found == false) {
						logger.log(LogStatus.FAIL,
								"The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
										+ "Is Executed Successfully On Dsn :" + " " + dataBaseName + ",  "
										+ "But Expected value : " + " " + changeColorUtility(expectedvalue, 3) + "  "
										+ changeColorUtility(
												", Does Not Exists In ResultSet Hence Verification Can Not Be Done",
												1));

					}
				} else {
					logger.log(LogStatus.FAIL, "The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
							+ "Is Executed Successfully On Dsn :" + " " + dataBaseName + ",  "
							+ changeColorUtility("But ResultSet Has No Record Hence Verification Can Not Be Done", 1));

				}
				break;

			case "FETCH":

				results = stat.executeQuery(sqlQuery);
				if (results.next()) {

					value = results.getString(columnName);
					logger.log(LogStatus.PASS,
							"The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
									+ "Is Executed Successfully On DataBase :" + " " + dataBaseName + ",  "
									+ " And Fetched value Is :" + " " + changeColorUtility(value, 2));

					return value;
				}

				else {
					logger.log(LogStatus.INFO, "The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
							+ "Is Executed Successfully On Dsn :" + " " + dataBaseName + ",  "
							+ changeColorUtility("But ResultSet Has No Record Hence value Can Not Be Fetched", 1));

				}
				break;

			case "MULTIPLEFETCH":

				results = stat.executeQuery(sqlQuery);
				value = "";
				while (results.next()) {
					if (value != "")
						value = value + "," + results.getInt(columnName);
					else
						value = Integer.toString(results.getInt(columnName));
				}
				if (value != "") {
					logger.log(LogStatus.PASS,
							"The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
									+ "Is Executed Successfully On DataBase :" + " " + dataBaseName + ",  "
									+ " And Fetched value Is :" + " " + changeColorUtility(value, 2));

					return value;
				}

				else {
					logger.log(LogStatus.FAIL, "The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
							+ "Is Executed Successfully On Dsn :" + " " + dataBaseName + ",  "
							+ changeColorUtility("But ResultSet Has No Record Hence value Can Not Be Fetched", 1));

				}
				break;

			case "MULTIPLEFETCHSTRING":
				results = stat.executeQuery(sqlQuery);
				value = "";
				while (results.next()) {
					if (value != "")
						value = value + "," + results.getString(columnName);
					else
						value = results.getString(columnName);
				}
				return value;

			}

		} catch (Exception | Error e) {
			logger.log(LogStatus.FAIL,
					"The Sql Query :" + " " + changeColorUtility(sqlQuery, 3) + ",  "
							+ "Is Not Executed Successfully, And Error Message Is : " + " "
							+ changeColorUtility(e.getMessage(), 1));

			throw (e);
		} finally {
			if (conn == null) {
			} else {
				conn.close();
			}
		}
		return null;
	}

	// ============================================================================================
	// FunctionName : WaitforpageToLoad
	// Revision : 0.0 - Biswajit-13-10-2016
	// ============================================================================================

	public void waitForPageToLoad() throws Exception, Error {
		try {

			JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

			// To Check Ajax call is complete
			for (int i = 0; i < Integer.valueOf(properties.getPropertyValue("GlobalWaitValue")); i++) {
				Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
				// return should be a number
				if (numberOfAjaxConnections instanceof Long) {
					Long n = (Long) numberOfAjaxConnections;
					if (n.longValue() == 0L) {
						break;
					}
				}
				Thread.sleep(1000);
			}

			// To check page ready state.
			for (int i = 0; i < Integer.valueOf(properties.getPropertyValue("GlobalWaitValue")); i++) {

				if (jsDriver.executeScript("return document.readyState").toString().equals("complete")) {
					return;
				}
				Thread.sleep(1000);
			}
		} catch (Exception | Error e) {
			// throw (e);
		}

	}

	// ============================================================================================
	// FunctionName : getElement
	// Description : To Fetch the locator type and it's value from Excel file
	// Input Parameter : String Type of SheetName, ElementName
	// Author : Imteyaz Ahmad

	public WebElement getElement(String sheetName, String elementName) throws Exception, Error {

		try {

			WebElement Element = null;
			String LocatorType;
			String Locatorvalue;
			LocatorType = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, 1);
			Locatorvalue = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, 2);
			Locatorvalue = Locatorvalue.trim();
			LocatorType = LocatorType.toUpperCase().trim(); 

				switch (LocatorType) {

				case "XPATH": // Find the Element using Xpath
					Element = driver.findElement(By.xpath(Locatorvalue));
					break;

				case "ID": // Find the Element using ID
					Element = driver.findElement(By.id(Locatorvalue));
					break;

				case "NAME": // Find the Element using NAME
					Element = driver.findElement(By.name(Locatorvalue));
					break;

				case "CLASSNAME": // Find the Element using ClassName
					Element = driver.findElement(By.className(Locatorvalue));
					break;

				case "CSS": // Find the Element using CSS
					Element = driver.findElement(By.cssSelector(Locatorvalue));
					break;

				case "LINKTEXT": // Find the Element using LinkText
					Element = driver.findElement(By.linkText(Locatorvalue));
					break;

				case "PARTIALLINKTEXT": // // Find the Element using Partial
										// Link
					// text
					Element = driver.findElement(By.partialLinkText(Locatorvalue));
					break;

				case "TAGNAME": // Find the Element using Tag Name
					Element = driver.findElement(By.tagName(Locatorvalue));
					break;
				}

			return Element;
		} catch (Exception | Error e) {

			throw (e);
		}

	}

	// ============================================================================================
	// FunctionName : getElements
	// Description : To Fetch the locator type and it's value from Excel file
	// Input Parameter : String Type of SheetName, ElementName
	// Author : Navneet Singhal

	public List<WebElement> getElements(String sheetName, String elementName) throws Exception, Error {

		try {
			List<WebElement> Element = null;
			String LocatorType;
			String Locatorvalue;
			LocatorType = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, 1);
			Locatorvalue = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, 2);
			Locatorvalue = Locatorvalue.trim();
			LocatorType = LocatorType.toUpperCase().trim();
			// value into UpperCase
			switch (LocatorType) {

			case "XPATH": // Find the Element using Xpath
				Element = driver.findElements(By.xpath(Locatorvalue));
				break;

			case "ID": // Find the Element using ID
				Element = driver.findElements(By.id(Locatorvalue));
				break;

			case "NAME": // Find the Element using NAME
				Element = driver.findElements(By.name(Locatorvalue));
				break;

			case "CLASSNAME": // Find the Element using ClassName
				Element = driver.findElements(By.className(Locatorvalue));
				break;

			case "CSS": // Find the Element using CSS
				Element = driver.findElements(By.cssSelector(Locatorvalue));
				break;

			case "LINKTEXT": // Find the Element using LinkText
				Element = driver.findElements(By.linkText(Locatorvalue));
				break;

			case "PARTIALLINKTEXT": // // Find the Element using Partial Link
				// text
				Element = driver.findElements(By.partialLinkText(Locatorvalue));
				break;

			case "TAGNAME": // Find the Element using Tag Name
				Element = driver.findElements(By.tagName(Locatorvalue));
				break;
			}

			return Element;
		} catch (Exception | Error e) {

			throw (e);
		}
	}

	// ============================================================================================
	// FunctionName : fileUploadToFtp
	// Description : To Upload File Into Caria Folder Using FTP Protocol
	// Input Parameter : server-ftpserverhost, port-portNumber,
	// user-ftpUsername, pass-ftpPassword, xmlFileName- file Name To Be Uploaded
	// Revision : 0.0 - ImteyazAhmad-28-10-2016
	// ============================================================================================

	public void fileUploadToFtp(String server, int port, String user, String pass, String xmlFileName)
			throws Exception, Error {
		FTPClient ftpClient = new FTPClient();
		try {

			ftpClient.connect(server, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();

			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

			File LocalFile = new File(System.getProperty("user.dir") + "\\SeleniumXMLTestData\\" + xmlFileName);

			String RemoteFile = "pub/test/caria/" + xmlFileName;
			InputStream inputStream = new FileInputStream(LocalFile);

			boolean done = ftpClient.storeFile(RemoteFile, inputStream);

			inputStream.close();
			if (done) {

				logger.log(LogStatus.INFO, "The File " + " " + changeColorUtility(xmlFileName, 3) + " "
						+ "Is Uploaded Successfully To FTP , Caria Folder");

			}

			else {
				logger.log(LogStatus.FAIL, "The File " + " " + changeColorUtility(xmlFileName, 3) + " "
						+ "Is Not Uploaded Successfully To FTP , Caria Folder");

				Assert.fail("File Not Uploaded");
			}

		} catch (Exception | Error e) {
			System.out.println("Error: " + e.getMessage());
			e.printStackTrace();

			logger.log(LogStatus.FAIL,
					"The File " + " " + changeColorUtility(xmlFileName, 3) + " "
							+ "Is Not Uploaded Successfully To FTP , Caria Folder , And Error Message Is " + " "
							+ changeColorUtility(e.getMessage(), 1));

			throw (e);
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException ex) {

			}
		}
	}

	// ============================================================================================
	// FunctionName : random_intgeneration_utility
	// Description : To Generate A Random Number Based On Number of Digit
	// Minimum Integer Limit Provided
	// Input Parameter : num Of The Type Integer To Specify
	// The number of digit of The Generated Random Number
	// Revision : 0.0 - Sumit Huria-23-11-2016
	// ============================================================================================
	public int randomIntGenerationUtility(int num) throws Exception, Error {
		int RandomNum = 1;
		int temp = 1;
		try {
			Random rand = new Random();
			for (int i = 1; i <= num; i++) {
				temp = temp * 10;

			}
			RandomNum = rand.nextInt(temp);

		} catch (Exception | Error e) {
			throw e;
		}
		return RandomNum;
	}

	// ============================================================================================
	// FunctionName : modifyXMLFileAmendApproval
	// Description : To pass existing Approval Number And update MessageID In
	// XML File for Amend Approval
	// Input Parameter : server-ftpserverhost, port-portNumber,
	// user-ftpUsername, pass-ftpPassword, fileName- file Name To Be Uploaded
	// Revision : 0.0 - Swati-06-12-2016
	// ============================================================================================
	public void modifyXmlFileAmendApproval(String xmlFileName, String approvalNumber, String version, int logVal)
			throws Exception, Error {
		synchronized (Generic.class) {
			String MessageID;
			try {
				MessageID = String.valueOf(randomIntGenerationUtility(7));
				String filepath = System.getProperty("user.dir") + "\\SeleniumXMLTestData\\" + xmlFileName;
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);

				// Set the Message ID
				doc.getElementsByTagName("MessageID").item(0).setTextContent(MessageID);

				// Set Approval Number
				doc.getElementsByTagName("ApprovalNumber").item(0).setTextContent(approvalNumber);

				// Set Version Number
				// version = version +1;
				doc.getElementsByTagName("Version").item(0).setTextContent(version);

				// write the content into xml file
				doc.getDocumentElement().normalize();
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filepath).getAbsolutePath());
				transformer.transform(source, result);

				// fileUploadToFtp(ftpServerHost, ftpServerPort,
				// ftpServerUserName, ftpServerPassword, xmlFileName, logVal);
				Thread.sleep(30000);

				logger.log(LogStatus.INFO,
						"The Approval Number :" + " " + changeColorUtility(approvalNumber, 3) + " " + "And MessageID :"
								+ " " + changeColorUtility(MessageID, 3) + " ," + "And Version :" + " "
								+ changeColorUtility(version, 3) + " ," + "Are Fetched Successfully From XML File :"
								+ " " + changeColorUtility(xmlFileName, 3));

			} catch (Exception e) {
				logger.log(LogStatus.FAIL,
						"Exception Occured while Updating XML Node  " + changeColorUtility(e.toString(), 1));

				throw e;
			}
		}
	}

	// ============================================================================================
	// FunctionName : xmlFileCopyUsingFileStreams
	// Description : To create a copy of valid XML
	// Input Parameter : cleanXmlFileName, copyXmlFileName
	// user-ftpUsername, pass-ftpPassword, fileName- file Name To Be Uploaded
	// Revision : 0.0 - Swati-08-12-2016
	// ============================================================================================

	public void xmlFileCopyUsingFileStreams(String cleanXmlFileName, String copyXmlFileName, int logVal)
			throws Exception, Error {
		synchronized (Generic.class) {
			String sourceFilePath = System.getProperty("user.dir") + "\\SeleniumXMLTestData\\" + cleanXmlFileName;
			String destinationFilePath = System.getProperty("user.dir") + "\\SeleniumXMLTestData\\" + copyXmlFileName;

			File fileToCopy = new File(sourceFilePath);
			FileInputStream input = new FileInputStream(fileToCopy);
			File newFile = new File(destinationFilePath);
			FileOutputStream output = new FileOutputStream(newFile);

			byte[] buf = new byte[1024];

			int bytesRead;

			try {
				while ((bytesRead = input.read(buf)) > 0)

				{

					output.write(buf, 0, bytesRead);

				}

				input.close();
				output.close();

				logger.log(LogStatus.INFO, "The Valid XML file :" + " " + changeColorUtility(cleanXmlFileName, 3) + " "
						+ "is copied  as :" + " " + changeColorUtility(copyXmlFileName, 3));

			} catch (Exception e) {
				logger.log(LogStatus.FAIL,
						"Exception Occured while copying XML file  " + changeColorUtility(e.toString(), 1));

				throw e;
			}
		}
	}

	// ============================================================================================
	// FunctionName : windowswitching_utility
	// Description : To Switch The Multiple Windows That Are Opened In
	// Application : MINT
	// Input Parameter : windowHandles and expectedNumberOfWindows
	// Revision : 0.0 - Navneet Singhal -13-12-2016
	// ============================================================================================
	public void windowSwitchingUtility(String windowHandles, int expectedNumberOfWindows) throws Exception, Error {
		String currentWindow;
		Set<String> strWindowHandles;
		Iterator<String> windowIterator;
		try {

			globalWait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));

			strWindowHandles = driver.getWindowHandles();
			windowIterator = strWindowHandles.iterator();
			while (windowIterator.hasNext()) {
				currentWindow = windowIterator.next();
				if (!windowHandles.contains(currentWindow)) // If

				{
					driver.switchTo().window(currentWindow);
				}
			}
		} catch (Exception | Error e) {
			throw (e);
		}
	}

	// ============================================================================================
	// FunctionName : convertDateToRequiredFormat
	// Description : To Convert Date To The Required Format
	// Application : MINT
	// Input Parameter : strDate, strCurrentDateFormat, strExpectedDateFormat
	// Revision : 0.0 - Navneet Singhal -19-12-2016
	// ============================================================================================
	public String convertDateToRequiredFormat(String strDate, String strCurrentDateFormat, String strExpectedDateFormat)
			throws Exception, Error {

		Date dateDate;
		String strDateInExpectedFormat = "";
		SimpleDateFormat formatter;
		SimpleDateFormat formatter2;
		try {

			formatter = new SimpleDateFormat(strCurrentDateFormat);
			dateDate = formatter.parse(strDate);
			formatter2 = new SimpleDateFormat(strExpectedDateFormat);
			strDateInExpectedFormat = formatter2.format(dateDate);
		} catch (Exception e) {
			Assert.fail();
		}
		return strDateInExpectedFormat;
	}

	/*
	 * 
	 * FunctionName : processXmlNodeUsingXpath Description :To Process Xml Node
	 * - update,delete,read,attributeupdate- Using Xpath Of That Particular Node
	 * Input Parameter : xmlFileName, ElementTagName, Xpath, OperationType,
	 * logVal Revision : 0.0 - Imteyaz Ahmad -19-12-2016
	 */

	public String processXmlNodeUsingXpath(String xmlFileName, String elementTagName, String xpath,
			String operationType) throws Exception, Error {

		Boolean processed = false;
		NodeList childNodeList;
		String value = null;

		try {

			String filepath = System.getProperty("user.dir") + "\\SeleniumXMLTestData\\" + xmlFileName;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			XPath xPath = XPathFactory.newInstance().newXPath();
			childNodeList = (NodeList) xPath.compile(xpath).evaluate(doc, XPathConstants.NODESET);
			List<String> elementTagList = Arrays.asList(elementTagName.split(";"));

			switch (operationType) {
			case "read":
				if (childNodeList.item(0).getNodeName().equals(elementTagList.get(0))) {
					value = childNodeList.item(0).getTextContent();
					processed = true;
					logger.log(LogStatus.INFO, "The XML Node :" + " " + changeColorUtility(xpath, 4) + " "
							+ "Has value " + " " + changeColorUtility(value, 2));

				}
				break;

			case "delete":

				if (childNodeList.item(0).getNodeName().equals(elementTagList.get(0))) {
					childNodeList.item(0).getParentNode().removeChild(childNodeList.item(0));
					processed = true;
					logger.log(LogStatus.INFO,
							"The XML Node :" + " " + changeColorUtility(xpath, 4) + " " + "Is Deleted Successfully");

				}
				break;

			case "update":
				if (childNodeList.item(0).getNodeName().equals(elementTagList.get(0))) {
					childNodeList.item(0).setTextContent(elementTagList.get(1));
					processed = true;
					logger.log(LogStatus.INFO,
							"The XML Node :" + " " + changeColorUtility(xpath, 4) + " "
									+ "Is Updated Successfully With value" + " "
									+ changeColorUtility(elementTagList.get(1), 3));

				}
				break;

			case "updateCdata":

				if (childNodeList.item(0).getNodeName().equals(elementTagList.get(0))) {
					Node newNode = doc.createElement(elementTagList.get(0));
					childNodeList.item(0).getParentNode().insertBefore(newNode, childNodeList.item(0));
					childNodeList.item(0).getParentNode().removeChild(childNodeList.item(0));
					Node cdata = doc.createCDATASection(elementTagList.get(1));
					newNode.appendChild(cdata);
					processed = true;
					logger.log(LogStatus.INFO,
							"The XML Node :" + " " + changeColorUtility(xpath, 4) + " "
									+ "Is Updated Successfully With value" + " "
									+ changeColorUtility(elementTagList.get(1), 3));

				}
				break;

			case "updateAttribute":

				if (childNodeList.item(0).getNodeName().equals(elementTagList.get(0))) {
					NamedNodeMap attr = childNodeList.item(0).getAttributes();
					Node nodeAttr = attr.getNamedItem(elementTagList.get(1));
					nodeAttr.setTextContent(elementTagList.get(2));
					processed = true;
					logger.log(LogStatus.INFO,
							"The Attribute" + " " + changeColorUtility(elementTagList.get(1), 4) + " " + "Of XML Node :"
									+ " " + changeColorUtility(xpath, 4) + " " + "Is Updated Successfully With value"
									+ " " + changeColorUtility(elementTagList.get(2), 3));

				}
				break;

			case "getAttribute":

				if (childNodeList.item(0).getNodeName().equals(elementTagList.get(0))) {
					NamedNodeMap attr = childNodeList.item(0).getAttributes();
					Node nodeAttr = attr.getNamedItem(elementTagList.get(1));
					value = nodeAttr.getTextContent();
					processed = true;
					logger.log(LogStatus.INFO,
							"The Attribute value " + changeColorUtility(elementTagList.get(1), 4) + " Of XML Node :"
									+ " " + changeColorUtility(xpath, 4) + " Is -  " + changeColorUtility(value, 3));

				}
				break;

			}
			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath).getAbsolutePath());
			transformer.transform(source, result);

			if (processed == false) {
				logger.log(LogStatus.FAIL,
						"Elemment Tag :" + " " + changeColorUtility(elementTagName, 4) + " "
								+ "Is Not Processed Successfully In XML" + " " + changeColorUtility(xmlFileName, 3)
								+ " " + "Owing To Provided Xpath Is Incorrect");

				Assert.fail();
			}

		} catch (Exception | Error e)

		{
			throw e;
		}

		return value;
	}

	/*
	 * =========================================================================
	 * =================== FunctionName : processXmlChildNodesUsingXpath
	 * Description : To Update All The Child Nodes At The Given Xpath Input
	 * Parameter : xmlFileName, Xpath, ElementTagName, ElementTagValue, logval
	 * Revision : 0.0 - Navneet Singhal -20-12-2016
	 * =========================================================================
	 * ===================
	 */

	public String processXmlChildNodesUsingXpath(String xmlFileName, String Xpath, String ElementTagName,
			String ElementTagValue) throws Exception, Error {

		NodeList nodeList;
		NodeList childNodeList;
		String value = null;
		int i;
		int j;

		try {

			String filepath = System.getProperty("user.dir") + "\\SeleniumXMLTestData\\" + xmlFileName;
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(filepath);
			XPath xPath = XPathFactory.newInstance().newXPath();
			nodeList = (NodeList) xPath.compile(Xpath).evaluate(doc, XPathConstants.NODESET);

			// Update Child Nodes
			for (i = 0; i < nodeList.getLength(); i++) {
				childNodeList = nodeList.item(i).getChildNodes();
				for (j = 0; j < childNodeList.getLength(); j++) {
					if (childNodeList.item(j).getNodeName() == ElementTagName) {
						childNodeList.item(j).setTextContent(ElementTagValue);
						break;
					}
				}
			}

			logger.log(LogStatus.INFO,
					"All XML Nodes : " + changeColorUtility(ElementTagName, 4) + " At Xpath "
							+ changeColorUtility(Xpath, 4) + " Updated Successfully With Value "
							+ changeColorUtility(ElementTagValue, 3));

			doc.getDocumentElement().normalize();
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(filepath).getAbsolutePath());
			transformer.transform(source, result);

		} catch (Exception | Error e)

		{
			throw e;
		}

		return value;
	}

	/*
	 * FunctionName : generateRandomString Description : To generate random
	 * string of user defined length Input Parameter : stringLength Revision :
	 * 0.0 - Kashif -18-04-2017
	 */

	public String generateRandomString(int stringLength) {

		StringBuffer randStr = new StringBuffer();
		try {
			for (int i = 0; i < stringLength; i++) {

				int number = getRandomNumber();

				char ch = CHAR_LIST.charAt(number);

				randStr.append(ch);

			}
		} catch (Exception | Error e)

		{
			throw e;
		}
		return randStr.toString();

	}

	/*
	 * FunctionName : getRandomNumber Description : To generate random number
	 * Input Parameter : N/A Revision : 0.0 - Kashif -18-04-2017
	 */

	private int getRandomNumber() {
		int randomInt = 0;
		try {
			Random randomGenerator = new Random();
			randomInt = randomGenerator.nextInt(CHAR_LIST.length());

			if (randomInt - 1 == -1) {
				return randomInt;
			} else {
				return randomInt - 1;
			}
		} catch (Exception | Error e)

		{
			throw e;
		}

	}

	// ============================================================================================
	// FunctionName : deleteXMLFile
	// Description : To Delete XML FIle
	// Input Parameter : xmlFileName
	// Revision : 0.0 - Navneet -16-05-2017
	// ============================================================================================

	public void deleteXMLFile(String xmlFileName) throws Exception, Error {

		try {
			File file = new File(System.getProperty("user.dir") + "\\SeleniumXMLTestData\\" + xmlFileName);
			if (file.delete()) {
				logger.log(LogStatus.PASS,
						"The XML file :" + " " + changeColorUtility(xmlFileName, 3) + " is deleted successfully");

			} else {
				logger.log(LogStatus.FAIL,
						"The XML file :" + " " + changeColorUtility(xmlFileName, 3) + " is not deleted successfully");

			}

		} catch (Exception e) {
			throw e;
		}
	}

	// ============================================================================================
	// FunctionName : waitUntilElementVisible
	// Description : To Wait Until Expected Element Is Visible
	// Input Parameter : waitUntilElementVisible
	// Revision : 0.0 - Navneet Singhal- 03-06-2017
	// ============================================================================================

	public void waitUntilElementVisible(WebElement element) throws Exception, Error {

		int loopCount = 0;
		Boolean loopFlag = true;

		try {
			Thread.sleep(1000);
			while (loopFlag && loopCount++ < 50) {
				try {
					globalWait.until(ExpectedConditions.visibilityOf(element));
					loopFlag = false;
				} catch (StaleElementReferenceException e1) {
					Thread.sleep(1000);
				}
			}
		} catch (Exception | Error e) {
			throw (e);

		}

	}

	/**
	 * @author iahmad Summary this is a custom wait method , this will check
	 *         visibility of element with delay of 1 seconds with max attempt of
	 *         500 else loop will break
	 */
	public void customWait(String sheetName, String elementName) throws Exception, Error {
		boolean found = false;
		int customPoling;
		try {
			customPoling = Integer.valueOf(properties.getPropertyValue("CustomeWaitPoling"));
			WebElement Element = null;
			String LocatorType;
			String Locatorvalue;
			LocatorType = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, 1);
			Locatorvalue = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, 2);
			Locatorvalue = Locatorvalue.trim();
			LocatorType = LocatorType.toUpperCase().trim(); // Convert the
															// Locator type
			// value into UpperCase
			loop: for (int i = 0; i < Integer.valueOf(properties.getPropertyValue("CustomWaitLoopCounter")); i++) {
				try {

					switch (LocatorType) {

					case "XPATH": // Find the Element using Xpath
						Element = driver.findElement(By.xpath(Locatorvalue));
						break;

					case "ID": // Find the Element using ID
						Element = driver.findElement(By.id(Locatorvalue));
						break;

					case "NAME": // Find the Element using NAME
						Element = driver.findElement(By.name(Locatorvalue));
						break;

					case "CLASSNAME": // Find the Element using ClassName
						Element = driver.findElement(By.className(Locatorvalue));
						break;

					case "CSS": // Find the Element using CSS
						Element = driver.findElement(By.cssSelector(Locatorvalue));
						break;

					case "LINKTEXT": // Find the Element using LinkText
						Element = driver.findElement(By.linkText(Locatorvalue));
						break;

					case "PARTIALLINKTEXT": // // Find the Element using Partial
											// Link
						// text
						Element = driver.findElement(By.partialLinkText(Locatorvalue));
						break;

					case "TAGNAME": // Find the Element using Tag Name
						Element = driver.findElement(By.tagName(Locatorvalue));
						break;
					}

					if (Element.isDisplayed()) {
						found = true;
					} else {
						found = false;
						Thread.sleep(customPoling);
						continue;
					}

				} catch (NoSuchElementException | StaleElementReferenceException e) {
					found = false;
					Thread.sleep(customPoling);
					continue;
				}
				if (found == true) {
					Thread.sleep(customPoling);
					break loop;
				}
			}

		} catch (Exception | Error e) {

			throw e;
		}

	}

	/*****
	 * @author bmohanty
	 * @param messageTxt
	 * @return
	 */
	public boolean waituntilTextPresentInUI(String messageTxt) {
		boolean flag = true;
		try {
			By byXpath = By.xpath("//*[contains(text(), '" + messageTxt + "')]");
			WebElement myElement = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.presenceOfElementLocated(byXpath));
			if (myElement.isDisplayed()) {
				logger.log(LogStatus.PASS, "Text is displaying Ui");
				return flag;
			} else {
				logger.log(LogStatus.FAIL, "Text is not displaying in UI");
				return false;
			}
		} catch (Exception | Error e) {
			throw e;
		}
	}

	// ============================================================================================
	// FunctionName : getElementWithNoDelay
	// Description : To Fetch the locator type and it's value from Excel file
	// Input Parameter : String Type of SheetName, ElementName
	// Author : Sumit Huria

	public WebElement getElementWithNoDelay(String sheetName, String elementName) throws Exception, Error {

		try {
			WebElement Element = null;
			String LocatorType;
			String Locatorvalue;
			LocatorType = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, 1);
			Locatorvalue = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, 2);
			Locatorvalue = Locatorvalue.trim();
			LocatorType = LocatorType.toUpperCase().trim(); // Convert the
															// Locator type
			// value into UpperCase
			switch (LocatorType) {

			case "XPATH": // Find the Element using Xpath
				Element = driver.findElement(By.xpath(Locatorvalue));
				break;

			case "ID": // Find the Element using ID
				Element = driver.findElement(By.id(Locatorvalue));
				break;

			case "NAME": // Find the Element using NAME
				Element = driver.findElement(By.name(Locatorvalue));
				break;

			case "CLASSNAME": // Find the Element using ClassName
				Element = driver.findElement(By.className(Locatorvalue));
				break;

			case "CSS": // Find the Element using CSS
				Element = driver.findElement(By.cssSelector(Locatorvalue));
				break;

			case "LINKTEXT": // Find the Element using LinkText
				Element = driver.findElement(By.linkText(Locatorvalue));
				break;

			case "PARTIALLINKTEXT": // // Find the Element using Partial Link
				// text
				Element = driver.findElement(By.partialLinkText(Locatorvalue));
				break;

			case "TAGNAME": // Find the Element using Tag Name
				Element = driver.findElement(By.tagName(Locatorvalue));
				break;
			}

			return Element;
		} catch (Exception | Error e) {

			throw (e);
		}
	}

	/**
	 * @author Imteyaz
	 * @Summary : This is used to kill driver instance
	 */
	public void closeApplication() throws Throwable {
		try {
			driver.quit();
			if (properties.getPropertyValue("BrowserName").equalsIgnoreCase("IE")) {
				killProcess("iedriver.exe");
			}

			else if (properties.getPropertyValue("BrowserName").equalsIgnoreCase("CHROME")) {
				//killProcess("chromedriver.exe");
			}

		} catch (Exception | Error e) {
			throw (e);
		}
	}

	/**
	 * @Summary : This is used to kill process
	 * @param processName- Name of process to be killed
	 * @throws Throwable
	 */
	public void killProcess(String processName) throws Throwable {
		String command;
		try {
			command = "taskkill /F /IM " + processName;
			Runtime.getRuntime().exec(command);
		} catch (Throwable t) {
			throw t;
		}
	}
	
	/**
	 * @author Imteyaz 
	 * @Summary To highlight webelement
	 * @param el
	 * @throws Throwable
	 */
	public void highlightElement(WebElement el) throws Throwable {
		try {
			JavascriptExecutor scriptExecutor = (JavascriptExecutor) driver;
			scriptExecutor.executeScript("arguments[0].style.border='3px solid red'", el);
			/*Thread.sleep(3000);
			scriptExecutor.executeScript("arguments[0].style.border=''", el);*/

		} catch (Throwable t) {
			throw t;
		}
	}
	
	/**
	 * @author Imteyaz 
	 * @Summary To bring particular element into view
	 * @param el
	 * @throws Throwable
	 */
	public void scrollIntoView(String sheetName, String elementName) throws Throwable {
		try {
			JavascriptExecutor scriptExecutor = (JavascriptExecutor) driver;
			scriptExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(sheetName, elementName));

		} catch (Throwable t) {
			throw t;
		}
	}
	
	/**
	 * 
	 * Summary , to return By reference 
	 */
	public By getByLocator(String sheetName, String elementName) throws Exception, Error {

		try {

			By locator = null;
			String LocatorType;
			String Locatorvalue;
			LocatorType = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, "LocatorType");
			Locatorvalue = webElementExcel.excelStringFetchDataUtility(sheetName, elementName, "LocatorValue");
			Locatorvalue = Locatorvalue.trim();
			LocatorType = LocatorType.toUpperCase().trim(); // Convert the
															// Locator type

			switch (LocatorType) {

			case "XPATH": // Find the Element using Xpath
				locator = By.xpath(Locatorvalue);
				break;

			case "ID": // Find the Element using ID
				locator = By.id(Locatorvalue);
				break;

			case "NAME": // Find the Element using NAME
				locator = By.name(Locatorvalue);
				break;

			case "CLASSNAME": // Find the Element using ClassName
				locator = By.className(Locatorvalue);
				break;

			case "CSS": // Find the Element using CSS
				locator = By.cssSelector(Locatorvalue);
				break;

			case "LINKTEXT": // Find the Element using LinkText
				locator = By.linkText(Locatorvalue);
				break;

			case "PARTIALLINKTEXT": // // Find the Element using Partial
									// Link
				// text
				locator = By.partialLinkText(Locatorvalue);
				break;

			case "TAGNAME": // Find the Element using Tag Name
				locator = By.tagName(Locatorvalue);
				break;
			}

			return locator;
		} catch (Exception | Error e) {

			throw (e);
		}
	}
}
