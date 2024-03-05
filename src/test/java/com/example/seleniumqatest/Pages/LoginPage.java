package com.example.seleniumqatest.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    @FindBy(id = "loginEmail")
    private WebElement emailLocator;
    @FindBy(id = "loginPassword")
    private WebElement passwordLocator;
    @FindBy(id = "authButton")
    private WebElement authButtonLocator;
    @FindBy(id = "emailFormatError")
    private WebElement errorEmailMessage;
    @FindBy(id = "invalidEmailPassword")
    private WebElement errorAuthMessage;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public InputsPage login(String email, String pass) {
        sendEmail(email);
        sendPass(pass);
        clickAuthBtn();

        return new InputsPage(driver);
    }

    public void assertMailErrorDisplayed(String message) {
        WebElement el = waitForLoad(errorEmailMessage);
        assertElementText(el, message);
    }

    public void assertAuthErrorDisplayed(String message) {
        WebElement el = waitForLoad(errorAuthMessage);
        assertElementText(el, message);
    }

    private void sendEmail(String email) {
        waitClickable(emailLocator).sendKeys(email);
    }

    private void sendPass(String pass) {
        waitClickable(passwordLocator).sendKeys(pass);
    }

    private void clickAuthBtn() {
        waitClickable(authButtonLocator).click();
    }
}
