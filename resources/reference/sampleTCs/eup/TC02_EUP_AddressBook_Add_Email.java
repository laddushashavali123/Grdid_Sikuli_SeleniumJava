package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import nuvia.eup.pages.FormManageSelf;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabAddressBook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC02_EUP_AddressBook_Add_Email extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC02");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC02_EUP_AddressBook_Add_Email_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		String email = "auto_email@gmail.com";
		String alert = "Contact updated.";
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		FormManageSelf manageS = new FormManageSelf(driver, timeout);
		
		String prev_email = manageS.getEmail();
		logger.info("Current Email: '" + prev_email + "'");
		
		logger.info("Set 'Email': '" + email + "'");
		manageS.setEmail(email);
		
		logger.info("Click 'SAVE CONTACT' Button");
		manageS.clickSaveContactButton();
		
		logger.info("Verify that alert '" + alert + "' appears after adding Email");
		boolean result1a = addressBookT.verifyAlertAppears(alert);
		if ( result1a ) {
			logger.info("Alert '" + alert + "' appears after adding Email: PASSED");
			Reporter.log("- Alert '" + alert + "' appears after adding Email: PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after adding Email: FAILED");
			Reporter.log("- Alert '" + alert + "' appears after adding Email: FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		String get_email = manageS.getEmail();
		logger.info("Current Email: '" + get_email + "'");
		
		logger.info("Verify that Email is '" + email + "'");
		boolean result1b = email.equals(get_email);
		if ( result1b ) {
			logger.info("Email is updated after editing: PASSED");
			Reporter.log("Email is updated after editing: PASSED");
		}
		else {
			logger.info("Email is updated after editing: FAILED");
			Reporter.log("Email is updated after editing: FAILED");
		}
		
		boolean result = result1a && result1b;
		if ( result ) {
			logger.info("Test case 'Add Email': PASSED");
			Reporter.log("Test case 'Add Email': PASSED");
		}
		else {
			logger.info("Test case 'Add Email': FAILED");
			Reporter.log("Test case 'Add Email': FAILED");
		}
		
		logger.info("Change Email back to '" + prev_email + "'");
		logger.info("Set 'Email': '" + prev_email + "'");
		manageS.setEmail(prev_email);
		
		logger.info("Click 'SAVE CONTACT' Button");
		manageS.clickSaveContactButton();
		
		Assert.assertTrue(result);

	}
}
