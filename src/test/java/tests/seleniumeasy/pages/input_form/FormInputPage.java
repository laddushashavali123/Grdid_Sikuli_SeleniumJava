package tests.seleniumeasy.pages.input_form;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormInputPage {
    private WebDriver driver;
    final Logger logger = LoggerFactory.getLogger(FormAjaxPage.class);

    // Constructor
    public FormInputPage(WebDriver driver){ this.driver = driver; }


    // Locator


    // Method
    public void goInputForm() {
        driver.get("http://www.seleniumeasy.com/test/input-form-demo.html");
        driver.manage().window().maximize();
    }


    //Verify
}
