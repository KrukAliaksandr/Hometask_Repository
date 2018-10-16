package Steps;

import PageObjects.AcccountLoginPage;
import Singleton.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class LoginSteps {
	private static final int IMPLICIT_DELAY = 10;
	private static final String LAGUAGE_FILTER = "Java";
	private WebDriver driver;

	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
		driver.manage().timeouts().implicitlyWait(
				IMPLICIT_DELAY, TimeUnit.SECONDS);
	}

	public void closeDriver() {
		DriverSingleton.closeDriver();
	}

	public void loginInToMailRu(String username, String password) {
		AcccountLoginPage loginPage = new AcccountLoginPage(driver);
		loginPage.openPage();
		loginPage.login(username, password);
	}


//	public void loginGithub(String username, String password)
//	{
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.openPage();
//		loginPage.login(username, password);
//	}
//
//	public boolean isLoggedIn(String username)
//	{
//		LoginPage loginPage = new LoginPage(driver);
//		String actualUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
//		logger.info("Actual username: " + actualUsername);
//		return actualUsername.equals(username);
//	}
//
//	public boolean createNewRepository(String repositoryName, String repositoryDescription)
//	{
//		MainPage mainPage = new MainPage(driver);
//		mainPage.clickOnCreateNewRepositoryButton();
//		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
//		String expectedRepoName = createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
//		return expectedRepoName.equals(createNewRepositoryPage.getCurrentRepositoryName());
//	}
//
//	public boolean currentRepositoryIsEmpty()
//	{
//		CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
//		return createNewRepositoryPage.isCurrentRepositoryEmpty();
//	}
//
//	public boolean createNewGist(String gistDescription, String gistFileName,String gistText)
//	{
//		MainPage mainPage = new MainPage(driver);
//		mainPage.clickOnCreateNewGistButton();
//		CreateNewGistPage createNewGistPage = new CreateNewGistPage(driver);
//		expectedGistName = createNewGistPage.createNewGist(gistDescription,gistFileName,gistText);
//		driver.manage().timeouts().implicitlyWait(IMPLICIT_DELAY, TimeUnit.SECONDS);
//		return expectedGistName.equals(createNewGistPage.getCurrentGistName());
//	}
//
//	public boolean deleteNewGist(){
//		CreateNewGistPage createNewGistPage = new CreateNewGistPage(driver);
//		driver.manage().timeouts().implicitlyWait(IMPLICIT_DELAY, TimeUnit.SECONDS);
//		return expectedGistName.equals(createNewGistPage.deleteGist());
//
//	}
//
//
//
//	public boolean makeNewSearchRequest(String request) {
//		MainPage mainPage = new MainPage(driver);
//		mainPage.sendRequest(request);
//		SearchRequestPage searchRequestPage = new SearchRequestPage(driver);
//		searchRequestPage.getJavaOnly();
//		return searchRequestPage.getLanguageMarkerText().equalsIgnoreCase(LAGUAGE_FILTER);
//	}
//
//	public boolean CheckFirstResult(){
//		SearchRequestPage searchRequestPage = new SearchRequestPage(driver);
//		return searchRequestPage.ClickFirstResultLink();
//}

}
