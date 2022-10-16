package org.example.lesson06.pages;

import org.apache.commons.lang.RandomStringUtils;
import org.example.lesson03.config.ApplicationGlobalState;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddressPage extends BasePage {

    @FindBy(id = "address1")
    private WebElement address1Field;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "id_state")
    private WebElement stateField;

    @FindBy(id = "postcode")
    private WebElement postcodeField;

    @FindBy(id = "phone")
    private WebElement phoneField;

    @FindBy(id = "alias")
    private WebElement aliasField;

    @FindBy(css = "#submitAddress > span")
    private WebElement submitAddressButton;

    public AddressPage(WebDriver driver) {
        super(driver);
        setPageTitle("Address - My Store");
        setPageURL(ApplicationGlobalState.getInstance().getBaseUrl() + "?controller=address");
    }

    public void setAddress1Field(String address) {
        setInputText(address1Field, address);
    }

    public void setCityField(String city) {
        setInputText(cityField, city);
    }

    public void selectRandomState() {
        stateField.click();
        selectRandomSelectBoxOption(stateField);
    }

    public void setPostcodeField(String postcode) {
        setInputText(postcodeField, postcode);
    }

    public void setPhoneField(String phone) {
        setInputText(phoneField, phone);
    }

    public void setAliasField(String alias) {
        setInputText(aliasField, alias);
    }

    public void clickSubmitAddressButton() {
        submitAddressButton.click();
    }

    public String addRandomAddress() {
        navigateToSelf();
        setAddress1Field(ApplicationGlobalState.getInstance().getValidAddress());
        setCityField(ApplicationGlobalState.getInstance().getValidCity());
        selectRandomState();
        setPostcodeField(ApplicationGlobalState.getInstance().getValidPostcode());
        setPhoneField(ApplicationGlobalState.getInstance().getValidPhone());
        String randomAddressAlias = RandomStringUtils.random(7, true, false);
        setAliasField(randomAddressAlias);
        clickSubmitAddressButton();

        return randomAddressAlias;
    }
}
