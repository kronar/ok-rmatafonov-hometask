package ru.ok.rmatafonov.functest;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ok.rmatafonov.functest.config.TestingConfig;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {
                "pretty"
        }
)
public class CucumberRunner {
}
