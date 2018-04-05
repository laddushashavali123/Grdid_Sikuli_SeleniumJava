package utils.tma.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WebElementSupport2 {
	/**	Click support for Web element
		@param Elm : the Web element you need to action 
	 **/
	
	public static void clickSupport(WebElement Elm) throws Exception {
		
		Thread.sleep(1000);
		try {
			//System.out.println("Try to Click");
			Elm.click();
		} catch (Exception e1) {
			Thread.sleep(2000);
			try {
				Elm.click();
			} catch (Exception e2) {
				Elm.click();
			}	
		}
		
	}
	
	public static void clickSupport(WebElement Elm, int timeout) throws Exception {
		Thread.sleep(1000);
		//System.out.println("Try to Click");
		try {
			Elm.click();
		} catch (Exception e1) {
			Thread.sleep(timeout*1000);
			try {
				Elm.click();
			} catch (Exception e2) {
				Elm.click();
			}	
		}
	}
	
	public static void clickSupport(WebElement Elm, int timeout, int interval_miliseconds) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < timeout*1000/interval_miliseconds; i++) {
			try {
				Elm.click();
				break;	
			} catch (Exception e1) {
				if (!(i < timeout*1000/interval_miliseconds)) {
					Elm.click(); 
				}
			}
		}
		
	}
	
	public static void sendKeysSupport(WebElement Elm, String text) throws Exception {
		Thread.sleep(1000);
		try {
			//System.out.println("Try to Click");
			Elm.clear();
			Elm.sendKeys(text);
		} catch (Exception e1) {
			Thread.sleep(2000);
			try {
				Elm.sendKeys(text);
			} catch (Exception e2) {
				Elm.clear();
				Elm.sendKeys(text);
			}	
		}
		
	}
	

	public static void sendKeysSupport(WebElement Elm, String text, int timeout) throws Exception {
		Thread.sleep(1000);
		try {
			//System.out.println("Try to Click");
			Elm.clear();
			Elm.sendKeys(text);
		} catch (Exception e1) {
			Thread.sleep(timeout*1000);
			try {
				Elm.sendKeys(text);
			} catch (Exception e2) {
				Elm.clear();
				Elm.sendKeys(text);
			}	
		}
		
	}
	
	public static void sendKeysSupport(WebElement Elm, String text, int timeout, int interval_miliseconds) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < timeout*1000/interval_miliseconds; i++) {
			//System.out.println("HEREREE: " + i + "----" + timeout*1000/interval_miliseconds);
			//System.out.println("HEREREE: " + i + "----" + timeout*1000/interval_miliseconds/10);
			try {
				//System.out.println("Try to Click");
				Elm.clear();
				Elm.sendKeys(text);
				break;	
			} catch (Exception e2) {
				if ((i > timeout*1000/interval_miliseconds)) {
					Elm.clear();
					Elm.sendKeys(text);
				}
			}
		}
		
	}
	
	public static String getTextSupport(WebElement Elm) throws Exception {
		Thread.sleep(1000);
		String result = null;
		try {
			//System.out.println("Try to Click");
			result = Elm.getText();
		} catch (Exception e1) {
			Thread.sleep(2000);
			try {
				result = Elm.getText();
			} catch (Exception e2) {
				InterruptedException e = new InterruptedException(e2.getMessage());
				throw e;
			}	
		}
				return result;
		
	}
	
	public static String getTextSupport(WebElement Elm, int timeout) throws Exception {
		Thread.sleep(1000);
		String result = null;
		try {
			//System.out.println("Try to Click");
			result = Elm.getText();
		} catch (Exception e1) {
			Thread.sleep(timeout*1000);
			try {
				result = Elm.getText();
			} catch (Exception e2) {
				InterruptedException e = new InterruptedException(e2.getMessage());
				throw e;
			}	
		}
		return result;
		
	}
	
	public static String getTextSupport(WebElement Elm, int timeout, int interval_miliseconds) throws Exception {
		Thread.sleep(1000);
		String result = null;
		for (int i = 0; i < timeout*1000/interval_miliseconds; i++) {
			try {
				result = Elm.getText();
				break;	
			} catch (Exception e2) {
				if (!(i < timeout*1000/interval_miliseconds)) {
					result = Elm.getText();
					throw e2;
				}
					
			}
		}
		return result;
	}
	
	public static WebElement findElementByTextSupport(WebDriver driver, String text, int timeout) throws Exception {
		Thread.sleep(1000);
		Exception e1 = null;
		WebElement elm = null;
		String tmp_xapth = "//*[text()='" + text + "']";
		for (int i = 0; i < timeout; i++) {
			try {
				elm = driver.findElement(By.xpath(tmp_xapth));
				break;
			} catch (Exception e) {
				Thread.sleep(1000);
				e1 = e;
			}
			if (elm == null) {
				throw e1;
			}
		}
		return elm;
	}
	
	  /**
	   * Wait for element
	   * 
	   * @param timeout
	   *          the time out in seconds
	   * @param driver
	   *          Webdriver
	   * @param bySomething
	   * 		  Selenium.By
	   */
	public static WebElement findElementSupport(WebDriver driver, By bySomething, int timeout) throws Exception {
		Thread.sleep(1000);
		WebElement elm = null;
		Exception e1 = null;
		for (int i = 0; i < timeout; i++) {
			System.out.print("*");
			try {
				elm = driver.findElement(bySomething);
				break;
			} catch (Exception e) {
				Thread.sleep(1000);
				e1 = e;
			}
		}
		if (elm == null) {
			throw e1;
		}
		return elm;
	}
	
	public static WebElement findElementByXpathSupport(WebDriver driver, String text, int timeout) throws Exception {
		  Exception e1 = null;
		  WebElement elm = null;
		 
		  for (int i = 0; i < timeout; i++) {
		   try {
		    elm = driver.findElement(By.xpath(text));
		    break;
		   } catch (Exception e) {
		    Thread.sleep(1000);
		    e1 = e;
		   }
		  }
		  if (elm == null) {
		   throw e1;
		  }
		  return elm;
		 }
	
	public static WebElement findSupport(WebDriver driver, By something, int times, int interval) throws Exception {
		WebElement elm = null;
		Exception e1 = null;
		
		for (int i = 0; i < times; i++) {
			try {
				elm = driver.findElement(something);
				break;
			} catch (Exception e) {
				Thread.sleep(interval);
				e1 = e;
			}
		}
		if (elm == null) {
			throw e1;
		}
		return elm;
	}
}
