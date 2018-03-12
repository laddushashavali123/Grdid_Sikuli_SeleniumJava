package nuvia.eup.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;

public class FormManageGroups {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("FormManageGroups");
	
	@FindBy(xpath = "//table[@id='group_tbody']/tbody/tr[1]/td[1]/div/span/input")
	private WebElement nameTextbox;
	
	@FindBy(xpath = ".//*[@id='group_inline_add']/td[3]/div/i[1]")
	private WebElement acceptAddGroupIcon;
	
	@FindBy(xpath = ".//*[@id='group_inline_add']/td[3]/div/i[2]")
	private WebElement cancelAddGroupIcon;
	
	@FindBy(id = "search_text")
	private WebElement searchTextbox;
	
	@FindBy(id = "search_icon")
	private WebElement searchIconbox;
	
	public FormManageGroups(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public FormManageGroups clickAddGroupButton() throws Exception {
		WebElementSupport2.clickAAccordingExactText("ADD GROUP", driver, timeout);
		Thread.sleep(1000);
		return this;
	}
	
	public FormManageGroups clickAcceptAddGroupIcon() throws Exception {
		WebElementSupport2.clickSupport(acceptAddGroupIcon, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormManageGroups clickCancelAddGroupIcon() throws Exception {
		WebElementSupport2.clickSupport(cancelAddGroupIcon, timeout);
		return this;
	}
	
	public FormManageGroups clickEditGroupContactInTableGroups(String groupName) throws Exception {
		WebElementSupport2.clickOnElementInTableByTableID(driver, "group_tbody", groupName, "/div/i[1][@event='inline_edit']", 2);
		return this;
	}
	
	public FormManageGroups clickRemoveGroupContactInTableGroups(String groupName) throws Exception {
		WebElementSupport2.clickOnElementInTableByTableID(driver, "group_tbody", groupName, "/div/i[2][@event='inline_delete']", 2);
		return this;
	}
	
	public FormManageGroups clickAcceptEditGroupContactInTableGroups(String groupName) throws Exception {
		WebElementSupport2.clickOnElementInTableByTableID(driver, "group_tbody", groupName, "/div/i[1][@event='inline_save']", 2);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormManageGroups setNameofGroupInTableGroups(String groupName, String groupNameEdit) throws Exception {
		WebElementSupport2.sendKeyToElementInTableByTableID(driver, "group_tbody", groupName, "/div/span/input", groupNameEdit, 0);
		return this;
	}
	
	public FormManageGroups clickNoButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("NO", driver, timeout);
		return this;
	}
	
	public FormManageGroups clickYesButton() throws Exception {
		WebElementSupport2.clickButtonAccordingExactText("YES", driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormManageGroups setName(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(nameTextbox, value, timeout);
		return this;
	}
	
	public FormManageGroups searchGroup(String value) throws Exception {
		logger.info("Send '" + value + "' to the search textbox" );
		WebElementSupport2.sendKeysSupport(searchTextbox, value, timeout);
		logger.info("Click the search icon");
		WebElementSupport2.clickSupport(searchIconbox, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	
	public FormManageGroups addGroupContact(String value) throws Exception {
		logger.info("Click 'ADD GROUP' Button");
		clickAddGroupButton();
		logger.info("Set Name '" + value + "'");
		setName(value);
		logger.info("Click Accept Add Group Icon");
		clickAcceptAddGroupIcon();
		return this;
	}
	
	public FormManageGroups removeGroupContact(String value) throws Exception {
		logger.info("Click Remove Group Contact of Group '" + value + "' in Table 'GROUPS' ");
		clickRemoveGroupContactInTableGroups(value);
		logger.info("Click 'YES' Icon");
		clickYesButton();
		return this;
	}
	
	public boolean verifyGroupListedInTableGroups(String value) throws Exception {
		try{
			logger.info("Search Group : '" + value + "'");
			searchGroup(value);
			String group = WebElementSupport2.getTextFromElementInTableByTableID(driver, "group_tbody", value, 0);
			if ( group.equals(value) ) {
				logger.info("Group '" + value + "' is listed in table 'GROUPS'");
				return true;
			}
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	

}
