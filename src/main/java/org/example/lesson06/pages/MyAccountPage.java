package org.example.lesson06.pages;

import org.example.lesson03.config.ApplicationGlobalState;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    @FindBy(xpath = "//a[@title=\'Addresses\']/span")
    private WebElement addressesOption;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        setPageTitle("My account - My Store");
        setPageURL(ApplicationGlobalState.getInstance().getBaseUrl() + "?controller=my-account");
    }

    public void clickAddressesOption() {
        addressesOption.click();
    }
}
