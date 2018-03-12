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

public class FormManageSelf {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("FormManageSelf");
	
	
	@FindBy(id = "business_phone")
	private WebElement businessPhoneTextbox;
	
	@FindBy(id = "mobile_phone")
	private WebElement mobilePhoneTextbox;
	
	@FindBy(id = "home_phone")
	private WebElement homePhoneTextbox;
	
	@FindBy(id = "email")
	private WebElement emailTextbox;
	
	@FindBy(id = "show_presence")
	private WebElement showPresenceDropdown;
	
	public FormManageSelf(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public FormManageSelf setBusinessPhone(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(businessPhoneTextbox, value, timeout);
		return this;
	}
	
	public String getBusinessPhone() throws Exception {
		//return WebElementSupport2.getTextByElementID(driver, "business_phone", timeout);
		return WebElementSupport2.getAttributeSupport(businessPhoneTextbox, "value", timeout);
		
	}
	
	public FormManageSelf setMobilePhone(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(mobilePhoneTextbox, value, timeout);
		return this;
	}
	
	public String getMobilePhone() throws Exception {
		//return WebElementSupport2.getTextByElementID(driver, "mobile_phone", timeout);
		return WebElementSupport2.getAttributeSupport(mobilePhoneTextbox, "value", timeout);
	}
	
	public FormManageSelf setHomePhone(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(homePhoneTextbox, value, timeout);
		return this;
	}
	
	public String getHomePhone() throws Exception {
		//return WebElementSupport2.getTextByElementID(driver, "home_phone", timeout);
		return WebElementSupport2.getAttributeSupport(homePhoneTextbox, "value", timeout);
	}
	
	public FormManageSelf setEmail(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(emailTextbox, value, timeout);
		return this;
	}
	
	public String getEmail() throws Exception {
		//return WebElementSupport2.getTextByElementID(driver, "email", timeout);
		return WebElementSupport2.getAttributeSupport(emailTextbox, "value", timeout);
	}
	
	public FormManageSelf selectShowPresence(String value) throws Exception {
		WebElementSupport2.selectDropdown(showPresenceDropdown, value, timeout);
		return this;
	}
	
	public FormManageSelf clickReturnToListButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("RETURN TO LIST", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormManageSelf clickSaveContactButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("SAVE CONTACT", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormManageSelf clickYesButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("YES", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormManageSelf clickNoButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("NO", driver, timeout);
		return this;
	}
	
	public FormManageSelf clickAddPictureButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("ADD PICTURE", driver, timeout);
		Thread.sleep(2000);
		return this;
	}
	
	public FormManageSelf clickRemovePictureButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("REMOVE PICTURE", driver, timeout);
		return this;
	}
	
	public FormManageSelf clickUploadButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("UPLOAD", driver, timeout);
		WebUtil.waitForUploadFinished(driver, timeout);
		Thread.sleep(2000);
		return this;
	}
	
	public FormManageSelf uploadFile(String file) throws Exception {
		logger.info("Select file '" + file + "'");
		WebElementSupport2.uploadFileByID(driver, "file_upload", file);
		logger.info("Click 'UPLOAD' Button");
		clickUploadButton();
		return this;
	}
	
	public boolean verifyPictureExists() throws Exception {
		boolean result = WebElementSupport2.checkElementExist(By.xpath(".//*[@id='contacts_form']/div/div/div/div/div/img"), driver, 4);
		if (result) {
			logger.info("Picture exists!!");
		} else {
			logger.info("There is no picture");
		}
		return result;
	}
	
	public boolean verifyPictureAlertAppears(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='contacts_form']/div[2]/div[1]"), driver, timeout);
			logger.info("Alert '" + alertAppear + "' appears");
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	
}
