package ru.ok.rmatafonov.pageobjects.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.ok.rmatafonov.pageobjects.BasePageObject;

@Component
@Lazy
public class PageHome extends BasePageObject {

    @FindBy(xpath = ".//*[@id='hook_Block_LeftColumnTopCardUser']//a[@href='/settings']/span")
    private WebElement linkSettings;

    @FindBy(xpath = ".//*[@id='hook_Block_MiddleColumnTopCardUser']//*[@class='mctc_name_tx bl']")
    private WebElement labelNameSurname;

    @FindBy(xpath = ".//*[@id='hook_Block_MiddleColumnTopCardUser']//*[@class='mctc_infoContainer_not_block']")
    private WebElement labelAgeCityOfResidence;

    @FindBy(xpath = ".//*[@id='aboutPanel']/div[@class='portlet_b']/ul/li[1]")
    private WebElement labelHometown;

    public void clickLinkSettings() {
        clickElement(linkSettings);
    }

    public String getNameSurname() {
        return getElementText(labelNameSurname);
    }

    public String getAgeCityOfResidence() {
        return getElementText(labelAgeCityOfResidence);
    }

    public String getHomeTownString() {
        return getElementText(labelHometown);
    }
}
