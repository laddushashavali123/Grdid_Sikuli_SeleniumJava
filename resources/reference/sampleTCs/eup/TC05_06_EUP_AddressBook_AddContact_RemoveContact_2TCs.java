package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import automation.DriverFactory;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabAddressBook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC05_06_EUP_AddressBook_AddContact_RemoveContact_2TCs extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC05_06");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC05_06_EUP_AddressBook_AddContact_RemoveContact_2TCs_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("Login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		String contactName = "addcontact";
		String sipAddress = "addcontact@gmail.com";
		String alertAdd = "Contact added.";
		String alertRemove = "Contacts removed.";
		
		logger.info("-----------------------Test case: Add Contact-----------------------");
		Reporter.log("1. Test case 'Add Contact'");
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Add Contact with Name '" + contactName + "' SIP Address '" + sipAddress + "'");
		addressBookT.addContact(contactName, sipAddress);
		
		logger.info("Verify that alert '" + alertAdd + "' appears after adding a Contact");
		boolean result1a = addressBookT.verifyAlertAppears(alertAdd);
		if ( result1a ) {
			logger.info("Alert '" + alertAdd + "' appears after adding a Contact: PASSED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding a Contact: PASSED");
		}
		else {
			logger.info("Alert '" + alertAdd + "' appears after adding a Contact: FAILED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding a Contact: FAILED");
		}
		
		logger.info("Verify that Contact '" + contactName + "' is listed in table 'CONTACTS'");
		boolean result1b = addressBookT.verifyContactListedInTableContacts(contactName);
		if ( result1b ) {
			logger.info("Contact '" + contactName + "' is listed in table 'CONTACTS': PASSED");
			Reporter.log("- Contact that has been added is listed in table 'CONTACTS': PASSED");
		}
		else {
			logger.info("Contact '" + contactName + "' is listed in table 'CONTACTS': FAILED");
			Reporter.log("- Contact that has been added is listed in table 'CONTACTS': FAILED");
		}
		
		boolean result1 = result1a && result1b;
		if (result1) {
			logger.info("--> Test case 'Add Contact': PASSED");
			Reporter.log("--> Test case 'Add Contact': PASSED");
		} 
		else {
			logger.info("--> Test case 'Add Contact': FAILED");
			Reporter.log("--> Test case 'Add Contact': FAILED");
		}
		
		logger.info("-----------------------Test case: Remove Contact-----------------------");
		Reporter.log("");
		Reporter.log("2. Test case 'Remove Contact'");
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Remove Contact Name '" + contactName + "'");
		addressBookT.removeContact(contactName);
		
		logger.info("Verify that alert '" + alertRemove + "' appears after removing a Contact");
		boolean result2a = addressBookT.verifyAlertAppears(alertRemove);
		if ( result2a ) {
			logger.info("Alert '" + alertRemove + "' appears after removing a Contact: PASSED");
			Reporter.log("- Alert '" + alertRemove + "' appears after removing a Contact: PASSED");
		}
		else {
			logger.info("Alert '" + alertRemove + "' appears after removing a Contact: FAILED");
			Reporter.log("- Alert '" + alertRemove + "' appears after removing a Contact: FAILED");
		}
		
		logger.info("Verify that Contact '" + contactName + "' is not listed in table 'CONTACTS'");
		boolean result2b = !addressBookT.verifyContactListedInTableContacts(contactName);
		if ( result2b ) {
			logger.info("Contact '" + contactName + "' is not listed in table 'CONTACTS': PASSED");
			Reporter.log("- Contact that has been removed is not listed in table 'CONTACTS': PASSED");
		}
		else {
			logger.info("Contact '" + contactName + "' is not listed in table 'CONTACTS': FAILED");
			Reporter.log("- Contact that has been removed is not listed in table 'CONTACTS': FAILED");
		}
		
		boolean result2 = result2a && result2b;
		if (result2) {
			logger.info("--> Test case 'Remove Contact': PASSED");
			Reporter.log("--> Test case 'Remove Contact': PASSED");
		} 
		else {
			logger.info("--> Test case 'Remove Contact': FAILED");
			Reporter.log("--> Test case 'Remove Contact': FAILED");
		}
	
		Assert.assertTrue(result1 && result2);
	}
}
