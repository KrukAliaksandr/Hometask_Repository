package Steps;

import PageObjects.*;
import Singleton.DriverSingleton;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Steps {
    private static final int IMPLICIT_DELAY = 10;
    private static final String LAGUAGE_FILTER = "Java";
    private WebDriver driver;
    private String expectedGistName;
    private SpamPage spamPage;
    private InboxPage inboxPage;
    private TrashPage trashPage;

    public Steps() {
        driver = DriverSingleton.getDriver();
    }

    public void chooseFirstMailAndMarkItAsSpam() {
        inboxPage = new InboxPage(driver);
        inboxPage.readFirstMsgSubjectAndBody()
                .clickOnCheckboxWithId(0)
                .clickOnSpamBtn();

    }

    public boolean isMailPresentInSpamFolder() {
        spamPage.openPage();
        return spamPage.getMsgIndexInListMarkedAsSpam(inboxPage.getFirstMsgSubjectAndBody()) >= 0;
    }

    public boolean isDeletedMailPresentInTrashFolder() {
        inboxPage = new InboxPage(driver);
        trashPage = new TrashPage(driver);
        trashPage.openPage();
        return trashPage.getDeletedMsgIndexInList("testtest") >= 0;
    }

    public void initBrowser() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(
                IMPLICIT_DELAY, TimeUnit.SECONDS);
    }

    public void fillNewMailAndSaveAsDraft() {
        CreateNewMailPage createNewMailPage = new CreateNewMailPage(driver);
        createNewMailPage.fillNewMailMessage(createNewMailPage.getMailReciever(), createNewMailPage.getMailTopic(), createNewMailPage.getMailContent());
    }

    public boolean searchforDrafts() {
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openPage();
        return (draftsPage.findMailInDraft() != null);

    }

    public boolean isMailInDraftDissapeared() {
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openPage();
        draftsPage.clickOnReference();
        CreateNewMailPage createNewMailPage = new CreateNewMailPage(driver);
        createNewMailPage.clickSendButton();
        createNewMailPage.mailIsSentMessage();
        draftsPage.openPage();
        return (draftsPage.findMailInDraft() == null);
    }

    public void chooseFirstMailAndDeleteIt() {
        InboxPage inboxPage = new InboxPage(driver);
        inboxPage.readFirstMsgSubjectAndBody()
                .clickOnCheckboxWithId(0)
                .clickOnDeleteMailBtn();
    }


    public void openDrafts() {
        CreateNewMailPage createNewMailPage = new CreateNewMailPage(driver);
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openPage();
        createNewMailPage.acceptAlert();
    }


    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

    public void loginIntoMailRu(String username, String password) {
        AcccountLoginPage loginPage = new AcccountLoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public boolean mailIsPresentInSentFolder() {
        SentPage sentPage = new SentPage(driver);
        sentPage.openPage();
        return sentPage.findSentMailInDraft();
    }

    public void createNewMail() {
        UserMailPage userMailPage = new UserMailPage(driver);
        userMailPage.pressCreateMessageButton();
    }

    public boolean checkForSuccessfulLogin() {
        UserMailPage userMailPage = new UserMailPage(driver);
        return (userMailPage.getUSERNAME()).equals(userMailPage.returnAccountEmail(userMailPage.getAccountCurrentEmail()));
    }

    public void logOut() {
        UserMailPage userMailPage = new UserMailPage(driver);
        userMailPage.pressLogoutButton();
    }

}

