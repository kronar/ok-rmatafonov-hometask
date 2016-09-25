package ru.ok.rmatafonov.pageobjects.notifications.pagesettings;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.ok.rmatafonov.pageobjects.BasePageObject;

@Component
@Lazy
public class NotificationDataChanged extends BasePageObject {

    @FindBy(xpath = ".//*[@id='notifyPanelId']//*[@id='buttonId_button_close']")
    private WebElement buttonClose;

    public void close() {
        clickElement(buttonClose);
    }
}
