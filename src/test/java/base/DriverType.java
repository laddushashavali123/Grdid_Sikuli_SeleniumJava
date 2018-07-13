/**
 * DriverType class create instances of WebDriver based upon the browser flavor.
 * If no browser name is matched then use FireFox browser as default.
 */
package base;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
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
import org.openqa.selenium.Proxy;

import static org.openqa.selenium.Proxy.ProxyType.MANUAL;

class DriverType {
	
	static WebDriver getDriverType(String browserName, String useRemote, String GridURL, String platform, String browserVersion,
								   String proxyEnabled, String proxyHost, String proxyPort) {
		WebDriver driver;
		DesiredCapabilities capabilities = getDesiredCapabilities(browserName);

		// Add proxy
		if (proxyEnabled.equalsIgnoreCase("y") || proxyEnabled.equalsIgnoreCase("yes")){
			String proxyDetails = proxyHost + ":" + proxyPort;
			System.out.println("Proxy is used as " + proxyDetails);
			Proxy proxy = new Proxy();
			proxy.setProxyType(MANUAL);
			proxy.setHttpProxy(proxyDetails);
			proxy.setSslProxy(proxyDetails);
			capabilities.setCapability(CapabilityType.PROXY, proxy);
		}
		
		if (useRemote.equalsIgnoreCase("y") || useRemote.equalsIgnoreCase("yes")) {
			URL seleniumGridURL = null;
			try {
				seleniumGridURL = new URL(GridURL);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}

			if (null != platform && !platform.isEmpty()) {
				capabilities.setPlatform(Platform.valueOf(platform.toUpperCase()));
	    	}
	    	if (null != browserVersion && !browserVersion.isEmpty()) {
	    		capabilities.setVersion(platform);
	    	}
	    	driver = new RemoteWebDriver(seleniumGridURL, capabilities);
	    	System.out.println("Remote driver is created on thread " + Thread.currentThread().getId());
		}
		else {
			driver = getLocalDriver(browserName, capabilities);
			System.out.println("Local driver is created on thread " + Thread.currentThread().getId());
		}
		return driver;
	}

	/**
	 * If you want to add more Capabilities for browser modify this method
	 * @param browser is browser name
	 * @return WebDriver Capabilities for selected browser
	 */
	private static DesiredCapabilities getDesiredCapabilities(String browser) {
		DesiredCapabilities capabilities;
		if (browser.equalsIgnoreCase("chrome")){
			capabilities = DesiredCapabilities.chrome();
		    // Turn off default browser selection and remember password
		    capabilities.setCapability("chrome.switches", Collections.singletonList("--no-default-browser-check"));
		    HashMap<String, String> chromePreferences = new HashMap<>();
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
		// can use this for COC COC browser and change hard location to dynamic system variable
		else if (browser.equalsIgnoreCase("electron")){
			String ChromeniumLocation = "xxx";
			ChromeOptions options = new ChromeOptions();
			options.setBinary(ChromeniumLocation);
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
	private static WebDriver getLocalDriver(String browser, DesiredCapabilities capabilities) {
		if (browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("webdriver.chrome.driver"));
			ChromeOptions options = new ChromeOptions().merge(capabilities);
			return new ChromeDriver(options);
		}
		else if (browser.equalsIgnoreCase("ie")) {
			InternetExplorerOptions options = new InternetExplorerOptions().merge(capabilities);
			return new InternetExplorerDriver(options);
		}
		else if (browser.equalsIgnoreCase("safari")) {
			SafariOptions options = new SafariOptions().merge(capabilities);
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
