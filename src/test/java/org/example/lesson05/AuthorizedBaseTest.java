package org.example.lesson05;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.junit.jupiter.api.BeforeAll;

public class AuthorizedBaseTest extends BaseTest {

    @BeforeAll
    static void authorize() {
        openURL(ApplicationGlobalState.getInstance().getTargetUrl());
        if (isElementsDisplayed(LOGIN_MENU_CSS, LocatorType.CSS)) {
            clickElement(LOGIN_MENU_CSS, LocatorType.CSS);
            setInputText(EMAIL_FIELD_ID, LocatorType.ID, ApplicationGlobalState.getInstance().getEmail());
            setInputText(PASSWORD_FIELD_ID, LocatorType.ID, ApplicationGlobalState.getInstance().getPassword());
            clickElement(SUBMIT_BUTTON_CSS, LocatorType.CSS);
            checkElementVisibility(LOGIN_MENU_CSS, LocatorType.CSS, false);
        }
    }
}
