package Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
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
            driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
            driver.manage().window().maximize();
        }

        return driver;
    }

//    public static WebDriver getDriver(){
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//        WebDriver driver = null;
//        try {
//            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4445/wd/hub"),capabilities);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        return  driver;
//
//    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
