package PageObjects;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

public class UserMailPage extends AbstractPage {
    private final String BASE_URL = "https://mail.google.com/mail/";
    private final String USERNAME = "akwebdrivertest@gmail.com";
    private final String PASSWORD = "San9san90394";

    @FindBy(id = "identifierId")
    private WebElement inputUsername;

    @FindBy(name ="password")
    private WebElement inputPassword;

    @FindBy(id = "identifierNext")
    private WebElement buttonFirstFuther;

    @FindBy(id = "passwordNext")
    private WebElement buttonSecondFuther;

    @FindBy(className = "WaidBe")
    private WebElement mailButton;

    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public UserMailPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(this.driver, this);
    }
    public void checkForSuccessfulLogin(String Username,String Password){

    }

}

