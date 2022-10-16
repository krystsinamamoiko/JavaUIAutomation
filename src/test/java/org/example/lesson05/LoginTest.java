package org.example.lesson05;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson06.pages.BasePage;
import org.example.lesson06.pages.LoginPage;
import org.example.lesson06.pages.MyAccountPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        openURL(ApplicationGlobalState.getInstance().getBaseUrl());
        BasePage basePage = new BasePage(getDriver());
        Assertions.assertEquals(basePage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        basePage.clickLoginMenuItem();
        LoginPage loginPage = new LoginPage(getDriver());
        Assertions.assertEquals(loginPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        loginPage.setValidEmail();
        loginPage.setValidPassword();
        loginPage.clickSubmitButton();
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        Assertions.assertEquals(myAccountPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        Assertions.assertFalse(myAccountPage.isLoginMenuItemVisible(), "The login menu item is displayed");
        Assertions.assertTrue(myAccountPage.isLogoutMenuItemVisible(), "The logout menu item is NOT displayed");
        Assertions.assertTrue(myAccountPage.isAccountMenuItemVisible(), "The account menu item is NOT displayed");
        Assertions.assertEquals(myAccountPage.getAccountMenuText(), ApplicationGlobalState.getInstance().getUsername(), "The account menu item has incorrect text");
    }
}
