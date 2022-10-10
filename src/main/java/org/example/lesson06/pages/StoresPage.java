package org.example.lesson06.pages;

import org.example.lesson03.config.ApplicationGlobalState;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class StoresPage extends BasePage {

    public StoresPage(WebDriver driver) {
        super(driver);
        setPageTitle("Stores - My Store");
        setPageURL(ApplicationGlobalState.getInstance().getBaseUrl() + "?controller=stores");
    }

    public boolean isGoogleMapLoaded() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();
        return (boolean) jsExecutor.executeScript(
            "try { \n" +
                " var result = (google && 'maps' in google) ? true : false;" +
                " return result;\n" +
                "} catch (e) {\n" +
                " return false;\n" +
                "}");
    }
}
