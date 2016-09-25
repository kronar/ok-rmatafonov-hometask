package ru.ok.rmatafonov.pageobjects.pages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.ok.rmatafonov.pageobjects.BasePageObject;
import ru.ok.rmatafonov.pageobjects.forms.pagelogin.FormLogin;

@Component
@Lazy
public class PageLogin extends BasePageObject {

    @Autowired
    private FormLogin formLogin;

    public void login(String username, String password) {
        formLogin.inputUsername(username).inputPassword(password).submit();
    }
}
