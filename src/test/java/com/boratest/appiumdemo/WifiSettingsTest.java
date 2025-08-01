package com.boratest.appiumdemo;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Automates WiFi setup through Preference dependencies screen
 * in the Android API Demos app.
 */
public class WifiSettingsTest extends BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(WifiSettingsTest.class);

    @Test
    public void setWifiTest() {
        logger.info("[START] setwifiTest");

        logger.info("[ACTION] Clicking 'Preference' menu");
        driver.findElement(AppiumBy.accessibilityId("Preference")).click();

        logger.info("[ACTION] Clicking '3. Preference dependencies'");
        driver.findElement(AppiumBy.accessibilityId("3. Preference dependencies")).click();

        logger.info("[ACTION] Toggling WiFi checkbox");
        driver.findElement(By.id("android:id/checkbox")).click();

        logger.info("[ACTION] Opening WiFi settings dialog");
        driver.findElement(By.xpath("//android.widget.ListView[@resource-id='android:id/list']/android.widget.LinearLayout[2]/android.widget.RelativeLayout")).click();

        String wifiPassword = "BoraPassword1234";
        logger.info("[ACTION] Entering WiFi password: {}", wifiPassword);
        driver.findElement(By.id("android:id/edit")).sendKeys(wifiPassword);

        logger.info("[ACTION] Clicking OK button");
        driver.findElement(By.id("android:id/button1")).click();

        logger.info("[END] setwifiTest completed successfully");
    }
}
