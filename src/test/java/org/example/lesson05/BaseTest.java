package org.example.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.lesson03.config.ApplicationGlobalState;
import org.example.lesson03.enums.LocatorType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
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

public abstract class BaseTest {

    public static String LOGIN_MENU_CSS = ".header_user_info .login";
    public static String LOGOUT_MENU_CSS = ".header_user_info .logout";
    public static String ACCOUNT_MENU_CSS = ".header_user_info .account";
    public static String EMAIL_FIELD_ID = "email";
    public static String PASSWORD_FIELD_ID = "passwd";
    public static String SUBMIT_BUTTON_CSS = "#SubmitLogin > span";
    public static String DRESSES_MENU_XPATH = "//ul[contains(@class,'sf-menu')]/li/a[@title='Dresses']";
    public static String SUMMER_DRESSES_SUBMENU_XPATH = "//ul[contains(@class,'submenu-container')]/li/a[@title='Summer Dresses']";
    public static String PRODUCT_CONTAINER_CLASS_NAME = "product-container";
    public static String QUICK_VIEW_BUTTON_XPATH = "(//div[@class='product-container'])[%d]//a[@class='quick-view']";
    public static String CERTAIN_PRODUCT_CONTAINER_XPATH = "(//div[@class='product-container'])[%d]";
    public static String FOOTER_MENU_ITEM_XPATH = "//ul[@class='toggle-footer']/li[@class='item']/a[@title='%s']";


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
    public static String ADDRESS_CARD_DELETE_BUTTON_XPATH = "//h3[translate(text(),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz')=\"%s\"]/parent::li/parent::ul/li[@class=\'address_update\']/a[@title=\'Delete\']";
    public static String ADDRESS_CARD_ADDRESS1_XPATH = "//h3[text()=\'%s\']/parent::li/parent::ul/li/span[@class=\'address_address1\']";
    public static String ADDRESS_CARD_PHONE_XPATH = "//h3[text()=\'%s\']/parent::li/parent::ul/li/span[@class=\'address_phone\']";

    // page titles
    public static String INDEX_PAGE_TITLE = "My Store";
    public static String LOGIN_PAGE_TITLE = "Login - My Store";
    public static String MY_ACCOUNT_PAGE_TITLE = "My account - My Store";
    public static String ADDRESSES_PAGE_TITLE = "Addresses - My Store";
    public static String ADDRESS_PAGE_TITLE = "Address - My Store";
    public static String SUMMER_DRESSES_PAGE_TITLE = "Summer Dresses - My Store";
    public static String STORIES_PAGE_TITLE = "Stores - My Store";

    // valid address data
    public static String VALID_ADDRESS = "242 Gentle Trace";
    public static String VALID_PHONE = "276-151-9740";
    public static String VALID_POSTCODE = "50385";
    public static String VALID_CITY = "Los Angeles";

    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    /**
     * Init web driver.
     */
    @BeforeAll
    static void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    /**
     * Close driver.
     */
    @AfterAll
    static void tearDown() {
        driver.close();
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
     * Check the given element visibility.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     * @param isVisible is expected visibility status.
     */
    public static void checkElementVisibility(String locator, LocatorType type, boolean isVisible) {
        Assertions.assertEquals(isVisible, isElementsDisplayed(locator, type), "Element visibility issue!");
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
    public void selectRandomSelectBoxOption(String locator, LocatorType type) {
        WebElement element = getElement(locator, type);
        Select selectBox = new Select(element);
        selectBox.selectByIndex(ThreadLocalRandom.current().nextInt(0, selectBox.getOptions().size()));
    }

    /**
     * Get title of the current page.
     * @return title of the current page.
     */
    public void checkPageTitle(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, driver.getTitle(), "Incorrect page title!");
    }

    /**
     * Get text of the web element with the given locator and locator type.
     * @param locator is the given locator value.
     * @param type is the given locator type.
     * @return text value.
     */
    public String getElementText(String locator, LocatorType type) {
        WebElement element = getElement(locator, type);
        return element.getText();
    }

    public void answerAlert(boolean answer) {
        Alert alert = driver.switchTo().alert();
        if (answer) {
            alert.accept(); // for OK
        } else {
            alert.dismiss(); // for Cancel
        }
    }

}
