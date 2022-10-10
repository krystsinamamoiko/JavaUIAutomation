package org.example.lesson06.pages;

import org.example.lesson03.enums.LocatorType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends BasePage {

    @FindBy(className = "product-container")
    private List<WebElement> products;

    private DynamicWebElement quickViewButton = new DynamicWebElement("(//div[@class='product-container'])[%s]//a[@class='quick-view']");
    private DynamicWebElement certainProductContainer = new DynamicWebElement("(//div[@class='product-container'])[%s]");

    public ProductsPage(WebDriver driver) {
        super(driver);
        setPageTitle("Summer Dresses - My Store");
    }

    public int getProductNumber() {
        return products.size();
    }

    public void hoverProduct(int order) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(
            certainProductContainer.parseDynamicLocator(String.valueOf(order), getDriver(), LocatorType.XPATH)
        ).perform();
    }

    public boolean isQuickViewButtonVisible(int order) {
        return isDynamicElementDisplayed(quickViewButton, String.valueOf(order));
    }
}
