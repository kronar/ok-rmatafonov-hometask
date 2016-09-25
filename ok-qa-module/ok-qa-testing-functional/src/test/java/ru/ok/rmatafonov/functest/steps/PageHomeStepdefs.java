package ru.ok.rmatafonov.functest.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ru.ok.rmatafonov.functest.businessobjects.UserProfile;
import ru.ok.rmatafonov.pageobjects.pages.PageHome;

import java.text.ParseException;

public class PageHomeStepdefs extends CommonStepdefs {

    @Autowired
    @Lazy
    private PageHome pageHome;

    @Autowired
    public String privateDataHometownHomePagePrefix;

    @Autowired
    private String privateDataHomeAgeDescriptor1;

    @Autowired
    private String privateDataHomeAgeDescriptor234;

    @Autowired
    private String privateDataHomeAgeDescriptor5more;


    @When("^I click the link Change Settings at the Home Page$")
    public void iClickTheLinkChangeSettingsAtTheHomePage() throws Throwable {
        pageHome.clickLinkSettings();
    }

    @When("^I navigate to the Home Page$")
    public void iNavigateToTheHomePage() {
        // It's weird... Some <div ... id='pointerOverlay'></div> receives click after
        // the Private Data is changed
        // TODO: ask developers what is that and how to work around
        webDriverUtils.navigateTo(appURL);
    }

    @Then("^I should see the changed name truncated to (\\d+) symbols with the changed surname truncated to (\\d+) symbols at the Home Page$")
    public void iShouldSeeTheChangedNameTruncatedToSymbolsWithTheChangedSurnameTruncatedToSymbolsAtTheHomePage(int expectedNameLength, int expectedSurnameLength) {
        String expectedNameSurname = formatNameSurname(userProfile, expectedNameLength, expectedSurnameLength);
        String nameSurname = pageHome.getNameSurname();
        assertThatStringsAreEquals(expectedNameSurname, nameSurname);
    }

    @Then("^I should see the stored name truncated to (\\d+) symbols with the stored surname truncated to (\\d+) symbols at the Home Page$")
    public void iShouldSeeTheStoredNameTruncatedToSymbolsWithTheStoredSurnameTruncatedToSymbolsAtTheHomePage(int expectedNameLength, int expectedSurnameLength) {
        String expectedNameSurname = formatNameSurname(storedUserProfile, expectedNameLength, expectedSurnameLength);
        String nameSurname = pageHome.getNameSurname();
        assertThatStringsAreEquals(expectedNameSurname, nameSurname);
    }

    private String formatNameSurname(UserProfile userProfile, int expectedNameLength, int expectedSurnameLength) {
        String expectedName = userProfile.getName();
        expectedName = expectedName.substring(0, Math.min(expectedNameLength, expectedName.length()));

        String expectedSurname = userProfile.getSurname();
        expectedSurname = expectedSurname.substring(0, Math.min(expectedSurnameLength, expectedSurname.length()));

        return expectedName + " " + expectedSurname;
    }

    @Then("^I should see the changed age with the City of Residence at the Home Page$")
    public void iShouldSeeTheChangedAgeAtTheHomePage() throws ParseException {
        String expectedAgeCityOfResidence = formatAgeCityOfResidence(userProfile);
        String ageCityOfResidence = pageHome.getAgeCityOfResidence();
        assertThatStringsAreEquals(expectedAgeCityOfResidence, ageCityOfResidence);
    }

    @Then("^I should see the stored age with the City of Residence at the Home Page$")
    public void iShouldSeeTheStoredAgeAtTheHomePage() throws ParseException {
        String expectedAgeCityOfResidence = formatAgeCityOfResidence(storedUserProfile);
        String ageCityOfResidence = pageHome.getAgeCityOfResidence();
        assertThatStringsAreEquals(expectedAgeCityOfResidence, ageCityOfResidence);
    }

    private String formatAgeCityOfResidence(UserProfile userProfile) {
        Integer dayOfBirth = Integer.parseInt(userProfile.getDayOfBirth());
        Integer monthNumber = userPrivateDataMonthManager.getMonthNumber(userProfile.getMonthOfBirth());
        Integer yearOfBirth = Integer.parseInt(userProfile.getYearOfBirth());

        LocalDate dateOfBirth = new LocalDate(yearOfBirth, monthNumber, dayOfBirth);
        int age = Years.yearsBetween(dateOfBirth, new LocalDate()).getYears();
        String years = age + " ";

        int div10 = age % 10;
        int div100 = age % 100;
        if ((div100 >= 10 && div100 <= 20) || div10 >= 5 || div10 == 0) {
            years += privateDataHomeAgeDescriptor5more;
        } else if (div10 == 1) {
            years += privateDataHomeAgeDescriptor1;
        } else if (div10 == 2 || div10 == 3 || div10 == 4) {
            years += privateDataHomeAgeDescriptor234;
        }

        return years + ", " + userProfile.getCityOfResidenceFormatted();
    }

    @Then("^I should see the changed Hometown at the Home Page$")
    public void iShouldSeeTheChangedHometownAtTheHomePage() {
        String expectedHometown = privateDataHometownHomePagePrefix + userProfile.getHometown();
        String hometown = pageHome.getHomeTownString();
        assertThatStringsAreEquals(expectedHometown, hometown);
    }

    @Then("^I should see the stored Hometown at the Home Page$")
    public void iShouldSeeTheStoredHometownAtTheHomePage() {
        String expectedHometown = privateDataHometownHomePagePrefix + storedUserProfile.getHometown();
        String hometown = pageHome.getHomeTownString();
        assertThatStringsAreEquals(expectedHometown, hometown);
    }
}
