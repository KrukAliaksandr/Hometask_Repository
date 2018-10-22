package PageObjects;

import AdditionalClasses.MailSaverClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CreateNewMailPage extends LeftBarClass {

    Actions actions = new Actions(driver);
    private  String BASE_URL = "https://mail.ru/compose";
    private  String mailReciever = "aliaksandrkrukwd@mail.ru";
    private  String mailTopic = "Test Message";
    private  String mailContent = "Test Content";


    @FindBy(xpath = "//div[contains(@class,'compose-head__row-wrapper compose-head__row-wrapper_to js-row')]//textarea[@class='js-input compose__labels__input']")
    WebElement addressee;

    @FindBy(xpath = "//input[contains(@name,'Subject')]")
    WebElement topic;

    @FindBy(css = "#tinymce")
    WebElement textArea;

    @FindBy(xpath = "//div[@id='b-toolbar__right']//div//div[contains(@class,'b-toolbar')]//div[contains(@class,'b-toolbar__group')]//div[contains(@class,'b-toolbar__item')]//div//div[contains(@title,'Сохранить (Ctrl+S)')]//span[contains(@class,'b-toolbar__btn__text')][contains(text(),'Сохранить')]")
    WebElement buttonSaveLetter;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    WebElement draftLink;

    @FindBy(xpath = "//div[@data-name = 'send']")
    WebElement sendLink;

    WebElement text_area;

    @FindBy(xpath = "//div[@id='b-toolbar__right']//div//div[contains(@class,'b-toolbar')]//div[contains(@class,'b-toolbar__group')]//div[contains(@class,'b-toolbar__item')]//div//div[contains(@title,'Сохранить (Ctrl+S)')]//span[contains(@class,'b-toolbar__btn__text')][contains(text(),'Сохранить')]")
    WebElement button_save_letter;

    @FindBy(xpath = "//span[contains(text(),'Черновики')]")
    WebElement draft_link;

    @FindBy(tagName = "iframe")
    private WebElement iframeForTextField;

    @FindBy(xpath = "//body[@id = 'tinymce']")
    private WebElement bodyForTextField;


    public CreateNewMailPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public CreateNewMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    public CreateNewMailPage fillNewMailMessageAndSaveToDraft(String mail, String topic_text, String text_msg) {
        addressee.sendKeys(mail);
        topic.sendKeys(topic_text);
        driver.switchTo().frame(iframeForTextField);
        bodyForTextField.clear();
        bodyForTextField.sendKeys(text_msg);
        driver.switchTo().defaultContent();
        highlightElement(sendLink);
        buttonSaveLetter.click();
        unHighlightElement(sendLink);
        MailSaverClass.getMailBuffer();
        MailSaverClass.saveMailInBuffer(topic_text + text_msg, mail);
        return this;
    }


    public CreateNewMailPage clickSendButton() {
        sendLink = new WebDriverWait(driver,10).until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-name = 'send']")));
        highlightElement(sendLink);
        unHighlightElement(sendLink);
        sendLink.click();
        return this;

    }

    public DraftsPage moveToDraftsAndAcceptAlert() {
        clickDraftsPage();
        Alert alert = driver.switchTo().alert();
        alert.accept();
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
