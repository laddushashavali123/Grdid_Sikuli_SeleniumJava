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

public class TC23_EUP_Routing_Edit_Call_Screening_To_Anonymous_Scope extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC23");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC23_EUP_Routing_Edit_Call_Screening_To_Anonymous_Scope_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		String alert = "Routing rules updated.";
		String scope = "Anonymous";

		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		
		TabRouting routingT = new TabRouting(driver, timeout);
		logger.info("Set Mode of Routing Tab to 'BASIC MODE'");
		routingT.setModeOfRoutingTabToBasicMode();
		
		logger.info("Uncheck all checkboxes in Table 'CALL SCREENING'");
		routingT.uncheckAllCheckboxesInTableCallScreening();
		
		logger.info("Check off '" + scope + "' in Table 'CALL SCREENING'");
		routingT.checkOffScopeInTableCallScreening(scope);
		
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
		
		
		logger.info("Verify that '" + scope + "' Scope in Table 'CALL SCREENING' is selected");
		boolean result2 = routingT.isScopeSelectedInTableCallScreening(scope);
		if ( result2 ) {
			logger.info("'" + scope + "' Scope in Table 'CALL SCREENING' is selected: PASSED");
			Reporter.log("- '" + scope + "' Scope in Table 'CALL SCREENING' is selected: PASSED");
		} else {
			logger.info("'" + scope + "' Scope in Table 'CALL SCREENING' is selected: FAILED");
			Reporter.log("- '" + scope + "' Scope in Table 'CALL SCREENING' is selected: FAILED");
		}
		
		boolean result = result1 && result2;
		if (result) {
			logger.info("--> Test case ''Edit call screening anonymous scope': PASSED");
			Reporter.log("--> Test case ''Edit call screening anonymous scope': PASSED");
		} 
		else {
			logger.info("--> Test case ''Edit call screening anonymous scope': FAILED");
			Reporter.log("--> Test case ''Edit call screening anonymous scope': FAILED");
		}
		
		Assert.assertTrue(result);		
	}
}
