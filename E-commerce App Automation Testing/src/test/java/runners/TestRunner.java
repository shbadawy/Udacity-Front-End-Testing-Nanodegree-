package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/main/resources/features",
		glue = "stepDefinition",
		tags = "@Regression"
		)

//extends AbstractTestNGCucumberTests
public class TestRunner extends AbstractTestNGCucumberTests{

}
