package Tests;

import Steps.Steps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestScenarios {
    protected Steps steps;
    protected String addressee;
    private final String USERNAME = "aliaksandrkrukwd@mail.ru";
    private final String PASSWORD = "12345aa";

    @BeforeClass(description = "Init browser and login")
    public void setUp() {
        steps = new Steps();
        addressee = USERNAME;
        steps.loginIntoMailRu(USERNAME, PASSWORD);
    }

//    @Test
//    public void isLoggedIn() {
//        Assert.assertTrue(steps.checkForSuccessfulLogin());
//    }

//    @Test
//    public void createMailAndPutItInDraftsFolder() {
//        steps.saveCreatedMailAndGoToDraftsPage();
//        Assert.assertTrue(steps.isMailPresentInDrafts());
//    }

    @Test
    public void checkSentMailInDraftsFolder() {
        steps.saveCreatedMailAndSendItFromDraft();
        Assert.assertFalse(steps.isMailPresentInDrafts());
    }

    @Test
    public void checkSentMailInSentFolder() {
        steps.saveCreatedMailAndSendItFromDraft();
        Assert.assertTrue(steps.isMailPresentInSent());
    }

//    @Test
//    public void deleteFirstMessageInInboxFolder() {
//        steps.chooseFirstMailAndDeleteIt();
//        Assert.assertTrue(steps.isDeletedMailPresentInTrashFolder());
//    }
//
//
//    @Test
//    public void markFirstMessageInInboxFolderAsSpam() {
//        steps.chooseFirstMailAndMarkItAsSpam();
//        Assert.assertTrue(steps.isMailPresentInSpamFolder());
//    }


}
