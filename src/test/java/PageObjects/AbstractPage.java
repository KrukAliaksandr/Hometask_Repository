package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.List;

import static sun.plugin2.message.HeartbeatMessage.DEFAULT_TIMEOUT;

public abstract class AbstractPage {

    private static final long WAIT_SECONDS = 40;

    protected WebDriver driver;
    protected final Logger logger = LogManager.getRootLogger();
    private WebDriverWait wait;
    private long currentWaitTime;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    private WebDriverWait getWebDriverWait() {
        if (null == wait) {
            wait = new WebDriverWait(this.driver, WAIT_SECONDS);
        }
        return wait;
    }

    private WebDriverWait getWebDriverWait(long waitSeconds) {
        if (null == wait) {
            wait = new WebDriverWait(this.driver, waitSeconds);
        } else if (waitSeconds != currentWaitTime) {
            wait = new WebDriverWait(this.driver, waitSeconds);
            currentWaitTime = waitSeconds;
        }
        return wait;
    }

    protected WebElement getVisibleElement(WebElement webElement) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOf(webElement));
    }

    protected WebElement getVisibleElement(WebElement webElement, long waitSeconds) {
        return getWebDriverWait(waitSeconds).until(ExpectedConditions.visibilityOf(webElement));
    }

    protected List<WebElement> getVisibleElements(List<WebElement> webElements) {
        return getWebDriverWait().until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    protected List<WebElement> getVisibleElements(List<WebElement> webElements, long waitSeconds) {
        return getWebDriverWait(waitSeconds).until(ExpectedConditions.visibilityOfAllElements(webElements));
    }

    protected WebElement getClickableElement(WebElement webElement) {
        return getWebDriverWait().until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected <T> boolean isNotEmpty(Collection<T> collection) {
        return !collection.isEmpty();
    }

    public <T extends AbstractPage> T reloadPage(T page) {
        this.driver.get(driver.getCurrentUrl());
        return page;
    }

    protected void highlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'", element);
    }

    protected void unHighlightElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'", element);
    }

    protected void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private ExpectedCondition<Boolean> isAjaxFinished() {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return jQuery.active == 0");
            }
        };
    }

    protected void waitForAjaxProcessed() {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(isAjaxFinished());
    }
}