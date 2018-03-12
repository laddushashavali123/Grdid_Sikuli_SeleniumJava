package nuvia.eupforgetpassword;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.DriverFactory;
import automation.utils.web.GetScreenshot;
import nuvia.eup.pages.PageLoginEUP;

public class TC_04_Verify_Only_Latest_Link_Reset_Valid extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC_04");

	@Test
	@Parameters({ "EUP_Forget_Password_url","EUP_Forget_Password_user","EUP_Forget_Password_password","waitTime","EUP_Forget_Password_domain" })
	public void TC_04_Verify_Only_Latest_Link_Reset_Valid_ (String url,String userID, String password, int timeout, String domain ) throws Exception {

		WebDriver driver = getDriver();

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}

		/* Step 1. Request reset password */
		logger.info("------- Step 1. Request reset password -------");
		Reporter.log("------- Step 1. Request reset password -------");

		String userEUP = userID + "@" + domain;
		String email = "hailhl3045@gmail.com";
		String emailPassword = "longhai686995";

		logger.info("Launch EUP Portal '" + url + "'");
		driver.get(url);

		PageLoginEUP eupPage = new PageLoginEUP(driver, timeout);
		logger.info("Click Forgot Your Password");
		eupPage.clickForgotPassword();

		logger.info("Set UserID '"+userEUP+"' and set Email '"+email+"'");
		eupPage.resetPassword(userEUP, email);

		String alearReset = "Request submitted. An Email was sent to your Email on file.";
		logger.info("Verify 'Request Reset Password' by Alert");

		boolean result1 = eupPage.verifyAlertAppears(alearReset);
		logger.info("----------------------------------------");
		if (result1) {
			logger.info("--> 'Request Reset Password': PASSED");
			Reporter.log("--> 'Request Reset Password': PASSED");
		} 
		else {
			logger.info("--> 'Request Reset Password': FAILED");
			Reporter.log("--> 'Request Reset Password': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1");
		}
		logger.info("----------------------------------------");

		/* Step 2. Get link reset in mail box  (url1) */
		logger.info("-------Step 2. Get link reset in mail box  (url1)-------");
		Reporter.log("-------Step 2. Get link reset in mail box  (url1)-------");

		logger.info("Get link Reset from Mailbox ");
		String URLReset1 = eupPage.getURLFromResetPasswordEmail(email, emailPassword);

		/* Step 3. Re-launch EUP Portal and request reset password  */
		logger.info("-------Step 3. Re-launch EUP Portal and request reset password-------");
		Reporter.log("-------Step 3. Re-launch EUP Portal and request reset password-------");

		logger.info("Launch EUP Portal '"+url+"'");
		driver.get(url);
		Thread.sleep(3000);

		logger.info("Click Forgot Your Password");
		eupPage.clickForgotPassword();
		Thread.sleep(3000);


		logger.info("Set UserID '"+userEUP+"' and set Email '"+email+"'");
		eupPage.resetPassword(userEUP, email);

		logger.info("Verify Request Reset Password by Alert");

		boolean result3 = eupPage.verifyAlertAppears(alearReset);
		logger.info("----------------------------------------");
		if (result3) {
			logger.info("--> 'Request Reset Password': PASSED");
			Reporter.log("--> 'Request Reset Password': PASSED");
		} 
		else {
			logger.info("--> 'Request Reset Password': FAILED");
			Reporter.log("--> 'Request Reset Password': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_3");
		}
		logger.info("----------------------------------------");

		/* Step 4. Get link reset in mail box  (url2) */
		logger.info("-------Step 4. Get link reset in mail box (url2)-------");
		Reporter.log("-------Step 4. Get link reset in mail box (url2)-------");

		logger.info("Get link Reset from Mailbox ");
		String URLReset2 = eupPage.getURLFromResetPasswordEmail(email, emailPassword);

		/* Step 5. Access older link reset password  */
		logger.info("------- Step 5. Access older link reset password -------");
		Reporter.log("------- Step 5. Access older link reset password -------");

		logger.info("Access Older Link Reset '"+URLReset1+"'");
		driver.get(URLReset1);
		Thread.sleep(3000);

		logger.info("Verify Link");
		boolean result5 = !eupPage.verifyChangeButton();
		logger.info("----------------------------------------");
		if (result5) {
			logger.info("--> 'Link Invalid': PASSED");
			Reporter.log("--> 'Link Invalid': PASSED");
		} 
		else {
			logger.info("--> 'Link Invalid': FAILED");
			Reporter.log("--> 'Link Invalid': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_5");
		}
		logger.info("----------------------------------------");

		/* Step 6. Access latest link reset password  */
		logger.info("------- Step 6. Access latest link reset password -------");
		Reporter.log("------- Step 6. Access latest link reset password -------");

		logger.info("Access Latest Link Reset'"+URLReset2+"'");
		driver.get(URLReset2);
		Thread.sleep(3000);

		logger.info("Verify Link");
		boolean result6 = eupPage.verifyChangeButton();
		logger.info("----------------------------------------");
		if (result6) {
			logger.info("--> 'Link valid': PASSED");
			Reporter.log("--> 'Link valid': PASSED");
		} 
		else {
			logger.info("--> 'Link valid': FAILED");
			Reporter.log("--> 'Link valid': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_6");
		}

		logger.info("----------------------------------------");
		Assert.assertTrue(result1 && result3 && result5 && result6);
	}
}