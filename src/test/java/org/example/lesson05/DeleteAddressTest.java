package org.example.lesson05;

import io.qameta.allure.*;
import org.example.lesson06.pages.AddressPage;
import org.example.lesson06.pages.AddressesPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class DeleteAddressTest extends AuthorizedBaseTest {

    @Test
    @DisplayName("Delete existing member's address")
    @Description("Verifying ability to delete existing member's addresses")
    @Link("https://some.link.com")
    @TmsLink("https://tms.link.com")
    @Issue("https://issue.link.com")
    @Severity(SeverityLevel.NORMAL)
    void deleteAddressTest() {
        AddressesPage addressesPage = new AddressesPage(getDriver());
        addressesPage.navigateToSelf();
        Assertions.assertEquals(addressesPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        // Delete address
        // if list of addresses is empty then add a new address
        String testAddressAlias = "";
        List<WebElement> cards = addressesPage.getAddressCardsList();
        if (cards.isEmpty()) {
            AddressPage addressPage = new AddressPage(getDriver());
            testAddressAlias = addressPage.addRandomAddress();
        } else {
            testAddressAlias = cards.get(ThreadLocalRandom.current().nextInt(0, cards.size())).getText().toLowerCase(Locale.ROOT);
        }

        addressesPage.clickDeleteAddressCardByAlias(testAddressAlias);
        addressesPage.answerAlert(true);
        Assertions.assertFalse(addressesPage.isAddressCardWithAliasVisible(testAddressAlias), "Deleted Address Card is visible!");
    }
}
