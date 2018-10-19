package Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MarkFirstInboxMessageAsSpamTest extends AbstractScenario {

    @Test
    public void loginMailRu() {
        super.loginIntoMailRu();
    }

    @Test(dependsOnMethods = "loginMailRu")
    public void markFirstMessageInInboxFolderAsSpam (){
        steps.chooseFirstMailAndMarkItAsSpam();
        Assert.assertTrue(steps.isMailPresentInSpamFolder());
    }
}
