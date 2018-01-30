// https://www.guru99.com/select-option-dropdown-selenium-webdriver.html

package tests.example;

import base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import utils.WebUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

public class DropDownandCheckBox extends DriverBase{
    Logger logger = LoggerFactory.getLogger(DropDownandCheckBox.class);

    @Test
    public void test() throws InterruptedException, AWTException {
        WebDriver driver = DriverBase.getDriver();
        driver.get("http://toolsqa.wpengine.com/automation-practice-form");

        WebUtils.pressKeyboard(KeyEvent.VK_PAGE_DOWN);

        // Checkbox and Radio button
        WebUtils.selectCheckBox("Male");
        WebUtils.selectCheckBox("Automation Tester");

        // Single dropdown list
        By dropDownList = By.xpath("//*[@id='continents']");
        WebUtils.selectDropDownByText(dropDownList, "Antartica");

        // Multiple choice list


        Thread.sleep(3000);


    }






}
