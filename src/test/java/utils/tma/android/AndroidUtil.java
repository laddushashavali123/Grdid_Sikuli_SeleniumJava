/**
 * Copyright (C) 2014 WTF org.
 */

package utils.tma.android;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;


/**
 * WTF Utils. Collection of util methods for invoking browser automation calls
 * more easier and much stable.
 * 
 * @author venkatesan.sundramurthy@gmail.com (Venkatesan Sundramurthy)
 */
public class AndroidUtil {

  public static Long WAIT_TIMEOUT_IN_SECONDS = 30L;

  private AndroidUtil() {
    // Utility class
  }
    
  /**
   * This method to press system button go Back of device 
   * 
   * @param driver   
   */
  public static void pressBackButton(AndroidDriver driver)
  {
      driver.pressKeyCode(AndroidKeyCode.BACK);
  }
  
  /**
   * This method to press system button Home of device 
   * 
   * @param driver   
   */
  public static void pressHomeButton(AndroidDriver driver)
  {
      driver.pressKeyCode(AndroidKeyCode.HOME);
  }
  
  /**
   * This method to press system button Home of device 
   * 
   * @param driver   
   */
  public static void pressMenuButton(AndroidDriver driver)
  {
      driver.pressKeyCode(AndroidKeyCode.MENU);
  }
  
  /**
   * This method to check a page contains a given string 
   * 
   * @param driver   
   */
  public static Boolean PageContainString(AndroidDriver driver, String str) {
    return driver.getPageSource().contains(str);
  }
}
