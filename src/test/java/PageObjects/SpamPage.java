package PageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;


    public class SpamPage extends AbstractPage {

        private final long WAIT_ELEMENT_VISIBILITY_SEC = 10;
        private final String BASE_URL = "https://e.mail.ru/messages/spam/";

        @FindBy(xpath = "//*[@id='b-letters']/div[1]/div[5]/div/div[2]/div")
        private List<WebElement> spamMessagesList;

        public SpamPage(WebDriver driver) {
            super(driver);
        }

        public int getMsgIndexInListMarkedAsSpam(String expectedSubjectAndBody) {
            getVisibleElements(spamMessagesList, WAIT_ELEMENT_VISIBILITY_SEC);
            for (int i = 0; i < spamMessagesList.size(); i++) {
                String[] trashListItem = spamMessagesList.get(i).getText().trim().split("\r\n|\n");
                String trashSubjectAndBody = trashListItem[1].trim();
                if (expectedSubjectAndBody.contains(trashSubjectAndBody)) {
                    logger.info("Message is found in Spam folder: sbj&body: '" + trashSubjectAndBody + "'");
                    return i;
                }
            }
            logger.info("Message is NOT found in Spam folder");
            return -1;
        }

        public void openPage() {
            driver.navigate().to(BASE_URL);
        }
    }

