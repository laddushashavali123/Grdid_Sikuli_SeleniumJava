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

public class TC31_EUP_Services_Edit_ID_Restriction_To_Disable extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC31");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC31_EUP_Services_Edit_ID_Restriction_To_Disable_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		String alert = "Settings updated.";
		String idRestriction = "Disabled";
		
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		TabService serviceT = new TabService(driver, timeout);
		
		String currentIDRestriction = serviceT.getIDRestriction();
		logger.info("'ID Restriction' is currently set as '" + currentIDRestriction + "'");
		
		if ( currentIDRestriction.equals("Disabled") ) {
			logger.info("Set 'ID Restriction' as 'Enabled' before running HelloController case 'Edit ID Restriction to disable'");
			serviceT.selectIDRestriction("Enabled");
			logger.info("Set 'Auto Retrieve' as 'Disabled'");
			serviceT.selectAutoRetrieve("Disabled");
			logger.info("Click 'SAVE SETTINGS' Button");
			serviceT.clickSaveSettingsButton();
			logger.info("Click 'Address Book' Tab");
			accountP.clickAddressBookTab();
			logger.info("Click 'Services' Tab");
			accountP.clickServiceTab();
		}
		
		logger.info("Set 'ID Restriction' as '" + idRestriction + "'");
		serviceT.selectIDRestriction(idRestriction);
		
		logger.info("Click 'SAVE SETTINGS' Button");
		serviceT.clickSaveSettingsButton();
		
		logger.info("Verify that alert '" + alert + "' appears after setting 'ID Restriction' to '" + idRestriction + "'");
		boolean result1 = serviceT.verifyAlertAppears(alert);
		if ( result1 ) {
			logger.info("Alert '" + alert + "' appears after setting 'ID Restriction' to '" + idRestriction + "': PASSED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'ID Restriction' to '" + idRestriction + "': PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after setting 'ID Restriction' to '" + idRestriction + "': FAILED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'ID Restriction' to '" + idRestriction + "': FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		String getIDRestriction = serviceT.getIDRestriction();
		logger.info("'ID Restriction' is currently set as '" + getIDRestriction + "'");
		
		logger.info("Verify that 'ID Restriction' is set as '" + idRestriction + "' after setting 'ID Restriction' to '" + idRestriction + "'");
		boolean result2 = getIDRestriction.equals(idRestriction);
		if ( result2 ) {
			logger.info("'ID Restriction' is set as '" + idRestriction + "' after setting 'ID Restriction' to '" + idRestriction + "': PASSED");
			Reporter.log("- 'ID Restriction' is set as '" + idRestriction + "' after setting 'ID Restriction' to '" + idRestriction + "': PASSED");
		} else {
			logger.info("'ID Restriction' is set as '" + idRestriction + "' after setting 'ID Restriction' to '" + idRestriction + "': FAILED");
			Reporter.log("- 'ID Restriction' is set as '" + idRestriction + "' after setting 'ID Restriction' to '" + idRestriction + "': FAILED");
		}
		
		boolean result = result1 && result2;
		if (result) {
			logger.info("--> Test case 'Edit ID Restriction to disable': PASSED");
			Reporter.log("--> Test case 'Edit ID Restriction to disable': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit ID Restriction to disable': FAILED");
			Reporter.log("--> Test case 'Edit ID Restriction to disable': FAILED");
		}
		
		Assert.assertTrue(result);
	}
}
