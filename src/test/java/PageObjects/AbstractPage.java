package PageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Collection;
import java.util.List;

public abstract class AbstractPage{

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
            if(null == wait) {
                wait = new WebDriverWait(this.driver, WAIT_SECONDS);
            }
            return wait;
        }

        private WebDriverWait getWebDriverWait(long waitSeconds) {
            if(null == wait) {
                wait = new WebDriverWait(this.driver, waitSeconds);
            } else if(waitSeconds != currentWaitTime) {
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

        protected <T> boolean isNotEmpty(Collection<T> collection){
            return !collection.isEmpty();
        }

        public <T extends AbstractPage> T reloadPage(T page){
            this.driver.get(driver.getCurrentUrl());
            logger.info("Page '" + driver.getCurrentUrl() + "' has been reloaded from '" + page.getClass().getSimpleName() + "'");
            return page;
        }

        protected void sleep(long millis) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }

}