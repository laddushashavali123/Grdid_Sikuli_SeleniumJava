package tests.page_object.specs;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.page_object.pages.Annotation_BankLogin;
import tests.page_object.pages.Normal_BankLogin;

public class Login_Annotation extends DriverBase {
    @Test
    public void Annotations(){
        WebDriver driver = getDriver();
        Annotation_BankLogin login = new Annotation_BankLogin(driver);
        login.enterUsername("mngr136913")
                .enterPassword("YbEhege")
                .clickSubmit()
                .verifyLoginSuccess();
    }
}
