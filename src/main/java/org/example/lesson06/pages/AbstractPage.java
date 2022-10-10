package org.example.lesson06.pages;

import org.example.lesson03.enums.LocatorType;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.ThreadLocalRandom;

public class AbstractPage {

    private WebDriver driver;
    private String pageURL;
    private String pageTitle;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Checks if a web element is displayed.
     *
     * @param element - WebElement to check.
     * @return - True if the element is displayed.
     */
    public boolean isElementDisplayed(WebElement element) {
        boolean result = false;
        try {
            result = element.isDisplayed();
        } catch (Throwable exception) {
            result = false;
        }
        return result;
    }

    /**
     * Get title of the current page.
     * @return title of the current page.
     */
    public String getPageTitle() {
        return this.pageTitle;
    }

    /**
     * Get title of the current page.
     * @return title of the current page.
     */
    public void navigateToSelf() {
        driver.get(pageURL);
    }

    public void setPageTitle(String pageTitle) {
        this.pageTitle = pageTitle;
    }

    public void setPageURL(String pageURL) {
        this.pageURL = pageURL;
    }

    /**
     * Select random option for selectbox with the given locator value and locator type.
     * @param selector is the given select element.
     */
    public void selectRandomSelectBoxOption(WebElement selector) {
        Select selectBox = new Select(selector);
        selectBox.selectByIndex(ThreadLocalRandom.current().nextInt(0, selectBox.getOptions().size()));
    }

    /**
     * Get text of the web element with the given locator and locator type.
     * @param element is the given webElement.
     * @return text value.
     */
    public String getElementText(WebElement element) {
        return element.getText();
    }

    /**
     * Set the given text into text field with the given locator value and locator type.
     * @param element is the given webElement.
     * @param text is the given text.
     */
    public void setInputText(WebElement element, String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
    }

    public void answerAlert(boolean answer) {
        Alert alert = driver.switchTo().alert();
        if (answer) {
            alert.accept(); // for OK
        } else {
            alert.dismiss(); // for Cancel
        }
    }

    /**
     * Checks that a dynamic webelement is or is not displayed
     *
     * @param dynamicWebElement - DynamicWebElement to check
     * @param replaceablePart - Replaceable part of the locator
     * @return - True if the element is displayed.
     */
    public boolean isDynamicElementDisplayed(DynamicWebElement dynamicWebElement, String replaceablePart) {
        boolean result = false;
        try {
            WebElement element = dynamicWebElement.parseDynamicLocator(replaceablePart, driver, LocatorType.XPATH);
            result = isElementDisplayed(element);
        } catch (Throwable exception) {
            result = false;
        }
        return result;
    }
}
