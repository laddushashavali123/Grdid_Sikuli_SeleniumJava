package nuvia.languagesupport.testcases;
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
import nuvia.pages.CustomerPage;
import nuvia.pages.DashboardTabPage;
import nuvia.pages.LoginPage;
import nuvia.pages.OrganizationPage;

public class TC_002_CreatePartnerWithLangugeIsPortuguese extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_002_Create_Update_Delete_PartnerWithLangugeIsPortuguese");
	@Test
	@Parameters({"waitTime","Partner_url","Partner_user","Partner_password", "new_Partner_name", "Language"})
	public void AddPartnerCustomerNormal(int waitTime, String url, String user, String password, String cusName, String language) throws Exception {

		cusName = cusName + language.substring(0,1);
		String domain = cusName;
		boolean TC_002 = true;
		boolean TC_005 = true;
		boolean TC_006 = true;
		
		WebDriver driver = getDriver();
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
		domain = domain.toLowerCase().replace("_", "").replace(" ", "");
		CustomerObject cusO = new CustomerObject("Partner", domain, cusName, "automate");
		cusO.Language = language;
		cusO.billing = "Disabled";
		cus.createCustomer(cusO);
		organ.clickOnActiveCustomerButton();
		
		organ.clickButtonReturnToList();
		// verify customer is added
		logger.info("Verify adding customer with root domain: " + cusName);
		if(!cus.verifyCustomerIsAddedAndActive(logger, cusName, "Partner")){
			logger.error("**TC Result: Verify adding customer with root domain: FAILED**");
			Reporter.log("**TC Result: Verify adding customer with root domain: FAILED**");
			TC_002 = false;
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
			TC_002 = false;
		}
		//Thread.sleep(mid);
		
		//Change language and versa
		TC_005 = organ.verifyUpdateCustomerLanguageAndVersa(cusName, language, "English", cus);
		
		organ.clickButtonReturnToList();
		WebUtil.waitFor_LoadingFinished(driver, waitTime);
		cus.verifyPageLoaded("type");
		TC_006 = cus.removeCustomerAndVerify(cusName);
		
		// Echo Results
		if(TC_002){
			logger.info("**TC Result: TC_002_CreatePartnerWithLangugeIs"+language+" finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_002_CreatePartnerWithLangugeIs"+language+" finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_002_CreatePartnerWithLangugeIs"+language+" finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_002_CreatePartnerWithLangugeIs"+language+" finished successfully with Result is : FAILED**");
		}
		
		if(TC_005){
			logger.info("**TC Result: TC_003_PartnerChangesLanguagefromEnglishTo"+language+"AndVersaForPartner finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_003_PartnerChangesLanguagefromEnglishTo"+language+"AndVersaForPartner finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_003_PartnerChangesLanguagefromEnglishTo"+language+"AndVersaForPartner finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_003_PartnerChangesLanguagefromEnglishTo"+language+"AndVersaForPartner finished successfully with Result is : FAILED**");
		}
		
		if(TC_006){
			logger.info("**TC Result: TC_002_RemovePARTNER finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_002_RemovePARTNER finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_002_RemovePARTNER finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_002_RemovePARTNER finished successfully with Result is : FAILED**");
		}
		
		Assert.assertTrue(TC_002 && TC_005 && TC_006); 
		
		//Thread.sleep(mid);
	}
}
