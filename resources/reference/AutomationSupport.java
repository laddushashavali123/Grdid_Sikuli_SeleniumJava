package automation.utils.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


public class AutomationSupport {
	public static int latencyRate = 80;
	private static int interval_miliseconds = 1000;
	public static final Logger logger = LogManager.getLogger("WebElementSupport2");


	public static void clickSupport(WebElement Elm, int timeout) throws Exception {
		for (int i = 0; i < timeout; i++) {
			if(i == 1) logger.info("Try to Click (Max: " + timeout + " seconds)");
			Thread.sleep(800);
			try {	
				if(Elm.getAttribute("disabled") == null || ! (Elm.getAttribute("disabled").equals("true") || Elm.getAttribute("disabled").equals("disabled"))){
					Elm.click();	
					if (i > 1) System.out.println("");
					break;
				} else{
				}
			} catch (Exception e) {
				if (!(i < timeout-1)) {
					System.out.println("");
					Elm.click();
					throw e;
				}
			}
			if (i > 0) System.out.print(".");
		}	
	}

	public static void clickSupport(By By, WebDriver driver, int timeOut) throws Exception {
		for (int i = 0; i < timeOut; i++) {
			if(i == 1) logger.info("Try to Click (Max: " + timeOut + " seconds)");
			Thread.sleep(800);
			try {
				WebElement Elm = driver.findElement(By);
				if(Elm.getAttribute("disabled") == null || ! (Elm.getAttribute("disabled").equals("true") || Elm.getAttribute("disabled").equals("disabled"))){
					Elm.click();
					if (i > 1) System.out.println("");
					break;
				} else{
					logger.info("temporary unable to click: Item is disabled");
				}
			} catch (Exception e) {
				if (!(i < timeOut - 1)) {
					WebElement Elm = driver.findElement(By);
					System.out.println("");
					Elm.click();
					throw e;
				}
			}
			if (i > 0) System.out.print(".");
		}
	}


	public static void sendKeysSupport(WebElement Elm, String text, int timeout) throws Exception {
		for (int i = 0; i < timeout; i++) {
			if(i == 1) logger.info("Try to Send Key '" + text + "' (Max: " + timeout + " seconds)");
			Thread.sleep(800);
			try {				
				Elm.clear();
				Elm.sendKeys(text);
				if (i > 1) System.out.println("");
				break;	
			} catch (Exception e) {
				if (!(i < timeout - 1)) {
					System.out.println("");
					Elm.clear();
					Elm.sendKeys(text);
					throw e;

				}
			}
			if (i > 0) System.out.print(".");
		}	
	}

	public static void sendKeysSupport(By By, WebDriver driver, String text, int timeOut) throws Exception {
		for (int i = 0; i < timeOut; i++) {
			if(i == 1) logger.info("Try to Send Key '" + text + "' (Max: " + timeOut + " seconds)");
			Thread.sleep(800);
			try {
				WebElement Elm = driver.findElement(By);
				Elm.clear();
				Elm.sendKeys(text);
				if (i > 1) System.out.println("");
				break;	
			} catch (Exception e) {
				if (!(i < timeOut -1)) {
					System.out.println("");
					WebElement Elm = driver.findElement(By);
					Elm.clear();
					Elm.sendKeys(text);
					throw e;

				}
			}
			if (i > 0) System.out.print(".");
		}

	}

	public static String getTextSupport(WebElement Elm, int timeOut) throws Exception {
		String result = null;
		for (int i = 0; i < timeOut; i++) {
			if(i == 1) logger.info("Try to Get Text (Max: " + timeOut + " seconds)");
			Thread.sleep(800);
			try {
				result = Elm.getText();
				if (i > 1) System.out.println("");
				break;	
			} catch (Exception e2) {
				if (!(i < timeOut - 1)) {
					System.out.println("");
					result = Elm.getText();
					throw e2;
				}
			}
			if (i > 0) System.out.print(".");
		}
		return result;

	}

	public static String getTextSupport(By By, WebDriver driver, int timeOut) throws Exception {
		String result = null;
		for (int i = 0; i < timeOut; i++) {
			if(i == 1) logger.info("Try to Get Text (Max: " + timeOut + " seconds)");
			Thread.sleep(800);
			try {
				result = driver.findElement(By).getText();
				if(result != null) {
					if (i > 1) System.out.println("");
					break;						
				}
			} catch (Exception e) {
				if (!(i < timeOut - 1)) {
					System.out.println("");
					result = driver.findElement(By).getText();
					throw e;
				}	
			}
			if (i > 0) System.out.print(".");
		}
		return result;

	}

	public static String getAttributeSupport(WebElement Elm, String att, int timeout) throws Exception {
		String result = "";
		for (int i = 0; i < timeout; i++) {
			if (i == 1) logger.info("Try to get Attribute '" + att + "' (Max: " + timeout +" seconds)");
			Thread.sleep(800);
			try {
				result = Elm.getAttribute(att);
				break;	
			} catch (Exception e2) {
				if (!(i < timeout*1000/interval_miliseconds - 1)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;
				}
			}
			if (i > 1) System.out.print(".");
		}
		return result;	

	}

	public static String getAttributeSupport(By by, WebDriver driver, String att, int timeout) throws Exception {
		String result = "";
		for (int i = 0; i < timeout; i++) {
			if (i == 1) logger.info("Try to get Attribute '" + att + "' (Max: " + timeout +" seconds)");
			try {
				WebElement Elm = driver.findElement(by);
				result = Elm.getAttribute(att);
				break; 
			} catch (Exception e2) {
				if (!(i < timeout)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					logger.info(e2.getMessage());
					throw e;
				}
			}
			if (i > 1) System.out.print(".");
		}
		return result;  
	}

	public static WebElement findElementSupport(By By, WebDriver driver, int timeOut) throws Exception {
		WebElement result = null;
		for (int i = 0; i < timeOut; i++) {
			if(i == 1) logger.info("Try to Get Element");
			Thread.sleep(800);
			try {
				result = driver.findElement(By);
				if(result != null) {
					if (i > 1) System.out.println("");
					break;						
				}

			} catch (Exception e) {
				if (!(i < timeOut-1)) {
					System.out.println("");
					result = driver.findElement(By);
					throw e;
				}
			}
			if (i > 0) System.out.print(".");
		}
		return result;
	}

	public static List<WebElement> findElementsSupport(By By, WebDriver driver, int timeOut) throws Exception {
		List<WebElement> result = null;
		for (int i = 0; i < timeOut; i++) {
			if(i == 1) logger.info("Try to Get Element");
			Thread.sleep(800);
			try {
				result = driver.findElements(By);
				if(result != null) {
					if (i > 1) System.out.println("");
					break;						
				}

			} catch (Exception e) {
				if (!(i < timeOut-1)) {
					System.out.println("");
					result = driver.findElements(By);
					throw e;
				}
			}
			if (i > 0) System.out.print(".");
		}
		return result;
	}

	public static void waitForElementSupport(By By, WebDriver driver, int timeOut) throws Exception {
		WebElement result = null;
		for (int i = 0; i < timeOut; i++) {
			if (i == 1) logger.info("Wait for Element (Max: " + timeOut +" seconds)");
			Thread.sleep(800);
			try {
				result = driver.findElement(By);
				if(result != null) {
					if (i > 1) System.out.println("");
					logger.info("Element Appears");
					break;						
				}
			} 
			catch (Exception e) {
				if (!(i < timeOut - 1)) {
					System.out.println("");
					result = driver.findElement(By);
					throw e;
				}
			}
			if (i > 1) System.out.print(".");
		}
	}	

	public static boolean checkElementExist(By By, WebDriver driver, int timeOut) throws Exception {
		Boolean result = false;
		for (int i = 0; i < timeOut; i++) {
			Thread.sleep(800);
			try { 	
				driver.findElement(By);
				result = true;
				break;
			} catch (Exception e) {
				result = false;
			}
		}
		if (result) {
			logger.info("Element Exist!");
		}
		else logger.info("Unable to see element after " + timeOut + " seconds");
		return result;
	}

	public static void waitForElementAppreared(By By, WebDriver driver, int timeOut) throws Exception {
		WebElement result = null;
		for (int i = 0; i < timeOut; i++) {
			if (i == 1) logger.info("Wait for Element Appeared (Max: " + timeOut +" seconds)");
			Thread.sleep(1000*latencyRate/100);
			try {
				result = driver.findElement(By);
				if(result != null && result.isDisplayed() && !result.getAttribute("class").contains("hidden")) {
					if (i > 1) System.out.println("");
					logger.info("Element Appears");
					return;						
				}
			} 
			catch (Exception e) {
				if (!(i < timeOut - 1)) {
					System.out.println("");
					result = driver.findElement(By);

					throw e;
				}
			}
			if (i > 1) System.out.print(".");
		}
		logger.info("Unable to see Element appear after " + timeOut +" seconds)");
	}

	public static void waitForElementDisAppreared(By By, WebDriver driver, int timeOut) throws Exception {
		WebElement result = null;
		for (int i = 0; i < timeOut; i++) {
			if (i == 1) logger.info("Wait for Element Appeared (Max: " + timeOut +" seconds)");
			Thread.sleep(1000*latencyRate/100);
			try {
				result = driver.findElement(By);				
				if(result != null && (!result.isDisplayed() || result.getAttribute("class").contains("hidden"))) {
					if (i > 1) System.out.println("");
					logger.info("Element DisAppears");
					return;						
				}
			} 
			catch (Exception e) {
				if (!(i < timeOut - 1)) {
					System.out.println("");
					result = driver.findElement(By);

					throw e;
				}
			}
			if (i > 1) System.out.print(".");
		}
		logger.info("Unable to see Element appear after " + timeOut +" seconds)");

	}

	public static void selectDropdown(WebElement Elm, String input, int timeout) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < timeout; i++) {
			if(i == 1) logger.info("Try to Select (Max: " + timeout + " seconds)");
			Thread.sleep(1000*latencyRate/100);			
			try {
				Select select = new Select(Elm);
				select.selectByVisibleText(input);
				break;	
			} catch (Exception e2) {
				if (!(i < timeout - 1)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;
				}
			}
			if (i > 0) System.out.print(".");
		}
	}

	public static void selectDropdown(By By, WebDriver driver, String input, int timeout) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < timeout; i++) {
			if(i == 1) logger.info("Try to Select (Max: " + timeout + " seconds)");
			Thread.sleep(1000*latencyRate/100);
			try {
				WebElement Elm = driver.findElement(By);
				Select select = new Select(Elm);
				select.selectByVisibleText(input);
				break;	
			} catch (Exception e2) {
				if (!(i < timeout - 1)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;					
				}
			}
			if (i > 0) System.out.print(".");
		}
	}

	public static String getSelectedItemFromDropdown(By By, WebDriver driver, int timeout) throws Exception {
		Thread.sleep(1000);
		String result = "";
		for (int i = 0; i < timeout; i++) {
			if(i == 1) logger.info("Try to Get Selected Item From Dropdown (Max: " + timeout + " seconds)");
			Thread.sleep(1000*latencyRate/100);
			try {

				WebElement Elm = driver.findElement(By);
				Select select = new Select(Elm);
				result = select.getFirstSelectedOption().getText();
				break;
			} catch (Exception e2) {
				if (!(i < timeout - 1)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;
				}	
			}
			if (i > 0) System.out.print(".");
		}
		return result;
	}

	public static String getSelectedItemFromDropdown(WebElement Elm, int timeout) throws Exception {
		Thread.sleep(1000);
		String result = "";
		for (int i = 0; i < timeout; i++) {
			if(i == 1) logger.info("Try to Get Selected Item From Dropdown (Max: " + timeout + " seconds)");
			Thread.sleep(1000*latencyRate/100);
			try {
				Select select = new Select(Elm);
				result = select.getFirstSelectedOption().getText();
				break;
			} catch (Exception e2) {
				if (!(i < timeout - 1)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;
				}	
			}
			if (i > 0) System.out.print(".");
		}
		return result;
	}

	public static List<String> getAllOptionsFromDropdown(WebElement Elm, int timeout) throws Exception {
		Thread.sleep(1000);
		List<String> result = new ArrayList<>();
		for (int i = 0; i < timeout; i++) {
			if(i == 1) logger.info("Try to Get Selected Item From Dropdown (Max: " + timeout + " seconds)");
			Thread.sleep(1000*latencyRate/100);
			try {
				Select select = new Select(Elm);
				List<WebElement> allOptions = select.getOptions();
				for (WebElement option : allOptions)
				{
					String opt = option.getText();
					result.add(opt);
				}			
				break;
			} catch (Exception e2) {
				if (!(i < timeout - 1)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;
				}
			}
			//if (i > 0) System.out.print(".");
		}
		return result;
	}
	
	public static String selectDropdownSelectedItem(WebElement Elm, int timeout) throws Exception {
		Thread.sleep(1000);
		String result = "";
		for (int i = 0; i < timeout; i++) {
			Thread.sleep(1000*latencyRate/100);			
			try {
				Select select = new Select(Elm);
				result = select.getFirstSelectedOption().getText();
				break;	
			} catch (Exception e2) {
				if (!(i < timeout -1)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;
				}
			}
			logger.info("Try to Select");
		}
		return result;
	}

	public static void selectDropdownByIndex(WebElement Elm, int idx, int timeout) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < timeout; i++) {
			Thread.sleep(1000*latencyRate/100);			
			try {
				Select select = new Select(Elm);
				select.selectByIndex(idx);
				break;	
			} catch (Exception e2) {
				if (!(i < timeout -1)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;
				}
			}
			logger.info("Try to Select");
		}
	}

	public static void selectDropdownByIndex(By by, WebDriver driver, int idx, int timeout) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < timeout; i++) {
			Thread.sleep(1000*latencyRate/100);
			try {

				WebElement Elm = driver.findElement(by);
				Select select = new Select(Elm);
				select.selectByIndex(idx);
				break;
			} catch (Exception e2) {
				if (!(i < timeout -1)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;
				}	
			}
			logger.info("Try to Select");
		}
	}

	public static void selectDropdown_LatestItem(WebElement Elm, int timeout) throws Exception {
		Thread.sleep(1000);
		for (int i = 0; i < timeout; i++) {
			if(i == 1) logger.info("Try to Select (Max: " + timeout + " seconds)");
			Thread.sleep(1000*latencyRate/100);   
			try {
				Select select = new Select(Elm);
				int idx = select.getOptions().size();
				select.selectByIndex(idx-1);
				break; 
			} catch (Exception e2) {
				if (!(i < timeout)) {
					InterruptedException e = new InterruptedException(e2.getMessage());
					throw e;
				}
			}
			if (i > 0) System.out.print(".");
		}
	}


	public static void checkOffCheckboxInTableByTableID(WebDriver driver, String tableIDString, String str_searchText, String searchText_xpath_extension, int search_column, int column_checkbox) throws Exception {
		boolean debug = false;
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";

		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+ Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+ Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";
		
		//Used for loop for number of rows.
		for (int i = 1; i <= Row_count; i++){

			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try{
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");
				
				if (str_searchText.equals(Table_data)){
					String checkBoxXpath = first_part +i + second_part + column_checkbox + third_part + "/input";
					if (debug) System.out.println("Xpath of checkbox: " + checkBoxXpath);
					
					WebElement checkBox = driver.findElement(By.xpath(checkBoxXpath));
					Boolean isChecked = checkBox.isSelected();
					if (debug) System.out.println("Checkbox Xpath" + isChecked);
					if (!isChecked)	checkBox.click();
					return;
				}
			} catch (Exception e) {}
		} 
		
		logger.info("Not found '" + str_searchText + "' in the table");
	}

	public static void uncheckCheckboxInTableByTableID(WebDriver driver, String tableIDString, String str_searchText, String searchText_xpath_extension, int search_column, int column_checkbox) throws Exception {
		boolean debug = false;
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";

		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+ Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+ Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";
		
		//Used for loop for number of rows.
		for (int i = 1; i <= Row_count; i++){

			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try{
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");
				
				if (str_searchText.equals(Table_data)){
					String checkBoxXpath = first_part +i + second_part + column_checkbox + third_part + "/input";
					if (debug) System.out.println("Xpath of checkbox: " + checkBoxXpath);
					
					WebElement checkBox = driver.findElement(By.xpath(checkBoxXpath));
					Boolean isChecked = checkBox.isSelected();
					if (debug) System.out.println("Checkbox Xpath" + isChecked);
					if (isChecked)	checkBox.click();
					return;
				}
			} catch (Exception e) {}
		} 
		
		logger.info("Not found '" + str_searchText + "' in the table");
	}
	

	public static void checkOffCheckboxInTableByTableXpath(WebDriver driver, String tableXpathString, String str_searchText, String searchText_xpath_extension, int search_column, int column_checkbox) throws Exception {
		boolean debug = false;
		String tableXpath = tableXpathString + "/tbody";

		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+ Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+ Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";
		
		//Used for loop for number of rows.
		for (int i = 1; i <= Row_count; i++){

			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try{
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");
				
				if (str_searchText.equals(Table_data)){
					String checkBoxXpath = first_part +i + second_part + column_checkbox + third_part + "/input";
					if (debug) System.out.println("Xpath of checkbox: " + checkBoxXpath);
					
					WebElement checkBox = driver.findElement(By.xpath(checkBoxXpath));
					Boolean isChecked = checkBox.isSelected();
					if (debug) System.out.println("Checkbox Xpath" + isChecked);
					if (!isChecked)	checkBox.click();
					return;
				}
			} catch (Exception e) {}
		} 
		
		logger.info("Not found '" + str_searchText + "' in the table");
	}
	
	public static void uncheckCheckboxInTableByTableXpath(WebDriver driver, String tableXpathString, String str_searchText, String searchText_xpath_extension, int search_column, int column_checkbox) throws Exception {
		boolean debug = false;
		String tableXpath = tableXpathString + "/tbody";

		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+ Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+ Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";
		
		//Used for loop for number of rows.
		for (int i = 1; i <= Row_count; i++){

			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try{
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");
				
				if (str_searchText.equals(Table_data)){
					String checkBoxXpath = first_part +i + second_part + column_checkbox + third_part + "/input";
					if (debug) System.out.println("Xpath of checkbox: " + checkBoxXpath);
					
					WebElement checkBox = driver.findElement(By.xpath(checkBoxXpath));
					Boolean isChecked = checkBox.isSelected();
					if (debug) System.out.println("Checkbox Xpath" + isChecked);
					if (!isChecked)	checkBox.click();
					return;
				}
			} catch (Exception e) {}
		} 
		
		logger.info("Not found '" + str_searchText + "' in the table");
	}
	
	public static void uncheckAllCheckboxesInTableByTableID(WebDriver driver, String tableIDString, String checkbox_extension) throws Exception {
		boolean debug = false;
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";
		
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+ Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+ Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i = 1; i <= Row_count; i++){
			//Used for loop for number of columns.
			for(int j = 1; j <= Col_count; j++){
				String data_xpath = first_part + i + second_part + j + third_part;
				try {
					String final_xpath = data_xpath + checkbox_extension;
					WebElement checkbox = driver.findElement(By.xpath(final_xpath));
					Boolean isChecked = checkbox.isSelected();
					if (isChecked) {
						logger.info("Found a checkbox that is being checked in Row " + i + " Column " + j +". Uncheck it!!!" );
						if (debug) logger.info("xpath:" + final_xpath);
						clickSupport(checkbox, 3);						
					}
					break;
				} catch (Exception e) {

				}
			}
		} 
	}

	public static void uncheckAllCheckboxesInTableByTableXpath(WebDriver driver, String tableXpathString, String checkbox_extension) throws Exception {
		boolean debug = false;
		String tableXpath = tableXpathString + "/tbody";
		
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+ Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+ Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i = 1; i <= Row_count; i++){
			//Used for loop for number of columns.
			for(int j = 1; j <= Col_count; j++){
				String data_xpath = first_part + i + second_part + j + third_part;
				try {
					String final_xpath = data_xpath + checkbox_extension;
					WebElement checkbox = driver.findElement(By.xpath(final_xpath));
					Boolean isChecked = checkbox.isSelected();
					if (isChecked) {
						logger.info("Found a checkbox that is being checked in Row " + i + " Column " + j +". Uncheck it!!!" );
						checkbox.click();
					}
					break;
				} catch (Exception e) {

				}
			}
		} 
	}

	/**
	 * Search a text in a table (find table by ID) then click on an element that is <offset_column> blocks away from the search text
	 * 
	 * @param tableIDString ID of a table
	 * @param str_searchText text using for locating a check box in a table
	 * @param xpath_extension xpath of element starting from td
	 * @param offset_column use for locating a check box.
	 *    For example:
	 *      -2 means that the check box is on the left side, 2 blocks away from the search text.
	 *       3 means that the check box is on the right side, 3 blocks away from the search text.
	 * 
	 */
	public static void clickOnElementInTableByTableID(WebDriver driver, String tableIDString, String str_searchText, String searchText_xpath_extension, int search_column, String result_xpath_extension, int comlumn_result) throws Exception {
		boolean debug = false;
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";

		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for ( int i = 1; i <= Row_count; i++ ){
			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try {
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");
				
				if (str_searchText.equals(Table_data)){
					String elementToClickXpath = first_part + i + second_part + comlumn_result + third_part + result_xpath_extension;
					if (debug) System.out.println("Element to Click Xpath: " + elementToClickXpath);
					
					WebElement elementToClick = driver.findElement(By.xpath(elementToClickXpath));
					elementToClick.click();
					
					return;
				}
			} catch (Exception e) {}
		} 	
	}

	/**
	 * Search a text in a table (find table by ID) then click on an element that is <offset_column> blocks away from the search text
	 * 
	 * @param tableXpathString Xpath of a table
	 * @param str_searchText text using for locating a check box in a table
	 * @param xpath_extension xpath of element starting from td
	 * @param offset_column use for locating a check box.
	 *    For example:
	 *      -2 means that the check box is on the left side, 2 blocks away from the search text.
	 *       3 means that the check box is on the right side, 3 blocks away from the search text.
	 * 
	 */
	public static void clickOnElementInTableByTableXpath(WebDriver driver, String tableXpathString, String str_searchText, String searchText_xpath_extension, int search_column, String result_xpath_extension,  int comlumn_result) throws Exception {
		boolean debug = false;
		String tableXpath = tableXpathString + "/tbody";

		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for ( int i = 1; i <= Row_count; i++ ){
			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try {
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");

				if (str_searchText.equals(Table_data)){
					String elementToClickXpath = first_part + i + second_part + comlumn_result + third_part + result_xpath_extension;
					if (debug) System.out.println("Element to Click Xpath: " + elementToClickXpath);

					WebElement elementToClick = driver.findElement(By.xpath(elementToClickXpath));
					elementToClick.click();

					return;
				}
			} catch (Exception e) {}
		} 	
	}

	public static boolean isCheckboxSelectedInTableByTableID(WebDriver driver, String tableIDString, String str_searchText, String searchText_xpath_extension, int search_column, String checkbox_xpath_extension,  int comlumn_result) throws Exception {
		boolean debug = false;
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";

		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = " + Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = " + Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for ( int i = 1; i <= Row_count; i++){
			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try {
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");

				if (str_searchText.equals(Table_data)){
					String checkboxXpath = first_part + i + second_part + comlumn_result + third_part + checkbox_xpath_extension;
					if (debug) System.out.println("Element of Checkbox: " + checkboxXpath);
					
					WebElement elementOfCheckbox = driver.findElement(By.xpath(checkboxXpath));
					boolean isChecked = elementOfCheckbox.isSelected();
					
					return isChecked;
				}

			}catch (Exception e) {}
		} 	
		return false;
	}

	public static boolean isCheckboxSelectedInTableByTableXpath(WebDriver driver, String tableXpathString, String str_searchText, String searchText_xpath_extension, int search_column, String checkbox_xpath_extension,  int comlumn_result) throws Exception {
		boolean debug = false;
		String tableXpath = tableXpathString + "/tbody";
		
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = " + Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = " + Col_count);

		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for ( int i = 1; i <= Row_count; i++){
			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try {
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");

				if (str_searchText.equals(Table_data)){
					String checkboxXpath = first_part + i + second_part + comlumn_result + third_part + checkbox_xpath_extension;
					if (debug) System.out.println("Element of Checkbox: " + checkboxXpath);
					
					WebElement elementOfCheckbox = driver.findElement(By.xpath(checkboxXpath));
					boolean isChecked = elementOfCheckbox.isSelected();
					
					return isChecked;
				}

			}catch (Exception e) {}
		} 	
		return false;
	}

	private static String getAnAttributeFromAnElementInTableByTableID(WebDriver driver, String tableIDString, String str_searchText, String xpath_extension, String attribute,  int offset_column) throws Exception {
		boolean debug = false;
		String value = "";
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";
		//Get number of rows In table.
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+Row_count);

		//Get number of columns In table.
		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+Col_count);

		//divided xpath In three parts to pass Row_count and Col_count values.
		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i=1; i<=Row_count; i++){
			//Used for loop for number of columns.
			for(int j=1; j<=Col_count; j++){
				//Prepared final xpath of specific cell as per values of i and j.
				String final_xpath = first_part+i+second_part+j+third_part;
				//Will retrieve value from located cell and print It.
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + j + " Table data '" + Table_data + "'");
				if (str_searchText.equals(Table_data)){
					j = j + offset_column;
					String elementXpath = first_part+i+second_part+j+third_part+ xpath_extension;
					if (debug) System.out.println("Element to get Attribute Xpath: " + elementXpath);
					value = driver.findElement(By.xpath(elementXpath)).getAttribute(attribute);
					return value;
				}
			}
		} 
		return value;
	}
	
	private static String getAnAttributeFromAnElementInTableByTableXpath(WebDriver driver, String tableXpathString, String str_searchText, String xpath_extension, String attribute,  int offset_column) throws Exception {
		boolean debug = false;
		String value = "";
		String tableXpath = tableXpathString + "/tbody";
		//Get number of rows In table.
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+Row_count);

		//Get number of columns In table.
		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+Col_count);

		//divided xpath In three parts to pass Row_count and Col_count values.
		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i=1; i<=Row_count; i++){
			//Used for loop for number of columns.
			for(int j=1; j<=Col_count; j++){
				//Prepared final xpath of specific cell as per values of i and j.
				String final_xpath = first_part+i+second_part+j+third_part;
				//Will retrieve value from located cell and print It.
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + j + " Table data '" + Table_data + "'");
				if (str_searchText.equals(Table_data)){
					j = j + offset_column;
					String elementXpath = first_part+i+second_part+j+third_part+ xpath_extension;
					if (debug) System.out.println("Element to get Attribute Xpath: " + elementXpath);
					value = driver.findElement(By.xpath(elementXpath)).getAttribute(attribute);
					return value;
				}
			}
		} 
		return value;
	}


	/**
	 * Search a text in a table (find table by ID) then send text to an element that is <offset_column> blocks away from the search text
	 * 
	 * @param tableIDString ID of a table
	 * @param str_searchText text using for locating a check box in a table
	 * @param offset_column use for locating a check box.
	 *    For example:
	 *      -2 means that the check box is on the left side, 2 blocks away from the search text.
	 *       3 means that the check box is on the right side, 3 blocks away from the search text.
	 * 
	 */
	private static void sendKeyToElementInTableByTableID(WebDriver driver, String tableIDString, String str_searchText, String xpath_extension, String str_sendText, int offset_column) throws Exception {
		boolean debug = false;
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";
		//Get number of rows In table.
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+Row_count);

		//Get number of columns In table.
		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+Col_count);

		//divided xpath In three parts to pass Row_count and Col_count values.
		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i=1; i<=Row_count; i++){
			//Used for loop for number of columns.
			for(int j=1; j<=Col_count; j++){
				//Prepared final xpath of specific cell as per values of i and j.
				String final_xpath = first_part+i+second_part+j+third_part;
				//Will retrieve value from located cell and print It.
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + j + " Table data '" + Table_data + "'");
				if (str_searchText.equals(Table_data)){
					j = j + offset_column;
					String elementToClickXpath = first_part+i+second_part+j+third_part+ xpath_extension;
					if (debug) System.out.println("Xpath of Element needs to be send text" + elementToClickXpath);
					WebElement elementToClick = driver.findElement(By.xpath(elementToClickXpath));
					elementToClick.clear();
					elementToClick.sendKeys(str_sendText);
					return;
				}
			}
		} 


	}


	/**
	 * Search a text in a table (find table by xpath) then send text to an element that is <offset_column> blocks away from the search text
	 * 
	 * @param tableXpathString xpath of a table
	 * @param str_searchText text using for locating a check box in a table
	 * @xpath_extension xpath of element to send text, start from /td ((do not include /td)
	 * @param offset_column use for locating a check box.
	 *    For example:
	 *      -2 means that the check box is on the left side, 2 blocks away from the search text.
	 *       3 means that the check box is on the right side, 3 blocks away from the search text.
	 * 
	 */
	private static void sendKeyToElementInTableByTableXpath(WebDriver driver, String tableXpathString, String str_searchText, String xpath_extension, String str_sendText, int offset_column) throws Exception {
		boolean debug = false;
		String tableXpath = tableXpathString + "/tbody";
		//Get number of rows In table.
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+Row_count);

		//Get number of columns In table.
		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+Col_count);

		//divided xpath In three parts to pass Row_count and Col_count values.
		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i=1; i<=Row_count; i++){
			//Used for loop for number of columns.
			for(int j=1; j<=Col_count; j++){
				//Prepared final xpath of specific cell as per values of i and j.
				String final_xpath = first_part+i+second_part+j+third_part;
				//Will retrieve value from located cell and print It.
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + j + " Table data '" + Table_data + "'");
				if (str_searchText.equals(Table_data)){
					j = j + offset_column;
					String elementToClickXpath = first_part+i+second_part+j+third_part+xpath_extension;
					if (debug) System.out.println("Xpath of Element needs to be send text" + elementToClickXpath);
					WebElement elementToClick = driver.findElement(By.xpath(elementToClickXpath));
					elementToClick.clear();
					elementToClick.sendKeys(str_sendText);
					return;
				}
			}
		} 
	}


	/**
	 * Search a text in a table (find table by ID) then get text from an element that is <offset_column> blocks away from the search text
	 * 
	 * @param tableIDString ID of a table
	 * @param str_searchText text using for locating a check box in a table
	 * @xpath_extension xpath of element to send text, start from /td (do not include /td)
	 * @param offset_column use for locating a check box.
	 *    For example:
	 *      -2 means that the check box is on the left side, 2 blocks away from the search text.
	 *       3 means that the check box is on the right side, 3 blocks away from the search text.
	 * 
	 */
	public static String getTextFromElementInTableByTableID(WebDriver driver, String tableIDString, String str_searchText, String searchText_xpath_extension, int search_column, String result_xpath_extension, int comlumn_result) throws Exception {
		boolean debug = false;
		String result = "";
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";

		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+Col_count);
		
		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i = 1; i <= Row_count; i++){
			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try{
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");

				if (str_searchText.equals(Table_data)){
					String elementToClickXpath = first_part + i + second_part + comlumn_result + third_part + result_xpath_extension;
					if (debug) System.out.println("Element to Get Text: " + elementToClickXpath);

					WebElement elementToClick = driver.findElement(By.xpath(elementToClickXpath));
					result = elementToClick.getText();

					if (debug) System.out.println("result: " + result);

					return result;
				}
			} catch (Exception e) {}
		}

		if (debug) System.out.println("result: " + result);
		
		return result;
	}


	/**
	 * Search a text in a table (find table by Xpath) then get text from an element that is <offset_column> blocks away from the search text
	 * 
	 * @param tableXPathString Xpath of a table
	 * @param str_searchText text using for locating a check box in a table
	 * @param offset_column use for locating a check box.
	 *    For example:
	 *      -2 means that the check box is on the left side, 2 blocks away from the search text.
	 *       3 means that the check box is on the right side, 3 blocks away from the search text.
	 * 
	 */
	public static String getTextFromElementInTableByTableXpath(WebDriver driver, String tableXPathString, String str_searchText, String searchText_xpath_extension, int search_column, String result_xpath_extension, int comlumn_result) throws Exception {
		boolean debug = false;
		String result = "";
		String tableXpath = tableXPathString + "/tbody";
		
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = "+Row_count);

		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = "+Col_count);
		
		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i = 1; i <= Row_count; i++){
			String final_xpath = first_part + i + second_part + search_column + third_part + searchText_xpath_extension;
			try {
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + search_column + " Table data '" + Table_data + "'");

				if (str_searchText.equals(Table_data)){
					String elementToClickXpath = first_part + i + second_part + comlumn_result + third_part + result_xpath_extension;
					if (debug) System.out.println("Element to Get Text: " + elementToClickXpath);

					WebElement elementToClick = driver.findElement(By.xpath(elementToClickXpath));
					result = elementToClick.getText();

					if (debug) System.out.println("result: " + result);

					return result;
				}
			} catch (Exception e) {}
		}
				
		if (debug) System.out.println("result: " + result);
		
		return result;
	}

	public static List<String> getTextFromAColumnInTableByTableXpath(WebDriver driver, String tableXpathString, String xpath_extension, int column, int row_start) throws Exception {
		boolean debug = false;
		List<String> result = new ArrayList<>();
		String tableXpath = tableXpathString + "/tbody";
		//Get number of rows In table.
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = " + Row_count);

		//Get number of columns In table.
		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = " + Col_count);

		//divided xpath In three parts to pass Row_count and Col_count values.
		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i=row_start; i<=Row_count; i++){
				String final_xpath = first_part + i + second_part + column + third_part + xpath_extension;	
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + column + " Table data '" + Table_data + "'");
				result.add(Table_data);
		} 
		
		if (debug) {
			logger.info("There is(are) '" + result.size() + "' value(s) listed in the table" );
			for (String value : result) {
				logger.info("----- : '" + value + "'");
			}
		}
		
		return result;
	}
	
	public static List<String> getTextFromAColumnInTableByTableID(WebDriver driver, String tableIDString, String xpath_extension, int column, int row_start) throws Exception {
		boolean debug = false;
		List<String> result = new ArrayList<>();
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";
		//Get number of rows In table.
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		if (debug) System.out.println("Number Of Rows = " + Row_count);

		//Get number of columns In table.
		int Col_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		if (debug) System.out.println("Number Of Columns = " + Col_count);

		//divided xpath In three parts to pass Row_count and Col_count values.
		String first_part = tableXpath + "/tr[";
		String second_part = "]/td[";
		String third_part = "]";

		//Used for loop for number of rows.
		for (int i=row_start; i<=Row_count; i++){
				String final_xpath = first_part + i + second_part + column + third_part + xpath_extension;	
				String Table_data = driver.findElement(By.xpath(final_xpath)).getText();
				if (debug) System.out.println("Row " + i + " Column " + column + " Table data '" + Table_data + "'");
				result.add(Table_data);
		} 
		
		if (debug) {
			logger.info("There is(are) '" + result.size() + "' value(s) listed in the table" );
			for (String value : result) {
				logger.info("----- : '" + value + "'");
			}
		}
		
		return result;
	}
	
	public static int getNumberOfRowsTableByTableXpath(WebDriver driver, String tableXpath) throws Exception {
		String Xpath = "//table[@class='" + tableXpath + "']/tbody";
		int Row_count = driver.findElements(By.xpath(Xpath + "/tr")).size();
		return Row_count;
	}

	public static int getNumberOfRowsTableByTableID(WebDriver driver, String tableIDString) throws Exception {
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr")).size();
		return Row_count;
	}
	
	public static int getNumberOfColumnsTableByTableXpath(WebDriver driver, String tableXpath) throws Exception {
		String Xpath = tableXpath + "/tbody";
		int Row_count = driver.findElements(By.xpath(Xpath + "/tr[1]/td")).size();
		return Row_count;
	}

	public static int getNumberOfColumnsTableByTableID(WebDriver driver, String tableIDString) throws Exception {
		String tableXpath = "//table[@id='" + tableIDString + "']/tbody";
		int Row_count = driver.findElements(By.xpath(tableXpath + "/tr[1]/td")).size();
		return Row_count;
	}

	public static void uploadFileByID(WebDriver driver, String idOfElementInputTypeFile, String file) throws Exception {		

		String strJS = "document.getElementById('" + idOfElementInputTypeFile + "').style.visibility = 'visible'";
		((JavascriptExecutor) driver).executeScript(strJS);
		strJS = "document.getElementById('" + idOfElementInputTypeFile + "').style.display='block'";		
		((JavascriptExecutor) driver).executeScript(strJS);
		logger.info("xpath: //input[@type='file' and @id='" + idOfElementInputTypeFile + "']");
		logger.info("Select file: '"+ file + "' to upload");
		WebElement element = driver.findElement(By.xpath("//input[@type='file' and @id='" + idOfElementInputTypeFile + "']"));
		element.sendKeys(file);
	}



	public static void waitForNewPopupWindowsSupport(WebDriver driver, int originalWindows, int timeOut) throws Exception {
		logger.info("Wait for Element (Max: " + timeOut +" seconds)");
		for (int i = 0; i < timeOut; i++) {
			Thread.sleep(1000*latencyRate/100);
			try {
				Set<String> handles = driver.getWindowHandles(); // get all window handles
				if(handles.size() > originalWindows) {
					if (i > 0) System.out.println("");
					logger.info("New window Appears");
					return;						
				}
			} 
			catch (Exception e) {
				if (!(i < timeOut - 1)) {
					System.out.println("");
					throw e;
				}
			}
			if (i > 0) System.out.print(".");
		}		
	}

	// set input according to exact place holder
	public static void setInputAccordingExactPlaceHolder(WebDriver driver, String label, String input, int timeout) throws Exception {
		String st = "//input[@placeholder='" + label + "']";
		sendKeysSupport(By.xpath(st), driver, input, timeout);
	}

	public static void checkOffCheckbox(WebElement checkboxElm, boolean true_false, int timeout) throws Exception {
		if (checkboxElm.isSelected()) {
			if (!true_false){
				AutomationSupport.clickSupport(checkboxElm, timeout);
			}
		} else {
			if (true_false){
				AutomationSupport.clickSupport(checkboxElm, timeout);
			}
		}
	}

	public static void checkOffCheckbox(By By, WebDriver driver, boolean true_false, int timeout) throws Exception {
		WebElement checkboxElm = AutomationSupport.findElementSupport(By, driver, timeout);
		if (checkboxElm.isSelected()) {
			if (!true_false){
				AutomationSupport.clickSupport(checkboxElm, timeout);
			}
		} else {
			if (true_false){
				AutomationSupport.clickSupport(checkboxElm, timeout);
			}
		}
	}



}