package tests.example;

import base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static base.DriverUtils.clickWhenReadyJavascript;

public class Test2 extends DriverBase {

    @Test
    public void LS() throws Exception {
        WebDriver driver = getDriver();
        driver.get("https://kvs1-ls-agent.kandy.io/ny01mR0a/login");


        /*driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/div[1]/input")).sendKeys("test321");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/div[2]/input")).sendKeys("Kandy-1234");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/div/form/input")).submit();
        clickWhenReadyJavascript(By.xpath("//div[contains(text(),'Voice Chat Request')]"), 15 );*/

        Thread.sleep(5000);
    }
}
