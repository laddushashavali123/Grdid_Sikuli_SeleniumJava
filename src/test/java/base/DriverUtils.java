package base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * This class contain useful method. Below are some definition:
 * - Visible means that the element is not only displayed but also have a height and width that is greater than 0.
 * - Click-able means that the element is visible and not disabled.
 */
public class DriverUtils {

	public static boolean isElementVisible(By locator, int timeOutInSeconds){
		try {
			WebDriver driver = DriverBase.getDriver();
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	/**
	 * Click on an element after it is visible and click-able even it is not load in DOM yet.
     * Used when DOM is changed
	 */
	public static void clickWhenReady(By locator, int timeOutInSeconds) throws Exception {
		WebDriver driver = DriverBase.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
	}

    /**
     * Click on an element after it is visible and click-able but this element must load in DOM first.
     * Used when element is disabled
     */
	public static void clickWhenReady(WebElement element, int timeOutInSeconds) throws Exception {
		WebDriver driver = DriverBase.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

    /******************************************************************************************************************
     * Javascript click on an element after it is visible and click-able even it is not load in DOM yet.
	 * Use when normal click does not work and required Javascript action
     */
    public static void clickWhenReadyJavascript (By locator, int timeOutInSeconds) throws Exception {
		WebDriver driver = DriverBase.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click()", element);
    }

	public static void clickWhenReadyJavascript (WebElement element, int timeOutInSeconds) throws Exception {
		WebDriver driver = DriverBase.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click()", element);
	}

	/******************************************************************************************************************
	 * Send text to element after it is visible and click-able even it is not load in DOM yet.
     * Used when DOM is changed.
	 */
	public static void sendWhenReady(By locator, String text, int timeOutInSeconds) throws Exception {
		WebDriver driver = DriverBase.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.sendKeys(text);
	}
    /**
     * Send text to element after it is visible and click-able even it is not load in DOM yet.
     * Most use when element is disabled.
     */
	public static void sendWhenReady(WebElement element, String text, int timeOutInSeconds) throws Exception {
		WebDriver driver = DriverBase.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(text);
	}

	/******************************************************************************************************************
	 * Accept alert when it is displayed
	 */
	public static void acceptAlert(int timeOutInSeconds){
		WebDriver driver = DriverBase.getDriver();
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	/******************************************************************************************************************
	 * Press a key in real Keyboard
	 */
	public static void pressKeyboard(int KeyEvent) throws AWTException, InterruptedException {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent);
		robot.keyRelease(KeyEvent);
		Thread.sleep(100);
	}
}
