package tests.example;

import base.DriverBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TabSwitch extends DriverBase {
    Logger loger = LoggerFactory.getLogger(TabSwitch.class);
    @Test
    public void TabSwitch() throws  Exception   {
        WebDriver driver = getDriver();
        driver.get("http://demo.guru99.com/popup.php");
        driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

        // Get the ID of parent tab
        String MainWindow = driver.getWindowHandle();
        loger.info("Current tab id is " + MainWindow);

        // Get all the IDs of tabs
        Set<String> allID = driver.getWindowHandles();
        String newWindow = null;
        for (String ID : allID){
            if (ID.equalsIgnoreCase(MainWindow)) {
                newWindow = ID;
            }
        }
        loger.info("New Wiindow ID is " + newWindow);

        Thread.sleep(5000);
        driver.switchTo().window(MainWindow);
        Thread.sleep(5000);
    }
}
