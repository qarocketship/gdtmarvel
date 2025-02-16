package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions","hooks"},
        plugin = {"pretty", "html:Report/test-report.html"},
        monochrome = true,
        tags = "@homepage or @scroll or @search or @character",
        dryRun = false
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
