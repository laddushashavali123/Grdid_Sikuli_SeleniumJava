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

        //driver.findElement(By.partialLinkText("Timelion")).click();

        WebElement ele = driver.findElement(By.partialLinkText("Timelion"));
        clickWhenReady(driver, ele, 5);


        /*driver.get("https://kvs1-ls-agent.kandy.io/ny01mR0a/login");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/div[1]/input")).sendKeys("test321");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/div[2]/input")).sendKeys("Kandy-1234");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/input")).submit();*/

        //Thread.sleep(10000);

        // OK
        //WebElement myelement = driver.findElement(By.xpath("//div[contains(text(),'Voice Chat Request')]"));
        //JavascriptExecutor executor = (JavascriptExecutor)driver;
        //executor.executeScript("arguments[0].click()", myelement);

        //clickWhenReadyJavascript(driver, By.xpath("//div[contains(text(),'Voice Chat Request')]"), 5);

        //WebElement myelement = driver.findElement(By.xpath("//div[@class='customer-sidebar']"));
        //myelement.click();
        //clickWhenReady(driver, By.xpath("//*[@id=\"root\"]/div/div/header/nav/ul/li[2]/a/span/span"), 5);


        Thread.sleep(5000);
    }


    @Test
    public void google1() throws Exception {
        googleExampleThatSearchesFor();
    }
}
