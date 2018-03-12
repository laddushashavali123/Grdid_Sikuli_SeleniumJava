package nuvia.languagesupport.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import nuvia.objects.CustomerObject;
import nuvia.pages.CustomerPage;
import nuvia.pages.DashboardTabPage;
import nuvia.pages.LoginPage;
import nuvia.pages.OrganizationPage;

public class TC_001_CreateOrgWithLanguageIsPortuguese extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_001_Create_Update_Delete_OrgWithLanguageIsPortuguese");
	@Test
	@Parameters({"waitTime","Partner_url","Partner_user","Partner_password", "new_ORG_name", "Language"})
	public void AddOrganizationCustomerRootDomainNormal(int waitTime, String url, String user, String password, String cusName, String language) throws Exception {
		WebDriver driver = getDriver();
		initializeDriver(driver, url, waitTime);
		// login
		//String cusName = "KAC_Organization2_Portuguese";
		cusName = cusName + language.substring(0,1);
		String domain = cusName;
		boolean TC_001 = true;
		boolean TC_004 = false;
		boolean TC_005 = false;
		LoginPage login = new LoginPage(driver, waitTime);
		login.Login(user, password, url);

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

		CustomerObject cusO = new CustomerObject("Organization", domain, cusName, "automate");
		cusO.Language = language;
		cusO.billing = "Disabled";
		//cusO.billingPrefix = "123";
		//organ.createCustomer(cusName, language, domain, "Organization", false, "automate", "");
		cus.createCustomer(cusO);
		organ.clickOnActiveCustomerButton();
		organ.clickButtonReturnToList();
		// verify customer is added
		logger.info("Verify adding customer with root domain: " + cusName);
		if(!cus.verifyCustomerIsAddedAndActive(logger, cusName, "Organization")){
			logger.error("**TC Result: Verify adding customer with root domain: FAILED**");
			Reporter.log("**TC Result: Verify adding customer with root domain: FAILED**");
			TC_001 = false;
		}		
		// search customer and remove
		cus.ModifyCustomer(cusName);
		//Thread.sleep(mid);
		
		// Verify Customer Language 
		organ.verifyPageLoaded("settings");
		
		// set text and select combo box
		logger.info("Verify Customer Language: "+language);
		if(!organ.verifyCustomerLanguage(logger, language))
		{
			logger.error("**TC Result: Verify customer's Language: FAILED**");
			Reporter.log("**TC Result: Verify customer's Language: FAILED**");
			TC_001 = false;
		}
		//Thread.sleep(mid);
		
		//Change language and versa
		TC_004 = organ.verifyUpdateCustomerLanguageAndVersa(cusName, language, "English", cus);
		organ.clickButtonReturnToList();		
		TC_005 = cus.removeCustomerAndVerify(cusName);
		// Echo Results
		if(TC_001){
			logger.info("**TC Result: TC_001_CreateOrgWithLanguageIs"+language+" finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_001_CreateOrgWithLanguageIs"+language+" finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_001_CreateOrgWithLanguageIs"+language+" finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_001_CreateOrgWithLanguageIs"+language+" finished successfully with Result is : FAILED**");
		}
		
		if(TC_004){
			logger.info("**TC Result: TC_002_ORGChangesLanguagefromEnglishTo"+language+"AndVersaForORG finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_002_ORGChangesLanguagefromEnglishTo"+language+"AndVersaForORG finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_002_ORGChangesLanguagefromEnglishTo"+language+"AndVersaForORG finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_002_ORGChangesLanguagefromEnglishTo"+language+"AndVersaForORG finished successfully with Result is : FAILED**");
		}
		if(TC_005){
			logger.info("**TC Result: TC_001_RemoveORG finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_001_RemoveORG finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_001_RemoveORG finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_001_RemoveORG finished successfully with Result is : FAILED**");
		}
		
		Assert.assertTrue(TC_001 && TC_004 & TC_005);
	}	
}
