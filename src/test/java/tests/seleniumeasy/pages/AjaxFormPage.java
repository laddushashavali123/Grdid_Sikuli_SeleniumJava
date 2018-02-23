package tests.seleniumeasy.pages;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AjaxFormPage {
    private WebDriver driver;
    final Logger logger = LoggerFactory.getLogger(AjaxFormPage.class);

    // Constructor
    public AjaxFormPage(WebDriver driver){ this.driver = driver; }


    // Locator


    // Method
    public AjaxFormPage goAjaxForm() {
        driver.get("http://www.seleniumeasy.com/test/ajax-form-submit-demo.html");
        driver.manage().window().maximize();
        return this;
    }

    // Verify

}
