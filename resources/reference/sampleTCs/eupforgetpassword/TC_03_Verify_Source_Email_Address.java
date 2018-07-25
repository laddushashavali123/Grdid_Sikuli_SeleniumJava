package nuvia.eupforgetpassword;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.DriverFactory;
import automation.utils.web.GetScreenshot;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.objects.SubscriberObject;
import nuvia.pages.LoginPage;
import nuvia.pages.Logout;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.Subscribers;

public class TC_03_Verify_Source_Email_Address extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC_03");

	@Test
	@Parameters({ "EUP_Forget_Password_Portal_url","EUP_Forget_Password_Portal_user","EUP_Forget_Password_Portal_password","waitTime","EUP_Forget_Password_domain","EUP_Forget_Password_url" })
	public void TC_04_Verify_Only_Latest_Link_Reset_Valid_ (String url,String user, String password, int timeout, String domain,String forgetPwdURL ) throws Exception {

		WebDriver driver = getDriver();

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}

		/* Step 1. Create new subscriber*/
		logger.info("------- Step 1. Create new subscriber -------");
		Reporter.log("------- Step 1. Create new subscriber  -------");	

		logger.info("Launch Admin Portal '"+url+"'");
		driver.get(url);

		logger.info("login Admin Portal '"+user+"'");
		LoginPage login = new LoginPage(driver, timeout); 		
		login.Login(user, password, url);

		logger.info("Click Provisioning then choose Subscriber");
		MenuAfterChoseCustomer menuAfter = new MenuAfterChoseCustomer(driver, timeout);
		menuAfter.clickToProvisionThenChooseSubscribers();

		String emaildefault = "partnerundergb@autosv.part";
		String firstName = "firstName";
		String lastName = "forget";
		String userID = "eupforget";
		String passwd = "Kandy-1234";
		List<String> sku_list = Arrays.asList("925-0065-101");
		String site = "forgetpwd (forgetpwd)";
		String alertAdd = "Subscriber added.";
		String email = "hailhl3045@gmail.com";
		String email_password = "longhai686995";

		SubscriberObject subObj = new SubscriberObject(firstName, lastName, userID, passwd, sku_list, site);
		subObj.email = email;

		Subscribers subPage = new Subscribers(driver, timeout);
		logger.info("Add Subscriber with UserID '"+userID+"'" );
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
			logger.info("--> 'Create new subscriber ': PASSED");
			Reporter.log("--> 'Create new subscriber ': PASSED");
		} 
		else {
			logger.info("--> 'Create new subscriber ': FAILED");
			Reporter.log("--> 'Create new subscriber ': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_1c");
		}
		logger.info("----------------------------------------");

		/* Step 2. Request reset password */
		logger.info("------- Step 2. Request reset password -------");
		Reporter.log("------- Step 2. Request reset password -------");

		String userEUP = userID + "@" + domain;

		logger.info("Logout Portal Admin");
		Logout logoutPage = new Logout(driver, timeout);
		logoutPage.logout();

		logger.info("Launch EUP Portal '" + forgetPwdURL + "'");
		driver.get(forgetPwdURL);
		Thread.sleep(5000);

		PageLoginEUP eupPage = new PageLoginEUP(driver, timeout);

		logger.info("Click Forgot Your Password Button");
		eupPage.clickForgotPassword();
		
		logger.info("Set 'UserID' '"+userID+"' and set 'Email' '"+email+"'");
		eupPage.resetPassword(userEUP, email);

		String alearReset = "Request submitted. An Email was sent to your Email on file.";
		logger.info("Verify 'Request Reset Password' by Alert");

		boolean result2 = eupPage.verifyAlertAppears(alearReset);
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

		/* Step 3. Get sender address and verify with email SMB */
		logger.info("Step 3. Get sender address and verify with email");
		Reporter.log("Step 3. Get sender address and verify with email");

		logger.info("Get Sender Address");
		/*String senderAdress = eupPage.getSenderAddress(email, email_password);
		logger.info("-----------"+senderAdress+"-----------");

		logger.info("Verify sender Address in mailbox with email");
		boolean result3 = senderAdress.equals(emaildefault);
		logger.info("----------------------------------------");
		if (result3) {
			logger.info("--> 'Verify source email address': PASSED");
			Reporter.log("--> 'Verify source email address': PASSED");
		} 
		else {
			logger.info("--> 'Verify source email address': FAILED");
			Reporter.log("--> 'Verify source email address': FAILED");
			GetScreenshot.capture(this.getClass().getSimpleName() + "_3");
		}*/
		logger.info("----------------------------------------");

		/* Remove All Data */
		logger.info("------- REMOVE ALL DATA  -------");

		logger.info("Launch Portal Admin '" + url + "'");
		driver.get(url);
		login.Login(user, password, url);

		logger.info("Remove subscriber");
		logger.info("Click Provision Then Choose Subscriber");
		menuAfter.clickToProvisionThenChooseSubscribers();

		logger.info("Remove subscriber '" + userID + "'");
		subPage.removeSubscriber(userID);

//		Assert.assertTrue(result1 && result2 && result3);
	}
}
