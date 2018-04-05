/**
 * Copyright (C) 2014 Web org.
 */

package utils.tma.web;

import java.util.List;

import javax.xml.xpath.XPath;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static utils.tma.web.WebExpectedConditions.*;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Web Utils. Collection of util methods for invoking browser automation calls
 * more easier and much stable.
 * 
 * @author venkatesan.sundramurthy@gmail.com (Venkatesan Sundramurthy)
 */
public class WebUtil {

  public static Long WAIT_TIMEOUT_IN_SECONDS = 30L;
  public static final Logger logger = LoggerFactory.getLogger("WebUtil");

  public WebUtil() {
    // Utility class
  }

  public static WebDriverWait getDefaultWait(WebDriver driver) {
    return new WebDriverWait(driver, WAIT_TIMEOUT_IN_SECONDS);
  }

  /**
   * Updates the default wait time out seconds with the user provided value.
   * 
   * @param timeout
   *          the time out in seconds
   */
  public static void setDefaultWaitTimeout(Long timeout) {
    WAIT_TIMEOUT_IN_SECONDS = timeout;
  }

  /**
   * Refresh current page.
   * 
   * @param driver
   *          the WebDriver
   */
  public static void refresh(WebDriver driver) {
    driver.navigate().refresh();
  }

  /**
   * Clicks the web element using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   */
  public static void click(WebDriver driver, WebDriverWait wait, By by) {
    wait.until(elementToBeClickable(by)).click();
  }

  /**
   * Clicks the web element using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   */
  public static void click(WebDriver driver, By by) {
    click(driver, getDefaultWait(driver), by);
  }

  /**
   * Clicks the web element inside a sub DOM using driver, wait, parent and
   * locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   */
  public static void click(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    wait.until(elementToBeClickable_(parent, by)).click();
  }

  /**
   * Clicks the web element inside a sub DOM using driver, parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   */
  public static void click(WebDriver driver, WebElement parent, By by) {
    click(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Double Clicks the web element using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   */
  public static void doubleClick(WebDriver driver, By by) {
    Actions action = new Actions(driver);
    WebElement el = findElement(driver, by);
    action.doubleClick(el).perform();
  }

  /**
   * Safe javascript click
   * scrolling
   * 
   * @param driver
   */
  public static void safeJavaScriptClick(WebDriver driver, WebElement element) throws Exception { 
    try { 
      if (element.isEnabled() && element.isDisplayed()) { 
        logger.info("Clicking on element with using java script click");

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
      } else { 
        logger.warn("Unable to click on element");
      } 
    } catch (StaleElementReferenceException e) { 
        logger.error("Element is not attached to the page document "+ e.getStackTrace());
    } catch (NoSuchElementException e) { 
        logger.error("Element was not found in DOM "+ e.getStackTrace());
    } catch (Exception e) { 
        logger.error("Unable to click on element "+ e.getStackTrace());
    } 
  }

   /**
   * Clicks the web element inside a sub DOM using driver, parent and locator.
   * 
   * @param driver
   *          the WebDriver
   *
   * @param by
   *          locator used to find the element
   */
  public static void clickJquery(WebDriver driver, By by) {
    WebElement el = findElement(driver, by);
    try {
      safeJavaScriptClick(driver, el);
    } catch (Exception e) {
        logger.error("Unable to click on element "+ e.getStackTrace());
    }
  }

  /**
   * Safe javascript drag and drop
   * scrolling
   * 
   * @param driver
   */
  public static void safedragAndDrop(WebElement sourceElement, WebElement destinationElement, WebDriver driver) {
    try {
      if (sourceElement.isDisplayed() && destinationElement.isDisplayed()) {
          Actions action = new Actions(driver);
          action.dragAndDrop(sourceElement, destinationElement).build().perform();
      } else {
          logger.info("Element was not displayed to drag");
      }
    } catch (StaleElementReferenceException e) {
        logger.error("Element with " + sourceElement + "or" + destinationElement + "is not attached to the page document "
                + e.getStackTrace());
    } catch (NoSuchElementException e) {
        logger.error("Element " + sourceElement + "or" + destinationElement + " was not found in DOM "+ e.getStackTrace());
    } catch (Exception e) {
        logger.error("Error occurred while performing drag and drop operation "+ e.getStackTrace());
    }
  }

   /**
   * Drag and drop
   * 
   * @param driver
   *          the WebDriver
   *
   */
  public static void dragAndDrop(WebDriver driver, By bysrc, By bydst) {
    WebElement elsrc = findElement(driver, bysrc);
    WebElement eldst = findElement(driver, bydst);
    try {
      safedragAndDrop(elsrc, eldst, driver);
    } catch (Exception e) {
        logger.error("Unable to click on element "+ e.getStackTrace());
    }
  }

   /**
   * Right click 
   * 
   * @param driver
   *          the WebDriver
   *
   * @param by
   *          locator used to find the element
   */
  public void rightClick(WebDriver driver, By by) {
    WebElement element = findElement(driver, by);
    try {
      Actions action = new Actions(driver).contextClick(element);
      action.build().perform();

      logger.info("Sucessfully Right clicked on the element");
    } catch (StaleElementReferenceException e) {
      logger.error("Element is not attached to the page document "
          + e.getStackTrace());
    } catch (NoSuchElementException e) {
      logger.error("Element " + element + " was not found in DOM "
          + e.getStackTrace());
    } catch (Exception e) {
      logger.error("Element " + element + " was not clickable "
          + e.getStackTrace());
    }
  }

   /**
   * select autocomplete text 
   * 
   * @param driver
   *          the WebDriver
   *
   * @param by
   *          locator used to find the element
   */
  public void selectTextAutocomplete(WebDriver driver, By by, WebDriverWait wait, String textToSelect) {
    try {
      WebElement autoOptions = findElement(driver, by);
      wait.until(visibilityOfElementLocated(by));

      List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
      for(WebElement option : optionsToSelect){
        if(option.getText().equals(textToSelect)) {
          logger.info("Trying to select: "+textToSelect);
          option.click();
          break;
        }
      }
      
    } catch (NoSuchElementException e) {
      logger.error(String.valueOf(e.getStackTrace()));
    }
    catch (Exception e) {
      logger.error(String.valueOf(e.getStackTrace()));
    }
  }

  /**
   * Determines the web element's presence using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean present(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(presenceOfElementLocated(by)) != null;
  }

  /**
   * Determines the web element's presence using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean present(WebDriver driver, By by) {
    return present(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element's presence inside a sub DOM using driver, wait,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean present(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return wait.until(presenceOfElementLocated_(parent, by)) != null;
  }

  /**
   * Determines the web element's presence inside a sub DOM using driver, parent
   * and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean present(WebDriver driver, WebElement parent, By by) {
    return present(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the given web element is no longer present on the DOM using
   * driver, wait.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param el
   *          the web element to be checked
   * @return true if present
   */
  public static Boolean notPresent(WebDriver driver, WebDriverWait wait,
      WebElement el) {
    return wait.until(stalenessOf(el));
  }

  /**
   * Determines the given web element is no longer present on the DOM using
   * driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param el
   *          the web element to be checked
   * @return true if present
   */
  public static Boolean notPresent(WebDriver driver, WebElement el) {
    return notPresent(driver, getDefaultWait(driver), el);
  }

  /**
   * Determines the web element's visibility using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return true if visible
   */
  public static Boolean visible(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(visibilityOfElementLocated(by)) != null;
  }

  /**
   * Determines the web element's visibility using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return true if visible
   */
  public static Boolean visible(WebDriver driver, By by) {
    return visible(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element's visibility inside a sub DOM using driver,
   * wait, parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if visible
   */
  public static Boolean visible(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return wait.until(visibilityOfElementLocated_(parent, by)) != null;
  }

  /**
   * Determines the web element's visibility inside a sub DOM using driver,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if visible
   */
  public static Boolean visible(WebDriver driver, WebElement parent, By by) {
    return visible(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the web element's invisibility using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return true if invisible
   */
  public static Boolean invisible(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(invisibilityOfElementLocated(by));
  }

  /**
   * Determines the web element's invisibility using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return true if invisible
   */
  public static Boolean invisible(WebDriver driver, By by) {
    return invisible(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element's invisibility inside a sub DOM using driver,
   * wait, parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if invisible
   */
  public static Boolean invisible(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return wait.until(invisibilityOfElementLocated_(parent, by));
  }

  /**
   * Determines the web element's invisibility inside a sub DOM using driver,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if invisible
   */
  public static Boolean invisible(WebDriver driver, WebElement parent, By by) {
    return invisible(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Returns the matching web element using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return the WebElement once it is located
   */
  public static WebElement findElement(WebDriver driver, WebDriverWait wait,
      By by) {
    return wait.until(presenceOfElementLocated(by));
  }

  /**
   * Returns the matching web element using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return the WebElement once it is located
   */
  public static WebElement findElement(WebDriver driver, By by) {
    return findElement(driver, getDefaultWait(driver), by);
  }

  /**
   * Returns the matching web element using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   *
   * @return the WebElement once it is located
   */
  public static WebElement findElementText(WebDriver driver, String text) {
    String st = ".//*[contains(text(),'" + text + "')]";
    return findElement(driver, getDefaultWait(driver), By.xpath(st));
  }

  /**
   * Returns the matching web element inside a sub DOM using driver, wait,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return the WebElement once it is located
   */
  public static WebElement findElement(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return wait.until(presenceOfElementLocated_(parent, by));
  }

  /**
   * Returns the matching web element inside a sub DOM using driver, parent and
   * locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return the WebElement once it is located
   */
  public static WebElement findElement(WebDriver driver, WebElement parent,
      By by) {
    return findElement(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Returns the list of matching web elements using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return the list of WebElements once it is located
   */
  public static List<WebElement> findElements(WebDriver driver,
      WebDriverWait wait, By by) {
    return wait.until(presenceOfAllElementsLocatedBy(by));
  }

  /**
   * Returns the matching web element using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   *
   * @return the WebElement once it is located
   */
  public static List<WebElement> findElementsText(WebDriver driver,  WebDriverWait wait, String text) {
    String st = ".//*[contains(text(),'" + text + "')]";
    return wait.until(presenceOfAllElementsLocatedBy(By.xpath(st)));
  }

  /**
   * Returns the list of matching web elements using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return the list of WebElements once it is located
   */
  public static List<WebElement> findElements(WebDriver driver, By by) {
    return findElements(driver, getDefaultWait(driver), by);
  }

  /**
   * Returns the list of matching web elements inside a sub DOM using driver,
   * wait, parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return the list of WebElements once it is located
   */
  public static List<WebElement> findElements(WebDriver driver,
      WebDriverWait wait, WebElement parent, By by) {
    return wait.until(presenceOfAllElementsLocatedBy_(parent, by));
  }

  /**
   * Returns the list of matching web elements inside a sub DOM using driver,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return the list of WebElements once it is located
   */
  public static List<WebElement> findElements(WebDriver driver,
      WebElement parent, By by) {
    return findElements(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the web element is selected using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean selected(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(elementSelectionStateToBe(by, true));
  }

  /**
   * Determines the web element is selected using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean selected(WebDriver driver, By by) {
    return selected(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element is selected inside a sub DOM using driver, wait,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean selected(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return wait.until(elementSelectionStateToBe_(parent, by, true)) != null;
  }

  /**
   * Determines the web element is selected inside a sub DOM using driver,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean selected(WebDriver driver, WebElement parent, By by) {
    return selected(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the web element is not selected using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean notSelected(WebDriver driver, WebDriverWait wait, By by) {
    return wait.until(elementSelectionStateToBe(by, false));
  }

  /**
   * Determines the web element is not selected using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean notSelected(WebDriver driver, By by) {
    return notSelected(driver, getDefaultWait(driver), by);
  }

  /**
   * Determines the web element is not selected inside a sub DOM using driver,
   * wait, parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean notSelected(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return wait.until(elementSelectionStateToBe_(parent, by, false));
  }

  /**
   * Determines the web element is not selected inside a sub DOM using driver,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return true if present
   */
  public static Boolean notSelected(WebDriver driver, WebElement parent, By by) {
    return notSelected(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Types the given text using the driver, wait locator, text.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be typed
   */
  public static void type(WebDriver driver, WebDriverWait wait, By by,
      String text) {
    wait.until(visibilityOfElementLocated(by)).sendKeys(text);
  }

  /**
   * Types the given text using the using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be typed
   */
  public static void type(WebDriver driver, By by, String text) {
    type(driver, getDefaultWait(driver), by, text);
  }

  /**
   * Types the given text on an element inside a sub DOM using driver, wait,
   * parent, locator and text.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be typed
   */
  public static void type(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by, String text) {
    wait.until(visibilityOfElementLocated_(parent, by)).sendKeys(text);
  }

  /**
   * Types the given text on an element inside a sub DOM using driver, parent,
   * locator and text
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be typed
   */
  public static void type(WebDriver driver, WebElement parent, By by,
      String text) {
    type(driver, getDefaultWait(driver), parent, by, text);
  }

  /**
   * Clears the text using the given driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   */
  public static void clear(WebDriver driver, WebDriverWait wait, By by) {
    wait.until(visibilityOfElementLocated(by)).clear();
  }

  /**
   * Clears the text using the given driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   */
  public static void clear(WebDriver driver, By by) {
    clear(driver, getDefaultWait(driver), by);
  }

  /**
   * Clears the text on an element inside a sub DOM using driver, wait, parent,
   * locator and text.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   */
  public static void clear(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    wait.until(visibilityOfElementLocated_(parent, by)).clear();
  }

  /**
   * Clears the text on an element inside a sub DOM using driver, parent and
   * locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   */
  public static void clear(WebDriver driver, WebElement parent, By by) {
    clear(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Determines the give text is present on a web element using driver, wait and
   * locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be looked for
   */
  @SuppressWarnings("deprecation")
  public static Boolean textPresent(WebDriver driver, WebDriverWait wait,
      By by, String text) {
    return wait.until(textToBePresentInElement(by, text));
  }

  /**
   * Determines the give text is present on a web element using driver, locator
   * and text.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be looked for
   */
  public static Boolean textPresent(WebDriver driver, By by, String text) {
    return textPresent(driver, getDefaultWait(driver), by, text);
  }

  /**
   * Determines the give text is present on a web element inside a sub DOM using
   * driver, wait, parent, locator and text.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be looked for
   */
  public static Boolean textPresent(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by, String text) {
    return wait.until(textToBePresentInElement_(parent, by, text));
  }

  /**
   * Determines the give text is present on a web element inside a sub DOM using
   * driver, parent, locator, text.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be looked for
   */
  public static Boolean textPresent(WebDriver driver, WebElement parent, By by,
      String text) {
    return textPresent(driver, getDefaultWait(driver), parent, by, text);
  }

  /**
   * Determines the give text not present on a web element using driver, wait,
   * locator and text.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be looked for
   */
  public static Boolean textNotPresent(WebDriver driver, WebDriverWait wait,
      By by, String text) {
    return wait.until(invisibilityOfElementWithText(by, text));
  }

  /**
   * Determines the give text not present on a web element using driver, locator
   * and text.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be looked for
   */
  public static Boolean textNotPresent(WebDriver driver, By by, String text) {
    return textNotPresent(driver, getDefaultWait(driver), by, text);
  }

  /**
   * Determines the give text not present on a web element inside a sub DOM
   * using driver, wait, parent, locator and text.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be looked for
   */
  public static Boolean textNotPresent(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by, String text) {
    return wait.until(invisibilityOfElementWithText_(parent, by, text));
  }

  /**
   * Determines the give text not present on a web element inside a sub DOM
   * using driver, parent, locator, text.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param text
   *          the text to be looked for
   */
  public static Boolean textNotPresent(WebDriver driver, WebElement parent,
      By by, String text) {
    return textNotPresent(driver, getDefaultWait(driver), parent, by, text);
  }

  /**
   * Determines the give attribute name and value present on a web element using
   * driver, wait, locator, attribute name and attribute value.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @param attributeName
   *          the attribute name
   * @param attributeValue
   *          the attribute value
   */
  public static Boolean attributeValuePresent(WebDriver driver,
      WebDriverWait wait, By by, String attributeName, String attributeValue) {
    return wait.until(attributeValueToBePresentInElement(by, attributeName,
        attributeValue));
  }

  /**
   * Determines the give attribute name and value present on a web element using
   * driver, locator, attribute name and attribute value.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @param attributeName
   *          the attribute name
   * @param attributeValue
   *          the attribute value
   */
  public static Boolean attributeValuePresent(WebDriver driver, By by,
      String attributeName, String attributeValue) {
    return attributeValuePresent(driver, getDefaultWait(driver), by,
        attributeName, attributeValue);
  }

  /**
   * Determines the give attribute name and value present on a web element
   * inside a sub DOM using driver, wait, parent, locator, attribute name and
   * attribute value.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param attributeName
   *          the attribute name
   * @param attributeValue
   *          the attribute value
   */
  public static Boolean attributeValuePresent(WebDriver driver,
      WebDriverWait wait, WebElement parent, By by, String attributeName,
      String attributeValue) {
    return wait.until(attributeValueToBePresentInElement_(parent, by,
        attributeName, attributeValue));
  }

  /**
   * Determines the give attribute name and value present on a web element
   * inside a sub DOM using driver, parent, locator, attribute name and
   * attribute value.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param attributeName
   *          the attribute name
   * @param attributeValue
   *          the attribute value
   */
  public static Boolean attributeValuePresent(WebDriver driver,
      WebElement parent, By by, String attributeName, String attributeValue) {
    return attributeValuePresent(driver, getDefaultWait(driver), parent, by,
        attributeName, attributeValue);
  }

  /**
   * Gets the attribute value for the give attribute name on a web element using
   * driver, wait, locator and attribute name.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @param attributeName
   *          the attribute name
   * @return attribute value
   */
  public static String getAttributeValue(WebDriver driver, WebDriverWait wait,
      By by, String attributeName) {
    return findElement(driver, wait, by).getAttribute(attributeName);
  }

  /**
   * Gets the attribute value for the give attribute name on a web element using
   * driver, locator and attribute name.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @param attributeName
   *          the attribute name
   * @return attribute value
   */
  public static String getAttributeValue(WebDriver driver, By by,
      String attributeName) {
    return getAttributeValue(driver, getDefaultWait(driver), by, attributeName);
  }

  /**
   * Gets the attribute value for the give attribute name on a web element
   * inside a sub DOM using driver, wait, parent, locator and attribute name.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param attributeName
   *          the attribute name
   * @return attribute value
   */
  public static String getAttributeValue(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by, String attributeName) {
    return findElement(driver, wait, parent, by).getAttribute(attributeName);
  }

  /**
   * Gets the attribute value for the give attribute name on a web element
   * inside a sub DOM using driver, parent, locator and attribute name.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @param attributeName
   *          the attribute name
   * @return attribute value
   */
  public static String getAttributeValue(WebDriver driver, WebElement parent,
      By by, String attributeName) {
    return getAttributeValue(driver, getDefaultWait(driver), parent, by,
        attributeName);
  }

  /**
   * Gets the text from a web element using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return text
   */
  public static String getText(WebDriver driver, WebDriverWait wait, By by) {
    return findElement(driver, wait, by).getText();
  }

  /**
   * Gets the text from a web element using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return text
   */
  public static String getText(WebDriver driver, By by) {
    return getText(driver, getDefaultWait(driver), by);
  }

  /**
   * Gets the text from a web element inside a sub DOM using driver, wait,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return text
   */
  public static String getText(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return findElement(driver, wait, parent, by).getText();
  }

  /**
   * Gets the text from a web element inside a sub DOM using driver, parent and
   * locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return text
   */
  public static String getText(WebDriver driver, WebElement parent, By by) {
    return getText(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Gets the value from a web element using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return value
   */
  public static String getValue(WebDriver driver, WebDriverWait wait, By by) {
    return getAttributeValue(driver, by, "value");
  }

  /**
   * Gets the value from a web element using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return value
   */
  public static String getValue(WebDriver driver, By by) {
    return getValue(driver, getDefaultWait(driver), by);
  }

  /**
   * Gets the value from a web element inside a sub DOM using driver, wait,
   * parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return value
   */
  public static String getValue(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return getAttributeValue(driver, wait, parent, by, "value");
  }

  /**
   * Gets the value from a web element inside a sub DOM using driver, parent and
   * locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return value
   */
  public static String getValue(WebDriver driver, WebElement parent, By by) {
    return getValue(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Gets the Select element using using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   * @return Select object
   */
  public static Select getSelectElement(WebDriver driver, WebDriverWait wait,
      By by) {
    return new Select(findElement(driver, wait, by));
  }

  /**
   * Gets the Select element using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   * @return Select object
   */
  public static Select getSelectElement(WebDriver driver, By by) {
    return getSelectElement(driver, getDefaultWait(driver), by);
  }

  /**
   * Gets the Select element inside a sub DOM using driver, wait, parent and
   * locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return Select value
   */
  public static Select getSelectElement(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    return new Select(findElement(driver, wait, parent, by));
  }

  /**
   * Gets the Select element inside a sub DOM using driver, parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   * @return Select value
   */
  public static Select getSelectElement(WebDriver driver, WebElement parent,
      By by) {
    return getSelectElement(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Hovers on element using driver, wait and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param by
   *          locator used to find the element
   */
  public static void hover(WebDriver driver, WebDriverWait wait, By by) {
    new Actions(driver).moveToElement(findElement(driver, by)).build()
        .perform();
  }

  /**
   * Hovers on element using driver and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param by
   *          locator used to find the element
   */
  public static void hover(WebDriver driver, By by) {
    findElement(driver, by);
    hover(driver, getDefaultWait(driver), by);
  }

  /**
   * Hovers on element inside a sub DOM using driver, wait, parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param wait
   *          the WebDriverWait
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   */
  public static void hover(WebDriver driver, WebDriverWait wait,
      WebElement parent, By by) {
    new Actions(driver).moveToElement(findElement(driver, parent, by)).build()
        .perform();
  }

  /**
   * Hovers on element inside a sub DOM using driver, parent and locator.
   * 
   * @param driver
   *          the WebDriver
   * @param parent
   *          the sub DOM to look
   * @param by
   *          locator used to find the element
   */
  public static void hover(WebDriver driver, WebElement parent, By by) {
    hover(driver, getDefaultWait(driver), parent, by);
  }

  /**
   * Use this method only when browser based scrolling is not implemented in the
   * application code Scrolls vertically If isUpward is true scrolls bottom to
   * top and vice versa
   * 
   * @param driver
   * @param locator
   * @param vertPixelsToScroll
   * @param pixelsPerDrag
   * @param isUpward
   */
  public static void scrollNonBrowserScrollersVertically(WebDriver driver,
      By locator, int vertPixelsToScroll, int pixelsPerDrag, boolean isUpward) {
    Actions dragger = new Actions(driver);
    WebElement draggablePartOfScrollbar = null;
    draggablePartOfScrollbar = driver.findElement(locator);
    if (locator != null) {
      if (isUpward) {
        for (int currentPos = 0; currentPos >= (0 - vertPixelsToScroll); currentPos = currentPos
            - pixelsPerDrag) {
          dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
              .moveByOffset(0, currentPos).release().perform();
          draggablePartOfScrollbar = driver.findElement(locator);
        }
      } else {
        for (int currentPos = 0; currentPos <= vertPixelsToScroll; currentPos = currentPos
            + pixelsPerDrag) {
          dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
              .moveByOffset((currentPos - pixelsPerDrag), currentPos).release()
              .perform();
          draggablePartOfScrollbar = driver.findElement(locator);
        }
      }
    }
  }

  /**
   * Use this method only when browser based scrolling is not implemented in the
   * application code. Scrolls horizontally If isRightToLeft parameter is true
   * then it will scroll from right to left, else it will scroll left to right.
   * 
   * @param driver
   * @param locator
   * @param horPixelsToScroll
   * @param pixelsPerDrag
   * @param isRightToLeft
   */
  public static void scrollNonBrowserScrollersHorizontally(WebDriver driver,
      By locator, int horPixelsToScroll, int pixelsPerDrag,
      boolean isRightToLeft) {
    Actions dragger = new Actions(driver);
    WebElement draggablePartOfScrollbar = driver.findElement(locator);
    if (locator != null) {
      if (isRightToLeft) {
        for (int currentPos = 0; currentPos >= (0 - horPixelsToScroll); currentPos = currentPos
            - pixelsPerDrag) {
          dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
              .moveByOffset(currentPos, 0).release().perform();
        }
      } else {
        for (int currentPos = 0; currentPos <= horPixelsToScroll; currentPos = currentPos
            + pixelsPerDrag) {
          dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
              .moveByOffset(currentPos, 0).release().perform();
        }
      }
    }
  }

  /**
   * Scroll to bottom of the page, this method supports only browser based
   * scrolling
   * 
   * @param driver
   */
  public static void scrollToBottom(WebDriver driver) {
    ((JavascriptExecutor) driver)
        .executeScript("window.scrollTo(0,document.body.scrollHeight);");
  }

  /**
   * Scroll back to top of the page this method supports only browser based
   * scrolling
   * 
   * @param driver
   */
  public static void scrollTop(WebDriver driver) {
    ((JavascriptExecutor) driver)
        .executeScript("return document.documentElement.scrollTop");
  }

  public static int getRandomIndexForDD(int min, int max) {
    int index = (int) (Math.random() * (max - min)) + min;
    if (index >= max)
      index = index - 1;
    return index;
  }

  /**
   * This method randomly selects an option from the drop down. if the select
   * box uses select tag and options uses option tags like shown. <select>
   * <option> optionToSelect </option> </select>
   * 
   * @param driver
   * @param by
   */

  public static void selectOptionFromDropDown(WebDriver driver, By by) {
    WebElement select = driver.findElement(by);
    List<WebElement> options = select.findElements(By.tagName("option"));
    int randomIndexToSelect = getRandomIndexForDD(1, options.size());
    options.get(randomIndexToSelect).click();
  }

  /**
   * Selects option from drop down with a given text if the select box uses
   * select tag and options uses option tags like shown. <select> <option>
   * optionToSelect </option> </select>
   * 
   * @param driver
   * @param optionToSelect
   */
  public static void selectOptionFromDropDown(WebDriver driver, By by,
      String optionToSelect) {
    WebElement select = driver.findElement(by);
    List<WebElement> options = select.findElements(By.tagName("option"));
    for (WebElement option : options) {
      if (optionToSelect.equals(option.getText())) {
        option.click();
        break;
      }
    }
  }

  /**
   * Selects option from drop down with a given option index, if the select box
   * uses select tag and options uses option tags like shown. <select> <option>
   * optionToSelect </option> </select>
   * 
   * @param driver
   * @param optionToSelect
   * @param optIdx
   */
  public static void selectOptionFromDropDown(WebDriver driver, By by,
      String optionToSelect, int optIdx) {
    WebElement select = driver.findElement(by);
    List<WebElement> options = select.findElements(By.tagName("option"));
    options.get(optIdx).click();
  }

  /**
   * This method randomly selects an option from the list of the options. Such
   * as <li>or a list of menu items created with <div>. Make sure that you
   * provide the absolute locator of Menu items
   * 
   * @param driver
   * @param by
   */
  public static void selectOptionRandomlyFromMenuList(WebDriver driver, By by) {
    List<WebElement> allSelectableOptions = driver.findElements(by);
    int randomIndexToSelect = getRandomIndexForDD(1,
        allSelectableOptions.size());
    allSelectableOptions.get(randomIndexToSelect).click();
  }
  
  /**
   * This method to wait for an element appears
   * 
   * @param element
 * @throws Exception 
   */
  
  public static void waitFor_Element(WebElement element, int timeout_seconds) throws Exception {
	   boolean result = false;
	   Exception e = new Exception("Unable to find element after " + timeout_seconds + " seconds");
	   for (int i = 0; i < timeout_seconds; i++) {
		   System.out.println("Wait: " + i);
	    //Thread.sleep(1000);
	    try {
	     if (!element.getAttribute("class").contains("ng-hide") & element.isDisplayed()){
	      result = true;
	      break;
	     }
	    } catch (Exception e1) {
	     e = e1;
	    }
	   }
	   if (!result) throw e;
	  }
  
  /**
   * This method to wait for an element appears
   * 
   * @param driver
   * @param element
 * @throws Exception 
   */
  public void waitFor_Element(WebDriver driver, WebElement element, int timeout_seconds) throws InterruptedException {
   WebDriverWait wait = new WebDriverWait(driver, timeout_seconds);
   wait.until(ExpectedConditions.visibilityOf(element));
  }
  
  /**
   * This method to wait for a text appears
   *
   * @param text
   */
  public static void waitFor_Text(WebDriver driver, String text, int timeout_seconds) throws Exception {
	  boolean result = false;
	  Exception e = new Exception("Unable to find element after " + timeout_seconds + " seconds");
	  for (int i = 0; i < timeout_seconds; i++) {
	   //Thread.sleep(1000);
	   try {
	    findElementText(driver, text);
	    result = true;
	    break;
	   } catch (Exception e1) {
	    e = e1;
	   }
	  }
	  if (!result) throw e;
	 }
  
  /**
   * This method to wait for page until loaded data
   */
  public static void waitFor_LoadingFinished(WebDriver driver1, int timeout_seconds) throws InterruptedException {
   boolean result = false;
   for (int i = 0; i < timeout_seconds; i++) {
	   Thread.sleep(1000);   
	   List<WebElement> elms = driver1.findElements(By.className("progress_bar"));
	   for (int j = 0; j < elms.size(); j++) {
		if (elms.get(j).isDisplayed() == true) {
			result = false;
			break;
			}
		result = true;
	   	}
	   if (result) break;
   }
    System.out.println(result);
    Thread.sleep(2000);
    if (!result) System.out.println("Unable to find element after " + timeout_seconds + " seconds");
  }
  
  /**
   * This method to wait for page until loaded data
   */
  
  public static void waitFor_LoadingFinished(WebDriver driver, int timeout_seconds, String id) throws InterruptedException {
	    boolean result = true;     
	    for (int i = 0; i < timeout_seconds; i++) {
	     result = true;
	     Thread.sleep(1000);  
	     List<WebElement> listProgress = driver.findElements(By.id(id));
	     System.out.println("number of progress order: " +listProgress.size());
	     for(int j=0;j< listProgress.size(); j++){
	      System.out.println("progress bar: " + j + " . is Display: " + listProgress.get(j).isDisplayed());
	      if (listProgress.get(j).isDisplayed() == true) {      
	       result = false;
	       break;
	      }
	     }
	     if(result == true){
	      break;
	     }
	    }
	     System.out.println(result);
	     if (!result) System.out.println("Unable to find element after " + timeout_seconds + " seconds");
	   }
  


}
