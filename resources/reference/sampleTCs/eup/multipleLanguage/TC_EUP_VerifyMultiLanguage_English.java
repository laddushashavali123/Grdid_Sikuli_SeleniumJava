package nuvia.eup.multipleLanguage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.PageMenuEUP;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC_EUP_VerifyMultiLanguage_English extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC39_EUP_Language_English");
	@Test
	@Parameters({"waitTime","EUP_url", "EUP_user", "EUP_password", "EUP_fileLocation"})
	public void TC39_EUP_Edit_UCD_Call_Group_(int timeout, String url, String user, String password, String fileLocation) throws Exception {
		
		WebDriver driver = getDriver(); 
		initializeDriver(driver, url, timeout);
		
		PageLoginEUP eup = new PageLoginEUP(driver, timeout);
		eup.login(user, password);
		//PageMenuEUP menuEUP = new PageMenuEUP(driver, timeout);
		//logger.info("EUP - Click 'Account' Then Click 'Service' Tab");
		//menuEUP.clickAccountThenClickServiceTab();
		
		// subscriber must be assign to a site which have config default client is Skype.
		
		VerifyMultipleLanguage multiple = new VerifyMultipleLanguage(driver, timeout);
		multiple.setEUPLanguage("English");		
		boolean result = verifyEUPLanguage(multiple, fileLocation);
		if(result){
			logger.info("+++++++++++++++++++++Verifying EUP language 'English' is PASSED!!!+++++++++++++++++++++");
			Reporter.log("+++++++++++++++++++++Verifying EUP language 'English' is PASSED!!!+++++++++++++++++++++");
		} else{
			logger.info("+++++++++++++++++++++Verifying EUP language 'English' is FAILED!!!+++++++++++++++++++++");
			Reporter.log("+++++++++++++++++++++Verifying EUP language 'English' is FAILED!!!+++++++++++++++++++++");
		}		
		Assert.assertTrue(result);
	}
	
	boolean verifyEUPLanguage(VerifyMultipleLanguage multiple, String fileLocation) throws Exception{
		boolean result = multiple.verifyHeader();
		result = result & multiple.verifyTabNames();
		result = result & multiple.verifyServiceTabContainByLanguage();
		result = result & multiple.verifyRoutingTab();
		result = result & multiple.verifyABTab(fileLocation + "FileForDemo.txt");
		return result;
	}
}

