package com.example.seleniumqatest.pages;

import com.example.seleniumqatest.models.InputFormModel;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy(xpath = "//table[@id='dataTable']//th")
    private List<WebElement> tableColumns;

    @FindBy(xpath = "//table[@id='dataTable']//td")
    private List<WebElement> tableValues;

    public InputsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }


    public InputsPage closeModal() {
        waitClickable(modalBtn).click();
        waitAppear(By.xpath("//html[@class='uk-notouch']"));
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
                .orElseThrow(() -> new NotFoundException("Given value do not exist"));
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

    public InputsPage assertItemHaveGivenData(InputFormModel model) {
        List<String> actualValues = tableValues
                .stream()
                .map(el -> el.getText().trim().toLowerCase())
                .toList();

        Assertions.assertTrue(actualValues.contains(model.getEmail().toLowerCase()));
        Assertions.assertTrue(actualValues.contains(model.getName().toLowerCase()));
        Assertions.assertTrue(actualValues.contains(model.getGender().toString().toLowerCase()));
        Assertions.assertTrue(actualValues.contains(model.getCheckBoxValue1().toLowerCase()));
        Assertions.assertTrue(actualValues.contains(model.getRadioValue().toLowerCase()));

        return this;
    }
}
