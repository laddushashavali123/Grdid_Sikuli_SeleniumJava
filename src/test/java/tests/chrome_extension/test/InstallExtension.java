package tests.chrome_extension.test;

import base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import base.DriverUtils;
import tests.example.Alert;

import java.awt.*;
import java.awt.event.KeyEvent;

import static base.DriverUtils.isElementVisible;

public class InstallExtension extends DriverBase {
    Logger loger = LoggerFactory.getLogger(Alert.class);
    @Test
    public void install() throws Exception {
        WebDriver driver = getDriver();
        driver.get("https://chrome.google.com/webstore/detail/click-to-call/djmahmfbjmiaepjbjkoekidjfcgbcfhc");
        By button = By.className("webstore-test-button-label");
        DriverUtils.clickWhenReady(button,5);
        loger.info("Button text: " + driver.findElement(button).getText());
        Thread.sleep(5000);

        DriverUtils.pressKeyboard(KeyEvent.VK_TAB);
        DriverUtils.pressKeyboard(KeyEvent.VK_ENTER);
        Thread.sleep(5000);

        loger.info("Button text: " + driver.findElement(button).getText());
        for (int i=0;i<100; i++){
            loger.info("Button text: " + driver.findElement(button).getText());
            Thread.sleep(5000);
            if (driver.findElement(button).getText().equalsIgnoreCase("ADDED TO CHROME")){
                break;
            }
        }
    }
}
