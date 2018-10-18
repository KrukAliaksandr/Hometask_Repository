package Tests;

import Steps.Steps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AbstractScenario {
    protected Steps steps;
    protected String addressee;
    private final String USERNAME = "akwebdrivertest";
    private final String PASSWORD = "San9san90394";

    @BeforeClass(description = "Init browser and login")
    public void setUp(){
        steps = new Steps();
        addressee = USERNAME;
    }

//    @AfterClass(description = "Stop browser")
//    public void stopBrowser(){
//        steps.logOut();
//        steps.closeDriver();
//    }

    @Test
    public void oneCanLoginMailRu() {
        steps.loginIntoMailRu(USERNAME, PASSWORD);
        Assert.assertTrue(steps.checkForSuccessfulLogin());
    }
//    protected void loginMailRu(){
//        steps.loginIntoMailRu(USERNAME,PASSWORD);
//        String mailRuTestLogin = DP.get("login");
//        Assert.assertTrue(steps.isLoggedIn(mailRuTestLogin));
//    }
}
