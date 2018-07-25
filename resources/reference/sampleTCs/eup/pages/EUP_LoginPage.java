package nuvia.eup.pages;

import org.openqa.selenium.support.FindBy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;

import org.openqa.selenium.support.ui.ExpectedCondition;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.PageFactory;

public class EUP_LoginPage {
	public static final Logger logger = LogManager.getLogger("EUP_LoginPage");
	
	private WebDriver driver;
	private int timeout = 90;
	private final String pageLoadedText = "Please choose a Web Mail Program:";
	private final String pageUrl = "/";

	WebUtil webUtil = new WebUtil();
	
	// text username
	@FindBy(xpath = ".//*[@id='user_id']")
	private WebElement customer_textbox_userName;
	
	// text password
	@FindBy(xpath = ".//*[@id='password']")
	private WebElement customer_textbox_passWord;

	// button login
	@FindBy(xpath = ".//*[@id='btn-signin']")
	private WebElement customer_button_login;

//	// button forgot user id
//	@FindBy(id = "forgot_userid")
//	private WebElement customer_button_forgot_userid;
//	
	// button forgot password
	@FindBy(xpath = ".//*[@id='forgot_password']")
	private WebElement customer_button_forgot_password;
//	
//	// tab Customer
//	@FindBy(id = "tab-customer")
//	private WebElement login_tab_customer;

	//
	public EUP_LoginPage() {
	}

	public EUP_LoginPage(WebDriver driver) {
		this();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public EUP_LoginPage(WebDriver driver, int timeout) {
		this();
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}

	// ----- Action ---------

	public EUP_LoginPage clickButtonLogin() throws Exception {		
		WebElementSupport2.clickSupport(customer_button_login, timeout, 1000);
		return this;
	}

	public EUP_LoginPage clickForgotPassword() throws Exception {		
		WebElementSupport2.clickSupport(customer_button_forgot_password, timeout, 1000);
		return this;
	}
	
	public EUP_LoginPage setUserName(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(customer_textbox_userName, value, timeout, 1000);
		return this;
	}

	public EUP_LoginPage setPassWord(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(customer_textbox_passWord, value, timeout, 1000);
		return this;
	}

	public EUP_LoginPage getTextUserName() throws Exception {
		WebElementSupport2.getTextSupport(customer_textbox_userName, timeout, 1000);
		return this;
	}
	
	public EUP_LoginPage login(String username, String password) throws Exception {
		logger.info("Set username: " + username);
		setUserName(username);
		
		logger.info("Set password: " + password);
		setPassWord(password);
		
		logger.info("Click 'login' Button");
		 clickButtonLogin();
		 
		 WebElementSupport2.waitForElementSupport(By.xpath(".//*[@id='address_book']"), driver, timeout, 1000);
		 WebUtil.waitFor_LoadingFinished(driver, timeout);
		
		return this;
	}
	// ----------- Verify ------------------------

	/*public WebElement getCustomer_textbox_userName() {
		return customer_textbox_userName;
	}

	public void setCustomer_textbox_userName(WebElement customer_textbox_userName) {
		this.customer_textbox_userName = customer_textbox_userName;
	}*/


}

