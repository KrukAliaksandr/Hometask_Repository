package Tests;

import PageObjects.InboxPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MarkFirstInboxMessageAsSpamTest extends AbstractScenario {

    @Test
    public void loginMailRu() {
        super.oneCanLoginMailRu();
    }

    @Test(dependsOnMethods = "loginMailRu")
    public void deleteFirstMessageInInboxFolder(){
        steps.chooseFirstMailAndDeleteIt();
        Assert.assertTrue(steps.isDeletedMailPresentInTrashFolder());
    }
}
