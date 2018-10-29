package pageObjects;
import additionalClasses.MailSaverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;


    public class SpamPage extends LeftBarClass {

        private final long WAIT_ELEMENT_VISIBILITY_SEC = 10;
        private final String BASE_URL = "https://e.mail.ru/messages/spam/";

        @FindBy(xpath = "//*[@class='b-datalist__item__info']")
        private List<WebElement> spamMessagesList;

        public SpamPage(WebDriver driver) {
            super(driver);
        }

        public WebElement findMailInSpam() {
            try {
                driver.navigate().refresh();
                Collection<WebElement> collection = getVisibleElements(driver.findElements(By.xpath("//a[contains(@href,'/thread/') and @data-name='link']")), 10);
                Iterator<WebElement> iterator = collection.iterator();
                WebElement element = null;
                while (iterator.hasNext()) {
                    element = iterator.next();
                    System.out.println(element.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText());
                    System.out.println(element.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText());
                    if (element.findElement(By.xpath(".//div[@class = 'b-datalist__item__subj']")).getText().equals(MailSaverClass.getMailSubjectAndBody()) &&
                            element.findElement(By.xpath(".//div[@class = 'b-datalist__item__addr']")).getText().equals(MailSaverClass.getMailAddressee())) {

                        return element;
                    }

                }
                return null;

            } catch (TimeoutException te) {
                return null;
            }
        }

        public SpamPage openPage() {
            driver.navigate().to(BASE_URL);
            return this;
        }
    }

