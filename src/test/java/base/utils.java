package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contain useful method. Below are some definition:
 * - Visibility means that the elements are not only displayed but also have a height and width that is greater than 0
 * 
 */
public class utils {
	/*
	 * Click on an element after it is visible.
	 * Timeout in second
	 */
	public static void clickWhenReady(WebDriver driver, By locator, int timeout) throws Exception {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();
	}
	public static void clickWhenReady(WebDriver driver, WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}
	
	/*
	 * Send text to element after it is visible.
	 * Timeout in second.
	 */
	public static void sendWhenReady(WebDriver driver, By locator, String text, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.sendKeys(text);
	}
	public static void sendWhenReady(WebDriver driver, WebElement element, String text, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.sendKeys(text);
	}
}
