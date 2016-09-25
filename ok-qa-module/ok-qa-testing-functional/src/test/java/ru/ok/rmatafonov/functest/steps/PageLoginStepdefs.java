package ru.ok.rmatafonov.functest.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.StringUtils;
import ru.ok.rmatafonov.functest.businessobjects.UsersManager;
import ru.ok.rmatafonov.pageobjects.pages.PageLogin;
import ru.ok.rmatafonov.utils.WebDriverUtils;

public class PageLoginStepdefs extends CommonStepdefs {

    private String login;
    private String password;

    @Autowired
    @Lazy
    private PageLogin pageLogin;

    /**
     * Set up a user for testing
     * @param username user for testing
     */
    @Given("^I am a user with name \"([^\"]*)\"$")
    public void iAmAUserWithName(String username) {
        this.login = username;
        this.password = usersManager.getUserPassword(username);
    }

    @When("^I navigate to the site and log in$")
    public void iNavigateToTheSiteAndLogIn() throws Throwable {
        if (StringUtils.isEmpty(login)) {
            throw new IllegalStateException("The step 'Given I am a user with name \"<string>\"' should be called first");
        }

        webDriverUtils.navigateTo(appURL);
        pageLogin.login(this.login, this.password);
    }
}
