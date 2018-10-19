package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftBarClass extends AbstractPage{
    private String BASE_URL = "https://e.mail.ru/messages";

    @FindBy(xpath = "//div[@class='b-toolbar__item']")
    private WebElement buttonCreateNewMessage;

    @FindBy(xpath = "//div[@data-id='500002']")
    private WebElement inboxMessagesButton;

    @FindBy(xpath = "//a[@href='/messages/sent/']")
    private WebElement sentMessagesButton;

    @FindBy(xpath = "//a[@href='/messages/drafts/']")
    private WebElement draftMessagesButton;

    @FindBy(xpath = "//div[@data-id='500002']")
    private WebElement deletedMessagesButton;


    public LeftBarClass  openPage()
    {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public LeftBarClass (WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public CreateNewMailPage pressCreateMessageButton() {
        buttonCreateNewMessage.click();
        return new CreateNewMailPage(driver);
    }

    public WebElement getInboxMessagesButton() {
        return inboxMessagesButton;
    }

    public WebElement getSentMessagesButton() {
        return sentMessagesButton;
    }

    public WebElement getDraftMessagesButton() {
        return draftMessagesButton;
    }

    public WebElement getDeletedMessagesButton() {
        return deletedMessagesButton;
    }

}
