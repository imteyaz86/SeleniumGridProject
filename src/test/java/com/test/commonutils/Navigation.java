package com.test.commonutils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Navigation extends com.test.basesetup.BaseSetup {

    String strValue;
    WebDriver driver = null;
    ExtentTest logger = null;
    WebDriverWait globalWait = null;
    Generic generic = null;

    public Navigation(WebDriver driver, Generic generic, ExtentTest logger, WebDriverWait globalWait) {
        this.driver = driver;
        this.generic = generic;
        this.logger = logger;
        this.globalWait = globalWait;
    }

    /**
     * This selects web link
     */
    public void webLinkSelect(String linkText, String controlDesc) throws Exception, Error {
        boolean found = false;
        try {
            controlDesc = generic.changeColorUtility(controlDesc, 4);
            // LinkText = generic.changeColorUtility(LinkText, 4);

            for (int i = 0; i < 10; i++) {
                try {
                    globalWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText(linkText))));
                    driver.findElement(By.linkText(linkText)).click();
                    found = true;
                } catch (StaleElementReferenceException e) {
                    found = false;
                    continue;

                }

                if (found == true) {
                    break;
                }

            }
            logger.log(LogStatus.INFO,
                    "Following Link:" + " " + linkText + " " + "On" + controlDesc + " " + "Is Successfully Clicked");

        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * This selects web link using partial text
     */
    public void webPartialLinkSelect(String partialLinkText, String controlDesc) throws Exception, Error {
        boolean found = false;
        try {
            controlDesc = generic.changeColorUtility(controlDesc, 4);
            String parLinkText = generic.changeColorUtility(partialLinkText, 4);

            for (int i = 0; i < 10; i++) {
                try {
                    globalWait
                            .until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(partialLinkText)));
                    Thread.sleep(1000);
                    driver.findElement(By.partialLinkText(partialLinkText)).click();
                    found = true;
                } catch (StaleElementReferenceException e) {
                    found = false;
                    continue;

                }

                if (found == true) {
                    break;
                }

            }
            logger.log(LogStatus.INFO,
                    "Following Link:" + " " + parLinkText + " " + "On" + controlDesc + " " + "Is Successfully Clicked");

        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * This clicks element
     */
    public void objectClick(WebElement element, String controlDesc) throws Exception, Error {
        String controlDescLabel;
        controlDescLabel = generic.changeColorUtility(controlDesc, 4);
        try {
            if (element.isEnabled()) {
                element.click();
                logger.log(LogStatus.INFO, controlDescLabel + " " + "Is Successfully Clicked");

            }

            else {
                Assert.fail();
            }

        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * This double clicks element
     */
    public void objectDoubleClickUtility(WebElement element, String controlDesc) throws Exception, Error {
        String controlDescLabel;
        controlDescLabel = generic.changeColorUtility(controlDesc, 4);
        try {
            Actions oAction = new Actions(driver);
            oAction.doubleClick(element).build().perform();
            logger.log(LogStatus.INFO, controlDescLabel + " " + "Is Successfully DoubleClicked");

        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * This select a value in web table
     */
    public void selectWtObjectbyValue(WebElement element, String searchedText, String options) throws Exception, Error {

        try {
            options = options.toUpperCase();
            String found = "False";
            List<WebElement> tableRows = element.findElements(By.tagName("tr"));
            switch (options) {
            case "WEBCHECKBOX":
                outerloop: for (int i = 0; i <= tableRows.size() - 1; i++) {
                    List<WebElement> tablecol = tableRows.get(i).findElements(By.tagName("td"));
                    for (int j = 0; j <= tablecol.size() - 1; j++) {
                        String text = tablecol.get(j).getText();
                        if (text.equalsIgnoreCase(searchedText)) {
                            // tablecol.get(0).findElement(By.tagName("input")).click();
                            tablecol.get(0).click();
                            int Rows = Integer.valueOf(i) + 1;

                            logger.log(LogStatus.INFO,
                                    "The CheckBox Is Selected Successfully Correspoding To Unique Text :" + " "
                                            + generic.changeColorUtility(searchedText, 4) + " "
                                            + "Available In Row No : " + " " + Rows);

                            found = "True";
                            break outerloop;
                        }
                    }
                }
                if (found == "False") {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility("The provided text :", 1) + " "
                                    + generic.changeColorUtility(searchedText, 4) + " "
                                    + generic.changeColorUtility(
                                            "is not available in table hence corresponding CheckBox can not be selected ",
                                            1));

                    Assert.fail();
                }
                break;
            case "RADIOBUTTON":
                outerloop: for (int i = 0; i <= tableRows.size() - 1; i++) {
                    List<WebElement> tablecol = tableRows.get(i).findElements(By.tagName("td"));
                    for (int j = 0; j <= tablecol.size() - 1; j++) {
                        String text = tablecol.get(j).getText();
                        if (text.contains(searchedText)) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                                    tablecol.get(0).findElement(By.tagName("input")));
                            tablecol.get(0).findElement(By.tagName("input")).click();
                            int Rows = Integer.valueOf(i) + 1;
                            logger.log(LogStatus.INFO,
                                    "The RadioButton is selected successfully corresponding to unique text :" + " "
                                            + generic.changeColorUtility(searchedText, 4) + "  "
                                            + "available in row no :" + "  " + Rows);

                            found = "True";
                            break outerloop;
                        }
                    }
                }
                if (found == "False") {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility("The provided text :", 1) + " "
                                    + generic.changeColorUtility(searchedText, 4)
                                    + generic.changeColorUtility(
                                            "is not available in table hence corresponding  RadioButton can not be selected ",
                                            1));

                    Assert.fail();
                }
                break;
            case "LINK":
                outerloop: for (int i = 0; i <= tableRows.size() - 1; i++) {
                    List<WebElement> tablecol = tableRows.get(i).findElements(By.tagName("td"));
                    for (int j = 0; j <= tablecol.size() - 1; j++) {
                        String text = tablecol.get(j).getText();
                        if (text.equalsIgnoreCase(searchedText)) {
                            tablecol.get(j).click();
                            int Rows = Integer.valueOf(i) + 1;
                            logger.log(LogStatus.INFO,
                                    "The Link with text  :" + " " + generic.changeColorUtility(searchedText, 4)
                                            + " is available in table" + ", " + " " + "in row no :" + "  " + Rows
                                            + "and clicked successfully");

                            found = "True";
                            break outerloop;
                        }
                    }
                }
                if (found == "False") {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility("The Link with text :", 1) + " "
                                    + generic.changeColorUtility(searchedText, 4) + generic.changeColorUtility(
                                            " is not available in table and hence it can not be selected", 1));

                    Assert.fail();
                }
                break;
            }
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * This is used to perform right click
     */
    public void objectRightClickUtility(WebElement elementRightClick, String controlDesc) throws Exception, Error {
        String controlDescLabel;
        controlDescLabel = generic.changeColorUtility(controlDesc, 4);
        try {
            Actions oAction = new Actions(driver);
            oAction.moveToElement(elementRightClick);
            oAction.contextClick(elementRightClick).build().perform();
            logger.log(LogStatus.INFO, controlDescLabel + " " + "Is Successfully Right Clicked");

        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * @param element
     * @param controlDesc
     * @param logval
     * @throws Exception
     * @throws Error
     * @Method : This is to click an element using Action
     */
    public void objectActionsClick(WebElement element, String controlDesc) throws Exception, Error {
        String controlDescLabel;
        controlDescLabel = generic.changeColorUtility(controlDesc, 4);
        try {
            Actions action = new Actions(driver);
            action.moveToElement(element).click().build().perform();
            logger.log(LogStatus.INFO, controlDescLabel + " " + "Is Successfully Clicked");

        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * @param element
     * @param searchedText
     * @param options
     * @param logVal
     * @throws Exception
     * @throws Error
     * @author ksiddiqui
     */
    public void selectWebtblObjectbyValue(WebElement element, String searchedText, String options)
            throws Exception, Error {

        try {
            Actions action = new Actions(driver);
            options = options.toUpperCase();
            String found = "False";
            List<WebElement> tableRows = element.findElements(By.tagName("tr"));
            switch (options) {
            case "WEBCHECKBOX":
                outerloop: for (int i = 0; i <= tableRows.size() - 1; i++) {
                    List<WebElement> tablecol = tableRows.get(i).findElements(By.tagName("td"));
                    for (int j = 0; j <= tablecol.size() - 1; j++) {
                        String text = tablecol.get(j).getText();
                        if (text.equalsIgnoreCase(searchedText)) {
                            WebElement element2 = tablecol.get(0).findElement(By.tagName("div"))
                                    .findElement(By.xpath("label"));
                            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);
                            // element2.click();
                            // WebElement elementcheck =
                            // tablecol.get(0).findElement(By.cssSelector("div>label"));

                            action.moveToElement(element2).click(element2).build().perform();
                            // tablecol.get(0).click();
                            int Rows = Integer.valueOf(i) + 1;

                            logger.log(LogStatus.INFO,
                                    "The CheckBox Is Selected Successfully Correspoding To Unique Text :" + " "
                                            + generic.changeColorUtility(searchedText, 4) + " "
                                            + "Available In Row No : " + " " + Rows);

                            found = "True";
                            break outerloop;
                        }
                    }
                }
                if (found == "False") {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility("The provided text :", 1) + " "
                                    + generic.changeColorUtility(searchedText, 4) + " "
                                    + generic.changeColorUtility(
                                            "is not available in table hence corresponding CheckBox can not be selected ",
                                            1));

                    Assert.fail();
                }
                break;
            case "RADIOBUTTON":
                outerloop: for (int i = 0; i <= tableRows.size() - 1; i++) {
                    List<WebElement> tablecol = tableRows.get(i).findElements(By.tagName("td"));
                    for (int j = 0; j <= tablecol.size() - 1; j++) {
                        String text = tablecol.get(j).getText();
                        if (text.contains(searchedText)) {
                            tablecol.get(0).findElement(By.tagName("input")).click();
                            int Rows = Integer.valueOf(i) + 1;
                            logger.log(LogStatus.INFO,
                                    "The RadioButton is selected successfully corresponding to unique text :" + " "
                                            + generic.changeColorUtility(searchedText, 4) + "  "
                                            + "available in row no :" + "  " + Rows);

                            found = "True";
                            break outerloop;
                        }
                    }
                }
                if (found == "False") {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility("The provided text :", 1) + " "
                                    + generic.changeColorUtility(searchedText, 4)
                                    + generic.changeColorUtility(
                                            "is not available in table hence corresponding  RadioButton can not be selected ",
                                            1));

                    Assert.fail();
                }
                break;
            case "LINK":
                outerloop: for (int i = 0; i <= tableRows.size() - 1; i++) {
                    List<WebElement> tablecol = tableRows.get(i).findElements(By.tagName("td"));
                    for (int j = 0; j <= tablecol.size() - 1; j++) {
                        String text = tablecol.get(j).getText();
                        if (text.equalsIgnoreCase(searchedText)) {
                            tablecol.get(j).click();
                            int Rows = Integer.valueOf(i) + 1;
                            logger.log(LogStatus.INFO,
                                    "The Link with text  :" + " " + generic.changeColorUtility(searchedText, 4)
                                            + " is available in table" + ", " + " " + "in row no :" + "  " + Rows
                                            + "and clicked successfully");

                            found = "True";
                            break outerloop;
                        }
                    }
                }
                if (found == "False") {
                    logger.log(LogStatus.FAIL,
                            generic.changeColorUtility("The Link with text :", 1) + " "
                                    + generic.changeColorUtility(searchedText, 4) + generic.changeColorUtility(
                                            " is not available in table and hence it can not be selected", 1));

                    Assert.fail();
                }
                break;
            }
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * This selects web list
     */
    public void selectWebList(WebElement element, String value, String controlName) throws Exception, Error {
        try {
            Select dropdown = new Select(element);
            dropdown.selectByVisibleText(value);
            logger.log(LogStatus.INFO, "Value Selected In" + " " + generic.changeColorUtility(controlName, 4) + "Is"
                    + "::" + " " + generic.changeColorUtility(value, 3));
        } catch (Exception | Error e) {
            throw (e);
        }
    }

    /**
     * This selects web list
     */
    public void elementWebListSelectUtility(WebElement element, int value, String controlName) throws Exception, Error {
        try {
            Select dropdown = new Select(element);
            dropdown.selectByIndex(value);
            logger.log(LogStatus.INFO, "Value Selected In" + " " + generic.changeColorUtility(controlName, 4) + "Is"
                    + "::" + " " + generic.changeColorUtility(String.valueOf(value), 3));
        } catch (Exception | Error e) {
            throw (e);
        }
    }

}
