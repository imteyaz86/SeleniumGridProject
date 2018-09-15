package com.test.commonutils;

//In The Following Class Code Of The Functions That Are Used For Verification purpose Are Listed Such As Text Compare, Element Existence Verification, Table data Verification And

//Checked Or Unchecked State Verification For A CheckBox Is Present

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Verification extends com.test.basesetup.BaseSetup {
    String returnValue;
    String strActualVal1;
    String strActualVal2;
    int intTableRowsCount;
    boolean flag = false;

    WebDriver driver = null;
    ExtentTest logger = null;
    WebDriverWait globalWait = null;
    Generic generic = null;

    public Verification(WebDriver driver, Generic generic, ExtentTest logger, WebDriverWait globalWait) {
        this.driver = driver;
        this.generic = generic;
        this.logger = logger;
        this.globalWait = globalWait;
    }

    /**
     * Verify element exists
     */
    public void verifyElementExist(WebElement element, String objectDescription) throws Exception, Error {
        try {
            Assert.assertTrue(element != null);
            if (element != null) {
                logger.log(LogStatus.PASS, "Following Object:" + " " + generic.changeColorUtility(objectDescription, 4)
                        + " " + "Is Found On Application Screen And Hence We Can Continue With Testing");

            } else {
                logger.log(LogStatus.FAIL,
                        "Following Object:" + " " + generic.changeColorUtility(objectDescription, 4) + " "
                                + generic.changeColorUtility(
                                        "Is Not Found On Application Screen And Hence Testing Is DisContinued", 1));

            }
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * This fetches attribute value
     */
    public String attributeFetchUtility(WebElement element, String attributeToFetch) throws Exception, Error {// In

        try {
            returnValue = element.getAttribute(attributeToFetch);
        } catch (Exception | Error e) {
            throw (e);
        }
        return returnValue;
    }

    /**
     * Verify Text in table
     */
    public void verifyTextInTable(WebElement element, String searchText, int col1, String expVal1, int col2,
            String expVal2, String tableName) throws Exception, Error {
        try {
            int intTableRowsCount;
            int intTableColumnsCount;
            String intTableCellText;
            // Local Variable Declaration

            List<WebElement> reservetable_rows = element.findElements(By.tagName("tr"));
            intTableRowsCount = reservetable_rows.size();
            breakloop: for (int row = 0; row < intTableRowsCount; row++) {
                List<WebElement> table_rows_columns = reservetable_rows.get(row).findElements(By.tagName("td"));
                intTableColumnsCount = table_rows_columns.size();
                for (int column = 0; column < intTableColumnsCount; column++) {
                    intTableCellText = table_rows_columns.get(column).getText();
                    if (searchText.equalsIgnoreCase(intTableCellText)) {
                        strActualVal1 = table_rows_columns.get(col1).getText();
                        strActualVal2 = table_rows_columns.get(col2).getText();
                        if (strActualVal1.equalsIgnoreCase(expVal1) && strActualVal2.equalsIgnoreCase(expVal2)) {
                            logger.log(LogStatus.PASS, "For Table::" + " " + generic.changeColorUtility(tableName, 4)
                                    + " " + "And Searched Text Value::" + " "
                                    + generic.changeColorUtility(searchText, 4) + " " + "Expected Values::" + " "
                                    + generic.changeColorUtility(expVal1, 3) + " " + "And" + " "
                                    + generic.changeColorUtility(expVal2, 3) + " " + "Are Equal To Actual Values" + " "
                                    + generic.changeColorUtility(strActualVal1, 2) + " " + "And" + " "
                                    + generic.changeColorUtility(strActualVal2, 2) + " "
                                    + "Hence Table Value Verification Is Successful");

                        } else {

                            logger.log(LogStatus.FAIL, generic.changeColorUtility("For Table::", 1) + " "
                                    + generic.changeColorUtility(tableName, 4) + " "
                                    + generic.changeColorUtility("And Searched Text Value::", 1) + " "
                                    + generic.changeColorUtility(searchText, 4)
                                    + generic.changeColorUtility("Expected Values::", 1) + " "
                                    + generic.changeColorUtility(expVal1, 3) + " "
                                    + generic.changeColorUtility("And", 1) + " "
                                    + generic.changeColorUtility(expVal2, 3) + " "
                                    + generic.changeColorUtility("Are Not Equal To Actual Values::", 1) + " "
                                    + generic.changeColorUtility(strActualVal1, 2) + " "
                                    + generic.changeColorUtility("And", 1) + " "
                                    + generic.changeColorUtility(strActualVal2, 2) + " " + generic
                                            .changeColorUtility("Hence Table Value Verification Is Not Successful", 1));

                        }
                        break breakloop;
                    }
                }
            }
            Assert.assertTrue((strActualVal1.equalsIgnoreCase(expVal1)) && (strActualVal2.equalsIgnoreCase(expVal2)));
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * To verify enable or disable state of element
     */
    public void verifyEnableDisableState(WebElement element, String state, String controlName) throws Exception, Error {
        try {
            switch (state) {
            case "enable":
                if (element.isEnabled()) {
                    logger.log(LogStatus.PASS,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + "Is In Enabled State And Hence Enabled State Verification For" + " "
                                    + generic.changeColorUtility(controlName, 4) + " " + "Is Successful");

                } else {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility(
                                            "Is In Disabled State And Hence Enabled State Verification For", 1)
                                    + " " + generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility("Is Unsuccessful", 1));
                }

                Assert.assertTrue(element.isEnabled());
                break;
            case "disable":
                if (!element.isEnabled()) {
                    logger.log(LogStatus.PASS,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + "Is In Disabled State And Hence Disabled State Verification For" + " "
                                    + generic.changeColorUtility(controlName, 4) + " " + "Is Successful");

                } else {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility(
                                            "Is In Enabled State And Hence Disabled State Verification For", 1)
                                    + " " + generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility("Is Unsuccessful", 1));

                }
                Assert.assertFalse(element.isEnabled());
                break;

            }
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * To verify selected or not selected state of element
     */
    public void selectDeselectStateVerification(WebElement element, String state, String controlName)
            throws Exception, Error {
        try {
            switch (state) {
            case "select":
                if (element.isSelected()) {
                    logger.log(LogStatus.PASS,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + "Is In Selected State And Hence Selected State Verification For" + " "
                                    + generic.changeColorUtility(controlName, 4) + " " + "Is Successful");

                } else {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility(
                                            "Is In Deselected State And Hence Selected State Verification For", 1)
                                    + " " + generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility("Is Unsuccessful", 1));

                }
                Assert.assertTrue(element.isSelected());
                break;
            case "deselect":
                if (!element.isSelected()) {
                    logger.log(LogStatus.PASS,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + "Is In DeSelected State And Hence Deselected State Verification For" + " "
                                    + generic.changeColorUtility(controlName, 4) + " " + "Is Successful");

                } else {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility(
                                            "Is In Selected State And Hence Deselected State Verification For", 1)
                                    + " " + generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility("Is Unsuccessful", 1));
                }
                Assert.assertFalse(element.isSelected());
                break;

            }
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * To verify display or not displayed state of element
     */
    public void verifyElementDisplay(WebElement element, String state, String controlName) throws Exception, Error {
        try {
            switch (state) {
            case "displayed":
                if (element.isDisplayed()) {
                    logger.log(LogStatus.PASS,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + "Is Displayed On Application Screen And Hence Displayed State Verification For"
                                    + " " + generic.changeColorUtility(controlName, 4) + " " + "Is Successful");

                } else {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility(
                                            "Is Not Displayed On Application Screen And Hence Displayed State Verification For",
                                            1)
                                    + " " + generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility("Is Unsuccessful", 1));
                }
                Assert.assertTrue(element.isDisplayed());
                break;
            case "notdisplayed":
                if (!element.isDisplayed()) {
                    logger.log(LogStatus.PASS,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + "Is Not Displayed On Application Screen And Hence Not Displayed State Verification For"
                                    + " " + generic.changeColorUtility(controlName, 4) + " " + "Is Successful");

                } else {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility(
                                            "Is Not Displayed On Application Screen And Hence Not Displayed State Verification For",
                                            1)
                                    + " " + generic.changeColorUtility(controlName, 4) + " "
                                    + generic.changeColorUtility("Is Unsuccessful", 1));

                }
                Assert.assertFalse(element.isDisplayed());
                break;

            }
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * To compare text
     */
    public void compareText(String expectedString, String actualString, String objDesc) throws Exception, Error {
        try {
            String actualStringLabel;
            String expectedStringLabel;
            // Local Variable Declaration

            actualStringLabel = generic.changeColorUtility(actualString, 2);
            expectedStringLabel = generic.changeColorUtility(expectedString, 3);

            if (expectedString.equalsIgnoreCase(actualString)) {

                logger.log(LogStatus.PASS,
                        "Expected Value" + " " + expectedStringLabel + " " + "And Actual Value" + " "
                                + actualStringLabel + " " + "Are Same And Hence" + " " + objDesc + " "
                                + "Verification Is Successful");

            } else {
                logger.log(LogStatus.FAIL,
                        generic.changeColorUtility("Expected Value", 1) + " " + expectedStringLabel + " "
                                + generic.changeColorUtility("And Actual Value", 1) + " " + actualStringLabel + " "
                                + generic.changeColorUtility("Are Not Same And Hence", 1) + " " + objDesc + " "
                                + generic.changeColorUtility("Verification Is Not Successful", 1));

            }

            Assert.assertTrue(expectedString.equalsIgnoreCase(actualString));
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * To verify text in table
     */
    public void verifyTextInTable(WebElement element, String searchText, int col1, String expVal1, String tableName)
            throws Exception, Error {
        try {
            int intTableRowsCount;
            int intTableColumnsCount;
            String intTableCellText;
            // Local Variable Declaration

            List<WebElement> table_rows = element.findElements(By.tagName("tr"));
            intTableRowsCount = table_rows.size();
            breakloop: for (int row = 0; row < intTableRowsCount; row++) {
                List<WebElement> table_rows_columns = table_rows.get(row).findElements(By.tagName("td"));
                intTableColumnsCount = table_rows_columns.size();
                for (int column = 0; column < intTableColumnsCount; column++) {
                    intTableCellText = table_rows_columns.get(column).getText();
                    if (searchText.equalsIgnoreCase(intTableCellText)) {
                        strActualVal1 = table_rows_columns.get(col1).getText();
                        if (strActualVal1.equalsIgnoreCase(expVal1)) {
                            logger.log(LogStatus.PASS,
                                    "For Table::" + " " + generic.changeColorUtility(tableName, 4) + " "
                                            + "And Searched Text Value::" + " "
                                            + generic.changeColorUtility(searchText, 4) + " " + "Expected Value::" + " "
                                            + generic.changeColorUtility(expVal1, 3) + " " + "Is Equal To Actual Value"
                                            + " " + generic.changeColorUtility(strActualVal1, 2) + " " + "And" + " "
                                            + "Hence Table Value Verification Is Successful");

                        } else {
                            logger.log(LogStatus.FAIL, generic.changeColorUtility("For Table::", 1) + " "
                                    + generic.changeColorUtility(tableName, 4) + " "
                                    + generic.changeColorUtility("And Searched Text Value::", 1) + " "
                                    + generic.changeColorUtility(searchText, 4) + " "
                                    + generic.changeColorUtility("Expected Value::", 1) + " "
                                    + generic.changeColorUtility(expVal1, 3) + " "
                                    + generic.changeColorUtility("Is Not Equal To Actual Value", 1) + " "
                                    + generic.changeColorUtility(strActualVal1, 2) + " " + generic
                                            .changeColorUtility("Hence Table Value Verification Is Not Successful", 1));
                            break breakloop;
                        }
                    }
                }
            }
            Assert.assertTrue(strActualVal1.equalsIgnoreCase(expVal1));
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * To Verify Partial Text in table
     */
    public void verifyPartialTextInTable(WebElement element, String searchText, int col1, String expVal1,
            String expVal2, String tableName) throws Exception, Error {
        try {
            int intTableRowsCount;
            int intTableColumnsCount;
            String intTableCellText;
            // Local Variable Declaration

            List<WebElement> reservetable_rows = element.findElements(By.tagName("tr"));
            intTableRowsCount = reservetable_rows.size();

            breakloop: for (int row = 0; row < intTableRowsCount; row++) {
                List<WebElement> table_rows_columns = reservetable_rows.get(row).findElements(By.tagName("td"));
                intTableColumnsCount = table_rows_columns.size();
                for (int column = 0; column < intTableColumnsCount; column++) {
                    intTableCellText = table_rows_columns.get(column).getText();
                    if (searchText.equalsIgnoreCase(intTableCellText)) {
                        strActualVal1 = table_rows_columns.get(col1).getText();
                        if (strActualVal1.contains(expVal1) && strActualVal1.contains(expVal2)) {
                            logger.log(LogStatus.PASS, "For Table::" + " " + generic.changeColorUtility(tableName, 4)
                                    + " " + "And Searched Text Value::" + " "
                                    + generic.changeColorUtility(searchText, 4) + " " + "Expected Values::" + " "
                                    + generic.changeColorUtility(expVal1, 3) + " " + "And" + " "
                                    + generic.changeColorUtility(expVal2, 3) + " " + "Are Present In Actual Value" + " "
                                    + generic.changeColorUtility(strActualVal1, 2) + " "
                                    + "Hence Table Value Verification Is Successful");

                        } else {

                            logger.log(LogStatus.FAIL, generic.changeColorUtility("For Table::", 1) + " "
                                    + generic.changeColorUtility(tableName, 4) + " "
                                    + generic.changeColorUtility("And Searched Text Value::", 1) + " "
                                    + generic.changeColorUtility(searchText, 4) + " "
                                    + generic.changeColorUtility("Expected Values::", 1) + " "
                                    + generic.changeColorUtility(expVal1, 3) + " "
                                    + generic.changeColorUtility("And", 1) + " "
                                    + generic.changeColorUtility(expVal2, 3) + " "
                                    + generic.changeColorUtility("Are Not Present In Actual Value", 1) + " "
                                    + generic.changeColorUtility(strActualVal1, 2) + " " + generic
                                            .changeColorUtility("Hence Table Value Verification Is Not Successful", 1));

                        }

                        break breakloop;

                    }
                }
            }
            Assert.assertTrue((strActualVal1.contains(expVal1)) && (strActualVal1.contains(expVal2)));
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * Partial comparison of text
     * 
     */
    public void compareTextPartially(String expectedString, String actualString, String objDesc, int logVal)
            throws Exception, Error {
        try {
            String actualStringLabel;
            String expectedStringLabel;
            // Local Variable Declaration

            actualStringLabel = generic.changeColorUtility(actualString, 2);
            expectedStringLabel = generic.changeColorUtility(expectedString, 3);

            if (actualString.contains(expectedString)) {

                logger.log(LogStatus.PASS,
                        "Expected Value" + " " + expectedStringLabel + " " + "Is Contained In Actual Value" + " "
                                + actualStringLabel + " " + "Hence" + " " + objDesc + " "
                                + "Partial Text Verification Is Successful");
            } else {
                logger.log(LogStatus.FAIL, generic.changeColorUtility("Expected Value", 1) + " " + expectedStringLabel
                        + " " + generic.changeColorUtility("Is Not Present Partially In Actual Value", 1) + " "
                        + actualStringLabel + " "
                        + generic.changeColorUtility("Hence Partial Text Verification Is Not Successful For ", 1) + " "
                        + objDesc);
            }
            Assert.assertTrue(actualString.contains(expectedString));
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * To verify drop down value
     */
    public void VerifyDropDownValue(WebElement element, String objDescription, String expectedOptions)
            throws Exception, Error {

        boolean elementFoundFlag = false;
        String elementsNotfound = "";
        String[] exp = expectedOptions.split(";");
        try {
            Select select = new Select(element);

            List<WebElement> options = select.getOptions();

            for (int i = 0; i < exp.length; i++)

            {
                elementFoundFlag = false;
                for (WebElement we : options) {
                    if (we.getText().equals(exp[i])) {
                        elementFoundFlag = true;
                        break;
                    }
                }
                if (elementFoundFlag == false) {
                    elementsNotfound = elementsNotfound + ", " + exp[i];
                }

            }

            if (elementsNotfound == "") {
                logger.log(LogStatus.PASS,
                        "All The Expected Dropdown Options : " + generic.changeColorUtility(expectedOptions, 2)
                                + " Are Available In DropDown " + generic.changeColorUtility(objDescription, 4));

            } else {

                logger.log(LogStatus.FAIL,
                        generic.changeColorUtility(elementsNotfound, 1) + "Elements out of " + expectedOptions
                                + "Not Found In The DropDown" + generic.changeColorUtility(objDescription, 4));
                Assert.fail();

            }

        } catch (Exception | Error e)

        {
            throw (e);
        }

    }

    /**
     * Validate Drop down values
     */
    public void validateSelectedDropDownValue(WebElement element, String elementDesc, String expectedDropDownValue)
            throws Exception, Error {

        Select sel;
        String strActualDropDownValue;
        try {

            sel = new Select(element);
            strActualDropDownValue = sel.getFirstSelectedOption().getText();
            if (strActualDropDownValue.equals(expectedDropDownValue)) {
                logger.log(LogStatus.PASS, generic.changeColorUtility(expectedDropDownValue, 3)
                        + " Value Is Selected In The DropDown " + generic.changeColorUtility(elementDesc, 3));

            } else {

                logger.log(LogStatus.FAIL,
                        "Expected DropDown Value " + generic.changeColorUtility(expectedDropDownValue, 1)
                                + " Is Not Selected In The DropDown " + generic.changeColorUtility(elementDesc, 3)
                                + " As The Actual Selected Value Is "
                                + generic.changeColorUtility(strActualDropDownValue, 1));

                Assert.fail();

            }

        } catch (Exception e) {
            throw (e);
        }

    }
}
