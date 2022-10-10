package org.example.lesson06.pages;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DynamicWebElement {

    private String locatorValue;

    public DynamicWebElement(String locatorValue) {
        this.locatorValue = locatorValue;
    }

    /**
     * This will return the WebElement object using the dynamic locator.
     */
    public WebElement parseDynamicLocator(String dynamicPortion, WebDriver driver, LocatorType locatorType) {
        WebElement element = null;
        switch (locatorType) {
            case XPATH:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(locatorValue, dynamicPortion))));
                element = driver.findElement(By.xpath(String.format(locatorValue, dynamicPortion)));
                break;
            case ID:new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.id(String.format(locatorValue, dynamicPortion))));
                element = driver.findElement(By.id(String.format(locatorValue, dynamicPortion)));
                break;
            case CSS:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(String.format(locatorValue, dynamicPortion))));
                element = driver.findElement(By.xpath(String.format(locatorValue, dynamicPortion)));
                break;
            case CLASS_NAME:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.className(String.format(locatorValue, dynamicPortion))));
                element = driver.findElement(By.className(String.format(locatorValue, dynamicPortion)));
                break;
            case TAG_NAME:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.tagName(String.format(locatorValue, dynamicPortion))));
                element = driver.findElement(By.tagName(String.format(locatorValue, dynamicPortion)));
                break;
        }
        return element;
    }

    /**
     * This will return the list of WebElement objects using the dynamic locator.
     */
    public List<WebElement> parseDynamicListLocator(String dynamicPortion, WebDriver driver, LocatorType locatorType) {
        List<WebElement> elements = null;
        switch (locatorType) {
            case XPATH:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(locatorValue, dynamicPortion))));
                elements = driver.findElements(By.xpath(String.format(locatorValue, dynamicPortion)));
                break;
            case ID:new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.id(String.format(locatorValue, dynamicPortion))));
                elements = driver.findElements(By.id(String.format(locatorValue, dynamicPortion)));
                break;
            case CSS:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(String.format(locatorValue, dynamicPortion))));
                elements = driver.findElements(By.xpath(String.format(locatorValue, dynamicPortion)));
                break;
            case CLASS_NAME:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.className(String.format(locatorValue, dynamicPortion))));
                elements = driver.findElements(By.className(String.format(locatorValue, dynamicPortion)));
                break;
            case TAG_NAME:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.tagName(String.format(locatorValue, dynamicPortion))));
                elements = driver.findElements(By.tagName(String.format(locatorValue, dynamicPortion)));
                break;
        }
        return elements;
    }

    /**
     * This will return the [WebElement] object using the dynamic locator.
     */
    public WebElement parseMultiDynamicLocator(List<String> dynamicPortions, WebDriver driver) {
        String[] locatorArray = locatorValue.split("%s");
        var formattedLocator = "";
        dynamicPortions.add("");
        for (int i = 0; i < locatorArray.length; i++) {
            formattedLocator += locatorArray[i] + dynamicPortions.get(i);
        }
        return driver.findElement(By.xpath(formattedLocator));
    }

    /**
     * This will return a List of [WebElement] object using the [dynamicPortions].
     */
    public List<WebElement> parseMultiDynamicLocatorList(List<String> dynamicPortions, WebDriver driver) {
        String[] locatorArray = locatorValue.split("%s");
        var formattedLocator = "";
        dynamicPortions.add("");
        for (int i = 0; i < locatorArray.length; i++) {
            formattedLocator += locatorArray[i] + dynamicPortions.get(i);
        }
        return driver.findElements(By.xpath(formattedLocator));
    }
}
