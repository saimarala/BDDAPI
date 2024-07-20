package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;



    @CucumberOptions(features = "src/test/resources/features/DataTable.feature",
            glue = {"com.qa.hrm"},
            plugin = {"pretty", "html:target/cucumber-reports", "json:target/cucumber.json"},
            monochrome = true,
            publish = true,
            dryRun = true)
    public class TestRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = true)
        public Object[][] scenarios() {
            return super.scenarios();
        }
    }

