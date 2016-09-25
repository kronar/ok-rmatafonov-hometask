package ru.ok.rmatafonov.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.test.annotation.DirtiesContext;
import ru.ok.rmatafonov.exception.NotSupportedDriverException;

import java.util.concurrent.TimeUnit;

@Configuration
@ComponentScan
@PropertySource(value = "classpath:${APP_ENV:prod}.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WebDriverConfiguration {

    private Logger logger = LoggerFactory.getLogger(WebDriverConfiguration.class);

    @Value("${BROWSER:firefox}")
    private String propertyBrowser;

    @Value("${ru.ok.rmatafonov.browser.path.chrome}")
    private String propertyChromePath;

    @Value("${ru.ok.rmatafonov.browser.path.ie}")
    private String propertyIEPath;

    @Value("${ru.ok.rmatafonov.timouts.sec.implicitWait}")
    private int propertyDefaultTimeoutImplicitWait;

    @Value("${ru.ok.rmatafonov.timouts.sec.explicitWait}")
    private int propertyDefaultTimeoutExplicitWait;

    @Value("${ru.ok.rmatafonov.timouts.sec.jsCompletion}")
    private int propertyJSCompletionDefaultTimeout;

    @Bean
    public int jsCompletionDefaultTimeout() {
        return propertyJSCompletionDefaultTimeout;
    }

    @Bean
    public int defaultTimeoutImplicitWait() {
        return propertyDefaultTimeoutImplicitWait;
    }

    @Bean
    public int defaultTimeoutExplicitWait() {
        return propertyDefaultTimeoutExplicitWait;
    }

    @Bean(destroyMethod = "quit")
    public WebDriver webDriver() {
        WebDriver driver;

        switch (propertyBrowser.toLowerCase()) {
            case "firefox":
            case "ff":
                logger.info("Instantiating Firefox Driver");
                driver = new FirefoxDriver();
                logger.info("Instantiated Firefox");
                break;
            case "chrome":
            case "ch":
                logger.info("Instantiating Chrome Driver");
                System.setProperty("webdriver.chrome.driver", propertyChromePath);
                driver = new ChromeDriver();
                logger.info("Instantiated Chrome");
                break;
            case "internet explorer":
            case "internetexplorer":
            case "ie":
                logger.info("Instantiating Internet Explorer Driver");
                System.setProperty("webdriver.ie.driver", propertyIEPath);
                driver = new InternetExplorerDriver();
                logger.info("Instantiated Internet Explorer");
                break;
            default:
                throw new NotSupportedDriverException("Requested WebDriver \"" + propertyBrowser + "\" is not supported yet");
        }

        driver.manage().timeouts().implicitlyWait(propertyDefaultTimeoutImplicitWait, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    //To resolve ${} in @Value
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
