package PageObjects;

import AdditionalClasses.MailSaverClass;
import okio.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.sql.Time;
import java.util.Collection;
import java.util.Iterator;

public class SentPage extends LeftBarClass {
    private final String BASE_URL = "https://e.mail.ru/messages/sent/";


    public SentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public WebElement findMailInSent() {

        while (true) {
            try {
                Collection<WebElement> collection = driver.findElements(By.xpath(".//div[@class='b-datalist__item__info']"));
                Iterator<WebElement> iterator = collection.iterator();
                WebElement element = null;
                while (iterator.hasNext()) {
                    element = iterator.next();
                    if (element.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText().substring(1).equals(MailSaverClass.getMailSubjectAndBody()) &&
                            element.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText().equals(MailSaverClass.getMailAddressee())) {

                        return element;
                    }

                }
                return null;
            } catch (StaleElementReferenceException ex) {
            } catch (TimeoutException te) {
                return null;
            }
        }
    }

    public SentPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}

