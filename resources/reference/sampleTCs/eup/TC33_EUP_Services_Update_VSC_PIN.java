package nuvia.eup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class TC33_EUP_Services_Update_VSC_PIN extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC_EUP_Services_Update_VSC_PIN");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC33_EUP_Services_Update_VSC_PIN_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmm");
		Date date = new Date();
		String datetext = dateFormat.format(date);
		
		String alert = "Settings updated.";
		String VSCPIN = datetext;
		
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("Login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		TabService serviceT = new TabService(driver, timeout);
		
		String currentVSCPIN = serviceT.getVSCPIN();
		logger.info("'VSC PIN' is currently set as '" + currentVSCPIN + "'");
		
		logger.info("Set 'VSC PIN' as '" + VSCPIN + "'");
		serviceT.setVSCPIN(VSCPIN);
		
		logger.info("Click 'SAVE SETTINGS' Button");
		serviceT.clickSaveSettingsButton();
		
		logger.info("Verify that alert '" + alert + "' appears after editing 'VSC PIN'");
		boolean result1 = serviceT.verifyAlertAppears(alert);
		if ( result1 ) {
			logger.info("Alert '" + alert + "' appears after setting 'VSC PIN': PASSED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'VSC PIN': PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after setting 'VSC PIN': FAILED");
			Reporter.log("- Alert '" + alert + "' appears after setting 'VSC PIN': FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'Services' Tab");
		accountP.clickServiceTab();
		
		String getVSCPIN = serviceT.getVSCPIN();
		logger.info("'VSC PIN' is currently set as '" + getVSCPIN + "'");
		
		logger.info("Verify that 'VSC PIN' is set as '" + VSCPIN + "' after setting 'VSC PIN'");
		boolean result2 = getVSCPIN.equals(VSCPIN);
		if ( result2 ) {
			logger.info("'VSC PIN' is set as '" + VSCPIN + "' after setting 'VSC PIN': PASSED");
			Reporter.log("- 'VSC PIN' is changed after editing 'VSC PIN': PASSED");
		} else {
			logger.info("'VSC PIN' is set as '" + VSCPIN + "' after setting 'VSC PIN': FAILED");
			Reporter.log("- 'VSC PIN' is changed after editing 'VSC PIN': FAILED");
		}
		
		boolean result = result1 && result2;
		if (result) {
			logger.info("--> Test case 'Update VSC PIN': PASSED");
			Reporter.log("--> Test case 'Update VSC PIN': PASSED");
		} 
		else {
			logger.info("--> Test case 'Update VSC PIN': FAILED");
			Reporter.log("--> Test case 'Update VSC PIN': FAILED");
		}
		
		logger.info("Change 'VSC PIN' back to '" + currentVSCPIN + "'");
		serviceT.setVSCPIN(currentVSCPIN);
		
		logger.info("Click 'SAVE SETTINGS' Button");
		serviceT.clickSaveSettingsButton();
		
		Assert.assertTrue(result);
	}
}
