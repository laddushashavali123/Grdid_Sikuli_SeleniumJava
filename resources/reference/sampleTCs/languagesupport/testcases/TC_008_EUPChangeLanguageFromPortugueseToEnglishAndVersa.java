package nuvia.languagesupport.testcases;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import automation.utils.web.WebUtil;
import nuvia.pages.AddOrderPage;
import nuvia.pages.AddSitePage;
import nuvia.pages.AddSubscriberForm;
import nuvia.pages.CustomerPage;
import nuvia.pages.DashboardTabPage;
import nuvia.pages.LoginPage;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.MenuBeforeChoseCustomer;
import nuvia.pages.Subscribers;
import nuvia.eup.pages.EUP_LoginPage;
import nuvia.eup.pages.EUP_Navigation;
import nuvia.eup.pages.EUP_Header;
import nuvia.eup.pages.EUP_Section;
import nuvia.objects.SubscriberObject;

public class TC_008_EUPChangeLanguageFromPortugueseToEnglishAndVersa  extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_008_EUPChangeLanguageFromPortugueseToEnglishAndVersa");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "ORG_name", "Language"})
	public void createSMBWithLanguageIsPortuguese(int waitTime, String urlEUP, String userEUP, String password, String OrgName, String languageOrg) throws Exception {

		WebDriver driver = getDriver();			
		String language = "English";
		//String languageOrg = "Portuguese";
		
		initializeDriver(driver, urlEUP, waitTime);
		// login
		logger.info("Start EUP page");
		EUP_LoginPage login = new EUP_LoginPage(driver, waitTime);
		EUP_Header eupHeader = new EUP_Header(driver,waitTime);
		EUP_Section eupSection = new EUP_Section(driver,waitTime);
		EUP_Navigation eupNavigation = new EUP_Navigation(driver, waitTime);
		
		login.login(userEUP, password);
		//Thread.sleep(mid);
		
		if(eupHeader.verifyLoginByWelcome())
			logger.info("login successfully");
		
		// Move to Service tab
		eupHeader.clickButtonService();		
		logger.info("Select Setting tab successfully");
		
		if(!eupSection.verifyCustomerLanguage(logger, languageOrg)){
			logger.info("**TC Result: TC_018_EUPChangeLanguageFrom"+languageOrg+"ToEnglishAndVersa is : FAILED**.Current default language is not " + languageOrg);
			Reporter.log("**TC Result: TC_018_EUPChangeLanguageFrom"+languageOrg+"ToEnglishAndVersa is : FAILED**.Current default language is not " + languageOrg);
		}
		Assert.assertTrue(eupSection.verifyUpdateCustomerLanguageAndVersa(logger, waitTime, language, languageOrg, eupSection, eupNavigation));
	}
}
