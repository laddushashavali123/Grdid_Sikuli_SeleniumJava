package tests.demoqa.testcase;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.demoqa.pages.SortablePage;

public class SortableSpec extends DriverBase{
    @Test
    public void test1() throws InterruptedException {
        WebDriver driver = getDriver();
        SortablePage sortablePage = new SortablePage(driver);

        sortablePage.accessPage();
        sortablePage.moveElement();
        Thread.sleep(5000);
    }
}
