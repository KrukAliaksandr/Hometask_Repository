package driverManagers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RemoteDriverCreator implements WebDriverCreator {

    private WebDriver remoteDriver;
    private static final Logger logger = LogManager.getRootLogger();

    public RemoteDriverCreator() {
    }

    public  WebDriver getWebDriver() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        remoteDriver = null;
        try {
            remoteDriver = new RemoteWebDriver(new URL("http://127.0.0.1:4445/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        remoteDriver.manage().timeouts().pageLoadTimeout(20
                , TimeUnit.SECONDS);
        remoteDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        remoteDriver.manage().window().maximize();
        return remoteDriver;

    }

}
