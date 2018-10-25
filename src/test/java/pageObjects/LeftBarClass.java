package pageObjects;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftBarClass extends AbstractPage{
    private String BASE_URL = "https://e.mail.ru/messages";

    @FindBy(xpath = "//div[@class='b-toolbar__item']")
    private WebElement buttonCreateNewMessage;

    @FindBy(xpath = "//div[@data-id='0']")
    private WebElement inboxMessagesButton;

    @FindBy(xpath = "//div[@data-id='500000']")
    private WebElement sentMessagesButton;

    @FindBy(xpath = "//div[@data-id='500001']")
    private WebElement draftMessagesButton;

    @FindBy(xpath = "//div[@data-id='950']")
    private WebElement spamMessagesButton;

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

    public DraftsPage clickDraftsPage(){
        while(true){
            try{
                draftMessagesButton.click();
                return new DraftsPage(driver);
            }catch(StaleElementReferenceException ex){}

        }

    }

    public SentPage clickSentPage() {
        sentMessagesButton.click();
        return new SentPage(driver);
    }

    public SpamPage clickSpamPage() {
        spamMessagesButton.click();
        return new SpamPage(driver);
    }

    public InboxPage clickInboxPage() {
        inboxMessagesButton.click();
        return new InboxPage(driver);
    }

    public TrashPage clickTrashPage() {
        deletedMessagesButton.click();
        return new TrashPage(driver);
    }

    public WebElement getButtonCreateNewMessage() {
        return buttonCreateNewMessage;
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

    public WebElement getSpamMessagesButton() {
        return spamMessagesButton;
    }

    public WebElement getDeletedMessagesButton() {
        return deletedMessagesButton;
    }
}
