package nuvia.eup.multipleLanguage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import automation.utils.web.GetScreenshot;
import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;
import core.interact.Action;
import io.appium.java_client.ScrollsTo;
import nuvia.eup.pages.PageMenuEUP;

public class VerifyMultipleLanguage {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("EUP_MultipleLanguage");
	Properties properties = new Properties();
	
	
	public VerifyMultipleLanguage(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public void setEUPLanguage(String language) throws Exception{
		logger.info("+++++++++++++++++++Set the language of EUP to value '"+language+"'+++++++++++++++++++");
		PageMenuEUP menuEUP = new PageMenuEUP(driver, timeout);
		logger.info("EUP - Click 'Account' Then Click 'Service' Tab");
		menuEUP.clickAccountThenClickServiceTab();
		configLanguageParams(language);
		int idx = 1;
		if(language.equalsIgnoreCase("English")){
			logger.info("English");
			System.setProperty("currentLanguage", "English");
			idx = 0;
		}else if(language.equalsIgnoreCase("French")){
			logger.info("French");
			System.setProperty("currentLanguage", "French");
			idx = 2;
		} else if (language.equalsIgnoreCase("Portuguese")){
			logger.info("Portuguese");
			System.setProperty("currentLanguage", "Portuguese");
			idx = 1;
		}else if(language.equalsIgnoreCase("Spanish")){
			logger.info("Spanish");
			System.setProperty("currentLanguage", "Spanish");
			idx = 3;
		} else{
			configLanguageParams("English");
		}
		
		WebElementSupport2.selectDropdownByIndex(By.xpath(".//*[@id='lang_select']"), driver, idx, timeout);
		logger.info("Click button 'Save Settings'");
		Thread.sleep(2000);
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/div[9]/div/button"), driver, timeout);
		Thread.sleep(5000);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		WebElementSupport2.waitForElementAppreared(By.xpath(".//*[@id='lang_select']"), driver, timeout);
	}
	
	public void configLanguageParams(String language) throws FileNotFoundException, IOException{		
		String workingDir = System.getProperty("user.dir");
		String pathFile = workingDir + File.separator + "data" + File.separator;
		logger.info(pathFile + "EUP_"+language+".txt");
		FileInputStream input = new FileInputStream(pathFile + "EUP_"+language+".txt" );
		properties.load(new InputStreamReader(input, Charset.forName("UTF-8")));
	}
	
	public boolean verifyServiceTabContainByLanguage() throws Exception{
		logger.info("+++++++++++++++++++Verifying contain of Tab Service+++++++++++++++++++");
		PageMenuEUP menuEUP = new PageMenuEUP(driver, timeout);
		logger.info("EUP - Click 'Account' Then Click 'Service' Tab");
		menuEUP.clickAccountThenClickServiceTab();
		logger.info("---------Verifying Session names-------------------");
		boolean result = verifyTextEqualByXPath("Service_Session_Service", "//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/div[1]/div", "Tab 'Service' - Service Sessions");
		result = result & verifyTextEqualByXPath("Service_Session_Presence", "//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/cust_div[2]/div[4]/div", "Tab 'Service' - Presence Sessions");
		result = result & verifyTextEqualByXPath("Service_Session_Parameter", "//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/div[1]/div", "Tab 'Service' - Parameters Sessions");		
		result = result & verifyTextEqualByXPath("Service_Header", "//*[@id='sip_trunks_form']/div[3]/div/div[2]/div/div[1]", "Tab 'Service' - Header Text 'Service'");
		
		logger.info("---------Verifying Service Session-------------------");
		result = result & verifyTextEqualByXPath("Service_Label_TimeZone", ".//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/div[3]/div[1]/label", "Tab 'Service' - TimeZone");
		result = result & verifyTextEqualByXPath("Service_Label_Language", ".//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/div[4]/div[1]/label", "Tab 'Service' - Language");
		result = result & verifyTextEqualByXPath("Service_Item_Language_English", ".//*[@id='lang_select']/option[@value='0']", "Tab Service' - Language - Item English");		
		result = result & verifyTextEqualByXPath("Service_Item_Language_Portuguese", ".//*[@id='lang_select']/option[@value='1']", "Tab 'Service' - Language - Item Portuguese");
		result = result & verifyTextEqualByXPath("Service_Label_Language_French", ".//*[@id='lang_select']/option[@value='2']", "Tab 'Service' - Language - Item French");
		result = result & verifyTextEqualByXPath("Service_Label_IDRestriction", ".//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/cust_div[1]/div[1]/div[1]/label", "Tab 'Service' - ID Restriction");
		result = result & verifyTextEqualByXPath("Common_Item_Enabled", ".//*[@id='originating_id_restriction']/option[@value='0']", "Tab 'Service' - ID Restriction - Item Enabled");
		result = result & verifyTextEqualByXPath("Common_Item_Disabled", ".//*[@id='originating_id_restriction']/option[@value='1']", "Tab 'Service' - ID Restriction - Item Disabled");
		result = result & verifyTextEqualByXPath("Service_Label_VSCPin", ".//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/cust_div[1]/div[2]/div[1]/label", "Tab 'Service' - VSC PIN");
		result = result & verifyTextEqualByXPath("Service_Label_CallReturn", ".//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/cust_div[2]/div[1]/div[1]/label", "Tab 'Service' - Call Return");
		result = result & verifyTextEqualByXPath("Common_Item_Enabled", ".//*[@id='call_return']/option[@value='0']", "Tab 'Service' - Call Return - Item Enabled");
		result = result & verifyTextEqualByXPath("Common_Item_Disabled", ".//*[@id='call_return']/option[@value='1']", "Tab 'Service' - Call Return - Item Disabled");
		result = result & verifyTextEqualByXPath("Service_Label_NetworkCallWaiting", ".//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/cust_div[2]/div[2]/div[1]/label", "Tab 'Service' - Network Call Waiting");
		result = result & verifyTextEqualByXPath("Common_Item_Enabled", ".//*[@id='network_call_waiting']/option[@value='0']", "Tab 'Service' - Network Call Waiting - Item Enabled");
		result = result & verifyTextEqualByXPath("Common_Item_Disabled", ".//*[@id='network_call_waiting']/option[@value='1']", "Tab 'Service' - Network Call Waiting - Item Disabled");
		result = result & verifyTextEqualByXPath("Service_Label_AutoRetrieve", ".//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/cust_div[2]/div[3]/div[1]/label", "Tab 'Service' - Auto Retrieve");
		result = result & verifyTextEqualByXPath("Common_Item_Enabled", ".//*[@id='auto_retrieve_parked_calls']/option[@value='0']", "Tab 'Service' - Auto Retrieve - Item Enabled");
		result = result & verifyTextEqualByXPath("Common_Item_Disabled", ".//*[@id='auto_retrieve_parked_calls']/option[@value='1']", "Tab 'Service' - Auto Retrieve - Item Disabled");
		
		logger.info("---------Verifying Presence Session-------------------");
		result = result & verifyTextEqualByXPath("Service_Label_OnThePhonePresence", ".//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/cust_div[2]/div[6]/div[1]/label", "Tab 'Service' - On the Phone Presence");
		result = result & verifyTextEqualByXPath("Common_Item_Enabled", ".//*[@id='other_phone_presence']/option[@value='0']", "Tab 'Service' - On the Phone Presence - Item Enabled");
		result = result & verifyTextEqualByXPath("Common_Item_Disabled", ".//*[@id='other_phone_presence']/option[@value='1']", "Tab 'Service' - On the Phone Presence - Item Disabled");		
		result = result & verifyTextEqualByXPath("Service_Label_ReportWhenInactive", ".//*[@id='sip_trunks_form']/div[6]/div/div[1]/cust_div/cust_div[2]/div[7]/div[1]/label", "Tab 'Service' - Report when Inactive");
		result = result & verifyTextEqualByXPath("Common_Item_Enabled", ".//*[@id='report_inactive']/option[@value='0']", "Tab 'Service' - On the Phone Presence - Item Enabled");
		result = result & verifyTextEqualByXPath("Common_Item_Disabled", ".//*[@id='report_inactive']/option[@value='1']", "Tab 'Service' - On the Phone Presence - Item Disabled");
		
		logger.info("---------Verifying Parameters Session-------------------");
		result = result & verifyTextEqualByXPath("Service_Label_VoIPNumbers", ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/div[1]/div[1]/label", "Tab 'Service' - VoIP Numbers");
		
		logger.info("---------Verifying Buttons-------------------");
		result = result & verifyTextEqualByXPath("Service_Button_Client", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[2]/div/button", "Tab 'Service' - Button 'CLIENTS'");
		result = result & verifyTextEqualByXPath("Service_Button_VSC", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[4]/div/button", "Tab 'Service' - Button 'VSC'");
		result = result & verifyTextEqualByXPath("Service_Button_SaveSetting", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[9]/div/button", "Tab 'Service' - Button 'SAVE SETTINGS'");
		
		logger.info("---------Verifying Settings Section-------------------");
		result = result & verifyTextEqualByXPath("Service_Session_Settings", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[1]/div/h2", "Tab 'Service' - Session Settings");
		
		logger.info("---------Verifying Actions Section-------------------");
		result = result & verifyTextEqualByXPath("Service_Session_Actions", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[8]/div/h2", "Tab 'Service' - Session Actions");
		
		logger.info("---------Verifying Success Message-------------------");
		logger.info("click button 'Save Settings'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/div[9]/div/button"), driver, timeout);
		result = result & verifyTextEqualByXPath("Service_Success_Message", ".//*[@id='sip_trunks_form']/div[2]/div[1]", "Tab 'Service' - Save Settings Successful message");

		/*logger.info("---------Verifying Failed Message-------------------");
		logger.info("Set VSC value: '111111111111111'");
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='vsc_pin']"), driver, "111111111111111", timeout);
		logger.info("click button 'Save Settings'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/div[9]/div/button"), driver, timeout);
		result = result & verifyTextEqualByXPath("Service_Failed_Message", ".//*[@id='sip_trunks_form']/div[2]/div[1]", "Tab 'Service' - Save Settings Un-Successful message");
		*/
		logger.info("---------Verifying Clients-------------------");
		logger.info("click button 'Clients'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/div[2]/div/button"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("Service_Clients_Header", ".//*[@id='sip_trunks_form']/div[3]/div/div[2]/div/div[1]", "Tab 'Service' - Settings Client - Header");
		result = result & verifyTextEqualByXPath("Service_Clients_Session_Paramters", ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/div[1]/div", "Tab 'Service' - Settings Client - Sessions Parameters");
		
		logger.info("---------Verifying Clients - Session Parameters-------------------");
		result = result & verifyTextEqualByXPath("Service_Clients_Label_SipProxy", ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/div[1]/div[1]/label", "Tab 'Service' - Settings Client - SIP Proxy");
		result = result & verifyTextEqualByXPath("Service_Clients_Label_SipPort", ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/div[2]/div[1]/label", "Tab 'Service' - Settings Client - SIP Port");
		result = result & verifyTextEqualByXPath("Service_Clients_Label_UserID", ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/div[3]/div[1]/label", "Tab 'Service' - Settings Client - User ID");
		result = result & verifyTextEqualByXPath("Service_Clients_Label_Domain", ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/div[4]/div[1]/label", "Tab 'Service' - Settings Client - Domain");
		
		logger.info("---------Verifying Clients - Session Realtime Connections Client Downloads-------------------");
		result = result & verifyTextEqualByXPath("Service_Clients_Session_ClientDownload", ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/cust_div/div[1]/div/div/div", "Tab 'Service' - Settings Client - Session Realtime Connections Client Downloads");
		result = result & verifyTextEqualByXPath("Service_Clients_PCLink", ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/cust_div/div[3]/div[2]/a", "Tab 'Service' - Settings Client - PC Link");
		result = result & verifyTextEqualByXPath("Service_CLients_MACLink", ".//*[@id='sip_trunks_form']/div[6]/div/div[2]/div/div/cust_div/cust_div/div[5]/div[2]/a", "Tab 'Service' - Settings Client - MAC Link");
		
		logger.info("---------Verifying Clients - Session Settings-------------------");
		result = result & verifyTextEqualByXPath("Service_Clients_Sesstion_Settings", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[1]/div/h2", "Tab 'Service' - Settings Client - Session Settings");
		result = result & verifyTextEqualByXPath("Service_Clients_Button_VoiceServices", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[2]/div/button", "Tab 'Service' - Settings Client - Button Voice Service");
		result = result & verifyTextEqualByXPath("Service_Clients_Button_VSC", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[4]/div/button", "Tab 'Service' - Settings Client - Button VSC");
		
		logger.info("---------Verifying Clients - Session Actions-------------------");
		result = result & verifyTextEqualByXPath("Service_Clients_Sesstion_Actions", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[8]/div/h2", "Tab 'Service' - Settings Client - Session Actions");
		result = result & verifyTextEqualByXPath("Service_Clients_SessionAction_Value", ".//*[@id='content_div']/div[1]/div[3]/div/div/cust_div/p", "Tab 'Service' - Settings Client - Session Actions Value");
		
		logger.info("Click button 'Voice Services'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/div[2]/div/button"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		
		logger.info("Click button 'VSC'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/div[4]/div/button"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		
		result = result & verifyTextEqualByXPath("Service_Button_Client", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[4]/div/button", "Tab 'Service' - VSC - Button Client");
		result = result & verifyTextEqualByXPath("Service_Clients_Button_VoiceServices", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[2]/div/button", "Tab 'Service' - VSC - Button Voice Service");
		result = result & verifyTextEqualByXPath("Service_Clients_Sesstion_Actions", ".//*[@id='content_div']/div[1]/div[3]/div/div/div[8]/div/h2", "Tab 'Service' - VSC - Session Actions");
		result = result & verifyTextEqualByXPath("Service_Clients_SessionAction_Value", ".//*[@id='content_div']/div[1]/div[3]/div/div/cust_div/p", "Tab 'Service' - VSC - Session Actions Value");
		
		result = result & verifyTextEqualByXPath("Service_VSC_Header", ".//*[@id='sip_trunks_form']/div[3]/div/div[2]/div/div[1]", "Tab 'Service' - VSC - Header");
		result = result & verifyTextEqualByXPath("Service_VSC_TableHeader_Code", ".//span[@id='code']", "Tab 'Service' - VSC - Table Header - Code");
		result = result & verifyTextEqualByXPath("Service_VSC_TableHeader_Name", ".//span[@id='name | xlat']", "Tab 'Service' - VSC - Table Header - Name");
		
		logger.info("---------Verifying VSC Code details-------------------");
		for(int i=1;i<=91;i++){
			/*if(i<10){
				result = result & verifyTextEqualByXPath("Service_VSC_0" + i, ".//*[@id='vsccode_r"+(i-1)+"_c1']", "Tab 'Service' - VSC - Table Row - " + i + " - code: "+ WebElementSupport2.getTextSupport(By.xpath(".//*[@id='vsccode_r"+(i-1)+"_c0']"), driver, timeout));
			}else{
				result = result & verifyTextEqualByXPath("Service_VSC_" + i, ".//*[@id='vsccode_r"+(i-1)+"_c1']", "Tab 'Service' - VSC - Table Row - " + i + " - code: "+ WebElementSupport2.getTextSupport(By.xpath(".//*[@id='vsccode_r"+(i-1)+"_c0']"), driver, timeout));
			}*/
			String code = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='vsccode_r"+(i-1)+"_c0']"), driver, timeout);
			code = code.replace("*", "");
			result = result & verifyTextEqualByXPath("Service_VSC_" + code, ".//*[@id='vsccode_r"+(i-1)+"_c1']", "Tab 'Service' - VSC - Table Row - " + i + " - code: "+ WebElementSupport2.getTextSupport(By.xpath(".//*[@id='vsccode_r"+(i-1)+"_c0']"), driver, timeout));
		}
		
		logger.info("Click button 'Voice Services'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/div[2]/div/button"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return result;
	} 
	
	public boolean verifyRoutingTab() throws Exception{
		logger.info("+++++++++++++++++++Verifying contain of Tab Routing+++++++++++++++++++");
		PageMenuEUP menuEUP = new PageMenuEUP(driver, timeout);
		logger.info("EUP - Click 'Account' Then Click 'Routing' Tab");
		menuEUP.clickAccountThenClickRoutingTab();
		logger.info("---------Verifying Help Part-------------------");
	
		logger.info("Click Help");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='nav']/li[2]/span/i"), driver, timeout);
		Thread.sleep(3000);
		boolean result = verifyTextEqualByXPath("Routing_Advanced_HelpPartHeader", ".//*[@id='routing_rules_form']/div[3]/div/portal-help/div/div[1]/div/div/div/div[1]", "Tab 'Routing' - Help Part");
		result = result & verifyTextEqualByXPath("Routing_Advanced_HelpPartContain", ".//*[@id='routing_rules_form']/div[3]/div/portal-help/div/div[3]/div/div/div/p", "Tab 'Routing' - Help Part contains");		
		
		logger.info("Close Help");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='nav']/li[2]/span/i"), driver, timeout);
		Thread.sleep(3000);
		logger.info("---------Verifying Call Screening Values-------------------");
		
		result = result & verifyTextEqualByXPath("Routing_Session_CallScreening", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[1]/div[1]", "Tab 'Routing' - Call Screening Sessions");
		result = result & verifyTextEqualByXPath("Routing_ColumnHeader_Action", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table/thead/tr/th[3]/div/div/span", "Tab 'Routing' - Call Screening Header - Action");
		result = result & verifyTextEqualByXPath("Routing_ColumnHeader_Action", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table/thead/tr/th[3]/div/div/span", "Tab 'Routing' - Call Screening Header - Action");		
				
		result = result & verifyTextEqualByXPath("Routing_ColumnHeader_Active", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table/thead/tr/th[1]/div/div/span", "Tab 'Routing' - Call Screening Header - Active");
		result = result & verifyTextEqualByXPath("Routing_ColumnHeader_Scope", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table/thead/tr/th[2]/div/div/span", "Tab 'Routing' - Call Screening Header - Scope");
		result = result & verifyTextEqualByXPath("Routing_ColumnHeader_Action", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table/thead/tr/th[3]/div/div/span", "Tab 'Routing' - Call Screening Header - Action");		
		result = result & verifyTextEqualByXPath("Routing_Scope_1", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[1]/td[2]/div/div[1]/span", "Tab 'Routing' - Call Screening Value - All");
		result = result & verifyTextEqualByXPath("Routing_Action_1", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[1]/td[3]", "Tab 'Routing' - Call Screening Header - Reject");
		result = result & verifyTextEqualByXPath("Routing_Scope_2", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[2]/td[2]/div/div[1]/span", "Tab 'Routing' - Call Screening Header - Anonymous");
		result = result & verifyTextEqualByXPath("Routing_Action_2", ".//*[@id='routing_rules_form']/div[3]/div/div/div[2]/div[3]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[2]/td[3]", "Tab 'Routing' - Call Screening Header - Reject");
		
		logger.info("---------Verifying Actions Session-------------------");
		result = result & verifyTextEqualByXPath("Routing_Session_Actions", ".//*[@id='content_div']/div/div[2]/div/div/div[1]/div/h2", "Tab 'Routing' - Action Sessions");
		result = result & verifyTextEqualByXPath("Routing_Button_SaveRoute", ".//*[@id='content_div']/div/div[2]/div/div/div[2]/div/button", "Tab 'Routing' - Button 'Save Route'");
		result = result & verifyTextEqualByXPath("Routing_Button_AdvanceMode", ".//*[@id='content_div']/div/div[2]/div/div/div[4]/div/button", "Tab 'Routing' - Button 'Advance Mode'");
		
		logger.info("---------Verifying Call Redirection Session-------------------");		
		result = result & verifyTextEqualByXPath("Routing_Session_CallRedirection", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/div[1]/div[1]", "Tab 'Routing' - Call Redirection Sessions");
		result = result & verifyTextEqualByXPath("Routing_Label_Mode", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/div[3]/div[2]/label", "Tab 'Routing' - Call Redirection Sessions - Mode");		
		result = result & verifyTextEqualByXPath("Routing_Mode_Disabled", ".//*[@id='mode']/option[@value='0']",  "Tab 'Routing' - Call Redirection Sessions - Disabled Item");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous", ".//*[@id='mode']/option[@value='1']",  "Tab 'Routing' - Call Redirection Sessions - Simultaneous Item");
		result = result & verifyTextEqualByXPath("Routing_Mode_Sequential", ".//*[@id='mode']/option[@value='2']",  "Tab 'Routing' - Call Redirection Sessions - Sequential Item");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased", ".//*[@id='mode']/option[@value='3']",  "Tab 'Routing' - Call Redirection Sessions - Presence Based Item");
		
		logger.info("Select mode: Simultaneous");
		WebElementSupport2.selectDropdownByIndex(By.xpath(".//*[@id='mode']"), driver, 1, timeout);
		Thread.sleep(2000);
		logger.info("---------Verifying Call Redirection - Simultaneous-------------------");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous_Label_NumberOfRing", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/div[4]/div[1]/label", "Tab 'Routing' - Simultaneous - Number of Rings");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous_NumberOfRing_NoLimit", ".//*[@id='no_of_rings']/option[@value='50']", "Tab 'Routing' - Simultaneous - Number of Rings - No Limit");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous_RoutingNumber", ".//*[@id='th_number']", "Tab 'Routing' - Routing Number Header");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous_MyClient", "//*[text()='1']/following::select[@id='number_types'][1]/option[1]", "Tab 'Routing' - Routing Type - My Client");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous_Other", "//*[text()='1']/following::select[@id='number_types'][1]/option[2]", "Tab 'Routing' - Routing Type - Other");		
		result = result & verifyTextContain(properties.getProperty("Routing_Mode_Simultaneous_PlaceHolder_Number"), WebElementSupport2.getAttributeSupport(By.xpath(".//*[@id='sim_number1']"), driver, "placeholder", timeout), "Tab 'Routing' - Routing Type - Number");
	
		logger.info("Select mode: Sequential");
		WebElementSupport2.selectDropdownByIndex(By.xpath(".//*[@id='mode']"), driver, 2, timeout);
		Thread.sleep(2000);
		logger.info("---------Verifying Call Redirection - Sequential-------------------");
		result = result & verifyTextEqualByXPath("Routing_Mode_Sequential_ColumnHeader_RoutingNumber", ".//*[@id='th_seq_number']", "Tab 'Routing' - Sequential - Routing Number Header");
		result = result & verifyTextEqualByXPath("Routing_Mode_Sequential_ColumnHeader_NumberOfRing", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/div[10]/div[1]/scrollable-table/div/div[2]/table/thead/tr/th[4]/div/div/span", "Tab 'Routing' - Sequential - Number of Rings Header");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous_MyClient", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/div[10]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[1]/td[1]/following::select[1]/option[1]", "Tab 'Routing' - Routing Type - My Client");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous_Other", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/div[10]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[1]/td[1]/following::select[1]/option[2]", "Tab 'Routing' - Routing Type - Other");
			
		result = result & verifyTextContain(properties.getProperty("Routing_Mode_Simultaneous_PlaceHolder_Number"), WebElementSupport2.getAttributeSupport(By.xpath(".//*[@id='seq_number1']"), driver, "placeholder", timeout), "Tab 'Routing' - Routing Type - Number");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous_NumberOfRing_NoLimit", ".//*[@id='no_of_rings0']/option[@value='50']", "Tab 'Routing' - Sequential - Number of Rings - No Limit");
		
		logger.info("Select mode: Presence Based");
		WebElementSupport2.selectDropdownByIndex(By.xpath(".//*[@id='mode']"), driver, 3, timeout);		
		Thread.sleep(2000);
		logger.info("---------Verifying Call Redirection - Presence Based-------------------");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_Label_NumberOfRing", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/div[4]/div[1]/label", "Tab 'Routing' - Simultaneous - Number of Rings");
		result = result & verifyTextEqualByXPath("Routing_Mode_Simultaneous_NumberOfRing_NoLimit", ".//*[@id='no_of_rings']/option[@value='50']", "Tab 'Routing' - Presence Based - Number of Rings - No Limit");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_Label_RoutingNumber", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/custom-element/div[1]/div[1]/label", "Tab 'Routing' - Presence Based - Routing Number");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_Label_RedirectIf", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/custom-element/div[2]/div[1]/label", "Tab 'Routing' - Presence Based - Redirect If");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_RedirectIf_ActiveOnPhone", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/custom-element/div[2]/div[2]/span[2]", "Tab 'Routing' - Presence Based - Redirect If Active On Phone");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_RedirectIf_UnavailableBusy", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/custom-element/div[3]/div[2]/span[2]", "Tab 'Routing' - Presence Based - Redirect If Unavailable Busy");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_RedirectIf_UnavailableOffline", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/custom-element/div[4]/div[2]/span[2]", "Tab 'Routing' - Presence Based - Redirect If Unavailable Offline");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_RedirectIf_UnavailableOnVacation", ".//*[@id='routing_rules_form']/div[3]/div/div/div[3]/custom-element/div[5]/div[2]/span[2]", "Tab 'Routing' - Presence Based - Redirect If Unavailable On Vacation");
		
		logger.info("Select mode: Disabled");
		WebElementSupport2.selectDropdownByIndex(By.xpath(".//*[@id='mode']"), driver, 0, timeout);
		Thread.sleep(2000);
		logger.info("Click button 'SAVE ROUTE'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div/div[2]/div/div/div[2]/div/button"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		
		result = result & verifyTextEqualByXPath("Routing_Success_Message", ".//*[@id='routing_rules_form']/div[2]/div[1]", "Tab 'Routing' - Save Route Successful");
		
		result = result & verifyRoutingTab_Advanced();
		
		return result;
	}
	
	public boolean verifyRoutingTab_Advanced() throws Exception{
		boolean result = true;
		PageMenuEUP menuEUP = new PageMenuEUP(driver, timeout);
		logger.info("EUP - Click 'Account' Then Click 'Routing' Tab");
		menuEUP.clickAccountThenClickRoutingTab();
		logger.info("---------go to ADVANCED MODE-------------------");
		logger.info("Click button 'ADVANCED MODE'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div/div[2]/div/div/div[4]/div/button"), driver, timeout);
		
		result = result & verifyTextEqualByXPath("Routing_Button_Yes", ".//*[@id='content_div']/div/div[2]/div/div/div[5]/div/confirm-alert-box/div/div/div[2]/div[2]/div/button", "Tab 'Routing' - Advanced Mode - Button YES");
		result = result & verifyTextEqualByXPath("Routing_Button_No", ".//*[@id='content_div']/div/div[2]/div/div/div[5]/div/confirm-alert-box/div/div/div[3]/div[2]/div/button", "Tab 'Routing' - Advanced Mode - Button NO");
		logger.info("Click button 'YES'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div/div[2]/div/div/div[5]/div/confirm-alert-box/div/div/div[2]/div[2]/div/button"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		Thread.sleep(3000);
		
		result = result & verifyTextEqualByXPath("Routing_Button_AddRoute", ".//*[@id='add_route']", "Tab 'Routing' - Advanced Mode - Button ADD ROUTE");
		result = result & verifyTextEqualByXPath("Routing_Button_BasicRoute", "//*[@id='content_div']/div/div[2]/div/div/div[5]/div/button", "Tab 'Routing' - Advanced Mode - Button BASIC MODE");
		result = result & verifyTextEqualByXPath("Routing_Advanced_Header", ".//*[@id='content_div']/div/div[1]/div[3]/div/div/div/div[1]", "Tab 'Routing' - Advanced Mode - Header");
		result = result & verifyTextEqualByXPath("Routing_Advanced_NoRoute", ".//*[@id='content_div']/div/div[1]/div[6]/div[2]", "Tab 'Routing' - Advanced Mode - No Route label");
		logger.info("Click button 'ADD ROUTE'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='add_route']"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		
		logger.info("---------verifying NEW ROUTE-------------------");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Header", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[1]/div/div[1]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - Header");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Session_Action_SaveRoute", ".//*[@id='content_div']/div/div[2]/div/div/div[3]/div/button[1]", "Tab 'Routing' - Advanced Mode - New Route - Button 'SAVE ROUTE'");
		result = result & verifyTextEqualByXPath("Common_ReturnToList", ".//*[@id='content_div']/div/div[2]/div/div/div[5]/div/button", "Tab 'Routing' - Advanced Mode - New Route - Button 'RETURN TO LIST'");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Label_RouteName", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[1]/div/div[3]/div[1]/label", "Tab 'Routing' - Advanced Mode - New Route - Header");
		
		logger.info("---------verifying WHEN A CALL IS RECEIVED-------------------");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_WhenACallIsReceived", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[1]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - 'WHEN A CALL IS RECEIVED'");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromThesePEOPLEFromMyPersonal", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[1]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE PEOPLE from my Personal Address Book or..'");
		logger.info("Click 'THESE PEOPLE' of 'From THESE PEOPLE from my Personal Address Book or..'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[1]/td[2]/span/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromThesePEOPLEFromMyPersonal_AddressBook_Header", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[1]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE PEOPLE from my Personal Address Book or..' - 'PERSONAL ADDRESS BOOK'");
		//result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromThesePEOPLEFromMyPersonal_AddressBook_NoContact", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div[2]", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE PEOPLE from my Personal Address Book or..' - 'No Contacts'");
		
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromThesePEOPLEFromMyCompany", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[2]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE PEOPLE from my Company Address Book or..'");
		logger.info("Click 'THESE PEOPLE' of 'From THESE PEOPLE from my Company Address Book or..'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[2]/td[2]/span/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromThesePEOPLEFromMyCompany_AddressBook_Header", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[1]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE PEOPLE from my Company Address Book or..' - 'COMPANY ADDRESS BOOK'");
		//result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromThesePEOPLEFromMyCompany_AddressBook_NoContact", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div[2]/p", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE PEOPLE from my Company Address Book or..' - 'Use search to find entries'");
		
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseGROUPSFromMyPersonal", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[3]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE GROUPS from my Personal Address Book or..'");
		logger.info("Click 'THESE GROUPS' of 'From THESE GROUPS from my Personal Address Book or..'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[3]/td[2]/span/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseGROUPSFromMyPersonal_AddressBook_Header", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[1]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE GROUPS from my Personal Address Book or..' - 'GROUPS'");
		//result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseGROUPSFromMyPersonal_AddressBook_NoContact", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div[2]", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE GROUPS from my Personal Address Book or..' - 'No Groups'");
				
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseNUMBERS", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[4]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE NUMBERS or..'");
		logger.info("Click 'THESE NUMBERS' of 'From THESE NUMBERS or..'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[4]/td[2]/span/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseNUMBERS_Header", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[1]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE NUMBERS or..' - Header");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseNUMBERS_MyClient", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/table/tbody/tr[1]/td[2]", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE NUMBERS or..' - My Client");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseNUMBERS_OfficeNumber", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/table/tbody/tr[2]/td[2]", "Tab 'Routing' - Advanced Mode - New Route - 'From THESE NUMBERS or..' - My primary office number");
		
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromAnonymous", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[5]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - 'From Anonymous or..'");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_MyPresenceIs", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[6]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - 'My presence is in THESE STATES or..'");
		logger.info("Click 'THESE STATES' of 'My presence is in THESE STATES or..'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[6]/td[2]/span/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_MyPresenceIs_Header", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[1]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - 'My presence is in THESE STATES or..' - Header");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_RedirectIf_ActiveOnPhone", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/div[1]/div/span[2]", "Tab 'Routing' - Advanced Mode - New Route - 'My presence is in THESE STATES or..' - 'Active On Phone'");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_RedirectIf_UnavailableBusy", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/div[2]/div/span[2]", "Tab 'Routing' - Advanced Mode - New Route - 'My presence is in THESE STATES or..' - 'Unavailable Busy'");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_RedirectIf_UnavailableOffline", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/div[3]/div/span[2]", "Tab 'Routing' - Advanced Mode - New Route - 'My presence is in THESE STATES or..' - 'Unavailable Offline'");
		result = result & verifyTextEqualByXPath("Routing_Mode_PresenceBased_RedirectIf_UnavailableOnVacation", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/div[4]/div/span[2]", "Tab 'Routing' - Advanced Mode - New Route - 'My presence is in THESE STATES or..' - 'Unavailable On Vacation'");
		
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_ReceivedAt", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[7]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - 'Received at THESE TIMES'");
		logger.info("Click 'THESE TIMES' of 'Received at THESE TIMES'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[7]/td[2]/span/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_ReceivedAt_Header", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[1]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - 'Received at THESE TIMES'- Header");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_ReceivedAt_TableHeader", ".//*[@id='name']", "Tab 'Routing' - Advanced Mode - New Route - 'Received at THESE TIMES' - Table header");
		
		logger.info("---------verifying ROUTE-------------------");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[4]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - Route");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[1]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - Ring These Numbers");
		logger.info("Click 'THESE NUMBERS' of 'Ring THESE NUMBERS'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[1]/td[2]/span/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseNUMBERS_Header", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[1]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - Route - 'Ring THESE NUMBERS or..' - Header");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseNUMBERS_MyClient", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/table/tbody/tr[1]/td[2]", "Tab 'Routing' - Advanced Mode - New Route - Route - 'Ring THESE NUMBERS or..' - My Client");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_FromTheseNUMBERS_OfficeNumber", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/table/tbody/tr[2]/td[2]", "Tab 'Routing' - Advanced Mode - New Route - Route - 'Ring THESE NUMBERS or..' - My primary office number");
		
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_IfBusy", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[8]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - Route - If busy ring THESE NUMBERS");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_OneRing", ".//*[@id='no_of_times0']/option[@value='0']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - One Ring");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_TwoRing", ".//*[@id='no_of_times0']/option[@value='1']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Two Rings");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_ThreeRing", ".//*[@id='no_of_times0']/option[@value='2']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Three Rings");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_FourRing", ".//*[@id='no_of_times0']/option[@value='3']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Four Rings");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_FiveRing", ".//*[@id='no_of_times0']/option[@value='4']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Five Rings");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_SixRing", ".//*[@id='no_of_times0']/option[@value='5']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Six Rings");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_SevenRing", ".//*[@id='no_of_times0']/option[@value='6']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Seven Rings");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_EightRing", ".//*[@id='no_of_times0']/option[@value='7']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Eight Rings");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_NineRing", ".//*[@id='no_of_times0']/option[@value='8']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Nine Rings");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_Forever", ".//*[@id='no_of_times0']/option[@value='9']", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Forever");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RingTheseNumber_Then", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[1]/td[4]/span", "Tab 'Routing' - Advanced Mode - New Route - Route - Ring Number - Then");
		
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_IfNoAnswer", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[9]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - Route - If no answer ring THESE NUMBERS");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_IfUnreachable", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[10]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - Route - If unreachable ring THESE NUMBERS");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Route_RejectTheCall", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[11]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - Route - Reject the call");
		
		logger.info("---------verifying UNLESS-------------------");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Unless", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[7]/div[1]", "Tab 'Routing' - Advanced Mode - New Route - UNLESS");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Unless_FromThesePEOPLEFromMyPersonal", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[9]/div/table/tbody/tr[1]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - UNLESS - From THESE PEOPLE from my Personal Address Book or..");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Unless_FromThesePEOPLEFromMyCompany", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[9]/div/table/tbody/tr[2]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - UNLESS - From THESE PEOPLE from my Company Address Book or..");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Unless_FromTheseGROUPFromMyPersonal", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[9]/div/table/tbody/tr[3]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - UNLESS - From THESE GROUPS from my Personal Address Book or..");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Unless_FromTheseNUMBERS", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[9]/div/table/tbody/tr[4]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - UNLESS - From THESE NUMBERS or..");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Unless_FromAnonymous", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[9]/div/table/tbody/tr[5]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - UNLESS - From Anonymous or.. ");
		result = result & verifyTextEqualByXPath("Routing_Advanced_AddRoute_Unless_MyPresence", ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[9]/div/table/tbody/tr[6]/td[2]/span", "Tab 'Routing' - Advanced Mode - New Route - UNLESS - My presence is in THESE STATES");
				
		return result;
	}
	
	public boolean verifyABTab(String fileLocation) throws Exception{
		String user = "testuser" + System.getProperty("currentLanguage","English");
		String groupName = "AT Test Group";
		logger.info("+++++++++++++++++++Verifying contain of Tab Adrress Book +++++++++++++++++++");
		PageMenuEUP menuEUP = new PageMenuEUP(driver, timeout);
		logger.info("EUP - Click 'Account' Then Click 'Address Book' Tab");
		menuEUP.clickAccountThenClickAddressBookTab();
		logger.info("---------Verifying Help Part-------------------");		
		logger.info("Click Help");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='nav']/li[2]/span/i"), driver, timeout);
		Thread.sleep(3000);
		boolean result = verifyTextEqualByXPath("AB_HelpHeader", ".//*[@id='content_div']/div[1]/div[1]/div[3]/div/portal-help/div/div[1]/div/div/div/div[1]", "Tab 'Address Book' - Help Part");
		result = result & verifyTextEqualByXPath("AB_HelpContain", ".//*[@id='content_div']/div[1]/div[1]/div[3]/div/portal-help/div/div[3]/div/div/div/p", "Tab 'Address Book' - Help Part contains");
		
		logger.info("Close Help");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='nav']/li[2]/span/i"), driver, timeout);
		Thread.sleep(3000);
		
		result = result & verifyTextEqualByXPath("AB_Header", ".//*[@id='content_div']/div[1]/div[1]/div[3]/div/div/div/div[1]", "Address Book - Header");
		//result = result & verifyTextEqualByXPath("AB_Header_NoContact", ".//*[@id='content_div']/div[1]/div[1]/div[5]/div[2]/div/div", "Address Book - No contact");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_FirstName", ".//span[@id='first_name']", "Address Book - Table - Column First Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_LastName", ".//span[@id='last_name']", "Address Book - Table - Column Last Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_SIPAddress", ".//span[@id='sip_address']", "Address Book - Table - Column SIP Address");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_Group", ".//span[@id='group']", "Address Book - Table - Column Group");
		
		logger.info("---------Verifying Session Buttons-------------------");
		result = result & verifyTextEqualByXPath("AB_Button_SearchCompanyList", ".//*[@id='company_contacts']", "Address Book - Search Company List");
		result = result & verifyTextEqualByXPath("AB_Button_ManageGroups", ".//*[@id='manage_groups']", "Address Book - Manage Groups");
		result = result & verifyTextEqualByXPath("AB_Button_ManageSelf", "//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/div[6]/div/a", "Address Book - Manage Self");
		result = result & verifyTextEqualByXPath("AB_Button_AddContact", "//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/cust_div[1]/div[2]/div/a", "Address Book - Add Contact");
		result = result & verifyTextEqualByXPath("AB_Button_AddFromFile", "//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/cust_div[1]/div[4]/div/a", "Address Book - Add From File");
		result = result & verifyTextEqualByXPath("AB_Session_Actions", "//*[@id='content_div']/div[1]/div[2]/div/div/div/div/h2", "Address Book - Actions");
		result = result & verifyTextEqualByXPath("AB_Session_Actions_Explain", "//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/div[10]/div/p/span", "Address Book - Actions descriptions");
		
		logger.info("---------Verifying Add From File-------------------");
		logger.info("Click button 'ADD FROM FILE'");
		WebElementSupport2.clickSupport(By.xpath("//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/cust_div[1]/div[4]/div/a"), driver, timeout);
		Thread.sleep(3000);		
		result = result & verifyTextEqualByXPath("AB_AddFromFile_Label_Description", ".//*[@id='uploadImageModal']/div/div/div[1]/div[1]/div[4]/div", "Address Book - Upload file - description");
		result = result & verifyTextEqualByXPath("AB_AddFromFile_Label_FileUpLoad", ".//*[@id='uploadImageModal']/div/div/div[1]/div[1]/div[2]/div", "Address Book - Upload file - Label");
		result = result & verifyTextEqualByXPath("AB_AddFromFile_Button_Select", ".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[2]/div/div/span[1]", "Address Book - Upload file - Button Select");
		result = result & verifyTextEqualByXPath("AB_AddFromFile_Button_Upload", ".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[1]/button", "Address Book - Upload file - Button Upload");
		result = result & verifyTextEqualByXPath("AB_AddFromFile_Button_Cancel", ".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[2]/button", "Address Book - Upload file - Button Cancel");
		
		logger.info("Click button 'UPLOAD'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[1]/button"), driver, timeout);
		Thread.sleep(3000);
		result = result & verifyTextEqualByXPath("AB_AddFromFile_Error_SelectAFile", ".//*[@id='uploadImageModal']/div/div/div[2]/div/p", "Address Book - Upload file - Error no file");
		
		logger.info("Upload file '"+fileLocation+"'");
		WebElementSupport2.uploadFileByID(driver, "file_upload", fileLocation);		
		logger.info("Click button 'UPLOAD'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[1]/button"), driver, timeout);
		WebUtil.waitForUploadFinished(driver, timeout);
		Thread.sleep(3000);
		result = result & verifyTextEqualByXPath("AB_AddFromFile_Error_SelectACSVFile", ".//*[@id='uploadImageModal']/div/div/div[2]/div/p", "Address Book - Upload file - Error select CSV file");		
		logger.info("Click button 'CANCEL'");		
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[2]/button"), driver, timeout);		
		Thread.sleep(3000);
		
		
		logger.info("---------Verifying Update Contact-------------------");
		logger.info("Check off first Address book");		
		WebElementSupport2.checkOffCheckboxInTableByTableID(driver, "personal_tbody", "user", -2);
		result = result & verifyTextEqualByXPath("AB_Button_RemoveContact", "//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/cust_div[1]/cust_div/div[2]/div/a", "Address Book - Remove Contact");
		logger.info("Select the first user on Table");
		WebElementSupport2.clickOnElementInTableByTableID(driver, "personal_tbody", "user", "/div/a", -1);
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_FirstName", ".//*[@id='contacts_form']/div[6]/div/div/div[1]/div[1]/label", "Address Book - Add Contact - First Name");
		result = result & verifyTextContain(properties.getProperty("AB_AddContact_TextBox_FirstName"), WebElementSupport2.getAttributeSupport(By.xpath(".//*[@id='first_name']"), driver, "placeholder", timeout), "Address Book - Add Contact - First Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_LastName", ".//*[@id='contacts_form']/div[6]/div/div/div[2]/div[1]/label", "Address Book - Add Contact - Last Name");
		result = result & verifyTextContain(properties.getProperty("AB_AddContact_TextBox_LastName"), WebElementSupport2.getAttributeSupport(By.xpath(".//*[@id='last_name']"), driver, "placeholder", timeout), "Address Book - Add Contact - Last Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_NickName", ".//*[@id='contacts_form']/div[6]/div/div/div[3]/div[1]/label", "Address Book - Add Contact - Nick name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_SIPAddress", ".//*[@id='contacts_form']/div[6]/div/div/div[4]/div[1]/label", "Address Book - Add Contact - SIP Address");		
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_BussinessPhone", ".//*[@id='contacts_form']/div[6]/div/div/div[5]/div[1]/label", "Address Book - Add Contact - Business Phone");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_MobilePhone", ".//*[@id='contacts_form']/div[6]/div/div/div[6]/div[1]/label", "Address Book - Add Contact - Mobile Phone");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_HomePhone", ".//*[@id='contacts_form']/div[6]/div/div/div[7]/div[1]/label", "Address Book - Add Contact - Home Phone");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_EMail", ".//*[@id='contacts_form']/div[6]/div/div/div[8]/div[1]/label", "Address Book - Add Contact - E-mail");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_Group", ".//*[@id='contacts_form']/div[6]/div/div/div[9]/div[1]/label", "Address Book - Add Contact - Group");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_ShowPresence", ".//*[@id='contacts_form']/div[6]/div/div/div[10]/div[1]/label", "Address Book - Add Contact - Show Presence");
		result = result & verifyTextEqualByXPath("Common_Item_Disabled", ".//*[@id='show_presence']/option[@value='1']", "Address Book - Add Contact - Show Presence Items - Disabled");
		result = result & verifyTextEqualByXPath("Common_Item_Enabled", ".//*[@id='show_presence']/option[@value='0']", "Address Book - Add Contact - Show Presence Items - Enabled");
		result = result & verifyTextEqualByXPath("AB_AddContact_Button_SaveContact", ".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[2]/div/a", "Address Book - Add Contact - button 'Save Contact'");
		
		logger.info("Click button 'Save Contact'");		
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[2]/div/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("AB_AddContact_SaveSuccess", ".//*[@id='content_div']/div[1]/div[1]/div[2]/div[1]", "Address Book - Add Contact - Update Contact Success");		
		
		logger.info("---------Verifying Add Contact-------------------");
		logger.info("Click button 'ADD CONTACT'");		
		WebElementSupport2.clickSupport(By.xpath("//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/cust_div[1]/div[2]/div/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_FirstName", ".//*[@id='contacts_form']/div[6]/div/div/div[1]/div[1]/label", "Address Book - Add Contact - First Name");
		result = result & verifyTextContain(properties.getProperty("AB_AddContact_TextBox_FirstName"), WebElementSupport2.getAttributeSupport(By.xpath(".//*[@id='first_name']"), driver, "placeholder", timeout), "Address Book - Add Contact - First Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_LastName", ".//*[@id='contacts_form']/div[6]/div/div/div[2]/div[1]/label", "Address Book - Add Contact - Last Name");
		result = result & verifyTextContain(properties.getProperty("AB_AddContact_TextBox_LastName"), WebElementSupport2.getAttributeSupport(By.xpath(".//*[@id='last_name']"), driver, "placeholder", timeout), "Address Book - Add Contact - Last Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_NickName", ".//*[@id='contacts_form']/div[6]/div/div/div[3]/div[1]/label", "Address Book - Add Contact - Nick name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_SIPAddress", ".//*[@id='contacts_form']/div[6]/div/div/div[4]/div[1]/label", "Address Book - Add Contact - SIP Address");		
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_BussinessPhone", ".//*[@id='contacts_form']/div[6]/div/div/div[5]/div[1]/label", "Address Book - Add Contact - Business Phone");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_MobilePhone", ".//*[@id='contacts_form']/div[6]/div/div/div[6]/div[1]/label", "Address Book - Add Contact - Mobile Phone");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_HomePhone", ".//*[@id='contacts_form']/div[6]/div/div/div[7]/div[1]/label", "Address Book - Add Contact - Home Phone");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_EMail", ".//*[@id='contacts_form']/div[6]/div/div/div[8]/div[1]/label", "Address Book - Add Contact - E-mail");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_Group", ".//*[@id='contacts_form']/div[6]/div/div/div[9]/div[1]/label", "Address Book - Add Contact - Group");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_ShowPresence", ".//*[@id='contacts_form']/div[6]/div/div/div[10]/div[1]/label", "Address Book - Add Contact - Show Presence");
		result = result & verifyTextEqualByXPath("Common_Item_Disabled", ".//*[@id='show_presence']/option[@value='1']", "Address Book - Add Contact - Show Presence Items - Disabled");
		result = result & verifyTextEqualByXPath("Common_Item_Enabled", ".//*[@id='show_presence']/option[@value='0']", "Address Book - Add Contact - Show Presence Items - Enabled");
		result = result & verifyTextEqualByXPath("AB_AddContact_Button_SaveContact", ".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[2]/div/a", "Address Book - Add Contact - button 'Save Contact'");
		
		logger.info("input value for 'New Contact'");
		logger.info("Click button 'Save Contact' when First Name is Empty");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[2]/div/a"), driver, timeout);
		Thread.sleep(2000);
		result = result & verifyTextEqualByXPath("AB_AddContact_NoFirstName", ".//*[@id='contacts_form']/div[2]/div[1]", "Address Book - Add Contact - no First Name");
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='first_name']"), driver, user, timeout);
		
		logger.info("Click button 'Save Contact' when Last Name is Empty");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[2]/div/a"), driver, timeout);
		Thread.sleep(2000);
		result = result & verifyTextEqualByXPath("AB_AddContact_NoLastName", ".//*[@id='contacts_form']/div[2]/div[1]", "Address Book - Add Contact - no Last Name");
		
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='last_name']"), driver, "0000", timeout);
		
		logger.info("Click button 'Save Contact' when Nick Name is Empty");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[2]/div/a"), driver, timeout);
		Thread.sleep(2000);
		result = result & verifyTextEqualByXPath("AB_AddContact_NoNickName", ".//*[@id='contacts_form']/div[2]/div[1]", "Address Book - Add Contact - no Nick Name");
		
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='nick_name']"), driver, "user", timeout);
		
		logger.info("Click button 'Save Contact' when SIP Address is Empty");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[2]/div/a"), driver, timeout);
		Thread.sleep(2000);
		result = result & verifyTextEqualByXPath("AB_AddContact_NoSIP", ".//*[@id='contacts_form']/div[2]/div[1]", "Address Book - Add Contact - no SIP Address");
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='sip_address']"), driver, "abc@amc", timeout);
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='business_phone']"), driver, "09484837373", timeout);
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='mobile_phone']"), driver, "94894838373", timeout);
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='home_phone']"), driver, "94834783733", timeout);
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='email']"), driver, "anc@gmail.com", timeout);
		
		logger.info("Click button 'Save Contact' when filled all Info");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[2]/div/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);		
		result = result & verifyTextEqualByXPath("AB_AddContact_CreateSuccess", ".//*[@id='content_div']/div[1]/div[1]/div[2]/div[1]", "Address Book - Add Contact Success");

		try{
			logger.info("---------Verifying Remove Contact-------------------");
			logger.info("Check off user '"+user+"' Address book");
			WebElementSupport2.checkOffCheckboxInTableByTableID(driver, "personal_tbody", user, -1);
			logger.info("Click button 'Remove Contact'");
			WebElementSupport2.clickSupport(By.xpath("//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/cust_div[1]/cust_div/div[2]/div/a"), driver, timeout);
			result = result & verifyTextEqualByXPath("Routing_Button_Yes", ".//*[@id='content_div']/div[1]/div[2]/div/div/div[2]/div/confirm-alert-box/div/div/div[2]/div[2]/div/button", "Address Book - Remove Contact YES");
			result = result & verifyTextEqualByXPath("Routing_Button_No", ".//*[@id='content_div']/div[1]/div[2]/div/div/div[2]/div/confirm-alert-box/div/div/div[3]/div[2]/div/button", "Address Book - Remove Contact NO");			
			logger.info("Click button 'YES'");
			WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[2]/div/div/div[2]/div/confirm-alert-box/div/div/div[2]/div[2]/div/button"), driver, timeout);
			WebUtil.waitFor_LoadingFinished(driver, timeout);
			result = result & verifyTextEqualByXPath("AB_AddContact_RemoveSuccess", ".//*[@id='content_div']/div[1]/div[1]/div[2]/div[1]", "Address Book - Remove Contact Success");
		} catch(Exception e){			
		}
		
		logger.info("---------Verifying Search Company List-------------------");
		logger.info("Click 'Search Company List'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='company_contacts']"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
				
		result = result & verifyTextEqualByXPath("AB_CompanyList_Header", ".//*[@id='content_div']/div[1]/div[1]/div[3]/div/div/div/div[1]", "Address Book - Company Contact - Header");
		result = result & verifyTextEqualByXPath("AB_CompanyList_SearchTitle", ".//*[@id='content_div']/div[1]/div[1]/div[5]/div[2]/div/div/p", "Address Book - Company Contact - Description");
		result = result & verifyTextEqualByXPath("AB_CompanyList_Button_ViewPersonalList", ".//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/div[2]/div/a", "Address Book - Company Contact - View Personal List");
		logger.info("Search with value 'u'");
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='search_text']"), driver, "u", timeout);
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='search_icon']"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_FirstName", ".//span[@id='first_name']", "Address Book - Company Contact - Table Header - First Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_LastName", ".//span[@id='last_name']", "Address Book - Company Contact - Table Header - Last Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_SIPAddress", ".//span[@id='sip_address']", "Address Book - Company Contact - Table Header - SIP Address");
				
		logger.info("Click button 'View Personal List'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/div[2]/div/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		logger.info("---------Verifying Manage Groups-------------------");
		logger.info("Click 'Manage Groups'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='manage_groups']"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		
		result = result & verifyTextEqualByXPath("AB_MangageGroup_Header", ".//*[@id='content_div']/div[1]/div[1]/div[3]/div/div/div/div[1]", "Address Book - Manage Groups - Header");
		result = result & verifyTextEqualByXPath("AB_MangageGroup_AddGroup_Name", ".//span[@id='group_name']", "Address Book - Manage Groups - Table Header - Name");
		result = result & verifyTextEqualByXPath("AB_MangageGroup_AddGroup_Members", ".//span[@id='members']", "Address Book - Manage Groups - Table Header - Members");
		result = result & verifyTextEqualByXPath("AB_MangageGroup_ActionDescription", ".//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/div[8]/div/p/span", "Address Book - Manage Groups - Action Description");
		result = result & verifyTextEqualByXPath("AB_MangageGroup_ButtonAddGroup", ".//*[@id='add_group']", "Address Book - Manage Groups - Button Add Group");
		result = result & verifyTextEqualByXPath("AB_Button_SearchCompanyList", ".//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/div[2]/div/a", "Address Book - Manage Groups - Button Search Company List");
		result = result & verifyTextEqualByXPath("AB_CompanyList_Button_ViewPersonalList", ".//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/div[4]/div/a", "Address Book - Manage Groups - Button View Personal List");
		
		logger.info("Click button 'ADD GROUP'");
		WebElementSupport2.clickSupport(By.xpath("//*[@id='add_group']"), driver, timeout);
		Thread.sleep(1000);		
		logger.info("Click Save Group Icon - When no Group Name");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='group_inline_add']/td[3]/div/i[1]"), driver, timeout);
		result = result & verifyTextEqualByXPath("AB_MangageGroup_AddGroup_NoGroupName", ".//*[@id='content_div']/div[1]/div[1]/div[2]/div[1]", "Address Book - Manage Groups - Create Group - No Group Name");
		logger.info("Input Group Name '"+groupName+"'");
		WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='group_inline_add']/td[1]/div/span/input"), driver, groupName, timeout);
		
		logger.info("Click Save Group Icon");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='group_inline_add']/td[3]/div/i[1]"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		if(verifyTextEqualByXPath("AB_MangageGroup_AddGroup_AddSuccess", ".//*[@id='content_div']/div[1]/div[1]/div[2]/div[1]", "Address Book - Manage Groups - Create Group Success")){
			result = result & true;
			logger.info("Search Group '"+groupName+"'");
			WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='search_text']"), driver, groupName, timeout);
			WebElementSupport2.clickSupport(By.xpath(".//*[@id='search_icon']"), driver, timeout);
			WebUtil.waitFor_LoadingFinished(driver, timeout);			
			logger.info("Click Edit Icon");
			WebElementSupport2.clickSupport(By.xpath(".//*[@id='group_r0_edit']/i[1]"), driver, timeout);
			Thread.sleep(1000);
			logger.info("Click Save Icon");
			WebElementSupport2.clickSupport(By.xpath(".//*[@id='group_r0_update']/i[1]"), driver, timeout);
			WebUtil.waitFor_LoadingFinished(driver, timeout);
			result = result & verifyTextEqualByXPath("AB_MangageGroup_AddGroup_ModifySuccess", ".//*[@id='content_div']/div[1]/div[1]/div[2]/div[1]", "Address Book - Manage Groups - Modify Group Success");
			
			logger.info("Search Group '"+groupName+"'");
			WebElementSupport2.sendKeysSupport(By.xpath(".//*[@id='search_text']"), driver, groupName, timeout);
			WebElementSupport2.clickSupport(By.xpath(".//*[@id='search_icon']"), driver, timeout);
			WebUtil.waitFor_LoadingFinished(driver, timeout);			
			logger.info("Click Delete Icon");
			WebElementSupport2.clickSupport(By.xpath(".//*[@id='group_r0_edit']/i[2]"), driver, timeout);
			result = result & verifyTextEqualByXPath("Routing_Button_Yes", ".//*[@id='content_div']/div[1]/div[2]/div/div/div[2]/div/confirm-alert-box/div/div/div[2]/div[2]/div/button", "Address Book - Remove Contact YES");
			result = result & verifyTextEqualByXPath("Routing_Button_No", ".//*[@id='content_div']/div[1]/div[2]/div/div/div[2]/div/confirm-alert-box/div/div/div[3]/div[2]/div/button", "Address Book - Remove Contact NO");		
			logger.info("Click YES button");
			WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[2]/div/div/div[2]/div/confirm-alert-box/div/div/div[2]/div[2]/div/button"), driver, timeout);
			WebUtil.waitFor_LoadingFinished(driver, timeout);
			result = result & verifyTextEqualByXPath("AB_MangageGroup_AddGroup_RemoveSuccess", ".//*[@id='content_div']/div[1]/div[1]/div[2]/div[1]", "Address Book - Remove Group Success");
		} else{
			result = false;
		}
		logger.info("Click button 'View Personal List' to return Address Book Tab");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/div[4]/div/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		
		logger.info("---------Verifying Manage Self-------------------");
		logger.info("Click button 'Manage Self'");
		WebElementSupport2.clickSupport(By.xpath("//*[@id='content_div']/div[1]/div[2]/div/div/custom-element[1]/div[6]/div/a"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		
		result = result & verifyTextEqualByXPath("AB_ManageSelf_Header", ".//*[@id='contacts_form']/div[3]/div/div/div/div[1]", "Address Book - Manage Self - Header");
		result = result & verifyTextEqualByXPath("AB_ManageSelf_Button_SaveContact", ".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[2]/div/a", "Address Book - Manage Self - Button Save Contact");
		result = result & verifyTextEqualByXPath("AB_ManageSelf_Button_AddPicture", ".//*[@id='add_picture']", "Address Book - Manage Self - Button Add Picture");
		result = result & verifyTextEqualByXPath("Common_ReturnToList", ".//*[@id='content_div']/div[1]/div[3]/div/div/custom-element/div[4]/div/a", "Address Book - Manage Self - Button Return To List");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_FirstName", ".//*[@id='contacts_form']/div[6]/div/div/div[1]/div[1]/label", "Address Book - Manage Self - First Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_LastName", ".//*[@id='contacts_form']/div[6]/div/div/div[2]/div[1]/label", "Address Book - Manage Self - Last Name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_NickName", ".//*[@id='contacts_form']/div[6]/div/div/div[3]/div[1]/label", "Address Book - Manage Self - Nick name");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_BussinessPhone", ".//*[@id='contacts_form']/div[6]/div/div/div[4]/div[1]/label", "Address Book - Manage Self - Business Phone");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_MobilePhone", ".//*[@id='contacts_form']/div[6]/div/div/div[5]/div[1]/label", "Address Book - Manage Self - Mobile Phone");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_HomePhone", ".//*[@id='contacts_form']/div[6]/div/div/div[6]/div[1]/label", "Address Book - Manage Self - Home Phone");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_EMail", ".//*[@id='contacts_form']/div[6]/div/div/div[7]/div[1]/label", "Address Book - Manage Self - E-mail");
		result = result & verifyTextEqualByXPath("AB_AddContact_Label_ShowPresence", ".//*[@id='contacts_form']/div[6]/div/div/div[8]/div[1]/label", "Address Book - Manage Self - Show Presence");
		result = result & verifyTextEqualByXPath("Common_Item_Disabled", ".//*[@id='show_presence']/option[@value='1']", "Address Book - Manage Self - Show Presence Items - Disabled");
		result = result & verifyTextEqualByXPath("Common_Item_Enabled", ".//*[@id='show_presence']/option[@value='0']", "Address Book - Manage Self - Show Presence Items - Enabled");
		
		logger.info("Click button 'ADD PICTURE'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='add_picture']"), driver, timeout);
		Thread.sleep(3000);
		
		result = result & verifyTextEqualByXPath("AB_ManageSelf_AddPicture_Label_FileUpLoad", ".//*[@id='uploadImageModal']/div/div/div[1]/div[1]/div[2]/div", "Address Book - Manage Self - Upload file - Label");
		result = result & verifyTextEqualByXPath("AB_ManageSelf_AddPicture_Label_Description", ".//*[@id='uploadImageModal']/div/div/div[1]/div[1]/div[4]/div", "Address Book - Manage Self - Upload file - description");
		result = result & verifyTextEqualByXPath("AB_ManageSelf_AddPicture_Button_Select", ".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[2]/div/div/span[1]", "Address Book - Manage Self - Upload file - Button Select");
		result = result & verifyTextEqualByXPath("AB_ManageSelf_AddPicture_Button_Upload", ".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[1]/button", "Address Book - Manage Self - Upload file - Button Upload");
		result = result & verifyTextEqualByXPath("AB_ManageSelf_AddPicture_Button_Cancel", ".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[2]/button", "Address Book - Manage Self - Upload file - Button Cancel");
		
		logger.info("Click button 'UPLOAD'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[1]/button"), driver, timeout);
		Thread.sleep(3000);
		result = result & verifyTextEqualByXPath("AB_ManageSelf_AddPicture_Error_NoFile", ".//*[@id='uploadImageModal']/div/div/div[2]/div/p", "Address Book - Manage Self - Upload file - Error no file");
		
		logger.info("Upload file '"+fileLocation+"'");
		WebElementSupport2.uploadFileByID(driver, "file_upload", fileLocation);		
		logger.info("Click button 'UPLOAD'");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[1]/button"), driver, timeout);
		WebUtil.waitForUploadFinished(driver, timeout);
		Thread.sleep(3000);
		result = result & verifyTextEqualByXPath("AB_ManageSelf_AddPicture_Error_JPGFile", ".//*[@id='uploadImageModal']/div/div/div[2]/div/p", "Address Book - Manage Self - Upload file - Error select CSV file");		
		logger.info("Click button 'CANCEL'");		
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[2]/button"), driver, timeout);		
		Thread.sleep(3000);
		
		return result;
	}
	
	public boolean verifyHeader() throws Exception{
		boolean result = verifyTextContainByXPath("Common_Welcome", ".//*[@id='nav']/li[1]/a", "Header - Welcome");
		logger.info("Click Welcome");
		WebElementSupport2.clickSupport(By.xpath(".//*[@id='nav']/li[1]/a"), driver, timeout);
		Thread.sleep(3000);
		result = result & verifyTextEqualByXPath("Common_ChangePassword", ".//*[@id='class_menu']/li/div/div[1]/div[1]/a", " Header - Change Password");
		result = result & verifyTextEqualByXPath("Common_Logout", ".//*[@id='class_menu']/li/div/div[3]/div[1]/a", "Header - Log out");
		
		return result;
	}
	
	public boolean verifyTabNames() throws Exception{
		logger.info("---------Verifying Tabs Name-------------------");
		boolean result = verifyTextEqualByXPath("Tab_Service", "//*[@id='settings']/span[2]/span[1]", "Tab 'Service'");
		result = result & verifyTextEqualByXPath("Tab_Service_Define", "//*[@id='settings']/span[2]/span[2]", "Tab 'Service' Details");
		result = result & verifyTextEqualByXPath("Tab_Routing", "//*[@id='routing']/span[2]/span[1]", "Tab 'Routing'");
		result = result & verifyTextEqualByXPath("Tab_Routing_Define", "//*[@id='routing']/span[2]/span[2]", "Tab 'Routing' Details");
		
		result = result & verifyTextEqualByXPath("Tab_AddressBook", "//*[@id='address_book']/span[2]/span[1]", "Tab 'Address Book'");
		result = result & verifyTextEqualByXPath("Tab_AddressBook_Define", "//*[@id='address_book']/span[2]/span[2]", "Tab 'Address Book' Details");
		return result;
	}
	
 	boolean verifyTextContain(String expect, String value, String itemName)
	{
		//logger.info("---------Verifying :" + itemName + "---------------------");
		if(!value.contains(expect)){
			logger.info("---------Verifying : " + itemName + " : FAILED. Expect value contains '"+expect+"' . Current value '"+value+"'" );
			Reporter.log("---------Verifying : " + itemName + " : FAILED. Expect value contains '"+expect+"' . Current value '"+value+"'" );
			return false;
		} else{
			logger.info("---------Verifying : " + itemName + " : PASSED" );
			//Reporter.log("---------Verifying : " + itemName + " : PASSED" );
			return true;
		}
	}
 	
 	boolean verifyTextEqual(String expect, String value, String itemName)
	{
		//logger.info("---------Verifying :" + itemName + "---------------------");
		if(!expect.equalsIgnoreCase(value)){
			logger.info("---------Verifying : " + itemName + " : FAILED. Expect value '"+expect+"' . Current value '"+value+"'" );
			Reporter.log("---------Verifying : " + itemName + " : FAILED. Expect value '"+expect+"' . Current value '"+value+"'" );
			return false;
		} else{
			logger.info("---------Verifying : " + itemName + " : PASSED" );
			//Reporter.log("---------Verifying : " + itemName + " : PASSED" );
			return true;
		}
	}

 	boolean verifyTextEqualByXPath(String property, String xpath, String itemName) throws Exception
	{
 		try
 		{
	 		boolean result = verifyTextEqual(properties.getProperty(property), WebElementSupport2.getTextSupport(By.xpath(xpath), driver, 10), itemName);
	 		if(!result){
	 			logger.info("FAILED xpath:" + xpath);
	 			GetScreenshot.capture(System.getProperty("currentLanguage", "_") + "_" + property + "_" + itemName);
	 		} 		
	 		return result;
 		} catch(Exception e){
 			logger.info("Error xpath:" + xpath + ". Error:" + e.getMessage());
 			GetScreenshot.capture(System.getProperty("currentLanguage", "_") + "_" + property + "_" + itemName);
 			return false;
 		} 		
	}
 	
 	boolean verifyTextContainByXPath(String property, String xpath, String itemName) throws Exception
	{
 		try
 		{
	 		boolean result = verifyTextContain(properties.getProperty(property), WebElementSupport2.getTextSupport(By.xpath(xpath), driver, 10), itemName);
	 		if(!result){
	 			logger.info("FAILED xpath:" + xpath);
	 		} 		
	 		return result;
 		} catch(Exception e){
 			logger.info("Error xpath:" + xpath + ". Error:" + e.getMessage());
 			return false;
 		}
	} 	
}