
package nuvia.eup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class TC04_EUP_AddressBook_Add_Mobile_Phone extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC04");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC04_EUP_AddressBook_Add_Mobile_Phone_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
		Date date = new Date();
		String datetext = dateFormat.format(date);
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		String mobilePhone = "4444 - Edit " + datetext;
		String alert = "Contact updated.";
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		FormManageSelf manageS = new FormManageSelf(driver, timeout);
		
		logger.info("Set 'Mobile Phone': '" + mobilePhone + "'");
		manageS.setMobilePhone(mobilePhone);
		
		logger.info("Click 'SAVE CONTACT' Button");
		manageS.clickSaveContactButton();
		
		logger.info("Verify that alert '" + alert + "' appears after adding Mobile Phone");
		boolean result1a = addressBookT.verifyAlertAppears(alert);
		if ( result1a ) {
			logger.info("Alert '" + alert + "' appears after adding Mobile Phone: PASSED");
			Reporter.log("- Alert '" + alert + "' appears after adding Mobile Phone: PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after adding Mobile Phone: FAILED");
			Reporter.log("- Alert '" + alert + "' appears after adding Mobile Phone: FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		String get_mobilePhone = manageS.getMobilePhone();
		logger.info("Current Mobile Phone: '" + get_mobilePhone + "'");
		
		logger.info("Verify that Mobile Phone is '" + mobilePhone + "'");
		boolean result1b = mobilePhone.equals(get_mobilePhone);
		if ( result1b ) {
			logger.info("Mobile Phone is updated after editing: PASSED");
			Reporter.log("Mobile Phone is updated after editing: PASSED");
		}
		else {
			logger.info("Mobile Phone is updated after editing: FAILED");
			Reporter.log("Mobile Phone is updated after editing: FAILED");
		}
		
		boolean result = result1a && result1b;
		if ( result ) {
			logger.info("Test case 'Add Mobile Phone': PASSED");
			Reporter.log("Test case 'Add Mobile Phone': PASSED");
		}
		else {
			logger.info("Test case 'Add Mobile Phone': FAILED");
			Reporter.log("Test case 'Add Mobile Phone': FAILED");
		}
		
		Assert.assertTrue(result);
		
	}
}
