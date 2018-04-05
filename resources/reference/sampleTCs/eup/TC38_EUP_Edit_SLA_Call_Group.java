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

public class TC38_EUP_Edit_SLA_Call_Group extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC38");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user_sla","EUP_password", "EUP_Portal_url", "EUP_Portal_user", "EUP_Portal_password", "EUP_domain"})
	public void TC38_EUP_Edit_SLA_Call_Group_(int timeout, String url, String user, String password, String portal_url, String portal_user, String portal_password, String domain) throws Exception {
		
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
		String nameOfGroup = "SLAEUP";
		
		CallAnswerGroupPage callPage = new CallAnswerGroupPage(driver, timeout);
		logger.info("Portal - Add a Call Answer Group with Name '" + nameOfGroup + "'");
		callPage.addCallAnswerGroup_WithGroupMembers("Shared Line Appearance", nameOfGroup, firstNameOfSubscriber_List, firstName);
		
		Logout logoutPage = new Logout(driver, timeout);
		logger.info("Portal - Logout !!!!");
		logoutPage.logout();
		
		Thread.sleep(3000);
		
		//Edit Call Answer Group on EUP Portal
		logger.info("------------------------Start Test case 'Edit SLA call group'------------------------");
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
		
		String alert = "Call answer group updated.";
		
		AddCallGroup addCallPage = new AddCallGroup(driver, timeout);
		
		PageTabCallAnswerGroups callAnswerEUP = new PageTabCallAnswerGroups(driver, timeout);
		
		logger.info("EUP - Click 'SHOW ADVANCED' Button");
		addCallPage.clickButtonShowAdvanced();
		
		logger.info("EUP - Set 'Bridging' as 'Enabled'");
		addCallPage.setBridging("Enabled");
		logger.info("EUP - Set 'Bridging' as 'Disabled'");
		addCallPage.setBridgingWarningTone("Disabled");
		logger.info("EUP - Set 'Private Hold' as 'Disabled'");
		addCallPage.setPrivateHold("Disabled");
		
		logger.info("EUP - Click 'SAVE GROUP' Button");
		addCallPage.clickButtonSaveGroup();
		
		logger.info("EUP - Verify that alert '" + alert + "' appears after modifying SLA Call Answer Group");
		boolean result1c = callAnswerEUP.verifyAlertAppears(alert);
		if ( result1c ) {
			logger.info("Alert '" + alert + "' appears after modifying SLA Call Answer Group: PASSED");
			Reporter.log("- Alert '" + alert + "' appears after modifying SLA Call Answer Group: PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after modifying SLA Call Answer Group: FAILED");
			Reporter.log("- Alert '" + alert + "' appears after modifying SLA Call Answer Group: FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1_Alert");
		}
		
		logger.info("----------------------------------------------------");
		logger.info("EUP - Click 'Configuration' Then Choose 'Call Answer Groups'");
		menuEUP.clickConfigurationThenChooseCallAnswerGroups();
		
		logger.info("EUP - Click Call Answer Groups '" + nameOfGroup + "'");
		callAnsTab.clickCallAnswerGroup(nameOfGroup);
		
		logger.info("EUP - Click 'SHOW ADVANCED' Button");
		addCallPage.clickButtonShowAdvanced();
		
		logger.info("----------------------------------------------------");
		String currentBridging = addCallPage.getBridging();
		logger.info("EUP - 'Bridging' is currently set as '" + currentBridging + "'");
		
		logger.info("EUP - Verify that 'Bridging' is set as 'Enabled' after editing");
		boolean result1e = currentBridging.equals("Enabled");
		if ( result1e ) {
			logger.info("Edit 'Bridging': PASSED");
			Reporter.log("- Edit 'Bridging': PASSED");
		} else {
			logger.info("Edit 'Bridging': FAILED");
			Reporter.log("- Edit 'Bridging': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_2_Bridging");
		}
		
		logger.info("----------------------------------------------------");
		String currentBridgingWarningTone = addCallPage.getBridgingWarningTone();
		logger.info("EUP - 'Bridging Warning Tone' is currently set as '" + currentBridgingWarningTone + "'");
		
		logger.info("EUP - Verify that 'Bridging Warning Tone' is set as 'Disabled' after editing");
		boolean result1f = currentBridgingWarningTone.equals("Disabled");
		if ( result1f ) {
			logger.info("Edit 'Bridging Warning Tone': PASSED");
			Reporter.log("- Edit 'Bridging Warning Tone': PASSED");
		} else {
			logger.info("Edit 'Bridging Warning Tone': FAILED");
			Reporter.log("- Edit 'Bridging Warning Tone': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_3_Bridging_Warning_Tone");
		}
		
		logger.info("----------------------------------------------------");
		String currentPrivateHold = addCallPage.getPrivateHold();
		logger.info("EUP - 'Private Hold' is currently set as '" + currentPrivateHold + "'");
		
		logger.info("EUP - Verify that 'Private Hold' is set as 'Disabled' after editing");
		boolean result1g = currentPrivateHold.equals("Disabled");
		if ( result1g ) {
			logger.info("Edit 'Private Hold': PASSED");
			Reporter.log("- Edit 'Private Hold': PASSED");
		} else {
			logger.info("Edit 'Private Hold': FAILED");
			Reporter.log("- Edit 'Private Hold': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_4_Private_Hold");
		}
		
		logger.info("----------------------------------------------------");
		boolean result = result1c && result1e && result1f && result1g;

		if (result) {
			logger.info("--> Test case 'Edit SLA call group': PASSED");
			Reporter.log("--> Test case 'Edit SLA call group': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit SLA call group': FAILED");
			Reporter.log("--> Test case 'Edit SLA call group': FAILED");
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

