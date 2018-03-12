package nuvia.eup;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import nuvia.pages.LoginPage;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.PstnDidManagementPage;
import nuvia.pages.Subscribers;

public class Configuration05_AddPSTNsandSubscribers extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("Configuration05");	
	@Test
	@Parameters({"waitTime", "EUP_Portal_url","EUP_Portal_user","EUP_Portal_password","EUP_customer", "EUP_site_identifier", "EUP_site_name","EUP_user", "EUP_user_circular", "EUP_user_linear", "EUP_user_simring", "EUP_user_sla", "EUP_user_ucd"})
	public void Configuration05_AddPSTNsandSubscribers_(int timeout, String url, String user, String password, String customer, String siteName, String siteIdentifier, String EUP_user, String EUP_user_circular, String EUP_user_linear, String EUP_user_simring, String EUP_user_sla, String EUP_user_ucd) throws Exception {
		
		WebDriver driver = getDriver();
		driver.get(url);
		
		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}

		LoginPage login = new LoginPage(driver, timeout); 		
		login.Login(user, password, url);	
 		
 		MenuAfterChoseCustomer menuAfter = new MenuAfterChoseCustomer(driver, timeout); 		
 		logger.info("----------------------------------Add PSTN DIDs-------------------------------------");
		
/*		logger.info("Click 'Provision' then choose 'PSTN DIDs'");
		menuAfter.clickToProvisionThenChoosePSTNDIDs();
		
		PstnDidManagementPage pstndid = new PstnDidManagementPage(driver, timeout);
		logger.info("Add PSTN DIDs from: 84201707190 to: 84201707109");
		pstndid.addPSTNDIDs("84201707190", "84201707109");*/
 		
		logger.info("----------------------------------Add Subscriber-------------------------------------");
		logger.info("Click 'Provision' Then Choose 'Subscriber'");
		menuAfter.clickToProvisionThenChooseSubscribers();
		

		String passw = "12345678x@X";
		List<String> productListSKU = Arrays.asList("925-0064-101");		
		String site = siteName + " (" + siteIdentifier + ")";
		
		Subscribers subP = new Subscribers(driver, timeout);
		
/*		logger.info("Add subscriber '" + userID + "'");
		subP.addSubscriber_NoVoIPNumber(firstName, lastName, userID, passw, site, productListSKU);*/
		
		String firstName1 = EUP_user;
		String lastName1 = "EUP Automation";
		String userID1 = EUP_user;
		logger.info("Add subscriber '" + userID1 + "'");
		subP.addSubscriber_NoVoIPNumber(firstName1, lastName1, userID1, passw, site, productListSKU);
		
		String firstName2 = EUP_user_circular;
		String lastName2 = "EUP Automation";
		String userID2 = EUP_user_circular;
		logger.info("Add subscriber '" + userID2 + "'");
		subP.addSubscriber_NoVoIPNumber(firstName2, lastName2, userID2, passw, site, productListSKU);
		
		String firstName3 = EUP_user_linear;
		String lastName3 = "EUP Automation";
		String userID3 = EUP_user_linear;
		logger.info("Add subscriber '" + userID3 + "'");
		subP.addSubscriber_NoVoIPNumber(firstName3, lastName3, userID3, passw, site, productListSKU);
		
		String firstName4 = EUP_user_simring;
		String lastName4 = "EUP Automation";
		String userID4 = EUP_user_simring;
		logger.info("Add subscriber '" + userID4 + "'");
		subP.addSubscriber_NoVoIPNumber(firstName4, lastName4, userID4, passw, site, productListSKU);
		
		String firstName5 = EUP_user_sla;
		String lastName5 = "EUP Automation";
		String userID5 = EUP_user_sla;
		logger.info("Add subscriber '" + userID5 + "'");
		subP.addSubscriber_NoVoIPNumber(firstName5, lastName5, userID5, passw, site, productListSKU);
		
		String firstName6 = EUP_user_ucd;
		String lastName6 = "EUP Automation";
		String userID6 = EUP_user_ucd;
		logger.info("Add subscriber '" + userID6 + "'");
		subP.addSubscriber_NoVoIPNumber(firstName6, lastName6, userID6, passw, site, productListSKU);
		
		
		logger.info("Verify Subscriber '" + userID1 + "' is listed in Table SUBSCRIBERS");
		boolean result2 = subP.verifySubscriberListedInTableSubscribers(userID1);
		
		logger.info("Verify Subscriber '" + userID2 + "' is listed in Table SUBSCRIBERS");
		boolean result3 = subP.verifySubscriberListedInTableSubscribers(userID2);
		
		logger.info("Verify Subscriber '" + userID3 + "' is listed in Table SUBSCRIBERS");
		boolean result4 = subP.verifySubscriberListedInTableSubscribers(userID3);
		
		logger.info("Verify Subscriber '" + userID4 + "' is listed in Table SUBSCRIBERS");
		boolean result5 = subP.verifySubscriberListedInTableSubscribers(userID4);
		
		logger.info("Verify Subscriber '" + userID5 + "' is listed in Table SUBSCRIBERS");
		boolean result6 = subP.verifySubscriberListedInTableSubscribers(userID5);
		
		logger.info("Verify Subscriber '" + userID6 + "' is listed in Table SUBSCRIBERS");
		boolean result7 = subP.verifySubscriberListedInTableSubscribers(userID6);
		
		Assert.assertTrue(result2 && result3 && result4 && result5 && result6 && result7);
 		
	}
}
