package driverManageer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ChromeDriverCreator implements WebDriverCreator {

    private static final Logger logger = LogManager.getRootLogger();
    private ResourceBundle rb;
    private WebDriver chromeDriver;

    public WebDriver getWebDriver() {
        rb = ResourceBundle.getBundle("configurationFiles/driver_config");
        System.setProperty(rb.getString("drivers.chromedriver.name"), rb.getString("drivers.chromedriver.path"));
        chromeDriver = null;
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.manage().window().maximize();
        return chromeDriver;
    }

}
