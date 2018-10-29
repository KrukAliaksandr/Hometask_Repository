package steps;

import buisnessObjects.Mail;
import buisnessObjects.User;
import driverManageer.RemoteDriverCreator;
import driverManageer.WebDriverDecorator;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageObjects.*;
import driverManageer.ChromeDriverCreator;
import org.openqa.selenium.WebDriver;

public class Steps {
    private static final int IMPLICIT_DELAY = 10;
    private static final String LAGUAGE_FILTER = "Java";
    private WebDriver driver;
    private String expectedGistName;
    private SpamPage spamPage;
    private InboxPage inboxPage;
    private TrashPage trashPage;

    public Steps(String selectedDriver) throws WebDriverException {
        switch (selectedDriver) {
            case "Chrome":
                driver = new ChromeDriverCreator().getWebDriver();
                break;
            case "Remote":
                driver = new RemoteDriverCreator().getWebDriver();
                break;
            default:
                driver = new ChromeDriverCreator().getWebDriver();
                break;
        }

    }

    public String checkForSuccessfulLogin() {
        LeftBarClass leftBarClass = new LeftBarClass(driver);
        return (leftBarClass.getAccountCurrentEmail()).getText();
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

    public void chooseFirstInboxMailAndDeleteIt() {
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

    public void chooseFirstInboxMailAndMarkItAsSpam() {
        inboxPage = new InboxPage(driver);
        inboxPage.readFirstMsgSubjectAndBody()
                .clickOnCheckboxWithId(0)
                .clickOnSpamBtn();

    }

    public boolean isMailPresentInSpamFolder() {
        SpamPage spamPage = new SpamPage(driver);
        spamPage.openPage();
        return spamPage.findMailInSpam() != null;
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
        driver.close();
        driver = null;
    }

}

