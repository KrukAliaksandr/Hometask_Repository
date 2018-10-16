package Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class DriverSingleton {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getRootLogger();
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String GECKODRIVER_CHROME_EXE_PATH = "D:\\work\\libraries\\chromedriver_win32\\chromedriver.exe";

    private DriverSingleton(){};


    public static WebDriver getDriver(){
        if (null == driver){
            System.setProperty(WEBDRIVER_CHROME_DRIVER,GECKODRIVER_CHROME_EXE_PATH);
            driver = new ChromeDriver();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
            logger.info("Browser started");
        }

        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
