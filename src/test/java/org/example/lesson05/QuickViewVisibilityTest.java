package org.example.lesson05;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class QuickViewVisibilityTest extends BaseTest {

    @Test
    public void quickViewVisibilityOnHoverTest() throws InterruptedException {
        openURL(ApplicationGlobalState.getInstance().getTargetUrl());
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getElement(DRESSES_MENU_XPATH, LocatorType.XPATH)).
            click(getElement(SUMMER_DRESSES_SUBMENU_XPATH, LocatorType.XPATH)).
            build().
            perform();
        checkPageTitle(SUMMER_DRESSES_PAGE_TITLE);
        List<WebElement> products = getElements(PRODUCT_CONTAINER_CLASS_NAME, LocatorType.CLASS_NAME);
        int number = ThreadLocalRandom.current().nextInt(1, products.size() + 1);
        actions.moveToElement(getElement(String.format(CERTAIN_PRODUCT_CONTAINER_XPATH, number), LocatorType.XPATH)).
            perform();
        checkElementVisibility(String.format(QUICK_VIEW_BUTTON_XPATH, number), LocatorType.XPATH, true);
    }
}
