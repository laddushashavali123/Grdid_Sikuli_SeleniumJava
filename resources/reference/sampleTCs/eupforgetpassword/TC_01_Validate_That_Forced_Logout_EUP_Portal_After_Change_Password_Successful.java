package nuvia.eupforgetpassword;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.DriverFactory;
import automation.utils.web.GetScreenshot;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.PageMenuEUP;

public class TC_01_Validate_That_Forced_Logout_EUP_Portal_After_Change_Password_Successful extends DriverFactory {

	public static final Logger logger = LogManager.getLogger("TC_01");
	@Test
	@Parameters({ "EUP_Forget_Password_url","EUP_Forget_Password_user","EUP_Forget_Password_password","waitTime","EUP_Forget_Password_domain" })
	public void TC_01_Validate_That_Forced_Logout_EUP_Portal_After_Change_Password_Successful_ (String url,String userID, String password, int timeout, String domain ) throws Exception {

		WebDriver driver = new ChromeDriver();

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		/* Step 1. login EUP on browser 1 */
		logger.info("----- Step 1. login EUP on browser 1 ----- ");
		Reporter.log("----- Step 1. login EUP on browser 1 -----");

		logger.info("Launch EUP Portal '"+url+"'");
		driver.get(url);

		String userEUP = userID+"@"+domain;
		String email = "hailhl3045@gmail.com";
		String emailPassword ="longhai686995";

		PageLoginEUP eupPage = new PageLoginEUP(driver, timeout);
		logger.info("'login EUP' '"+userEUP+"'");
		try {
			eupPage.login(userEUP, password);
		}catch (Exception e)
		{
			eupPage.login(userEUP, "4321-Kandy");	
		}
		PageMenuEUP pageMenu = new PageMenuEUP(driver, timeout);
		try {
			pageMenu.clickAccountThenClickServiceTab();
			logger.info("----------------------------------------");
			logger.info("login EUP on browser 1 Successful : PASSED");
			Reporter.log("---login EUP on browser 1 Successful : PASSED");
			logger.info("----------------------------------------");
		} catch (Exception e1) {
			logger.info("----------------------------------------");
			logger.info("login EUP on browser 1 Successful : FAILED");
			Reporter.log("---login EUP on browser 1 Successful : FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1");
			logger.info("----------------------------------------");
		}

		/* Step 2.  Launch EUP Portal by browser 2 then request reset password */
		logger.info("----- Step 2.  Launch EUP Portal by browser 2 then request reset password ----- ");
		Reporter.log("----- Step 2.  Launch EUP Portal by browser 2 then request reset password -----");

		WebDriver forgetDriver = new ChromeDriver();

		logger.info("Launch EUP Portal '"+url+"'");
		forgetDriver.get(url);

		if (getProtocol().equals("selenium")) {
			forgetDriver.manage().window().maximize();
		}

		PageLoginEUP forgetPage = new PageLoginEUP(forgetDriver, timeout);
		logger.info("Click Forgot Your Password");
		forgetPage.clickForgotPassword();

		logger.info("Set UserID '"+userEUP+"' and set Email '"+email+"'");
		forgetPage.resetPassword(userEUP, email);

		String alearReset = "Request submitted. An Email was sent to your Email on file.";
		logger.info("Verify 'Request Reset Password' by Alert");

		boolean result2 = forgetPage.verifyAlertAppears(alearReset);
		logger.info("----------------------------------------");
		if (result2) {
			logger.info("--> 'Request Reset Password': PASSED");
			Reporter.log("--> 'Request Reset Password': PASSED");
		} 
		else {
			logger.info("--> 'Request Reset Password': FAILED");
			Reporter.log("--> 'Request Reset Password': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_2");
		}
		logger.info("----------------------------------------");

		logger.info("Close browser request reset password");
		forgetDriver.close();

		/* Step 3. Click link reset in mail box and change password */
		logger.info("----- Step 3. Click link reset in mail box and change password ----- ");
		Reporter.log("----- Step 3. Click link reset in mail box and change password -----");

		logger.info("Get link 'Reset' from Mailbox");
		String URLReset = forgetPage.getURLFromResetPasswordEmail(email, emailPassword);

		boolean result3a = !URLReset.equals("");
		logger.info("----------------------------------------");
		if (result3a) {
			logger.info("--> 'Verify reset link is sent to email box': PASSED");
			Reporter.log("--> 'Verify reset link is sent to email box': PASSED");
		}
		else {
			logger.info("--> 'Verify reset link is sent to email box': FAILED");
			Reporter.log("--> 'Verify reset link is sent to email box': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_3a");
		}
		logger.info("----------------------------------------");

		WebDriver resetDriver = new ChromeDriver();
		Thread.sleep(3000);

		logger.info("Access link Reset");
		resetDriver.get(URLReset);

		String newPassword = "4321-Kandy";
		PageLoginEUP forgetresetPage = new PageLoginEUP(resetDriver, timeout);
		logger.info("Change new password '"+newPassword+"'");
		try {
			forgetresetPage.changePasswordReset(newPassword);
		} catch (Exception e2) {
			logger.info("Reload");
			resetDriver.get(URLReset);
			forgetresetPage.changePasswordReset(newPassword);
		}

		String alertChangePassword = "Password changed.";
		boolean result3b = forgetresetPage.verifyAlertAppears(alertChangePassword);
		logger.info("----------------------------------------");
		if (result3b) {
			logger.info("--> 'Verify change new password failed': PASSED");
			Reporter.log("--> 'Verify change new password failed': PASSED");
		}
		else {
			logger.info("--> 'Verify change new password failed': FAILED");
			Reporter.log("--> 'Verify change new password failed': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_3b");
		}
		logger.info("----------------------------------------");
		logger.info("Close browser change password");
		resetDriver.close();
		boolean result3 = result3a && result3b;

		/* Step 4. Verify logout EUP Portal at browser 1 */
		logger.info("----- Step 4. Verify logout EUP Portal at browser 1 ----- ");
		Reporter.log("----- Step 4. Verify logout EUP Portal at browser 1 -----");	

		boolean result4 = false;
		try {
			pageMenu.clickAccountThenClickServiceTab();
			logger.info("----------------------------------------");
			logger.info("User forced logout after change password : FAILED");
			Reporter.log("User forced logout after change password : FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_4");
			result4 = false;
			logger.info("----------------------------------------");
		} catch (Exception e3) {
			logger.info("----------------------------------------");
			logger.info("User forced logout after change password : PASSED");
			Reporter.log("---User forced logout after change password : PASSED");
			result4 = true;
			logger.info("----------------------------------------");
		}
		driver.close();
		Assert.assertTrue(result2 && result3 && result4);
	}
}