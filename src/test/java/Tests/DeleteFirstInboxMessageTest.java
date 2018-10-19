package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteFirstInboxMessageTest extends AbstractScenario {

    @Test
    public void loginMailRu() {
        super.loginIntoMailRu();
    }

    @Test(dependsOnMethods = "loginMailRu")
    public void deleteFirstMessageInInboxFolder(){
        steps.chooseFirstMailAndDeleteIt();
        Assert.assertTrue(steps.isDeletedMailPresentInTrashFolder());
    }

}
