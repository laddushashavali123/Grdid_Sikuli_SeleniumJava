package nuvia.eup.pages;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;

public class EUP_Header {

	private WebDriver driver;
	private int timeOut = 30;
	private int interval_miliseconds = 1000;
	private final String pageLoadedText = "Please choose a Web Mail Program:";
	private final String pageUrl = "/";
	private int MAX_LOOP_MENU = 20;

	WebUtil webUtil = new WebUtil();
	
	//title Address Book tab
	@FindBy(id="address_book")
	private WebElement elm_address_book_tab;
	
	//title Routing tab
	@FindBy(id="routing")
	private WebElement elm_routing_tab;
	
	//title Service tab
	@FindBy(id="settings")
	private WebElement elm_service_tab;
	
	//title Subscriber Defaults
	@FindBy(xpath="html/body/div/div/div/div[1]/div[1]/div[2]/ul/li[1]/a")
	private WebElement elm_welcome;
	
	// ------------------------Constructors------------------------
	public EUP_Header() {
	}

	public EUP_Header(WebDriver driver) {
		this();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public EUP_Header(WebDriver driver, int timeout) {
		this();
		this.driver = driver;
		this.timeOut = timeout;
		PageFactory.initElements(driver, this);
	}

	//	// ----- Action ---------
	
	// click button Address Book
	public EUP_Header clickButtonAddressBook() throws Exception {
		boolean result = false;
		for (int i = 0; i < MAX_LOOP_MENU; i++) {
			WebElementSupport2.clickSupport(elm_address_book_tab, timeOut, interval_miliseconds);
			WebUtil.waitFor_LoadingFinished(driver, timeOut);
			result = WebElementSupport2.checkElementExist(By.id("remove_calls"), driver, 15);
			if (result) {
				break;
			}
		}
		return this;
	}
	
	// click button Address Book
	public EUP_Header clickButtonRouting() throws Exception {
		boolean result = false;
		for (int i = 0; i < MAX_LOOP_MENU; i++) {
			WebElementSupport2.clickSupport(elm_routing_tab, timeOut, interval_miliseconds);
			WebUtil.waitFor_LoadingFinished(driver, timeOut);
			boolean result1 = WebElementSupport2.checkElementExist(By.xpath("//button[text()='BASIC MODE']"), driver, 3);
			boolean result2 = WebElementSupport2.checkElementExist(By.xpath("//button[text()='ADVANCED MODE']"), driver, 3);
			result = result1 || result2;
			if (result) {
				break;
			}
		}
		return this;
	}
	
	// click button Address Book
	public EUP_Header clickButtonService() throws Exception {
		boolean result = false;
		for (int i = 0; i < MAX_LOOP_MENU; i++) {
			WebElementSupport2.clickSupport(elm_service_tab, timeOut, interval_miliseconds);
			WebUtil.waitFor_LoadingFinished(driver, timeOut);
			result = WebElementSupport2.checkElementExist(By.xpath("//button[text()='SAVE SETTINGS']"), driver, 5);
			if (result) {
				break;
			}
		}

		return this;
	}
	
//   //----------- Verify ------------------------
	
	public boolean verifyLoginByWelcome() {
		try{
			System.out.println(elm_welcome.getText().substring(0, 7));
		if(elm_welcome.getText().substring(0, 7).equals("Welcome"))
			return true;
		}catch(Exception ex){
			return false;
		}	
		return false;
	}


}
