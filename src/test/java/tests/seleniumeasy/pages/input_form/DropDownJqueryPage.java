package tests.seleniumeasy.pages.input_form;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropDownJqueryPage {
    private WebDriver driver;
    private final Logger logger = LoggerFactory.getLogger(DropDownJqueryPage.class);

    // Constructor
    public DropDownJqueryPage(WebDriver driver){
        this.driver =  driver;
    }

    // Locator
    By selectCountry = By.id("");



    // Action
    public DropDownJqueryPage goJqueryDropDown() {
        driver.get("http://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
        driver.manage().window().maximize();
        return this;
    }


}
