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
        Assert.assertEquals(steps.isMailPresentInDrafts().size(), 1);
    }

    @Test
    public void checkSentMailInDraftsFolder() {
        steps.saveCreatedMailAndSendItFromDraft(new Mail());
        Assert.assertTrue( steps.isMailPresentInDrafts().isEmpty());
    }

    @Test
    public void checkSentMailInSentFolder() {
        steps.saveCreatedMailAndSendItFromDraft(new Mail());
        Assert.assertEquals(steps.isMailPresentInSent().size(),1);
    }
    @Test
    public void deleteFirstMessageInInboxFolder() {
        steps.chooseFirstInboxMailAndDeleteIt();
        Assert.assertEquals(steps.isDeletedMailPresentInTrashFolder().size(),1);
    }

    @Test
    public void markFirstMessageInInboxFolderAsSpam() {
        steps.chooseFirstInboxMailAndMarkItAsSpam();
        Assert.assertEquals(steps.isMailPresentInSpamFolder().size(),1);
    }

}
