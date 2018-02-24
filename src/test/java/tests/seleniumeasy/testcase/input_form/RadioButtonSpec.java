package tests.seleniumeasy.testcase.input_form;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.seleniumeasy.pages.input_form.RadioButtonPage;

public class RadioButtonSpec extends DriverBase {
    @Test
    public void RadioButtonTest() {
        WebDriver driver = getDriver();
        RadioButtonPage radioButtonPage = new RadioButtonPage(driver);

        radioButtonPage.goRadioButton()
                .clickRadioButtonNameGroup("Male", "gender")
                .clickRadioButtonName("5 - 15")
                .clickButton("Get values")
                .verifyResultTextIs("Sex : Male and Age group: 5 - 15");
    }
}
