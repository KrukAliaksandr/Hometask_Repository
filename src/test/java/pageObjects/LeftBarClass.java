package pageObjects;

import additionalClasses.MailSaverClass;
import buisnessObjects.Mail;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

    @FindBy(xpath = "//*[@id='PH_user-email']")
    private WebElement accountCurrentEmail;

    public WebElement getAccountCurrentEmail() {
        return getVisibleElement(accountCurrentEmail);
    }

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

    public ArrayList<Mail> searchThroughMails(String xPath) {
        ArrayList<Mail> resultCollection = new ArrayList<Mail>();
        try {
            Collection<WebElement> collection = getVisibleElements(driver.findElements(By.xpath(xPath)), 10);
            Iterator<WebElement> iterator = collection.iterator();
            WebElement element = null;
            while (iterator.hasNext()) {
                element = iterator.next();
                System.out.println(element.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText());
                System.out.println(element.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText());
                if (element.findElement(By.xpath(".//div[@class = 'b-datalist__item__subj']")).getText().equals(MailSaverClass.getMailSubjectAndBody()) &&
                        element.findElement(By.xpath(".//div[@class = 'b-datalist__item__addr']")).getText().equals(MailSaverClass.getMailAddressee())) {
                    resultCollection.add(new Mail(element.findElement(By.xpath(".//div[@class = 'b-datalist__item__addr']")).getText(), element.findElement(By.xpath(".//div[@class = 'b-datalist__item__subj']")).getText()));
                }
            }
            return resultCollection;
        } catch (TimeoutException te) {
            return resultCollection;
        }
    }

    public ArrayList<WebElement> searchForSavedMail (String xPath) {
        ArrayList<WebElement> resultCollection = new ArrayList<WebElement>();
        try {
            Collection<WebElement> collection = getVisibleElements(driver.findElements(By.xpath(xPath)), 10);
            Iterator<WebElement> iterator = collection.iterator();
            WebElement element = null;
            while (iterator.hasNext()) {
                element = iterator.next();
                System.out.println(element.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText());
                System.out.println(element.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText());
                if (element.findElement(By.xpath(".//div[@class = 'b-datalist__item__subj']")).getText().equals(MailSaverClass.getMailSubjectAndBody()) &&
                        element.findElement(By.xpath(".//div[@class = 'b-datalist__item__addr']")).getText().equals(MailSaverClass.getMailAddressee())) {
                    resultCollection.add(element);
                }
            }
            return resultCollection;
        } catch (TimeoutException te) {
            return resultCollection;
        }
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
