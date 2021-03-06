package pageObjects;

import additionalClasses.MailSaverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InboxPage extends LeftBarClass {

    private final long WAIT_ELEMENT_VISIBILITY_SEC = 10;

    private String firstMsgSubjectAndBody;

    @FindBy(xpath = "//*[@id='PH_user-email']")
    private WebElement actualUserName;

    @FindBy(className = "b-datalist__item__info")
    private List<WebElement> inboxMessagesList;

    @FindBy(xpath = "//div[@class='js-checkbox b-checkbox']/div[@class='b-checkbox__box']")
    private List<WebElement> mailCheckBoxList;

    @FindBy(xpath = "//div[@data-name='remove']")
    private WebElement deleteMsgBtn;

    @FindBy(xpath = "//div[@data-name='spam']")
    private WebElement spamBtn;

    @FindBy(xpath = "//button[@class='btn b-spam-confirm__btn']")
    private WebElement confirmSpamBtn;

    @FindBy(xpath = "//div[@data-group='selectAll']")
    private WebElement selectAllCheckBox;

    @FindBy(className = "b-datalist__item__info")
    private WebElement firstMailOnPage;

    @FindBy(className = "js-href b-datalist__item__link")
    private WebElement mailToDrag;


    public InboxPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public InboxPage readFirstMsgSubjectAndBody() {
        highlightElement(firstMailOnPage);
        String mailSubjAndBody = firstMailOnPage.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText().substring(1);
        String mailAddressee = firstMailOnPage.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText();
        unHighlightElement(firstMailOnPage);
        MailSaverClass.getMailBuffer();
        System.out.println(mailSubjAndBody + "_" + mailAddressee);
        MailSaverClass.saveAsMailForComparsion(mailSubjAndBody, mailAddressee);
        return this;
    }


    public InboxPage clickOnCheckboxWithId(int mailIndex) {
        if (mailIndex >= 0) {
            mailCheckBoxList.get(mailIndex).click();
            logger.info("Inbox message with id='" + mailIndex + "' has been chosen");
        } else {
            logger.info("Inbox message with id='" + mailIndex + "' cannot be chosen");
        }
        return this;
    }

    public InboxPage clickOnSpamBtn() {
        spamBtn.click();
        confirmSpamBtn.click();
        return this;
    }


    public InboxPage deleteFirstMailUsingActions() {
        getVisibleElements(inboxMessagesList, WAIT_ELEMENT_VISIBILITY_SEC);
        new Actions(driver).dragAndDrop(inboxMessagesList.get(0), getDeletedMessagesButton()).build().perform();
        return this;
    }
}
