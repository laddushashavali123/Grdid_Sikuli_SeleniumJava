package example;

import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;
import org.openqa.selenium.Platform;

public class issue {

	@Test
    public void google() throws Exception { 
    	URL seleniumGridURL = new URL("http://192.168.1.103:4444/wd/hub");
    	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    	FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
    	capabilities.setPlatform(Platform.valueOf("VISTA"));
    	WebDriver driver = new RemoteWebDriver(seleniumGridURL, capabilities);
    	driver.get("http://www.google.com");
    	driver.close();
    	driver.quit();
   }
    
	/*
	 * 
	 
	@Test
    public void google1() throws Exception { 
    	URL seleniumGridURL = new URL("http://192.168.1.103:4444/wd/hub");
    	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    	FirefoxOptions options = new FirefoxOptions();
        options.setBinary("C:/Program Files/Mozilla Firefox/firefox.exe");
        capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
    	capabilities.setPlatform(Platform.valueOf("VISTA"));
    	WebDriver driver = new RemoteWebDriver(seleniumGridURL, capabilities);
    	driver.get("http://www.google.com");
    	driver.close();
    	driver.quit();
   }
   */
}