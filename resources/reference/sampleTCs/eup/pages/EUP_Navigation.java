package nuvia.eup.pages;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;
//import automation.sublib.SubDriver;

public class EUP_Navigation {

	private WebDriver driver;
	private int timeOut = 30;
	private int interval_miliseconds = 1000;
	private final String pageLoadedText = "Please choose a Web Mail Program:";
	private final String pageUrl = "/";

	WebUtil webUtil = new WebUtil();
	
	private int minor = 1000;
	private int min = 3000;
	private int mid = 5000;
	private int max = 7000;
	
	//title New Sites
//	@FindBy(id="panel-title")
//	SubDriver subDriver = new SubDriver();
//	private WebElement ab_button_manageSelf = subDriver.findByText("MANAGE SELF", driver);
//	
//	@FindBy(xpath="//button[contains(.,'MANAGE SELF')]") private WebElement ;
//	private WebElement site_title_newSite;
	
//	html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div/div/custom-element[1]/div[6]/div/a
//	//title Subscriber Defaults
	@FindBy(xpath="html/body/div/div/div/div[2]/div/div/div/div[1]/div[2]/div/div/custom-element[1]/div[6]/div/a")
	private WebElement ab_button_manageSelf;
	
//	SubDriver sub01 = new SubDriver();
//	private WebElement ab_button_saveContact = sub01.findByText("SAVE CONTACT", driver);
	
	@FindBy(xpath="//*[contains(text(),'SAVE CONTACT')]") private WebElement ab_button_saveContact;
	@FindBy(xpath="//*[contains(text(),'ADD CONTACT')]") private WebElement ab_button_addContact;
	@FindBy(xpath="//*[contains(text(),'REMOVE CONTACT')]") private WebElement ab_button_removeContact;
	@FindBy(xpath="//*[contains(text(),'YES')]") private WebElement ab_button_confirmYes;
	@FindBy(xpath="//*[contains(text(),'ADD FROM FILE')]") private WebElement ab_button_addFromFile;
//	@FindBy(xpath="html/body/div[1]/div/div/div[2]/div/div/div/div[1]/div[2]/div/div/custom-element[1]/cust_div[1]/div[4]/div/a") private WebElement ab_button_addFromFile;
	
//	@FindBy(xpath="//*[contains(text(),'RETURN TO LIST')]") private WebElement ab_button_returnToList;
	@FindBy(xpath="//*[(text()='RETURN TO LIST')]") private WebElement ab_button_returnToList;
//	@FindBy(xpath="//*[contains(text(),'CONFIRM IMPORT ')]") private WebElement ab_button_confirmImport;
	@FindBy(xpath="//*[contains(text(),'CONFIRM IMPORT')]") private WebElement ab_button_confirmImport;
	
	@FindBy(xpath="//*[contains(text(),'MANAGE GROUPS')]") private WebElement ab_button_manageGroups;
	@FindBy(xpath="//*[contains(text(),'ADD GROUP')]") private WebElement ab_button_addGroup;
	@FindBy(xpath="//*[contains(text(),'ADD PICTURE')]") private WebElement ab_button_addPicture;
	@FindBy(xpath="//*[contains(text(),'REMOVE PICTURE')]") private WebElement ab_button_removePicture;
//	@FindBy(xpath="html/body/div[1]/div/div/div[2]/div/div/div/div/div[2]/div/div/div[4]/div/button") private WebElement rt_button_advancedMode;
	@FindBy(xpath="//*[contains(text(),'ADVANCED MODE')]") private WebElement rt_button_advancedMode;
//	@FindBy(xpath=".//*[@id='content_div']/div/div[2]/div/div/div[4]/div/button") private WebElement rt_button_advancedMode;
	
	@FindBy(xpath="//*[contains(text(),'ADD ROUTE')]") private WebElement rt_button_addRoute;
	@FindBy(xpath="//*[contains(text(),'REMOVE ROUTE')]") private WebElement rt_button_removeRoute;
	@FindBy(xpath="//*[contains(text(),'BASIC MODE')]") private WebElement rt_button_basicMode;
	@FindBy(xpath="//*[contains(text(),'SAVE ROUTE')]") private WebElement rt_button_saveRoute;
//	@FindBy(xpath=".//*[@id='content_div']/div/div[2]/div/div/div[3]/div/button[1]") private WebElement rt_button_saveRoute;
	
	@FindBy(xpath="//*[contains(text(),'SEARCH COMPANY LIST')]") private WebElement button_searchCompanyList;
	@FindBy(xpath="//*[contains(text(),'VIEW PERSONAL LIST')]") private WebElement button_viewPersonalList;
	@FindBy(xpath="//*[contains(text(),'VSC')]") private WebElement button_VSC;
	@FindBy(xpath="//*[contains(text(),'CLIENTS')]") private WebElement button_Clients;
	//@FindBy(xpath="//*[contains(text(),'SAVE SETTINGS')]") private WebElement button_saveSettings;
	@FindBy(xpath="//*[@id='content_div']/div[1]/div[3]/div/div/div[9]/div/button") private WebElement button_saveSettings;
	@FindBy(xpath="//*[contains(text(),'USER PORTAL')]") private WebElement button_userPortal;
	
//	// button MANAGE ADDRESS
//	@FindBy(id="btn-site-manage-address")
//	private WebElement site_button_manageAddress;

//	@FindBy(id="business_phone")
//	private WebElement ab_textbox_businessPhone;

//	
//	//combobox Type
//	@FindBy(id="type")
//	private WebElement site_combobox_type;

//	
//	// ------------------------Constructors------------------------
	public EUP_Navigation() {
	}

	public EUP_Navigation(WebDriver driver) {
		this();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public EUP_Navigation(WebDriver driver, int timeOut) {
		this();
		this.driver = driver;
		this.timeOut = timeOut;
		PageFactory.initElements(driver, this);
	}
//
//	// ----- Action ---------
	// click button manage self
	
	public EUP_Navigation clickButtonManageSelf() throws Exception {
		WebElementSupport2.clickSupport(ab_button_manageSelf, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}

	// click button save contact

	public EUP_Navigation clickButtonSaveContact() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_saveContact, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}	
	// click button add contact

	public EUP_Navigation clickButtonAddContact() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_addContact, timeOut, interval_miliseconds);
		return this;
	}	
	
	// click button manage groups
	public EUP_Navigation clickButtonManageGroups() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_manageGroups, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}	
	
	// click button manage groups

	public EUP_Navigation clickButtonAddGroup() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_addGroup, timeOut, interval_miliseconds);
		return this;
	}
	
	// click button add contact from file

	public EUP_Navigation clickButtonAddFromFile() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_addFromFile, timeOut, interval_miliseconds);
		return this;
	}	
	
	// click button Yes

	public EUP_Navigation clickbButtonConfirmYes() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_confirmYes, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}
	
	// click button remove contact
	public EUP_Navigation clickButtonRemoveContact() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_removeContact, timeOut, interval_miliseconds);
		return this;
	}	
	
	// click button return to list
	public EUP_Navigation clickButtonReturnToList() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_returnToList, timeOut, interval_miliseconds);
		return this;
	}	
	
	// click button return to list
	public EUP_Navigation clickButtonConfirmImport() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_confirmImport, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}	
	
	// click button add picture

	public EUP_Navigation clickButtonAddPicture() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_addPicture, timeOut, interval_miliseconds);
		return this;
	}
	
	// click button remove picture
	public EUP_Navigation clickButtonRemovePicture() throws Exception {		
		WebElementSupport2.clickSupport(ab_button_removePicture, timeOut, interval_miliseconds);
		return this;
	}
		
	// click button add picture
	public EUP_Navigation clickButtonAddRoute() throws Exception {		
		WebElementSupport2.clickSupport(rt_button_addRoute, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}

	// click button advance mode
	public EUP_Navigation clickButtonAdvanceMode() throws Exception {		
		WebElementSupport2.clickSupport(rt_button_advancedMode, timeOut, interval_miliseconds);
		return this;
	}
	
	// click button basic mode
	public EUP_Navigation clickButtonBasicMode() throws Exception {		
		WebElementSupport2.clickSupport(rt_button_basicMode, timeOut, interval_miliseconds);
		return this;
	}
	
	// click button remove route
	public EUP_Navigation clickButtonRemoveRoute() throws Exception {		
		WebElementSupport2.clickSupport(rt_button_removeRoute, timeOut, interval_miliseconds);
		return this;
	}
	
	// click button save route
	public EUP_Navigation clickButtonSaveRoute() throws Exception {		
		WebElementSupport2.clickSupport(rt_button_saveRoute, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}	
	
	// click search company list
	public EUP_Navigation clickSerchCompanyList() throws Exception {		
		WebElementSupport2.clickSupport(button_searchCompanyList, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}	
	
	// click view personal list
	public EUP_Navigation clickViewPersonalList() throws Exception {		
		WebElementSupport2.clickSupport(button_viewPersonalList, timeOut, interval_miliseconds);
		return this;
	}	
	
	// click VSC
	public EUP_Navigation clickVSC() throws Exception {		
		WebElementSupport2.clickSupport(button_VSC, timeOut, interval_miliseconds);
		return this;
	}	
	
	// click VSC
	public EUP_Navigation clickClients() throws Exception {		
		WebElementSupport2.clickSupport(button_Clients, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}	
	
	// click Save Settings
	public EUP_Navigation clickButtonSaveSettings() throws Exception {		
		WebElementSupport2.clickSupport(button_saveSettings, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);

		return this;
	}	
	
	// click User Portal
	public EUP_Navigation clickButtonUserPortal() throws Exception {		
		WebElementSupport2.clickSupport(button_saveSettings, timeOut, interval_miliseconds);
		return this;
	}	
	
	// check navigation is in Basic mode or not
	public boolean  isWebElementExist(WebDriver driver, String label) {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		String st = "//*[contains(text(),'" + label + "')]";
		boolean exists = driver.findElements(By.xpath(st)).size() != 0;
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return exists;
	}
//   //----------- Verify ------------------------
//	
	

}
