package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabRouting;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC19_EUP_Routing_Edit_Call_Redirection_To_Presence_Based_Mode extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC19");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC19_EUP_Routing_Edit_Call_Redirection_To_Presence_Based_Mode_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		String routingNumber = "12345678";
		String alert = "Routing rules updated.";
		String mode = "Presence Based";

		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("Login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		
		TabRouting routingT = new TabRouting(driver, timeout);
		logger.info("Set Mode of Routing Tab to 'BASIC MODE'");
		routingT.setModeOfRoutingTabToBasicMode();
		
		logger.info("Uncheck all checkboxes in Table 'CALL SCREENING'");
		routingT.uncheckAllCheckboxesInTableCallScreening();
		
		logger.info("Select 'Mode' of 'CALL REDIRECTION' as '" + mode + "'");
		routingT.selectModeOfCallRedirection(mode);
		
		logger.info("Select 'Number of Rings' as '3'");
		routingT.selectNumberOfRings("3");
		
		logger.info("Set 'Routing Number': '" + routingNumber + "'");
		routingT.setRoutingNumber(routingNumber);
		
		logger.info("Check off 'Active On the Phone'");
		routingT.checkOffActiveOnThePhoneCheckbox();
		logger.info("Check off 'Unavailable Busy'");
		routingT.checkOffUnavailableBusyCheckbox();
		logger.info("Check off 'Unavailable Busy'");
		routingT.checkOffUnavailableOfflineCheckbox();
		logger.info("Check off 'Unavailable On Vacation'");
		routingT.checkOffUnavailableOnVacationCheckbox();
		
		logger.info("Click 'SAVE ROUTE' Button");
		routingT.clickSaveRouteButton();
		
		logger.info("Verify that alert '" + alert + "' appears after editing a route");
		boolean result1 = routingT.verifyRouteAlertAppears(alert);
		if ( result1 ) {
			logger.info("Alert '" + alert + "' appears after editing a route: PASSED");
			Reporter.log("- Alert '" + alert + "' appears after editing a route: PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after adding a route: FAILED");
			Reporter.log("- Alert '" + alert + "' appears after editing a route: FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		logger.info("Set Mode of Routing Tab to 'BASIC MODE'");
		routingT.setModeOfRoutingTabToBasicMode();
		
		String modeCurrent = routingT.getModeOfCallRedirection();
		logger.info("'Mode' of 'CALL REDIRECTION' is currently set as '" + modeCurrent + "'");
		
		logger.info("Verify that 'Mode' of 'CALL REDIRECTION' is set as '" + mode + "' ");
		boolean result2 = modeCurrent.equals(mode);
		if ( result2 ) {
			logger.info("'Mode' of 'CALL REDIRECTION' is set as '" + mode + "' after updating: PASSED");
			Reporter.log("- 'Mode' of 'CALL REDIRECTION' is set as '" + mode + "' after updating: PASSED");
		} else {
			logger.info("'Mode' of 'CALL REDIRECTION' is set as '" + mode + "' after updating: FAILED");
			Reporter.log("- 'Mode' of 'CALL REDIRECTION' is set as '" + mode + "' after updating: FAILED");
		}
		
		boolean result = result1 && result2;
		if (result) {
			logger.info("--> Test case 'Edit call redirection to presence based mode': PASSED");
			Reporter.log("--> Test case 'Edit call redirection to presence based mode': PASSED");
		} 
		else {
			logger.info("--> Test case 'Edit call redirection to presence based mode': FAILED");
			Reporter.log("--> Test case 'Edit call redirection to presence based mode': FAILED");
		}
		
		Assert.assertTrue(result);
	}
}
