package org.example.lesson06.pages;

import org.example.lesson03.config.ApplicationGlobalState;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    public WebElement emailField;

    @FindBy(id = "passwd")
    public WebElement passwordField;

    @FindBy(css = "#SubmitLogin > span")
    public WebElement submitButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        setPageTitle("Login - My Store");
        setPageURL(ApplicationGlobalState.getInstance().getBaseUrl() + "?controller=authentication");
    }

    public void setValidEmail() {
        setInputText(emailField, ApplicationGlobalState.getInstance().getEmail());
    }

    public void setValidPassword() {
        setInputText(passwordField, ApplicationGlobalState.getInstance().getPassword());
    }

    public void clickSubmitButton() {
        submitButton.click();
    }

    public void loginWithValidCredentials() {
        navigateToSelf();
        setValidEmail();
        setValidPassword();
        clickSubmitButton();
    }
}
