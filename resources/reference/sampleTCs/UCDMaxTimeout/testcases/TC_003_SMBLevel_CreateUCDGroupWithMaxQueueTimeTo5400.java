package nuvia.UCDMaxTimeout.testcases;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import automation.utils.web.WebUtil;
import nuvia.pages.AddSubscriberForm;
import nuvia.pages.CallAnswerGroupPage;
import nuvia.pages.LoginPage;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.MenuBeforeChoseCustomer;
import nuvia.pages.Subscribers;

public class TC_003_SMBLevel_CreateUCDGroupWithMaxQueueTimeTo5400 extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_001_OrgLevel_CreateUCDGroupWithMaxQueueTimeTo5400");
	@Test
	@Parameters({"waitTime","Partner_url","Partner_user","Partner_password","UCD_SMB_customer", "UCD_MaxWaitTime", "SMBUCDSub"})
	public void OrgLevel_CreateUCDGroupWithMaxQueueTimeTo5400(int waitTime, String url, String user, String password, String customer, String maxWaitTime, String sub) throws Exception {

		WebDriver driver = getDriver();
		initializeDriver(driver, url, waitTime);
		LoginPage login = new LoginPage(driver, waitTime);
 		login.Login(user, password, url);
		
 		String nameOfGroup = "AutoUCDMAX";
 		String alertAdd = "Call answer group created.";
 		String alertRemove = "Call answer groups removed.";
 		
 		boolean result1 = false;
 		boolean result2 = false;
 		boolean result3 = false;

		MenuBeforeChoseCustomer menubefore = new MenuBeforeChoseCustomer(driver, waitTime);
		logger.info("Search customer '" + customer + "' and click");
		menubefore.searchCustomer(customer);
		
		MenuAfterChoseCustomer menuafter = new MenuAfterChoseCustomer(driver, waitTime);
		logger.info("Select Call Answer Groups");
		menuafter.clickToProvisionThenChooseCallAnswerGroups();
		
		CallAnswerGroupPage callgroup= new CallAnswerGroupPage(driver, waitTime);
		
		logger.info("-----------------------Test case: Add Hunt Groups of UCD-----------------------");
		Reporter.log("1. Test case: Add Hunt Groups of UCD");
		callgroup.addCallAnswerGroup_WithMaxWaitTime("Call Distribution", nameOfGroup, maxWaitTime, sub);		
		
		logger.info("Verify that alert '" + alertAdd + "' appears after adding a catalog");
		boolean result1a = callgroup.verifyAlertAppears(alertAdd);
		if ( result1a ) {
			logger.info("Alert '" + alertAdd + "' appears after adding a catalog: PASSED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding a catalog: PASSED");
		}
		else {
			logger.info("Alert '" + alertAdd + "' appears after adding a catalog: FAILED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding a catalog: FAILED");
			logger.info("Go to Call Answer Group");
			menuafter.clickToProvisionThenChooseCallAnswerGroups();
		}
		
		logger.info("Verify that Call Answer Group '" + nameOfGroup + "' is listed in table 'CALL ANSWER GROUPS'");
		boolean result1b = callgroup.verifyCallAnwerGroupListedInTableCallAnswerGroups(nameOfGroup);
		if ( result1b ) {
			logger.info("Call Answer Group '" + nameOfGroup + "' is listed in table 'CALL ANSWER GROUPS': PASSED");
			Reporter.log("- Call Answer Group that has been added is listed in table 'CALL ANSWER GROUPS': PASSED");
		}
		else {
			logger.info("Call Answer Group '" + nameOfGroup + "' is listed in table 'CALL ANSWER GROUPS': FAILED");
			Reporter.log("- Call Answer Group that has been added is listed in table 'CALL ANSWER GROUPS': FAILED");
		}
		logger.info("Verify that Call Answer Group '" + nameOfGroup + "' has the Max WaitTime is " + maxWaitTime);
		boolean result1c = callgroup.verifyCallAnswerGroup_WithMaxWaitTime(nameOfGroup, maxWaitTime);
		if ( result1c ) {
			logger.info("Call Answer Group '" + nameOfGroup + "' has the Max Wait Time is "+maxWaitTime+": PASSED");
			Reporter.log("- Call Answer Group that has been added has the Max Wait Time is "+maxWaitTime+": PASSED");
		}
		else {
			logger.info("Call Answer Group '" + nameOfGroup + "' has the Max Wait Time is "+maxWaitTime+": FAILED");
			Reporter.log("- Call Answer Group that has been added has the Max Wait Time is "+maxWaitTime+": FAILED");
		}
		
		result1 = result1a && result1b & result1c;
		if (result1) {
			logger.info("--> Test case: Add Hunt Groups of UCD: PASSED");
			Reporter.log("--> Test case: Add Hunt Groups of UCD: PASSED");
		} 
		else {
			logger.info("--> Test case: Add Hunt Groups of UCD: FAILED");
			Reporter.log("--> Test case: Add Hunt Groups of UCD: FAILED");
			
		}
		
		
		logger.info("-----------------------Test case: Remove Group-----------------------");
		Reporter.log("");
		Reporter.log("3. Test case: Edit Hunt Groups of UCD");
		callgroup.removeCallAnswerGroup(nameOfGroup);
		
		logger.info("Verify that alert '" + alertRemove + "' appears after removing a Call Answer Group");
		boolean result3a = callgroup.verifyAlertAppears(alertRemove);
		if ( result3a ) {
			logger.info("Alert '" + alertRemove + "' appears after removing a Call Answer Group: PASSED");
			Reporter.log("- Alert '" + alertRemove + "' appears after removing a Call Answer Group: PASSED");
		}
		else {
			logger.info("Alert '" + alertRemove + "' appears after removing a Call Answer Group: FAILED");
			Reporter.log("- Alert '" + alertRemove + "' appears after removing a Call Answer Group: FAILED");
		}
		
		logger.info("Verify that Call Answer Group '" + nameOfGroup + "' is not listed in table 'CALL ANSWER GROUPS'");
		boolean result3b = callgroup.verifyCallAnwerGroupListedInTableCallAnswerGroups(nameOfGroup);
		result3b = !result3b;
		if ( result3b ) {
			logger.info("Call Answer Group '" + nameOfGroup + "' is not listed in table 'CALL ANSWER GROUPS': PASSED");
			Reporter.log("- Call Answer Group that has been removed is not listed in table 'CALL ANSWER GROUPS': PASSED");
		}
		else {
			logger.info("Call Answer Group '" + nameOfGroup + "' is not listed in table 'CALL ANSWER GROUPS': FAILED");
			Reporter.log("- Call Answer Group that has been removed is not listed in table 'CALL ANSWER GROUPS': FAILED");
		}
		result3 = result3a && result3b;
		if (result3) {
			logger.info("--> Test case: Edit Hunt Groups of UCD: PASSED");
			Reporter.log("--> Test case: Edit Hunt Groups of UCD: PASSED");
		} 
		else {
			logger.info("--> Test case: Edit Hunt Groups of UCD: FAILED");
			Reporter.log("--> Test case: Edit Hunt Groups of UCD: FAILED");
			
		}
		
		Assert.assertTrue(result1 && result3 );
	}
}
