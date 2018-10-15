package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



public class DraftsClass extends AbstractPage {

    private final String BASE_URL = "https://mail.ru/";

    @FindBy(className = "b-datalist__body")
    private WebElement drafts;

    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public DraftsClass(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

}
