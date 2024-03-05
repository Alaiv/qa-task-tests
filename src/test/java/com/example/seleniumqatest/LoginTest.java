package com.example.seleniumqatest;

import com.example.seleniumqatest.Pages.LoginPage;
import com.example.seleniumqatest.contants.ErrorConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest{
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
}
