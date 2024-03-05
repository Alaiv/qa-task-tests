package com.example.seleniumqatest.tests;

import com.example.seleniumqatest.constants.ErrorConstants;
import com.example.seleniumqatest.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LoginTests extends BaseTest {
    private LoginPage loginPage;

    @BeforeEach
    public void init() {
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginWithValidDataTest() {
        String email = settingsConfig.email();
        String pass = settingsConfig.password();

        loginPage
                .login(email, pass)
                .assertInputFormVisible();
    }

    @ParameterizedTest
    @MethodSource("invalidLoginData")
    public void loginWithInValidEmail(String email, String pass, String error) {
        loginPage.login(email, pass);
        loginPage.assertErrorDisplayed(error);
    }

    private Stream<Arguments> invalidLoginData() {
        return Stream.of(
                Arguments.of("rotei.ru", settingsConfig.password(), ErrorConstants.MAIL_ERROR),
                Arguments.of(settingsConfig.email(), "qwerty", ErrorConstants.AUTH_ERROR),
                Arguments.of("", "", ErrorConstants.MAIL_ERROR)
        );
    }
}
