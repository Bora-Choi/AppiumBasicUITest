package com.boratest.appiumdemo;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test to verify that the 'Normal' button under Views > Buttons screen
 * is present and has the correct label text.
 */
public class ViewButtonTextTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(ViewButtonTextTest.class);

    @Test
    public void textTest() {
        logger.info("[START] Verify 'Normal' button text");

        logger.info("[ACTION] Clicking 'Views' menu");
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        logger.info("[ACTION] Clicking 'Buttons' screen");
        driver.findElement(AppiumBy.accessibilityId("Buttons")).click();

        logger.info("[ACTION] Retrieving text from 'Normal' button");
        String buttonText = driver.findElement(AppiumBy.accessibilityId("Normal")).getText();

        logger.info("[ASSERT] Verifying button text equals 'Normal'");
        Assert.assertEquals(buttonText, "Normal", "Button text mismatch");

        logger.info("[END] Test completed successfully");
    }
}
