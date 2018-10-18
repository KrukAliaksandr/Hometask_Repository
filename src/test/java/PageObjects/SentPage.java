package PageObjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.Collection;
import java.util.Iterator;

public class SentPage extends AbstractPage{
    private final String BASE_URL = "https://e.mail.ru/messages/sent/";


    public SentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public boolean findSentMailInDraft() {
        Collection<WebElement> collection = driver.findElements(By.xpath("//a[@rel = 'history'][@class = 'js-href b-datalist__item__link']"));
        System.out.println(collection.size());
        Iterator<WebElement> iterator = collection.iterator();
        boolean resultOfSearch = false;
        WebElement element = null;
        while (iterator.hasNext()) {
            element = iterator.next();
            System.out.println(element.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText());
            System.out.println(element.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText());
            if (element.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText().contains("Test MessageTest Content") &&
                    element.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText().contains("akwebdrivertest@mail.ru")) {
                resultOfSearch = true;
            }

        }
        return resultOfSearch;
    }

    public SentPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}

