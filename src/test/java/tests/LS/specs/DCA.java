package tests.LS.specs;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.LS.pages.AgentAdminPage;
import tests.LS.pages.CustomerPages;

public class DCA extends DriverBase{
    @Test
    public void DCATest() throws InterruptedException {
        WebDriver driver = getDriver();
        CustomerPages customerPages = new CustomerPages(driver);
        AgentAdminPage agentAdminPage = new AgentAdminPage(driver);

        /*customerPages.accessCustomerPage()
                .clickOnText("DCA")
                .enterTextTo("Name", "Dung")
                .clickStartBotChat()
                .enterTextTo("Enter a message", "hi")
                .openAgentTab();*/

        agentAdminPage.loginAgentAdminPage()
                .sideBarClickOn("Kandy Bot Sessions")
                .getMonitorMessage();

        Thread.sleep(20000);
    }
}
