package Steps;

import PageObjects.CreateNewMailPage;
import PageObjects.UserMailPage;
import Singleton.DriverSingleton;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CreateNewMailSteps {
    private static final int IMPLICIT_DELAY = 10;
    private static final String LAGUAGE_FILTER = "Java";
    private WebDriver driver;
    private String expectedGistName;


    public void initBrowser() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(
                IMPLICIT_DELAY, TimeUnit.SECONDS);
    }

    public void fillNewMailAndSaveAsDraft(){
        CreateNewMailPage createNewMailPage = new CreateNewMailPage(driver);
        createNewMailPage.fillNewMailMessage(createNewMailPage.getMailReciever(),createNewMailPage.getMailTopic(),createNewMailPage.getMailContent());
    }


}
