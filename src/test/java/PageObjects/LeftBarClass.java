package PageObjects;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LeftBarClass extends AbstractPage {
    protected WebDriver driver;

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
    @FindBy(xpath="//*[@id='b-toolbar__left']/div/div/div[2]/div/a")
    private WebElement composeMailBtn;

    @FindBy(xpath="//a[contains(@href,'/inbox') and contains(@class,'b-nav__link')]")
    private WebElement inboxFolderLink;

    @FindBy(xpath="//a[contains(@href,'/sent') and contains(@class,'b-nav__link')]")
    private WebElement sentFolderLink;

    @FindBy(xpath="//a[contains(@href,'/draft') and contains(@class,'b-nav__link')]")
    private WebElement draftFolderLink;

    @FindBy(xpath="//a[contains(@href,'/spam') and contains(@class,'b-nav__link')]")
    private WebElement spamFolderLink;

    @FindBy(xpath="//a[contains(@href,'/trash') and contains(@class,'b-nav__link')]")
    private WebElement trashFolderLink;

    @FindBy(id="PH_logoutLink")
    private WebElement logOutLink;


//    public ComposePage clickComposeMailBtn(){
//        click(composeMailBtn);
//        return new ComposePage(driver);
//    }

    public InboxPage openInboxFolder(){
        click(inboxFolderLink);
        return new InboxPage(driver);
    }

    public SentPage openSentFolder(){
        click(sentFolderLink);
        return new SentPage(driver);
    }

    public DraftsPage openDraftFolder(){
        click(draftFolderLink);
        return new DraftsPage(driver);
    }

    public SpamPage openSpamFolder(){
        click(spamFolderLink);
        return new SpamPage(driver);
    }

    public TrashPage openTrashFolder(){
        click(trashFolderLink);
        return new TrashPage(driver);
    }

//    public MainPage clickLogOutLink(){
//        click(logOutLink);
//        sleep(1000);
//        return new MainPage(driver);
//    }

    /* Next two methods are needed to avoid 'StaleElementReferenceException' for clickable WebElements */
    protected void click(WebElement webElement) {
        boolean isPeresent = false;
        int counter = 0;
        while(!isPeresent && counter < 100) {
            isPeresent = isElementPresentAndClicked(webElement);
            counter++;
        }
    }

    private boolean isElementPresentAndClicked(WebElement webElement) {
        try {
            getVisibleElement(webElement).click();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /* Next two methods are needed to avoid 'StaleElementReferenceException' for single WebElements */
    protected WebElement getNotStaledElement(WebElement webElement) {
        boolean isPeresent = false;
        int counter = 0;
        while(!isPeresent && counter < 100) {
            isPeresent = isElementPresent(webElement);
            counter++;
        }
        return webElement;
    }

    private boolean isElementPresent(WebElement webElement) {
        try {
            getVisibleElement(webElement);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /* Next two methods are needed to avoid 'StaleElementReferenceException' for List of WebElements */
    protected List<WebElement> getNotStaledElements(List<WebElement> webElements) {
        boolean isPeresent = false;
        int counter = 0;
        while(!isPeresent && counter < 100) {
            isPeresent = isElementsPresent(webElements);
            counter++;
        }
        return webElements;
    }

    private boolean isElementsPresent(List<WebElement> webElements) {
        try {
            getVisibleElements(webElements);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}
