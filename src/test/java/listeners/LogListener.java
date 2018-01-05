/**
 * 
 */
package listeners;

import org.testng.IClass;
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
    private static final Logger log = LoggerFactory.getLogger(LogListener.class);
    public static final String TEST_NAME = "testname";


    @Override
    public void onTestStart(ITestResult result)  {
        log.info("I am in log listener");
        MDC.put(TEST_NAME, result.getName());

    }


    @Override
    public void onTestSuccess(ITestResult tr) {
        MDC.remove(TEST_NAME);
        System.out.println(".....");
    }
/*
    @Override
    public void onTestFailure(ITestResult tr) {

        log("Test '" + tr.getName() + "' FAILED");
        log("Priority of this method is " + tr.getMethod().getPriority());
        System.out.println(".....");
    }

    @Override
    public void onTestSkipped(ITestResult tr) {
        log("Test '" + tr.getName() + "' SKIPPED");
        System.out.println(".....");
    }

    private void log(String methodName) {
        System.out.println(methodName);
    }

    private void log(IClass testClass) {
        System.out.println(testClass);
    }*/
}
