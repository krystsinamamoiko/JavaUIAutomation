package org.example.lesson05;

import org.apache.commons.lang.RandomStringUtils;
import org.example.lesson03.enums.LocatorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddAddressTest extends AuthorizedBaseTest {

    @Test
    void addNewAddressTest() {
        // navigate to Add a new address form
        clickElement(ACCOUNT_MENU_CSS, LocatorType.CSS);
        checkPageTitle(MY_ACCOUNT_PAGE_TITLE);
        clickElement(ADDRESSES_OPTION_XPATH, LocatorType.XPATH);
        checkPageTitle(ADDRESSES_PAGE_TITLE);
        clickElement(ADD_ADDRESS_BUTTON_XPATH, LocatorType.XPATH);
        checkPageTitle(ADDRESS_PAGE_TITLE);
        // fill in Add a new address form
        setInputText(ADDRESS1_FIELD_ID, LocatorType.ID, VALID_ADDRESS);
        setInputText(CITY_FIELD_ID, LocatorType.ID, VALID_CITY);
        clickElement(STATE_FIELD_ID, LocatorType.ID);
        selectRandomSelectBoxOption(STATE_FIELD_ID, LocatorType.ID);
        setInputText(POSTCODE_FIELD_ID, LocatorType.ID, VALID_POSTCODE);
        setInputText(PHONE_FIELD_ID, LocatorType.ID, VALID_PHONE);
        String randomAddressAlias = RandomStringUtils.random(7, true, false);
        setInputText(ALIAS_FIELD_ID, LocatorType.ID, randomAddressAlias);
        clickElement(SUBMIT_ADDRESS_BUTTON_CSS, LocatorType.CSS);
        checkPageTitle(ADDRESSES_PAGE_TITLE);

        Assertions.assertTrue(isElementsDisplayed(String.format(ADDRESS_CARD_WITH_ALIAS_XPATH, randomAddressAlias), LocatorType.XPATH), "A new address card is not displayed");
        Assertions.assertEquals(VALID_ADDRESS, getElementText(String.format(ADDRESS_CARD_ADDRESS1_XPATH, randomAddressAlias), LocatorType.XPATH), "Incorrect address value!");
        Assertions.assertEquals(VALID_PHONE, getElementText(String.format(ADDRESS_CARD_PHONE_XPATH, randomAddressAlias), LocatorType.XPATH), "Incorrect phone value!");
    }
}
