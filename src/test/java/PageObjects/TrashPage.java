package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

    public class TrashPage extends AbstractPage {

        private final long WAIT_ELEMENT_VISIBILITY_SEC = 10;
        private final String BASE_URL = "https://e.mail.ru/messages/trash";
        @FindBy(xpath = "//*[@id='b-letters']/div[1]/div[5]/div/div[2]/div")
        private List<WebElement> deleteMessagesList;

        public TrashPage(WebDriver driver) {
            super(driver);
        }

        public int getDeletedMsgIndexInList(String expectedSubjectAndBody) {
            getVisibleElements(deleteMessagesList, WAIT_ELEMENT_VISIBILITY_SEC);
            for (int i = 0; i < deleteMessagesList.size(); i++) {
                System.out.println( deleteMessagesList.size());
                String[] trashListItem = deleteMessagesList.get(i).getText().trim().split("\r\n|\n");
                String trashSubjectAndBody = trashListItem[1].trim();
                System.out.println(trashSubjectAndBody + "_" + expectedSubjectAndBody);
                if (trashSubjectAndBody.contains(expectedSubjectAndBody)) {
                    return i;
                }
            }

            return -1;
        }
        public TrashPage openPage() {
            driver.navigate().to(BASE_URL);
            return this;
        }
    }
