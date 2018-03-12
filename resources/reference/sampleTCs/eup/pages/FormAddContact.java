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

public class FormAddContact {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("FormAddContact");
	
	@FindBy(id = "first_name")
	private WebElement firstNameTextbox;
	
	@FindBy(id = "last_name")
	private WebElement lastNameTextbox;
	
	@FindBy(id = "nick_name")
	private WebElement nickNameTextbox;
	
	@FindBy(id = "sip_address")
	private WebElement sipAddressTextbox;
	
	@FindBy(id = "business_phone")
	private WebElement businessPhoneTextbox;
	
	@FindBy(id = "mobile_phone")
	private WebElement mobilePhoneTextbox;
	
	@FindBy(id = "home_phone")
	private WebElement homePhoneTextbox;
	
	@FindBy(id = "email")
	private WebElement emailTextbox;
	
	@FindBy(id = "group")
	private WebElement groupDropdown;
	
	@FindBy(id = "show_presence")
	private WebElement showPresenceDropdown;
	
	public FormAddContact(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public FormAddContact setFirstName(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(firstNameTextbox, value, timeout);
		return this;
	}
	
	public FormAddContact setLastName(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(lastNameTextbox, value, timeout);
		return this;
	}
	
	public FormAddContact setNickname(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(nickNameTextbox, value, timeout);
		return this;
	}
	
	public FormAddContact setSIPAddress(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(sipAddressTextbox, value, timeout);
		return this;
	}
	
	public FormAddContact setBusinessPhone(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(businessPhoneTextbox, value, timeout);
		return this;
	}
	
	public FormAddContact setMobilePhone(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(mobilePhoneTextbox, value, timeout);
		return this;
	}
	
	public FormAddContact setHomePhone(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(homePhoneTextbox, value, timeout);
		return this;
	}
	
	public FormAddContact setEmail(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(emailTextbox, value, timeout);
		return this;
	}
	
	public FormAddContact selectGroup(String value) throws Exception {
		WebElementSupport2.selectDropdown(groupDropdown, value, timeout);
		return this;
	}
	
	public FormAddContact selectShowPresence(String value) throws Exception {
		WebElementSupport2.selectDropdown(showPresenceDropdown, value, timeout);
		return this;
	}
	
	public String  getShowPresence() throws Exception {
		return WebElementSupport2.getSelectedItemFromDropdown(showPresenceDropdown, timeout);
	}
	
	public FormAddContact clickReturnToListButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("RETURN TO LIST", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormAddContact clickSaveContactButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("SAVE CONTACT", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormAddContact clickYesButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("YES", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormAddContact clickNoButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("NO", driver, timeout);
		return this;
	}

}
