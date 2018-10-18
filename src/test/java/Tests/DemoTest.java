package Tests;

import Steps.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class DemoTest {

    private Steps steps;
    private final String USERNAME = "akwebdrivertest";
    private final String PASSWORD = "San9san90394";

    @BeforeMethod(description = "Init browser")
    public void setUp() {

        steps = new Steps();
        steps.initBrowser();

    }

    @Test
    public void oneCanLoginMailRu() {
        steps.loginIntoMailRu(USERNAME, PASSWORD);
        Assert.assertTrue(steps.checkForSuccessfulLogin());
        steps.createNewMail();
        steps.fillNewMailAndSaveAsDraft();
        steps.openDrafts();
        Assert.assertTrue(steps.searchforDrafts());
        Assert.assertTrue(steps.isMailInDraftDissapeared());
        Assert.assertTrue(steps.mailIsPresentInSentFolder());
        steps.logOut();
    }

}


