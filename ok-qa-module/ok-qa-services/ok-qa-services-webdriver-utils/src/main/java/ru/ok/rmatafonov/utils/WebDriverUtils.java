package ru.ok.rmatafonov.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class WebDriverUtils {
    private Logger logger = LoggerFactory.getLogger(WebDriverUtils.class);

    private static final String JS_AJAX_COMPLETION =
            "var docReady = window.document.readyState == 'complete';" +
            "var isJqueryComplete = typeof(window.jQuery) != 'function' || window.jQuery.active == 0;" +
            "var isPrototypeComplete = typeof(window.Ajax) != 'function' || window.Ajax.activeRequestCount == 0;" +
            "var isDojoComplete = typeof(window.dojo) != 'function' || window.dojo.io.XMLHTTPTransport.inFlight.length == 0;" +
            "var result = docReady && isJqueryComplete && isPrototypeComplete && isDojoComplete;" +
            "return result;";

    @Autowired
    protected WebDriver driver;

    @Autowired
    private int jsCompletionDefaultTimeout;

    /**
     * The method navigates to the specified URL
     * @param url URL to navigate
     */
    public void navigateTo(String url) {
        logger.info("Navigating to URL: " + url);
        driver.get(url);
    }

    private ExpectedCondition<Object> isPageUpdated = new ExpectedCondition<Object>() {
        public Boolean apply(WebDriver webDriver) {
            return Boolean.parseBoolean(((JavascriptExecutor) webDriver).executeScript(JS_AJAX_COMPLETION).toString());
        }
    };

    /**
     * The method waits for Javascript completion at a page
     * @param timeout timeout in seconds
     */
    public void waitForPageUpdate(int timeout) {
        logger.info("Waiting for JS completion with timeout " + timeout + " seconds");
        Wait<WebDriver> wait = new WebDriverWait(driver, timeout, 200);
        wait.until(isPageUpdated);
    }

    /**
     * The method waits for Javascript completion at a page with default timeout
     */
    public void waitForPageUpdate() {
        logger.info("Waiting for JS completion with timeout " + jsCompletionDefaultTimeout + " seconds");
        Wait<WebDriver> wait = new WebDriverWait(driver, jsCompletionDefaultTimeout, 200);
        wait.until(isPageUpdated);
    }

    /**
     * The method takes a screenshot of the current page
     * @return bytes array of the screenshot
     */
    public byte[] getScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
