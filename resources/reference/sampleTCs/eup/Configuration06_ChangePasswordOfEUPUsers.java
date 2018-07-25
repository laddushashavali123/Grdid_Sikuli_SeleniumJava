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
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.PageLogoutEUP;

public class Configuration06_ChangePasswordOfEUPUsers extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("Configuration06");	
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password", "EUP_domain", "EUP_user_circular", "EUP_user_linear", "EUP_user_simring", "EUP_user_sla", "EUP_user_ucd"})
	public void Configuration06_ChangePasswordOfEUPUsers_(int timeout, String url, String user, String password, String domain, String circular, String linear, String simring, String sla, String ucd) throws Exception {
		WebDriver driver = getDriver();
		
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		String user_circular = circular + "@" + domain;
		String user_linear = linear + "@" + domain;
		String user_simring = simring + "@" + domain;
		String user_sla = sla + "@" + domain;
		String user_ucd = ucd + "@" + domain;
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		
		String old_password = "12345678x@X";
		
		logger.info("login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login_changePW(user, old_password, password);
		
		logger.info("login with User ID '" + user_circular + "' and Password '" + password + "'");
		loginP.login_changePW(user_circular, old_password, password);
		
		logger.info("login with User ID '" + user_linear + "' and Password '" + password + "'");
		loginP.login_changePW(user_linear, old_password, password);
		
		logger.info("login with User ID '" + user_simring + "' and Password '" + password + "'");
		loginP.login_changePW(user_simring, old_password, password);
		
		logger.info("login with User ID '" + user_sla + "' and Password '" + password + "'");
		loginP.login_changePW(user_sla, old_password, password);
		
		logger.info("login with User ID '" + user_ucd + "' and Password '" + password + "'");
		loginP.login_changePW(user_ucd, old_password, password);
 		
	}
}
