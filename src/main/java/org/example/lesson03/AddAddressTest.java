package org.example.lesson03;

import org.apache.commons.lang.RandomStringUtils;
import org.example.lesson03.enums.LocatorType;

public class AddAddressTest extends BaseTest {

    public static void main(String[] args) {
        initDriver();

        // Login
        simpleLogin();

        // Add a new address
        clickElement(ACCOUNT_MENU_CSS, LocatorType.CSS);
        isTitleCorrect(MY_ACCOUNT_PAGE_TITLE);
        clickElement(ADDRESSES_OPTION_XPATH, LocatorType.XPATH);
        isTitleCorrect(ADDRESSES_PAGE_TITLE);
        clickElement(ADD_ADDRESS_BUTTON_XPATH, LocatorType.XPATH);
        isTitleCorrect(ADDRESS_PAGE_TITLE);
        setInputText(ADDRESS1_FIELD_ID, LocatorType.ID, VALID_ADDRESS);
        setInputText(CITY_FIELD_ID, LocatorType.ID, VALID_CITY);
        clickElement(STATE_FIELD_ID, LocatorType.ID);
        selectRandomSelectBoxOption(STATE_FIELD_ID, LocatorType.ID);
        setInputText(POSTCODE_FIELD_ID, LocatorType.ID, VALID_POSTCODE);
        setInputText(PHONE_FIELD_ID, LocatorType.ID, VALID_PHONE);

        String randomAddressAlias = RandomStringUtils.random(7, true, false);
        setInputText(ALIAS_FIELD_ID, LocatorType.ID, randomAddressAlias);

        clickElement(SUBMIT_ADDRESS_BUTTON_CSS, LocatorType.CSS);
        isTitleCorrect(ADDRESSES_PAGE_TITLE);

        if(isElementsDisplayed(String.format(ADDRESS_CARD_WITH_ALIAS_XPATH, randomAddressAlias), LocatorType.XPATH)) {
            System.out.println("Verification Successful - A new address card is displayed.");
        } else {
            System.out.println("Verification Successful - A new address card is not displayed.");
        }

        isElementTextCorrect(String.format(ADDRESS_CARD_ADDRESS1_XPATH, randomAddressAlias), LocatorType.XPATH, VALID_ADDRESS);
        isElementTextCorrect(String.format(ADDRESS_CARD_PHONE_XPATH, randomAddressAlias), LocatorType.XPATH, VALID_PHONE);

        closeDriver();
    }
}
