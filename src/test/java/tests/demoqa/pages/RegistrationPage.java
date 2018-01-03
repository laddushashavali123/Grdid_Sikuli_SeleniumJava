package tests.demoqa.pages;

import base.DriverBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    @FindBy(xpath="//*[@id='name_3_firstname']")
    private WebElement firstNameTextBox;

    @FindBy(xpath="//*[@id='name_3_lastname']")
    private WebElement lasNameTextBox;


    // Constructor
    public RegistrationPage() throws Exception {
        PageFactory.initElements(DriverBase.getDriver(), this);
    }

    //
    public RegistrationPage enterUserName(String text){
        firstNameTextBox.sendKeys(text);
        return this;
    }

}
