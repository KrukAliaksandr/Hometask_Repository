package Steps;

import PageObjects.UserMailPage;
import Singleton.DriverSingleton;
import org.openqa.selenium.WebDriver;
<<<<<<< HEAD
=======

>>>>>>> 8acdab394ea9206ccd709619d30ad1108b727940
import java.util.concurrent.TimeUnit;

public class UserMailPageSteps {

    private static final int IMPLICIT_DELAY = 10;
    private static final String LAGUAGE_FILTER = "Java";
    private WebDriver driver;
    private String expectedGistName;


    public void initBrowser() {
        driver = DriverSingleton.getDriver();
        driver.manage().timeouts().implicitlyWait(
                IMPLICIT_DELAY, TimeUnit.SECONDS);
    }

    public void closeDriver() {
        DriverSingleton.closeDriver();
    }

    public void createNewMail() {
        UserMailPage userMailPage = new UserMailPage(driver);
        userMailPage.pressCreateMessageButton();
    }

    public boolean checkForSuccessfulLogin(){
        UserMailPage userMailPage = new UserMailPage(driver);
        return (userMailPage.getUSERNAME()).equals(userMailPage.returnAccountEmail(userMailPage.getAccountCurrentEmail()));
    }


}
