package example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.DriverBase;

public class Test1 extends DriverBase{

	private void googleExampleThatSearchesFor(final String searchString) throws Exception {
        WebDriver driver = DriverBase.getDriver();
        driver.get("http://www.google.com");
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.clear();        
        searchField.sendKeys(searchString);
        
        searchField.submit();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
        	public Boolean apply(WebDriver driverObject) {
        		return driverObject.getTitle().toLowerCase().startsWith(searchString.toLowerCase());
        	}        
        });
        System.out.println("Page title is: " + driver.getTitle());
         Assert.assertEquals(searchString, driver.getTitle());
    }
	
	
    @Test
    public void google1() throws Exception { 
    	googleExampleThatSearchesFor("1!");    
    }
    /**
     * 
     * @throws Exception
     
    @Test
    public void google2() throws Exception {        
    	googleExampleThatSearchesFor("2!");    
    } 
    
    @Test
    public void google3() throws Exception {        
    	googleExampleThatSearchesFor("3!");    
    } */
    @Test
    public void google4() throws Exception {        
    	googleExampleThatSearchesFor("4!");    
    } 
    
}