package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CreateNewMailPage extends AbstractPage {

    Actions actions = new Actions(driver);
    private  String BASE_URL = "https://mail.ru/compose";
    private  String mailReciever = "akwebdrivertest@mail.ru";
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


    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public CreateNewMailPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);

    }

    public void fillNewMailMessage(String mail, String topic_text, String text_msg) {

        addressee.sendKeys(mail);
        topic.sendKeys(topic_text);
        driver.switchTo().frame(0);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].textContent= arguments[1];", textArea, text_msg);
        driver.switchTo().defaultContent();
        buttonSaveLetter.click();
    }


    public CreateNewMailPage clickSendButton() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@data-name = 'send']")));
        sendLink = driver.findElement(By.xpath("//div[@data-name = 'send']"));
        sendLink.click();
        return this;

    }

    public CreateNewMailPage mailIsSentMessage() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("message-sent__title"))));
        return this;
    }

//    public void ClickDraftLink() {
//        draftLink.click();
//        js.executeScript("arguments[0].textContent= arguments[1];", text_area, text_msg);
//        driver.switchTo().defaultContent();
//        button_save_letter.click();
//    }

    public void ClickDraftLink() {
        draft_link.click();

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
