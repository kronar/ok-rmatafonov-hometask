package ru.ok.rmatafonov.functest.steps;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import ru.ok.rmatafonov.functest.businessobjects.UserPrivateDataMonthManager;
import ru.ok.rmatafonov.functest.businessobjects.UserProfile;
import ru.ok.rmatafonov.functest.businessobjects.UsersManager;
import ru.ok.rmatafonov.functest.config.TestingConfig;
import ru.ok.rmatafonov.reporter.Reporter;
import ru.ok.rmatafonov.utils.WebDriverUtils;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

@ContextConfiguration(classes = {TestingConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CommonStepdefs {

    static UserProfile userProfile = new UserProfile();
    static UserProfile storedUserProfile = new UserProfile();

    @Autowired
    UserPrivateDataMonthManager userPrivateDataMonthManager;

    @Autowired
    UsersManager usersManager;

    @Autowired
    Reporter reporter;

    @Autowired
    String appURL;

    @Autowired
    WebDriverUtils webDriverUtils;

    void assertThatStringsAreEquals(String expected, String actual) {
        try {
            Assert.assertThat(actual, equalTo(expected));
        } catch (Throwable e) {
            reporter.makeAttachments();
            throw e;
        }
    }

    void assertThatTrue(String message, boolean condition) {
        try {
            Assert.assertThat(message, condition, is(true));
        } catch (Throwable e) {
            reporter.makeAttachments();
            throw e;
        }
    }

}
