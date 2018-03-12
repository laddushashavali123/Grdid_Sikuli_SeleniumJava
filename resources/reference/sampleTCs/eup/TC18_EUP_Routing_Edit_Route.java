package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import nuvia.eup.pages.FormAddRoute;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabRouting;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC18_EUP_Routing_Edit_Route extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC18");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC18_EUP_Routing_Edit_Route_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("Login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		String name = "editroute";
		String alert = "Routing rule updated.";
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		
		TabRouting routingT = new TabRouting(driver, timeout);
		logger.info("Set Mode of Routing Tab to 'ADVANCED MODE'");
		routingT.setModeOfRoutingTabToAdvancedMode();
		
		logger.info("Add Route '" + name + "'");
		routingT.addRoute(name);
		
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		logger.info("Set Mode of Routing Tab to 'ADVANCED MODE'");
		routingT.setModeOfRoutingTabToAdvancedMode();
		
		logger.info("Click Route '" + name + "' in table 'ROUTES'");
		routingT.clickRouteInTableRoutes(name);
		
		FormAddRoute addR = new FormAddRoute(driver, timeout);
		
		logger.info("Click 'From THESE NUMBERS or..' in Table 'WHEN A CALL IS RECEIVED'");
		addR.clickFromTheseNumbersOrInTableWhenACallIsReceived();
		logger.info("Select 'Mobile Phone'");
		addR.checkOffNumberInTableNumbers("Mobile Phone");
		logger.info("Click 'SAVE ROUTE' Button");
		addR.clickSaveRouteButton();
		
		logger.info("Verify that alert '" + alert + "' appears after editing a route");
		boolean result = routingT.verifyRouteAlertAppears(alert);
		if ( result ) {
			logger.info("Alert '" + alert + "' appears after editing a route: PASSED");
		}
		else {
			logger.info("Alert '" + alert + "' appears after adding a route: FAILED");
		}
		
		logger.info("Click 'Routing' Tab");
		accountP.clickRoutingTab();
		logger.info("Set Mode of Routing Tab to 'ADVANCED MODE'");
		routingT.setModeOfRoutingTabToAdvancedMode();
		
		logger.info("Remove Route '" + name + "'");
		routingT.removeRoute(name);
		
		Assert.assertTrue(result);
		
	}
}
