package pageObjects;

import additionalClasses.MailSaverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

    public class TrashPage extends LeftBarClass {

        private final long WAIT_ELEMENT_VISIBILITY_SEC = 10;
        private final String BASE_URL = "https://e.mail.ru/messages/trash";

        @FindBy(xpath = "//*[@class='b-datalist__item__info']")
        private List<WebElement> deletedMessagesList;

        public TrashPage(WebDriver driver) {
            super(driver);
        }

        public int checkForDeletedMessage() {
            getVisibleElements(deletedMessagesList, WAIT_ELEMENT_VISIBILITY_SEC);
            System.out.println( deletedMessagesList.size());
            for (int i = 0; i < deletedMessagesList.size(); i++) {
                if (deletedMessagesList.get(i).findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText().equals((MailSaverClass.getMailSubjectAndBody())) &&
                        deletedMessagesList.get(i).findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText().equals((MailSaverClass.getMailAddressee())))
                {
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
