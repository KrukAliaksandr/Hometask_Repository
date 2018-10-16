package Steps;

import PageObjects.LeftBarClass;
import Singleton.DriverSingleton;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LeftBarClassSteps {
    private static final int IMPLICIT_DELAY = 10;
    private static final String LAGUAGE_FILTER = "Java";
    private WebDriver driver;
    private String expectedGistName;

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(
                IMPLICIT_DELAY, TimeUnit.SECONDS);
    }

    public void openDrafts(){
        LeftBarClass leftBarClass = new LeftBarClass(driver);
        leftBarClass.pressDraftsButton();
    }

}
