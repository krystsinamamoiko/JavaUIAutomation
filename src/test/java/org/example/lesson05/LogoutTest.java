package org.example.lesson05;

import org.example.lesson03.enums.LocatorType;
import org.junit.jupiter.api.Test;

public class LogoutTest extends AuthorizedBaseTest {

    @Test
    void logoutTest() {
        clickElement(LOGOUT_MENU_CSS, LocatorType.CSS);
        checkPageTitle(LOGIN_PAGE_TITLE);
        checkElementVisibility(LOGIN_MENU_CSS, LocatorType.CSS, true);
        checkElementVisibility(LOGOUT_MENU_CSS, LocatorType.CSS, false);
        checkElementVisibility(ACCOUNT_MENU_CSS, LocatorType.CSS, false);
    }
}
