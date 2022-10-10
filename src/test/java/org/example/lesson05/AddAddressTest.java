package org.example.lesson05;

import org.apache.commons.lang.RandomStringUtils;
import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson06.pages.AddressPage;
import org.example.lesson06.pages.AddressesPage;
import org.example.lesson06.pages.MyAccountPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddAddressTest extends AuthorizedBaseTest {

    @Test
    void addNewAddressTest() {
        // navigate to Add a new address form
        MyAccountPage myAccountPage = new MyAccountPage(getDriver());
        myAccountPage.navigateToSelf();
        Assertions.assertEquals(myAccountPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        myAccountPage.clickAddressesOption();
        AddressesPage addressesPage = new AddressesPage(getDriver());
        Assertions.assertEquals(addressesPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        addressesPage.clickAddAddressButton();
        AddressPage addressPage = new AddressPage(getDriver());
        Assertions.assertEquals(addressPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        // fill in Add a new address form
        addressPage.setAddress1Field(ApplicationGlobalState.getInstance().getValidAddress());
        addressPage.setCityField(ApplicationGlobalState.getInstance().getValidCity());
        addressPage.selectRandomState();
        addressPage.setPostcodeField(ApplicationGlobalState.getInstance().getValidPostcode());
        addressPage.setPhoneField(ApplicationGlobalState.getInstance().getValidPhone());
        String randomAddressAlias = RandomStringUtils.random(7, true, false);
        addressPage.setAliasField(randomAddressAlias);
        addressPage.clickSubmitAddressButton();
        Assertions.assertEquals(addressesPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");

        Assertions.assertTrue(addressesPage.isAddressCardWithAliasVisible(randomAddressAlias), "A new address card is not displayed");
        Assertions.assertEquals(ApplicationGlobalState.getInstance().getValidAddress(), addressesPage.getAddressCardAddressByAlias(randomAddressAlias), "Incorrect address value!");
        Assertions.assertEquals(ApplicationGlobalState.getInstance().getValidPhone(), addressesPage.getAddressCardPhoneByAlias(randomAddressAlias), "Incorrect phone value!");
    }
}
