package Steps;

import PageObjects.InboxPage;
import PageObjects.TrashPage;
import org.openqa.selenium.WebDriver;

public class InboxPageSteps {

    private static final int IMPLICIT_DELAY = 10;
    private static final String LAGUAGE_FILTER = "Java";
    private WebDriver driver;
    private String expectedGistName;
    private TrashPage trashPage;

    public void chooseFirstMailAndDeleteIt(){
        InboxPage inboxPage = new InboxPage(driver);
        inboxPage.readFirstMsgSubjectAndBody()
                .clickOnCheckboxWithId(0)
                .clickOnDeleteMailBtn();
    }

    public boolean isDeletedMailPresentInTrashFolder(){
        InboxPage inboxPage = new InboxPage(driver);
        trashPage = inboxPage.openTrashFolder();
        return trashPage.getDeletedMsgIndexInList(inboxPage.getFirstMsgSubjectAndBody()) >= 0;
    }
}
