package PageObjects;

import AdditionalClasses.MailSaverClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.lang.Thread.sleep;


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


    public WebElement findMailInDraft() {
//        Collection<WebElement> collection = driver.findElements(By.xpath("//a[@rel = 'history'][@class = 'js-href b-datalist__item__link']"));

        while (true){
            try{
                Collection<WebElement> collection =  getVisibleElements(driver.findElements(By.xpath("//a[contains(@href,'/drafts/') and @data-name='link']")),10);
                WebElement mail = getVisibleElement(driver.findElement(By.xpath("//a[@rel = 'history'][@class = 'js-href b-datalist__item__link']")));
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
            } catch (StaleElementReferenceException ex) {
            } catch (TimeoutException te) {
                return null;
            }
        }

    }

    public CreateNewMailPage clickOnSavedMailInDrafts(WebElement element) {
        findMailInDraft().click();
        return new CreateNewMailPage(driver);
    }

    public DraftsPage openPage() {
        driver.navigate().to("https://e.mail.ru/messages/drafts/");
        return this;
    }


}
