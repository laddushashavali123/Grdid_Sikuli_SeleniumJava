package nuvia.eup.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;

public class PageAccountEUP {
	private WebDriver driver;
	private int timeout = 30;
	private int MAX_LOOP_MENU = 20;
	public static final Logger logger = LogManager.getLogger("PageAccountEUP");
	
	@FindBy(id = "address_book")
	private WebElement addressBookTab;
	
	@FindBy(id = "routing")
	private WebElement routingTab;
	
	@FindBy(id = "settings")
	private WebElement serviceTab;
	
	public PageAccountEUP(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public PageAccountEUP clickAddressBookTab() throws Exception {
		boolean result = false;
		for (int i = 0; i < MAX_LOOP_MENU; i++) {
			WebElementSupport2.clickSupport(addressBookTab, timeout);
			WebUtil.waitFor_LoadingFinished(driver, timeout);
			result = WebElementSupport2.checkElementExist(By.id("remove_calls"), driver, 15);
			if (result) {
				break;
			}
		}
		return this;
	}
		
	public PageAccountEUP clickRoutingTab() throws Exception {
		boolean result = false;
		for (int i = 0; i < MAX_LOOP_MENU; i++) {
			WebElementSupport2.clickSupport(routingTab, timeout);
			WebUtil.waitFor_LoadingFinished(driver, timeout);
			boolean result1 = WebElementSupport2.checkElementExist(By.xpath("//button[text()='BASIC MODE']"), driver, 3);
			boolean result2 = WebElementSupport2.checkElementExist(By.xpath("//button[text()='ADVANCED MODE']"), driver, 3);
			boolean result3 = WebElementSupport2.checkElementExist(By.xpath(".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[1]/div[1]"), driver, 3);
			result = result1 || result2 || result3;
			if (result) {
				break;
			}
		}
		return this;
	}
	
	public PageAccountEUP clickServiceTab() throws Exception {
		boolean result = false;
		String saveSettingsButton = "//*[@id='settings']";
		for (int i = 0; i < MAX_LOOP_MENU; i++) {
			WebElementSupport2.clickSupport(serviceTab, timeout);
			WebUtil.waitFor_LoadingFinished(driver, timeout);
			result = WebElementSupport2.checkElementExist(By.xpath(saveSettingsButton), driver, 5);
			if (result) {
				break;
			}
		}
		return this;
	}
	
}
