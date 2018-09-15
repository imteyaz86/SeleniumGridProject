package com.test.basesetup;

import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.test.commonutils.ReportLib;

public class EmailableReport {
	private static int recCount = 0;
    public static void generateEmailReport(String path) throws Throwable {
        ArrayList<String> epicPassFail = new ArrayList<String>();
        String[] epicArray;
        int counter=0;
        double passRatePer=0.0;
        BigDecimal passRateResult = new BigDecimal(0);
        BigDecimal epicPercent;
        BigDecimal limit= new BigDecimal(75.00);
        limit=limit.setScale(2, BigDecimal.ROUND_DOWN);
        WebDriver driver=null;
        String totalTests=null,passTests=null,passRate=null,failTests=null,skipTests=null;
        String reportPath= System.getProperty("user.dir")+"/"+path;
        String destinationPath=System.getProperty("user.dir")+"/EmailableReport/";
        String phantomJSExePath = System.getProperty("user.dir")
                + "/SeleniumWebDrivers/phantomjs.exe";
        
        
        try {
        	
            while (counter<10){
                if (new File(reportPath).exists()){
                    break;
                }else {
                    Thread.sleep(30000);
                    counter++;
                    continue;
                }
            }
           System.setProperty("phantomjs.binary.path",phantomJSExePath);
           driver = new PhantomJSDriver();
           
            System.out.println("Driver Instance Created Successfully"); 
            WebDriverWait wait = new WebDriverWait(driver,300);
            driver.navigate().to("file:///" + reportPath); 
            System.out.println("Opening File"); 
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            
            wait.until(ExpectedConditions.urlContains("html"));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='dashboard-view']/i")));
            driver.findElement(By.xpath("//a[@class='dashboard-view']/i")).click();                                 //clicking on dashboard of html page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dashboard-view")));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='total-tests']/span")));
            totalTests= driver.findElement(By.xpath("//span[@class='total-tests']/span")).getText();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='weight-light']/span)[1]")));
            passTests= driver.findElement(By.xpath("(//span[@class='weight-light']/span)[1]")).getText();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='weight-light']/span)[2]")));
            failTests = driver.findElement(By.xpath("(//span[@class='weight-light']/span)[2]")).getText();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[@class='weight-light']/span)[3]")));
            skipTests = driver.findElement(By.xpath("(//span[@class='weight-light']/span)[3]")).getText();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Pass Percentage']/following-sibling::span")));
            passRate = driver.findElement(By.xpath("//span[text()='Pass Percentage']/following-sibling::span")).getText();
            
            if (totalTests==null || passTests==null || failTests==null || skipTests==null ||passRate==null) {
            	Assert.fail("Test Stats Not Captured");
            }
           
            // convert two decimal places
            if(passRate != null && passRate != ""){
            passRatePer = ((Double.parseDouble(passTests))*100)/Double.parseDouble(totalTests);
            passRateResult = new BigDecimal(passRatePer);
            passRateResult=passRateResult.setScale(2, BigDecimal.ROUND_DOWN);
           
            }
            
            driver.findElement(By.xpath("//a[@class='categories-view']/i")).click();                                 //clicking on dashboard of html page
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cat-collection")));
            
            List<WebElement> epicList= driver.findElements(By.xpath("//ul[@id='cat-collection']/li"));
            for (int i=0 ; i<epicList.size(); i++) {
            String epic =epicList.get(i).getText();
           epicPassFail.add(epicDetails(epic));
            }
            
         // Create a folder if not exist21
			File directory = new File(destinationPath);
			if (!directory.exists()) {
				directory.mkdir();
			}
         			
            File resultFile = new File(destinationPath + "/emailreport.html");
            PrintWriter writer = new PrintWriter(resultFile);
            
            writer.write("<html><head><b><p><font color=blue>UI Automation Test Report : </font></p></b></head>");
            writer.write("<html><head><b><p><font color=blue>Overall Pass Rate : </font></p></b></head>");
            writer.append("<body>");
            writer.append("<table border=\"1\" bordercolor=\"#000000\">");
            writer.append("<tr><td><b> Total Tests </b></td><td><b> Passed </b></td><td><b> Failed </b></td><td><b> Skipped </b></td><td><b> Pass Percentage </b></td></tr>");
            writer.append("<tr><td>"+totalTests+"</td><td><p><font color=green>"+passTests+"</font></p></td><td><p><font color=red>"+failTests+"</font></p></td><td>"+skipTests+"</td><td>"+passRateResult+"</td></tr>");
            writer.append("</table>");
            writer.append("</body>");
            writer.append("</html>");
            
            writer.write("<html><head><b><p><font color=blue>Epic Pass Rate : </font></p></b></head>");
            writer.append("<body>");
            writer.append("<table border=\"1\" bordercolor=\"#000000\">");
            writer.append("<tr><td><b> Epic Name </b></td><td><b> Passed </b></td><td><b> Failed </b></td><td><b> Pass Percentage </b></td></tr>");
            
            for (int i=0; i<epicPassFail.size(); i++) {
            if (epicPassFail.contains(null))  {
                continue;
            }
            epicArray = epicPassFail.get(i).split(";");
            epicPercent = convertToPercentage(epicArray[1],epicArray[2]);
            if (epicPercent.compareTo(limit)>=0) {
                writer.append("<tr><td>"+epicArray[0]+"</td><td><p><font color=green>"+epicArray[1]+"</font></p></td><td><p><font color=red>"+epicArray[2]+"</font></p></td><td style=background-color:green>"+epicPercent+"</td></tr>");  
            }
            else {
                writer.append("<tr><td>"+epicArray[0]+"</td><td><p><font color=green>"+epicArray[1]+"</font></p></td><td><p><font color=red>"+epicArray[2]+"</font></p></td><td style=background-color:red>"+epicPercent+"</td></tr>");   
            }
            }
            writer.append("</table>");
            writer.append("</body>");
            writer.append("</html>");
            writer.close();
            System.out.println("Email Report Generation Complete!");
        }
        
        catch (Throwable e) {
            System.out.println("Error Occurred In generateEmailReport Method = " + e.getMessage());
            recCount++;
            if (driver !=null) {
            driver.quit();
            }
            Thread.sleep(30000);
            if (recCount<15) {
            	generateEmailReport(ReportLib.path);
            }
		} finally {
			try {
				driver.quit();
			} catch (Throwable t) {

			}
		}
    }
    
    private static String epicDetails(String epic) throws Throwable {
       String epicName=null;
       String subString;
       String pass=null;
       String fail=null;
        
        try {
            
            if (epic.contains("PASS:")&& epic.contains("FAIL:") && !epic.contains("OTHERS:")) {
                epicName = epic.substring(0, epic.indexOf("PASS:"));
                subString = epic.substring( epic.indexOf("PASS:"));
                pass = subString.substring(subString.indexOf(":")+1,subString.indexOf("FAIL:"));
                fail = subString.substring(subString.lastIndexOf(":")+1);
                
            }
            
            else if (!epic.contains("OTHERS:") && !epic.contains("PASS:")&& epic.contains("FAIL:")){
                epicName = epic.substring(0, epic.indexOf("FAIL:"));
                subString = epic.substring( epic.indexOf("FAIL:"));
                pass = " 0";
                fail = subString.substring(subString.indexOf(":")+1);
            }
            
            else if (epic.contains("PASS:")&& !epic.contains("FAIL:")&& !epic.contains("OTHERS:")) {
                epicName = epic.substring(0, epic.indexOf("PASS:"));
                subString = epic.substring( epic.indexOf("PASS:"));
                pass = subString.substring(subString.indexOf(":")+1);
                fail=" 0";
            }
        }catch(Throwable t){
            
            throw t;
        }
        
        return epicName+";"+pass+";"+fail;
    }
    
    public static BigDecimal convertToPercentage(String pass, String fail) throws Throwable {
       double totalTests ;
       double passRatePer=0.0;
       BigDecimal passRateResult = new BigDecimal(0);
        try {
            totalTests = Double.parseDouble(pass) + Double.parseDouble(fail);
            passRatePer = ((Double.parseDouble(pass))*100)/totalTests;
            passRateResult = new BigDecimal(passRatePer);
            passRateResult=passRateResult.setScale(2, BigDecimal.ROUND_DOWN);  
        }catch (Throwable t){
            throw t;
        }
        return passRateResult;
    }
}
