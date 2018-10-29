package pageObjects;
import buisnessObjects.Mail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.List;

    public class SpamPage extends LeftBarClass {

        private final String BASE_URL = "https://e.mail.ru/messages/spam/";

        @FindBy(xpath = "//*[@class='b-datalist__item__info']")
        private List<WebElement> spamMessagesList;

        public SpamPage(WebDriver driver) {
            super(driver);
        }

        public ArrayList<Mail> findMailInSpam() {
                    return searchThroughMails("//a[contains(@href,'/thread/') and @data-name='link']");
        }

        public SpamPage openPage() {
            driver.navigate().to(BASE_URL);
            return this;
        }
    }

