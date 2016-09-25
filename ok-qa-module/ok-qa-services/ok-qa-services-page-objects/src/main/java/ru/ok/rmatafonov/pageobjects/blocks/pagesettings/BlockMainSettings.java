package ru.ok.rmatafonov.pageobjects.blocks.pagesettings;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.ok.rmatafonov.pageobjects.BasePageObject;

public class BlockMainSettings extends BasePageObject {

    @FindBy(xpath = ".//*[@id='hook_Block_UserConfigMRB']//a[contains(@href, '_EditProfile')]")
    private WebElement linkEditProfile;

    public void clickLinkEditProfile() {
        clickElement(linkEditProfile);
    }

}
