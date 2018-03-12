package nuvia.eup.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;
import nuvia.pages.AddCallGroup;

public class PageTabCallAnswerGroups {
	private WebDriver driver;
	private int timeOut = 30;
	private int interval_miliseconds = 1000;
	public static final Logger logger = LogManager.getLogger("PageTabCallAnswerGroups");
	
	// Icon - search Call Answer Group textbox
	@FindBy(id = "search_text")
	private WebElement searchCallAnswerGroup_textbox;
	
	// Icon - search Call Answer Group Icon
	@FindBy(id = "search_icon")
	private WebElement searchCallAnswerGroup_icon;
		
	public PageTabCallAnswerGroups(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeOut = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public PageTabCallAnswerGroups searchCallAnswerGroup(String value) throws Exception {
		logger.info("Send text: '" + value + "' to the search textbox");
		WebElementSupport2.sendKeysSupport(searchCallAnswerGroup_textbox, value, timeOut, interval_miliseconds);
		logger.info("Click on the search icon");
		clickSearchCallAnswerGroupIcon();
		return this;
	}
	
	private PageTabCallAnswerGroups clickSearchCallAnswerGroupIcon() throws Exception {
		WebElementSupport2.clickSupport(searchCallAnswerGroup_icon, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}
	
	public PageTabCallAnswerGroups clickCallAnswerGroup(String value) throws Exception {
		boolean result = false;
		for (int i = 0; i < 10; i++) { //repeat 10 times
			try {
				WebElementSupport2.clickOnElementInTableByTableID(driver, "callrouting_tbody", value, "/div/a", 0);
				Thread.sleep(3000); // do not delete this sleep
				WebUtil.waitFor_LoadingFinished(driver, timeOut);
				result = WebElementSupport2.checkElementExist(By.id("save_group"), driver, 5);
				
				if (result) {
					break;
				}
				
			} catch (Exception e) {}
			
			logger.info("Try to Click 'Call Answer Group' '" + value + "'");
		}
		return this;
	}
	
	
	public boolean verifyAndEditCallAnswerGroup_InvalidMaxWaitTime(String strFind, String maxWaitTime, boolean needToFindItem) throws Exception {
		if(needToFindItem){
			logger.info("Search Call Answer Groups: " + strFind);
			searchCallAnswerGroup(strFind);
			logger.info("Click Call Answer Groups: " + strFind);
			clickCallAnswerGroup(strFind);
			
			AddCallGroup addCallGroup = new AddCallGroup(driver, timeOut);
			addCallGroup.clickButtonShowAdvanced();
		}
		return inputAndVerify_InvalidMaxWaitTime(strFind, maxWaitTime);
	}
	
	public boolean inputAndVerify_InvalidMaxWaitTime(String strFind, String maxWaitTime) throws Exception {
		String alert = "Please enter a max wait time from 60 to 5400 seconds.";
		AddCallGroup addCallGroup = new AddCallGroup(driver, timeOut);		
		logger.info("Set new Max Wait Time:" + maxWaitTime);
		addCallGroup.setMaxWaitTimeSec(maxWaitTime);
		logger.info("Click on Save Group Button");
		addCallGroup.clickButtonSaveGroup();
		return verifyAlertUpdateUCDAppears(alert);
	}
	
	public boolean verifyAndEditCallAnswerGroup_MaxWaitTime(String strFind, String maxWaitTime, String oldValue) throws Exception {
		boolean result = false;
		logger.info("Search Call Answer Groups: " + strFind);
		searchCallAnswerGroup(strFind);
		logger.info("Click Call Answer Groups: " + strFind);
		clickCallAnswerGroup(strFind);
		
		AddCallGroup addCallGroup = new AddCallGroup(driver, timeOut);
		addCallGroup.clickButtonShowAdvanced();
		logger.info("Get Current Max Wait Time:" + addCallGroup.getMaxWaitTime() + ". Expected Current Wait time is " + oldValue);
		result = addCallGroup.getMaxWaitTime().equals(oldValue);
		logger.info("Set new Max Wait Time:" + maxWaitTime);
		addCallGroup.setMaxWaitTimeSec(maxWaitTime);
		logger.info("Click on Save Group Button");
		addCallGroup.clickButtonSaveGroup();
		return result;
	}
	
	public boolean editCallAnswerGroup_MaxWaitTime(String strFind, String maxWaitTime, String oldValue) throws Exception {
		boolean result = true;
		logger.info("Search Call Answer Groups: " + strFind);
		searchCallAnswerGroup(strFind);
		logger.info("Click Call Answer Groups: " + strFind);
		clickCallAnswerGroup(strFind);
		
		AddCallGroup addCallGroup = new AddCallGroup(driver, timeOut);
		addCallGroup.clickButtonShowAdvanced();		
		
		String current = addCallGroup.getMaxWaitTime();
		logger.info("Get current Max Wait Time:" + current);
		result = current.equals(oldValue);
		if(!result){
			logger.info("Current value '"+current+"' does not match the expected value : '"+oldValue+"'");
			Reporter.log("Current value '"+current+"' does not match the expected value : '"+oldValue+"'");
		}
		logger.info("Set new Max Wait Time:" + maxWaitTime);
		addCallGroup.setMaxWaitTimeSec(maxWaitTime);
		logger.info("Click on Save Group Button");
		addCallGroup.clickButtonSaveGroup();
		return result;
	}
	
	public boolean verifyCallAnwerGroupListedInTableCallAnswerGroups(String value) throws Exception {
		try{
			logger.info("Search Call Answer Group '" + value + "'");
			searchCallAnswerGroup(value);
			String catalog = WebElementSupport2.getTextFromElementInTableByTableID(driver, "callrouting_tbody", value, 0);
			if ( catalog.equals(value) ) {
				logger.info("Call Answer Group '" + value + "' is listed in table 'CALL ANSWER GROUPS'");
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean verifyAlertAppears(String alert){
		int interval_miliseconds = 1000;
		try{
			logger.info("Expect '" + alert + "' to appear");
			
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='content_div']/div/div/div/div[1]/div[2]/div[1]"), driver, timeOut, interval_miliseconds);
			logger.info("Alert '" + alertAppear + "' appears");
			if (alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean verifyAlertUpdateUCDAppears(String alert){
		int interval_miliseconds = 1000;
		try{
			logger.info("Expect '" + alert + "' to appear");
			
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='frm_call_answer']/div/div[2]/div[1]"), driver, timeOut, interval_miliseconds);
			logger.info("Alert '" + alertAppear + "' appears");
			if (alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
}
