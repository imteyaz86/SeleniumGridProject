package com.test.basesetup;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.test.commonutils.ReportLib;

/**
 * Purpose : This is a base class in which before suite and after suite
 * configuration is defined
 * 
 * @author iahmad
 *
 */

public class BaseSetup {
    public static ExtentReports reports;

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Throwable {
        try {
            updatePropertyFile();
            reports = new ReportLib().createExtentReportsInstance();

        } catch (Throwable e) {
            System.out.println("Error Occurred In Before Suite Configuration Is :" + e.toString());

        }
    }

    @AfterSuite(alwaysRun = true)
    public void exitSetUp() throws Throwable {
        try {
            reports.flush();
            reports.close();
            EmailableReport.generateEmailReport(ReportLib.path);
        } catch (Exception | Error e) {
            System.out.println("Error Occurred In After Suite Configuration Is :" + e.toString());
        }

    }

    /**
     * update Property File
     */
    private void updatePropertyFile() throws Throwable {
        PropertiesConfiguration config = new PropertiesConfiguration(
                BaseSetup.class.getClassLoader().getResource("utilities.properties"));
        config.setProperty("Execution", "Local");
        config.save();
    }

    /**
     * Read Environment Variable From CI Tool
     */
   /* private void readEnvVariablesFromCiTool() throws Throwable {

    }*/
}