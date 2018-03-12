package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabService;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC26_EUP_Services_Edit_Auto_Retrieve_Timer extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC26");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC26_EUP_Services_Edit_Auto_Retrieve_Timer_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		String alert = "Settings updated.";
		String alertF = "Please enter vaild Auto Retrieve Timer [120-350]";
		String timer1 = "150";
		String timer2 = "40";
		String timer3 = "360";
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("Login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		TabService serviceT = new TabService(driver, timeout);
		
		logger.info("Set 'ID Restriction' as 'Disabled'");
		serviceT.selectIDRestriction("Disabled");
		
		logger.info("Set 'Auto Retrieve' as 'Enabled'");
		serviceT.selectAutoRetrieve("Enabled");
		
		logger.info("Set 'Auto Retrieve Timer' as '" + timer1 + "'");
		serviceT.setAutoRetrieveTimer(timer1);
		
		logger.info("Click 'SAVE SETTINGS' Button");
		serviceT.clickSaveSettingsButton();
		
		logger.info("Verify that alert '" + alert + "' appears after editing 'Auto Retrieve Timer'");
		boolean result1 = serviceT.verifyAlertAppears(alert);
		if ( result1 ) {
			logger.info("Alert '" + alert + "' appears after editing 'Auto Retrieve': PASSED");
			Reporter.log("- Alert '" + alert + "' appears after editing 'Auto Retrieve': PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after editing 'Auto Retrieve': FAILED");
			Reporter.log("- Alert '" + alert + "' appears after editing 'Auto Retrieve': FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		String getAutoRetrieveTimer = serviceT.getAutoRetrieveTimer();
		logger.info("'Auto Retrieve Timer' is currently set as '" + getAutoRetrieveTimer + "'");
		
		logger.info("Verify that 'Auto Retrieve Timer' is set as '" + timer1 + "' after setting 'Auto Retrieve' to '" + timer1 + "'");
		boolean result2 = getAutoRetrieveTimer.equals(timer1);
		if ( result2 ) {
			logger.info("'Auto Retrieve' is set as '" + timer1 + "' after editing 'Auto Retrieve Timer' to '" + timer1 + "': PASSED");
			Reporter.log("- 'Auto Retrieve' is set as '" + timer1 + "' after editing 'Auto Retrieve Timer' to '" + timer1 + "': PASSED");
		} else {
			logger.info("'Auto Retrieve' is set as '" + timer1 + "' after editing 'Auto Retrieve Timer' to '" + timer1 + "': FAILED");
			Reporter.log("- 'Auto Retrieve' is set as '" + timer1 + "' after editing 'Auto Retrieve Timer' to '" + timer1 + "': FAILED");
		}
		
		serviceT.clickSaveSettingsButton();
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		logger.info("Set 'Auto Retrieve Timer' as '" + timer2 + "'");
		serviceT.setAutoRetrieveTimer(timer2);
		
		logger.info("Click 'SAVE SETTINGS' Button");
		serviceT.clickSaveSettingsButton();
		
		logger.info("Verify that alert '" + alertF + "' appears after editing 'Auto Retrieve Timer' < 120");
		boolean result3 = serviceT.verifyAlertAppears(alertF);
		if ( result3 ) {
			logger.info("Alert '" + alertF + "' appears after editing 'Auto Retrieve' < 120 : PASSED");
			Reporter.log("- Alert '" + alertF + "' appears after editing 'Auto Retrieve' < 120 : PASSED");
		}
		else {
			logger.info("Alert '" + alertF + "' appears after editing 'Auto Retrieve' < 120 : FAILED");
			Reporter.log("- Alert '" + alertF + "' appears after editing 'Auto Retrieve' < 120 : FAILED");
		}
		
		serviceT.clickSaveSettingsButton();
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		logger.info("Set 'Auto Retrieve Timer' as '" + timer3 + "'");
		serviceT.setAutoRetrieveTimer(timer3);
		
		logger.info("Click 'SAVE SETTINGS' Button");
		serviceT.clickSaveSettingsButton();
		
		logger.info("Verify that alert '" + alertF + "' appears after editing 'Auto Retrieve Timer' > 350");
		boolean result4 = serviceT.verifyAlertAppears(alertF);
		if ( result4 ) {
			logger.info("Alert '" + alertF + "' appears after editing 'Auto Retrieve' > 350 : PASSED");
			Reporter.log("- Alert '" + alertF + "' appears after editing 'Auto Retrieve' > 350 : PASSED");
		}
		else {
			logger.info("Alert '" + alertF + "' appears after editing 'Auto Retrieve' > 350 : FAILED");
			Reporter.log("- Alert '" + alertF + "' appears after editing 'Auto Retrieve' > 350 : FAILED");
		}
		
		
		boolean result = result1 && result2 && result3 && result4;
		if (result) {
			logger.info("--> Test case 'Edit auto retrieve timer': PASSED");
			Reporter.log("--> Test case 'Edit auto retrieve timer': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit auto retrieve timer': FAILED");
			Reporter.log("--> Test case 'Edit auto retrieve timer': FAILED");
		}
		
		Assert.assertTrue(result);
	}
}
