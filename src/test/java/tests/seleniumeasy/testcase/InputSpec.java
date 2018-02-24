package tests.seleniumeasy.testcase;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import tests.seleniumeasy.pages.InputPage;

public class InputSpec extends DriverBase{
    final Logger logger = LoggerFactory.getLogger(InputSpec.class);


    @Test
    public void SelectDropDown() throws InterruptedException {
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);

        inputPage.goSelectDropDown();
        inputPage.dropDownSingleSelectByText("Select List Demo", "Saturday");
        inputPage.dropDownSingleSelectByText("Multi Select List Demo", "Ohio")
                .dropDownSingleSelectByText("Multi Select List Demo", "Texas");
        Thread.sleep(5000);

        inputPage.dropDownMultipleSelectContinous("Multi Select List Demo", 1,4);
        Thread.sleep(5000);
    }

    /*
    public void JqueryDropdown(){
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
    }}*/
}
