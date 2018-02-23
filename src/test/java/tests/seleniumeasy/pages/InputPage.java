package tests.seleniumeasy.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WebUtils;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class InputPage {
    private WebDriver driver;
    private final Logger logger = LoggerFactory.getLogger(InputPage.class);

    // Constructor
    public InputPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator
    private By checkBoxMessage = By.id("txtAge");
    private By checkAllbutton  = By.id("check1");


    // -----------------------------------------------------------------------------
    // Simple input form
    public void goSimpleForm() {
        driver.get("http://www.seleniumeasy.com/test/basic-first-form-demo.html");
        driver.manage().window().maximize();
    }

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

    public boolean verifyText(String textboxName, String expectedText){
        String actualText = driver.findElement(By.xpath("//*[text()='" + textboxName + "']/following-sibling::span")).getText();
        logger.info("Actual your message is: " + actualText);
        if (expectedText.equals(actualText)){
            return true;
        }
        return false;
    }


    // -----------------------------------------------------------------------------
    // CheckBox
    public void goCheckBox() {
        driver.get("http://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        driver.manage().window().maximize();
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
        return buttonText.equals(expectedText);
    }


    // -----------------------------------------------------------------------------
    // Radio button
    public void goRadioButton() {
        driver.get("http://www.seleniumeasy.com/test/basic-radiobutton-demo.html");
        driver.manage().window().maximize();
    }

    public InputPage clickRadioButtonName(String buttonName){
        driver.findElement(By.xpath("//input[@value='" + buttonName + "']")).click();
        return this;
    }

    public InputPage clickRadioButtonNameGroup(String buttonName, String buttonGroup){
        driver.findElement(By.xpath("//input[@value='" + buttonName + "' and @name='" + buttonGroup + "']")).click();
        return this;
    }


    // -----------------------------------------------------------------------------
    // Select tag drop-down list
    public void goSelectDropDown() {
        driver.get("http://www.seleniumeasy.com/test/basic-select-dropdown-demo.html");
        driver.manage().window().maximize();
    }

    public InputPage dropDownSingleSelectByText(String dropDownBoxName, String text){
        Select dropDown = new Select(driver.findElement(By.xpath("//*[text()='" + dropDownBoxName + "']/following-sibling::div/select")));
        if (!dropDown.isMultiple()){
            dropDown.selectByVisibleText(text);
            return this;
        }

        try {
            Robot rb = new Robot();
            rb.keyPress(KeyEvent.VK_CONTROL);
            dropDown.selectByVisibleText(text);
            Thread.sleep(100);
            rb.keyRelease(KeyEvent.VK_CONTROL);

        } catch (AWTException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }

    public InputPage dropDownMultipleSelectContinous(String dropDownBoxName, int fromItem, int toItem ){
        List<WebElement> selectList = driver.findElements((By.xpath("//*[text()='" + dropDownBoxName + "']/following-sibling::div/select/option")));
        Actions action = new Actions(driver);
        action.clickAndHold(selectList.get(fromItem - 1)).moveToElement(selectList.get(toItem - 1)).release().build().perform();
        return this;
    }

    // -----------------------------------------------------------------------------
    // Jquery drop-down list
    public void goJqueryDropDown() {
        driver.get("http://www.seleniumeasy.com/test/jquery-dropdown-search-demo.html");
        driver.manage().window().maximize();
    }





}
