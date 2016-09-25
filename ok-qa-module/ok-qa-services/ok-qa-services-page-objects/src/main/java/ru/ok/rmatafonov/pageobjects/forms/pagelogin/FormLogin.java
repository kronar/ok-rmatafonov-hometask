package ru.ok.rmatafonov.pageobjects.forms.pagelogin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.ok.rmatafonov.pageobjects.BasePageObject;

@Component
@Lazy
public class FormLogin extends BasePageObject {

    @FindBy(xpath = ".//*[@id='loginContentBlock']/form")
    private WebElement loginForm;

    @FindBy(xpath = ".//*[@id='field_email']")
    private WebElement loginFormUsername;

    @FindBy(xpath = ".//*[@id='field_password']")
    private WebElement loginFormPassword;

    public FormLogin inputUsername(String username) {
        clearAndSendKeysTo(loginFormUsername, username);
        return this;
    }

    public FormLogin inputPassword(String password) {
        clearAndSendKeysTo(loginFormPassword, password);
        return this;
    }

    public void submit() {
        loginForm.submit();
    }
}
