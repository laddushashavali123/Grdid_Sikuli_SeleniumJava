package nuvia.UCDMaxTimeout.testcases;

import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import automation.DriverFactory;
import automation.utils.web.WebUtil;
import nuvia.pages.AddCallGroup;
import nuvia.pages.AddSubscriberForm;
import nuvia.pages.CallAnswerGroupPage;
import nuvia.pages.LoginPage;
import nuvia.pages.MenuAfterChoseCustomer;
import nuvia.pages.MenuBeforeChoseCustomer;
import nuvia.pages.Subscribers;

public class TC_002_OrgLevel_HelpPartWillUpdateTheMaxQueueTimeTo5400ForUCDGroup extends DriverFactory{
	public static final Logger logger = LogManager.getLogger("TC_002_OrgLevel_HelpPartWillUpdateTheMaxQueueTimeTo5400ForUCDGroup");
	@Test
	@Parameters({"waitTime","Partner_url","Partner_user","Partner_password","UCD_customer", "UCD_MaxWaitTime"})
	public void OrgLevel_CreateUCDGroupWithMaxQueueTimeTo5400(int waitTime, String url, String user, String password, String customer, String maxWaitTime) throws Exception {

		WebDriver driver = getDriver();
		initializeDriver(driver, url, waitTime);
		LoginPage login = new LoginPage(driver, waitTime);
 		login.Login(user, password, url);		
 		
		MenuBeforeChoseCustomer menubefore = new MenuBeforeChoseCustomer(driver, waitTime);
		logger.info("Search customer '" + customer + "' and left click");
		menubefore.searchCustomer(customer);
		
		MenuAfterChoseCustomer menuafter = new MenuAfterChoseCustomer(driver, waitTime);
		logger.info("Select Call Answer Groups");
		menuafter.clickToProvisionThenChooseCallAnswerGroups();
		
		CallAnswerGroupPage callgroup= new CallAnswerGroupPage(driver, waitTime);
		callgroup.clickButtonAddGroup();
		AddCallGroup addCAG = new AddCallGroup(driver, waitTime);
		logger.info("-----------------------Test case: ORG_Verify the Help Part will update the max queue time to 5400 for UCD group-----------------------");
		Reporter.log("1. Test case: ORG_Verify The help Part Max Queue Time = 5400");		
		Assert.assertTrue(addCAG.verifyMaxWaitTimeValueInHelpPart("5400"));
	}
}
