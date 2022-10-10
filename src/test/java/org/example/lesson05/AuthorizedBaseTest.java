package org.example.lesson05;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson06.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

public class AuthorizedBaseTest extends BaseTest {

    @BeforeAll
    static void authorize() {
        openURL(ApplicationGlobalState.getInstance().getBaseUrl());
        LoginPage loginPage = new LoginPage(getDriver());
        if (loginPage.isLoginMenuItemVisible()) {
            loginPage.loginWithValidCredentials();
            Assertions.assertFalse(loginPage.isLoginMenuItemVisible(), "The login menu item is displayed after successful authorization!");
        }
    }
}
