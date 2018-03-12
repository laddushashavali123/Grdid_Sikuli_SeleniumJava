package nuvia.eup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import automation.utils.web.GetScreenshot;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.PageMenuEUP;
import nuvia.eup.pages.PageTabCallAnswerGroups;
import nuvia.pages.AddCallGroup;
import nuvia.pages.CallAnswerGroupPage;
import nuvia.pages.LoginPage;
import nuvia.pages.Logout;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.Subscribers;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC36_EUP_Edit_Hunt_Linear_Call_Group extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC36");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user_linear","EUP_password", "EUP_Portal_url", "EUP_Portal_user", "EUP_Portal_password", "EUP_domain"})
	public void TC36_EUP_Edit_Hunt_Linear_Call_Group_(int timeout, String url, String user, String password, String portal_url, String portal_user, String portal_password, String domain) throws Exception {
		
		WebDriver driver = getDriver();
		driver.get(portal_url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		LoginPage loginPortal = new LoginPage(driver, timeout);
		logger.info("Portal - Login with User ID '" + portal_user + "' and Password '" + portal_password + "'");
		loginPortal.Login(portal_user, portal_password, portal_url);
		
		//Add Call Answer Group
		logger.info("------------------------Add Call Answer Group on Portal before starting this test case------------------------");
		
		MenuAfterChoseCustomer menuAfter = new MenuAfterChoseCustomer(driver, timeout);
		logger.info("Portal - Click 'Provision' Then Choose 'Subscribers'");
		menuAfter.clickToProvisionThenChooseSubscribers();
		
		Subscribers subsriberPage = new Subscribers(driver, timeout);
		logger.info("Portal - Search User ID '" + user + "'");
		subsriberPage.searchUserID(user);
		
		String firstName = subsriberPage.getFirstNameOfSubscriberInTableSubscribers(user);
		logger.info("Portal - First name of User ID '" + user + "' is '" + firstName + "'");
		
		logger.info("Portal - Click 'Provision' Then Choose 'Call Answer Groups'");
		menuAfter.clickToProvisionThenChooseCallAnswerGroups();
		
		List<String> firstNameOfSubscriber_List = Arrays.asList(user);
		String nameOfGroup = "linearEUP";
		
		CallAnswerGroupPage callPage = new CallAnswerGroupPage(driver, timeout);
		logger.info("Portal - Add a Call Answer Group with Name '" + nameOfGroup + "'");
		callPage.addCallAnswerGroup_WithGroupMembers("Linear", nameOfGroup, firstNameOfSubscriber_List, firstName);
		
		Logout logoutPage = new Logout(driver, timeout);
		logger.info("Portal - Logout !!!!");
		logoutPage.logout();
		
		Thread.sleep(3000);
		
		//Edit Call Answer Group on EUP Portal
		logger.info("------------------------Start Test case 'Edit hunt linear call group'------------------------");
		logger.info("Launch EUP Portal '" + url + "'");
		driver.get(url);
		
		PageLoginEUP loginEUP = new PageLoginEUP(driver, timeout);
		user = user + "@" + domain;
		logger.info("EUP - Login with User ID '" + user + "' and Password '" + password + "'");
		loginEUP.login(user, password);
		
		PageMenuEUP menuEUP = new PageMenuEUP(driver, timeout);
		logger.info("EUP - Click 'Configuration' Then Choose 'Call Answer Groups'");
		menuEUP.clickConfigurationThenChooseCallAnswerGroups();
		
		PageTabCallAnswerGroups callAnsTab = new PageTabCallAnswerGroups(driver, timeout);
		logger.info("EUP - Click Call Answer Groups '" + nameOfGroup + "'");
		callAnsTab.clickCallAnswerGroup(nameOfGroup);
		
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
		Date date = new Date();
		String datetext = dateFormat.format(date);
		
		String waitTime = "6";
		String noAnswerAction = "Use Busy Route";
		String agentCallRules = "Enabled";
		String alert = "Call answer group updated.";
		
		
		AddCallGroup addCallPage = new AddCallGroup(driver, timeout);
		logger.info("EUP - Set 'Aliases' as '" + datetext + "'");
		addCallPage.setAliases(datetext);
		logger.info("EUP - Set 'Busy Route' as '" + datetext + "'");
		addCallPage.setBusyRoute(datetext);
		
		logger.info("EUP - Click 'SHOW ADVANCED' Button");
		addCallPage.clickButtonShowAdvanced();
		logger.info("EUP - Set 'Wait Time (sec)' as '" + waitTime + "'");
		addCallPage.setWaitTimeSec(waitTime);
		logger.info("EUP - Set 'No Answer Action' as '" + noAnswerAction + "'");
		addCallPage.setNoAnswerAction(noAnswerAction);
		logger.info("EUP - Set 'Agent Call Rules' as '" + agentCallRules + "'" );
		addCallPage.setAgentCallRules(agentCallRules);
		logger.info("EUP - Click 'SAVE GROUP' Button");
		addCallPage.clickButtonSaveGroup();
		
		PageTabCallAnswerGroups callAnswerEUP = new PageTabCallAnswerGroups(driver, timeout);
		
		logger.info("EUP - Verify that alert '" + alert + "' appears after editing a Call Answer Group");
		boolean result1a = callAnswerEUP.verifyAlertAppears(alert);
		if ( result1a ) {
			logger.info("Alert '" + alert + "' appears after editing a Call Answer Group: PASSED");
			Reporter.log("- Alert '" + alert + "' appears after editing a Call Answer Group: PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after editing a Call Answer Group: FAILED");
			Reporter.log("- Alert '" + alert + "' appears after editing a Call Answer Group: FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1_Vefiry_Alert");
		}
		
		
		logger.info("EUP - Click 'Configuration' Then Choose 'Call Answer Groups'");
		menuEUP.clickConfigurationThenChooseCallAnswerGroups();
		
		logger.info("EUP - Click Call Answer Groups '" + nameOfGroup + "'");
		callAnsTab.clickCallAnswerGroup(nameOfGroup);
		
		logger.info("----------------------------------------------------");
		String currentAliases = addCallPage.getAliases();
		logger.info("EUP - 'Aliases' is currently set as '" + currentAliases + "'");
		
		logger.info("EUP - Verify that 'Aliases' is changed to '" + datetext + "' after editing");
		boolean result1b = currentAliases.equals(datetext);
		if ( result1b ) {
			logger.info("Edit 'Aliases': PASSED");
			Reporter.log("- Edit 'Aliases': PASSED");
		} else {
			logger.info("Edit 'Aliases': FAILED");
			Reporter.log("- Edit 'Aliases': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_2_Edit_Aliases");
		}
		
		logger.info("----------------------------------------------------");
		String currentBusyRoute = addCallPage.getBusyRoute();
		logger.info("EUP - 'Busy Route' is currently set as '" + currentBusyRoute + "'");
		
		logger.info("EUP - Verify that 'Busy Route' is changed to '" + datetext + "' after editing");
		boolean result1c = currentBusyRoute.equals(datetext);
		if ( result1c ) {
			logger.info("Edit 'Busy Route': PASSED");
			Reporter.log("- Edit 'Busy Route': PASSED");
		} else {
			logger.info("Edit 'Busy Route': FAILED");
			Reporter.log("- Edit 'Busy Route': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_3_Busy_Route");
		}
		
		logger.info("EUP - Click 'SHOW ADVANCED' Button");
		addCallPage.clickButtonShowAdvanced();
		
		logger.info("----------------------------------------------------");
		String currentWaitTime = addCallPage.getWaitTimeSec();
		logger.info("EUP - 'Wait Time (sec)' is currently set as '" + currentWaitTime + "'");

		logger.info("EUP - Verify that 'Wait Time (sec)' is changed to '" + waitTime + "' after editing");
		boolean result1d = currentWaitTime.equals(waitTime);
		if ( result1b ) {
			logger.info("Edit 'Wait Time (sec)': PASSED");
			Reporter.log("- Edit 'Wait Time (sec)': PASSED");
		} else {
			logger.info("Edit 'Wait Time (sec)': FAILED");
			Reporter.log("- Edit 'Wait Time (sec)': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_4_Wait_Time");
		}
		
		logger.info("----------------------------------------------------");
		String currentNoAnswerAction = addCallPage.getNoAnswerAction();
		logger.info("EUP - 'No Answer Action' is currently set as '" + currentNoAnswerAction + "'");
		
		logger.info("EUP - Verify that 'No Answer Action' is changed to '" + noAnswerAction + "' after editing");
		boolean result1e = currentNoAnswerAction.equals(noAnswerAction);
		if ( result1e ) {
			logger.info("Edit 'No Answer Action': PASSED");
			Reporter.log("- Edit 'No Answer Action': PASSED");
		} else {
			logger.info("Edit 'No Answer Action': FAILED");
			Reporter.log("- Edit 'No Answer Action': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_5_No_Answer_Action");
		}
		
		logger.info("----------------------------------------------------");
		String currentAgentCallRules = addCallPage.getAgentCallRules();
		logger.info("EUP - 'Agent Call Rules' is currently set as '" + currentAgentCallRules + "'");
		
		logger.info("EUP - Verify that 'Agent Call Rules' is changed to '" + agentCallRules + "' after editing");
		boolean result1f = currentAgentCallRules.equals(agentCallRules);
		if ( result1f ) {
			logger.info("Edit 'Agent Call Rules': PASSED");
			Reporter.log("- Edit 'Agent Call Rules': PASSED");
		} else {
			logger.info("Edit 'Agent Call Rules': FAILED");
			Reporter.log("- Edit 'Agent Call Rules': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "__Agent_Call_Rules");
		}

		logger.info("----------------------------------------------------");
		boolean result = result1a && result1b && result1c && result1d && result1e && result1f;

		if (result) {
			logger.info("--> Test case 'Edit hunt linear call group': PASSED");
			Reporter.log("--> Test case 'Edit hunt linear call group': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit hunt linear call group': FAILED");
			Reporter.log("--> Test case 'Edit hunt linear call group': FAILED");
		}
		
		//Delete Call Answer Group on Portal
		logger.info("------------------------Delete Call Answer Group on Portal before completing this test case------------------------");
		logger.info("Launch Portal '" + portal_url + "'");
		driver.get(portal_url);

		logger.info("Portal - Login with User ID '" + portal_user + "' and Password '" + portal_password + "'");
		loginPortal.Login(portal_user, portal_password, portal_url);
		
		logger.info("Portal - Click 'Provision' Then Choose 'Subscribers'");
		menuAfter.clickToProvisionThenChooseSubscribers();
		
		logger.info("Portal - Click 'Provision' Then Choose 'Call Answer Groups'");
		menuAfter.clickToProvisionThenChooseCallAnswerGroups();
		
		logger.info("Portal - Remove a Call Answer Group with Name '" + nameOfGroup + "'");
		callPage.removeCallAnswerGroup(nameOfGroup);
		
		Assert.assertTrue(result);
		
	}
}

