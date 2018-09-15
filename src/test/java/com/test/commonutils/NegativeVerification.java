package com.test.commonutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class NegativeVerification extends com.test.basesetup.BaseSetup {
	
	String strValue;
	WebDriver driver = null;
	ExtentTest logger = null;
	WebDriverWait globalWait = null;
	Generic generic = null;
	
	public NegativeVerification(WebDriver driver, Generic generic, ExtentTest logger,  WebDriverWait globalWait){
		this.driver = driver;
		this.generic = generic;
		this.logger = logger;
		this.globalWait = globalWait;
	}

	/**
	 * @param sheetName
	 * @param elementName
	 * @param objectDescription
	 * @throws Exception
	 * @throws Error
	 */
	public void verifyElementNotExist(String sheetName, String elementName, String objectDescription ) throws Exception, Error {
		WebElement element = null;
		try {

			element = generic.getElement(sheetName, elementName);
			if (!(element == null)) {
			logger.log(LogStatus.FAIL,
						generic.changeColorUtility("Following Object", 1) + " " + objectDescription + " "
								+ generic.changeColorUtility(
										"Is Found On Application Screen And Hence Testing Is DisContinued", 1));
			
			Assert.fail(objectDescription + " - Element Exists"); }
		} catch (Exception | Error e) {
			if (e.toString().contains("NoSuchElementException")) {
					logger.log(LogStatus.PASS, "Following Object:" + " " + objectDescription + " "
							+ "Is Not Found On Application Screen And Hence We Can Continue With Testing");
			} else {
				throw (e);
			}

		}
	}


	/**
	verifyColumnsNotPresentInTable
	
	 */
	public void verifyColumnsNotPresentInTable(List<WebElement> element, String elementDesc, String expectedColumns
			) throws Exception, Error {

		int i;
		String columnsFound = "";
		List<String> ActualColumnsList = new ArrayList<String>();
		
		try {

			List<String> expectedColumnList = Arrays.asList(expectedColumns.split(","));

			for (i = 0; i < element.size(); i++) {
				ActualColumnsList.add(element.get(i).getText().toLowerCase());
			}

			for (i = 0; i < expectedColumnList.size(); i++) {
				if (ActualColumnsList.contains(expectedColumnList.get(i).trim().toLowerCase())) {
					if (columnsFound == "")
						columnsFound = expectedColumnList.get(i);
					else{
						columnsFound = columnsFound + ", " + expectedColumnList.get(i);
					}
				}
			}

			if (columnsFound == "") {
					logger.log(LogStatus.PASS, "Columns - " + generic.changeColorUtility(expectedColumns, 4)
					+ " In Table " + generic.changeColorUtility(elementDesc, 4) + " Are Not Displaying");

				

			} else {

					logger.log(LogStatus.FAIL,
							"Columns - " + generic.changeColorUtility(columnsFound, 4) + " In Table "
									+ generic.changeColorUtility(elementDesc, 4) + " Are Displaying");
				
				Assert.fail();
			}

		} catch (Throwable e) {
			throw (e);
		}

	}
	
	/**
	 * @author iahmad
	 * @param element
	 * @param elementDesc
	 * @param colNum
	 * @param value
	 * @param logval
	 * @throws Exception
	 * @throws Error
	 */
	public void verifyGivenValueNotPresentInColumn(WebElement element, String elementDesc, int colNum,
			String value) throws Exception, Error {
		int i;
		//int rowNum = -1;
		List<WebElement> elementAllRows;
		List<WebElement> elementAllColumns;
		String rowsHavingGivenValue = "";
		try {
			elementAllRows = element.findElements(By.tagName("tr"));
			for (i = 1; i < elementAllRows.size(); i++) {
				elementAllColumns = elementAllRows.get(i).findElements(By.tagName("td"));
				if (elementAllColumns.size() > colNum) {
					if (value.equals(elementAllColumns.get(colNum).getText())) {
						if (rowsHavingGivenValue == "")
							rowsHavingGivenValue = Integer.toString(i + 1);
						else
							rowsHavingGivenValue = rowsHavingGivenValue + ", " + Integer.toString(i + 1);

					}
				}

			}

			if (rowsHavingGivenValue == "") {
					logger.log(LogStatus.PASS,
							"Column - " + generic.changeColorUtility(String.valueOf(colNum), 4) + "  in Table - "
									+ generic.changeColorUtility(elementDesc, 4) + " Doest Not Contain Value - "
									+ generic.changeColorUtility(value, 2));
				
			} else {

					logger.log(LogStatus.FAIL,
							"Column - " + generic.changeColorUtility(String.valueOf(colNum), 4) + " in Table - "
									+ generic.changeColorUtility(elementDesc, 4)
									+ " Conains Value Other Than The Expected Value i.e. - "
									+ generic.changeColorUtility(value, 4) + " in Rows -"
									+ generic.changeColorUtility(rowsHavingGivenValue, 1));
				
				Assert.fail();
			}
		} catch (Exception | Error e) {

			throw (e);
		}

	}
}
