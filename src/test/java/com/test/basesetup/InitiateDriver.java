package com.test.basesetup;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.test.commonutils.PropertyReader;

public class InitiateDriver extends BaseSetup {

    private WebDriver driver;

    public InitiateDriver(PropertyReader properties) throws Exception, Error {

        String hubIp = properties.getPropertyValue("HubIp");
        String hubPort = properties.getPropertyValue("HubPort");
        String url = "http://" + hubIp + ":" + hubPort + "/wd/hub";
        String browserName = properties.getPropertyValue("BrowserName");
        String execution = properties.getPropertyValue("Execution");
        String browserUrl = properties.getPropertyValue("BrowserURL");

        try {
            if (browserName.equalsIgnoreCase("IE")) {
                new DesiredCapabilities();
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                // capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS,
                // true);
                // capabilities.setCapability(BrowserType.EDGE, Platform.WIN10);
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
                        true);
                capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
                /*
                 * capabilities.setCapability(
                 * InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
                 */
                capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
                capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
                capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "none");

                if (execution.equalsIgnoreCase("Remote")) {

                    this.driver = new RemoteWebDriver(new URL(url), capabilities);

                } else {
                    System.setProperty("webdriver.ie.driver",
                            System.getProperty("user.dir") + "/SeleniumWebDrivers/IEDriverServer.exe");
                    this.driver = new InternetExplorerDriver(capabilities);
                }
            }

            else if (browserName.equalsIgnoreCase("Chrome")) {

                DesiredCapabilities cap = DesiredCapabilities.chrome();
                cap.setBrowserName("chrome");
                cap.setPlatform(Platform.WINDOWS);
                /*
                 * cap.setCapability("maxInstances", 1);
                 * cap.setCapability("maxSessions", 1);
                 */
                if (execution.equalsIgnoreCase("Remote")) {
                    driver = new RemoteWebDriver(new URL(url), cap);
                } else {
                    System.setProperty("webdriver.chrome.driver",
                            System.getProperty("user.dir") + "/SeleniumWebDrivers/chromedriver.exe");
                    driver = new ChromeDriver(cap);
                }
            }
            driver.navigate().to(browserUrl);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        } catch (Exception | Error e) {

            throw (e);
        }

    }

    public WebDriver getDriver() {
        return driver;
    }
}
