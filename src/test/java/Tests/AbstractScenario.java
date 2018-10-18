package Tests;

import Steps.Steps;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AbstractScenario {
    protected Steps steps;
    protected String addressee;
    private final String USERNAME = "akwebdrivertest";
    private final String PASSWORD = "12345aa";

    @BeforeClass(description = "Init browser and login")
    public void setUp(){
        steps = new Steps();
        addressee = USERNAME;
    }

    @Test
    public void loginIntoMailRu() {
        steps.loginIntoMailRu(USERNAME, PASSWORD);
    }

}
