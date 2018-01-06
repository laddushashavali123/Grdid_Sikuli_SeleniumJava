package listeners;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * @author MyPC
 * Ref: http://www.nullin.com/2010/07/28/logging-tests-to-separate-files
 */
public class LogListener extends TestListenerAdapter {
    private static final Logger log = LoggerFactory.getLogger(LogListener.class.getName());
    private static final String TEST_NAME = "testname";

    @Override
    public void onTestStart(ITestResult tr)  {
        MDC.put(TEST_NAME, tr.getName());
        log.info(tr.getMethod() + " is running.");
    }

    @Override
    public void onTestSuccess(ITestResult tr) {
        log.info(tr.getName() + " is PASSED.");
        MDC.remove(TEST_NAME);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error(tr.getName() + " is FAILED.");
        MDC.remove(TEST_NAME);
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log.info(tr.getName() + " is SKIPPED.");
        MDC.remove(TEST_NAME);
    }


}
