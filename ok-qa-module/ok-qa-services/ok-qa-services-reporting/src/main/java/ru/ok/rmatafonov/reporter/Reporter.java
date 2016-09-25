package ru.ok.rmatafonov.reporter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ok.rmatafonov.utils.WebDriverUtils;
import ru.yandex.qatools.allure.annotations.Attachment;

@Component
public class Reporter {
    @Autowired
    private WebDriverUtils webDriverUtils;

    /**
     * Create all the known attachments:
     * <ul>
     *     <li>Screenshot for Allure</li>
     * </ul>
     */
    public void makeAttachments() {
        attachScreenshotToAllure();
    }

    /**
     * Adds a current page screenshot to Allure <br/>
     * If you need to attach screenshot to Allure just call the method without any cast, e.g.
     * <p>
     *     <code>
     *         try { <br/>
     *              ... <br/>
     *         } catch (Throwable e) { <br/>
     *              attachScreenshotToAllure() <br/>
     *         } <br/>
     *     </code>
     * </p>
     * @return screenshot bytes
     */
    @Attachment(value = "Page Screenshot", type = "image/png")
    public byte[] attachScreenshotToAllure() {
        return webDriverUtils.getScreenshot();
    }
}
