package pageObjects;

import additionalClasses.MailSaverClass;
import buisnessObjects.Mail;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

    public class TrashPage extends LeftBarClass {

        private final long WAIT_ELEMENT_VISIBILITY_SEC = 10;
        private final String BASE_URL = "https://e.mail.ru/messages/trash";


        public TrashPage(WebDriver driver) {
            super(driver);
        }

        public ArrayList<Mail> checkForDeletedMessage() {
                return searchThroughMails("//a[contains(@href,'/thread/') and @data-name='link']");
        }

        public TrashPage openPage() {
            driver.navigate().to(BASE_URL);
            return this;
        }
    }
