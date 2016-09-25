package ru.ok.rmatafonov.pageobjects.forms.pagesettings;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.ok.rmatafonov.pageobjects.BasePageObject;

import java.util.List;
import java.util.Random;

@Component
@Lazy
public class FormEditProfile extends BasePageObject {

    private static final String SYMBOLS_SET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final String XPATH_CITY_OF_RESIDENCE_SUGGEST_LIST = ".//*[@id='citySugg_SuggestItems']/li";
    private static final String XPATH_HOMETOWN_SUGGEST_LIST = ".//*[@id='cityBSugg_SuggestItems']/li";

    private static final String XPATH_TEMPLATE_ERROR_MESSAGE = ".//*[@id='hook_Form_PopLayerEditUserProfileNewForm']//*[text()='%s']";

    private Logger logger = LoggerFactory.getLogger(FormEditProfile.class);

    @FindBy(xpath = ".//*[@id='field_name']")
    private WebElement fieldName;

    @FindBy(xpath = ".//*[@id='field_surname']")
    private WebElement fieldSurname;

    @FindBy(xpath = ".//*[@id='field_bday']")
    private WebElement fieldBirthdayDay;

    @FindBy(xpath = ".//*[@id='field_bmonth']")
    private WebElement fieldBirthdayMonth;

    @FindBy(xpath = ".//*[@id='field_byear']")
    private WebElement fieldBirthdayYear;

    @FindBy(xpath = ".//*[@id='field_gender_1']")
    private WebElement fieldGenderMale;

    @FindBy(xpath = ".//*[@id='field_gender_2']")
    private WebElement fieldGenderFemale;

    @FindBy(xpath = ".//*[@id='field_citySugg_SearchInput']")
    private WebElement fieldCityOfResidence;

    @FindBy(xpath = ".//*[@id='field_cityBSugg_SearchInput']")
    private WebElement fieldHometown;

    @FindBy(xpath = ".//*[@id='hook_FormButton_button_savePopLayerEditUserProfileNew']")
    private WebElement buttonSave;

    @FindBy(xpath = ".//*[@id='button_cancelPopLayerEditUserProfileNew']")
    private WebElement buttonCancel;

    @FindBy(xpath = ".//*[@id='nohook_modal_close']")
    private WebElement buttonClose;

    public String inputRandomName(int length) {
        String name = generateRandomString(length);
        logger.info("Generated name: {}", name);
        inputName(name);
        return name;
    }

    public void inputName(String name) {
        clearAndSendKeysTo(fieldName, name);
    }

    public String inputRandomSurname(int length) {
        String surname = generateRandomString(length);
        logger.info("Generated surname: {}", surname);
        inputSurname(surname);
        return surname;
    }

    public void inputSurname(String surname) {
        clearAndSendKeysTo(fieldSurname, surname);
    }

    private String generateRandomString(int length) {
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(SYMBOLS_SET.charAt(rnd.nextInt(SYMBOLS_SET.length())));
        return sb.toString();
    }

    private String setNextOptionInSelectElement(WebElement element, int maxIndex) {
        List<WebElement> possibleElements = getSelectElementOptions(element);
        WebElement selectedElement = getSelectElementSelectedOption(element);
        int indexOfNext = possibleElements.indexOf(selectedElement) + 1;
        if (indexOfNext >= possibleElements.size() || indexOfNext > maxIndex) {
            indexOfNext = 1;
        }
        setSelectElementOptionByIndex(element, indexOfNext);
        return getElementText(possibleElements.get(indexOfNext));
    }

    public String selectNextBirthdayDay() {
        return setNextOptionInSelectElement(fieldBirthdayDay, 28);
    }

    public String selectNextBirthdayMonth() {
        return setNextOptionInSelectElement(fieldBirthdayMonth, Integer.MAX_VALUE);
    }

    public String selectNextBirthdayYear() {
        return setNextOptionInSelectElement(fieldBirthdayYear, Integer.MAX_VALUE);
    }

    public String changeGender() {
        if (isElementSelected(fieldGenderMale)) {
            clickElement(fieldGenderFemale);
            return "female";
        }

        clickElement(fieldGenderMale);
        return "male";
    }

    private String selectItemFromSuggestList(WebElement field, String xpathSuggestList, String valueToSearch) {
        String symbol = generateRandomString(1);
        if (valueToSearch != null) {
            symbol = valueToSearch;
        }

        clearAndSendKeysTo(field, symbol);

        List<WebElement> suggestList = getElements(xpathSuggestList);

        int itemToSelect = 0;
        if (suggestList.size() > 1) {
            itemToSelect = new Random().nextInt(suggestList.size() - 1);
        }
        WebElement randomSuggestListItem = suggestList.get(itemToSelect);

        String newValue = getElementText(randomSuggestListItem);

        clickElement(randomSuggestListItem);

        return newValue;
    }

    public String selectRandomCityOfResidence() {
        return selectItemFromSuggestList(fieldCityOfResidence, XPATH_CITY_OF_RESIDENCE_SUGGEST_LIST, null);
    }

    public String selectCityOfResidence(String value) {
        return selectItemFromSuggestList(fieldCityOfResidence, XPATH_CITY_OF_RESIDENCE_SUGGEST_LIST, value);
    }

    public String getCityOfResidence() {
        return getJsPropertyOfElement(fieldCityOfResidence, "value");
    }

    public String selectRandomHometown() {
        return selectItemFromSuggestList(fieldHometown, XPATH_HOMETOWN_SUGGEST_LIST, null);
    }

    public String selectHometown(String value) {
        return selectItemFromSuggestList(fieldCityOfResidence, XPATH_CITY_OF_RESIDENCE_SUGGEST_LIST, value);
    }

    public String getHometown() {
        return getJsPropertyOfElement(fieldHometown, "value");
    }

    public void clickButtonSave() {
        clickElement(buttonSave);
    }

    public void clickButtonCancel() {
        clickElement(buttonCancel);
    }

    public void closeForm() {
        clickElement(buttonClose);
    }

    public void inputCityOfResidence(String value) {
        clearAndSendKeysTo(fieldCityOfResidence, value);
    }

    public void inputHometown(String value) {
        clearAndSendKeysTo(fieldHometown, value);
    }

    public boolean isErrorMessageDisplayed(String message) {
        return isElementPresent(By.xpath(String.format(XPATH_TEMPLATE_ERROR_MESSAGE, message)));
    }

    public String getName() {
        return getJsPropertyOfElement(fieldName, "value");
    }

    public String getSurname() {
        return getJsPropertyOfElement(fieldSurname, "value");
    }

    public String getDayOfBirth() {
        return getElementText(getSelectElementSelectedOption(fieldBirthdayDay));
    }

    public String getMonthOfBirth() {
        return getElementText(getSelectElementSelectedOption(fieldBirthdayMonth));
    }

    public String getYearOfBirth() {
        return getElementText(getSelectElementSelectedOption(fieldBirthdayYear));
    }

    public String getGender() {
        if (isElementSelected(fieldGenderMale)) {
            return "male";
        }
        return "female";
    }
}
