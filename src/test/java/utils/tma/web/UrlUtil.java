package utils.tma.web;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UrlUtil {
    
    private static int invalidLinksCount;
    public static final Logger logger = LoggerFactory.getLogger("UrlUtil");
    
    public static int validateInvalidLinks(WebDriver driver) {
        
        try {   
            invalidLinksCount = 0;
            List<WebElement> anchorTagsList = driver.findElements(By.tagName("a"));
            logger.info("Total number of links are: " + anchorTagsList.size());
            for (WebElement anchorTagElement : anchorTagsList) {
                if (anchorTagElement != null) {
                    String url = anchorTagElement.getAttribute("href");
                    if (url != null && !url.contains("javascript")) {
                        verifyURLStatus(url);
                    } else {
                        invalidLinksCount++;
                        logger.info("Dead link detected: " + url);
                    }
                }
            }
            
            logger.info("Total number of invalid links are: "  + invalidLinksCount);
        
        } catch (Exception e) {
            e.printStackTrace();
            logger.info(e.getMessage());
            return 0;
        }
        return invalidLinksCount;
    }         
    
    public static void verifyURLStatus(String URL) {

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(URL);
        try {
            HttpResponse response = client.execute(request);
            // verifying response code and The HttpStatus should be 200 if not,
            // increment invalid link count
            ////We can also check for 404 status code like response.getStatusLine().getStatusCode() == 404
            if (response.getStatusLine().getStatusCode() != 200)
                invalidLinksCount++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
