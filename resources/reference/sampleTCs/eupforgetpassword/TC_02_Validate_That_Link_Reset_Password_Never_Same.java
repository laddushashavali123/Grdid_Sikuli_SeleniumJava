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

public class TC_02_Validate_That_Link_Reset_Password_Never_Same extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC_02");

	@Test
	@Parameters({ "EUP_Forget_Password_url","EUP_Forget_Password_user","EUP_Forget_Password_password","waitTime","EUP_Forget_Password_domain" })
	public void TC_01_Validate_That_Forced_Logout_EUP_Portal_After_Change_Password_Successful_ (String url,String userID, String password, int timeout, String domain ) throws Exception {

		WebDriver driver = getDriver();

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}

		/* Step 1. Launch EUP Portal and Request reset password */
		logger.info("------- Step 1. Launch EUP Portal and Request reset password-------");
		Reporter.log("------- Step 1. Launch EUP Portal and Request reset password -------");

		logger.info("Launch EUP Portal '"+url+"'");
		driver.get(url);		

		String userEUP = userID + "@" + domain;
		String email = "hailhl3045@gmail.com";
		String emailPassword ="longhai686995";
		
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

		logger.info("Get link 'Reset' from Mailbox ");
		String URLReset1 = eupPage.getURLFromResetPasswordEmail(email, emailPassword);

		/* Step 3. Re-launch EUP Portal and request reset password  */
		logger.info("-------Step 2. Get link reset in mail box  (url1)-------");
		Reporter.log("-------Step 2. Get link reset in mail box  (url1)-------");

		logger.info("Launch EUP Portal '"+url+"'");
		driver.get(url);

		logger.info("Click Forgot Your Password");
		eupPage.clickForgotPassword();
		Thread.sleep(3000);

		logger.info("Set UserID '"+userEUP+"' and set Email '"+email+"'");
		eupPage.resetPassword(userEUP, email);

		logger.info("Verify 'Request Reset Password' by Alert");

		boolean result3 = eupPage.verifyAlertAppears(alearReset);
		logger.info("----------------------------------------");
		if (result3) {
			logger.info("--> 'Request Reset Password': PASSED");
			Reporter.log("--> 'Request Reset Password': PASSED");
		} 
		else {
			logger.info("--> 'Request Reset Password': FAILED");
			Reporter.log("--> 'Request Reset Password': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1");
		}
		logger.info("----------------------------------------");

		/* Step 4. Get link reset in mail box  (url2) */
		logger.info("-------Step 2. Get link reset in mail box  (url1)-------");
		Reporter.log("-------Step 2. Get link reset in mail box  (url1)-------");

		logger.info("Get link 'Reset' from Mailbox ");
		String URLReset2 = eupPage.getURLFromResetPasswordEmail(email, emailPassword);

		/* Step 5. Compare 2 link reset */
		logger.info("-------Step 2. Get link reset in mail box  (url1)-------");
		Reporter.log("-------Step 2. Get link reset in mail box  (url1)-------");

		logger.info("-----"+URLReset1+"-----");
		logger.info("-----"+URLReset2+"-----");
		boolean result5 = !URLReset1.equals(URLReset2);
		logger.info("----------------------------------------");
		if (result5) {
			logger.info("--> Link reset password never same: PASSED");
			Reporter.log("--> Link reset password never same: PASSED");
		} 
		else {
			logger.info("--> Link reset password never same: FAILED");
			Reporter.log("--> Link reset password never same: FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_5");
		}
		logger.info("----------------------------------------");
		Assert.assertTrue(result1 && result3 && result5);
	}
}