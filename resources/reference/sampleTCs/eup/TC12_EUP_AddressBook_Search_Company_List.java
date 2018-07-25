package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import nuvia.eup.pages.FormSearchCompanyList;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabAddressBook;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC12_EUP_AddressBook_Search_Company_List extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC12");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain"})
	public void TC12_EUP_AddressBook_Search_Company_List_(int timeout, String url, String user, String password, String domain) throws Exception {
		WebDriver driver = getDriver();
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Click 'SEARCH COMPANY LIST' Button");
		addressBookT.clickSearchCompanyListButton();
		
		FormSearchCompanyList searchCom = new FormSearchCompanyList(driver, timeout);
		searchCom.searchCompany("A");
		
		boolean result = searchCom.verifySearchCompanyList();
		Assert.assertTrue(result);
		
	}
}
