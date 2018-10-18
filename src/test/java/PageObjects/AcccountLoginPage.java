package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

public class AcccountLoginPage extends AbstractPage {
    private final String BASE_URL = "https://mail.ru/";

    @FindBy(id = "mailbox:login")
    private WebElement inputUsername;

    @FindBy(id ="mailbox:password")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@class = 'o-control']")
    private WebElement mailButton;

    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public AcccountLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void login (String username,String password){
        inputUsername.clear();
        inputUsername.sendKeys(username);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        mailButton.click();
    }
}
