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

public class TC15_EUP_AddressBook_AddFromFile extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC15");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password","EUP_fileLocation", "EUP_domain"})
	public void TC15_EUP_AddressBook_AddFromFile_(int timeout, String url, String user, String password, String fileLocation, String domain) throws Exception {
		WebDriver driver = getDriver();
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		String alertAdd = "Contacts added.";
		String contactName = "auto_firstName_AFF"; // get from excel file
		String file = fileLocation + "BatchAddressBook_Personal_Worksheet.csv";
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		//Sometimes the script cannot click 'Add From File' (The reason is because the portal is slow)
		// Workaround: -> Move to 'Routing' Tab then move back to 'Address Book' tab, then click 'Add From File'
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Click 'ADD FROM FILE' Button");
		addressBookT.clickAddFromFileButton();
		
		logger.info("Upload file '" + file + "'");
		addressBookT.uploadFile(file);
		
		logger.info("Click 'CONFIRM IMPORT' Button");
		addressBookT.clickConfirmImportButton();
		
		logger.info("Verify that alert '" + alertAdd + "' appears after adding from file");
		boolean result1a = addressBookT.verifyAlertAppears(alertAdd);
		if ( result1a ) {
			logger.info("Alert '" + alertAdd + "' appears after adding from file: PASSED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding from file: PASSED");
		}
		else {
			logger.info("Alert '" + alertAdd + "' appears after adding from file: FAILED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding from file: FAILED");
		}
		
		logger.info("Verify that Contact '" + contactName + "' is listed in table 'CONTACTS'");
		boolean result1b = addressBookT.verifyContactListedInTableContacts(contactName);
		if ( result1b ) {
			logger.info("Contact '" + contactName + "' is listed in table 'CONTACTS': PASSED");
			Reporter.log("- Contact that has been added from file is listed in table 'CONTACTS': PASSED");
		}
		else {
			logger.info("Contact '" + contactName + "' is listed in table 'CONTACTS': FAILED");
			Reporter.log("- Contact that has been added from file is listed in table 'CONTACTS': FAILED");
		}
		
		boolean result1 = result1a && result1b;
		if (result1) {
			logger.info("--> Test case 'Add From File': PASSED");
			Reporter.log("--> Test case 'Add From File': PASSED");
		} 
		else {
			logger.info("--> Test case 'Add From File': FAILED");
			Reporter.log("--> Test case 'Add From File': FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Remove Contact Name '" + contactName + "'");
		addressBookT.removeContact(contactName);
		
		Assert.assertTrue(result1);		
	}
}
