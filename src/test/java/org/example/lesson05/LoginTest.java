package org.example.lesson05;

import io.qameta.allure.*;
import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson06.pages.BasePage;
import org.example.lesson06.pages.LoginPage;
import org.example.lesson06.pages.MyAccountPage;
import org.example.lesson07.CustomJUnitTestWatcher;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Member login test")
    @Description("Verifying ability to login with valid member's credentials")
    @Link("https://some.link.com")
    @TmsLink("https://tms.link.com")
    @Issue("https://issue.link.com")
    @ExtendWith(CustomJUnitTestWatcher.class)
    @Severity(SeverityLevel.BLOCKER)
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
