package tests.seleniumeasy.testcase.input_form;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.seleniumeasy.pages.input_form.DropDownSelectPage;

import java.awt.*;

public class DropDownSelectSpec extends DriverBase {
    @Test
    public void SelectDropDownTest() throws InterruptedException, AWTException {
        WebDriver driver = getDriver();
        DropDownSelectPage selectDropDownPage = new DropDownSelectPage(driver);

        selectDropDownPage.goSelectDropDown()
                .dropDownSingleSelectByText("Select List Demo", "Tuesday")
                .verifySelectListResultIs("Tuesday");

        selectDropDownPage.dropDownMultipleSelectContinous("Multi Select List Demo", 1,3);
        Thread.sleep(5000);

        selectDropDownPage.dropDownSingleSelectByText("Multi Select List Demo", "Ohio")
                .dropDownSingleSelectByText("Multi Select List Demo","Washington");
        Thread.sleep(5000);
    }
}
