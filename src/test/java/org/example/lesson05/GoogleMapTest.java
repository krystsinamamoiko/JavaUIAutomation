package org.example.lesson05;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson06.pages.StoresPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GoogleMapTest extends BaseTest {

    @Test
    void googleMapLoadedTest() {
        openURL(ApplicationGlobalState.getInstance().getBaseUrl());
        StoresPage storesPage = new StoresPage(getDriver());
        Assertions.assertTrue(storesPage.isStoresFooterMenuItemVisible(), "The Stores footer menu item is NOT displayed");
        storesPage.clickStoresFooterMenuItem();
        Assertions.assertEquals(storesPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        Assertions.assertTrue(storesPage.isGoogleMapLoaded(), "There is no Google map on the Our stories page");
    }
}
