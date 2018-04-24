/**
 * Automatic webdriver instantiation
 */
package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import listeners.LogListener;
import listeners.RetryListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import org.testng.annotations.Listeners;


@Listeners({LogListener.class, RetryListener.class})
public class DriverBase {
	private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<WebDriver>();
	private static List<WebDriver> driverThreadPool = Collections.synchronizedList(new ArrayList<WebDriver>());

	@BeforeSuite
	@Parameters({"browserName", "remote", "gridURL", "desiredPlatform", "browserVersion"})
	public void DriverSetup(String browserName, String remote, String gridURL, String desiredPlatform, String browserVersion) {
	    final String browser = browserName;
		final String useRemote = remote;
		final String url = gridURL;
		final String flatform = desiredPlatform;
		final String version = browserVersion;
		driverThread = new ThreadLocal<WebDriver>() {
			@Override
			protected WebDriver initialValue() {
				final WebDriver driver = DriverType.getDriverType(browser, useRemote, url, flatform, version);
				driverThreadPool.add(driver);
				return driver;
			}
		};
	}

	
	/**
	  * Uses the getDriver() method  on the WebDriverThread object to pass each HelloController a WebDriver instance it can use
	  * @return browser Desired Capabilities which defined in WebDriverThread.java
	  */
	 public static WebDriver getDriver()  {
		if (driverThread.get() == null){
			System.out.println("!!! WebDriver is not established !!!");
			throw new NullPointerException();
		}
	 	return driverThread.get();
	 }

	 /**
	  * Automatic clear browser cookies after complete each HelloController so no need to close browser for next HelloController.
	  */
	 @AfterMethod
	 public static void clearCookies(){
		 getDriver().manage().deleteAllCookies();
		 // deleteAllCookies is not working with Safari Driver
		 // workaround solution: https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/5212
	 }

	 
	 /**
	  * Automatically destroy the driver instance in each thread (close all browsers) after running all HelloController
	  */
	 @AfterSuite
	 public static void quitDriver() {
		for (WebDriver driver : driverThreadPool) {
			if (driver != null) {
				driver.close();
				driver.quit();
			}
		} 
	 }
}
