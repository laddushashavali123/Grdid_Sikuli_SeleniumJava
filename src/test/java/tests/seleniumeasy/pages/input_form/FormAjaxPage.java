package tests.seleniumeasy.pages.input_form;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FormAjaxPage {
    private WebDriver driver;
    final Logger logger = LoggerFactory.getLogger(FormAjaxPage.class);

    // Constructor
    public FormAjaxPage(WebDriver driver){ this.driver = driver; }


    // Locator


    // Method
    public FormAjaxPage goAjaxForm() {
        driver.get("http://www.seleniumeasy.com/test/ajax-form-submit-demo.html");
        driver.manage().window().maximize();
        return this;
    }

    // Verify

}
