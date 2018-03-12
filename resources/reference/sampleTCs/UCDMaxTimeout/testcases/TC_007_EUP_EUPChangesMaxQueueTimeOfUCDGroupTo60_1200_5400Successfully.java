package nuvia.UCDMaxTimeout.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import nuvia.eup.pages.EUP_LoginPage;
import nuvia.eup.pages.PageMenuEUP;
import nuvia.eup.pages.PageTabCallAnswerGroups;

public class TC_007_EUP_EUPChangesMaxQueueTimeOfUCDGroupTo60_1200_5400Successfully extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_007_EUP_EUPChangesMaxQueueTimeOfUCDGroupTo60_1200_5400Successfully");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password","UCD_customer", "UCD_MaxWaitTime", "EUP_UCD"})
	public void OrgLevel_CreateUCDGroupWithMaxQueueTimeTo5400(int waitTime, String url, String user, String password, String customer, String maxWaitTime, String nameOfGroup) throws Exception {

		/* EUP changes max queue time of UCD group to 60, 1200, 5400 successfully, more than 5400 or less than 60 will failed with error
		 * 
		 * */
		WebDriver driver = getDriver();
		initializeDriver(driver, url, waitTime);
		EUP_LoginPage login = new EUP_LoginPage(driver, waitTime);
 		login.login(user, password);
 		//String nameOfGroup = "au_donotdel";//"AutoUCD_donotdel";
 		
 		String level_org = "5400";
 		String level1 = "60";
 		String level2 = "1200";
 		String level3 = "5400"; 
 		
 		boolean result1 = false;
 		boolean result2 = false;
 		boolean result3 = false;
 		boolean result4 = false;

		
		PageMenuEUP menu = new PageMenuEUP(driver, waitTime);
		logger.info("Select Call Answer Groups");
		menu.clickMenuThenChooseMenuItem("Configuration", "Call Answer Groups");
		
		PageTabCallAnswerGroups callgroup= new PageTabCallAnswerGroups(driver, waitTime);
		
		logger.info("-----------------------Test case: EUP changes max queue time of UCD group to 60, 1200, 5400 successfully, more than 5400 or less than 60 will failed with error -Please enter a max wait time from 60 to 5400 seconds-------------------------");
		
		result1 = updateUCD_ValidMaxWaitTimes(nameOfGroup, level1, level_org, callgroup, menu);
		result2 = updateUCD_ValidMaxWaitTimes(nameOfGroup, level2, level1, callgroup, menu);
		result3 = updateUCD_ValidMaxWaitTimes(nameOfGroup, level3, level2, callgroup, menu);
		result4 = updateUCD_InValidMaxWaitTimes(nameOfGroup, callgroup, menu);
		
		Assert.assertTrue(result4 & result1 & result2 & result3);
	}
	
	boolean updateUCD_ValidMaxWaitTimes(String nameOfGroup, String level, String oldLevel, PageTabCallAnswerGroups callgroup, PageMenuEUP menu) throws Exception{
		String alertEdit = "Call answer group updated.";
		boolean result = false;
		logger.info("-----------------------Test case: Edit Hunt Groups of UCD-----------------------");
		Reporter.log("");
		Reporter.log("2. Test case: Edit Hunt Groups of UCD from " + oldLevel + " to " + level);
		logger.info("Verify the Max Wait Time of Call Answer Group is correct");
		result = callgroup.editCallAnswerGroup_MaxWaitTime(nameOfGroup, level, oldLevel);		
		logger.info("The Max Wait Time of Call Answer Group is set successfully: PASSED");
		Reporter.log("- The Max Wait Time of Call Answer Group is set successfully: PASSED");		
		
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
			menu.clickMenuThenChooseMenuItem("Configuration", "Call Answer Groups");
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
		result = result & result2a && result2b;
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

	
	boolean updateUCD_InValidMaxWaitTimes(String nameOfGroup, PageTabCallAnswerGroups callgroup, PageMenuEUP menu) throws Exception{
		String level4 = "5500";
 		String level5 = "10";
		boolean result = false;
		logger.info("-----------------------Test case: Edit Hunt Groups of UCD to " + level4 + "-----------------------");
		Reporter.log("");
		Reporter.log("2. Test case: Edit Hunt Groups of UCD to " + level4);
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
			menu.clickMenuThenChooseMenuItem("Configuration", "Call Answer Groups");
		}
		
		logger.info("-----------------------Test case: Edit Hunt Groups of UCD to " + level5 + "-----------------------");
		Reporter.log("2. Test case: Edit Hunt Groups of UCD to " + level5);
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
			menu.clickMenuThenChooseMenuItem("Configuration", "Call Answer Groups");
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

}
