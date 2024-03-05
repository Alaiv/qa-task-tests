package com.example.seleniumqatest.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected static WebDriver driver;
    protected final int BASE_DURATION = 10;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }

    // basic asserts
    public void assertElementDisplayed(WebElement element) {
        Assertions.assertTrue(element.isDisplayed());
    }

    public void assertAllDisplayed(List<WebElement> element) {
        element.forEach(el -> Assertions.assertTrue(el.isDisplayed()));
    }

    public void assertElementText(WebElement element, String text) {;
        Assertions.assertEquals(element.getText(), text);
    }

    // basic waiters
    public WebElement waitAppear(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BASE_DURATION));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitAppear(By element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BASE_DURATION));
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }

    public WebElement waitClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BASE_DURATION));
        wait.until(ExpectedConditions.visibilityOf(element));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public List<WebElement> waitAllClickable(List<WebElement> elements) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(BASE_DURATION));
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }
}
