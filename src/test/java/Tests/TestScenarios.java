package Tests;

import Steps.Steps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestScenarios {
    protected Steps steps;
    protected String addressee;
    private final String USERNAME = "webdrivertestak";
    private final String PASSWORD = "12345aa";

    @BeforeClass(description = "Init browser and login")
    public void setUp() {
        steps = new Steps();
        addressee = USERNAME;
        steps.loginIntoMailRu(USERNAME, PASSWORD);
    }

    @Test
    public void isLoggedIn() {
        Assert.assertTrue(steps.checkForSuccessfulLogin());
    }

    @Test
    public void deleteFirstMessageInInboxFolder() {
        steps.chooseFirstMailAndDeleteIt();
        Assert.assertTrue(steps.isDeletedMailPresentInTrashFolder());
    }


    @Test
    public void markFirstMessageInInboxFolderAsSpam() {
        steps.chooseFirstMailAndMarkItAsSpam();
        Assert.assertTrue(steps.isMailPresentInSpamFolder());
    }


    @Test
    public void createMailAndPutItInDraftsFolder() {
        steps.createNewMail();
        steps.fillNewMailAndSaveAsDraft();
        steps.openDraftsWithAcceptAlert();
        Assert.assertTrue(steps.searchforDrafts());

    }

    @Test
    public void checkSentMailInDraftsFolder() {
        steps.createNewMail();
        steps.fillNewMailAndSaveAsDraft();
        steps.openDraftsWithAcceptAlert();
        steps.sendMailFromDraft();
        Assert.assertTrue(steps.searchforDrafts());
    }

    @Test
    public void checkSentMailInSentFolder() {
        steps.createNewMail();
        steps.fillNewMailAndSaveAsDraft();
        steps.openDraftsWithAcceptAlert();
        steps.sendMailFromDraft();
        Assert.assertTrue(steps.mailIsPresentInSentFolder());
    }



}
