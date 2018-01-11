package tests.example;

import base.DriverUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.DriverBase;
import static base.DriverUtils.clickWhenReady;
import static base.DriverUtils.clickWhenReadyJavascript;

public class Test1 extends DriverBase{
    private void googleExampleThatSearchesFor() throws Exception {
        WebDriver driver = DriverBase.getDriver();

        /*driver.get("https://kibana-eu.kandy.io");
        clickWhenReady(By.partialLinkText("Timelion"), 5);*/

        driver.get("chrome-extension://djmahmfbjmiaepjbjkoekidjfcgbcfhc/popup.html");
        DriverUtils.clickWhenReady(By.id("viewSettings"),5);
        WebElement northAmerica = driver.findElement(By.id("southAmerica"));
        Assert.assertTrue(northAmerica.isSelected());


        Thread.sleep(2000);
    }


    @Test
    public void google1() throws Exception {
        googleExampleThatSearchesFor();

    }
}
