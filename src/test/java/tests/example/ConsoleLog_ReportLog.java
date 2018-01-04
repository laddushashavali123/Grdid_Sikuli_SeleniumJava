package tests.example;

import base.DriverBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class ConsoleLog_ReportLog {
    private static final Logger logger = LogManager.getLogger(ConsoleLog_ReportLog.class);

    @Test
    public void test1() throws Exception {

        logger.trace("tessdftt *****");

        Thread.sleep(5000);
    }
}
