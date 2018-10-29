package pageObjects;

import additionalClasses.MailSaverClass;
import buisnessObjects.Mail;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SentPage extends LeftBarClass {
    private final String BASE_URL = "https://e.mail.ru/messages/sent/";


    public SentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ArrayList<Mail> findMailInSent() {
            return searchThroughMails(".//div[@class='b-datalist__item__info']");
    }

    public SentPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}

