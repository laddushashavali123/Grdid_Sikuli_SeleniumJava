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

public class TC30_EUP_Services_Edit_Call_Return_To_Enable extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC30");
	String subWindowHandler = null;
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC30_EUP_Services_Edit_Call_Return_To_Enable_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		String alert = "Settings updated.";
		String callReturn = "Enabled";
		
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		TabService serviceT = new TabService(driver, timeout);
		
		String currentCallReturn = serviceT.getCallReturn();
		logger.info("'Call Return' is currently set as '" + currentCallReturn + "'");
		
		if ( currentCallReturn.equals("Enabled") ) {
			logger.info("Set 'Call Return' as 'Disabled' before running Test case 'Edit ID call return to enabled'");
			serviceT.selectCallReturn("Disabled");
			logger.info("Click 'SAVE SETTINGS' Button");
			serviceT.clickSaveSettingsButton();
			logger.info("Click 'Address Book' Tab");
			accountP.clickAddressBookTab();
			logger.info("Click 'Services' Tab");
			accountP.clickServiceTab();
		}
		
		logger.info("Set 'Call Return' as '" + callReturn + "'");
		serviceT.selectCallReturn(callReturn);
		
		logger.info("Click 'SAVE SETTINGS' Button");
		serviceT.clickSaveSettingsButton();
		
		logger.info("Verify that alert '" + alert + "' appears after setting 'Call Return' to '" + callReturn + "'");
		boolean result1 = serviceT.verifyAlertAppears(alert);
		if ( result1 ) {
			logger.info("Alert '" + alert + "' appears after setting 'Call Return' to '" + callReturn + "': PASSED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'Call Return' to '" + callReturn + "': PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after setting 'Call Return' to '" + callReturn + "': FAILED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'Call Return' to '" + callReturn + "': FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		String getCallReturn = serviceT.getCallReturn();
		logger.info("'Call Return' is currently set as '" + getCallReturn + "'");
		
		logger.info("Verify that 'Call Return' is set as '" + callReturn + "' after setting 'Call Return' to '" + callReturn + "'");
		boolean result2 = getCallReturn.equals(callReturn);
		if ( result2 ) {
			logger.info("'Call Return' is set as '" + callReturn + "' after setting 'Call Return' to '" + callReturn + "': PASSED");
			Reporter.log("- 'Call Return' is set as '" + callReturn + "' after setting 'Call Return' to '" + callReturn + "': PASSED");
		} else {
			logger.info("'Call Return' is set as '" + callReturn + "' after setting 'Call Return' to '" + callReturn + "': FAILED");
			Reporter.log("- 'Call Return' is set as '" + callReturn + "' after setting 'Call Return' to '" + callReturn + "': FAILED");
		}
		
		boolean result = result1 && result2;
		if (result) {
			logger.info("--> Test case 'Edit ID call return to enable': PASSED");
			Reporter.log("--> Test case 'Edit ID call return to enable': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit ID call return to enable': FAILED");
			Reporter.log("--> Test case 'Edit ID call return to enable': FAILED");
		}
		
		Assert.assertTrue(result);
	}
}
