package org.example.lesson07;

import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.example.lesson05.BaseTest;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class CustomJUnitTestWatcher implements TestWatcher {

    private String SCREENSHOT_NAME = "failure %s - %s.png";

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        WebDriver driver = ((BaseTest) context.getRequiredTestInstance()).getDriver();
        File screen = MyUtils.makeScreenshot(driver, String.format(SCREENSHOT_NAME, context.getRequiredTestInstance().getClass().getSimpleName(), System.currentTimeMillis()));
        try {
            Allure.addAttachment("Screenshot", FileUtils.openInputStream(screen));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
