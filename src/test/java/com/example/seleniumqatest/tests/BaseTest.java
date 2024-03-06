package com.example.seleniumqatest.tests;

import com.example.seleniumqatest.models.SettingsConfig;
import com.example.seleniumqatest.pages.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
    protected String BASE_URL;
    protected WebDriver driver;
    protected SettingsConfig settingsConfig;


    @BeforeAll
    public void setUp() {
        settingsConfig = ConfigFactory.create(SettingsConfig.class);
        BASE_URL = Objects.requireNonNull(getClass().getResource(settingsConfig.baseUrl())).toString();
    }

    @BeforeEach
    public void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        BasePage.setDriver(driver);
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
