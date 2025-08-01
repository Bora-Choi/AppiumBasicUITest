package com.boratest.appiumdemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class SwipeGestureTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(SwipeGestureTest.class);

    @Test
    public void swipeTest() {
        logger.info("[START] Swipe gesture test");

        logger.info("[ACTION] Clicking 'Views' menu");
        driver.findElement(AppiumBy.accessibilityId("Views")).click();

        logger.info("[ACTION] Clicking 'Gallery'");
        driver.findElement(AppiumBy.accessibilityId("Gallery")).click();

        logger.info("[ACTION] Clicking '1. Photos'");
        driver.findElement(AppiumBy.accessibilityId("1. Photos")).click();

        logger.info("[ACTION] Locating first image in gallery");
        WebElement firstImage = driver.findElement(By.xpath("//android.widget.Gallery[@resource-id=\"io.appium.android.apis:id/gallery\"]/android.widget.ImageView[1]"));

        logger.info("[CHECK] Checking 'focusable' attribute before swipe");
        String firstTimeValue = firstImage.getAttribute("focusable");
        Assert.assertEquals("true", firstTimeValue);

        logger.info("[ACTION] Performing swipe left gesture on first image");
        swipeAction(firstImage, "left");

        logger.info("[CHECK] Checking 'focusable' attribute after swipe");
        String secondTimeValue = firstImage.getAttribute("focusable");
        Assert.assertEquals("false", secondTimeValue);

        logger.info("[END] Swipe gesture test completed");
    }

}
