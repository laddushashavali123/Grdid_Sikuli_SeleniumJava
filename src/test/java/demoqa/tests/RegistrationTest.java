package demoqa.tests;

import base.DriverBase;
import demoqa.pages.RegistrationPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.Assert;

public class RegistrationTest extends DriverBase{
    @Test
    public void registerSuccess() throws Exception {
        WebDriver driver = DriverBase.getDriver();
        driver.get("http://demoqa.com/registration/");
        RegistrationPage registrationPage = new RegistrationPage();

        registrationPage.enterUserName("Dung HandSome");
        Assert.assertEquals(driver.getTitle(), "aa");
        Thread.sleep(1000);
    }


}

