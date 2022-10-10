package org.example.lesson03;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang.RandomStringUtils;
import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class BaseTest {

    public static String LOGIN_MENU_CSS = ".header_user_info .login";
    public static String LOGOUT_MENU_CSS = ".header_user_info .logout";
    public static String ACCOUNT_MENU_CSS = ".header_user_info .account";
    public static String EMAIL_FIELD_ID = "email";
    public static String PASSWORD_FIELD_ID = "passwd";
    public static String SUBMIT_BUTTON_CSS = "#SubmitLogin > span";

    // create a new address
    public static String ADDRESSES_OPTION_XPATH = "//a[@title=\'Addresses\']/span";
    public static String ADD_ADDRESS_BUTTON_XPATH = "//a[@title=\'Add an address\']/span";
    public static String ADDRESS1_FIELD_ID = "address1";
    public static String CITY_FIELD_ID = "city";
    public static String STATE_FIELD_ID = "id_state";
    public static String POSTCODE_FIELD_ID = "postcode";
    public static String PHONE_FIELD_ID = "phone";
    public static String ALIAS_FIELD_ID = "alias";
    public static String SUBMIT_ADDRESS_BUTTON_CSS = "#submitAddress > span";
    public static String ADDRESS_CARD_XPATH = "//div[contains(@class,\"bloc_adresses\")]/div[contains(@class,address)]//h3";
    public static String ADDRESS_CARD_WITH_ALIAS_XPATH = "//div[contains(@class,\"bloc_adresses\")]/div[contains(@class,address)]//h3[text()=\'%s\']";
    public static String ADDRESS_CARD_DELETE_BUTTON_XPATH = "//h3[text()=\"%s\"]/parent::li/parent::ul/li[@class=\'address_update\']/a[@title=\'Delete\']";
    public static String ADDRESS_CARD_ADDRESS1_XPATH = "//h3[text()=\'%s\']/parent::li/parent::ul/li/span[@class=\'address_address1\']";
    public static String ADDRESS_CARD_PHONE_XPATH = "//h3[text()=\'%s\']/parent::li/parent::ul/li/span[@class=\'address_phone\']";

    // page titles
    public static String INDEX_PAGE_TITLE = "My Store";
    public static String LOGIN_PAGE_TITLE = "Login - My Store";
    public static String MY_ACCOUNT_PAGE_TITLE = "My account - My Store";
    public static String ADDRESSES_PAGE_TITLE = "Addresses - My Store";
    public static String ADDRESS_PAGE_TITLE = "Address - My Store";

    // valid address data
    public static String VALID_ADDRESS = "242 Gentle Trace";
    public static String VALID_PHONE = "276-151-9740";
    public static String VALID_POSTCODE = "50385";
    public static String VALID_CITY = "Los Angeles";

    private static WebDriver driver;

    /**
     * Init web driver.
     */
    public static void initDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(ApplicationGlobalState.getInstance().getImplicitWait()));
    }

    /**
     * Open the given url.
     * @param url is the given url.
     */
    public static void openURL(String url) {
        driver.get(url);
    }

    /**
     * Get web element with the given locator value and locator type.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     * @return web element if it is observed or null otherwise.
     */
    public static WebElement getElement(String locator, LocatorType type) {
        WebElement element = null;
        switch (type) {
            case XPATH:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
                element = driver.findElement(By.xpath(locator));
                break;
            case ID:new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
                element = driver.findElement(By.id(locator));
                break;
            case CSS:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locator)));
                element = driver.findElement(By.cssSelector(locator));
                break;
            case CLASS_NAME:
                new WebDriverWait(driver, Duration.ofSeconds(ApplicationGlobalState.getInstance().getExplicitWait())).until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
                element = driver.findElement(By.className(locator));
                break;
        }
        return element;
    }

    /**
     * Get list of web elements with the given locator value and locator type.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     * @return list of web elements if they are observed or null otherwise.
     */
    public static List<WebElement> getElements(String locator, LocatorType type) {
        List<WebElement> elements = null;
        switch (type) {
            case XPATH:
                elements = driver.findElements(By.xpath(locator));
                break;
            case ID:
                elements = driver.findElements(By.id(locator));
                break;
            case CSS:
                elements = driver.findElements(By.cssSelector(locator));
                break;
            case CLASS_NAME:
                elements = driver.findElements(By.className(locator));
                break;
        }
        return elements;
    }

    /**
     * Check if web element with the given locator value and locator type is displayed or not.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     * @return true is the web element is displayed or false otherwise.
     */
    public static boolean isElementsDisplayed(String locator, LocatorType type) {
        List<WebElement> elements = getElements(locator, type);
        if (!elements.isEmpty()) {
            for(WebElement element : elements) {
                if(!element.isDisplayed()) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Click web element with the given locator and locator type.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     */
    public static void clickElement(String locator, LocatorType type) {
        getElement(locator, type).click();
    }

    /**
     * Set the given text into text field with the given locator value and locator type.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     * @param text is the given text.
     */
    public static void setInputText(String locator, LocatorType type, String text) {
        WebElement webElement = getElement(locator, type);
        webElement.click();
        webElement.clear();
        webElement.sendKeys(text);
    }

    /**
     * Select random option for selectbox with the given locator value and locator type.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     */
    public static void selectRandomSelectBoxOption(String locator, LocatorType type) {
        WebElement element = getElement(locator, type);
        Select selectBox = new Select(element);
        selectBox.selectByIndex(ThreadLocalRandom.current().nextInt(0, selectBox.getOptions().size()));
    }

    /**
     * Check if the current web page title corresponds to the expected title.
     * @param expectedTitle is expected title value.
     * @return true if the current title corresponds to the expected one.
     */
    public static boolean isTitleCorrect(String expectedTitle) {
        String actualTitle = driver.getTitle();
        if (expectedTitle.equals(actualTitle)) {
            System.out.println("Verification Successful - The correct title '" + actualTitle + "' is displayed on the web page.");
            return true;
        } else {
            System.out.println("Verification Failed - An incorrect title '" + actualTitle + "' is displayed on the web page.");
            return false;
        }
    }

    /**
     * Get text of the web element with the given locator and locator type.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     * @return text value.
     */
    public static String getElementText(String locator, LocatorType type) {
        WebElement element = getElement(locator, type);
        return element.getText();
    }

    /**
     * Check if text of a web element with the given locator value and locator type contains the given text.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     * @param expectedText is expected text.
     * @return true if texts are the same or false otherwise.
     */
    public static boolean isElementTextCorrect(String locator, LocatorType type, String expectedText) {
        String actualText = getElementText(locator, type);
        if (expectedText.equals(getElementText(locator, type))) {
            System.out.println("Verification Successful - The web element contains correct text '" + actualText + "'.");
            return true;
        } else {
            System.out.println("Verification Failed - The web element contains incorrect text '" + actualText + "'.");
            return false;
        }
    }

    public static void answerAlert(boolean answer) {
        Alert alert = driver.switchTo().alert();
        if (answer) {
            alert.accept(); // for OK
        } else {
            alert.dismiss(); // for Cancel
        }
    }

    public static void simpleLogin() {
        openURL(ApplicationGlobalState.getInstance().getBaseUrl());
        clickElement(LOGIN_MENU_CSS, LocatorType.CSS);
        setInputText(EMAIL_FIELD_ID, LocatorType.ID, ApplicationGlobalState.getInstance().getEmail());
        setInputText(PASSWORD_FIELD_ID, LocatorType.ID, ApplicationGlobalState.getInstance().getPassword());
        clickElement(SUBMIT_BUTTON_CSS, LocatorType.CSS);
    }

    public static void logoutThroughCookie() {
        driver.manage().deleteAllCookies();
        driver.navigate().refresh();
    }

    public static String addRandomAddress() {
        clickElement(ACCOUNT_MENU_CSS, LocatorType.CSS);
        clickElement(ADDRESSES_OPTION_XPATH, LocatorType.XPATH);
        clickElement(ADD_ADDRESS_BUTTON_XPATH, LocatorType.XPATH);
        setInputText(ADDRESS1_FIELD_ID, LocatorType.ID, VALID_ADDRESS);
        setInputText(CITY_FIELD_ID, LocatorType.ID, VALID_CITY);
        clickElement(STATE_FIELD_ID, LocatorType.ID);
        selectRandomSelectBoxOption(STATE_FIELD_ID, LocatorType.ID);
        setInputText(POSTCODE_FIELD_ID, LocatorType.ID, VALID_POSTCODE);
        setInputText(PHONE_FIELD_ID, LocatorType.ID, VALID_PHONE);
        String randomAddressAlias = RandomStringUtils.random(7, true, false);
        setInputText(ALIAS_FIELD_ID, LocatorType.ID, randomAddressAlias);
        clickElement(SUBMIT_ADDRESS_BUTTON_CSS, LocatorType.CSS);
        return randomAddressAlias;
    }

    /**
     * Close driver.
     */
    public static void closeDriver() {
        driver.close();
    }
}
