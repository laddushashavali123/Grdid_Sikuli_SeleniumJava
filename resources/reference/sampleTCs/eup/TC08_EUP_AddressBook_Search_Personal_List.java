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

public class TC08_EUP_AddressBook_Search_Personal_List extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC08");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC08_EUP_AddressBook_Search_Personal_List_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		String contactName = "searchpersonal";
		String sipAddress = "searchpersonal@gmail.com";
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Add Contact with Name '" + contactName + "' SIP Address '" + sipAddress + "'");
		addressBookT.addContact(contactName, sipAddress);
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Search 'Contact' " + contactName + "'");
		addressBookT.searchContact(contactName);
		
		logger.info("Verify that the 'search contact' feature works. Expect contact '" + contactName + "' to be listed in table CONTACTS after searching");
		boolean result = addressBookT.verifyContactListedInTableContacts(contactName);
		
		if ( result ) {
			logger.info("=> Test case 'Search Contact': PASSED");
			Reporter.log("=> Test case 'Search Contact': PASSED ");
		} else {
			logger.info("=> Test case 'Search Contact': FAILED");
			Reporter.log("=> Test case 'Search Contact': FAILED");
		}
		
		logger.info("Remove Contact '" + contactName + "'");
		addressBookT.removeContact(contactName);
		
		Assert.assertTrue(result);
		
	}
}
