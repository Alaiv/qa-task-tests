package com.example.seleniumqatest.Pages;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@RequiredArgsConstructor
public class BasePage {
    protected static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    // basic asserts
    public void assertElementText(WebElement el, String expectedText) {
        String actualText = el.getText();
        Assertions.assertEquals(expectedText, actualText);
    }

    public void assertElementDisplayed(WebElement el) {
        Assertions.assertTrue(el.isDisplayed());
    }

    public void assertElementNotDisplayed(WebElement el) {
        Assertions.assertTrue(el.isDisplayed());
    }

    // basic waiters
    public WebElement waitForLoad(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForUnLoad(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    public WebElement waitClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
}
