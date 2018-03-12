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
import nuvia.pages.CustomerPage;
import nuvia.pages.DashboardTabPage;
import nuvia.pages.LoginPage;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.MenuBeforeChoseCustomer;
import nuvia.pages.OrderPage;
import nuvia.pages.OrganizationPage;
import nuvia.pages.SitePage;

public class TC_004_ORGCreateSiteWithPortugueseLanguage  extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_004_ORG_Create_Update_Delete_SiteWithPortugueseLanguage");
	@Test
	@Parameters({"waitTime","Partner_url","Partner_user","Partner_password", "ORG_name", "new_Site_name", "Language"})
	public void createSMBWithLanguageIsPortuguese(int waitTime, String url, String user, String password, String cusName, String siteName, String language) throws Exception {

		WebDriver driver = getDriver();
		//String cusName = "automation ORG";
		//String siteName = "ORG_AT5_Portuguse";
		//String language = "Portuguese";
		String siteIdentify = siteName;//"ORGAT09";
		siteIdentify = siteIdentify + language.substring(0,1);
		boolean TC_004 = true;
		boolean TC_005 = true;
		boolean TC_006 = false;
		
		initializeDriver(driver, url, waitTime);
		// login
		LoginPage login = new LoginPage(driver, waitTime);
		login.Login(user, password, url);

		// choose customer to add site in 
		MenuBeforeChoseCustomer menuBefore = new MenuBeforeChoseCustomer(driver,waitTime);
		menuBefore.verifyPageLoaded("portal mark");
		menuBefore.searchCustomer(cusName);
		//Thread.sleep(min);
		logger.info("Search for customer successfully");
		
		// move to sites page 
		MenuAfterChoseCustomer menuAfter = new MenuAfterChoseCustomer(driver,waitTime);
		menuAfter.verifyPageLoaded("portal mark");		
		menuAfter.clickToProvisionThenChooseSites();
		logger.info("Select site page successfully");
		
		// add site
		SitePage site = new SitePage(driver, waitTime);
		site.verifyPageLoaded("sites");
		logger.info("Click on add site button");
		site.clickButtonAddSite();
		
		// add site form
		AddSitePage sitepage = new AddSitePage(driver, waitTime);
		sitepage.verifyPageLoaded("type");
		logger.info("Set site name:" + siteName);
		sitepage.setSiteName(siteName);
		logger.info("Set site identifier");
		sitepage.setSiteIndentifier(siteIdentify);
		
		logger.info("Verify default site language is " + language);
		if(sitepage.verifyDefaultLanguage(language) == false){
			TC_004 = false;
			logger.error("**TC Result: Default language is not correct.");
			Reporter.log("**TC Result: Default language is not correct.");
		}
		
		logger.info("Set site language");
		sitepage.selectLanguage(language);
		
		
		logger.info("click on save site button");
		sitepage.clickButtonSaveSite();
		logger.info("Saving a new site");
		
		// verify results after creating sites
		//Thread.sleep(mid);
		
		logger.info("Verify whether notification shows up");
		if(!site.verifyAddSiteByNotification()){
			logger.error("**TC Result: Verify whether notification shows up: FAILED**");
			Reporter.log("**TC Result: Verify whether notification shows up: FAILED**");
			TC_004 = false;
		}
		logger.info("Verify whether the new site has been added successfully");
		menuAfter.clickToProvisionThenChooseSites();
		if(!site.searchResultsAdd(siteIdentify)){
			logger.error("**TC Result: Verify whether the new site has been added successfully: FAILED**");
			Reporter.log("**TC Result: Verify whether the new site has been added successfully: FAILED**");
			TC_004 = false;
		}
		logger.info("Add a site successfully");

		//Thread.sleep(mid);
		
		TC_005 = site.changeSiteLanguageAndVersa(logger, waitTime, "English", language, siteIdentify, site, sitepage);
		
		
		TC_006 = site.removeSiteAndVerifyRemove(siteIdentify);
		// Echo Results
		if(TC_004){
			logger.info("**TC Result: TC_006_ORGCreateSiteWith"+language+"Language finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_006_ORGCreateSiteWith"+language+"Language finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_006_ORGCreateSiteWith"+language+"Language finished successfully with Result is : FAILED** - Default language is not correct.");
			Reporter.log("**TC Result: TC_006_ORGCreateSiteWith"+language+"Language finished successfully with Result is : FAILED** - Default language is not correct.");
		}
		
		if(TC_005){
			logger.info("**TC Result: TC_007_ORGChangeLanguageForSiteFromEnglishTo"+language+"AndVersa finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_007_ORGChangeLanguageForSiteFromEnglishTo"+language+"AndVersa finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_007_ORGChangeLanguageForSiteFromEnglishTo"+language+"AndVersa finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_007_ORGChangeLanguageForSiteFromEnglishTo"+language+"AndVersa finished successfully with Result is : FAILED**");
		}
		
		if(TC_006){
			logger.info("**TC Result: TC_008_ORGRemoveSiteWith"+language+"Language finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_008_ORGRemoveSiteWith"+language+"Language finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_008_ORGRemoveSiteWith"+language+"Language finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_008_ORGRemoveSiteWith"+language+"Language finished successfully with Result is : FAILED**");
		}
		Assert.assertTrue(TC_004 && TC_005 & TC_006);
	}
		
}
