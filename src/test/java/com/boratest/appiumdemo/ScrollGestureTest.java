package com.boratest.appiumdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;


public class ScrollGestureTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ScrollGestureTest.class);

    @Test
    public void scrollTest() throws InterruptedException {
        logger.info("[START] Scroll test");

        logger.info("[ACTION] Clicking 'Views' menu");
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        logger.info("[WAIT] Waiting 2 seconds for UI to stabilize");
        Thread.sleep(2000);

        logger.info("[ACTION] Scrolling to 'WebView' element");
        scrollToElement("WebView").click();

        logger.info("[END] Scroll test completed");
    }
}
