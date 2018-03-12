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

public class TC25_EUP_Services_Edit_Auto_Retrieve_Waiting_To_Enable extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC25");
	String subWindowHandler = null;
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC25_EUP_Services_Edit_Auto_Retrieve_Waiting_To_Enable_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		String alert = "Settings updated.";
		String autoretrieve = "Enabled";
		
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("Login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		TabService serviceT = new TabService(driver, timeout);
		
		String currentAutoRetrieve = serviceT.getAutoRetrieve();
		logger.info("'Auto Retrieve' is currently set as '" + currentAutoRetrieve + "'");
		
		if ( currentAutoRetrieve.equals("Enabled") ) {
			logger.info("Set 'Auto Retrieve' as 'Disabled' before running test case 'Edit ID auto retrieve waiting to enabled'");
			serviceT.selectAutoRetrieve("Disabled");
			logger.info("Click 'SAVE SETTINGS' Button");
			serviceT.clickSaveSettingsButton();
			logger.info("Click 'Address Book' Tab");
			accountP.clickAddressBookTab();
			logger.info("Click 'Services' Tab");
			accountP.clickServiceTab();
		}
		
		logger.info("Set 'ID Restriction' as 'Disabled'");
		serviceT.selectIDRestriction("Disabled");
		
		logger.info("Set 'Auto Retrieve' as '" + autoretrieve + "'");
		serviceT.selectAutoRetrieve(autoretrieve);
		
		logger.info("Set 'Auto Retrieve Timer' as '150'");
		serviceT.setAutoRetrieveTimer("150");
		
		logger.info("Click 'SAVE SETTINGS' Button");
		serviceT.clickSaveSettingsButton();
		
		logger.info("Verify that alert '" + alert + "' appears after setting 'Auto Retrieve' to '" + autoretrieve + "'");
		boolean result1 = serviceT.verifyAlertAppears(alert);
		if ( result1 ) {
			logger.info("Alert '" + alert + "' appears after setting 'Auto Retrieve' to '" + autoretrieve + "': PASSED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'Auto Retrieve' to '" + autoretrieve + "': PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after setting 'Auto Retrieve' to '" + autoretrieve + "': FAILED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'Auto Retrieve' to '" + autoretrieve + "': FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		String getAutoRetrieve = serviceT.getAutoRetrieve();
		logger.info("'Auto Retrieve' is currently set as '" + getAutoRetrieve + "'");
		
		logger.info("Verify that 'Auto Retrieve' is set as '" + autoretrieve + "' after setting 'Auto Retrieve' to '" + autoretrieve + "'");
		boolean result2 = getAutoRetrieve.equals(autoretrieve);
		if ( result2 ) {
			logger.info("'Auto Retrieve' is set as '" + autoretrieve + "' after setting 'Auto Retrieve' to '" + autoretrieve + "': PASSED");
			Reporter.log("- 'Auto Retrieve' is set as '" + autoretrieve + "' after setting 'Auto Retrieve' to '" + autoretrieve + "': PASSED");
		} else {
			logger.info("'Auto Retrieve' is set as '" + autoretrieve + "' after setting 'Auto Retrieve' to '" + autoretrieve + "': FAILED");
			Reporter.log("- 'Auto Retrieve' is set as '" + autoretrieve + "' after setting 'Auto Retrieve' to '" + autoretrieve + "': FAILED");
		}
		
		boolean result = result1 && result2;
		if (result) {
			logger.info("--> Test case 'Edit ID auto retrieve waiting to enabled': PASSED");
			Reporter.log("--> Test case 'Edit ID auto retrieve waiting to enabled': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit ID auto retrieve waiting to enabled': FAILED");
			Reporter.log("--> Test case 'Edit ID auto retrieve waiting to enabled': FAILED");
		}
		
		Assert.assertTrue(result);
		
	}
}
