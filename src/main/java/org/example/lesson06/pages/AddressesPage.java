package org.example.lesson06.pages;

import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AddressesPage extends BasePage {

    @FindBy(xpath = "//a[@title=\'Add an address\']/span")
    private WebElement addAddressButton;

    @FindBy(xpath = "//div[contains(@class,\"bloc_adresses\")]/div[contains(@class,address)]//h3")
    private List<WebElement> addressCardsList;

    private DynamicWebElement addressCardWithAlias = new DynamicWebElement("//div[contains(@class,\"bloc_adresses\")]/div[contains(@class,address)]//h3[text()=\'%s\']");
    private DynamicWebElement addressCardDeleteButton = new DynamicWebElement("//h3[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')=\"%s\"]/parent::li/parent::ul/li[@class=\'address_update\']/a[@title=\'Delete\']");
    private DynamicWebElement addressCardAddress1 = new DynamicWebElement("//h3[text()=\'%s\']/parent::li/parent::ul/li/span[@class=\'address_address1\']");
    private DynamicWebElement addressCardPhone = new DynamicWebElement("//h3[text()=\'%s\']/parent::li/parent::ul/li/span[@class=\'address_phone\']");

    public AddressesPage(WebDriver driver) {
        super(driver);
        setPageTitle("Addresses - My Store");
        setPageURL(ApplicationGlobalState.getInstance().getBaseUrl() + "?controller=addresses");
    }

    public List<WebElement> getAddressCardsList() {
        return addressCardsList;
    }

    public void clickAddAddressButton() {
        addAddressButton.click();
    }

    public boolean isAddressCardWithAliasVisible(String alias) {
        return isDynamicElementDisplayed(addressCardWithAlias, alias);
    }

    public String getAddressCardAddressByAlias(String alias) {
        return addressCardAddress1.parseDynamicLocator(alias, getDriver(), LocatorType.XPATH).getText();
    }

    public String getAddressCardPhoneByAlias(String alias) {
        return addressCardPhone.parseDynamicLocator(alias, getDriver(), LocatorType.XPATH).getText();
    }

    public void clickDeleteAddressCardByAlias(String alias) {
        addressCardDeleteButton.parseDynamicLocator(alias, getDriver(), LocatorType.XPATH).click();
    }
}
