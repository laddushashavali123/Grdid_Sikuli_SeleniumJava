package nuvia.eup.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import automation.utils.email.EmailUtils;
import automation.utils.web.AutomationSupport;
import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;

public class PageLoginEUP {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("PageLoginEUP");

	@FindBy(id = "user_id")
	private WebElement userIDTextbox;

	@FindBy(id = "password")
	private WebElement passwordTextbox;

	@FindBy(id = "btn-signin")
	private WebElement signInButton;

	@FindBy(id = "old_password")
	private WebElement oldPassTextbox;

	@FindBy(id = "new_password")
	private WebElement newPassTextbox;

	@FindBy(id = "confirm_password")
	private WebElement confirmTextbox;

	@FindBy(id = "change")
	private WebElement changeButton;

	@FindBy(id = "cancel")
	private WebElement cancelTextbox;

	// email and forgot password
	@FindBy(id = "email")
	private WebElement emailTextbox;

	@FindBy(id = "confirm_email")
	private WebElement confirmEmailTextbox;

	@FindBy(id = "submit_email")
	private WebElement submitEmailButton;

	@FindBy(id="forgot_password")
	private WebElement forgotPasswordButton;

	@FindBy(id="reset")
	private WebElement resetButton;
	
	@FindBy(id="change")
	private WebElement changePasswordButton;

	@FindBy(xpath=".//*[@id='js_enabled_content']/div/div/div[2]/div/div/div/div/div[3]/form/div/div[6]/div/p[1]")
	private WebElement alertLoginError;

	@FindBy(xpath=".//*[@id='js_enabled_content']/div/div/div[2]/div/div/div/div/div[3]/form/div/div[6]/div[2]/p[1]")
	private WebElement alertForgotPassword;
	
	public PageLoginEUP(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}

	public PageLoginEUP setUserID(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(userIDTextbox, value, timeout);
		return this;
	}

	public PageLoginEUP setPassword(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(passwordTextbox, value, timeout);
		return this;
	}

	public PageLoginEUP clickSignInButton() throws Exception {
		WebElementSupport2.clickSupport(signInButton, timeout);
		//		WebElementSupport2.waitForElementSupport(By.xpath(".//*[@id='address_book']"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}

	public PageLoginEUP clickSignInButton_ChangePW() throws Exception {
		WebElementSupport2.clickSupport(signInButton, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}

	public PageLoginEUP setOldPassword(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(oldPassTextbox, value, timeout);
		return this;
	}

	public PageLoginEUP setNewPassword(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(newPassTextbox, value, timeout);
		return this;
	}

	public PageLoginEUP setConfirmPassword(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(confirmTextbox, value, timeout);
		return this;
	}

	public PageLoginEUP clickChangeButton() throws Exception {
		WebElementSupport2.clickSupport(changeButton, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}

	//email and forgot password
	public void setEmail(String value) throws Exception {
		AutomationSupport.sendKeysSupport(emailTextbox, value, timeout);
	}

	public void setConfirmEmail(String value) throws Exception {
		AutomationSupport.sendKeysSupport(confirmEmailTextbox, value, timeout);
	}

	public void clickSubmitButton() throws Exception {
		AutomationSupport.clickSupport(submitEmailButton, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
	}

	public void clickForgotPassword() throws Exception {		
		WebElementSupport2.clickSupport(forgotPasswordButton, timeout, 1000);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
	}

	public void clickResetButton() throws Exception {
		AutomationSupport.clickSupport(resetButton, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
	}

	public PageLoginEUP login(String user, String password) throws Exception {
		logger.info("Set User ID: '" + user + "'");
		setUserID(user);
		logger.info("Set Password: '" + password + "'");
		setPassword(password);
		logger.info("Click 'SIGN IN' Button");
		clickSignInButton();
		return this;
	}

	public PageLoginEUP changePassword(String oldPassword, String newPassword) throws Exception {
		logger.info("Set 'Old Password': '" + oldPassword + "'");
		setOldPassword(oldPassword);
		logger.info("Set 'New Password': '" + newPassword + "'");
		setNewPassword(newPassword);
		logger.info("Set 'Confirm Password': '" + newPassword + "'");
		setConfirmPassword(newPassword);
		logger.info("Click 'CHANGE' Button");
		clickChangeButton();
		return this;
	}

	public PageLoginEUP login_changePW(String user, String oldPassword, String newPassword) throws Exception {
		logger.info("Set User ID: '" + user + "'");
		setUserID(user);
		logger.info("Set Password: '" + oldPassword + "'");
		setPassword(oldPassword);
		logger.info("Click 'SIGN IN' Button");
		clickSignInButton_ChangePW();
		logger.info("Change Password from '" + oldPassword + "' to '" + newPassword + "'");
		changePassword(oldPassword, newPassword);
		return this;
	}

	public PageLoginEUP firstLoginAndChangeInitiatePwd(String username, String password) throws Exception {
		login_changePW(username, password, password + "1");
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		WebUtil.waitFor_Element(userIDTextbox, timeout);
		login(username, password+"1");
		PageLogoutEUP logout = new PageLogoutEUP(driver, timeout);
		logout.accessToChangePwd();
		changePassword(password + "1", password);
		return this;
	}

	//submit email
	public void setEmailAndSubmit (String email) throws Exception {
		logger.info("Set Email '"+email+"'");
		setEmail(email);
		logger.info("Set Confirm Email '"+email+"'");
		setConfirmEmail(email);
		logger.info("Click Submit Button");
		clickSubmitButton();
	}
	// action forgot password
	public void resetPassword (String userID, String email) throws Exception {
		logger.info("Set 'User ID' '"+userID+"'");
		setUserID(userID);
		logger.info("Set 'Email' '"+email+"'");
		setEmail(email);
		logger.info("Click 'RESET' Button");
		clickResetButton();
	}
	
	public String getURLFromResetPasswordEmail (String email_username, String email_password) throws Exception {
		String URL = "";
		String email_subject = "Password Reset Request";
		List<String> emails = EmailUtils.getListUnreadEmailsBySubject(email_username, email_password, email_subject);
		for ( String email : emails ) {
			URL = email.split("paste the URL below into your web browser:<bold> ")[1];
			URL = URL.split(" </bold>")[0];
		}
		logger.info("URL: '" + URL + "'");
		return URL;
	}

	public void changePasswordReset (String value) throws Exception {
		logger.info("Set 'New Password' '"+value+"'");
		setNewPassword(value);
		logger.info("Set 'Confirm Password' '"+value+"'");
		setConfirmPassword(value);
		logger.info("Cick 'CHANGE' Button");
		clickChangeButton();
	}
	
	public boolean verifyChangeButton() throws Exception {
		return AutomationSupport.checkElementExist(By.id("change"), driver,30);
	}
	
	public boolean verifyAlertAppears(String alert) throws Exception {
		try{
			logger.info("Expect '" + alert + "' to appear");
			String alertLoginErrorAppear = AutomationSupport.getTextSupport(alertLoginError, timeout);
			logger.info(alertLoginErrorAppear);
			if (alertLoginErrorAppear.equals(alert) )
			{	logger.info("Alert '" + alert + "' appears");
				return true;
			}
			else
			{
				Reporter.log("Alert '" + alertLoginErrorAppear + "' appears");
			}
		}catch(Exception ex){
			return false;
		}
		return false;	
	}
}
