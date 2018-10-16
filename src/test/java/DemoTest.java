<<<<<<< HEAD
import PageObjects.DraftsPage;
import Steps.*;
=======
import Steps.CreateNewMailSteps;
import Steps.LeftBarClassSteps;
import Steps.LoginSteps;

import Steps.UserMailPageSteps;
>>>>>>> 8acdab394ea9206ccd709619d30ad1108b727940
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class DemoTest {

    private LoginSteps loginSteps;
    private UserMailPageSteps userMailPageSteps;
    private CreateNewMailSteps createNewMailSteps;
<<<<<<< HEAD
    private DraftsSteps draftsSteps;
=======
>>>>>>> 8acdab394ea9206ccd709619d30ad1108b727940
    LeftBarClassSteps leftBarClassSteps;
    private final String USERNAME = "akwebdrivertest";
    private final String PASSWORD = "San9san90394";

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        loginSteps = new LoginSteps();
        userMailPageSteps = new UserMailPageSteps();
        createNewMailSteps = new CreateNewMailSteps();
<<<<<<< HEAD
        draftsSteps = new DraftsSteps();
=======
>>>>>>> 8acdab394ea9206ccd709619d30ad1108b727940
        loginSteps.initBrowser();
        userMailPageSteps.initBrowser();
        createNewMailSteps.initBrowser();
        leftBarClassSteps = new LeftBarClassSteps();
        leftBarClassSteps.initBrowser();
<<<<<<< HEAD
        draftsSteps.initBrowser();
    }
    @Test
        public void oneCanLoginMailRu() {
=======

    }
    @Test
            public void oneCanLoginGmail() {
>>>>>>> 8acdab394ea9206ccd709619d30ad1108b727940
        loginSteps.loginInToMailRu(USERNAME,PASSWORD);
//        Assert.assertTrue(userMailPageSteps.checkForSuccessfulLogin());
        userMailPageSteps.createNewMail();
        createNewMailSteps.fillNewMailAndSaveAsDraft();
        leftBarClassSteps.openDrafts();
<<<<<<< HEAD
        Assert.assertTrue(draftsSteps.searchforDrafts());
        Assert.assertTrue(draftsSteps.isMailInDraftDissapeared());
    }

=======

    }







>>>>>>> 8acdab394ea9206ccd709619d30ad1108b727940
}
