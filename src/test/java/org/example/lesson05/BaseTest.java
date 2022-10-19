package org.example.lesson05;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.lesson07.MyWebDriverEventListener;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseTest {

    private static EventFiringWebDriver driver;
    //private static WebDriver driver;

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

        //driver = new ChromeDriver(options);
        driver = new EventFiringWebDriver(new ChromeDriver((options)));
        driver.register(new MyWebDriverEventListener());
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    /**
     * Close driver.
     */
    @AfterAll
    static void tearDown() {
        driver.quit();
    }

    @AfterEach
    public void checkBrowser(){
        List<LogEntry> allLogRows = driver.manage().logs().get(LogType.BROWSER).getAll();
        if(!allLogRows.isEmpty()){

            if (allLogRows.size() > 0 ) {
                allLogRows.forEach(logEntry -> {
                    System.out.println(logEntry.getMessage());
                });
            }
        }
    }

    /**
     * Open the given url.
     * @param url is the given url.
     */
    public static void openURL(String url) {
        driver.get(url);
    }

    public List<String> openURLInNewTab(String url) {
        JavascriptExecutor js = (JavascriptExecutor)getDriver();
        js.executeScript( "window.open();" );

        List<String> tabs = new ArrayList<>(getDriver().getWindowHandles());
        getDriver().switchTo().window(tabs.get(1)); //switches to new tab
        getDriver().get(url);

        return tabs;
    }

    public String getCurrentPageTitle() {
        return driver.getTitle();
    }
}
