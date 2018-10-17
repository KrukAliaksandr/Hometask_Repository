package Steps;

import PageObjects.SentPage;
import Singleton.DriverSingleton;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class SentPageSteps {
    private static final int IMPLICIT_DELAY = 10;
    private static final String LAGUAGE_FILTER = "Java";
    private WebDriver driver;

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(
                IMPLICIT_DELAY, TimeUnit.SECONDS);
    }
    public boolean mailIsPresentInSentFolder(){
        SentPage sentPage = new SentPage(driver);
        sentPage.openPage();
        return sentPage.findSentMailInDraft();
    }
}
