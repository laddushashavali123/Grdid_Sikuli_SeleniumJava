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

public class TabService {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("TabRouting");
	
	@FindBy(id = "timezone")
	private WebElement timeZoneDropdown;
	
	@FindBy(id = "lang_select'")
	private WebElement languageDropdown;
	
	@FindBy(id = "originating_id_restriction")
	private WebElement IDRestrictionDropdown;
	
	@FindBy(id = "vsc_pin")
	private WebElement vscPinTextbox;
	
	@FindBy(id = "call_return")
	private WebElement callReturnDropdown;
	
	@FindBy(id = "network_call_waiting")
	private WebElement networkCallWaitingDropdown;
	
	@FindBy(id = "auto_retrieve_parked_calls")
	private WebElement autoRetrieveDropdown;
	
	@FindBy(id = "auto_retrieve_timer")
	private WebElement autoRetrieveTimerTextbox;
	
	@FindBy(id = "other_phone_presence")
	private WebElement onThePhonePresenceDropdown;
	
	@FindBy(id = "report_inactive")
	private WebElement reportWhenInactiveDropdown;
	
	
	public TabService(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public TabService selectTimeZone(String value) throws Exception {
		WebElementSupport2.selectDropdown(timeZoneDropdown, value, timeout);
		return this;
	}
	
	public TabService selectLanguague(String value) throws Exception {
		WebElementSupport2.selectDropdown(languageDropdown, value, timeout);
		return this;
	}
	
	public TabService selectIDRestriction(String value) throws Exception {
		WebElementSupport2.selectDropdown(IDRestrictionDropdown, value, timeout);
		return this;
	}
	
	public String getIDRestriction() throws Exception {
		return WebElementSupport2.getSelectedItemFromDropdown(IDRestrictionDropdown, timeout);
	}
	
	public TabService setVSCPIN(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(vscPinTextbox, value, timeout);
		return this;
	}
	
	public String getVSCPIN() throws Exception {
		//return WebElementSupport2.getTextByElementID(driver, "vsc_pin", timeout);
		return WebElementSupport2.getAttributeSupport(vscPinTextbox, "value", timeout);
	}
	
	public TabService selectCallReturn(String value) throws Exception {
		WebElementSupport2.selectDropdown(callReturnDropdown, value, timeout);
		return this;
	}
	
	public String getCallReturn() throws Exception {
		return WebElementSupport2.getSelectedItemFromDropdown(callReturnDropdown, timeout);
	}
	
	public TabService selectNetworkCallWaiting(String value) throws Exception {
		WebElementSupport2.selectDropdown(networkCallWaitingDropdown, value, timeout);
		return this;
	}
	
	public String getNetworkCallWaiting() throws Exception {
		return WebElementSupport2.getSelectedItemFromDropdown(networkCallWaitingDropdown, timeout);
	}
	
	public TabService selectAutoRetrieve(String value) throws Exception {
		WebElementSupport2.selectDropdown(autoRetrieveDropdown, value, timeout);
		return this;
	}
	
	public String getAutoRetrieve() throws Exception {
		return WebElementSupport2.getSelectedItemFromDropdown(autoRetrieveDropdown, timeout);
	}
	
	public TabService setAutoRetrieveTimer(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(autoRetrieveTimerTextbox, value, timeout);
		return this;
	}
	
	public String getAutoRetrieveTimer() throws Exception {
		//return WebElementSupport2.getTextByElementID(driver, "auto_retrieve_timer", timeout);
		return WebElementSupport2.getAttributeSupport(autoRetrieveTimerTextbox, "value", timeout);
	}
	
	public TabService selectOnThePhonePresence(String value) throws Exception {
		WebElementSupport2.selectDropdown(onThePhonePresenceDropdown, value, timeout);
		return this;
	}
	
	public TabService selectReportWhenInactive(String value) throws Exception {
		WebElementSupport2.selectDropdown(reportWhenInactiveDropdown, value, timeout);
		return this;
	}
	
	public TabService clickSaveSettingsButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("SAVE SETTINGS", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabService clickClientsButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("CLIENTS", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	//-------------------------------------Page After Clicking Clients Button----------------------
	
	private String clientSummarytext_xpath = ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/cust_div/div[1]/div/div/div";
	private String linkDownloadClientForWin_xpath = ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/cust_div/div[3]/div[2]/a";
	private String linkDownloadClientForMAC_xpath = ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/cust_div/div[5]/div[2]/a";
	
	public boolean checkSummaryDownloadText() throws Exception {
		return WebElementSupport2.checkElementExist(By.xpath(clientSummarytext_xpath), driver, 5);
	}
	
	public boolean checkLinkDownloadClientForWin() throws Exception {
		return WebElementSupport2.checkElementExist(By.xpath(linkDownloadClientForWin_xpath), driver, 5);
	}
	
	public boolean checkLinkDownloadClientForMAC() throws Exception {
		return WebElementSupport2.checkElementExist(By.xpath(linkDownloadClientForMAC_xpath), driver, 5);
	}
	
	public String getSummaryDownloadText() throws Exception {
		String result = "";
		if (checkSummaryDownloadText() ) {
			result = WebElementSupport2.getTextSupport(By.xpath(clientSummarytext_xpath), driver, timeout);
		} else {
			logger.info("Don't see Summary Download Text");
		}
		return result;
	}
	
	public String getTextOfDownloadClientLinkForWin() throws Exception {
		String result = "";
		if (checkLinkDownloadClientForWin()) {
			result = WebElementSupport2.getTextSupport(By.xpath(linkDownloadClientForWin_xpath), driver, timeout);
		} else {
			logger.info("Don't see Download Client Link For Win");
		}
		return result;
	}
	
	public String getTextOfDownloadClientLinkForMAC() throws Exception {
		String result = "";
		if (checkLinkDownloadClientForMAC()) {
			result = WebElementSupport2.getTextSupport(By.xpath(linkDownloadClientForMAC_xpath), driver, timeout);
		} else {
			logger.info("Don't see Download Client Link For MAC");
		}
		return result;
	}
	
	public String getURIOfDownloadClientLinkForWin() throws Exception {
		return WebElementSupport2.getAttributeSupport(By.xpath(linkDownloadClientForWin_xpath), driver, "href", timeout);
	}
	
	public String getURIOfDownloadClientLinkForMAC() throws Exception {
		return WebElementSupport2.getAttributeSupport(By.xpath(linkDownloadClientForMAC_xpath), driver, "href", timeout);
	}
	
	public boolean verifyThatDownloadClientLinkForWinIsALink() throws Exception {
		boolean result = false;
		if (checkLinkDownloadClientForWin() ) {
			//String href = WebElementSupport2.getAttributeSupport(By.xpath(linkDownloadClientForMAC_xpath), driver, "href", timeout);
			String href = getURIOfDownloadClientLinkForWin();
			if (!href.equals("")) result = true;
		} else {
			logger.info("Don't see Download Client Link For Win");
		}
		return result;
	}
	
	public boolean verifyThatDownloadClientLinkForMACIsALink() throws Exception {
		boolean result = false;
		if (checkLinkDownloadClientForMAC()) {
			//String href = WebElementSupport2.getAttributeSupport(By.xpath(linkDownloadClientForMAC_xpath), driver, "href", timeout);
			String href = getURIOfDownloadClientLinkForMAC();
			if (!href.equals("")) result = true;
		} else {
			logger.info("Don't see Download Client Link For MAC");
		}
		return result;
	}
	
	public boolean verifyDownloadLinksForMACAndWin(String summaryDownloadLink_Text, String downloadLinkForWin_Text, String downloadLinkForMac_Text ) throws Exception {
		boolean result = false;
		
		boolean result1 = false;
		logger.info("Verify that Text of Summary Download Link is '" + summaryDownloadLink_Text + "'");
		String currentSummaryDownloadLink = getSummaryDownloadText();
		logger.info("Current Text of Summary Download Link is '" + currentSummaryDownloadLink + "'");
		if (currentSummaryDownloadLink.equals(summaryDownloadLink_Text)) {
			result1 = true;
			logger.info("Text of Summary Download Link: PASSED");
		} else {
			logger.info("Text of Summary Download Link: FAILED");
		}
		
		boolean result2 = false;
		boolean result2a = false;
		boolean result2b = false;
		logger.info("Verify that Text of Download Link For Win is '" + downloadLinkForWin_Text + "' and it is a link");
		
		String currentDownloadLinkForWin = getTextOfDownloadClientLinkForWin();
		logger.info("Current Text of Download Link For Win is '" + currentDownloadLinkForWin + "'");
		if (currentDownloadLinkForWin.equals(downloadLinkForWin_Text)) {
			result2a = true;
			logger.info("Text of Download Link For Win: PASSED");
		} else {
			logger.info("Text of Download Link For Win: FAILED");
		}
		result2b = verifyThatDownloadClientLinkForWinIsALink();
		if (result2b) {
			logger.info("Download Link For Win is a link: PASSED");
		} else {
			logger.info("Download Link For Win is a link: FAILED");
		}
		
		result2 = result2a && result2b;
		
		
		boolean result3 = false;
		boolean result3a = false;
		boolean result3b = false;
		logger.info("Verify that Text of Download Link For MAC is '" + downloadLinkForMac_Text + "' and it is a link");
		
		String currentDownloadLinkForMac = getTextOfDownloadClientLinkForMAC();
		logger.info("Current Text of Download Link For MAC is '" + currentDownloadLinkForMac + "'");
		if (currentDownloadLinkForMac.equals(downloadLinkForMac_Text)) {
			result3a = true;
			logger.info("Text of Download Link For MAC: PASSED");
		} else {
			logger.info("Text of Download Link For MAC: FAILED");
		}
		result3b = verifyThatDownloadClientLinkForWinIsALink();
		if (result3b) {
			logger.info("Download Link For MAC is a link: PASSED");
		} else {
			logger.info("Download Link For MAC is a link: FAILED");
		}
		
		result3 = result3a && result3b;
		
		result = result1 && result2 && result3;

		return result;
	}
	
	public boolean verifyGENComDownloadLink() throws Exception {
		return verifyDownloadLinksForMACAndWin("Soft Client Downloads", "GENCom Desktop PC Client Link", "GENCom Desktop MAC Client Link");
	}
	
	public boolean verifySmartOfficeDownloadLink() throws Exception {
		return verifyDownloadLinksForMACAndWin("Smart Office Desktop Client Downloads", "Smart Office Desktop Client PC Link", "Smart Office Desktop Client MAC Link");
	}
	
	public boolean verifyRealtimeConnectionsDownloadLink() throws Exception {
		return verifyDownloadLinksForMACAndWin("Realtime Connections Client Downloads", "Realtime Connections Client PC Link", "Realtime Connections Client MAC Link");
	}
	
	public boolean verifyRTCClientForSkypeDownloadLink() throws Exception {
		return verifyDownloadLinksForMACAndWin("RTC Client for Skype Downloads", "RTC Client for Skype PC Link", "RTC Client for Skype MAC Link");
	}
	
	public boolean verifyNoDownloadLink() throws Exception {
		boolean result = false;
		
		boolean result1 = !checkSummaryDownloadText();
		if (result1) {
			logger.info("Summary Download Link does not appear: PASSED");
		} else {
			logger.info("Summary Download Link does not appear: FAILED");
		}
		
		boolean result2 = !checkLinkDownloadClientForWin();
		if (result2) {
			logger.info("Download Link For Win does not appear: PASSED");
		} else {
			logger.info("Download Link For Win does not appear: FAILED");
		}
		
		boolean result3 = !checkLinkDownloadClientForMAC();
		if (result3) {
			logger.info("Download Link For MAC does not appear: PASSED");
		} else {
			logger.info("Download Link For MAC does not appear: FAILED");
		}
		
		result = result1 && result2 && result3;
		return result;
	}
	
	//---------------------------------------------------------------------------------------------
	public TabService clickVSCButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("VSC", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public boolean verifyAlertAppears(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='sip_trunks_form']/div[2]/div[1]"), driver, timeout);
			logger.info("Alert '" + alertAppear + "' appears");
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
}
