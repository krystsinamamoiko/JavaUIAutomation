package org.example.lesson05;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

public class GoogleMapTest extends BaseTest {

    private static String STORIES_MENU_TITLE = "Our stores";

    @Test
    void googleMapLoadedTest() {
        openURL(ApplicationGlobalState.getInstance().getTargetUrl());
        checkElementVisibility(String.format(FOOTER_MENU_ITEM_XPATH, STORIES_MENU_TITLE), LocatorType.XPATH, true);
        clickElement(String.format(FOOTER_MENU_ITEM_XPATH, STORIES_MENU_TITLE), LocatorType.XPATH);
        checkPageTitle(STORIES_PAGE_TITLE);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        Boolean isGoogleLoaded = (boolean) jsExecutor.executeScript(
            "try { \n" +
            " var result = (google && 'maps' in google) ? true : false;" +
            " return result;\n" +
            "} catch (e) {\n" +
            " return false;\n" +
            "}");
        Assertions.assertTrue(isGoogleLoaded, "There is no Google map on the Our stories page");
    }
}
