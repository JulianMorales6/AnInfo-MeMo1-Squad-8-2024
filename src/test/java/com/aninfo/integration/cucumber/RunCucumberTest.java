import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.aninfo.ProyectApp;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/cucumber",
    glue = "com.aninfo.integration.cucumber",
    plugin = {"pretty", "html:target/cucumber/report.html, json:target/cucumber/report.json"}
)
@CucumberContextConfiguration
@SpringBootTest(classes = ProyectApp.class)
public class RunCucumberTest {
}
