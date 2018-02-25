package tests.seleniumeasy.testcase.input_form;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.seleniumeasy.pages.input_form.DropDownJqueryPage;

public class DropDownJquerySpec extends DriverBase{
    @Test
    public void JqueryDropDownTest(){
        WebDriver driver = getDriver();
        DropDownJqueryPage jqueryDropDownPage = new DropDownJqueryPage(driver);

        jqueryDropDownPage.goJqueryDropDown();

    }
}
