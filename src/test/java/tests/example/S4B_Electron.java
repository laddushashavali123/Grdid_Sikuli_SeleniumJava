package tests.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import utils.WebUtils;

import java.net.URL;

public class S4B_Electron {
	@Test
	public void login() throws Exception {
		//System.setProperty("webdriver.chrome.driver", "E:\\zDung\\eclipse\\workspace\\test\\src\\test\\resources\\chromedriver.exe");
		String S4BLocation = "C:\\Users\\test1\\AppData\\Local\\Programs\\1RTC_Client_for_Skype_for_Business_SV_KBSUCC_Skype2015\\RTC Client for Skype for Business - SV KBSUCC Skype2015.exe";
		// C:\Users\test1\AppData\Local\Programs\1RTC_Client_for_Skype_for_Business_SV_KBSUCC_Skype2015\RTC Client for Skype for Business - SV KBSUCC Skype2015.exe


		ChromeOptions options = new ChromeOptions();
	    options.setBinary(S4BLocation);

	    // Local instance
		//WebDriver driver = new ChromeDriver(options);

		// Remote instance
		WebDriver driver = new RemoteWebDriver(new URL("http://10.250.193.110:4444/wd/hub"), options);


		// Action
		WebDriverWait wait = new WebDriverWait(driver, 50);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"emailSkype\"]")));
		element.clear();
		element.sendKeys("ucc.user61@gbsolutions.work");

      	Thread.sleep(50000);
      	driver.close();
	  	driver.quit();
	}
}
