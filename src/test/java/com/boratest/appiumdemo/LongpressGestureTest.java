package com.boratest.appiumdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tests long click gesture on 'People Names' in Android API Demos app
 * and verifies the context menu options.
 */
public class LongpressGestureTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(LongpressGestureTest.class);

    @Test
    public void testLongClickGesture() {
        logger.info("[START] Long Click Gesture test");

        logger.info("[ACTION] Clicking 'Views' menu");
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        logger.info("[ACTION] Clicking 'Expandable Lists'");
        driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();

        logger.info("[ACTION] Clicking '1. Custom Adapter'");
        driver.findElement(AppiumBy.accessibilityId("1. Custom Adapter")).click();

        logger.info("[ACTION] Locating 'People Names' element");
        WebElement peopleNamesElement = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));

        logger.info("[ACTION] Performing long click gesture (2000ms)");
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
            "elementId", ((RemoteWebElement) peopleNamesElement).getId(),
            "duration", 2000
        ));

        logger.info("[VERIFY] Checking if context menu title is displayed");
        WebElement contextMenuTitle = driver.findElement(By.id("android:id/title"));
        Assert.assertTrue(contextMenuTitle.isDisplayed(), "Context menu title is not displayed");

        logger.info("[VERIFY] Verifying first context menu option text");
        WebElement sampleMenuOption = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Sample menu']"));
        Assert.assertEquals(sampleMenuOption.getText(), "Sample menu");

        logger.info("[VERIFY] Verifying second context menu option text");
        WebElement sampleActionOption = driver.findElement(By.xpath("//android.widget.TextView[@resource-id='android:id/title' and @text='Sample action']"));
        Assert.assertEquals(sampleActionOption.getText(), "Sample action");

        logger.info("[END] Long Click Gesture test completed successfully");
    }
}
