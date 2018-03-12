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

public class FormSearchCompanyList {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("FormSearchCompanyList");
	
	@FindBy(id = "search_text")
	private WebElement searchTextbox;
	
	@FindBy(id = "search_icon")
	private WebElement searchIconbox;
	
	public FormSearchCompanyList(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public FormSearchCompanyList searchCompany(String value) throws Exception {
		logger.info("Send '" + value + "' to the search textbox" );
		WebElementSupport2.sendKeysSupport(searchTextbox, value, timeout);
		logger.info("Click the search icon");
		WebElementSupport2.clickSupport(searchIconbox, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public boolean verifySearchCompanyList() throws Exception {
		boolean result1 = WebElementSupport2.checkElementExist(By.xpath(".//*[@id='grid_company']"), driver, timeout);
		boolean result2 = false;
		if (!result1) {
			result2 = WebElementSupport2.checkElementExist(By.xpath(".//*[contains(text(),'No Subscriber Contact')]"), driver, timeout);
		}
		
		boolean result = result1 || result2;
		
		return result;
	}
	

}
