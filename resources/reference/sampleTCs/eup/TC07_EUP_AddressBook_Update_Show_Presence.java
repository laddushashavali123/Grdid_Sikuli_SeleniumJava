package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import nuvia.eup.pages.FormAddContact;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabAddressBook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC07_EUP_AddressBook_Update_Show_Presence extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC07");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC07_EUP_AddressBook_Update_Show_Presence_(int timeout, String url, String user, String password, String domain) throws Exception {
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
		
		String contactName = "showpresence";
		String sipAddress = "showpresence@gmail.com";
		String alert = "Contact updated.";
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Add Contact with Name '" + contactName + "' SIP Address '" + sipAddress + "'");
		addressBookT.addContact(contactName, sipAddress);
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Click Contact '" + contactName + "' in Table CONTACTS");
		addressBookT.clickContactInTableContacts(contactName);
		
		FormAddContact addContactF = new FormAddContact(driver, timeout);
		
		String currentShowPresence = addContactF.getShowPresence();
		logger.info("'Show Presence' is currently set as '" + currentShowPresence + "'");
		
		if (currentShowPresence.equals("Disabled")) {
			logger.info("Select 'Show Presence' as 'Enabled'");
			addContactF.selectShowPresence("Enabled");
			logger.info("Click 'SAVE CONTACT' Button");
			addContactF.clickSaveContactButton();
			
			logger.info("Click 'Address Book' Tab");
			accountP.clickAddressBookTab();
			logger.info("Click Contact '" + contactName + "' in Table CONTACTS");
			addressBookT.clickContactInTableContacts(contactName);
			
		} 
		
		logger.info("----------------------Disable 'Show Presence'----------------------");
		logger.info("Select 'Show Presence' as 'Disabled'");
		addContactF.selectShowPresence("Disabled");
		logger.info("Click 'SAVE CONTACT' Button");
		addContactF.clickSaveContactButton();
		
		logger.info("Verify that alert '" + alert + "' appears after disabling 'Show Presence'");
		boolean result1a = addressBookT.verifyAlertAppears(alert);
		if ( result1a ) {
			logger.info("Alert '" + alert + "' appears after disabling 'Show Presence'");
			Reporter.log("- Alert '" + alert + "' appears after disabling 'Show Presence'");
		}
		else {
			logger.info("Alert '" + alert + "' appears after disabling 'Show Presence'");
			Reporter.log("- Alert '" + alert + "' appears after disabling 'Show Presence'");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click Contact '" + contactName + "' in Table CONTACTS");
		addressBookT.clickContactInTableContacts(contactName);
		
		String currentShowPresenceDis =  addContactF.getShowPresence();
		logger.info("'Show Presence' is currently set as '" + currentShowPresenceDis +"'");
		
		logger.info("Verify that 'Show Presence' is set as 'Disabled' after disabling 'Show Presence'");
		
		boolean result1b = currentShowPresenceDis.equals("Disabled");
		if ( result1b ) {
			logger.info("'Show Presence' is set as 'Disabled' after disabling 'Show Presence': PASSED");
			Reporter.log("- 'Show Presence' is set as 'Disabled' after disabling 'Show Presence': PASSED");
		}
		else {
			logger.info("'Show Presence' is set as 'Disabled' after disabling 'Show Presence': FAILED");
			Reporter.log("- 'Show Presence' is set as 'Disabled' after disabling 'Show Presence': FAILED");
		}
		
		boolean result1 = result1a && result1b;
		if ( result1 ) {
			logger.info("-> Disable 'Show Presence': PASSED");
			Reporter.log("-> Disable 'Show Presence': PASSED");
		} else {
			logger.info("-> Disable 'Show Presence': FAILED");
			Reporter.log("-> Disable 'Show Presence': FAILED");
		}
		
		logger.info("----------------------Enabled 'Show Presence'----------------------");
		Reporter.log("");
		logger.info("Select 'Show Presence' as 'Enabled'");
		addContactF.selectShowPresence("Enabled");
		logger.info("Click 'SAVE CONTACT' Button");
		addContactF.clickSaveContactButton();
		
		logger.info("Verify that alert '" + alert + "' appears after enabling 'Show Presence'");
		boolean result2a = addressBookT.verifyAlertAppears(alert);
		if ( result2a ) {
			logger.info("Alert '" + alert + "' appears after enabling 'Show Presence'");
			Reporter.log("- Alert '" + alert + "' appears after enabling 'Show Presence'");
		}
		else {
			logger.info("Alert '" + alert + "' appears after enabling 'Show Presence'");
			Reporter.log("- Alert '" + alert + "' appears after enabling 'Show Presence'");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click Contact '" + contactName + "' in Table CONTACTS");
		addressBookT.clickContactInTableContacts(contactName);
		
		String currentShowPresenceEna =  addContactF.getShowPresence();
		logger.info("'Show Presence' is currently set as '" + currentShowPresenceEna +"'");
		
		logger.info("Verify that 'Show Presence' is set as 'Enabled' after enabling 'Show Presence'");
		
		boolean result2b = currentShowPresenceEna.equals("Enabled");
		if ( result2b ) {
			logger.info("'Show Presence' is set as 'Enabled' after enabling 'Show Presence': PASSED");
			Reporter.log("- 'Show Presence' is set as 'Enabled' after enabling 'Show Presence': PASSED");
		}
		else {
			logger.info("'Show Presence' is set as 'Enabled' after enabling 'Show Presence': FAILED");
			Reporter.log("- 'Show Presence' is set as 'Enabled' after enabling 'Show Presence': FAILED");
		}
		
		boolean result2 = result2a && result2b;
		if ( result1 ) {
			logger.info("-> Enable 'Show Presence': PASSED");
			Reporter.log("-> Enable 'Show Presence': PASSED");
		} else {
			logger.info("-> Enable 'Show Presence': FAILED");
			Reporter.log("-> Enable 'Show Presence': FAILED");
		}
		
		Reporter.log("");
		boolean result = result1 && result2;
		if ( result ) {
			logger.info("=> Test case 'Update Show Presence': PASSED");
			Reporter.log("=> Test case 'Update Show Presence': PASSED");
		} else {
			logger.info("=> Test case 'Update Show Presence': FAILED");
			Reporter.log("=> Test case 'Update Show Presence': FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Remove Contact '" + contactName + "'");
		addressBookT.removeContact(contactName);
		
		Assert.assertTrue(result);
		
	}
}
