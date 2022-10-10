package org.example.lesson05;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson06.pages.ProductsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;

public class QuickViewVisibilityTest extends BaseTest {

    @Test
    public void quickViewVisibilityOnHoverTest() throws InterruptedException {
        openURL(ApplicationGlobalState.getInstance().getBaseUrl());
        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.clickSummerDressMenuItem();
        Assertions.assertEquals(productsPage.getPageTitle(), getCurrentPageTitle(), "Page title is incorrect!");
        int order = ThreadLocalRandom.current().nextInt(1, productsPage.getProductNumber() + 1);
        productsPage.hoverProduct(order);
        Assertions.assertTrue(productsPage.isQuickViewButtonVisible(order));
    }
}
