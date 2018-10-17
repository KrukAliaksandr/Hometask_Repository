import PageObjects.UserMailPage;
import Steps.*;
import Steps.CreateNewMailSteps;
import Steps.LeftBarClassSteps;
import Steps.LoginSteps;
import Steps.UserMailPageSteps;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class DemoTest {

    private LoginSteps loginSteps;
    private UserMailPageSteps userMailPageSteps;
    private CreateNewMailSteps createNewMailSteps;
    private DraftsSteps draftsSteps;
    private SentPageSteps sentPageSteps;
    private LeftBarClassSteps leftBarClassSteps;
    private final String USERNAME = "akwebdrivertest";
    private final String PASSWORD = "San9san90394";

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        loginSteps = new LoginSteps();
        userMailPageSteps = new UserMailPageSteps();
        createNewMailSteps = new CreateNewMailSteps();
        draftsSteps = new DraftsSteps();
        sentPageSteps = new SentPageSteps();
        sentPageSteps.initBrowser();
        loginSteps.initBrowser();
        userMailPageSteps.initBrowser();
        createNewMailSteps.initBrowser();
        leftBarClassSteps = new LeftBarClassSteps();
        leftBarClassSteps.initBrowser();
        draftsSteps.initBrowser();
    }

    @Test
    public void oneCanLoginMailRu() {
        loginSteps.loginInToMailRu(USERNAME,PASSWORD);
//        Assert.assertTrue(userMailPageSteps.checkForSuccessfulLogin());
        userMailPageSteps.createNewMail();
        createNewMailSteps.fillNewMailAndSaveAsDraft();
        leftBarClassSteps.openDrafts();
        Assert.assertTrue(draftsSteps.searchforDrafts());
        Assert.assertTrue(draftsSteps.isMailInDraftDissapeared());
        Assert.assertTrue(sentPageSteps.mailIsPresentInSentFolder());
        userMailPageSteps.logOut();
    }

    }


