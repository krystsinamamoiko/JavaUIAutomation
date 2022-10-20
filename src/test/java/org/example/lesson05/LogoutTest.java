package org.example.lesson05;

import io.qameta.allure.*;
import org.example.lesson06.pages.LoginPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LogoutTest extends AuthorizedBaseTest {

    @Test
    @DisplayName("Member logout test")
    @Description("Verifying ability to logout")
    @Link("https://some.link.com")
    @TmsLink("https://tms.link.com")
    @Issue("https://issue.link.com")
    @Severity(SeverityLevel.CRITICAL)
    void logoutTest() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickLogoutMenuItem();
        Assertions.assertEquals(loginPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        Assertions.assertTrue(loginPage.isLoginMenuItemVisible(), "The login menu item is NOT displayed");
        Assertions.assertFalse(loginPage.isLogoutMenuItemVisible(), "The logout menu item is displayed");
        Assertions.assertFalse(loginPage.isAccountMenuItemVisible(), "The account menu item is displayed");
    }
}
