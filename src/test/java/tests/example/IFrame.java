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
        driver.get("http://toolsqa.com/iframe-practice-page/");
        Thread.sleep(1000);

        // Total number of IFrame
        List<WebElement> iframeElements = driver.findElements(By.xpath("//iframe"));
        System.out.println("The total number of iframes are " + iframeElements.size());
        System.out.println(iframeElements);
    }
}
