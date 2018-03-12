package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import nuvia.objects.CustomerObject;
import nuvia.pages.CustomerPage;
import nuvia.pages.DashboardTabPage;
import nuvia.pages.LoginPage;
import nuvia.pages.MenuBeforeChoseCustomer;
import nuvia.pages.OrganizationPage;

public class Configuration02_AddOrgUnderRootPartner extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("Configuration02");	
	@Test
	@Parameters({"Root_Partner_url","Root_Partner_user","Root_Partner_password","EUP_customer", "EUP_catalog", "EUP_domain", "waitTime"})
	public void Configuration02_AddOrgUnderRootPartner_(String url, String user, String password, String customerName, String catalogName, String domain, int timeout) throws Exception {
		
		WebDriver driver = getDriver();
		driver.get(url);
		
		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}

		LoginPage login = new LoginPage(driver, timeout); 		
		login.Login(user, password, url);	
		
		
		MenuBeforeChoseCustomer menubefore = new MenuBeforeChoseCustomer(driver, timeout);
		DashboardTabPage dashPage = new DashboardTabPage(driver, timeout);
		CustomerPage customerPage = new CustomerPage(driver, timeout);
		OrganizationPage orgPage = new OrganizationPage(driver, timeout);
		
		logger.info("Click Menu 'Dashboard'");
		menubefore.clickDashboard();
		logger.info("Click on Customer tab");
		dashPage.clickOnCustomer();
		
		logger.info("Create Organization '" + customerName + "'" );
		CustomerObject cusO = new CustomerObject("Organization", domain, customerName, catalogName);
		cusO.Language = "English";
		cusO.isGBLevel = true;
		cusO.maxOrder = "10000";
		cusO.DNSBranding ="Disabled";
		cusO.PSTNDIDs = "Enabled";
		customerPage.createCustomer(cusO);
		
		logger.info("Click 'ACTIVE CUSTOMER' Button");
		orgPage.clickOnActiveCustomerButton();
		
		logger.info("Click 'RETURN TO LIST' Button");
		orgPage.clickButtonReturnToList();
		
		String state = "Active";
		logger.info("Verify that '" + customerName + "' Organization is listed in table 'CUSTOMER REPORT' and its State is " + state);
		boolean result = customerPage.verifyCustomerState(customerName, state);
		
		Assert.assertTrue(result);
	}
}
