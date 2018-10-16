package Steps;

import PageObjects.CreateNewMailPage;
import PageObjects.DraftsPage;
import Singleton.DriverSingleton;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class DraftsSteps {
    private static final int IMPLICIT_DELAY = 10;
    private WebDriver driver;
    private  String mailReciever = "akwebdrivertest@mail.ru";
    private  String mailTopic = "Test Message";
    private  String mailContent = "Test Content";


    public void initBrowser() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(
                IMPLICIT_DELAY, TimeUnit.SECONDS);
    }

    public boolean searchforDrafts (){
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openPage();
        return (draftsPage.findMailInDraft()!= null);

    }

    public boolean isMailInDraftDissapeared() {
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openPage();
        draftsPage.clickOnReference();
        CreateNewMailPage createNewMailPage = new CreateNewMailPage(driver);
        createNewMailPage.clickSendButton();
        createNewMailPage.mailIsSentMessage();
        draftsPage.openPage();
        return draftsPage.findMailInDraft() == null;
    }
}
