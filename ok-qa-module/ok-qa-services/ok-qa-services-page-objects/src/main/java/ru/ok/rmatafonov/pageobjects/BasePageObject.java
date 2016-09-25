package ru.ok.rmatafonov.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.ok.rmatafonov.reporter.Reporter;
import ru.ok.rmatafonov.utils.WebDriverUtils;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Lazy
public class BasePageObject extends WebDriverUtils {

    private static Logger logger = LoggerFactory.getLogger(BasePageObject.class);

    @Autowired
    private Reporter reporter;

    @Autowired
    public int defaultTimeoutImplicitWait;

    @Autowired
    public int defaultTimeoutExplicitWait;

    @PostConstruct
    public void init() {
        logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Initializing Page Object: " + this.getClass().getSimpleName());
        PageFactory.initElements(driver, this);
    }

    /**
     * Wait for specified element to be clickable <br/>
     * The default explicit timeout is used
     *
     * @param element the element under waiting
     */
    protected void waitUntilElementIsClickable(WebElement element) {
        logger.info("Waiting for element \"{}\" to be clickable", element);
        new WebDriverWait(driver, defaultTimeoutExplicitWait).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * Wait for specified element to be visible <br/>
     * The default explicit timeout is used
     *
     * @param element the element under waiting
     */
    protected void waitUntilElementIsVisible(WebElement element) {
        logger.info("Waiting for element \"{}\" to be visible", element);
        new WebDriverWait(driver, defaultTimeoutExplicitWait).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Get the specified JS property of the specified element
     *
     * @param element  the element under waiting
     * @param propName property name
     */
    protected String getJsPropertyOfElement(WebElement element, String propName) {
        logger.info("Getting Javascript property '" + propName + "' of an element " + element);
        return (String) (((JavascriptExecutor) driver).executeScript("return jQuery(arguments[0]).prop('" + propName + "');", element));
    }

    /**
     * Find an element by the specified xpath with driver.findElement <br/>
     * Exceptions are cought: ElementNotVisibleException | NoSuchElementException | StaleElementReferenceException
     *
     * @param xpath xpath
     * @return a WebElement by the specified xpath
     */
    protected WebElement getElement(String xpath) {
        logger.info("Trying to find element by XPATH: {}", xpath);
        try {
            try {
                return driver.findElement(By.xpath(xpath));
            } catch (ElementNotVisibleException | NoSuchElementException | StaleElementReferenceException e) {
                waitForPageUpdate();
                return driver.findElement(By.xpath(xpath));
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Find elements by the specified xpath with driver.findElements <br/>
     * Exceptions are cought: ElementNotVisibleException | NoSuchElementException | StaleElementReferenceException
     *
     * @param xpath xpath
     * @return a List of WebElements by the specified xpath
     */
    protected List<WebElement> getElements(String xpath) {
        logger.info("Trying to find elements by XPATH: {}", xpath);
        try {
            try {
                return driver.findElements(By.xpath(xpath));
            } catch (ElementNotVisibleException | NoSuchElementException | StaleElementReferenceException e) {
                waitForPageUpdate();
                return driver.findElements(By.xpath(xpath));
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Clears the specified WebElement and send keys to it <br/>
     * ElementNotVisibleException and StaleElementReferenceException are caught <br/>
     * Makes attachments on fail <br/>
     *
     * @param to   WebElement to action with
     * @param keys a String to be sent to the WebElement
     */
    protected void clearAndSendKeysTo(WebElement to, CharSequence keys) {
        logger.info("Sending keys '" + keys + "' to an element " + to);
        try {
            try {
                to.clear();
                to.sendKeys(keys);
            } catch (ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                to.clear();
                to.sendKeys(keys);
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
        waitForPageUpdate();
    }

    /**
     * Send keys the specified WebElement <br/>
     * ElementNotVisibleException and StaleElementReferenceException are caught <br/>
     * Makes attachments on fail <br/>
     *
     * @param to   WebElement to action with
     * @param keys a String to be sent to the WebElement
     */
    protected void sendKeysTo(WebElement to, CharSequence keys) {
        logger.info("Sending keys '" + keys + "' to an element " + to);
        try {
            try {
                to.sendKeys(keys);
            } catch (ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                to.sendKeys(keys);
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Check if a specified WebElement is displayed on a page <br/>
     * ElementNotVisibleException and StaleElementReferenceException are caught <br/>
     * Makes attachments on fail <br/>
     *
     * @param element the WebElement to check
     * @return true or false
     */
    protected boolean isElementPresent(WebElement element) {
        logger.info("Checking presence of an element " + element);
        try {
            try {
                return element.isDisplayed();
            } catch (NoSuchElementException | ElementNotVisibleException e) {
                return false;
            } catch (StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                return element.isDisplayed();
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Check if a specified WebElement is displayed on a page <br/>
     * ElementNotVisibleException and StaleElementReferenceException are caught <br/>
     * Makes attachments on fail <br/>
     *
     * @param by the WebElement to check
     * @return true or false
     */
    protected boolean isElementPresent(By by) {
        logger.info("Checking presence of an element {}", by);
        try {
            try {
                return driver.findElement(by).isDisplayed();
            } catch (NoSuchElementException | ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                return driver.findElement(by).isDisplayed();
            }
        } catch (NoSuchElementException | ElementNotVisibleException e) {
            return false;
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Clicks a specified WebElement and waits for Javascript completion after click <br/>
     * ElementNotVisibleException and StaleElementReferenceException are caught <br/>
     * Makes attachments on fail <br/>
     *
     * @param element the WebElement to be clicked
     */
    protected void clickElement(WebElement element) {
        clickElement(element, true);
    }

    /**
     * Clicks a specified WebElement and waits for Javascript completion after click <br/>
     * ElementNotVisibleException and StaleElementReferenceException are caught <br/>
     * Makes attachments on fail <br/>
     *
     * @param element   the WebElement to be clicked
     * @param waitForJS waits for Javascript completion after click if true
     */
    protected void clickElement(WebElement element, boolean waitForJS) {
        logger.info("Clicking an element " + element);
        try {
            try {
                element.click();
            } catch (ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                element.click();
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }

        if (waitForJS) {
            waitForPageUpdate();
        }
    }

    /**
     * Clicks a specified WebElement by it's locator and waits for Javascript completion after click <br/>
     * ElementNotVisibleException and StaleElementReferenceException are caught <br/>
     * Makes attachments on fail <br/>
     *
     * @param by        the WebElement locator to be clicked
     * @param waitForJS waits for Javascript completion after click if true
     */
    protected void clickElement(By by, boolean waitForJS) {
        logger.info("Clicking an element " + by);
        try {
            try {
                driver.findElement(by).click();
            } catch (ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                driver.findElement(by).click();
            }

            if (waitForJS) {
                waitForPageUpdate();
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Get a text of a specified WebElement <br/>
     * ElementNotVisibleException and StaleElementReferenceException are caught <br/>
     * Makes attachments on fail <br/>
     *
     * @param element the WebElement to interact with
     * @return the text of the specified WebElement
     */
    protected String getElementText(WebElement element) {
        logger.info("Getting a text of an element " + element);
        try {
            try {
                return element.getText();
            } catch (NoSuchElementException | ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                return element.getText();
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Check if the specified WebElement selected <br/>
     * ElementNotVisibleException and StaleElementReferenceException are caught <br/>
     * Makes attachments on fail <br/>
     *
     * @param element the WebElement to interact with
     * @return true - selected, false - vise versa
     */
    protected boolean isElementSelected(WebElement element) {
        logger.info("Checking if an element {} is selected", element);
        try {
            try {
                return element.isSelected();
            } catch (NoSuchElementException | ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                return element.isSelected();
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Convert the specified element into Select element and get it's options
     *
     * @param element the Select WebElement
     * @return a List of WebElements - options of the specified Select
     */
    protected List<WebElement> getSelectElementOptions(WebElement element) {
        logger.info("Trying to get options of a Select: {}", element);
        try {
            try {
                Select select = new Select(element);
                return select.getOptions();
            } catch (NoSuchElementException | ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                Select select = new Select(element);
                return select.getOptions();
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Convert the specified element into Select element and get it's selected option
     *
     * @param element the Select WebElement
     * @return a WebElement - selected option of the specified Select
     */
    protected WebElement getSelectElementSelectedOption(WebElement element) {
        logger.info("Trying to get a selected option of a Select: {}", element);
        try {
            try {
                Select select = new Select(element);
                return select.getFirstSelectedOption();
            } catch (NoSuchElementException | ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                Select select = new Select(element);
                return select.getFirstSelectedOption();
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    /**
     * Convert the specified element into Select element and set it's option by the specified index
     *
     * @param element the Select WebElement
     * @param index   index of a Select element to be selected
     */
    protected void setSelectElementOptionByIndex(WebElement element, int index) {
        logger.info("Trying to get a selected option of a Select: {}", element);
        try {
            try {
                Select select = new Select(element);
                select.selectByIndex(index);
            } catch (NoSuchElementException | ElementNotVisibleException | StaleElementReferenceException e) {
                waitForPageUpdate();
                PageFactory.initElements(driver, this);
                Select select = new Select(element);
                select.selectByIndex(index);
            }
        } catch (Exception e) {
            reporter.makeAttachments();
            throw e;
        }
    }
}
