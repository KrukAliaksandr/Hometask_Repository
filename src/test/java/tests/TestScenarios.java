package tests;

import buisnessObjects.Mail;
import buisnessObjects.User;
import steps.Steps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class TestScenarios {
    ResourceBundle rb;
    protected Steps steps;

    @BeforeClass(description = "Init browser and login")
    public void setUp() {
        rb  = ResourceBundle.getBundle("configurationFiles/driver_config");
        steps = new Steps(rb.getString("driver.chrome"));
        steps.loginIntoMailRu(User.getUser());
    }

    @Test
    public void isLoggedIn() {
        Assert.assertEquals(steps.checkForSuccessfulLogin(),User.getUserMailAddress());
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
//    @Test
//    public void deleteFirstMessageInInboxFolder() {
//        steps.chooseFirstInboxMailAndDeleteIt();
//        Assert.assertTrue(steps.isDeletedMailPresentInTrashFolder());
//    }
//
//    @Test
//    public void markFirstMessageInInboxFolderAsSpam() {
//        steps.chooseFirstInboxMailAndMarkItAsSpam();
//        Assert.assertTrue(steps.isMailPresentInSpamFolder());
//    }

}
