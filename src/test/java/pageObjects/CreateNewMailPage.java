package pageObjects;

import additionalClasses.MailSaverClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CreateNewMailPage extends LeftBarClass {

    Actions actions = new Actions(driver);
    private String BASE_URL = "https://mail.ru/compose";
    private String mailReciever = "aliaksandrkrukwd@mail.ru";
    private String mailTopic = "Test Message";
    private String mailContent = "Test Content";


    @FindBy(xpath = "//div[contains(@class,'compose-head__row-wrapper compose-head__row-wrapper_to js-row')]//textarea[@class='js-input compose__labels__input']")
    WebElement addressee;

    @FindBy(xpath = "//input[contains(@name,'Subject')]")
    WebElement topic;

    @FindBy(css = "#tinymce")
    WebElement textArea;

    @FindBy(xpath = "//div[@data-name='saveDraft']/span")
    WebElement buttonSaveLetter;

    @FindBy(xpath = "//div[@data-name = 'send']")
    WebElement sendLink;

    @FindBy(xpath = "//div[@data-name='saveDraft']/span")
    WebElement button_save_letter;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    WebElement draft_link;

    @FindBy(tagName = "iframe")
    private WebElement iframeForTextField;

    @FindBy(xpath = "//body[@id = 'tinymce']")
    private WebElement bodyForTextField;

    @FindBy(xpath = ".//div[@class = 'classmessage-sent__title']")
    private WebElement mailIsSentMessage;


    public CreateNewMailPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public CreateNewMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    public CreateNewMailPage fillNewMailMessageAndSaveToDraft(String mailAddressee,String mailTopic,String mailContent) {
        addressee.sendKeys(mailAddressee);
        topic.sendKeys(mailTopic);
        driver.switchTo().frame(iframeForTextField);
        bodyForTextField.clear();
        bodyForTextField.sendKeys(mailContent);
        driver.switchTo().defaultContent();
        highlightElement(buttonSaveLetter);
        buttonSaveLetter.click();
        waitForAjaxProcessed();
        unHighlightElement(buttonSaveLetter);
        MailSaverClass.getMailBuffer();
        MailSaverClass.saveMailInBuffer(mailTopic + mailContent,mailAddressee);
        return this;
    }


    public CreateNewMailPage clickSendButton() {
        sendLink = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-name = 'send']")));
        highlightElement(sendLink);
        unHighlightElement(sendLink);
        sendLink.click();
        waitForAjaxProcessed();
        return this;

    }

    public DraftsPage moveToDraftsAndAcceptAlert() {
        clickDraftsPage();
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
        return new DraftsPage(driver);

    }

    public String getMailReciever() {
        return mailReciever;
    }

    public String getMailTopic() {
        return mailTopic;
    }

    public String getMailContent() {
        return mailContent;
    }
}
