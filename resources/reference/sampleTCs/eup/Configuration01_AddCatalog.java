package nuvia.eup;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.DriverFactory;
import nuvia.pages.CatalogPage;
import nuvia.pages.LoginPage;
import nuvia.pages.MenuBeforeChoseCustomer;

public class Configuration01_AddCatalog extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("Configuration01");

	@Test
	@Parameters({"Root_Partner_url","Root_Partner_user","Root_Partner_password","EUP_catalog", "waitTime"})
	public void Configuration01_AddCatalogConfigureForPartnerUnderGB_(String url, String user, String password, String catalogName, int timeout) throws Exception {

		WebDriver driver = getDriver();
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}

		LoginPage login = new LoginPage(driver, timeout); 		
		login.Login(user, password, url);	

		List<String> productNames = Arrays.asList("925-0064-101", "925-0084-101");

		MenuBeforeChoseCustomer menubefore = new MenuBeforeChoseCustomer(driver, timeout);
		CatalogPage catalog = new CatalogPage(driver, timeout);

		logger.info("Click 'Product' then Choose 'Catalogs'");
		menubefore.clickProductThenChooseCatalogs();

		logger.info("Create catalog: '" + catalogName + "'");
		catalog.addCatalog(catalogName, productNames);

		boolean result = catalog.verifyCatalogListedInTableCatalogs(catalogName);
		if ( result ) {
			logger.info("Catalog '" + catalogName + "' is listed in table 'CATALOGS': PASSED");
			Reporter.log("- Catalog that has been added is listed in table 'CATALOGS': PASSED");
		}
		else {
			logger.info("Catalog '" + catalogName + "' is listed in table 'CATALOGS': FAILED");
			Reporter.log("- Catalog that has been added is listed in table 'CATALOGS': FAILED");
		}

		Assert.assertTrue(result);

	}
}
