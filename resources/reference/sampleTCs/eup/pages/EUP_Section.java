package nuvia.eup.pages;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;
import nuvia.pages.CustomerPage;
import nuvia.pages.OrganizationPage;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

public class EUP_Section {

	private WebDriver driver;
	private int timeout = 15;
	private int timeOut = 30;
	private int interval_miliseconds = 1000;
	
	public static final Logger logger = LogManager.getLogger("EUP_Section");
	
	WebUtil webUtil = new WebUtil();
	
	// search text box
	@FindBy(xpath = ".//*[@id='search_text']")
	private WebElement contact_textbox_search;
	
	// first contact
	@FindBy(xpath = ".//*[@id='personal_r0_c1']/a")
	private WebElement firstContact_textlink;
	
	// search button
	@FindBy(xpath = ".//*[@id='search_icon']")
	private WebElement contact_button_search;
//	
	//text Business Phone
//	@FindBy(xpath="//*[contains(text(),'SAVE CONTACT')]") private WebElement ab_button_saveContact;
	@FindBy(xpath="//*[contains(text(),'No Group')]") private WebElement elmNoGroup;
	@FindBy(xpath="html/body/div/div/div/div[2]/div/div/div/div[1]/div[1]/form/div[6]/div/div/div[4]/div[2]/input")
	private WebElement ab_textbox_businessPhone;
	
	String strNoGroupXpath ="html/body/div/div/div/div[2]/div/div/div/div[1]/div[1]/div[5]/div[2]/grid-table/div[3]/div[1]/div[2]/table/tbody/tr/td[1]/div/span/input";
	String strGroupExistXpath ="html/body/div/div/div/div[2]/div/div/div/div[1]/div[1]/div[5]/div[2]/grid-table/div[3]/div[1]/div[2]/table/tbody/tr[1]/td[1]/div/span/input";
	
	@FindBy(xpath="html/body/div/div/div/div[2]/div/div/div/div[1]/div[1]/div[5]/div[2]/grid-table/div[3]/div[1]/div[2]/table/tbody/tr/td[1]/div/span/input")
	private WebElement elmNoGroupExist;
	@FindBy(xpath="html/body/div/div/div/div[2]/div/div/div/div[1]/div[1]/div[5]/div[2]/grid-table/div[3]/div[1]/div[2]/table/tbody/tr[1]/td[1]/div/span/input")
	private WebElement elmGroupExist;
	
	// Confirm add group button
	@FindBy(xpath = ".//*[@id='group_inline_add']/td[3]/div/i[1]")
	private WebElement btnConfirmAddGroup;
	
//	@FindBy(xpath="//*[contains(text(),'Home Phone')]") private WebElement elmHomePhone;
	
	// Add route
	// WHEN A CALL IS RECEIVED
	@FindBy(xpath="html/body/div[1]/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[4]/td[1]/input")
	private WebElement checkbox_CallReceived_FromNumbers;
	
	@FindBy(xpath=""
			+ "html/body/div[1]/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[4]/td[2]/span/a")
	private WebElement button_CallReceived_FromNumbers;
	
	// ROUTE
	@FindBy(xpath="html/body/div[1]/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[1]/td[1]/input")
	private WebElement checkbox_Route_IfNotBusy;
	
	@FindBy(xpath="html/body/div[1]/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[1]/td[2]/span/a")
	private WebElement checkbox_Route_IfNotBusyTheseNumbers;
	
	@FindBy(xpath="html/body/div[1]/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[8]/td[1]/input")
	private WebElement checkbox_Route_IfBusy;
	
	@FindBy(xpath="html/body/div[1]/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[8]/td[2]/span/a")
	private WebElement checkbox_Route_IfBusyTheseNumbers;
	
	
	@FindBy(xpath="html/body/div/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody/tr[11]/td[1]/input")
	private WebElement checkbox_Route_RejectTheCall;
	@FindBy(xpath=".//*[@id='mode']")
	private WebElement mySelectElement;
//	String st = ".//*[@id='mode']";
//	WebElement mySelectElement = driver.findElement(By.xpath(st));
	
	String str_second_col = "html/body/div/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div/div/div[3]/div[10]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[1]/td[2]/select";
	String str_fourth_col = "html/body/div/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div/div/div[3]/div[10]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[1]/td[4]/div/select";
	
	// combo box language
	@FindBy(id = "lang_select")
	private WebElement service_combobox_language;
//	public String getstr_second_col() throws IllegalStateException {
//	    return taxId.get(); //will throw exception if not set
//	}
	// ------------------------Constructors------------------------
	public EUP_Section() {
	}

	public String getStr_second_col() {
		return str_second_col;
	}

	public void setStr_second_col(String str_second_col) {
		this.str_second_col = str_second_col;
	}

	public String getStr_fourth_col() {
		return str_fourth_col;
	}

	public void setStr_fourth_col(String str_fourth_col) {
		this.str_fourth_col = str_fourth_col;
	}

	public EUP_Section(WebDriver driver) {
		this();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public EUP_Section(WebDriver driver, int timeout) {
		this();
		this.driver = driver;
		this.timeOut = timeout;
		PageFactory.initElements(driver, this);
	}

//	// ----- Action ---------
	// set Business Phone
	public EUP_Section setBusinessPhone(String name) throws Exception {		
		WebElementSupport2.sendKeysSupport(ab_textbox_businessPhone, name, timeOut, interval_miliseconds);
		return this;
	}
	
	// get Business Phone
	public String getBusinessPhone() throws Exception {		
		return WebElementSupport2.getTextSupport(ab_textbox_businessPhone, timeOut, interval_miliseconds);
	}
	
	public boolean verifyCustomerLanguage(Logger logger, String value)throws Exception { //Customer language.
		try{
			int idx = convertLanguageNameToIdx(value);
			Select select = new Select(service_combobox_language);			
			WebElement elm = select.getFirstSelectedOption();
			logger.info("Current language is:" + elm.getText());
			//if (elm.getText().equals(text))
			logger.info("idx:" + elm.getAttribute("value") + "----" + idx);
			if(elm.getAttribute("value").equals(idx+""))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	// select Language
	public EUP_Section selectLanguage(String value) {
		Select select = new Select(service_combobox_language);
		int idx = convertLanguageNameToIdx(value);
		select.selectByIndex(idx);
		//select.selectByVisibleText(value);
		return this;
	}
	
	// set Home Phone
//	public EUP_Section setHomePhone(String name) {
//		elmHomePhone.clear();
//		elmHomePhone.sendKeys(name);
//		return this;
//	}
	
	
	// set input according label
	public EUP_Section setInputAccordingLabel(String label, String input) throws Exception {
		String st = "//label[contains(.,'" + label + "')]/following::input[1]";
		WebElementSupport2.sendKeysSupport(By.xpath(st), driver, input, timeOut, interval_miliseconds);
		return this;
	}
	
	// get input according label
	public String getInputAccordingLabel(String label) throws Exception {
		String st = "//label[contains(.,'" + label + "')]/following::input[1]";
		WebElement elm = WebElementSupport2.findElementSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		return elm.getAttribute("value");
	}
	
	// set input according xpath
	public EUP_Section setInputAccordingXpath(String xpath_link, String input) throws Exception {
/*		WebElement elm = driver.findElement(By.xpath(xpath_link));		
		WebElementSupport2.sendKeysSupport(elm, input, timeOut, interval_miliseconds);*/
		WebElementSupport2.sendKeysSupport(By.xpath(xpath_link), driver, input, timeOut, interval_miliseconds);
		return this;
	}
	
//	// set select according to label
//	public EUP_Section setSelectAccordingLabel(String label, String input) {
//		String st = "//label[text()='" + label + "']/following::select[1]";
//		
//		Select select = new Select(driver.findElement(By.xpath(st)));
////		select.selectByIndex(int_index);
//		select.selectByVisibleText(input);
//		
//		return this;
//	}
	
	// set select according to label
	public EUP_Section setSelectAccordingLabel(String label, String input) throws Exception {
		String st = "//label[contains(.,'" + label + "')]/following::select[1]";
		WebElement elm = WebElementSupport2.findElementSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		Select select = new Select(elm);
		select.selectByVisibleText(input);
		return this;
	}
	
	// set select mode according to label
	public EUP_Section setSelectModeAccordingInput(String input) throws Exception {
		WebUtil.waitFor_Element(mySelectElement, 180);
		Select dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText(input);
		return this;
	}
    
	// set select according to label
	public EUP_Section setSelectRingsAccordingInput(String input) throws Exception {
		String st = ".//*[@id='no_of_rings']";
/*		WebElement mySelectElement = driver.findElement(By.xpath(st));
		WebUtil.waitFor_Element(mySelectElement, 180);*/
		WebElement mySelectElement = WebElementSupport2.findElementSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		Select dropdown= new Select(mySelectElement);
		dropdown.selectByVisibleText(input);
		return this;
	}
	// set input according to exact label
	public EUP_Section setInputAccordingExactLabel(String label, String input) throws Exception {
		String st = "//label[text()='" + label + "']/following::input[1]";
//		System.out.println(st);
/*		WebElement elm = driver.findElement(By.xpath(st));
		WebElementSupport2.sendKeysSupport(elm, input, timeOut, interval_miliseconds);*/
		WebElementSupport2.sendKeysSupport(By.xpath(st), driver, input, timeOut, interval_miliseconds);
		return this;
	}
	
	// set select according exact td
	public EUP_Section setSelectAccordingExactTd(String label, String input) throws Exception {
		String st = "//td[text()='" + label + "']/following::select[1]";
		WebElement mySelectElement = WebElementSupport2.findElementSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		Select select = new Select(mySelectElement);
		select.selectByVisibleText(input);
		return this;
	}
	
	// set select according td
	public EUP_Section setSelectAccordingTd(String label, String input) throws Exception {
		String st = "//td[contains(.,'" + label + "')]/following::select[1]";
		WebElement mySelectElement = WebElementSupport2.findElementSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		Select select = new Select(mySelectElement);
		select.selectByVisibleText(input);
		return this;
	}
	
	// set select according td Simultaneous
	public EUP_Section setSelectAccordingTdSimultaneous(String label, String input) throws Exception {
//		String st = "//td[contains(.,'" + label + "')]/following::select[1]";
		String st = "html/body/div/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div/div/div[3]/div[9]/div[1]/scrollable-table/div/div[2]/table/tbody/tr[" + label + "]/td[2]/select";
		WebElement mySelectElement = WebElementSupport2.findElementSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		Select select = new Select(mySelectElement);
		select.selectByVisibleText(input);
		return this;
	}
	
	// get input according div
	public String getInputAccordingDiv(String label) throws Exception {
		String st = "//div[contains(.,'" + label + "')]/following::input[1]";
/*		WebElement elm = driver.findElement(By.xpath(st));
		WebUtil.waitFor_Element(elm, 180);*/
		WebElement elm = WebElementSupport2.findElementSupport(By.xpath(st), driver, 180, 1000);
		return elm.getAttribute("value");
	}
	
	 public void selectRandomByXpath(String xpath)
	 {
		 //Object of the Dropdownlist
		 WebElement drpDwnList = driver.findElement(By.xpath(xpath));
		 //Using FindElements to create a List object
		 //List <WebElement> weblist = driver.findElements(By.xpath(".//*[@id='drpdwnTopics']/option"));
		 //Using Select Class to fetch the count
		 Select objSel = new Select(drpDwnList);
		 List <WebElement> weblist = objSel.getOptions();
		 //Taking the count of items
		 int iCnt = weblist.size();
		 //Using Random class to generate random values
		 Random num = new Random();
		 int iSelect = num.nextInt(iCnt);
		 //Selecting value from DropDownList
		 objSel.selectByIndex(iSelect);
		 //Selected Value
		 System.out.println(drpDwnList.getAttribute("value"));
	 }
	 
	 public void selectRandomRowOfSequentialTable(String fullXpath)
	 {
		 String[] parts = fullXpath.split("(?<=tr)");
		 String part1 = parts[0]; 
		 String part2 = parts[1];
		 part1 = part1 + "[";
		 String[] subString = part2.split("/", 2);
		 part2 = "]/" + subString[1];
		 
		 String st_table = "html/body/div[1]/div/div/div[2]/div/div/div/div/div[1]/form/div[3]/div/div/div[3]/div[10]/div[1]/scrollable-table/div/div[2]/table/tbody/tr";
	     int rowCount=driver.findElements(By.xpath(st_table)).size();
	     
	     for(int index=1; index <= rowCount; index++)
		    {
	    	 String newXpath = part1 + index +part2;
	    	 selectRandomByXpath(newXpath);
		    }
	     
	 }
	// set input select according td
	public EUP_Section selectCheckboxAccordingTd(String label) throws Exception {
		String st = "//td[contains(text(),'" + label + "') ]/preceding::input[1]";
		WebElementSupport2.clickSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		return this;
	}
	
	// set input select according span
	public EUP_Section selectCheckboxAccordingSpan(String label) throws Exception {
		String st = "//span[contains(text(),'" + label + "') ]/preceding::input[1]";
		WebElementSupport2.clickSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		return this;
	}
	
	public EUP_Section selectCheckboxAccordingSpanIfNotSelected(String label) throws Exception {
		String st = "//span[contains(text(),'" + label + "') ]/preceding::input[1]";
   		WebElement element = WebElementSupport2.findElementSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
   		boolean isChecked = element.isSelected();
   		if (!isChecked) {
   			WebElementSupport2.clickSupport(element, timeOut, interval_miliseconds);
   		}
		return this;
	}
	
	// set input select according exact span data
	public EUP_Section selectCheckboxAccordingExactSpan(String label) throws Exception {
		String st = "//span[contains(text(),'" + label + "') ]/preceding::input[1]";
		WebElementSupport2.clickSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		return this;
	}
	
	// set i select according div
	public EUP_Section removeSearchedGroupContact(String label) throws Exception {
		String st = "//div[contains(text(),'" + label + "') ]/following::i[2]";
		WebElementSupport2.clickSupport(By.xpath(st), driver, timeOut, interval_miliseconds);
		return this;
	}
	
	// set input select according div
	public EUP_Section selectCheckboxAccordingDiv(String label) throws Exception {
		String st = "//div[contains(text(),'" + label + "')]/preceding::input[1]";
		WebElement checkBox = WebElementSupport2.findElementSupport(By.xpath(st), driver, 180, 1000);
		if(!checkBox.getAttribute("type").toLowerCase().equals("checkbox")){
	        throw new Exception("This element is not a checkbox!");
	    }
		WebElementSupport2.clickSupport(checkBox, timeOut, interval_miliseconds);
		return this;
	}
	
	// set input select according a
	public EUP_Section selectCheckboxAccordingA(String label) throws Exception {
		String st = "//a[contains(text(),'" + label + "')]/preceding::input[1]";
		WebElement checkBox = WebElementSupport2.findElementSupport(By.xpath(st), driver, 180, 1000);
		if(!checkBox.getAttribute("type").toLowerCase().equals("checkbox")){
	        throw new Exception("This element is not a checkbox!");
	    }
		WebElementSupport2.clickSupport(checkBox, timeOut, interval_miliseconds);
	   /* checkBox.click();*/
		return this;
	}
	
	// search contact
	public EUP_Section searchContact(String value) throws Exception {		
		WebElementSupport2.sendKeysSupport(contact_textbox_search, value, timeOut, interval_miliseconds);
		//WebElementSupport2.clickSupport(contact_textbox_search, timeOut, interval_miliseconds);
		WebElementSupport2.clickSupport(contact_button_search, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}
	
	// search company list
	public EUP_Section searchCompanyList(String value) throws Exception {		
		WebElementSupport2.sendKeysSupport(contact_textbox_search, value, timeOut, interval_miliseconds);
		//WebElementSupport2.clickSupport(contact_textbox_search, timeOut, interval_miliseconds);
		WebElementSupport2.clickSupport(contact_button_search, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}
	
	// search group contact
	public EUP_Section searchGroupContact(String value) throws Exception {		
		WebElementSupport2.sendKeysSupport(contact_textbox_search, value, timeOut, interval_miliseconds);
		//WebElementSupport2.clickSupport(contact_textbox_search, timeOut, interval_miliseconds);
		WebElementSupport2.clickSupport(contact_button_search, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeOut);
		return this;
	}
	
	public EUP_Section clickOnTheFirstContact() throws Exception {
		WebElementSupport2.clickSupport(firstContact_textlink, timeOut, interval_miliseconds);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	// remove group contact
	public EUP_Section removeGroupContact(String value) throws Exception {
		logger.info("Search group contact: " + value);
		searchGroupContact(value);
		
		String tmp_xpath = ".//*[contains(text(),'" + value + "')]";
		WebElement feature_choose = driver.findElement(By.xpath(tmp_xpath));
		String tmp_id = feature_choose.getAttribute("id");
		
		logger.info("Click to Delete icon");
		String deleteIconXpath = ".//*[@id='" + tmp_id.replace("c0", "edit']/i[2]");
		WebElementSupport2.clickSupport(By.xpath(deleteIconXpath), driver, timeOut, interval_miliseconds);
		
		EUP_Navigation navigate = new EUP_Navigation(driver, timeOut);
		logger.info("Click to 'Yes' Button");
		navigate.clickbButtonConfirmYes();
		
		return this;
	}
	//select contact in Contact Table based on Value
	public EUP_Section checkOffContactInContactTableByFirstName(String value) throws Exception {	
		WebElementSupport2.checkOffCheckboxInTableByTableID(driver, "personal_tbody", value, -1);
		return this;
	}
	
	
	// check group exist
	public Boolean isNoGroup(WebDriver driver) throws Exception {
//		Boolean isGroupExist1 = driver.findElements(By.xpath(strGroupExistXpath)).size() > 0;
		Boolean isGroupExist1=false;
//		System.out.println("========================" + elmNoGroup.getText() + "========================");
//		if (elmNoGroup.getText().equals("No Group")){
		if (driver.findElements(By.xpath(strNoGroupXpath)).size() > 0){
			isGroupExist1 = true;
			System.out.println("There is no group");
		} else {
//			isGroupExist1 = false;
			System.out.println("There is some groups");
		}
		return isGroupExist1;
	}
	
	// Add group
	public EUP_Section addGroup(String name, Boolean noGroup) throws Exception {
		WebUtil.waitFor_Element(btnConfirmAddGroup, 180);
		if (noGroup){
			System.out.println("There is no group");
			WebElementSupport2.sendKeysSupport(elmNoGroupExist, name, timeOut, interval_miliseconds);
			WebElementSupport2.clickSupport(btnConfirmAddGroup, timeOut, interval_miliseconds);
		} else {
			System.out.println("There is some groups");			
			WebElementSupport2.sendKeysSupport(elmGroupExist, name, timeOut, interval_miliseconds);
		}
		return this;
	}
	
	// Add route
	public EUP_Section addRoute(EUP_Navigation navigate, String routeName) throws Exception {
		logger.info("Route Name: " + routeName);
		setInputAccordingLabel("Route Name", routeName);
//		String str_WhenACallIsReceivedXpath = ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody";
//		String str_NumbersXpath = ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/table/tbody";
//		String str_Route = ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[6]/div/table/tbody";
//		String str_UnlessXpath = ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[9]/div/table/tbody";
		
//		checkOptionInTable(str_WhenACallIsReceivedXpath,"From THESE NUMBERS or..", -1);
//		checkbox_CallReceived_FromNumbers.click();
		WebElementSupport2.clickSupport(checkbox_CallReceived_FromNumbers, timeOut, interval_miliseconds);
		//Thread.sleep(2000);
		//button_CallReceived_FromNumbers.click();
		WebElementSupport2.clickSupport(button_CallReceived_FromNumbers, timeOut, interval_miliseconds);
		//Thread.sleep(2000);
		logger.info("Select nnduy@duyorgroot.com");
		selectCheckboxAccordingTd("nnduy@duyorgroot.com");
		logger.info("Select 1234567800");
		selectCheckboxAccordingTd("1234567800");
		
		
//		selectCheckboxAccordingTd("Reject the call");
		//checkbox_Route_RejectTheCall.click();
		WebElementSupport2.clickSupport(checkbox_Route_RejectTheCall, timeOut, interval_miliseconds);
//		checkbox_Route_IfNotBusy.click();
//		Thread.sleep(2000);
//		checkbox_Route_IfNotBusyTheseNumbers.click();
//		Thread.sleep(3000);
//		selectCheckboxAccordingTd("5031@04a.com");
//		
//		checkbox_Route_IfBusy.click();
//		Thread.sleep(2000);
//		checkbox_Route_IfBusyTheseNumbers.click();
//		Thread.sleep(3000);
//		selectCheckboxAccordingTd("0188@04a.com");
		logger.info("Click to 'Save Route' Button");
		navigate.clickButtonSaveRoute();
		return this;
	}
	
	// Remove route
	public EUP_Section removeRoute(String routeName) throws Exception {
		Thread.sleep(2000);
		selectCheckboxAccordingA(routeName);
		return this;
	}
	
	// check web element exist
	public Boolean isLabelExist (String label){
//		WebElement elm = driver.findElement(By.xpath(xpath));
//		System.out.println("=============++++++" + elm.getText());
//		return ((driver.findElements( By.xpath(xpath) ).size() != 0) && label.equals(elm.getText()));
		List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'" + label + "')]"));
//		Boolean test = list.size() > 0;
//		System.out.println("=============++++++" + test);
		return ( list.size() > 0);
	}
	
//   //----------- Verify ------------------------
	// verify updating by alert
	public boolean verifyUpdateByAlert(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='content_div']/div[1]/div[1]/div[2]/div[1]"), driver, timeOut, interval_miliseconds);
			logger.info("Alert '" + alertAppear + "' appears");
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	// verify updating picture by alert
	public boolean verifyUpdatePictureByAlert(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='contacts_form']/div[2]/div[1]"), driver, timeOut, interval_miliseconds);
			logger.info("Alert '" + alertAppear + "' appears");
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	// verify updating settings by alert
	public boolean verifyUpdateSettingsByAlert(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='sip_trunks_form']/div[2]/div[1]"), driver, timeOut, interval_miliseconds);
			logger.info("Alert '" + alertAppear + "' appears");
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	// verify updating routing by alert
	public boolean verifyUpdateRoutingByAlert(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@id='routing_rules_form']/div[2]/div[1]"), driver, timeOut, interval_miliseconds);
			logger.info("Alert '" + alertAppear + "' appears");
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}

	// verify remove routing by alert
	public boolean verifyRemoveRoutingByAlert(String alert) {
		logger.info("Expect '" + alert + "' alert to appear");
		try{
			String alertAppear = WebElementSupport2.getTextSupport(By.xpath(".//*[@class='alert-box success ng-binding ng-scope']"), driver, timeOut, interval_miliseconds);
			logger.info("Alert '" + alertAppear + "' appears");
			if(alertAppear.equals(alert))
				return true;
		}catch(Exception ex){
			return false;
		}
		return false;
	}
	
	public EUP_Section checkOptionInTable(String xpath_tbody, String str_searchText, int offset_column) throws Exception {
		//Get number of rows In table.
		 int Row_count = driver.findElements(By.xpath(xpath_tbody + "/tr")).size();
//		 System.out.println("Number Of Rows = "+Row_count);
		 
		 //Get number of columns In table.
		 int Col_count = driver.findElements(By.xpath(xpath_tbody + "/tr[1]/td")).size();
//		 System.out.println("Number Of Columns = "+Col_count);
		 
		 //divided xpath In three parts to pass Row_count and Col_count values.
		 String first_part = xpath_tbody + "/tr[";
		 String second_part = "]/td[";
		 String third_part = "]";
		 
		 //Used for loop for number of rows.
		 for (int i=1; i<=Row_count; i++){
		  //Used for loop for number of columns.
		  for(int j=1; j<=Col_count; j++){
		   //Prepared final xpath of specific cell as per values of i and j.
		   String final_xpath = first_part+i+second_part+j+third_part;
		   //Will retrieve value from located cell and print It.
		   String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
		   if (str_searchText.equals(Table_data)){
			   j = j + offset_column;
			   String checkBoxXpath = first_part+i+second_part+j+third_part+"/input";
//			   System.out.println("============" + checkBoxXpath);
			   WebElement checkBox = driver.findElement(By.xpath(checkBoxXpath));
//			   WebElement elm = null;
//			   Boolean isChecked;
			   //			   Boolean isChecked = elm.findElement(By.xpath(checkBoxXpath)).isSelected();
			   Boolean isChecked = checkBox.isSelected();
//			   System.out.println("============" + isChecked);
			   if (!isChecked) {
				   checkBox.click();
			   }
//			   return true;
			   break;
		   }
		  }
		 } 
		return this;
	}
	
	
	public boolean updateLanguage(Logger logger, int wait, String newLanguage, EUP_Navigation nav) throws Exception {
		logger.info("Set new language to:" + newLanguage);
		this.selectLanguage(newLanguage);
		try {			
			logger.info("Click button save Settings");
			nav.clickButtonSaveSettings();			
			WebUtil.waitFor_LoadingFinished(driver, 90);
			Thread.sleep(3000);
			WebUtil.waitFor_LoadingFinished(driver, 90);
			WebUtil.waitFor_Element(service_combobox_language, 180);
			return this.verifyCustomerLanguage(logger, newLanguage);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean verifyUpdateCustomerLanguageAndVersa(Logger logger, int wait, String language,
			String orgLanguage, EUP_Section section, EUP_Navigation nav) throws Exception {
		//Verify Update Customer language
		if(section.updateLanguage(logger, wait, language, nav) == true){
			try
			{
				return section.updateLanguage(logger, wait, orgLanguage, nav);
			} catch(Exception ex){
				logger.error(ex);
				return false;
			}
		}
		return false;
	}
	
	public boolean verifyAddressBookPageLoad() throws Exception {
		boolean result = WebElementSupport2.checkElementExist(By.xpath(".//*[@id='manage_groups']"), driver, timeOut);
		return result;
	}
	
	public boolean verifySearchCompanyList() throws Exception {
		boolean result1 = WebElementSupport2.checkElementExist(By.xpath(".//*[@id='grid_company']"), driver, timeOut);
		boolean result2 = false;
		if (!result1) {
			result2 = WebElementSupport2.checkElementExist(By.xpath(".//*[contains(text(),'No Subscriber Contact')]"), driver, timeOut);
		}
		
		boolean result = result1 || result2;
		
		return result;
	}
	
	public boolean verifySearchContact() throws Exception {
		boolean result = WebElementSupport2.checkElementExist(By.xpath(".//*[@id='personal_r0_c1']/a"), driver, timeOut);
		if ( result) {
			logger.info("Contact appears!");
		} else {
			logger.info("Don't see contact!");
		}
		return result;
	}	
	
	public int convertLanguageNameToIdx(String language){
		System.out.println("*******language :" + language);
		switch(language){
		case "English":
			return 0;
		case "Portuguese":
			return 1;
		case "French":
			return 2;
		}
		return 0;
	}
	
	
	private boolean checkModeOfRoutingPage(String value) throws Exception {
		Thread.sleep(3000);
		String xpath = "//*[contains(text(),'" + value + "')]";
		boolean result = WebElementSupport2.checkElementExist(By.xpath(xpath), driver, 5);
		return !result;
	}
	
	public EUP_Section setModeOfRoutingPageToAdvancedMode() throws Exception {
		if (checkModeOfRoutingPage("ADVANCED MODE")) {
			// do nothing
			logger.info("Routing Page is already in Advanced Mode");
		} else {
			EUP_Navigation navigate = new EUP_Navigation(driver, timeOut);
			logger.info("Click to 'Advance Mode' Button");
			navigate.clickButtonAdvanceMode();
			logger.info("Click to 'Yes' Button");
			navigate.clickbButtonConfirmYes();
			WebUtil.waitFor_LoadingFinished(driver, timeOut);
		}
		return this;
	}
	
	public EUP_Section setModeOfRoutingPageToBasicMode() throws Exception {
		if (checkModeOfRoutingPage("BASIC MODE")) {
			// do nothing
			logger.info("Routing Page is already in Basic Mode");
		} else {
			EUP_Navigation navigate = new EUP_Navigation(driver, timeOut);
			logger.info("Click to 'Basic Mode' Button");
			navigate.clickButtonBasicMode();
			logger.info("Click to 'Yes' Button");
			navigate.clickbButtonConfirmYes();
			WebUtil.waitFor_LoadingFinished(driver, timeOut);
		}
		return this;
	}
	
}
