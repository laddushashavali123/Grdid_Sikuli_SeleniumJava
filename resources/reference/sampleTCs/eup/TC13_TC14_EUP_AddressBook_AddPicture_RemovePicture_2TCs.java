package nuvia.eup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import automation.DriverFactory;
import nuvia.eup.pages.FormManageSelf;
import nuvia.eup.pages.PageAccountEUP;
import nuvia.eup.pages.PageLoginEUP;
import nuvia.eup.pages.TabAddressBook;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TC13_TC14_EUP_AddressBook_AddPicture_RemovePicture_2TCs extends DriverFactory {
	public static final Logger logger = LogManager.getLogger("TC13_TC14");
	@Test
	@Parameters({"waitTime","EUP_url","EUP_user","EUP_password","EUP_fileLocation", "EUP_domain"})
	public void TC13_TC14_EUP_AddressBook_AddPicture_RemovePicture_2TCs_(int timeout, String url, String user, String password, String fileLocation, String domain) throws Exception {
		WebDriver driver = getDriver();
		driver.get(url);

		if (getProtocol().equals("selenium")) {
			driver.manage().window().maximize();
		}
		
		user = user + "@" + domain;
		
		String alertAdd1 = "Picture uploaded.Changes will be reflected only after save.";
		String alertAdd2 = "Contact updated.";
		
		String alertRemove1 = "Picture removed. Changes will be reflected only after save.";
		
		PageLoginEUP loginP = new PageLoginEUP(driver, timeout);
		logger.info("Login with User ID '" + user + "' and Password '" + password + "'");
		loginP.login(user, password);
		
		PageAccountEUP accountP = new PageAccountEUP(driver, timeout);
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		TabAddressBook addressBookT = new TabAddressBook(driver, timeout);
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		logger.info("-----------------------Test case: Add Picture-----------------------");
		Reporter.log("1. Test case 'Add Picture'");
		
		FormManageSelf manageSelf = new FormManageSelf(driver, timeout);
		logger.info("Click 'ADD PICTURE' Button");
		manageSelf.clickAddPictureButton();
		
		String file = fileLocation + "EUP_Add_Picture.jpg";
		logger.info("Upload file '" + file + "'");
		manageSelf.uploadFile(file);
		
		logger.info("Verify that alert '" + alertAdd1 + "' appears after uploading Picture");
		boolean result1a = manageSelf.verifyPictureAlertAppears(alertAdd1);
		if ( result1a ) {
			logger.info("Alert '" + alertAdd1 + "' appears after uploading Picture: PASSED");
			Reporter.log("- Alert '" + alertAdd1 + "' appears after uploading Picture: PASSED");
		}
		else {
			logger.info("Alert '" + alertAdd1 + "' appears after uploading Picture: FAILED");
			Reporter.log("- Alert '" + alertAdd1 + "' appears after uploading Picture: FAILED");
		}
		
		logger.info("Click 'SAVE CONTACT' Button");
		manageSelf.clickSaveContactButton();
		
		logger.info("Verify that alert '" + alertAdd2 + "' appears after adding Picture");
		boolean result1b = addressBookT.verifyAlertAppears(alertAdd2);
		if ( result1b ) {
			logger.info("Alert '" + alertAdd2 + "' appears after adding Picture: PASSED");
			Reporter.log("- Alert '" + alertAdd2 + "' appears after adding Picture: PASSED");
		}
		else {
			logger.info("Alert '" + alertAdd2 + "' appears after adding Picture: FAILED");
			Reporter.log("- Alert '" + alertAdd2 + "' appears after adding Picture: FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		logger.info("Verify that Picture exists after adding");
		boolean result1c = manageSelf.verifyPictureExists();
		if ( result1c ) {
			logger.info("Picture exists after adding: PASSED");
			Reporter.log("- Picture exists after adding: PASSED");
		}
		else {
			logger.info("Picture exists after adding: FAILED");
			Reporter.log("- Picture exists after adding: FAILED");
		}
		
		boolean result1 = result1a && result1b && result1c;
		if ( result1 ) {
			logger.info("Test case 'Add Picture': PASSED");
			Reporter.log("-> Test case 'Add Picture': PASSED");
		}
		else {
			logger.info("Test case 'Add Picture': FAILED");
			Reporter.log("-> Test case 'Add Picture': FAILED");
		}
	
		logger.info("-----------------------Test case: Remove Picture-----------------------");
		Reporter.log("");
		Reporter.log("2. Test case 'Remove Picture'");
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		logger.info("Click 'REMOVE PICTURE' Button");
		manageSelf.clickRemovePictureButton();
		
			
		logger.info("Verify that alert '" + alertRemove1 + "' appears");
		boolean result2a = manageSelf.verifyPictureAlertAppears(alertRemove1);
		if ( result2a ) {
			logger.info("Alert '" + alertRemove1 + "' appears: PASSED");
			Reporter.log("- Alert '" + alertRemove1 + "' appears: PASSED");
		}
		else {
			logger.info("Alert '" + alertRemove1 + "' appears: FAILED");
			Reporter.log("- Alert '" + alertRemove1 + "' appears: FAILED");
		}
		
		logger.info("Click 'SAVE CONTACT' Button");
		manageSelf.clickSaveContactButton();
		
		logger.info("Verify that alert '" + alertAdd2 + "' appears after removing Picture");
		boolean result2b = addressBookT.verifyAlertAppears(alertAdd2);
		if ( result2b ) {
			logger.info("Alert '" + alertAdd2 + "' appears after removing Picture: PASSED");
			Reporter.log("- Alert '" + alertAdd2 + "' appears after removing Picture: PASSED");
		}
		else {
			logger.info("Alert '" + alertAdd2 + "' appears after removing Picture: FAILED");
			Reporter.log("- Alert '" + alertAdd2 + "' appears after removing Picture: FAILED");
		}
		
		logger.info("Click 'Address Book' Tab");
		accountP.clickAddressBookTab();
		logger.info("Click 'MANAGE SELF' Button");
		addressBookT.clickMangeSelfButton();
		
		logger.info("Verify that Picture does not exist after removing");
		boolean result2c = !manageSelf.verifyPictureExists();
		if ( result1c ) {
			logger.info("Picture does not exist after removing: PASSED");
			Reporter.log("- Picture does not exist after removing: PASSED");
		}
		else {
			logger.info("Picture does not exist after removing: FAILED");
			Reporter.log("- Picture does not exist after removing: FAILED");
		}
		
		boolean result2 = result2a && result2b && result2c;
		if ( result2 ) {
			logger.info("Test case 'Remove Picture': PASSED");
			Reporter.log("-> Test case 'Remove Picture': PASSED");
		}
		else {
			logger.info("Test case 'Remove Picture': FAILED");
			Reporter.log("-> Test case 'Remove Picture': FAILED");
		}
	
		Assert.assertTrue(result1 && result2);		
	}
}
