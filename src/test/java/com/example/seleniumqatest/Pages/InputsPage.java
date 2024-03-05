package com.example.seleniumqatest.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InputsPage extends BasePage {

    @FindBy(id = "inputsPage")
    private WebElement inputsPageContainer;
    public InputsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void assertInputFormVisible() {
        assertElementDisplayed(inputsPageContainer);
    }
}
