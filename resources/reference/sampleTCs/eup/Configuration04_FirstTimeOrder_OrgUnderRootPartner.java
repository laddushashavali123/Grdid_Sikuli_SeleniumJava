package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import nuvia.pages.LoginPage;
import nuvia.pages.Logout;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.MenuBeforeChoseCustomer;
import nuvia.pages.OrderPage;
import nuvia.pages.SitePage;
import nuvia.pages.UserAccountsPage;

public class Configuration04_FirstTimeOrder_OrgUnderRootPartner extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("Configuration04");	
	@Test
	@Parameters({"waitTime", "Root_Partner_url","Root_Partner_user","Root_Partner_password","EUP_customer", "EUP_site_identifier", "EUP_site_name", "EUP_Portal_url", "EUP_Portal_user", "EUP_Portal_password"})
	public void Configuration04_FirstTimeOrder_OrgUnderRootPartner_(int timeout, String url, String user, String password, String customer, String siteName, String siteIdentifier, String url_portalEUP, String user_portalEUP, String password_portalEUP) throws Exception {
		
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
 		
 		
 		logger.info("Click 'Dashboard' Then click 'Order' Tab");
 		menuBefore.clickDashboardThenClickOrderTab();
 		
 		OrderPage orderpage = new OrderPage(driver, timeout);
 		
 		//Add increment Order at Organization Level
 		logger.info("Add an Increment Order: SKU = 925-0064-101, Quantity = 10"); //Voice Premium
 		orderpage.addOrder("Increment", "925-0064-101", "10");
 		
 		//Accept Order at GB Level
		logger.info("Get Number of Lastest Order");
		String lastNumber = orderpage.getLastestOrderName();
		logger.info("Number of Lastest Order: '" + lastNumber + "'");
		
		Logout logoutP = new Logout(driver, timeout);
		logger.info("Quit Customer Right Click");
 		logoutP.quitCustomerLeftClick();

 		logger.info("Click 'Dashboard' Then click 'Order' Tab");
 		menuBefore.clickDashboardThenClickOrderTab();
 		
 		logger.info("Accept order '" + lastNumber + "'");
 		orderpage.acceptOrder(lastNumber);
		
 		logger.info("----------------------------------Increment SKU-------------------------------------");
 		
 		logger.info("Search customer '" + customer + "' and left click");
 		menuBefore.searchCustomer(customer);
 		
 		logger.info("Click 'Dashboard' Then click 'Order' Tab");
 		menuBefore.clickDashboardThenClickOrderTab();
 		
 		logger.info("Add an Increment Order: SKU = 925-0084-101, Quantity = 20"); //Basic Call Center Bundle
 		orderpage.addOrder("Increment", "925-0084-101", "20");
 		 		
 		logger.info("----------------------------------Add Site-------------------------------------");
 		MenuAfterChoseCustomer menuAfter = new MenuAfterChoseCustomer(driver, timeout);
 		
		logger.info("Click 'Provision' Then Choose 'Sites'"); 
		menuAfter.clickToProvisionThenChooseSites();
		
		SitePage siteP = new SitePage(driver, timeout);
		
		logger.info("Add Site '" + siteName + "'");
		siteP.addSite(siteName, siteIdentifier);
		
 		
		logger.info("----------------------------------Change Password for User 'admin'-------------------------------------");
		logger.info("Click 'Account' Then click 'Users' Tab");
		menuBefore.clickAccountThenChooseUsers();
		
		UserAccountsPage userPage = new UserAccountsPage(driver, timeout);
		logger.info("Change Password of User '" + user_portalEUP + "' to '" + password_portalEUP + "'");
		userPage.changePassword(user_portalEUP, password_portalEUP);		
		
	}
}
