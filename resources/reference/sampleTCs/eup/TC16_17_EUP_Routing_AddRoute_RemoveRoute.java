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

public class TC16_17_EUP_Routing_AddRoute_RemoveRoute extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC16_17");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC16_17_EUP_Routing_AddRoute_RemoveRoute_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("Login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		String name = "addroute";
		String alert = "Routing rule saved.";
		String alertRemove = "Routing rule(s) removed.";
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		
		TabRouting routingT = new TabRouting(driver, timeout);
		logger.info("Set Mode of Routing Tab to 'ADVANCED MODE'");
		routingT.setModeOfRoutingTabToAdvancedMode();
		
		logger.info("-----------------------Test case: Add Route-----------------------");
		Reporter.log("1. Test case 'Add Route'");
		
		logger.info("Add Route '" + name + "'");
		routingT.addRoute(name);
		
		logger.info("Verify that alert '" + alert + "' appears after adding a route");
		boolean result1a = routingT.verifyRouteAlertAppears(alert);
		if ( result1a ) {
			logger.info("Alert '" + alert + "' appears after adding a route: PASSED");
			Reporter.log("- Alert '" + alert + "' appears after adding a route: PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after adding a route: FAILED");
			Reporter.log("- Alert '" + alert + "' appears after adding a route: FAILED");
		}
		
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		
		logger.info("Verify that Route '" + name + "' is listed in table 'ROUTES'");
		boolean result1b = routingT.verifyRouteListedInTableRoutes(name);
		if ( result1b ) {
			logger.info("Route '" + name + "' is listed in table 'ROUTES': PASSED");
			Reporter.log("- Route that has been added is listed in table 'ROUTES': PASSED");
		}
		else {
			logger.info("Route '" + name + "' is listed in table 'ROUTES': FAILED");
			Reporter.log("- Route that has been added is listed in table 'ROUTES': FAILED");
		}
		
		boolean result1 = result1a && result1b;
		if (result1) {
			logger.info("--> Test case 'Add Route': PASSED");
			Reporter.log("--> Test case 'Add Route': PASSED");
		} 
		else {
			logger.info("--> Test case 'Add Route': FAILED");
			Reporter.log("--> Test case 'Add Route': FAILED");
		}
		
		logger.info("-----------------------Test case: Remove Route-----------------------");
		Reporter.log("");
		Reporter.log("2. Test case 'Remove Route'");
		
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		logger.info("Set Mode of Routing Tab to 'ADVANCED MODE'");
		routingT.setModeOfRoutingTabToAdvancedMode();
		
		logger.info("Remove Route '" + name + "'");
		routingT.removeRoute(name);
		
		logger.info("Verify that alert '" + alertRemove + "' appears after removing a route");
		boolean result2a = routingT.verifyAlertAppears(alertRemove);
		if ( result2a ) {
			logger.info("Alert '" + alertRemove + "' appears after removing a route: PASSED");
			Reporter.log("- Alert '" + alertRemove + "' appears after removing a route: PASSED");
		}
		else {
			logger.info("Alert '" + alertRemove + "' appears after removing a route: FAILED");
			Reporter.log("- Alert '" + alertRemove + "' appears after removing a route: FAILED");
		}
		
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		logger.info("Set Mode of Routing Tab to 'ADVANCED MODE'");
		routingT.setModeOfRoutingTabToAdvancedMode();
		
		logger.info("Verify that Route '" + name + "' is not listed in table 'ROUTES'");
		boolean result2b = !routingT.verifyRouteListedInTableRoutes(name);
		if ( result2b ) {
			logger.info("Route '" + name + "' is not listed in table 'ROUTES': PASSED");
			Reporter.log("- Route that has been removed is not listed in table 'ROUTES': PASSED");
		}
		else {
			logger.info("Route '" + name + "' is not listed in table 'ROUTES': FAILED");
			Reporter.log("- Route that has been removed is not listed in table 'ROUTES': FAILED");
		}
		
		boolean result2 = result2a && result2b;
		if (result2) {
			logger.info("--> Test case 'Remove Route': PASSED");
			Reporter.log("--> Test case 'Remove Route': PASSED");
		} 
		else {
			logger.info("--> Test case 'Remove Route': FAILED");
			Reporter.log("--> Test case 'Remove Route': FAILED");
		}
		
		boolean result = result1 && result2;
		Assert.assertTrue(result);
		
	}
}
