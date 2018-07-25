package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import automation.DriverFactory;
import automation.utils.web.GetScreenshot;
import nuvia.eup.pages.FormManageSelf;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabAddressBook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC01_EUP_AddressBook_Add_Business_Phone extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC01");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC01_EUP_AddressBook_Add_Business_Phone_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		String businessPhone = "123456789";
		String alert = "Contact updated.";
		
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		FormManageSelf manageS = new FormManageSelf(driver, timeout);
		
		String prev_businessPhone = manageS.getBusinessPhone();
		logger.info("Current Business Phone: '" + prev_businessPhone + "'");
		
		logger.info("Set 'Business Phone': '" + businessPhone + "'");
		manageS.setBusinessPhone(businessPhone);
		
		logger.info("Click 'SAVE CONTACT' Button");
		manageS.clickSaveContactButton();
		
		logger.info("Verify that alert '" + alert + "' appears after adding Business Phone");
		boolean result1a = addressBookT.verifyAlertAppears(alert);
		if ( result1a ) {
			logger.info("Alert '" + alert + "' appears after adding Business Phone: PASSED");
			Reporter.log("- Alert '" + alert + "' appears after adding Business Phone: PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after adding Business Phone: FAILED");
			Reporter.log("- Alert '" + alert + "' appears after adding Business Phone: FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1_Alert");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		String get_businessPhone = manageS.getBusinessPhone();
		logger.info("Current Business Phone: '" + get_businessPhone + "'");
		
		logger.info("Verify that Business Phone is '" + businessPhone + "'");
		boolean result1b = businessPhone.equals(get_businessPhone);
		if ( result1b ) {
			logger.info("Business Phone is updated after editing: PASSED");
			Reporter.log("Business Phone is updated after editing: PASSED");
		}
		else {
			logger.info("Business Phone is updated after editing: FAILED");
			Reporter.log("Business Phone is updated after editing: FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_2_BusinessPhone");
		}
		
		boolean result = result1a && result1b;
		if ( result ) {
			logger.info("Test case 'Add Business Phone': PASSED");
			Reporter.log("Test case 'Add Business Phone': PASSED");
		}
		else {
			logger.info("Test case 'Add Business Phone': FAILED");
			Reporter.log("Test case 'Add Business Phone': FAILED");
		}
		
		logger.info("Change Business Phone back to '" + prev_businessPhone + "'");
		logger.info("Set 'Business Phone': '" + prev_businessPhone + "'");
		manageS.setBusinessPhone(prev_businessPhone);
		
		logger.info("Click 'SAVE CONTACT' Button");
		manageS.clickSaveContactButton();
		
		Assert.assertTrue(result);
	}
}
