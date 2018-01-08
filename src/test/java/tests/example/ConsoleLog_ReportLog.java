package tests.example;

import base.DriverBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleLog_ReportLog extends DriverBase {
    final Logger logger = LoggerFactory.getLogger(ConsoleLog_ReportLog.class);


    @Test
    public void test1() throws Exception {
        Thread.sleep(3000);
        logger.info("This is test 1");
    }

    @Test
    public void test3() throws Exception {
        Thread.sleep(2000);
        logger.info("This is test 3");
    }


    @Test
    public void test2() throws Exception  {
        WebDriver driver = DriverBase.getDriver();
        Thread.sleep(1000);
        logger.info("This is test 2");
        try {
            driver.get("google.com");
        }
        catch (Exception e){
            logger.debug("Exception error is ", e);
        }
    }
}
