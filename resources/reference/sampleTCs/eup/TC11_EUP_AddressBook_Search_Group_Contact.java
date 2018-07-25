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

public class TC11_EUP_AddressBook_Search_Group_Contact extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC11");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC11_EUP_AddressBook_Search_Group_Contact_(int timeout, String url, String user, String password, String domain) throws Exception {
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
		
		String groupName = "searchgroup";
		
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Click 'MANAGE GROUPS' Button");
		addressBookT.clickManageGroupsButton();
		
		FormManageGroups manageGrF = new FormManageGroups(driver, timeout);
		logger.info("Add Group Contact '" + groupName + "'");
		manageGrF.addGroupContact(groupName);
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Click 'MANAGE GROUPS' Button");
		addressBookT.clickManageGroupsButton();
		
		logger.info("Search Group '" + groupName + "'" );
		manageGrF.searchGroup(groupName);
		
		logger.info("Verify that Group Contact '" + groupName + "' is listed in table 'GROUPS' after searching");
		boolean result = manageGrF.verifyGroupListedInTableGroups(groupName);
		if ( result ) {
			logger.info("Group Contact '" + groupName + "' is listed in table 'GROUPS' after searching: PASSED");
			Reporter.log("- Group Contact is listed in table 'GROUPS' after searching: PASSED");
		}
		else {
			logger.info("Group Contact '" + groupName + "' is not listed in table 'GROUPS' after searching: FAILED");
			Reporter.log("- Group Contact is listed in table 'GROUPS' after searching: FAILED");
		}
		
		logger.info("Remove Group Contact '" + groupName + "'");
		manageGrF.removeGroupContact(groupName);
		
		Assert.assertTrue(result);
		
	}
}

