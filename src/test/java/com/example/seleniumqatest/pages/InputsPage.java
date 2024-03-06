package com.example.seleniumqatest.pages;

import com.example.seleniumqatest.constants.ErrorConstants;
import com.example.seleniumqatest.constants.InputFormConstants;
import com.example.seleniumqatest.models.InputFormModel;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.StringJoiner;

public class InputsPage extends BasePage {

    @FindBy(id = "inputsPage")
    private WebElement inputsPageContainer;

    @FindBy(id = "dataEmail")
    private WebElement email;

    @FindBy(id = "dataName")
    private WebElement name;

    @FindBy(xpath = "//input[contains(@id, 'dataCheck')]/ancestor::div[@class='uk-form-row']")
    private List<WebElement> checkboxes;

    @FindBy(xpath = "//input[@name='radioGroup']/ancestor::div[@class='uk-form-row']")
    private List<WebElement> radioButtons;

    @FindBy(id = "dataSend")
    private WebElement submitBtn;

    @FindBy(xpath = "//div[@class='uk-margin uk-modal-content']")
    private WebElement modal;

    @FindBy(xpath = "//button[contains(@class, 'uk-modal-close')]")
    private WebElement modalBtn;

    @FindBy(xpath = "//table[@id='dataTable']//td")
    private List<WebElement> tableValues;

    @FindBy(id = "emailFormatError")
    private WebElement errorEmailMessage;

    @FindBy(id = "blankNameError")
    private WebElement errorBlankNameMessage;

    public InputsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public InputsPage closeModal() {
        By noModalHtmlTag = By.xpath("//html[@class='uk-notouch']");

        waitClickable(modalBtn).click();
        waitAppear(noModalHtmlTag);
        return this;
    }

    public InputsPage sendEmail(String email) {
        waitClickable(this.email).sendKeys(email);
        return this;
    }

    public InputsPage sendName(String name) {
        waitClickable(this.name).sendKeys(name);
        return this;
    }

    public InputsPage selectGender(String gender) {
        String xpath = String.format("//select/option[text()='%s']", gender);

        driver.findElement(By.xpath(xpath))
                .click();
        return this;
    }

    public InputsPage selectCheckbox(String itemValue) {
        WebElement webElement = findItem(checkboxes, itemValue);
        webElement.findElement(By.tagName("input")).click();
        return this;
    }

    public InputsPage selectRadio(String itemValue) {
        WebElement webElement = findItem(radioButtons, itemValue);
        webElement.findElement(By.tagName("input")).click();
        return this;
    }

    public InputsPage submitForm() {
        waitClickable(submitBtn).click();

        return this;
    }

    private WebElement findItem(List<WebElement> elements, String itemValue) {
        String basePrefix = "Вариант ";

        return waitAllClickable(elements)
                .stream()
                .filter(f -> f.getText().trim().equalsIgnoreCase(basePrefix + itemValue))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Given value do not exist: " + itemValue));
    }

    // assertions
    public InputsPage assertInputFormVisible() {
        assertElementDisplayed(inputsPageContainer);
        return this;
    }

    public InputsPage assertModalIsVisible() {
        waitAppear(modal);
        assertElementDisplayed(modal);

        return this;
    }

    public InputsPage assertModalNotVisible() {
        boolean isVisible;

        try {
            isVisible = modal.isDisplayed();
        } catch (NoSuchElementException ignored) {
            isVisible = false;
        }

        Assertions.assertFalse(isVisible);

        return this;
    }

    public InputsPage assertModalHaveText(String text) {
        assertElementText(modal, text);

        return this;
    }

    public InputsPage assertThatItemAdded() {
        waitAllClickable(tableValues);
        assertAllDisplayed(tableValues);

        return this;
    }

    public void assertErrorDisplayed(String error) {
        WebElement el = error.equals(ErrorConstants.MAIL_ERROR)
                ? waitAppear(errorEmailMessage)
                : waitAppear(errorBlankNameMessage);

        assertElementText(el, error);
    }

    public InputsPage assertItemHaveGivenData(InputFormModel model) {
        List<String> actualValues = tableValues
                .stream()
                .map(el -> el.getText().trim().toLowerCase())
                .toList();

        Assertions.assertTrue(actualValues.contains(model.getEmail().toLowerCase()));
        Assertions.assertTrue(actualValues.contains(model.getName().toLowerCase()));
        Assertions.assertTrue(actualValues.contains(model.getGender().toString().toLowerCase()));
        Assertions.assertTrue(actualValues.contains(getRadioValue(model)));
        Assertions.assertTrue(actualValues.contains(getCheckboxValue(model)));

        return this;
    }

    private String getCheckboxValue(InputFormModel model) {
        StringJoiner joiner = new StringJoiner(", ");

        if (model.getCheckBoxValue1() != null) {
            joiner.add(model.getCheckBoxValue1());
        }

        if (model.getCheckBoxValue2() != null) {
            joiner.add(model.getCheckBoxValue2());
        }

        return joiner.toString().isEmpty()
                ? InputFormConstants.defaultCheckBoxValue.toLowerCase()
                : joiner.toString();
    }

    private String getRadioValue(InputFormModel model) {
        return model.getRadioValue() == null ? "" : model.getRadioValue().toLowerCase();
    }
}
