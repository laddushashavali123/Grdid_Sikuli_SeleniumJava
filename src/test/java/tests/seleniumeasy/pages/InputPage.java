package tests.seleniumeasy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WebUtils;

import java.util.List;

public class InputPage {
    private WebDriver driver;
    final Logger logger = LoggerFactory.getLogger(InputPage.class);

    // Constructor
    public InputPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator
    By checkBoxMessage = By.id("txtAge");
    By checkAllbutton  = By.id("check1");


    // Page access
    public void goSimpleForm() {
        driver.get("http://www.seleniumeasy.com/test/basic-first-form-demo.html");
        driver.manage().window().maximize();
    }

    public void goCheckBox() {
        driver.get("http://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        driver.manage().window().maximize();
    }

    public void goRadioButton() {
        driver.get("http://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
        driver.manage().window().maximize();
    }

    public void goSelectDropDown() {
        driver.get("http://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        driver.manage().window().maximize();
    }

    public void goJqueryDropDown() {
        driver.get("http://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
        driver.manage().window().maximize();
    }


    // Method
    public InputPage enterText(String textboxName, String contents){
        driver.findElement(By.xpath("//*[text()='" + textboxName + "']/following-sibling::input")).sendKeys(contents);
        logger.info("Enter " + contents + " to textbox " + textboxName);
        return this;
    }

    public InputPage clickButton(String buttonName){
        driver.findElement(By.xpath("//button[text()='" + buttonName + "']")).click();
        logger.info("Click button " + buttonName);
        return this;
    }

    public InputPage clickCheckBoxName(String checkBoxName){
        driver.findElement(By.xpath("//*[text()='" + checkBoxName + "']/input")).click();
        return this;
    }

    public InputPage clickInputButton(String buttonName){
        driver.findElement(By.xpath("//input[@value='" + buttonName + "']")).click();
        logger.info("Click button " + buttonName);
        return this;
    }

    public String getInputButtonText(String buttonName){
        String buttonText = driver.findElement(By.xpath("//input[@value='" + buttonName + "']")).getText();
        logger.info(buttonName + " has text: " + "\"" + buttonText + "\"");
        return buttonText;
    }


    // Verify
    public boolean verifyText(String textboxName, String expectedText){
        String actualText = driver.findElement(By.xpath("//*[text()='" + textboxName + "']/following-sibling::span")).getText();
        logger.info("Actual your message is: " + actualText);
        if (expectedText.equals(actualText)){
            return true;
        }
        return false;
    }

    public boolean verifyCheckBoxMessage(String expectedMessage){
        String actualText = driver.findElement(checkBoxMessage).getText();
        logger.info("Actual message from checkbox is: \"" + actualText + "\"");
        if (expectedMessage.equals(actualText)){
            return true;
        }
        return false;
    }

    public boolean verifyCheckBoxIsChecked(String checkBoxName){
        return driver.findElement(By.xpath("//*[text()='" + checkBoxName + "']/input")).isSelected();
    }

    public boolean verifyAllCheckBoxIsChecked(){
        List<WebElement> elements = driver.findElements(By.xpath("//input[@class='cb1-element']"));
        for (WebElement e : elements){
            if(!e.isSelected()){
                return false;
            }
        }
        return true;
    }

    public boolean verifyInputButtonText(String expectedText){
        String buttonText = driver.findElement(checkAllbutton).getAttribute("value");
        logger.info("Check All button text is: " + "\"" + buttonText + "\"");
        if (buttonText.equals(expectedText)){
            return true;
        }
        return false;
    }
}
