/**
 * DriverType class create instances of WebDriver based upon the browser flavor.
 * If no browser name is matched then use FireFox browser as default.
 */
package base;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.Platform;

public class DriverType {
	
	static WebDriver getDriverType(String browser, String remote, String GridURL, String platform, String browserVersion ) {
		WebDriver driver = null;
	
		DesiredCapabilities capabilities = getDesiredCapabilities(browser);
		
		if (remote.equalsIgnoreCase("y")) {
			URL seleniumGridURL = null;
			try {
				seleniumGridURL = new URL(GridURL);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			String desiredBrowserVersion = browserVersion;
			String desiredPlatform = platform;
			if (null != desiredPlatform && !desiredPlatform.isEmpty()) {
				capabilities.setPlatform(Platform.valueOf(desiredPlatform.toUpperCase()));
	    	}
	    	if (null != desiredBrowserVersion && !desiredBrowserVersion.isEmpty()) {
	    		capabilities.setVersion(desiredBrowserVersion);
	    	}
	    	driver = new RemoteWebDriver(seleniumGridURL, capabilities);
	    	System.out.println("Remote driver is created on thread " + Thread.currentThread().getId());
		}
		else {
			driver = getLocalDriver(browser, capabilities);
			System.out.println("Local driver is created on thread " + Thread.currentThread().getId());
		}
		return driver;
	}
	
	/**
	 * If you want to add more Capabilities for browser modify this method
	 * @param browser
	 * @return WebDriver Capabilities for selected browser
	 */
	static DesiredCapabilities getDesiredCapabilities(String browser) {
		DesiredCapabilities capabilities = null;
		if (browser.equalsIgnoreCase("chrome")){
			capabilities = DesiredCapabilities.chrome();
		    // Turn off default browser selection and remember password
		    capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
		    HashMap<String, String> chromePreferences = new HashMap<String, String>();
		    chromePreferences.put("profile.password_manager_enabled", "false");
		    capabilities.setCapability("chrome.prefs", chromePreferences);
		}
		else if (browser.equalsIgnoreCase("ie")) {
			capabilities = DesiredCapabilities.internetExplorer();
		    capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		    capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, true);
		    capabilities.setCapability("requireWindowFocus", true);
		    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
		}
		else if (browser.equalsIgnoreCase("safari")) {
			capabilities = DesiredCapabilities.safari();
		    capabilities.setCapability("safari.cleanSession", true);
		}
		// can use this for HelloController COC COC browser
		else if (browser.equalsIgnoreCase("electron")){
			String S4BLocation = "C:\\Users\\test1\\AppData\\Local\\Programs\\RTC_Client_for_Skype_for_Business.S4B2015UCCSV\\RTC Client for Skype for Business - S4B2015UCCSV.exe";
			ChromeOptions options = new ChromeOptions();
			options.setBinary(S4BLocation);
			capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		}
		else {
			capabilities = DesiredCapabilities.firefox();
		}
		return capabilities;
	}
	
	/**
	 * This method will return the correct local browser
	 */
	static WebDriver getLocalDriver(String browser, DesiredCapabilities capabilities) {
		if (browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("webdriver.chrome.driver"));
			ChromeOptions options = (ChromeOptions) new ChromeOptions().merge(capabilities);
			return new ChromeDriver(options);
		}
		else if (browser.equalsIgnoreCase("ie")) {
			InternetExplorerOptions options = (InternetExplorerOptions) new InternetExplorerOptions().merge(capabilities);
			return new InternetExplorerDriver(options);
		}
		else if (browser.equalsIgnoreCase("safari")) {
			SafariOptions options = (SafariOptions) new SafariOptions().merge(capabilities);
			return new SafariDriver(options);
		}
		else if (browser.equalsIgnoreCase("electron")){
			String S4BLocation = "C:\\Users\\MyPC\\AppData\\Local\\Programs\\RTC_Client_for_Skype_for_Business.S4B2015UCCSV\\RTC Client for Skype for Business - S4B2015UCCSV.exe";
			ChromeOptions options = new ChromeOptions();
			options.setBinary(S4BLocation);
			return new ChromeDriver(options);
		}
		else {
			System.setProperty("webdriver.gecko.driver", System.getProperty("firefox.driver.path"));
			FirefoxOptions options = new FirefoxOptions().merge(capabilities);
			return new FirefoxDriver(options);
		}
	}
}
