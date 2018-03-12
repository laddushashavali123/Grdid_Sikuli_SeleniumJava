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
import nuvia.pages.OrderPage;
import nuvia.pages.OrganizationPage;
import nuvia.pages.SitePage;
import nuvia.pages.Subscribers;

public class TC_007_SMBCreateSubsWithPortugueseLanguage  extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_007_SMB_Create_Update_Delete_SubsWithPortugueseLanguage");
	@Test
	@Parameters({"waitTime","Partner_url","Partner_user","Partner_password", "SMB_name", "new_Sub_name", "Language"})
	public void createSMBWithLanguageIsPortuguese(int waitTime, String url, String user, String password, String cusName, String userID, String language) throws Exception {

		WebDriver driver = getDriver();
		//String cusName = "SMB Test";
		userID += "smb";
		userID = userID + language.substring(0,1);
		String siteName = "smbsite (smbsite)";
		//String language = "Portuguese";
		//String userID = "smbuser05";
		String userPass = "Kandy-1234";
		String product = "925-0064-101";
		String DN = "84907777718";
		String phonenumber = "841645774816";		
		boolean TC_005 = true;
		boolean TC_006 = true;
		boolean TC_007 = true;
		//boolean TC_006 = false;
		
		initializeDriver(driver, url, waitTime);
		LoginPage login = new LoginPage(driver, waitTime);
		login.Login(user, password, url);

		// go to customer menu and search customer
		MenuBeforeChoseCustomer menubefore = new MenuBeforeChoseCustomer(driver, waitTime);
		menubefore.verifyPageLoaded("portal mark");
		logger.info("Search for customer: "+ cusName);
		menubefore.searchCustomer(cusName);
		
		//Thread.sleep(mid);
		
		// go to subscriber page
		MenuAfterChoseCustomer menuafter = new MenuAfterChoseCustomer(driver, waitTime);
		logger.info("Select text link: Subscriber");
		//menuafter.selectTextLink("Subscribers");
		//menubefore.clickOnProvisionItem("Subscribers");
		//menuafter.selectTextLink("Subscribers");
		menuafter.clickToProvisionThenChooseSubscribers();

		// go to Add Subscriber page
		WebUtil.waitFor_LoadingFinished(driver, 90);
		Subscribers subscriber = new Subscribers(driver, waitTime);
		logger.info("Click on add subscriber button");
		subscriber.clickButtonAddUser();
		//Thread.sleep(mid);

		// go to AddSubscriberForm
		AddSubscriberForm addsub = new AddSubscriberForm(driver, waitTime);
		logger.info("Set first name: Automation");
		addsub.setFirstName("Automation");
		logger.info("Set last name: Subscriber");
		addsub.setLastName("Subscriber");
		logger.info("Set use ID: "+ userID);
		addsub.setUserId(userID);
		logger.info("Set site name: " + siteName);
		addsub.selectSite(siteName);
		logger.info("Click on product :" + product);
		addsub.selectProductByIndex(product);
		//logger.info("Click on product :AUT-002");
		//addsub.checkOnCheckBoxProduct();
		logger.info("Set password: " + userPass);
		addsub.setPassword(userPass);
		logger.info("Set confirm password :" + userPass);
		addsub.setConfirmPassword(userPass);
		logger.info("Click on VoIP number");
		addsub.clickVoipNumber();
		//logger.info("Search for: " + DN);
		//addsub.searchDirectoryNumber(DN);
		//Thread.sleep(min);
		logger.info("Click on directory number");
		addsub.checkOnCheckBoxDirectoryNumber();
		//logger.info("Click on button accept number");
		//addsub.clickButtonAcceptNumber();
		//Thread.sleep(min);
		logger.info("Set phone number: " + phonenumber);
		addsub.setMobileNumber(phonenumber);
		logger.info("Verify Default language: " + language);
		if(!addsub.verifyDefaultLanguage(logger, language)){
			TC_005 = false;
			logger.error("**TC Result: Verify adding Subscriber with default language: FAILED**" + addsub.getDefaultLanguage());
			Reporter.log("**TC Result: Verify adding Subscriber with default language: FAILED**" + addsub.getDefaultLanguage());
		}
				
		logger.info("Click on save user button");
		addsub.clickSaveUser();
		
		WebUtil.waitFor_LoadingFinished(driver, 60);
		if(!addsub.verifyCreatedSubscriberByAlert()){
			TC_005 = false;
			logger.error("**TC Result: TC_015_ORGCreateSubsWith"+language+"Languagez: Verify adding Subscriber with userID by Alert: FAILED**");
			Reporter.log("**TC Result: TC_015_ORGCreateSubsWith"+language+"Languagez: Verify adding Subscriber with userID by Alert: FAILED**");
		}
		
		// verify Subscriber is added successfully
		logger.info("Verify user: " + userID);
		menuafter.clickToProvisionThenChooseSubscribers();
		if(!subscriber.verifyAddingSubscriber(userID)){
			TC_005 = false;
			logger.error("**TC Result: TC_015_ORGCreateSubsWith"+language+"Languagez: Verify adding Subscriber with userID: FAILED**");
			Reporter.log("**TC Result: TC_015_ORGCreateSubsWith"+language+"Languagez: Verify adding Subscriber with userID: FAILED**");
		} else{			
			subscriber.ModifySubscribers(userID);
			addsub = new AddSubscriberForm(driver, waitTime);
			logger.info("Verify Default language: " + language);
			if(!addsub.verifyDefaultLanguage(logger, language)){
				TC_005 = false;
				logger.error("**TC Result: TC_015_ORGCreateSubsWith"+language+"Language: Verify adding Subscriber with default language: FAILED**" + addsub.getDefaultLanguage());				
				Reporter.log("**TC Result: TC_015_ORGCreateSubsWith"+language+"Language: Verify adding Subscriber with default language: FAILED**" + addsub.getDefaultLanguage());
			} 
			addsub.clickReturnToList();
			WebUtil.waitFor_LoadingFinished(driver, 120);
		}
		logger.info("Add a new subscriber successfully");
		
		TC_006 = subscriber.changeSubscriberLanguageAndVersa(logger,waitTime, "English",language, userID, subscriber, addsub);
		
		// go to Subscriber page		
		logger.info("Search for: "+userID);
		subscriber.searchUserID(userID);
		logger.info("Click on check box ID");
		subscriber.checkOnCheckBoxUserID();
	    logger.info("Click on remove user button");
		subscriber.clickButtonRemoveUser();
		logger.info("CLick on confirm YES button");
		subscriber.clickButtonYesRemoveUser();
		WebUtil.waitFor_LoadingFinished(driver, 120);
		subscriber.VerifyAlertMessage("subscriber removed.");
				
		// verify Subscriber is removed successfully
		logger.info("Verify removing subscriber: " + userID);
		if(!subscriber.verifyRemovingSubscriber(userID)){
			TC_007 = false;
			logger.error("**TC Result: TC_017_ORGRemoveSubsWith"+language+"Language: Verify Removing Subscriber with userID: FAILED**");
			Reporter.log("**TC Result: TC_017_ORGRemoveSubsWith"+language+"Language: Verify Removing Subscriber with userID: FAILED**");
		}
		logger.info("Removing a subscriber successfully");

		// Echo Results
		if(TC_005){
			logger.info("**TC Result: TC_015_ORGCreateSubsWith"+language+"Language finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_015_ORGCreateSubsWith"+language+"Language finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_015_ORGCreateSubsWith"+language+"Language finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_015_ORGCreateSubsWith"+language+"Language finished successfully with Result is : FAILED**");
		}
		if(TC_006){
			logger.info("**TC Result: TC_016_ORGChangeLanguageForSubsFromEnglishTo"+language+"AndVersa finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_016_ORGChangeLanguageForSubsFromEnglishTo"+language+"AndVersa finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_016_ORGChangeLanguageForSubsFromEnglishTo"+language+"AndVersa finished successfully with Result is : FAILED**");
			Reporter.log("**TC Result: TC_016_ORGChangeLanguageForSubsFromEnglishTo"+language+"AndVersa finished successfully with Result is : FAILED**");
		}
		if(TC_007){
			logger.info("**TC Result: TC_017_ORGRemoveSubsWith"+language+"Language finished successfully with Result is : PASSED**");
			Reporter.log("**TC Result: TC_017_ORGRemoveSubsWith"+language+"Language finished successfully with Result is : PASSED**");
		} else{
			logger.info("**TC Result: TC_017_ORGRemoveSubsWith"+language+"Language finished successfully with Result is : FAILED**");
			logger.info("**TC Result: TC_017_ORGRemoveSubsWith"+language+"Language finished successfully with Result is : FAILED**");
		}
		Assert.assertTrue(TC_007 && TC_005 && TC_006);
	}
	
}