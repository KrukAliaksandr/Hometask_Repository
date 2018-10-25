package tests;

import buisnessObjects.Mail;
import buisnessObjects.User;
import steps.Steps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestScenarios {
    protected Steps steps;


    @BeforeClass(description = "Init browser and login")
    public void setUp() {
        steps = new Steps();
        steps.loginIntoMailRu(User.getUser());
    }

    @Test
    public void isLoggedIn() {
        Assert.assertEquals(steps.checkForSuccessfulLogin(),User.getMailLogin());
    }

    @Test
    public void createMailAndPutItInDraftsFolder() {
        steps.saveCreatedMailAndGoToDraftsPage();
        Assert.assertTrue(steps.isMailPresentInDrafts());
    }

    @Test
    public void checkSentMailInDraftsFolder() {
        steps.saveCreatedMailAndSendItFromDraft(Mail.getMail());
        Assert.assertFalse(steps.isMailPresentInDrafts());
    }

    @Test
    public void checkSentMailInSentFolder() {
        steps.saveCreatedMailAndSendItFromDraft(Mail.getMail());
        Assert.assertTrue(steps.isMailPresentInSent());
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


}
