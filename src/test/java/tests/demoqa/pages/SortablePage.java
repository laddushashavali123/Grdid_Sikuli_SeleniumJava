package tests.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SortablePage {
    private WebDriver driver;
    public SortablePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locator

    // Element
    @FindBy(id="ui-id-1")
    WebElement sortItems;

    // Method
    public void accessPage(){
        driver.get("http://demoqa.com/sortable/");
    }

    public void moveElement(){
        System.out.println(sortItems.getText());

        /*Actions act=new Actions(driver);
        act.clickAndHold(sortItems.get(0)).clickAndHold(sortItems.get(4)).release().build().perform();*/
    }



    // Assertion

}

