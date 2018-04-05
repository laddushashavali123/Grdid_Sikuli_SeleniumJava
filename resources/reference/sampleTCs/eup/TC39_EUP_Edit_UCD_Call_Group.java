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

public class TC39_EUP_Edit_UCD_Call_Group extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC39");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user_ucd","EUP_password", "EUP_Portal_url", "EUP_Portal_user", "EUP_Portal_password", "EUP_domain"})
	public void TC39_EUP_Edit_UCD_Call_Group_(int timeout, String url, String user, String password, String portal_url, String portal_user, String portal_password, String domain) throws Exception {
		
		WebDriver driver = getDriver();
		driver.get(portal_url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		LoginPage loginPortal = new LoginPage(driver, timeout);
		logger.info("Portal - Login with User ID '" + portal_user + "' and Password '" + portal_password + "'");
		loginPortal.Login(portal_user, portal_password, portal_url);
		
		//Add Call Answer Group
		logger.info("------------------------Add Call Answer Group on Portal before starting this HelloController case------------------------");
		
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
		String nameOfGroup = "ucdEUP";
		
		CallAnswerGroupPage callPage = new CallAnswerGroupPage(driver, timeout);
		logger.info("Portal - Add a Call Answer Group with Name '" + nameOfGroup + "'");
		callPage.addCallAnswerGroup_WithGroupMembers("Call Distribution", nameOfGroup, firstNameOfSubscriber_List, firstName);
		
		Logout logoutPage = new Logout(driver, timeout);
		logger.info("Portal - Logout !!!!");
		logoutPage.logout();
		
		Thread.sleep(3000);
		
		//Edit Call Answer Group on EUP Portal
		logger.info("------------------------Start Test case 'Edit UCD call group'------------------------");
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
		
		String callParty = "Agent";
		String presentation = "21";
		String anncDelay = "31";
		String maxWaitTime = "119";
		String maxQueueSize = "101";
		String queueMaxRule = "Route";
		String queueClosureRule = "Route";
		String queueTimeoutRule = "Route";
		
		String alert = "Call answer group updated.";
		
		
		AddCallGroup addCallPage = new AddCallGroup(driver, timeout);
		logger.info("EUP - Set 'Aliases' as '" + datetext + "'");
		addCallPage.setAliases(datetext);
		
		logger.info("EUP - Click 'SHOW ADVANCED' Button");
		addCallPage.clickButtonShowAdvanced();
		
		logger.info("EUP - Set 'Called Party' as '" + callParty + "'");
		addCallPage.setCalledParty(callParty);
		logger.info("EUP - Set 'Presentation' as '" + presentation + "'");
		addCallPage.setPresentation(presentation);
		logger.info("EUP - Set 'Annc Delay' as '" + anncDelay + "'" );
		addCallPage.setAnncDelay(anncDelay);
		logger.info("EUP - Set 'Max Wait Time' as '" + maxWaitTime + "'" );
		addCallPage.setMaxWaitTimeSec(maxWaitTime);
		logger.info("EUP - Set 'Max Queue Size' as '" + maxQueueSize + "'" );
		addCallPage.setMaxQueueSize(maxQueueSize);
		logger.info("EUP - Set 'Queue Max Rule' as '" + queueMaxRule + "'" );
		addCallPage.setQueueMaxRule(queueMaxRule);
		logger.info("EUP - Set 'Queue Closure Rule' as '" + queueClosureRule + "'" );
		addCallPage.setQueueClosureRule(queueClosureRule);
		logger.info("EUP - Set 'Queue Timeout Rule' as '" + queueTimeoutRule + "'" );
		addCallPage.setQueueTimeoutRule(queueTimeoutRule);
		logger.info("EUP - Set 'Call Route Number' as '" + datetext + "'" );
		addCallPage.setCallRouteNumber(datetext);
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
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1_Alert");
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
			GetScreenshot.capture(this.getClass().getSimpleName() + "_2_Aliases");
		}
		
		logger.info("EUP - Click 'SHOW ADVANCED' Button");
		addCallPage.clickButtonShowAdvanced();
		
		logger.info("----------------------------------------------------");		
		String currentCallParty = addCallPage.getCalledParty();
		logger.info("EUP - 'Called Party' is currently set as '" + currentCallParty + "'");

		logger.info("EUP - Verify that 'Called Party' is changed to '" + callParty + "' after editing");
		boolean result1d = currentCallParty.equals(callParty);
		if ( result1b ) {
			logger.info("Edit 'Called Party': PASSED");
			Reporter.log("- Edit 'Called Party': PASSED");
		} else {
			logger.info("Edit 'Called Party': FAILED");
			Reporter.log("- Edit 'Called Party': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_4_Called_Party");
		}
		
		logger.info("----------------------------------------------------");
		String currentPresentation = addCallPage.getPresentation();
		logger.info("EUP - 'Presentation' is currently set as '" + currentPresentation + "'");
		
		logger.info("EUP - Verify that 'Presentation' is changed to '" + presentation + "' after editing");
		boolean result1e = currentPresentation.equals(presentation);
		if ( result1e ) {
			logger.info("Edit 'Presentation': PASSED");
			Reporter.log("- Edit 'Presentation': PASSED");
		} else {
			logger.info("Edit 'Presentation': FAILED");
			Reporter.log("- Edit 'Presentation': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_5_Presentation");
		}
		
		logger.info("----------------------------------------------------");
		String currentAnncDelay = addCallPage.getAnncDelay();
		logger.info("EUP - 'Annc Delay' is currently set as '" + currentAnncDelay + "'");
		
		logger.info("EUP - Verify that 'Annc Delay' is changed to '" + anncDelay + "' after editing");
		boolean result1f = currentAnncDelay.equals(anncDelay);
		if ( result1f ) {
			logger.info("Edit 'Annc Delay': PASSED");
			Reporter.log("- Edit 'Annc Delay': PASSED");
		} else {
			logger.info("Edit 'Annc Delay': FAILED");
			Reporter.log("- Edit 'Annc Delay': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_6_Annc_Delay");
		}

		logger.info("----------------------------------------------------");
		String currentMaxWaitTime = addCallPage.getMaxWaitTime();
		logger.info("EUP - 'Max Wait Time' is currently set as '" + maxWaitTime + "'");
		
		logger.info("EUP - Verify that 'Max Wait Time' is changed to '" + maxWaitTime + "' after editing");
		boolean result1g = currentMaxWaitTime.equals(maxWaitTime);
		if ( result1g ) {
			logger.info("Edit 'Max Wait Time': PASSED");
			Reporter.log("- Edit 'Max Wait Time': PASSED");
		} else {
			logger.info("Edit 'Max Wait Time': FAILED");
			Reporter.log("- Edit 'Max Wait Time': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_7_Max_Wait_Time");
		}

		logger.info("----------------------------------------------------");
		String currentMaxQueueSize = addCallPage.getMaxQueueSize();
		logger.info("EUP - 'Max Queue Size' is currently set as '" + maxQueueSize + "'");
		
		logger.info("EUP - Verify that 'Max Queue Size' is changed to '" + maxQueueSize + "' after editing");
		boolean result1h = currentMaxQueueSize.equals(maxQueueSize);
		if ( result1h ) {
			logger.info("Edit 'Max Queue Size': PASSED");
			Reporter.log("- Edit 'Max Queue Size': PASSED");
		} else {
			logger.info("Edit 'Max Queue Size': FAILED");
			Reporter.log("- Edit 'Max Queue Time': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_8_Max_Queue_Size");
		}
		
		logger.info("----------------------------------------------------");
		String currentQueueMaxRule = addCallPage.getQueueMaxRule();
		logger.info("EUP - 'Queue Max Rule' is currently set as '" + queueMaxRule + "'");
		
		logger.info("EUP - Verify that 'Queue Max Rule' is changed to '" + queueMaxRule + "' after editing");
		boolean result1i = currentQueueMaxRule.equals(queueMaxRule);
		if ( result1i ) {
			logger.info("Edit 'Queue Max Rule': PASSED");
			Reporter.log("- Edit 'Queue Max Rule': PASSED");
		} else {
			logger.info("Edit 'Queue Max Rule': FAILED");
			Reporter.log("- Edit 'Queue Max Rule': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_9_Queue_Max_Rule");
		}
		
		logger.info("----------------------------------------------------");
		String currentQueueClosureRule = addCallPage.getQueueClosureRule();
		logger.info("EUP - 'Queue Closure Rule' is currently set as '" + queueClosureRule + "'");
		
		logger.info("EUP - Verify that 'Queue Closure Rule' is changed to '" + queueClosureRule + "' after editing");
		boolean result1k = currentQueueClosureRule.equals(queueClosureRule);
		if ( result1k ) {
			logger.info("Edit 'Queue Closure Rule': PASSED");
			Reporter.log("- Edit 'Queue Closure Rule': PASSED");
		} else {
			logger.info("Edit 'Queue Closure Rule': FAILED");
			Reporter.log("- Edit 'Queue Closure Rule': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_10_QueueClosureRule");
		}
		
		
		logger.info("----------------------------------------------------");
		String currentQueueTimeoutRule = addCallPage.getQueueTimeoutRule();
		logger.info("EUP - 'Queue Timeout Rule' is currently set as '" + queueTimeoutRule + "'");
		
		logger.info("EUP - Verify that 'Queue Timeout Rule' is changed to '" + queueTimeoutRule + "' after editing");
		boolean result1l = currentQueueTimeoutRule.equals(queueTimeoutRule);
		if ( result1l ) {
			logger.info("Edit 'Queue Timeout Rule': PASSED");
			Reporter.log("- Edit 'Queue Timeout Rule': PASSED");
		} else {
			logger.info("Edit 'Queue Timeout Rule': FAILED");
			Reporter.log("- Edit 'Queue Timeout Rule': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_11_QueueTimeoutRule");
		}
		
		logger.info("----------------------------------------------------");
		String currentCallRouteNumber = addCallPage.getCallRouteNumber();
		logger.info("EUP - 'Call Route Number' is currently set as '" + datetext + "'");
		
		logger.info("EUP - Verify that 'Call Route Number' is changed to '" + datetext + "' after editing");
		boolean result1m = currentCallRouteNumber.equals(datetext);
		if ( result1m ) {
			logger.info("Edit 'Call Route Number': PASSED");
			Reporter.log("- Edit 'Call Route Number': PASSED");
		} else {
			logger.info("Edit 'Call Route Number': FAILED");
			Reporter.log("- Edit 'Call Route Number': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_13_CallRouteNumber");
		}
		
		
		logger.info("----------------------------------------------------");
		boolean result = result1a && result1b && result1d && result1e && result1f && result1g && result1h && result1i && result1k && result1l && result1m;

		if (result) {
			logger.info("--> Test case 'Edit UCD call group': PASSED");
			Reporter.log("--> Test case 'Edit UCD call group': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit UCD call group': FAILED");
			Reporter.log("--> Test case 'Edit UCD call group': FAILED");
		}
		
		//Delete Call Answer Group on Portal
		logger.info("------------------------Delete Call Answer Group on Portal before completing this HelloController case------------------------");
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

