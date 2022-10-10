package org.example.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public abstract class BaseTest {

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

    public String getCurrentPageTitle() {
        return driver.getTitle();
    }
}
