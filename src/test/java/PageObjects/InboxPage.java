package PageObjects;

import PersonalClasses.MailSaverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

    public class InboxPage extends AbstractPage {

        private final long WAIT_ELEMENT_VISIBILITY_SEC = 10;

        private String firstMsgSubjectAndBody;

        @FindBy(xpath = "//*[@id='PH_user-email']")
        private WebElement actualUserName;

        @FindBy(xpath = "//*[@id='b-letters']/div[1]/div[2]/div/div[2]/div/div/a/div[4]")
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


        public InboxPage(WebDriver driver) {
            super(driver);
        }

        public InboxPage readFirstMsgSubjectAndBody() {
            getVisibleElements(inboxMessagesList, WAIT_ELEMENT_VISIBILITY_SEC);
            String mailSubjAndBody=firstMailOnPage.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText().substring(1);
            String mailAddressee = firstMailOnPage.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText();
            MailSaverClass.getMailBuffer();
            System.out.println(mailSubjAndBody+"_"+mailAddressee);
            MailSaverClass.saveMailInBuffer(mailSubjAndBody,mailAddressee);
            return this;
        }


        public InboxPage clickOnCheckboxWithId(int mailIndex){
            if (mailIndex >= 0){
                mailCheckBoxList.get(mailIndex).click();
                logger.info("Inbox message with id='" + mailIndex+ "' has been chosen");
            } else {
                logger.info("Inbox message with id='" + mailIndex+ "' cannot be chosen");
            }
            return this;
        }

        public InboxPage clickOnDeleteMailBtn(){
            deleteMsgBtn.click();
            return this;
        }

        public InboxPage clickOnSpamBtn(){
            spamBtn.click();
            confirmSpamBtn.click();
            return this;
        }

        public InboxPage clickSelectAllMsgsCheckBox(){
            selectAllCheckBox.click();
            return this;
        }

        public boolean isAllCheckBoxSelected(){
            for (WebElement element : mailCheckBoxList){
                if (!element.isSelected()){
                    logger.info("There is at least one checkbox has not been selected");
                    return false;
                }
            }
            logger.info("All checkboxes have been selected");
            return true;
        }

        public String getLoggedInUserName(){
            return getVisibleElement(actualUserName).getText();
        }

        public String getFirstMsgSubjectAndBody() {
            if (firstMsgSubjectAndBody == null || firstMsgSubjectAndBody.trim().isEmpty()){
                this.readFirstMsgSubjectAndBody();
            }
            return firstMsgSubjectAndBody;
        }
    }
