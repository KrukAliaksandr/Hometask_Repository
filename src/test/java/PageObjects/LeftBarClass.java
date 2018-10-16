package PageObjects;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftBarClass extends AbstractPage {

    private final String BASE_URL = "https://mail.ru/";

    @FindBy(xpath = "//div[@id = 'b-nav_folders']/div/div[3]/a")
    private WebElement drafts;

    public void openPage()
    {
        driver.navigate().to(BASE_URL);
    }

    public LeftBarClass(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void pressDraftsButton(){
        drafts.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

}
