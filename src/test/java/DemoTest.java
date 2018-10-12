import Steps.Steps;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Test
public class DemoTest {

    private Steps steps;
    private final String USERNAME = "akwebdrivertest@gmail.com";
    private final String PASSWORD = "San9san90394";

    @BeforeMethod(description = "Init browser")
    public void setUp()
    {
        steps = new Steps();
        steps.initBrowser();

    }
    @Test
            public void oneCanLoginGmail() {
        steps.loginGmail(USERNAME,PASSWORD);
    }


}
