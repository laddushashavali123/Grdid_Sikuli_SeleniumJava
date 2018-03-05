package tests.seleniumeasy.pages.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class TableDataSearchPage {
    private WebDriver driver;
    final Logger logger = LoggerFactory.getLogger(TableDataSearchPage.class);

    public  TableDataSearchPage(WebDriver driver){
        this.driver = driver;
    }

    // Locator
    By taskInput              = By.xpath("//*[@id= 'task-table-filter']");
    By FilterButtonListedUser = By.xpath("//button[contains(text(), 'Filter')]");


    // Method
    public TableDataSearchPage goTableDataSearchPage(){
        driver.get("http://www.seleniumeasy.com/test/table-search-filter-demo.html");
        driver.manage().window().maximize();
        return this;
    }

    public TableDataSearchPage entertaskInput(String text){
        driver.findElement(taskInput).clear();
        driver.findElement(taskInput).sendKeys(text);
        return this;
    }



    // Assertion
    public TableDataSearchPage verifyTotalItemDisplayIs(int x){
        try {
            String errorMessage = driver.findElement(By.xpath("//*[@id= 'task-table']/tbody/tr[@class='filterTable_no_results']")).getText();
            logger.info("Error message is " + errorMessage);
            Assert.assertEquals(errorMessage, "No results found");
            logger.info("No results found");
            Reporter.log("No results found");
            return this;
        }
        catch (Exception e) {
            List<WebElement> totalElements = driver.findElements(By.xpath("//*[@id= 'task-table']/tbody/tr"));
            List<WebElement> hiddenElements = driver.findElements(By.xpath("//*[@id= 'task-table']/tbody/tr[@style='display: none;']"));
            logger.info("Total Row is " + totalElements.size());
            logger.info("Total Hidden Row is " + hiddenElements.size());
            Assert.assertEquals(x, totalElements.size() - hiddenElements.size());
            logger.info("Result table displays correctly with " + x + " item");
            Reporter.log("Result table displays correctly with " + x + " item");
            return this;
        }
    }
}
