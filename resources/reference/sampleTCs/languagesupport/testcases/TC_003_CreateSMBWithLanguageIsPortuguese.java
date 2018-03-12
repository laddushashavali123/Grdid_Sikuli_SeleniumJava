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
import nuvia.objects.CustomerObject;
import nuvia.pages.AddOrderPage;
import nuvia.pages.CustomerPage;
import nuvia.pages.DashboardTabPage;
import nuvia.pages.LoginPage;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.MenuBeforeChoseCustomer;
import nuvia.pages.OrderPage;
import nuvia.pages.OrganizationPage;

public class TC_003_CreateSMBWithLanguageIsPortuguese  extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_003_Create_Update_Delete_SMBWithLanguageIsPortuguese");
	@Test
	@Parameters({"waitTime","Partner_url","Partner_user","Partner_password", "new_SMB_name", "Language"})
	public void createSMBWithLanguageIsPortuguese(int waitTime, String url, String user, String password, String cusName, String language) throws Exception {

		WebDriver driver = getDriver();
		String domain = cusName;
		cusName = cusName + language.substring(0,1);
		boolean TC_003 = true;
		boolean TC_006 = false;
		boolean TC_005 = false;
		initializeDriver(driver, url, waitTime);
		// login
		LoginPage login = new LoginPage(driver, waitTime);
		login.Login(user, password, url);

		// click tab customer
		DashboardTabPage bar = new DashboardTabPage(driver, waitTime);
		bar = bar.accessCustomerReportTab(logger);
		//Thread.sleep(mid);		
		
		CustomerPage cus = new CustomerPage(driver, waitTime);
		cus.verifyPageLoaded("type");

		OrganizationPage organ = new OrganizationPage(driver, waitTime);
		organ.verifyPageLoaded("settings");
		//Thread.sleep(mid);
		domain = domain.toLowerCase().replace("_", "").replace(" ", "") + ".smb";
		CustomerObject cusO = new CustomerObject("SMB", domain, cusName, "automate");
		cusO.Language = language;
		cusO.prefix = cusName;
		cusO.billing = "Disabled";
		//cusO.billingPrefix = "123";
		//organ.createCustomer(cusName, language, "", "SMB", false, "automate", cusName);
		cus.createCustomer(cusO);
		organ.clickOnActiveCustomerButton();
		organ.clickButtonReturnToList();
		
		// verify customer is added
		logger.info("Verify adding customer with root domain: " + cusName);
		if(!cus.verifyCustomerIsAddedAndActive(logger, cusName, "SMB")){
			logger.error("**TC Result: Verify adding customer with root domain: FAILED**");
			Reporter.log("**TC Result: Verify adding customer with root domain: FAILED**");
			TC_003 = false;
		}		
		// search customer and verify		
		
		cus.ModifyCustomer(cusName);
		//Thread.sleep(mid);		
		// Verify Customer Language 
		organ.verifyPageLoaded("settings");
		// set text and select combo box
		logger.info("Verify Customer Language: " + language);		
		if(!organ.verifyCustomerLanguage(logger, language) == true){
			logger.error("**TC Result: Verify customer's Language: FAILED**");
			Reporter.log("**TC Result: Verify customer's Language: FAILED**");
			TC_003 = false;
		}
		
		TC_006 = organ.verifyUpdateCustomerLanguageAndVersa(cusName, "English", language, cus);
		
		organ.clickButtonReturnToList();
		WebUtil.waitFor_LoadingFinished(driver, waitTime);
		cus.verifyPageLoaded("type");
		TC_005 = cus.removeCustomerAndVerify(cusName);
		// Echo Results
		if(TC_003){
			logger.info("**TC Result: TC_004_CreateSMBWithLanguageIs"+language+" finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_004_CreateSMBWithLanguageIs"+language+" finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_004_CreateSMBWithLanguageIs"+language+" finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_004_CreateSMBWithLanguageIs"+language+" finished successfully with Result is : FAILED**");
		}
		
		if(TC_006){
			logger.info("**TC Result: TC_005_PartnerChangesLanguagefromEnglishTo"+language+"AndVersaForSMB finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_005_PartnerChangesLanguagefromEnglishTo"+language+"AndVersaForSMB finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_005_PartnerChangesLanguagefromEnglishTo"+language+"AndVersaForSMB finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_005_PartnerChangesLanguagefromEnglishTo"+language+"AndVersaForSMB finished successfully with Result is : FAILED**");
		}
		
		if(TC_005){
			logger.info("**TC Result: TC_003_RemoveSMB finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_003_RemoveSMB finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_003_RemoveSMB finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_003_RemoveSMB finished successfully with Result is : FAILED**");
		}
		Assert.assertTrue(TC_003 && TC_006 & TC_005);
	}	
}
