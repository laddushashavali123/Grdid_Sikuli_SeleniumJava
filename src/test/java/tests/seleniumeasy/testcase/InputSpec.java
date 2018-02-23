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

    /*@Test
    public void InputForm(){
        WebDriver driver = getDriver();
        InputPage inputPaged = new InputPage(driver);
        inputPaged.goSimpleForm();

        inputPaged.enterText("Enter message", "Hi, I am Danny")
                .clickButton("Show Message");
        Assert.assertTrue(inputPaged.verifyText("Your Message: ","Hi, I am Danny"));
        Reporter.log("Single Input Field is PASSED");

        inputPaged.enterText("Enter a", "1")
                .enterText("Enter b", "2")
                .clickButton("Get Total");
        Assert.assertTrue(inputPaged.verifyText(" Total a + b = " ,"3"));
        Reporter.log("Two Input Fields is PASSED");
    }*/


    /*@Test
    public void CheckBox() throws InterruptedException {
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
        inputPage.goCheckBox();

        logger.info("Verify checkbox message display correctly");
        inputPage.clickCheckBoxName("Click on this check box");
        Assert.assertTrue(inputPage.verifyCheckBoxMessage("Success - Check box is checked"), "Error: Checkbox message displays incorrectly");
        Reporter.log("Checkbox message display correctly.");
        Thread.sleep(3000);

        logger.info("Verify partial checkbox is checked successfully");
        inputPage.clickCheckBoxName("Option 1")
                .clickCheckBoxName("Option 4");
        Assert.assertTrue(inputPage.verifyCheckBoxIsChecked("Option 1"));
        Assert.assertFalse(inputPage.verifyCheckBoxIsChecked("Option 2"));
        Assert.assertFalse(inputPage.verifyCheckBoxIsChecked("Option 3"));
        Assert.assertTrue(inputPage.verifyCheckBoxIsChecked("Option 4"));
        Assert.assertTrue(inputPage.verifyInputButtonText("Check All"));
        Reporter.log("Partial checkBox is checked successfully");

        Thread.sleep(3000);
        inputPage.clickInputButton("Check All");
        Assert.assertTrue(inputPage.verifyAllCheckBoxIsChecked());
        Assert.assertTrue(inputPage.verifyInputButtonText("Uncheck All"));
        Reporter.log("Check All button is change to Uncheck All after click on Check All button");

        Thread.sleep(3000);
        inputPage.clickInputButton("Uncheck All");
        Assert.assertFalse(inputPage.verifyAllCheckBoxIsChecked());
        Assert.assertTrue(inputPage.verifyInputButtonText("Check All"));
        Reporter.log("UnCheck All button is change to Check All after click on Uncheck All button");
    }*/


    /*@Test
    public void RadioButton() throws InterruptedException {
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
        inputPage.goRadioButton();

        inputPage.clickRadioButtonNameGroup("Male", "gender")
                .clickRadioButtonName("5 - 15")
                .clickButton("Get values");

        Thread.sleep(5000);
    }*/


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

    /*@Test
    public void JqueryDropdown(){
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
    }}*/
}
