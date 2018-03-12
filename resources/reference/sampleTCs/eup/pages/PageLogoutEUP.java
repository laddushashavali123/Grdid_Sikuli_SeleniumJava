package nuvia.eup.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import automation.utils.web.WebElementSupport2;

public class PageLogoutEUP {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("PageLogoutEUP");
	
	@FindBy(xpath = ".//*[@id='nav']/li[1]/a")
	private WebElement welcomeText;
	
	@FindBy(xpath = ".//*[@id='class_menu']/li/div/div[3]/div[1]/a")
	private WebElement logoutText;
	
	@FindBy(xpath = "//*[@id='class_menu']/li/div/div[1]/div[1]/a")
	private WebElement changePwd;
		
	public PageLogoutEUP(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public PageLogoutEUP logout() throws Exception {
		logger.info("Click 'Welcome ...'");
		WebElementSupport2.clickSupport(welcomeText, timeout);
		logger.info("Click 'Logout'");
		WebElementSupport2.clickSupport(logoutText, timeout);
		WebElementSupport2.waitForElementSupport(By.id("user_id"), driver, timeout);
		return this;
	}
	
	public PageLogoutEUP accessToChangePwd() throws Exception {
		logger.info("Click 'Welcome ...'");
		WebElementSupport2.clickSupport(welcomeText, timeout);
		logger.info("Click 'Change Password'");
		WebElementSupport2.clickSupport(changePwd, timeout);
		WebElementSupport2.waitForElementSupport(By.id("old_password"), driver, timeout);
		return this;
	}
}
