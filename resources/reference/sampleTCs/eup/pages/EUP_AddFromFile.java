package nuvia.eup.pages;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import automation.utils.web.WebElementSupport2;
import automation.utils.web.WebUtil;

import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

public class EUP_AddFromFile {

	private WebDriver driver;
	private int timeout = 15;
	private final String pageLoadedText = "Please choose a Web Mail Program:";
	private final String pageUrl = "/";
	
	public static final Logger logger = LogManager.getLogger("EUP_AddFromFile");
	
	WebUtil webUtil = new WebUtil();
	
	@FindBy(xpath="html/body/div[3]/div/div/div[1]/div[2]/div[2]/div/div/span[1]") private WebElement button_select;
	@FindBy(xpath=".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[1]/button") private WebElement button_upload;
	@FindBy(xpath=".//*[@id='uploadImageModal']/div/div/div[1]/div[2]/div[3]/div/div/div[2]/button") private WebElement button_cancel;
//	
//	
	// ------------------------Constructors------------------------
	public EUP_AddFromFile() {
	}

	public EUP_AddFromFile(WebDriver driver) {
		this();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public EUP_AddFromFile(WebDriver driver, int timeout) {
		this();
		this.driver = driver;
		this.timeout = timeout;
		PageFactory.initElements(driver, this);
	}
//
//	// ----- Action ---------
		
	// click button Select
	public EUP_AddFromFile clickButtonSelect() throws Exception {
		WebElementSupport2.clickSupport(button_select, timeout, 1000);
		return this;
	}
	
	// click button Upload
	public EUP_AddFromFile clickButtonUpload() throws Exception {
		WebElementSupport2.clickSupport(button_upload, timeout, 1000);
		waitForUploadFinished(driver, timeout);
		return this;
	}
	
	// click button Cancel
	public EUP_AddFromFile clickButtonCancel() throws Exception {
		WebElementSupport2.clickSupport(button_cancel, timeout, 1000);
		return this;
	}
	
	public EUP_AddFromFile uploadFile(String strPicture) throws Exception{
		Thread.sleep(3);
		System.out.println(strPicture);		
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(strPicture);
		Thread.sleep(2000);		
		return this;
	}
	
	public EUP_AddFromFile uploadFileEUP(String fileLocation, String fileName) throws Exception {
		logger.info("Select '" + fileName + "' to upload");
		String file = fileLocation + File.separator + fileName;
		WebElementSupport2.uploadFileByID(driver, "file_upload", file);	
		logger.info("Click 'UPLOAD' button");
		clickButtonUpload();
		return this;
	}
	
	private static void waitForUploadFinished(WebDriver driver, int timeout_seconds) throws Exception {
		   boolean result = true; 
		   Thread.sleep(1000);
		   logger.info("Waiting for page loading (Max: " + timeout_seconds + " seconds)");
		   for (int i = 0; i < timeout_seconds; i++) {
			   Thread.sleep(1000);
			   try{		   
				   List<WebElement> elms = null;
				   try{
					 
					   elms = driver.findElements(By.xpath("//img[@data-ng-show='upload_progress']"));
				   } catch(Exception e){
					   
				   }
				   for (int j = 0; j < elms.size(); j++) {			   
					   if (elms.get(j).isDisplayed() == true) {
						   result = false;
						   break;
					   }
					   result = true;
				   }
				   if (result) {
					   System.out.print("");
					   break;
				   }
			   } catch (Exception e){ 
			   }
			   if (i > 0) System.out.print(".");
		   }
		    if (!result) System.out.println("Unable to find element after " + timeout_seconds + " seconds");
		    else logger.info("Page loads successfully");
		  }
		  
//   //----------- Verify ------------------------
//	
	

}
