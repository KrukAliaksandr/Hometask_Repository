package PageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import org.openqa.selenium.WebDriver;

    public class InboxPage extends LeftBarClass {

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

        public InboxPage(WebDriver driver) {
            super(driver);
        }

        public InboxPage readFirstMsgSubjectAndBody(){
            getVisibleElements(inboxMessagesList, WAIT_ELEMENT_VISIBILITY_SEC);
            String[] inboxListItem = inboxMessagesList.get(0).getText().trim().split("\r\n|\n");
            firstMsgSubjectAndBody = inboxListItem[1].trim();
            logger.info("Message with '" + firstMsgSubjectAndBody + "' has been read");
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
            click(deleteMsgBtn);
            sleep(2000);
            logger.info("'Delete' message button has been clicked");
            return this;
        }

        public InboxPage clickOnSpamBtn(){
            click(spamBtn);
            sleep(2000);
            confirmSpamBtn.click();
            logger.info("'Spam' button has been clicked");
            sleep(1000);
            return this;
        }

        public InboxPage clickSelectAllMsgsCheckBox(){
            click(selectAllCheckBox);
            sleep(1000);
            logger.info("'Select all' checkbox has been clicked");
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
