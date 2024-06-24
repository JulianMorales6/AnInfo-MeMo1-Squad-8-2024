import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/cucumber",
    glue = "com.aninfo.integration.cucumber",
    plugin = {"pretty", "html:target/cucumber/report.html, json:target/cucumber/report.json"}
)
public class RunCucumberTest {
}