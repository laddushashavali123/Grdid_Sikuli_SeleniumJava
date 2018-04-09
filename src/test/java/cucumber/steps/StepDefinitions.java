package cucumber.steps;

import base.DriverBase;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class StepDefinitions extends DriverBase {
    final Logger logger = LoggerFactory.getLogger(StepDefinitions.class);
    WebDriver driver;

    @Given("^I am on the Login page on URL \"([^\"]*)\"$")
    public void iAmOnTheLoginPageOnURL(String arg0){
        this.driver = getDriver();
        driver.get(arg0);
    }

    @When("^I fill in \"([^\"]*)\" with \"([^\"]*)\"$")
    public void i_fill_in_with(String arg1, String arg2){
        driver.findElement(By.xpath("//*[contains(text(),'" + arg1 + "')]/input")).sendKeys(arg2);

    }

    @When("^I click on the \"([^\"]*)\" button$")
    public void i_click_on_the_button(String arg1){
        driver.findElement(By.xpath("//input[@name='" + arg1 + "']")).click();

    }

    @Then("^I should see \"([^\"]*)\" message$")
    public void i_should_see_message(String arg1) throws Throwable {
        String actualText = driver.findElement(By.xpath("//*[text()='" + arg1 + "']")).getText();
        Assert.assertEquals(actualText, arg1);
        logger.info("I saw " + arg1);

    }
}
