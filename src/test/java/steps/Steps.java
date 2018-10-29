package steps;

import buisnessObjects.Mail;
import buisnessObjects.User;
import driverManagers.RemoteDriverCreator;
import driverManagers.WebDriverDecorator;
import org.openqa.selenium.WebDriverException;
import pageObjects.*;
import driverManagers.ChromeDriverCreator;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class Steps {
    private static final int IMPLICIT_DELAY = 10;
    private static final String LAGUAGE_FILTER = "Java";
    private static WebDriver driver;
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

    public ArrayList<Mail> isMailPresentInDrafts() {
        LeftBarClass leftBarClass = new LeftBarClass(driver);
        return (leftBarClass.clickDraftsPage().findMailInDraft());
    }

    public void saveCreatedMailAndSendItFromDraft(Mail mail) {
        CreateNewMailPage createNewMailPage = new CreateNewMailPage(driver);
        createNewMailPage.pressCreateMessageButton().fillNewMailMessageAndSaveToDraft(mail);
        createNewMailPage.moveToDraftsAndAcceptAlert();
        DraftsPage draftsPage = new DraftsPage(driver);
        draftsPage.openPage().clickOnSavedMailInDrafts().clickSendButton();
    }

    public void chooseFirstInboxMailAndDeleteIt() {
        InboxPage inboxPage = new InboxPage(driver);
        inboxPage.readFirstMsgSubjectAndBody().deleteFirstMailUsingActions();
    }

    public void saveCreatedMailAndGoToDraftsPage() {
        CreateNewMailPage createNewMailPage = new CreateNewMailPage(driver);
        createNewMailPage.pressCreateMessageButton().fillNewMailMessageAndSaveToDraft(new Mail());
        createNewMailPage.moveToDraftsAndAcceptAlert();
        createNewMailPage.moveToDraftsAndAcceptAlert();
    }

    public void loginIntoMailRu(User user) {
        AcccountLoginPage loginPage = new AcccountLoginPage(driver);
        loginPage.openPage();
        loginPage.login(User.getMailLogin(),User.getMailPassword());
    }

    public ArrayList<Mail> isMailPresentInSent() {
        LeftBarClass leftBarClass = new LeftBarClass(driver);
        return  leftBarClass.clickSentPage().findMailInSent();
    }

    public void chooseFirstInboxMailAndMarkItAsSpam() {
        inboxPage = new InboxPage(driver);
        inboxPage.readFirstMsgSubjectAndBody()
                .clickOnCheckboxWithId(0)
                .clickOnSpamBtn();

    }

    public ArrayList<Mail> isMailPresentInSpamFolder() {
        SpamPage spamPage = new SpamPage(driver);
        spamPage.openPage();
        return spamPage.findMailInSpam();
    }

    public ArrayList<Mail> isDeletedMailPresentInTrashFolder() {
        trashPage = new TrashPage(driver);
        trashPage.openPage();
        return trashPage.checkForDeletedMessage();
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

