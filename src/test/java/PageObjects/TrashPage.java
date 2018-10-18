package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

    public class TrashPage extends LeftBarClass {

        private final long WAIT_ELEMENT_VISIBILITY_SEC = 10;

        @FindBy(xpath = "//*[@id='b-letters']/div[1]/div[5]/div/div[2]/div")
        private List<WebElement> deleteMessagesList;

        public TrashPage(WebDriver driver) {
            super(driver);
        }

        public int getDeletedMsgIndexInList(String expectedSubjectAndBody) {
            getVisibleElements(deleteMessagesList, WAIT_ELEMENT_VISIBILITY_SEC);
            for (int i = 0; i < deleteMessagesList.size(); i++) {
                String[] trashListItem = deleteMessagesList.get(i).getText().trim().split("\r\n|\n");
                String trashSubjectAndBody = trashListItem[1].trim();
                if (expectedSubjectAndBody.contains(trashSubjectAndBody)) {
                    logger.info("Message is found in Trash folder: sbj&body: '" + trashSubjectAndBody + "'");
                    return i;
                }
            }
            logger.info("Message is NOT found in Trash folder");
            return -1;
        }

    }
