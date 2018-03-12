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

public class TC_005_OrgLevel_Create_Update_Delete_UCDGroupWithMaxQueueTimeTo60_1200_5400_3TCs extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_005_OrgLevel_Create_Update_Delete_UCDGroupWithMaxQueueTimeTo60_1200_5400_3TCs");
	@Test
	@Parameters({"waitTime","Partner_url","Partner_user","Partner_password","UCD_customer", "UCD_MaxWaitTime", "ORGUCDSub"})
	public void OrgLevel_CreateUCDGroupWithMaxQueueTimeTo5400(int waitTime, String url, String user, String password, String customer, String maxWaitTime, String sub) throws Exception {

		/* Create UCD group with max queue time 60, 1200, 5400 successfully, more than 5400 or less than 60 will failed with error -Please enter a max wait time from 60 to 5400 seconds-
		 * 
		 * */
		WebDriver driver = getDriver();
		initializeDriver(driver, url, waitTime);
		LoginPage login = new LoginPage(driver, waitTime);
 		login.Login(user, password, url);
 		String nameOfGroup = "AutoUCDMAX_multi";
 		String level1 = "60";
 		String level2 = "1200";
 		String level3 = "5400"; 
 		String level4 = "10"; 
 		String level5 = "5410"; 
 		
 		boolean result1 = false;
 		boolean result2 = false;
 		boolean result3 = false;
 		boolean result4 = false;
 		boolean result5 = false;
 		boolean result6 = false;
 		
		MenuBeforeChoseCustomer menubefore = new MenuBeforeChoseCustomer(driver, waitTime);
		logger.info("Search customer '" + customer + "' and left click");
		menubefore.searchCustomer(customer);
		
		MenuAfterChoseCustomer menuafter = new MenuAfterChoseCustomer(driver, waitTime);
		logger.info("Select Call Answer Groups");
		menuafter.clickToProvisionThenChooseCallAnswerGroups();
		
		CallAnswerGroupPage callgroup= new CallAnswerGroupPage(driver, waitTime);
		
		logger.info("-----------------------Test case: Create UCD group with max queue time 60, 1200, 5400 successfully, more than 5400 or less than 60 will failed with error -Please enter a max wait time from 60 to 5400 seconds------------------------");
		result1 = Create_UCDWith_ValidMaxWaitTimes(nameOfGroup, level1, callgroup, menuafter, sub);
		result2 = updateUCD_ValidMaxWaitTimes(nameOfGroup, level2, level1, callgroup, menuafter);
		result3 = updateUCD_ValidMaxWaitTimes(nameOfGroup, level3, level2, callgroup, menuafter);		
		result4 = updateUCD_InValidMaxWaitTimes(nameOfGroup, level3, callgroup, menuafter);
		menuafter.clickToProvisionThenChooseCallAnswerGroups();
		result5 = CreateUCDWithMaxWaitTimesIncorrect(nameOfGroup+"_invalid", callgroup, menuafter, level4, level5);
		
		result6 = removeUCD(nameOfGroup, callgroup, menuafter);
		Assert.assertTrue(result1 & result2 & result3 & result4 & result5 & result6);
	}
	
	
	boolean Create_UCDWith_ValidMaxWaitTimes(String nameOfGroup, String level, CallAnswerGroupPage callgroup, MenuAfterChoseCustomer menuafter, String sub) throws Exception{
		
		String alertAdd = "Call answer group created."; 		 		
 		
		boolean result1 = false;
		Reporter.log("1. Test case: Create UCD group with max queue time " + level);
		callgroup.addCallAnswerGroup_WithMaxWaitTime("Call Distribution", nameOfGroup, level, sub);
		
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
		result1 = result1a & result1b;
		if (result1) {
			logger.info("--> Test case: Add Hunt Groups of UCD with valid of Max UCD " +level+ ": PASSED");
			Reporter.log("--> Test case: Add Hunt Groups of UCD with valid of Max UCD " +level+ ": PASSED");
		} 
		else {
			logger.info("--> Test case: Add Hunt Groups of UCD with valid of Max UCD " +level+ ": FAILED");
			Reporter.log("--> Test case: Add Hunt Groups of UCD with valid of Max UCD " +level+ ": FAILED");			
		}
		
		return result1;
	}
	
	boolean updateUCD_ValidMaxWaitTimes(String nameOfGroup, String level, String oldLevel, CallAnswerGroupPage callgroup, MenuAfterChoseCustomer menuafter) throws Exception{
		String alertEdit = "Call answer group updated.";
		boolean result = false;
		logger.info("-----------------------Test case: Edit Hunt Groups of UCD-----------------------");
		Reporter.log("");
		Reporter.log("2. Test case: Edit Hunt Groups of UCD from " + oldLevel + " to " + level);
		logger.info("Verify the Max Wait Time of Call Answer Group is correct");
		boolean result2c = callgroup.verifyAndEditCallAnswerGroup_MaxWaitTime(nameOfGroup, level, oldLevel);		
		if ( result2c ) {
			logger.info("The Max Wait Time of Call Answer Group is set successfully: PASSED");
			Reporter.log("- The Max Wait Time of Call Answer Group is set successfully: PASSED");
		}
		else {
			logger.info("The Max Wait Time of Call Answer Group is set successfully: FAILED");
			Reporter.log("- The Max Wait Time of Call Answer Group is set successfully: FAILED");
			logger.info("Go to Call Answer Group");
			menuafter.clickToProvisionThenChooseCallAnswerGroups();
		}
		
		logger.info("Verify that alert '" + alertEdit + "' appears after editing a Call Answer Group");
		boolean result2a = callgroup.verifyAlertAppears(alertEdit);
		if ( result2a ) {
			logger.info("Alert '" + alertEdit + "' appears after editing a Call Answer Group: PASSED");
			Reporter.log("- Alert '" + alertEdit + "' appears after editing a Call Answer Group: PASSED");
		}
		else {
			logger.info("Alert '" + alertEdit + "' appears after editing a Call Answer Group: FAILED");
			Reporter.log("- Alert '" + alertEdit + "' appears after editing a Call Answer Group: FAILED");
			logger.info("Go to Call Answer Group");
			menuafter.clickToProvisionThenChooseCallAnswerGroups();
		}
		
		logger.info("Verify that Call Answer Group '" + nameOfGroup + "' is listed in table 'CALL ANSWER GROUPS'");
		boolean result2b = callgroup.verifyCallAnwerGroupListedInTableCallAnswerGroups(nameOfGroup);
		if ( result2b ) {
			logger.info("Call Answer Group '" + nameOfGroup + "' is listed in table 'CALL ANSWER GROUPS': PASSED");
			Reporter.log("- Call Answer Group that has been edited is listed in table 'CALL ANSWER GROUPS': PASSED");
		}
		else {
			logger.info("Call Answer Group '" + nameOfGroup + "' is listed in table 'CALL ANSWER GROUPS': FAILED");
			Reporter.log("- Call Answer Group that has been edited is listed in table 'CALL ANSWER GROUPS': FAILED");
		}
		result = result2a && result2b & result2c;
		if (result) {
			logger.info("--> Test case: Edit Hunt Groups of UCD set Max Wait Time to "+level+": PASSED");
			Reporter.log("--> Test case: Edit Hunt Groups of UCD set Max Wait Time to "+level+": PASSED");
		} 
		else {
			logger.info("--> Test case: Edit Hunt Groups of UCD set Max Wait Time to "+level+": FAILED");
			Reporter.log("--> Test case: Edit Hunt Groups of UCD set Max Wait Time to "+level+": FAILED");
			
		}
		return result;
	}

	boolean updateUCD_InValidMaxWaitTimes(String nameOfGroup, String oldLevel, CallAnswerGroupPage callgroup, MenuAfterChoseCustomer menuafter) throws Exception{
		String level4 = "5500";
 		String level5 = "10";
		boolean result = false;
		logger.info("-----------------------Test case: Edit Hunt Groups of UCD from " + oldLevel + " to " + level4 + "-----------------------");
		Reporter.log("");
		Reporter.log("2. Test case: Edit Hunt Groups of UCD from " + oldLevel + " to " + level4);
		logger.info("Verify the Max Wait Time of Call Answer Group is incorrect");
		
		boolean result2a = callgroup.verifyAndEditCallAnswerGroup_InvalidMaxWaitTime(nameOfGroup, level4, true);		
		if ( result2a ) {
			logger.info("The Max Wait Time of Call Answer Group is set to "+level4+" and verify the alert successfully: PASSED");
			Reporter.log("- The Max Wait Time of Call Answer Group is set to "+level4+" and verify the alert successfully: PASSED");
		}
		else {
			logger.info("The Max Wait Time of Call Answer Group is set to "+level4+" and verify the alert successfully: FAILED");
			Reporter.log("- The Max Wait Time of Call Answer Group is set to "+level4+" and verify the alert successfully: FAILED");
			logger.info("Go to Call Answer Group");
			menuafter.clickToProvisionThenChooseCallAnswerGroups();
		}
		
		logger.info("-----------------------Test case: Edit Hunt Groups of UCD from " + oldLevel + " to " + level5 + "-----------------------");
		Reporter.log("2. Test case: Edit Hunt Groups of UCD from " + oldLevel + " to " + level5);
		logger.info("Verify the Max Wait Time of Call Answer Group is incorrect");
		boolean result2b = callgroup.verifyAndEditCallAnswerGroup_InvalidMaxWaitTime(nameOfGroup, level5, false);		
		if ( result2b ) {
			logger.info("The Max Wait Time of Call Answer Group is set to "+level5+" and verify the alert successfully: PASSED");
			Reporter.log("- The Max Wait Time of Call Answer Group is set to "+level5+" and verify the alert successfully: PASSED");
		}
		else {
			logger.info("The Max Wait Time of Call Answer Group is set to "+level5+" and verify the alert successfully: FAILED");
			Reporter.log("- The Max Wait Time of Call Answer Group is set to "+level5+" and verify the alert successfully: FAILED");
			logger.info("Go to Call Answer Group");
			menuafter.clickToProvisionThenChooseCallAnswerGroups();
		}
		
		result = result2a && result2b;
		if (result) {
			logger.info("--> Test case: Edit Hunt Groups of UCD set Max Wait Time to "+level4 + " and "+ level5 + " then verify alert: PASSED");
			Reporter.log("--> Test case: Edit Hunt Groups of UCD set Max Wait Time to "+level4 + " and "+ level5 + " then verify alert: PASSED");
		} 
		else {
			logger.info("--> Test case: Edit Hunt Groups of UCD set Max Wait Time to "+level4 + " and "+ level5 + " then verify alert: FAILED");
			Reporter.log("--> Test case: Edit Hunt Groups of UCD set Max Wait Time to "+level4 + " and "+ level5 + " then verify alert: FAILED");
			
		}
		return result;
	}

	boolean removeUCD(String nameOfGroup, CallAnswerGroupPage callgroup, MenuAfterChoseCustomer menuafter) throws Exception{
		String alertRemove = "Call answer groups removed.";
		boolean result3 = false;
		logger.info("-----------------------Test case: Remove Group-----------------------");
		Reporter.log("");
		Reporter.log("3. Test case: Remove Hunt Groups of UCD");
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
			logger.info("--> Test case: Remove Hunt Groups of UCD: PASSED");
			Reporter.log("--> Test case: Remove Hunt Groups of UCD: PASSED");
		} 
		else {
			logger.info("--> Test case: Remove Hunt Groups of UCD: FAILED");
			Reporter.log("--> Test case: Remove Hunt Groups of UCD: FAILED");
			
		}
		return result3;
	}
	
	boolean CreateUCDWithMaxWaitTimesIncorrect(String nameOfGroup, CallAnswerGroupPage callgroup, MenuAfterChoseCustomer menuafter, String lowerLevel, String upperLevel) throws Exception{
		
		String alertFailed = "Please enter a max wait time from 60 to 5400 seconds.";
 		
		Reporter.log("1. Test case: Create UCD group with max queue time " + lowerLevel + " and " + upperLevel);
		callgroup.addCallAnswerGroup_WithInvalidMaxWaitTime("Call Distribution", nameOfGroup, lowerLevel, upperLevel);
		
		logger.info("Verify that alert '" + alertFailed + "' appears after adding a catalog");
		boolean result1a = callgroup.verifyAlertAppears(alertFailed);
		if ( result1a ) {
			logger.info("Alert '" + alertFailed + "' appears after adding a catalog: PASSED");
			Reporter.log("- Alert '" + alertFailed + "' appears after adding a catalog: PASSED");
		}
		else {
			logger.info("Alert '" + alertFailed + "' appears after adding a catalog: FAILED");
			Reporter.log("- Alert '" + alertFailed + "' appears after adding a catalog: FAILED");
			logger.info("Go to Call Answer Group");			
		}		
		menuafter.clickToProvisionThenChooseCallAnswerGroups();
		
		return result1a;
	}
	
}
