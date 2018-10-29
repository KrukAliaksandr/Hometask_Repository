package pageObjects;

import additionalClasses.MailSaverClass;
import buisnessObjects.Mail;
import buisnessObjects.User;
import exceptions.MaiEx;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;


public class DraftsPage extends LeftBarClass {

    private final String BASE_URL = "https://e.mail.ru/messages/drafts/";
    private final int WAIT_ELEMENT_VISIBILITY_SEC = 5;
    private static final Logger logger = LogManager.getRootLogger();
    private final int WAIT_SECONDS = 5;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@rel = 'history'][@class = 'js-href b-datalist__item__link']")
    Collection<WebElement> collection;

    public DraftsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public ArrayList<Mail> findMailInDraft() {
            return searchThroughMails("//a[contains(@href,'/drafts/') and @data-name='link']");
    }

    public CreateNewMailPage clickOnSavedMailInDrafts() throws WebDriverException {

        ArrayList<WebElement> searchResult = searchForSavedMail("//a[contains(@href,'/drafts/') and @data-name='link']");

        try {
            if (searchResult.size() > 1) {
                throw new MaiEx("duplicated mail");
            }
            if (searchResult.size() == 0) {
                throw new MaiEx("no mails found,nothing to click");
            }
            searchResult.get(0).click();
            return new CreateNewMailPage(driver);
        } catch (MaiEx maiEx) {
            throw new WebDriverException();
        }
    }

    public DraftsPage openPage() {
        driver.navigate().to("https://e.mail.ru/messages/drafts/");
        return this;
    }

}
