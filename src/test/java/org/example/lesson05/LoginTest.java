package org.example.lesson05;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() {
        openURL(ApplicationGlobalState.getInstance().getTargetUrl());
        checkPageTitle(INDEX_PAGE_TITLE);
        clickElement(LOGIN_MENU_CSS, LocatorType.CSS);
        checkPageTitle(LOGIN_PAGE_TITLE);
        setInputText(EMAIL_FIELD_ID, LocatorType.ID, ApplicationGlobalState.getInstance().getEmail());
        setInputText(PASSWORD_FIELD_ID, LocatorType.ID, ApplicationGlobalState.getInstance().getPassword());
        clickElement(SUBMIT_BUTTON_CSS, LocatorType.CSS);
        checkPageTitle(MY_ACCOUNT_PAGE_TITLE);
        checkElementVisibility(LOGIN_MENU_CSS, LocatorType.CSS, false);
        checkElementVisibility(LOGOUT_MENU_CSS, LocatorType.CSS, true);

        WebElement accountMenu = getElement(ACCOUNT_MENU_CSS, LocatorType.CSS);
        Assertions.assertTrue(accountMenu.isDisplayed(), "The account menu item is not displayed");
        String accountMenuText = accountMenu.getText();
        Assertions.assertEquals(accountMenuText, ApplicationGlobalState.getInstance().getUsername(), "The account menu item has incorrect " + accountMenuText + " text");
    }
}
