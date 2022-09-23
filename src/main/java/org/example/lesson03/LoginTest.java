package org.example.lesson03;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    public static void main(String[] args) {
        initDriver();

        openURL(ApplicationGlobalState.getInstance().getTargetUrl());
        isTitleCorrect(INDEX_PAGE_TITLE);
        clickElement(LOGIN_MENU_CSS, LocatorType.CSS);
        isTitleCorrect(LOGIN_PAGE_TITLE);
        setInputText(EMAIL_FIELD_ID, LocatorType.ID, ApplicationGlobalState.getInstance().getEmail());
        setInputText(PASSWORD_FIELD_ID, LocatorType.ID, ApplicationGlobalState.getInstance().getPassword());
        clickElement(SUBMIT_BUTTON_CSS, LocatorType.CSS);
        isTitleCorrect(MY_ACCOUNT_PAGE_TITLE);

        if(!isElementsDisplayed(LOGIN_MENU_CSS, LocatorType.CSS)) {
            System.out.println("Verification Successful - The login menu item is NOT presented.");
        } else {
            System.out.println("Verification Successful - The logout menu item is presented.");
        }

        if(isElementsDisplayed(LOGOUT_MENU_CSS, LocatorType.CSS)) {
            System.out.println("Verification Successful - The logout menu item is presented.");
        } else {
            System.out.println("Verification Successful - The logout menu item is not presented.");
        }

        WebElement accountMenu = getElement(ACCOUNT_MENU_CSS, LocatorType.CSS);
        if(accountMenu.isDisplayed()) {
            System.out.println("Verification Successful - The account menu item is displayed.");
        } else {
            System.out.println("Verification Successful - The account menu item is not displayed.");
        }

        String accountMenuText = accountMenu.getText();
        if(accountMenuText.equals(ApplicationGlobalState.getInstance().getUsername())) {
            System.out.println("Verification Successful - The account menu item has correct '" +  accountMenuText + "' text");
        } else {
            System.out.println("Verification Successful - The account menu item has incorrect '" + accountMenuText + "'text.");
        }

        closeDriver();
    }
}
