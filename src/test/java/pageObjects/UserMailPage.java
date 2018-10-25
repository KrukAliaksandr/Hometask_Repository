package pageObjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class UserMailPage extends LeftBarClass {
    private final String BASE_URL = "https://e.mail.ru/messages";
    private final int WAIT_SECONDS = 40;
    private  WebDriverWait wait;


    @FindBy(xpath = "//*[@id='PH_user-email']")
    private WebElement accountCurrentEmail;

    @FindBy(xpath = "//*[@id='PH_logoutLink']")
    private WebElement logoutButton;

    @FindBy(xpath = "//div[@class='b-toolbar__item']")
    private WebElement buttonCreateNewMessage;


    public WebElement getAccountCurrentEmail() {
        return accountCurrentEmail;
    }

    public UserMailPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public UserMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private WebDriverWait getWebDriverWait() {
        if (null == wait) {
            wait = new WebDriverWait(this.driver, WAIT_SECONDS);
        }
        return wait;
    }

    public String returnAccountEmail(WebElement webElement) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement)).getText();
    }

    public CreateNewMailPage pressCreateMessageButton() {
        buttonCreateNewMessage.click();
        return new CreateNewMailPage(driver);
    }

    public UserMailPage pressLogoutButton() {
        logoutButton.click();
        return this;
    }

    public String getBASE_URL() {
        return BASE_URL;
    }

}

