package org.example.lesson03;

import org.example.lesson03.enums.LocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DeleteAddressTest extends BaseTest {

    public static void main(String[] args) {
        initDriver();

        // Login
        simpleLogin();

        // Delete address
        clickElement(ACCOUNT_MENU_CSS, LocatorType.CSS);
        clickElement(ADDRESSES_OPTION_XPATH, LocatorType.XPATH);
        isTitleCorrect(ADDRESSES_PAGE_TITLE);
        // if list of addresses is empty then add a new address
        String testAddressAlias = "";
        List<WebElement> cards = getElements(ADDRESS_CARD_XPATH, LocatorType.XPATH);
        if (cards.isEmpty()) {
            testAddressAlias = addRandomAddress();
        } else {
            testAddressAlias = cards.get(ThreadLocalRandom.current().nextInt(0, cards.size())).getText();
        }

        clickElement(String.format(ADDRESS_CARD_DELETE_BUTTON_XPATH, testAddressAlias), LocatorType.XPATH);
        answerAlert(true);

        if(!isElementsDisplayed(String.format(ADDRESS_CARD_WITH_ALIAS_XPATH, testAddressAlias), LocatorType.XPATH)) {
            System.out.println("Verification Successful - Deleted address card is NOT displayed.");
        } else {
            System.out.println("Verification Successful - Deleted address card is still displayed.");
        }

        closeDriver();
    }
}
