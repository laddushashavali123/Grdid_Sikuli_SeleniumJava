package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import nuvia.pages.LoginPage;
import nuvia.pages.Logout;
import nuvia.pages.ManageTasksPage;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.MenuBeforeChoseCustomer;
import nuvia.pages.OrderPage;
import nuvia.pages.SitePage;

public class Configuration03_CompleteManageTasks_OrgUnderRootPartner extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("Configuration03");	
	@Test
	@Parameters({"waitTime", "GB_url","GB_user","GB_password","EUP_customer", "EUP_site_identifier", "EUP_site_name"})
	public void Configuration03_CompleteManageTasks_OrgUnderRootPartner_(int timeout, String url, String user, String password, String customer, String siteName, String siteIdentifier) throws Exception {
		
		WebDriver driver = getDriver();
		driver.get(url);
		
		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}

		LoginPage login = new LoginPage(driver, timeout); 		
		login.Login(user, password, url);	
		
		MenuBeforeChoseCustomer menuBefore = new MenuBeforeChoseCustomer(driver, timeout);
		logger.info("Search customer '" + customer + "' and left click");
 		menuBefore.searchCustomer(customer);
 		
 		logger.info("----------------------------------Manage Tasks-------------------------------------");
		logger.info("Click 'Provision' Menu");
		menuBefore.clickOnProvisionMenu();
		
 		ManageTasksPage managePage = new ManageTasksPage(driver, timeout);

		logger.info("Click 'MANAGE TASKS' Button");
		managePage.clickButtonManageTasks();
 		logger.info("Complete All Tasks");
 		boolean result = managePage.clickAllTaskItems();
 		
 		Logout logoutP = new Logout(driver, timeout);
 		logger.info("Quit Customer Left Click");
 		logoutP.quitCustomerLeftClick();
 		
 		
		
		Assert.assertTrue(result);
 		
	}
}
