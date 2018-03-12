package nuvia.languagesupport.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import automation.utils.web.WebUtil;
import nuvia.objects.SubscriberObject;
import nuvia.pages.AddSitePage;
import nuvia.pages.CustomerPage;
import nuvia.pages.DashboardTabPage;
import nuvia.pages.LoginPage;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.MenuBeforeChoseCustomer;
import nuvia.pages.OrganizationPage;
import nuvia.pages.SitePage;
import nuvia.pages.Subscribers;

public class TC_000_PostConfigure extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_000_Preconfigure_DefaultLanguage");
	@Test
	@Parameters({"waitTime","Partner_url","Partner_user","Partner_password", "ORG_name", "SMB_name", "Sub_name","Language"})
	public void AddOrganizationCustomerRootDomainNormal(int waitTime, String url, String user, String password, String cusName, String smbName, String subName, String language) throws Exception {
		language = "English";
		WebDriver driver = getDriver();
		initializeDriver(driver, url, waitTime);
		LoginPage login = new LoginPage(driver, waitTime);
		login.Login(user, password, url);
		
		System.setProperty("Language", language);

		// click tab customer
		DashboardTabPage bar = new DashboardTabPage(driver, waitTime);
		bar.verifyPageLoaded("customer");
		logger.info("Click on customer report tab");
		bar.clickOnCustomer();
		//Thread.sleep(mid);
		
		CustomerPage cus = new CustomerPage(driver, waitTime);
		cus.verifyPageLoaded("type");
		OrganizationPage organ = new OrganizationPage(driver, waitTime);
		organ.verifyPageLoaded("settings");
		//Thread.sleep(mid);
			
		// search customer and remove
		cus.ModifyCustomer(cusName);
		//Thread.sleep(mid);
		
		// Verify Customer Language 
		organ.verifyPageLoaded("settings");
		
		if(organ.updateLanguage(language)){
			logger.info("Language of Customer '"+cusName+"' has been changed to " + language);
			Reporter.log("Language of Customer '"+cusName+"' has been changed to " + language);
		} else {
			logger.info("Language of Customer '"+cusName+"' has NOT been changed to " + language);
			Reporter.log("Language of Customer '"+cusName+"' has NOT been changed to " + language);
		}
		
		cus.ModifyCustomer(smbName);
		//Thread.sleep(mid);
		
		// Verify Customer Language 
		organ.verifyPageLoaded("settings");
		
		if(organ.updateLanguage(language)){
			logger.info("Language of Customer '"+cusName+"' has been changed to " + language);
			Reporter.log("Language of Customer '"+cusName+"' has been changed to " + language);
		} else {
			logger.info("Language of Customer '"+cusName+"' has NOT been changed to " + language);
			Reporter.log("Language of Customer '"+cusName+"' has NOT been changed to " + language);
		}
		
		MenuBeforeChoseCustomer menubefore = new MenuBeforeChoseCustomer(driver, waitTime);
		logger.info("Search for customer: "+ cusName);
		menubefore.searchCustomer(cusName);
		
		ChangeSubscriberLanguage(driver, waitTime, subName, language);
		ChangeSiteLanguage(driver, waitTime, "atsite", language);		
	}
	
	public void ChangeSubscriberLanguage(WebDriver driver, int waitTime, String subName, String language) throws Exception{		
		MenuAfterChoseCustomer menuafter = new MenuAfterChoseCustomer(driver, waitTime);
		logger.info("Select text link: Subscriber");
		//menuafter.selectTextLink("Subscribers");
		menuafter.clickToProvisionThenChooseSubscribers();
		WebUtil.waitFor_LoadingFinished(driver, waitTime);
		// go to Add Subscriber page
		SubscriberObject sub = new SubscriberObject(subName);
		sub.language = language;
		Subscribers subscriber = new Subscribers(driver, waitTime);		
		subscriber.modifySubscriber(sub);		
	}
	
	public void ChangeSiteLanguage(WebDriver driver, int waitTime, String siteName, String language) throws Exception{		
		MenuAfterChoseCustomer menuafter = new MenuAfterChoseCustomer(driver, waitTime);
		logger.info("Select text link: Sites");
		//menuafter.selectTextLink("Subscribers");
		menuafter.clickToProvisionThenChooseSites();
		
		// go to Add Subscriber page
		SitePage site = new SitePage(driver, waitTime);
		
		site.ModifySite(siteName);
		AddSitePage sitepage = new AddSitePage(driver, waitTime);
		sitepage.verifyPageLoaded("type");
		logger.info("Change site's language to " + language);
		sitepage.selectLanguage(language);

		logger.info("Click save button");
		sitepage.clickButtonSaveSite();		
	}
}
