package nuvia.eupforgetpassword;

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
import automation.utils.web.GetScreenshot;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.PageMenuEUP;
import nuvia.objects.SubscriberObject;
import nuvia.pages.LoginPage;
import nuvia.pages.Logout;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.Subscribers;

public class TC_05_Verify_Able_To_Reset_Password_Successfully_When_Subscriber_Request_Reset_Password extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC_05");

	@Test
	@Parameters({ "EUP_Forget_Password_Portal_url","EUP_Forget_Password_Portal_user","EUP_Forget_Password_Portal_password","waitTime","EUP_Forget_Password_domain","EUP_Forget_Password_url" })
	public void TC_04_Verify_Only_Latest_Link_Reset_Valid_ (String url,String user, String password, int timeout, String domain,String urlEUP ) throws Exception {

		WebDriver driver = getDriver();

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}

		/* Step 1. Create subscriber without email */
		logger.info("------- Step 1. Create subscriber without email -------");
		Reporter.log("------- Step 1. Create subscriber without email -------");

		logger.info("Launch Admin Portal '"+url+"'");
		driver.get(url);

		logger.info("login Admin Portal '"+user+"'");
		LoginPage login = new LoginPage(driver, timeout); 		
		login.Login(user, password, url);

		logger.info("Click Provisioning then choose Subscriber");
		MenuAfterChoseCustomer menuAfter = new MenuAfterChoseCustomer(driver, timeout);
		menuAfter.clickToProvisionThenChooseSubscribers();

		String firstName = "firstName";
		String lastName = "forget";
		String userID = "eupforget";
		String passwd = "Kandy-1234";
		List<String> sku_list = Arrays.asList("925-0065-101");
		String site = "forgetpwd (forgetpwd)";
		String alertAdd = "Subscriber added.";

		SubscriberObject subObj = new SubscriberObject(firstName, lastName, userID, passwd, sku_list, site);

		Subscribers subPage = new Subscribers(driver, timeout);
		logger.info("Add 'Subscriber' with 'UserID' '"+userID+"'" );
		subPage.addSubscriber(subObj);

		logger.info("Verify that alert '" + alertAdd + "' appears after adding a subscriber");
		boolean result1a = subPage.verifyAlertAppears(alertAdd);

		logger.info("----------------------------------------");
		if ( result1a ) {
			logger.info("Alert '" + alertAdd + "' appears after adding a subscriber: PASSED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding a subscriber: PASSED");
		}
		else {
			logger.info("Alert '" + alertAdd + "' appears after adding a subscriber: FAILED");
			Reporter.log("- Alert '" + alertAdd + "' appears after adding a subscriber: FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1a");
		}
		logger.info("----------------------------------------");

		logger.info("Verify that 'Subscriber' '" + userID + "' is listed in table 'SUBSCRIBERS'");
		boolean result1b = subPage.verifySubscriberListedInTableSubscribers(userID);

		logger.info("----------------------------------------");
		if ( result1b ) {
			logger.info("Subscriber '" + userID + "' is listed in table 'SUBSCRIBERS': PASSED");
			Reporter.log("- Subscriber that has been added is listed in table 'SUBSCRIBERS': PASSED");
		}
		else {
			logger.info("Subscriber '" + userID + "' is listed in table 'SUBSCRIBERS': FAILED");
			Reporter.log("- Subscriber that has been added is listed in table 'SUBSCRIBERS': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1b");
		}
		logger.info("----------------------------------------");

		logger.info("----------------------------------------");
		boolean result1 = result1a && result1b;
		if (result1) {
			logger.info("--> 'Create new subscriber without email': PASSED");
			Reporter.log("--> 'Create new subscriber without email': PASSED");
		} 
		else {
			logger.info("--> 'Create new subscriber without email': FAILED");
			Reporter.log("--> 'Create new subscriber without email': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1c");
		}
		logger.info("----------------------------------------");

		/* Step 2. login EUP with correct password, input email and log out */
		logger.info("------- Step 2. login EUP with correct password, input email and log out -------");
		Reporter.log("------- Step 2. login EUP with correct password, input email and log out -------");

		String userEUP = userID + "@" + domain;	
		String email = "hailhl3045@gmail.com";
		String email_password = "longhai686995";

		logger.info("Logout Portal Admin");
		Logout logoutPage = new Logout(driver, timeout);
		logoutPage.logout();

		logger.info("Launch EUP Portal '" + urlEUP + "'");
		driver.get(urlEUP);
		Thread.sleep(5000);
		
		PageLoginEUP eupPage = new PageLoginEUP(driver, timeout);
		logger.info("Set User ID '"+userEUP+"'");
		eupPage.setUserID(userEUP);
		logger.info("Set Password '"+passwd+"'");
		eupPage.setPassword(passwd);
		logger.info("Click Sign In Button");
		eupPage.clickSignInButton();

		logger.info("Set Email '"+email+"' and click Submit");
		
		eupPage.setEmailAndSubmit(email);

		PageMenuEUP pageMenu = new PageMenuEUP(driver, timeout);
		boolean result2 = true;
		try {
			pageMenu.clickAccountThenClickServiceTab();
			result2 = true;
			logger.info("--------------------------------");
			logger.info("login EUP Successful : PASSED");
			Reporter.log("--> login EUP Successful : PASSED");
			logger.info("----------------------------------------");
		} catch (Exception e) { 
			result2 = false;
			logger.info("login EUP Successful : FAILED");
			Reporter.log("--> login EUP Successful : FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_2");
			logger.info("----------------------------------------");
		}

		logger.info("Logout EUP Portal");
		logoutPage.logoutEUP();
		Thread.sleep(2000);

		/* Step 3.  login with incorrect password and request reset password */
		logger.info("------- Step 3.  login with incorrect password and request reset password -------");
		Reporter.log("------- Step 3.  login with incorrect password and request reset password -------");

		String passwordWrong= "0987-Kandy";
		logger.info("login EUP wrong password '"+passwordWrong+"'");
		eupPage.login(userEUP, passwordWrong);

		String loginIncorrectAlert = "That combination of credentials is not valid. Please try again.";
		logger.info("Verify 'login Incorrect Password' by Alert");

		boolean result3a = eupPage.verifyAlertAppears(loginIncorrectAlert);
		logger.info("----------------------------------------");
		if (result3a) {
			logger.info("--> 'login EUP Wrong Password': PASSED");
			Reporter.log("--> 'login EUP Wrong Password': PASSED");
		} 
		else {
			logger.info("--> 'login EUP Wrong Password': FAILED");
			Reporter.log("--> 'login EUP Wrong Password': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_3a");
		}
		logger.info("----------------------------------------");
		
		driver.close();
		driver.get(urlEUP);
		logger.info("Click Forgot Your Password Button");
		eupPage.clickForgotPassword();
		logger.info("Set 'UserID' '"+userID+"' and set 'Email' '"+email+"'");
		eupPage.resetPassword(userEUP, email);

		String alearReset = "Request submitted. An Email was sent to your Email on file.";
		logger.info("Verify 'Request Reset Password' by Alert");

		boolean result3b = eupPage.verifyAlertAppears(alearReset);
		logger.info("----------------------------------------");
		if (result3b) {
			logger.info("--> 'Request Reset Password': PASSED");
			Reporter.log("--> 'Request Reset Password': PASSED");
		} 
		else {
			logger.info("--> 'Request Reset Password': FAILED");
			Reporter.log("--> 'Request Reset Password': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_3b");
		}
		logger.info("----------------------------------------");

		/* Step 4. Get link reset in mailbox and reset password */
		logger.info("------- Step 4. Get link reset in mailbox and reset password -------");
		Reporter.log("------- Step 4. Get link reset in mailbox and reset password -------");

		logger.info("Get link 'Reset' from Mailbox");
		String URLReset = eupPage.getURLFromResetPasswordEmail(email, email_password);

		boolean result4a = !URLReset.equals("");
		logger.info("----------------------------------------");
		if (result4a) {
			logger.info("--> 'Verify reset link is sent to email box': PASSED");
			Reporter.log("--> 'Verify reset link is sent to email box': PASSED");
		}
		else {
			logger.info("--> 'Verify reset link is sent to email box': FAILED");
			Reporter.log("--> 'Verify reset link is sent to email box': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_4a");
		}
		logger.info("----------------------------------------");

		logger.info("Access link Reset");
		driver.get(URLReset);
		Thread.sleep(5000);

		String newPassword = "4321-Kandy";
		logger.info("Change new password '"+newPassword+"'");
		try {
			eupPage.changePasswordReset(newPassword);
		} catch (Exception e1) {
			logger.info("Reload");
			driver.get(URLReset);
			eupPage.changePasswordReset(newPassword);
		}

		String alertChangePassword = "Password changed.";
		boolean result4b = eupPage.verifyAlertAppears(alertChangePassword);
		logger.info("----------------------------------------");
		if (result4b) {
			logger.info("--> 'Verify change new password': PASSED");
			Reporter.log("--> 'Verify change new password': PASSED");
		}
		else {
			logger.info("--> 'Verify change new password': FAILED");
			Reporter.log("--> 'Verify change new password': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_4b");
		}
		boolean result4 = result4a && result4b;
		logger.info("----------------------------------------");
		if (result4) {
			logger.info("--> 'Step 4 - Get link reset in mailbox and reset password': PASSED");
			Reporter.log("--> 'Step 4 - Get link reset in mailbox and reset password': PASSED");
		}
		else {
			logger.info("--> 'Step 4 - Get link reset in mailbox and reset password': FAILED");
			Reporter.log("--> 'Step 4 - Get link reset in mailbox and reset password': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_4");
		}

		/* Step 5. Verify login successful with new password */ 
		logger.info("------- Step 5. Verify login successful with new password -------");
		Reporter.log("------- Step 5. Verify login successful with new password -------");

		logger.info("Launch EUP Portal '" + urlEUP + "'");
		driver.get(urlEUP);
		Thread.sleep(5000);

		logger.info("login EUP new password '"+newPassword+"'");
		eupPage.login(userEUP, newPassword);
		Thread.sleep(2000);
		boolean result5 = true;
		try {
			pageMenu.clickAccountThenClickServiceTab();
			result5 = true;
			logger.info("--------------------------------");
			logger.info("login EUP Successful : PASSED");
			Reporter.log("--> login EUP Successful : PASSED");
			logger.info("----------------------------------------");
		} catch (Exception e2) { 
			result5 = false;
			logger.info("login EUP Successful : FAILED");
			Reporter.log("--> login EUP Successful : FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_5");
			logger.info("----------------------------------------");
		}

		/* Remove All Data */
		logger.info("------- REMOVE ALL DATA  -------");

		logger.info("Logout EUP Portal");
		logoutPage.logoutEUP();
		Thread.sleep(2000);

		logger.info("Launch Portal Admin '" + url + "'");
		driver.get(url);
		login.Login(user, password, url);

		logger.info("Remove subscriber");
		logger.info("Click Provision Then Choose Subscriber");
		menuAfter.clickToProvisionThenChooseSubscribers();

		logger.info("Remove subscriber '" + userID + "'");
		subPage.removeSubscriber(userID);

		Assert.assertTrue(result1 && result2 && result3a && result3b && result4 && result5);
	}
}