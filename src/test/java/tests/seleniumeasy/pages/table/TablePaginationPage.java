package tests.seleniumeasy.pages.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.Reporter;

public class TablePaginationPage {
    private WebDriver driver;
    final Logger logger = LoggerFactory.getLogger(TablePaginationPage.class);

    public TablePaginationPage(WebDriver driver){
        this.driver = driver;
    }

    // Method
    public TablePaginationPage goToPaginationPage(){
        driver.get("http://www.seleniumeasy.com/test/table-pagination-demo.html");
        driver.manage().window().maximize();
        return this;
    }

    public TablePaginationPage clickPaginationButton(String pageName) throws InterruptedException {
        By xpath = By.xpath("//*[@id= 'myPager']/li/a[contains(text(), '" + pageName + "')]");
        driver.findElement(xpath).click();
        Thread.sleep(200);
        return this;
    }

    // Assertion
    public TablePaginationPage verifyPreviousButtonIs(String status){
        String style = driver.findElement(By.xpath("//*[@id= 'myPager']/li/a[contains(text(), '«')]")).getAttribute("style");
        if (status.equalsIgnoreCase("hide")){
            Assert.assertEquals(style, "display: none;");
            logger.info("Previous button is hidden");
            Reporter.log("Previous button is hidden");
        }
        else if (status.equalsIgnoreCase("hide")){
            Assert.assertEquals(style, "display: block;");
            logger.info("Previous button is hidden");
            Reporter.log("Previous button is hidden");
        }

        return this;
    }

    public TablePaginationPage verifyContentNumberDisplayIs(int x){

        return this;
    }

    public TablePaginationPage verifyNextButtonHidden(){
        String style = driver.findElement(By.xpath("//*[@id= 'myPager']/li/a[contains(text(), '»)]")).getAttribute("style");
        Assert.assertEquals(style, "display: none;");
        logger.info("Previous button is hidden");
        Reporter.log("Previous button is hidden");
        return this;
    }
}
