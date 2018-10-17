package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.Collection;
import java.util.Iterator;

public class SentPage extends AbstractPage{
    public SentPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public SentPage mailIsSentMessage() {
        waitForElementVisible(By.xpath("//div[@class = 'message-sent__title']"));
        return this;
    }

    public boolean findSentMailInDraft() {
        Collection<WebElement> collection = driver.findElements(By.xpath("//a[@rel = 'history'][@class = 'js-href b-datalist__item__link']"));
        Iterator<WebElement> iterator = collection.iterator();
        boolean resultOfSearch = false;
        WebElement element = null;
        while (iterator.hasNext()) {
            element = iterator.next();
            if (element.findElement(By.xpath("//div[@class = 'b-datalist__item__subj']")).getText().equals("testText for text field") &&
                    element.findElement(By.xpath("//div[@class = 'b-datalist__item__addr']")).getText().equals("aldoshin.2013@mail.ru")) {
                resultOfSearch = true;
            }

        }
        return resultOfSearch;
    }

    @Override
    public void openPage() {
        driver.navigate().to("https://e.mail.ru/messages/sent/");
    }
}

