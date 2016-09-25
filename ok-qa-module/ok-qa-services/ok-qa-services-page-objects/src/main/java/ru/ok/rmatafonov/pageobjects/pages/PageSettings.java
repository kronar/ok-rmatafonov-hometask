package ru.ok.rmatafonov.pageobjects.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.ok.rmatafonov.pageobjects.BasePageObject;

@Component
@Lazy
public class PageSettings extends BasePageObject {

    @FindBy(xpath = ".//*[@id='hook_Block_UserConfigMRB']//a[contains(@href, '_EditProfile')]")
    private WebElement linkEditPrivateInfo;

    @FindBy(xpath = ".//*[@id='hook_Block_UserConfigMRB']//div[@class='user-settings_i_tx textWrap']")
    private WebElement labelPrivateData;

    public void clickLinkEditPrivateInfo() {
        clickElement(linkEditPrivateInfo);
    }

    public String getPrivateDataText() {
        return getElementText(labelPrivateData);
    }
}
