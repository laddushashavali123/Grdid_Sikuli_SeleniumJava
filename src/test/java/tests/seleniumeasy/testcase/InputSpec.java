package tests.seleniumeasy.testcase;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import tests.seleniumeasy.pages.InputPage;

public class InputSpec extends DriverBase{
    @Test
    public void SimpleForm(){
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
    }

    @Test
    public void CheckBox(){
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
    }

    @Test
    public void RadioButton(){
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
    }

    @Test
    public void SelectDropDown(){
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
    }

    @Test
    public void JqueryDropdown(){
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
    }

    @Test
    public void InputForm(){
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
    }

    @Test
    public void AjaxForm(){
        WebDriver driver = getDriver();
        InputPage inputPage = new InputPage(driver);
    }
}
