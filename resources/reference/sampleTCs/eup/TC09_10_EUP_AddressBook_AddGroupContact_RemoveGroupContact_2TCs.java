package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import nuvia.eup.pages.FormManageGroups;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabAddressBook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC09_10_EUP_AddressBook_AddGroupContact_RemoveGroupContact_2TCs extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC09_10");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC09_10_EUP_AddressBook_AddGroupContact_RemoveGroupContact_2TCs_(int timeout, String url, String user, String password, String domain) throws Exception {
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
		
		String groupName = "addgroup";
		String alertAdd = "Group added.";
		String alertRemove = "Group removed.";
		
		logger.info("-----------------------Test case: Add Group Contact-----------------------");
		Reporter.log("");
		Reporter.log("1. Test case 'Add Group Contact'");
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Click 'MANAGE GROUPS' Button");
		addressBookT.clickManageGroupsButton();
		
		FormManageGroups manageGrF = new FormManageGroups(driver, timeout);
		logger.info("Add Group Contact '" + groupName + "'");
		manageGrF.addGroupContact(groupName);
		
		logger.info("Verify that alert '" + alertAdd + "' appears after adding a Group Contact");
		boolean result1a = addressBookT.verifyAlertAppears(alertAdd);
		if ( result1a ) {
			logger.info("Alert '" + alertAdd + "' appears after adding a Group Contact: PASSED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding a Group Contact: PASSED");
		}
		else {
			logger.info("Alert '" + alertAdd + "' appears after adding a Group Contact: FAILED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding a Group Contact: FAILED");
		}
		
		logger.info("Verify that Group Contact '" + groupName + "' is listed in table 'GROUPS'");
		boolean result1b = manageGrF.verifyGroupListedInTableGroups(groupName);
		if ( result1b ) {
			logger.info("Group Contact '" + groupName + "' is listed in table 'GROUPS': PASSED");
			Reporter.log("- Group Contact that has been added is listed in table 'GROUPS': PASSED");
		}
		else {
			logger.info("Group Contact '" + groupName + "' is listed in table 'GROUPS': FAILED");
			Reporter.log("- Group Contact that has been added is listed in table 'GROUPS': FAILED");
		}
		
		boolean result1 = result1a && result1b;
		if (result1) {
			logger.info("--> Test case 'Add Group Contact': PASSED");
			Reporter.log("--> Test case 'Add Group Contact': PASSED");
		} 
		else {
			logger.info("--> Test case 'Add Group Contact': FAILED");
			Reporter.log("--> Test case 'Add Group Contact': FAILED");
		}
		
		logger.info("-----------------------Test case: Remove Group Contact-----------------------");
		Reporter.log("");
		Reporter.log("2. Test case 'Remove Group Contact'");
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Click 'MANAGE GROUPS' Button");
		addressBookT.clickManageGroupsButton();
		
		logger.info("Add Group Contact '" + groupName + "'");
		manageGrF.removeGroupContact(groupName);
		
		logger.info("Verify that alert '" + alertRemove + "' appears after removing a Group Contact");
		boolean result2a = addressBookT.verifyAlertAppears(alertRemove);
		if ( result1a ) {
			logger.info("Alert '" + alertRemove + "' appears after removing a Group Contact: PASSED");
			Reporter.log("- Alert '" + alertRemove + "' appears after removing a Group Contact: PASSED");
		}
		else {
			logger.info("Alert '" + alertRemove + "' appears after removing a Group Contact: FAILED");
			Reporter.log("- Alert '" + alertRemove + "' appears after removing a Group Contact: FAILED");
		}
		
		logger.info("Verify that Group Contact '" + groupName + "' is not listed in table 'GROUPS'");
		boolean result2b = !manageGrF.verifyGroupListedInTableGroups(groupName);
		if ( result1b ) {
			logger.info("Group Contact '" + groupName + "' is not listed in table 'GROUPS': PASSED");
			Reporter.log("- Group Contact that has been removed is not listed in table 'GROUPS': PASSED");
		}
		else {
			logger.info("Group Contact '" + groupName + "' is not listed in table 'GROUPS': FAILED");
			Reporter.log("- Group Contact that has been removed is not listed in table 'GROUPS': FAILED");
		}
		
		boolean result2 = result2a && result2b;
		if (result2) {
			logger.info("--> Test case 'Remove Group Contact': PASSED");
			Reporter.log("--> Test case 'Remove Group Contact': PASSED");
		} 
		else {
			logger.info("--> Test case 'Remove Group Contact': FAILED");
			Reporter.log("--> Test case 'Remove Group Contact': FAILED");
		}
		
		boolean result = result1 && result2;
		Assert.assertTrue(result);
		
	}
}

