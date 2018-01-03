package tests.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import static base.DriverUtils.*;

public class S4B {
	@Test
	public void login() throws Exception {
	  //System.setProperty("webdriver.chrome.driver", "E:\\zDung\\eclipse\\workspace\\test\\src\\test\\resources\\chromedriver.exe");
	  String S4BLocation = "C:\\Users\\MyPC\\AppData\\Local\\Programs\\RTC_Client_for_Skype_for_Business.S4B2015UCCSV\\RTC Client for Skype for Business - S4B2015UCCSV.exe";
	  
	  ChromeOptions options = new ChromeOptions();
      options.setBinary(S4BLocation);
      WebDriver driver = new ChromeDriver(options);
      
      
      sendWhenReady(driver, By.xpath("//*[@id=\"emailSkype\"]"), "ucc.user61@gbsolutions.work", 100);
      sendWhenReady(driver, By.xpath("//*[@id=\"passwordSkype\"]"), "Lkjh1234", 10);
	  clickWhenReady(driver, By.xpath("//*[@id=\"login-page\"]/div/div[3]/button"), 10);
	  
	
	  sendWhenReady(driver, By.xpath("//*[@id=\"usernameUC\"]"), "user_61@slr.com", 10);
      sendWhenReady(driver, By.xpath("//*[@id=\"passwordUC\"]"), "temp1234", 10);
      clickWhenReady(driver, By.xpath("//*[@id=\"login-page\"]/div/div[3]/button"), 10);
	  
            
      Thread.sleep(50000);
      driver.close();
	  driver.quit();
	}
}
