package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static java.lang.Thread.sleep;


public class DraftsPage extends AbstractPage {

    private final String BASE_URL = "https://e.mail.ru/messages/drafts/";
    private final int WAIT_ELEMENT_VISIBILITY_SEC = 5;
    private static final Logger logger = LogManager.getRootLogger();
    private final int WAIT_SECONDS = 5;
    WebDriverWait wait;


    @FindBy(xpath = "//div[@class = 'b-datalist__body']/div/div/a")
    private WebElement drafts;


    public DraftsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "//*[@id='b-letters']/div[1]/div[8]/div/div[2]/div/div/a")
    private List<WebElement> sentMessagesList;

    @FindBy(xpath = "//div[@class = 'b-datalist__body']")
    private WebElement list_of_elements;

    public WebElement findMailInDraft() {

        Collection<WebElement> collection = driver.findElements(By.xpath("//a[@rel = 'history'][@class = 'js-href b-datalist__item__link']"));
        Iterator<WebElement> iterator = collection.iterator();
        WebElement element = null;
        while (iterator.hasNext()) {
            element = iterator.next();
            if (element.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText().equalsIgnoreCase("Test MessageTest Content") &&
                    element.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText().equalsIgnoreCase("akwebdrivertest@mail.ru")) {
                return element;
            }

        }
        return null;

    }

    public DraftsPage clickOnReference() {
        findMailInDraft().click();
        return this;
    }

    public DraftsPage openPage() {
        driver.navigate().to("https://e.mail.ru/messages/drafts/");
        return this;
    }

}
