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

public class TabRouting {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("TabRouting");
	
	@FindBy(id = "search_text")
	private WebElement searchTextbox;
	
	@FindBy(id = "search_icon")
	private WebElement searchIcon;
	
	@FindBy(id = "mode")
	private WebElement modeDropdown;
	
	@FindBy(id = "no_of_rings")
	private WebElement numberOfRingsDropdown;
	
	@FindBy(id = "presence_number")
	private WebElement presenceNumberTextbox;
	
	
	@FindBy(id = "Active On the Phone")
	private WebElement activeOnThePhoneCheckbox;
	
	@FindBy(id = "Unavailable Busy")
	private WebElement unavailableBusyCheckbox;
	
	@FindBy(id = "Unavailable Offline")
	private WebElement unavailableOfflineCheckbox;
	
	@FindBy(id = "Unavailable On Vacation")
	private WebElement unavailableOnVacationCheckbox;
	
	
	public TabRouting(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public TabRouting clickAdvanceModeButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("ADVANCED MODE", driver, timeout);
		return this;
	}
	
	public TabRouting clickBasicModeButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("BASIC MODE", driver, timeout);
		return this;
	}
	
	public TabRouting clickAddRouteButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("ADD ROUTE", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabRouting clickRemoveRouteButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("REMOVE ROUTE", driver, timeout);
		return this;
	}
	
	public TabRouting clickSaveRouteButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("SAVE ROUTE", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabRouting clickToggleStatusButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("TOGGLE STATUS", driver, timeout);
		return this;
	}
	
	public TabRouting clickYesButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("YES", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabRouting clickNoButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("NO", driver, timeout);
		return this;
	}
	
	public TabRouting clickReturnToListButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("RETURN TO LIST", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabRouting checkOffScopeInTableCallScreening(String scope) throws Exception {
		String tableXpath = ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table";
		WebElementSupport2.checkOffCheckboxInTableByTableXpath(driver, tableXpath, scope, -1);
		return this;
	}
	
	public boolean isScopeSelectedInTableCallScreening(String scope) throws Exception {
		String tableXpath = ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table";
		return WebElementSupport2.isCheckboxSelectedInTableByTableXpath(driver, tableXpath, scope, "/input", -1);
	}
	
	public TabRouting uncheckAllCheckboxesInTableCallScreening() throws Exception {
		String tableXpath = ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table";
		WebElementSupport2.uncheckAllCheckboxesInTableByTableXpath(driver, tableXpath);
		return this;
	}
	
	
	private boolean checkModeOfRoutingPage(String value) throws Exception {
		String xpath = "//*[contains(text(),'" + value + "')]";
		boolean result = WebElementSupport2.checkElementExist(By.xpath(xpath), driver, 5);
		return !result;
	}
	
	public TabRouting setModeOfRoutingTabToAdvancedMode() throws Exception {
		if (checkModeOfRoutingPage("ADVANCED MODE")) {
			// do nothing
			logger.info("Routing Page is already in Advanced Mode");
		} else {
			logger.info("Click 'ADVANCE MODE' Button");
			clickAdvanceModeButton();
			logger.info("Click 'YES' Button");
			clickYesButton();
		}
		return this;
/*		String st = "//button[text()='BASIC MODE']";
		try{
			WebElement elm = driver.findElement(By.xpath(st));
			if(elm.isDisplayed()) {
				logger.info("Routing Page is already in Advanced Mode");
				return this;
			}
		} catch(Exception e){
			
		}
		logger.info("Click 'ADVANCE MODE' Button");
		clickAdvanceModeButton();
		logger.info("Click to 'YES' Button");
		clickYesButton();
		return this;*/
	}
	
	public TabRouting setModeOfRoutingTabToBasicMode() throws Exception {
		if (checkModeOfRoutingPage("BASIC MODE")) {
			// do nothing
			logger.info("Routing Page is already in Basic Mode");
		} else {
			logger.info("Click 'BASIC Mode' Button");
			clickBasicModeButton();
			logger.info("Click 'YES' Button");
			clickYesButton();
		}
		return this;
/*		String st = "//button[text()='ADVANCED MODE']";
		try{
			WebElement elm = driver.findElement(By.xpath(st));
			if(elm.isDisplayed()) {
				logger.info("Routing Page is already in Basic Mode");
				return this;
			}
		} catch(Exception e){
			
		}
		logger.info("Click 'BASIC MODE' Button");
		clickBasicModeButton();
		logger.info("Click 'YES' Button");
		clickYesButton();
		return this;*/
	}
	
	public TabRouting searchRoute(String name) throws Exception {
		logger.info("Send '" + name + "' to the search textbox");
		WebElementSupport2.sendKeysSupport(searchTextbox, name, timeout);
		logger.info("Click the search icon");
		WebElementSupport2.clickSupport(searchIcon, timeout);
		return this;
	}
	
	public TabRouting clickRouteInTableRoutes(String name) throws Exception {
		WebElementSupport2.clickOnElementInTableByTableID(driver, "routing_rules_tbody", name, "/div/a", 0);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabRouting checkOffRouteInTableRoutes(String name) throws Exception {
		WebElementSupport2.checkOffCheckboxInTableByTableID(driver, "routing_rules_tbody", name, -1);
		return this;
	}
	
	public TabRouting selectModeOfCallRedirection(String value) throws Exception {
		WebElementSupport2.selectDropdown(modeDropdown, value, timeout);
		Thread.sleep(1000);
		return this;
	}
	
	public String getModeOfCallRedirection() throws Exception {
		return WebElementSupport2.getSelectedItemFromDropdown(modeDropdown, timeout);
	}

	public TabRouting selectNumberOfRings(String value) throws Exception {
		WebElementSupport2.selectDropdown(numberOfRingsDropdown, value, timeout);
		Thread.sleep(1000);
		return this;
	}
	
	public TabRouting setRoutingNumber(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(presenceNumberTextbox, value, timeout);
		return this;
	}
	
	public TabRouting checkOffActiveOnThePhoneCheckbox() throws Exception {
		WebElementSupport2.checkOffCheckbox(activeOnThePhoneCheckbox, true, timeout);
		//WebElementSupport2.clickSupport(activeOnThePhoneCheckbox, timeout);
		return this;
	}
	
	public TabRouting checkOffUnavailableBusyCheckbox() throws Exception {
		WebElementSupport2.checkOffCheckbox(unavailableBusyCheckbox, true, timeout);
		//WebElementSupport2.clickSupport(unavailableBusyCheckbox, timeout);
		return this;
	}
	
	public TabRouting checkOffUnavailableOfflineCheckbox() throws Exception {
		WebElementSupport2.checkOffCheckbox(unavailableOfflineCheckbox, true, timeout);
		//WebElementSupport2.clickSupport(unavailableOfflineCheckbox, timeout);
		return this;
	}
	
	public TabRouting checkOffUnavailableOnVacationCheckbox() throws Exception {
		WebElementSupport2.checkOffCheckbox(unavailableOnVacationCheckbox, true, timeout);
		//WebElementSupport2.clickSupport(unavailableOnVacationCheckbox, timeout);
		return this;
	}
	
	public TabRouting selectSimultaneous(String label, String value) throws Exception {
		String st = ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/div[9]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[" + label + "]/td[2]/select";
		WebElementSupport2.selectDropdown(By.xpath(st), driver, value, timeout);
		return this;
	}
	
	public TabRouting addRoute(String name) throws Exception {
		logger.info("Click 'ADD ROUTE' Button");
		clickAddRouteButton();
		
		FormAddRoute addR = new FormAddRoute(driver, timeout);
		logger.info("Set 'Route Name': '" + name + "'");
		addR.setRouteName(name);
		logger.info("Check off 'From THESE NUMBERS or..' in Table 'WHEN A CALL IS RECEIVED'");
		addR.checkOffFromTheseNumbersOrInTableWhenACallIsReceived();
		logger.info("Click 'From THESE NUMBERS or..' in Table 'WHEN A CALL IS RECEIVED'");
		addR.clickFromTheseNumbersOrInTableWhenACallIsReceived();
		logger.info("Select 'Home Phone'");
		addR.checkOffNumberInTableNumbers("Home Phone");
		logger.info("Check off 'Reject the call' in Table 'ROUTE'");
		addR.checkOffRejectTheCallInTableRoute();
		logger.info("Click 'SAVE ROUTE' Button");
		addR.clickSaveRouteButton();
		
		return this;
	}
	
	public TabRouting removeRoute(String name) throws Exception {
		logger.info("Search Route '" + name + "'");
		searchRoute(name);
		
		logger.info("Check off Route '" + name + "'");
		checkOffRouteInTableRoutes(name);
		
		logger.info("Click 'REMOVE ROUTE' Button");
		clickRemoveRouteButton();
		
		logger.info("Click 'YES' Button");
		clickYesButton();
		
		return this;
	}
	
	public boolean verifyAlertAppears(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='content_div']/div/div[1]/div[2]/div[1]"), driver, timeout);
			logger.info("Alert '" + alertAppear + "' appears");              
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean verifyRouteAlertAppears(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='routing_rules_form']/div[2]/div[1]"), driver, timeout);
			logger.info("Alert '" + alertAppear + "' appears");                  
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}

	public boolean verifyRouteListedInTableRoutes(String value) throws Exception {
		try{
			logger.info("Search Route : '" + value + "'");
			searchRoute(value);
			String contact = WebElementSupport2.getTextFromElementInTableByTableID(driver, "routing_rules_tbody", value, 0);
			if ( contact.equals(value) ) {
				logger.info("Contact '" + value + "' is listed in table 'CONTACTS'");
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
}
