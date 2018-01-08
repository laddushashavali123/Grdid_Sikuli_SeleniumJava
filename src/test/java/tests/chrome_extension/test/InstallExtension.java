package tests.chrome_extension.test;

import base.DriverBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class InstallExtension extends DriverBase {
    @Test
    public void install() throws Exception {
        WebDriver driver = getDriver();
        driver.get("https://chrome.google.com/webstore/detail/click-to-call/djmahmfbjmiaepjbjkoekidjfcgbcfhc");
        Thread.sleep(4000);
        driver.findElement(By.className("webstore-test-button-label")).click();

        Thread.sleep(4000);

        driver.switchTo().alert().getText();
    }
}
