package nuvia.eup.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;

public class TabCallLog_VoiceMail {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("TabCallLogs_VoicemailEUP");
	
	@FindBy(id = "search_text")
	private WebElement searchTextbox;
	
	@FindBy(id = "search_icon")
	private WebElement searchIcon;
	
	public TabCallLog_VoiceMail(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public void selectVoiceMailByIdx(int idx) throws Exception{
		WebElementSupport2.clickSupport(By.xpath("//*[@id='call_logs_r"+idx+"']/td[0]/input"), driver, timeout);		
	}
	
	public void selectAllVoiceMail() throws Exception{
		List<WebElement> listRows = driver.findElements(By.xpath("//*[@id='call_logs_tbody']/tbody/tr"));
		for(int i = 1; i<listRows.size();i++){//first row is header
			WebElementSupport2.clickSupport(By.xpath("//*[@id='call_logs_r"+(i-1)+"']/td[0]/input"), driver, timeout);
		}
	}
	
	public void deleteAllVoiceMail() throws Exception{
		logger.info("Select all voicemail");
		selectAllVoiceMail();
		Thread.sleep(1000);		
		logger.info("click button 'REMOVE' and confirm");
		clickButtonRemoveVoicemail();
		
		verifyAlertAppears("");
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
	
	public void clickButtonRemoveVoicemail() throws Exception{
		WebElementSupport2.clickSupport(By.xpath("//*[@id='remove_calls']"), driver, timeout);
		Thread.sleep(1000);
		WebElementSupport2.clickSupport(By.xpath("//*[@id='confirm_button']"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
	}
	
	public String getVoiceMailInfo(String headerColumn, int row) throws Exception{
		String result = "";				
		if(headerColumn.equals("Event"))
			result = WebElementSupport2.getTextSupport(By.xpath("call_logs_r"+row+"_c1"), driver, timeout);
		else if(headerColumn.equals("Time"))
			result = WebElementSupport2.getTextSupport(By.xpath("call_logs_r"+row+"_c2"), driver, timeout);
		else if(headerColumn.equals("Duration"))
			result = WebElementSupport2.getTextSupport(By.xpath("call_logs_r"+row+"_c3"), driver, timeout);
		else if(headerColumn.contains("Contact") || headerColumn.contains("Number"))
			result = WebElementSupport2.getTextSupport(By.xpath("call_logs_r"+row+"_c4"), driver, timeout);
		else if(headerColumn.equals("Status"))
			result = WebElementSupport2.getTextSupport(By.xpath("call_logs_r"+row+"_c5"), driver, timeout);
		else{
			logger.info("There is no column '"+headerColumn+"'  in this table");
		}
		return result;
	}
}
