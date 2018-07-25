package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import automation.utils.web.GetScreenshot;
import nuvia.eup.pages.FormManageGroups;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabAddressBook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC34_EUP_AddressBook_Edit_Group_Contact extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC34");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC34_EUP_AddressBook_Edit_Group_Contact_(int timeout, String url, String user, String password, String domain) throws Exception {
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
		
		String groupName = "editgroup";
		String groupNameEdit = "editdone";
		String alert = "Group updated.";
		
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
		
		logger.info("Click Edit Group Icon of Group '" + groupName + "'");
		manageGrF.clickEditGroupContactInTableGroups(groupName);
		
		logger.info("Change Name of Group '" + groupName + "' to '" + groupNameEdit + "'");
		manageGrF.setNameofGroupInTableGroups(groupName, groupNameEdit);
		
		logger.info("Click Accept Edit Group Icon of Group '" + groupName + "'");
		manageGrF.clickAcceptEditGroupContactInTableGroups(groupName);
		
		logger.info("Verify that alert '" + alert + "' appears after editing a Group Contact");
		boolean result1a = addressBookT.verifyAlertAppears(alert);
		if ( result1a ) {
			logger.info("Alert '" + alert + "' appears after editing a Group Contact: PASSED");
			Reporter.log("- Alert '" + alert + "' appears after editing a Group Contact: PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after editing a Group Contact: FAILED");
			Reporter.log("- Alert '" + alert + "' appears after editing a Group Contact: FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1_Vefiry_Alert");
		}
		
		logger.info("Verify that Group Contact '" + groupNameEdit + "' is listed in table 'GROUPS'");
		boolean result1b = manageGrF.verifyGroupListedInTableGroups(groupNameEdit);
		if ( result1b ) {
			logger.info("Group Contact '" + groupNameEdit + "' is listed in table 'GROUPS': PASSED");
			Reporter.log("- Group Contact that has been edited is listed in table 'GROUPS': PASSED");
		}
		else {
			logger.info("Group Contact '" + groupNameEdit + "' is listed in table 'GROUPS': FAILED");
			Reporter.log("- Group Contact that has been edited is listed in table 'GROUPS': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_2_Group_Listed_In_Table");
		}
		
		boolean result1 = result1a && result1b;
		if (result1) {
			logger.info("--> Test case 'Edit Group Contact': PASSED");
			Reporter.log("--> Test case 'Edit Group Contact': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit Group Contact': FAILED");
			Reporter.log("--> Test case 'Edit Group Contact': FAILED");
		}
		
		String groupRemove = groupNameEdit;
		if ( !result1b ) groupRemove = groupName;
				
		logger.info("Remove Group Contact '" + groupRemove + "'");
		manageGrF.removeGroupContact(groupRemove);
		
		Assert.assertTrue(result1);
		
	}
}

