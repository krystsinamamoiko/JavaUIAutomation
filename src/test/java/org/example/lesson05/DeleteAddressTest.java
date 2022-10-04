package org.example.lesson05;

import org.apache.commons.lang.RandomStringUtils;
import org.example.lesson03.enums.LocatorType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class DeleteAddressTest extends AuthorizedBaseTest {

    @Test
    void deleteAddressTest() {
        // Delete address
        clickElement(ACCOUNT_MENU_CSS, LocatorType.CSS);
        clickElement(ADDRESSES_OPTION_XPATH, LocatorType.XPATH);
        checkPageTitle(ADDRESSES_PAGE_TITLE);
        // if list of addresses is empty then add a new address
        String testAddressAlias = "";
        List<WebElement> cards = getElements(ADDRESS_CARD_XPATH, LocatorType.XPATH);
        if (cards.isEmpty()) {
            testAddressAlias = addRandomAddress();
        } else {
            testAddressAlias = cards.get(ThreadLocalRandom.current().nextInt(0, cards.size())).getText().toLowerCase(Locale.ROOT);
        }

        clickElement(String.format(ADDRESS_CARD_DELETE_BUTTON_XPATH, testAddressAlias), LocatorType.XPATH);
        answerAlert(true);

        checkElementVisibility(String.format(ADDRESS_CARD_WITH_ALIAS_XPATH, testAddressAlias), LocatorType.XPATH, false);
    }

    private String addRandomAddress() {
        clickElement(ACCOUNT_MENU_CSS, LocatorType.CSS);
        clickElement(ADDRESSES_OPTION_XPATH, LocatorType.XPATH);
        clickElement(ADD_ADDRESS_BUTTON_XPATH, LocatorType.XPATH);
        setInputText(ADDRESS1_FIELD_ID, LocatorType.ID, VALID_ADDRESS);
        setInputText(CITY_FIELD_ID, LocatorType.ID, VALID_CITY);
        clickElement(STATE_FIELD_ID, LocatorType.ID);
        selectRandomSelectBoxOption(STATE_FIELD_ID, LocatorType.ID);
        setInputText(POSTCODE_FIELD_ID, LocatorType.ID, VALID_POSTCODE);
        setInputText(PHONE_FIELD_ID, LocatorType.ID, VALID_PHONE);
        String randomAddressAlias = RandomStringUtils.random(7, true, false);
        setInputText(ALIAS_FIELD_ID, LocatorType.ID, randomAddressAlias);
        clickElement(SUBMIT_ADDRESS_BUTTON_CSS, LocatorType.CSS);

        return randomAddressAlias;
    }
}
