package PageObjects;
<<<<<<< HEAD
import org.openqa.selenium.Alert;
=======

>>>>>>> 8acdab394ea9206ccd709619d30ad1108b727940
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LeftBarClass extends AbstractPage {

    private final String BASE_URL = "https://mail.ru/";

<<<<<<< HEAD
    @FindBy(xpath = "//div[@id = 'b-nav_folders']/div/div[3]/a")
=======
    @FindBy(xpath = "//div[@class = 'b-nav_folders']/div/div[2]/a")
>>>>>>> 8acdab394ea9206ccd709619d30ad1108b727940
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
<<<<<<< HEAD
        Alert alert = driver.switchTo().alert();
        alert.accept();
=======
>>>>>>> 8acdab394ea9206ccd709619d30ad1108b727940
    }

}
