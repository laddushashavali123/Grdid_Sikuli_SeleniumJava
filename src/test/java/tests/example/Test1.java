package tests.example;

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
import static base.DriverUtils.sendWhenReady;

public class Test1 extends DriverBase{
    private void googleExampleThatSearchesFor() throws Exception {
        WebDriver driver = DriverBase.getDriver();

        driver.get("https://kibana-eu.kandy.io");
        /*driver.findElement(By.xpath("//*[@id=\"login-form-username\"]")).sendKeys("kadung");
        driver.findElement(By.xpath("//*[@id=\"login-form-password\"]")).sendKeys("123456789z@Z");
        driver.findElement(By.xpath("//*[@id=\"login-form-submit\"]")).submit();*/

        //clickWhenReady(By.partialLinkText("Timelion"), 5);

        Thread.sleep(5000);
    }


    @Test
    public void google1() throws Exception {
        googleExampleThatSearchesFor();
    }
}
