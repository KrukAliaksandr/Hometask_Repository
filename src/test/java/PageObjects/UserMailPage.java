package PageObjects;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class UserMailPage extends AbstractPage {
    private final String BASE_URL = "https://e.mail.ru/messages";
    private final String USERNAME = "akwebdrivertest@mail.ru";
    private final String PASSWORD = "San9san90394";
    private final int WAIT_SECONDS = 40;
    private  WebDriverWait wait;


    @FindBy(xpath = "//*[@id='PH_user-email']")
    private WebElement accountCurrentEmail;

    @FindBy(xpath = "//div[@class='b-toolbar__item']")
    private WebElement buttonCreateNewMessage;


    public WebElement getAccountCurrentEmail() {
        return accountCurrentEmail;
    }

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public UserMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    private WebDriverWait getWebDriverWait() {
        if(null == wait) {
            wait = new WebDriverWait(this.driver, WAIT_SECONDS);
        }
        return wait;
    }

    public WebElement returnAccountEmail(WebElement webElement) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
    }
public void pressCreateMessageButton(){
        buttonCreateNewMessage.click();
}

    public String getBASE_URL() {
        return BASE_URL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }
}

