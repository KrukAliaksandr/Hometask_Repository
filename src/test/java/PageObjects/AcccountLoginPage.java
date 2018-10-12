package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

public class AcccountLoginPage extends AbstractPage {
    private final String BASE_URL = "https://accounts.google.com";

    @FindBy(id = "identifierId")
    private WebElement inputUsername;

    @FindBy(name ="password")
    private WebElement inputPassword;

    @FindBy(id = "identifierNext")
    private WebElement buttonFirstFuther;

    @FindBy(xpath = "//span[text() = 'Далее']")
    private WebElement buttonSecondFuther;
    @Override
    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public AcccountLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void loginInToGmail(String username,String password){
        inputUsername.sendKeys(username);
        buttonFirstFuther.click();
        inputPassword.sendKeys(password);
        driver.switchTo().defaultContent();
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        buttonSecondFuther.click();
    }
}
