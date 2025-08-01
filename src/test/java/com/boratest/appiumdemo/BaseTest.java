package com.boratest.appiumdemo;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

/**
 * Base class for Appium tests.
 * Sets up Appium server and Android driver before tests,
 * and tears down after all tests complete.
 */
public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected AndroidDriver driver;
    protected AppiumDriverLocalService service;

    private static final String DEVICE_NAME = "Pixel 4";
    private static final String PLATFORM_NAME = "Android";
    private static final int IMPLICIT_WAIT_SEC = 10;
    private static final int SERVER_TIMEOUT_SEC = 60;
    private static final String APPIUM_JS_PATH = "C:\\Users\\Bora\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
    private static final String APP_PATH = System.getProperty("user.dir") + "/src/test/java/resources/ApiDemos-debug.apk";
    private static final String SERVER_URL = "http://127.0.0.1:4723";

    @BeforeClass
    public void setup() throws MalformedURLException {
        logger.info("Start: Appium server setup");

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(APPIUM_JS_PATH))
                .withIPAddress("127.0.0.1")
                .usingPort(4723)
                .withTimeout(Duration.ofSeconds(SERVER_TIMEOUT_SEC))
                .build();

        service.start();
        logger.info("Appium server started");

        logger.info("Initializing Android driver");
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName(DEVICE_NAME)
                .setPlatformName(PLATFORM_NAME)
                .setApp(APP_PATH);

        driver = new AndroidDriver(new URL(SERVER_URL), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICIT_WAIT_SEC));

        logger.info("Android driver initialized with implicit wait: {} seconds", IMPLICIT_WAIT_SEC);
        logger.info("End: Appium server setup");
    }
    
    /**
     * Scrolls down until no more scrolling is possible.
     */
    public void scrollToEnd() {
        logger.info("[ACTION] Scroll to end started");
        boolean canScrollMore;
        do {
            canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
                "left", 100, "top", 100, "width", 200, "height", 200,
                "direction", "down",
                "percent", 1.0
            ));
            logger.info("[STEP] Scrolling... canScrollMore={}", canScrollMore);
        } while (canScrollMore);
        logger.info("[ACTION] Scroll to end finished");
    }
    
    /**
     * Scrolls to the element with the given visible text.
     * @param ele the visible text of the target element
     * @return WebElement found after scroll
     */
    public WebElement scrollToElement(String ele) {
        logger.info("[ACTION] Scrolling to element with text: '{}'", ele);
        WebElement element = driver.findElement(AppiumBy.androidUIAutomator(
            "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(text(\"" + ele + "\"))"
        ));
        logger.info("[ACTION] Found element with text: '{}'", ele);
        return element;
    }
    
    /**
     * Performs swipe gesture on a given element.
     * @param ele the target WebElement to swipe on
     * @param swipeDirection direction of swipe ("left", "right", "up", "down")
     */
    public void swipeAction(WebElement ele, String swipeDirection) {
        logger.info("[ACTION] Swipe gesture '{}' on element with id: {}", swipeDirection, ((RemoteWebElement) ele).getId());
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
            "elementId", ((RemoteWebElement) ele).getId(),
            "direction", swipeDirection,
            "percent", 0.2
        ));
        logger.info("[ACTION] Swipe gesture completed");
    }

    @AfterClass
    public void tearDown() {
        logger.info("Start: Test environment cleanup");

        if (driver != null) {
            driver.quit();
            logger.info("Android driver stopped");
        }

        if (service != null && service.isRunning()) {
            service.stop();
            logger.info("Appium server stopped");
        }

        logger.info("End: Test environment cleanup");
    }
}
