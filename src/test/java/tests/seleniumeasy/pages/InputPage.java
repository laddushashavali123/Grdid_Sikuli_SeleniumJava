package tests.seleniumeasy.pages;

import org.openqa.selenium.WebDriver;

public class InputPage {
    private WebDriver driver;

    public InputPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goSimpleForm() {
        driver.get("http://www.seleniumeasy.com/test/basic-first-form-demo.html");
    }

    public void goCheckBox() {
        driver.get("http://www.seleniumeasy.com/test/basic-checkbox-demo.html");
    }

    public void goRadioButton() {
        driver.get("http://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
    }

    public void goSelectDropDown() {
        driver.get("http://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
    }

    public void goJqueryDropDown() {
        driver.get("http://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
    }

    public void goInputForm() {
        driver.get("http://www.seleniumeasy.com/test/input-form-demo.html");
    }

    public void goAjaxForm() {
        driver.get("http://www.seleniumeasy.com/test/ajax-form-submit-demo.html");
    }



}
