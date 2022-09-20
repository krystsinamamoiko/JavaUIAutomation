package org.example.lesson03;

import org.example.lesson03.enums.LocatorType;

public class LogoutTest extends BaseTest {

    public static void main(String[] args) {
        initDriver();

        // Login
        simpleLogin();

        // Logout test
        clickElement(LOGOUT_MENU_CSS, LocatorType.CSS);
        isTitleCorrect(LOGIN_PAGE_TITLE);
        if(isElementsDisplayed(LOGIN_MENU_CSS, LocatorType.CSS)) {
            System.out.println("Verification Successful - The login menu item is displayed.");
        } else {
            System.out.println("Verification Successful - The login menu item is not displayed.");
        }

        if(!isElementsDisplayed(LOGOUT_MENU_CSS, LocatorType.CSS)) {
            System.out.println("Verification Successful - The logout menu item is NOT displayed.");
        } else {
            System.out.println("Verification Successful - The logout menu item is displayed.");
        }

        if(!isElementsDisplayed(ACCOUNT_MENU_CSS, LocatorType.CSS)) {
            System.out.println("Verification Successful - The account menu item is NOT displayed.");
        } else {
            System.out.println("Verification Successful - The account menu item is displayed.");
        }

        closeDriver();
    }
}
