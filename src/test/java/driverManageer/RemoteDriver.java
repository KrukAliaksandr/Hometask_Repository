package driverManageer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RemoteDriver {

    private static WebDriver driver;
    private static final Logger logger = LogManager.getRootLogger();
    private static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String GECKODRIVER_CHROME_EXE_PATH = "D:\\work\\libraries\\chromedriver_win32\\chromedriver.exe";

    private RemoteDriver() {
    }

    public static WebDriver getDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://127.0.0.1:4445/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        driver.manage().timeouts().pageLoadTimeout(10
                , TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;

    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}