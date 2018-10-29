package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AcccountLoginPage extends AbstractPage {

    private final String BASE_URL = "https://mail.ru/";

    @FindBy(id = "mailbox:login")
    private WebElement inputUsername;

    @FindBy(id = "mailbox:password")
    private WebElement inputPassword;

    @FindBy(xpath = "//input[@class = 'o-control']")
    private WebElement mailButton;

    public AcccountLoginPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public AcccountLoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void login(String username, String password) {
        inputUsername.clear();
        inputUsername.sendKeys(username);
        inputPassword.clear();
        inputPassword.sendKeys(password);
        mailButton.click();
    }
}
