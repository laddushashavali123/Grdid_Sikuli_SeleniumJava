package tests.example;

import base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.List;

public class IFrame extends DriverBase {
    Logger logger = LoggerFactory.getLogger(IFrame.class);

    @Test
    public void iframe() throws InterruptedException {
        WebDriver driver = getDriver();
        //driver.manage().window().fullscreen();
        driver.get("http://demo.guru99.com/selenium/guru99home/");
        Thread.sleep(1000);

        // Total number of IFrame
        List<WebElement> elements = driver.findElements(By.tagName("iframe"));
        System.out.println(elements.size());

        // Switch to new frame
        driver.switchTo().frame("a077aa5e");
        // Click on the link on this frame
        driver.findElement(By.xpath("html/body/a/img")).click();
        Thread.sleep(10000);

    }
}
