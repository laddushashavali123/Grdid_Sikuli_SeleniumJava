package nuvia.eup.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;

public class PageMenuEUP {
	private WebDriver driver;
	private int timeout = 30;
	private int MAX_LOOP_MENU = 20;
	public static final Logger logger = LogManager.getLogger("PageMenuEUP");
	
	public PageMenuEUP(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public PageMenuEUP clickMenu(String menu) throws Exception {
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='" + menu + "']"), driver, timeout);
		return this;
	}
	
	public PageMenuEUP clickMenuThenChooseMenuItem(String menu, String subMenu) throws Exception {
		boolean result = false;
		for (int i = 0; i < MAX_LOOP_MENU; i++) {
			try{
				logger.info("Click '" + menu + "'");
				clickMenu(menu);
				Thread.sleep(1000);
				
				logger.info("Click '" + subMenu + "'");
				WebElement subMenuElement = driver.findElement(By.linkText(subMenu));
				subMenuElement.click(); // Please do not change click to clickSupport
				WebUtil.waitFor_LoadingFinished(driver, timeout);
				
				if ( menu.equals("Configuration")) {
					if ( subMenu.equals("Call Answer Groups") ) {
						result = WebElementSupport2.checkElementExist(By.xpath(".//*[@id='call_answer_groups']"), driver, 15);
					}					
					else {
						result = true;
					}
				}				
				else {
					// Do nothing
				}
				
				if (result) {
					break;
				}
			}catch (Exception e) {
			}
			logger.info("Try to Click '" + menu + "' Then Choose '" + subMenu + "'");
		}
		return this;
	}	
	
	public PageMenuEUP clickConfigurationThenChooseCallAnswerGroups() throws Exception {
		clickAccount(); //workaround to fix issue cannot click call answer groups
		clickMenuThenChooseMenuItem("Configuration", "Call Answer Groups");
		return this;
	}
	
	public PageMenuEUP clickAccount() throws Exception {
		clickMenu("Account");
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}

	public PageMenuEUP clickAccountThenClickAddressBookTab() throws Exception {
		logger.info("Click 'Account' Menu'");
		clickAccount();
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Address Book' Tab'");
		accountP.clickAddressBookTab();
		return this;
	}
	
	public PageMenuEUP clickAccountThenClickRoutingTab() throws Exception {
		logger.info("Click 'Account' Menu'");
		clickAccount();
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Routing' Tab'");
		accountP.clickRoutingTab();		
		return this;
	}
	
	public PageMenuEUP clickAccountThenClickServiceTab() throws Exception {
		logger.info("Click 'Account' Menu'");
		clickAccount();
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Service' Tab'");
		accountP.clickServiceTab();
		return this;
	}
}
