package com.example.seleniumqatest.tests;

import com.example.seleniumqatest.pages.LoginPage;
import com.example.seleniumqatest.constants.ErrorConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTest{
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

    @Test
    public void loginWithInValidEmail() {
        String email = "rotei.ru";
        String pass = settingsConfig.password();

        loginPage.login(email, pass);
        loginPage.assertMailErrorDisplayed(ErrorConstants.MAIL_ERROR);
    }

    @Test
    public void loginWithInValidPassword() {
        String email = settingsConfig.email();
        String pass = "tfsdf";

        loginPage.login(email, pass);
        loginPage.assertAuthErrorDisplayed(ErrorConstants.AUTH_ERROR);
    }

    @Test
    public void loginWithEmptyFields() {
        String email = "";
        String pass = "";

        loginPage.login(email, pass);
        loginPage.assertMailErrorDisplayed(ErrorConstants.MAIL_ERROR);
    }
}
