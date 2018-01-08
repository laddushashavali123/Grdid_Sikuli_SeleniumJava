package tests.example;

import base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.util.Set;

public class Alert extends DriverBase {
    Logger loger = LoggerFactory.getLogger(Alert.class);
    @Test
    public void ConfirmationAlert() throws  Exception {
        WebDriver driver = getDriver();
        driver.get("http://demo.guru99.com/test/delete_customer.php");
        driver.findElement(By.xpath("//*[contains(@type,'submit')]")).click();
        // Dismiss the alert
        driver.switchTo().alert().dismiss();
        Thread.sleep(500);
        // Display content of the alert
        driver.findElement(By.xpath("//*[contains(@type,'submit')]")).click();
        loger.info(String.valueOf(driver.switchTo().alert().getText()));
        // Accept the alert
        driver.switchTo().alert().accept();
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
    }

    /**
     * Authentication via Alert
     * @throws Exception
     */
    @Test
    public void PromptAlert() throws  Exception   {
        WebDriver driver = getDriver();
        driver.get("https://kadung:123456789z@Z@portal.genband.com/");
        Thread.sleep(5000);
        Thread.sleep(5000);
    }
}
