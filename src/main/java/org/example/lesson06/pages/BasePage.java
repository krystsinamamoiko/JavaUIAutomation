package org.example.lesson06.pages;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class BasePage extends AbstractPage {

    private final String STORIES_MENU_TITLE = "Our stores";

    @FindBy(css = ".header_user_info .login")
    private WebElement loginMenuItem;

    @FindBy(css = ".header_user_info .logout")
    private WebElement logoutMenuItem;

    @FindBy(css = ".header_user_info .account")
    private WebElement accountMenuItem;

    @FindBy(xpath = "//ul[contains(@class,'sf-menu')]/li/a[@title='Dresses']")
    private WebElement dressMenuItem;

    @FindBy(xpath = "//ul[contains(@class,'submenu-container')]/li/a[@title='Summer Dresses']")
    private WebElement summerDressMenuItem;

    private DynamicWebElement footerMenuItem = new DynamicWebElement("//ul[@class='toggle-footer']/li[@class='item']/a[@title='%s']");

    public BasePage(WebDriver driver) {
        super(driver);
        setPageTitle("My Store");
        setPageURL(ApplicationGlobalState.getInstance().getBaseUrl());
    }

    public void clickLoginMenuItem() {
        loginMenuItem.click();
    }

    public boolean isLoginMenuItemVisible() {
        return isElementDisplayed(loginMenuItem);
    }

    public void clickLogoutMenuItem() {
        logoutMenuItem.click();
    }

    public boolean isLogoutMenuItemVisible() {
        return isElementDisplayed(logoutMenuItem);
    }

    public void clickAccountMenuItem() {
        accountMenuItem.click();
    }

    public String getAccountMenuText() {
       return getElementText(accountMenuItem);
    }

    public boolean isAccountMenuItemVisible() {
        return isElementDisplayed(accountMenuItem);
    }

    public void clickStoresFooterMenuItem() {
        footerMenuItem.parseDynamicLocator(STORIES_MENU_TITLE, getDriver(), LocatorType.XPATH).click();
    }

    public boolean isStoresFooterMenuItemVisible() {
        return isDynamicElementDisplayed(footerMenuItem, STORIES_MENU_TITLE);
    }

    public void clickSummerDressMenuItem() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(dressMenuItem).
            click(summerDressMenuItem).
            build().
            perform();
    }
}
