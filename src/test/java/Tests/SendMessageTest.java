package Tests;

import Steps.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class SendMessageTest {

    private Steps steps;
    private final String USERNAME = "akwebdrivertest";
    private final String PASSWORD = "San9san90394";

    @BeforeMethod(description = "Init browser")
    public void setUp() {

        steps = new Steps();
        steps.initBrowser();

    }

    @Test
    public void loginMailRu() {
        steps.loginIntoMailRu(USERNAME, PASSWORD);
        Assert.assertTrue(steps.checkForSuccessfulLogin());

    }
    @Test(dependsOnMethods = "loginMailRu")
    public void createMailAndPutItInDraftsFolder() {
        steps.createNewMail();
        steps.fillNewMailAndSaveAsDraft();
        steps.openDrafts();
        Assert.assertTrue(steps.searchforDrafts());

    }
    @Test(dependsOnMethods = "createMailAndPutItInDraftsFolder")
    public void checkSentMailInDraftsFolder() {

        Assert.assertTrue(steps.isMailInDraftDissapeared());

    }
    @Test(dependsOnMethods = "checkSentMailInDraftsFolder")
    public void checkSentMailInSentFolder() {

        Assert.assertTrue(steps.mailIsPresentInSentFolder());
        steps.logOut();
    }

}


