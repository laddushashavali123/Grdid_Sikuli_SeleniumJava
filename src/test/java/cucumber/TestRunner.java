package cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        // Indicat the location folder of feature file
        features = "src/test/java/cucumber/features",
        // Indicate the location folder of steps file
        glue = {"cucumber/steps"},
        // The tags can be used when specifying what tests to run through any of the running mechanism.
        // Run multiple feature with specific tags, tags ={“@SoapUI","@Functional"},...
        // Run @SoapUI tag feature and does not run feature with @Functional tag, tags ={“@SoapUI","~@Functional"},...
        tags = {"~@Ignore"}
        // Plugin option is used to specify different formatting options for the output reports.
        /*plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty",
                "json:target/cucumber-reports/json-reports/CucumberTestReport.json",
                "rerun:target/cucumber-reports/rerun-reports/rerun.txt"
        }*/
)
public class TestRunner extends AbstractTestNGCucumberTests {
}

