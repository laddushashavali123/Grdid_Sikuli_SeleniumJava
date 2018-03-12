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

public class TabAddressBook {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("TabAddressBookEUP");
	
	@FindBy(id = "search_text")
	private WebElement searchTextbox;
	
	@FindBy(id = "search_icon")
	private WebElement searchIcon;
	
	public TabAddressBook(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public TabAddressBook clickSearchCompanyListButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("SEARCH COMPANY LIST", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabAddressBook clickManageGroupsButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("MANAGE GROUPS", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabAddressBook clickMangeSelfButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("MANAGE SELF", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabAddressBook clickAddContactButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("ADD CONTACT", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabAddressBook clickAddFromFileButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("ADD FROM FILE", driver, timeout);
		Thread.sleep(2000);
		return this;
	}
	
	public TabAddressBook clickRemoveContactButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("REMOVE CONTACT", driver, timeout);
		return this;
	}
	
	public TabAddressBook clickYesButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("YES", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabAddressBook clickNoButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("NO", driver, timeout);
		return this;
	}
	
	public TabAddressBook searchContact(String name) throws Exception {
		logger.info("Send '" + name + "' to the search textbox");
		WebElementSupport2.sendKeysSupport(searchTextbox, name, timeout);
		logger.info("Click the search icon");
		WebElementSupport2.clickSupport(searchIcon, timeout);
		return this;
	}
	
	public TabAddressBook addContact(String name, String sipAddress) throws Exception {
		logger.info("Click 'ADD CONTACT' Button");
		clickAddContactButton();
		
		FormAddContact addContactP = new FormAddContact(driver, timeout);
		logger.info("Set 'First Name': '" + name + "'");
		addContactP.setFirstName(name);
		logger.info("Set 'Last Name': '" + name + "'");
		addContactP.setLastName(name);
		logger.info("Set 'Nickname': '" + name + "'");
		addContactP.setNickname(name);
		logger.info("Set 'SIP Address': '" + sipAddress + "'");
		addContactP.setSIPAddress(sipAddress);
		logger.info("Click 'SAVE CONTACT' Button");
		addContactP.clickSaveContactButton();
		return this;
	}
	
	public TabAddressBook clickContactInTableContacts(String name) throws Exception {
		WebElementSupport2.clickOnElementInTableByTableID(driver, "personal_tbody", name, "/div/a", 0);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public TabAddressBook checkOffContactInTableContacts(String name) throws Exception {
		WebElementSupport2.checkOffCheckboxInTableByTableID(driver, "personal_tbody", name, -1);
		return this;
	}
	
	public TabAddressBook removeContact(String name) throws Exception {
		logger.info("Search Contact '" + name + "'");
		searchContact(name);
		
		logger.info("Check off Contact '" + name + "'");
		checkOffContactInTableContacts(name);
		
		logger.info("Click 'REMOVE CONTACT' Button");
		clickRemoveContactButton();
		
		logger.info("Click 'YES' Button");
		clickYesButton();
		return this;
	}
	
	public TabAddressBook clickUploadButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("UPLOAD", driver, timeout);
		WebUtil.waitForUploadFinished(driver, timeout);
		Thread.sleep(2000);
		return this;
	}
	
	public TabAddressBook uploadFile(String file) throws Exception {
		logger.info("Select file '" + file + "'");
		WebElementSupport2.uploadFileByID(driver, "file_upload", file);
		logger.info("Click 'UPLOAD' Button");
		clickUploadButton();
		return this;
	}
	
	public TabAddressBook clickConfirmImportButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("CONFIRM IMPORT ", driver, timeout);
		WebUtil.waitForUploadFinished(driver, timeout);
		return this;
	}
	
	public boolean verifyAlertAppears(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='content_div']/div[1]/div[1]/div[2]/div[1]"), driver, timeout);
			logger.info("Alert '" + alertAppear + "' appears");
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public boolean verifyContactListedInTableContacts(String value) throws Exception {
		try{
			logger.info("Search Contact : '" + value + "'");
			searchContact(value);
			String contact = WebElementSupport2.getTextFromElementInTableByTableID(driver, "personal_tbody", value, 0);
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
