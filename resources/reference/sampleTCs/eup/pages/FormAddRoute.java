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

public class FormAddRoute {
	private WebDriver driver;
	private int timeout = 30;
	public static final Logger logger = LogManager.getLogger("TabRouting");
	
	@FindBy(id = "route_name")
	private WebElement routeNameTextbox;
	
	@FindBy(id = "condition_numbers")
	private WebElement fromTheseNumbersOrCheckbox;
	
	@FindBy(xpath = ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[1]/div[3]/div/table/tbody/tr[4]/td[2]/span/a")
	private WebElement fromTheseNumbersOrLinktext;
	
	@FindBy(id = "termAction_reject")
	private WebElement rejectTheCallCheckbox;
	
	public FormAddRoute(WebDriver driver, int timeout) {
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
	
	public FormAddRoute setRouteName(String value) throws Exception {
		WebElementSupport2.sendKeysSupport(routeNameTextbox, value, timeout);
		return this;
	}
	
	public FormAddRoute clickSaveRouteButton() throws Exception {
		WebElementSupport2.clickSupport(By.xpath("//button[text()='SAVE ROUTE' and not(contains(@class,'ng-hide'))]"), driver, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	public FormAddRoute checkOffFromTheseNumbersOrInTableWhenACallIsReceived() throws Exception {
		WebElementSupport2.checkOffCheckbox(fromTheseNumbersOrCheckbox, true, timeout);
		//WebElementSupport2.clickSupport(fromTheseNumbersOrCheckbox, timeout);
		return this;
	}
	
	public FormAddRoute clickFromTheseNumbersOrInTableWhenACallIsReceived() throws Exception {
		WebElementSupport2.clickSupport(fromTheseNumbersOrLinktext, timeout);
		WebUtil.waitFor_LoadingFinished(driver, timeout);
		return this;
	}
	
	
	public FormAddRoute checkOffNumberInTableNumbers(String value) throws Exception {
		WebElementSupport2.checkOffCheckboxInTableByTableXpath(driver, ".//*[@id='routing_rules_form']/div[3]/div[2]/div[2]/div[3]/div[3]/div/table", value, -1);
		return this;
	}
	
	public FormAddRoute checkOffRejectTheCallInTableRoute() throws Exception {
		WebElementSupport2.checkOffCheckbox(rejectTheCallCheckbox, true, timeout);
		//WebElementSupport2.clickSupport(rejectTheCallCheckbox, timeout);
		return this;
	}

}
