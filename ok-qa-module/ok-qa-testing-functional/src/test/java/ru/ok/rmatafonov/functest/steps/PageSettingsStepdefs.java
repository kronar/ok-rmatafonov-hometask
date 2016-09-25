package ru.ok.rmatafonov.functest.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import ru.ok.rmatafonov.functest.businessobjects.UserProfile;
import ru.ok.rmatafonov.pageobjects.forms.pagesettings.FormEditProfile;
import ru.ok.rmatafonov.pageobjects.notifications.pagesettings.NotificationDataChanged;
import ru.ok.rmatafonov.pageobjects.pages.PageSettings;

public class PageSettingsStepdefs extends CommonStepdefs {

    private static final int MAX_NAME_LENGTH = 16;
    private static final int MAX_SURNAME_LENGTH = 24;

    @Autowired
    @Lazy
    private PageSettings pageSettings;

    @Autowired
    @Lazy
    private FormEditProfile formEditProfile;

    @Autowired
    @Lazy
    private NotificationDataChanged notificationDataChanged;

    @Autowired
    private String privateDataSettingsTemplate;

    @Autowired
    private String privateDataGenderDescriptorMale;

    @Autowired
    private String privateDataGenderDescriptorFemale;

    @When("^I click the link Change in the section Private Data at the Settings Page$")
    public void iClickTheLinkChangeInTheSectionPrivateDataAtTheSettingsPage() throws Throwable {
        pageSettings.clickLinkEditPrivateInfo();
    }

    @When("^I set a random Name of (\\d+) symbols in the form Change Private Data at the Settings Page$")
    public void iSetARandomNameOfSymbolsInTheFormChangePrivateDataAtTheSettingsPage(int symbolsCount) {
        String newName = formEditProfile.inputRandomName(symbolsCount);
        userProfile.setName(newName);
    }

    @When("^I set a random Surname of (\\d+) symbols in the form Change Private Data at the Settings Page$")
    public void iSetARandomSurnameOfSymbolsInTheFormChangePrivateDataAtTheSettingsPage(int symbolsCount) {
        String newSurname = formEditProfile.inputRandomSurname(symbolsCount);
        userProfile.setSurname(newSurname);
    }

    @When("^I set the next Day, Month and Year for the Birth Date in the form Change Private Data at the Settings Page$")
    public void iSetTheNextDayMonthAndYearForTheBirthDateInTheFormChangePrivateDataAtTheSettingsPage() {
        String newDayOfBirth = formEditProfile.selectNextBirthdayDay();
        String newMonthOfBirth = formEditProfile.selectNextBirthdayMonth();
        String newYearOfBirth = formEditProfile.selectNextBirthdayYear();

        userProfile.setDayOfBirth(newDayOfBirth);
        userProfile.setMonthOfBirth(newMonthOfBirth);
        userProfile.setYearOfBirth(newYearOfBirth);
    }

    @When("^I change the Gender in the form Change Private Data at the Settings Page$")
    public void iChangeTheGenderInTheFormChangePrivateDataAtTheSettingsPage() {
        String newGender = formEditProfile.changeGender();
        userProfile.setGender(newGender);
    }

    @When("^I input a letter into the City of Residence and select some value in the form Change Private Data at the Settings Page$")
    public void iInputALetterIntoTheCityOfResidenceAndSelectSomeValueInTheFormChangePrivateDataAtTheSettingsPage() {
        String newCityOfResidence = formEditProfile.selectRandomCityOfResidence();
        userProfile.setCityOfResidence(newCityOfResidence);
        userProfile.setCityOfResidenceFormatted(formEditProfile.getCityOfResidence());
    }

    @When("^I input the stored City of Residence and select it with storing the long name in the form Change Private Data at the Settings Page$")
    public void iInputTheStoredCityOfResidenceAndSelectItInTheFormChangePrivateDataAtTheSettingsPage() {
        String newCityOfResidence = formEditProfile.selectCityOfResidence(storedUserProfile.getCityOfResidenceFormatted());
        storedUserProfile.setCityOfResidence(newCityOfResidence);
    }

    @When("^I input a letter into the Hometown and select some value in the form Change Private Data at the Settings Page$")
    public void iInputALetterIntoTheHometownAndSelectSomeValueInTheFormChangePrivateDataAtTheSettingsPage() {
        String newHometown = formEditProfile.selectRandomHometown();
        userProfile.setHometown(newHometown);
        userProfile.setHometownFormatted(formEditProfile.getHometown());
    }

    @When("^I input the stored Hometown and select it with storing the long name in the form Change Private Data at the Settings Page$")
    public void iInputTheStoredHometownAndSelectItInTheFormChangePrivateDataAtTheSettingsPage() {
        String newHometown = formEditProfile.selectHometown(storedUserProfile.getHometownFormatted());
        storedUserProfile.setHometown(newHometown);
    }

    @When("^I click button Save in the form Change Private Data at the Settings Page$")
    public void iClickButtonSaveInTheFormChangePrivateDataAtTheSettingsPage() {
        formEditProfile.clickButtonSave();
    }

    @When("^I click button Close in the notification Private Data Changed at the Settings Page$")
    public void iClickButtonCloseInTheNotificationPrivateDataChangedAtTheSettingsPage() {
        notificationDataChanged.close();
    }

    @Then("^I should see the section Private Data formatted by the current language template with the changed data at the Settings Page$")
    public void iShouldSeeTheSectionPrivateDataFormattedByTheCurrentLanguageTemplateWithTheChangedDataAtTheSettingsPage() {
        // TODO: it's much more stable to get this data from DB or some Service (currently this could produce false failure)
        String expectedPrivateData = formatPrivateData(userProfile);
        String actualPrivateData = pageSettings.getPrivateDataText();
        assertThatStringsAreEquals(expectedPrivateData, actualPrivateData);
    }

    @Then("^I should see the section Private Data formatted by the current language template with the stored data at the Settings Page$")
    public void iShouldSeeTheSectionPrivateDataFormattedByTheCurrentLanguageTemplateWithTheStoredDataAtTheSettingsPage() {
        // TODO: it's much more stable to get this data from DB or some Service (currently this could produce false failure)
        String expectedPrivateData = formatPrivateData(storedUserProfile);
        String actualPrivateData = pageSettings.getPrivateDataText();
        assertThatStringsAreEquals(expectedPrivateData, actualPrivateData);
    }

    @Then("^I should see the section Private Data formatted by the current language template with the stored data and changed name and surname at the Settings Page$")
    public void iShouldSeeTheSectionPrivateDataFormattedByTheCurrentLanguageTemplateWithTheStoredDataAndChangedNameSndSurnameAtTheSettingsPage() {
        // TODO: it's much more stable to get this data from DB or some Service (currently this could produce false failure)
        storedUserProfile.setName(userProfile.getName());
        storedUserProfile.setSurname(userProfile.getSurname());
        String expectedPrivateData = formatPrivateData(storedUserProfile);
        String actualPrivateData = pageSettings.getPrivateDataText();
        assertThatStringsAreEquals(expectedPrivateData, actualPrivateData);
    }

    private String formatPrivateData(UserProfile userProfile) {
        String expectedName = userProfile.getName();
        expectedName = expectedName.substring(0, Math.min(MAX_NAME_LENGTH, expectedName.length()));

        String expectedSurname = userProfile.getSurname();
        expectedSurname = expectedSurname.substring(0, Math.min(MAX_SURNAME_LENGTH, expectedSurname.length()));

        return privateDataSettingsTemplate
                .replace("<name>", expectedName)
                .replace("<surname>", expectedSurname)
                .replace("<gender>", "male".equals(userProfile.getGender()) ? privateDataGenderDescriptorMale : privateDataGenderDescriptorFemale)
                .replace("<date_of_birth>", userProfile.getDayOfBirth() + " " +
                        userPrivateDataMonthManager.getChangedMonth(userProfile.getMonthOfBirth()) + " " +
                        userProfile.getYearOfBirth())
                .replace("<city_of_residence>", userProfile.getCityOfResidence().replaceAll("\\n.*", ""))
                .replace("<hometown>", userProfile.getHometown().replaceAll("\\n.*", ""));
    }

    @When("^I input \"([^\"]*)\" into City of Residence in the form Change Private Data at the Settings Page$")
    public void iInputIntoCityOfResidenceInTheFormChangePrivateDataAtTheSettingsPage(String value) {
        formEditProfile.inputCityOfResidence(value);
    }

    @When("^I input \"([^\"]*)\" into Hometown in the form Change Private Data at the Settings Page$")
    public void iInputIntoHometownInTheFormChangePrivateDataAtTheSettingsPage(String value) {
        formEditProfile.inputHometown(value);
    }

    @Then("^I should see the error message under the City of Residence in the form Change Private Data at the Settings Page:$")
    public void iShouldSeeTheErrorMessageUnderTheCityOfResidenceInTheFormChangePrivateDataAtTheSettingsPage(String message) {
        boolean isDisplayed = formEditProfile.isErrorMessageDisplayed(message);
        assertThatTrue("Error message under the City of Residence is not displayed", isDisplayed);
    }

    @Then("^I should see the error message under the Hometown in the form Change Private Data at the Settings Page:$")
    public void iShouldSeeTheErrorMessageUnderTheHometownInTheFormChangePrivateDataAtTheSettingsPage(String message) {
        boolean isDisplayed = formEditProfile.isErrorMessageDisplayed(message);
        assertThatTrue("Error message under the Hometown is not displayed", isDisplayed);
    }

    @When("^I store the current data from the fields in the form Change Private Data at the Settings Page$")
    public void iStoreTheCurrentDataFromTheFieldsInTheFormChangePrivateDataAtTheSettingsPage() {
        storedUserProfile.setName(formEditProfile.getName());
        storedUserProfile.setSurname(formEditProfile.getSurname());
        storedUserProfile.setDayOfBirth(formEditProfile.getDayOfBirth());
        storedUserProfile.setMonthOfBirth(formEditProfile.getMonthOfBirth());
        storedUserProfile.setYearOfBirth(formEditProfile.getYearOfBirth());
        storedUserProfile.setGender(formEditProfile.getGender());
        storedUserProfile.setCityOfResidenceFormatted(formEditProfile.getCityOfResidence());
        storedUserProfile.setHometownFormatted(formEditProfile.getHometown());
    }

    @When("^I click button Cancel in the form Change Private Data at the Settings Page$")
    public void iClickButtonCancelInTheFormChangePrivateDataAtTheSettingsPage() {
        formEditProfile.clickButtonCancel();
    }
}
