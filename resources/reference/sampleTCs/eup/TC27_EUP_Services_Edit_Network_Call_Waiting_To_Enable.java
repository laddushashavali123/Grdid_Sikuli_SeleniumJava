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

public class TC27_EUP_Services_Edit_Network_Call_Waiting_To_Enable extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC27");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC27_EUP_Services_Edit_Network_Call_Waiting_To_Enable_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		String alert = "Settings updated.";
		String networkCallWaiting = "Enabled";
		
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		TabService serviceT = new TabService(driver, timeout);
		
		String currentNetworkCallWaiting = serviceT.getNetworkCallWaiting();
		logger.info("'Network Call Waiting' is currently set as '" + currentNetworkCallWaiting + "'");
		
		if ( currentNetworkCallWaiting.equals("Enabled") ) {
			logger.info("Set 'Network Call Waiting' as 'Disabled' before running Test case 'Edit ID network call waiting to enable'");
			serviceT.selectNetworkCallWaiting("Disabled");
			logger.info("Click 'SAVE SETTINGS' Button");
			serviceT.clickSaveSettingsButton();
			logger.info("Click 'Address Book' Tab");
			accountP.clickAddressBookTab();
			logger.info("Click 'Services' Tab");
			accountP.clickServiceTab();
		}
		
		logger.info("Set 'Network Call Waiting' as '" + networkCallWaiting + "'");
		serviceT.selectNetworkCallWaiting(networkCallWaiting);
		
		logger.info("Click 'SAVE SETTINGS' Button");
		serviceT.clickSaveSettingsButton();
		
		logger.info("Verify that alert '" + alert + "' appears after setting 'Network Call Waiting' to '" + networkCallWaiting + "'");
		boolean result1 = serviceT.verifyAlertAppears(alert);
		if ( result1 ) {
			logger.info("Alert '" + alert + "' appears after setting 'Network Call Waiting' to '" + networkCallWaiting + "': PASSED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'Network Call Waiting' to '" + networkCallWaiting + "': PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after setting 'Network Call Waiting' to '" + networkCallWaiting + "': FAILED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'Network Call Waiting' to '" + networkCallWaiting + "': FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		String getNetworkCallWaiting = serviceT.getNetworkCallWaiting();
		logger.info("'Network Call Waiting' is currently set as '" + getNetworkCallWaiting + "'");
		
		logger.info("Verify that 'Network Call Waiting' is set as '" + networkCallWaiting + "' after setting 'Network Call Waiting' to '" + networkCallWaiting + "'");
		boolean result2 = getNetworkCallWaiting.equals(networkCallWaiting);
		if ( result2 ) {
			logger.info("'Network Call Waiting' is set as '" + networkCallWaiting + "' after setting 'Network Call Waiting' to '" + networkCallWaiting + "': PASSED");
			Reporter.log("- 'Network Call Waiting' is set as '" + networkCallWaiting + "' after setting 'Network Call Waiting' to '" + networkCallWaiting + "': PASSED");
		} else {
			logger.info("'Network Call Waiting' is set as '" + networkCallWaiting + "' after setting 'Network Call Waiting' to '" + networkCallWaiting + "': FAILED");
			Reporter.log("- 'Network Call Waiting' is set as '" + networkCallWaiting + "' after setting 'Network Call Waiting' to '" + networkCallWaiting + "': FAILED");
		}
		
		boolean result = result1 && result2;
		if (result) {
			logger.info("--> Test case 'Edit ID network call waiting to enable': PASSED");
			Reporter.log("--> Test case 'Edit ID network call waiting to enable': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit ID network call waiting to enable': FAILED");
			Reporter.log("--> Test case 'Edit ID network call waiting to enable': FAILED");
		}
		
		Assert.assertTrue(result);
	}
}
