package steps;

import buisnessObjects.Mail;
import buisnessObjects.User;
import pageObjects.*;
import driverManageer.DriverSingleton;
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


    public void initBrowser() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(
                IMPLICIT_DELAY, TimeUnit.SECONDS);
    }

    public String checkForSuccessfulLogin() {
        UserMailPage userMailPage = new UserMailPage(driver);
        return (userMailPage.returnAccountEmail(userMailPage.getAccountCurrentEmail()));
    }

    public boolean isMailPresentInDrafts() {
        LeftBarClass leftBarClass = new LeftBarClass(driver);
        return (leftBarClass.clickDraftsPage().findMailInDraft() != null);
    }

    public void saveCreatedMailAndSendItFromDraft(Mail mail) {
        CreateNewMailPage createNewMailPage = new CreateNewMailPage(driver);
        createNewMailPage.pressCreateMessageButton().fillNewMailMessageAndSaveToDraft(Mail.getMailAddressee(),Mail.getMailTopic(),Mail.getMailContent());
        createNewMailPage.moveToDraftsAndAcceptAlert();
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openPage().clickOnSavedMailInDrafts(draftsPage.findMailInDraft()).clickSendButton();
    }

    public void chooseFirstMailAndDeleteIt() {
        InboxPage inboxPage = new InboxPage(driver);
        inboxPage.readFirstMsgSubjectAndBody().deleteFirstMailUsingActions();
    }

    public void saveCreatedMailAndGoToDraftsPage() {
        LeftBarClass leftBarClass = new LeftBarClass(driver);
        CreateNewMailPage createNewMailPage = leftBarClass.pressCreateMessageButton();
        createNewMailPage.fillNewMailMessageAndSaveToDraft(Mail.getMailAddressee(),Mail.getMailTopic(),Mail.getMailContent());
        DraftsPage draftsPage = leftBarClass.clickDraftsPage();
        createNewMailPage.moveToDraftsAndAcceptAlert();
    }

    public void loginIntoMailRu(User user) {
        AcccountLoginPage loginPage = new AcccountLoginPage(driver);
        loginPage.openPage();
        loginPage.login(User.getMailLogin(),User.getMailPassword());
    }

    public boolean isMailPresentInSent() {
        LeftBarClass leftBarClass = new LeftBarClass(driver);
        return  leftBarClass.clickSentPage().findMailInSent()!= null;
    }

    public void chooseFirstMailAndMarkItAsSpam() {
        inboxPage = new InboxPage(driver);
        inboxPage.readFirstMsgSubjectAndBody()
                .clickOnCheckboxWithId(0)
                .clickOnSpamBtn();

    }

    public boolean isMailPresentInSpamFolder() {
        SpamPage spamPage = new SpamPage(driver);
        spamPage.openPage();
        return spamPage.getMsgIndexInListMarkedAsSpam() >= 0;
    }

    public boolean isDeletedMailPresentInTrashFolder() {
        trashPage = new TrashPage(driver);
        trashPage.openPage();
        return trashPage.checkForDeletedMessage() >= 0;
    }
//    public void logOut() {
//        UserMailPage userMailPage = new UserMailPage(driver);
//        userMailPage.pressLogoutButton();
//    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

}
