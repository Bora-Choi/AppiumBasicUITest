# AppiumBasicUITest

This project demonstrates essential UI interaction tests on Android using Appium. It covers basic gestures like swipe, scroll, long press, and button text verification on the API Demos app.

## Project Structure
AppiumBasicUITest/
├── README.md
├── .gitignore
├── src/
│ └── test/
│ └── java/
│ └── com/
│ └── boratest/
│ └── appiumdemo/
│ ├── BaseTest.java
│ ├── LongpressGestureTest.java
│ ├── ScrollGestureTest.java
│ ├── SwipeGestureTest.java
│ ├── ViewButtonTextTest.java
│ └── WifiSettingsTest.java
└── resources/
└── ApiDemos-debug.apk


- **BaseTest.java** — Sets up and tears down Appium server and Android driver, includes reusable methods for gestures like scroll and swipe.
- **LongpressGestureTest.java** — Tests long press gesture and verifies context menu options.
- **ScrollGestureTest.java** — Tests scrolling functionality to a specific element.
- **SwipeGestureTest.java** — Tests swipe gesture on an image in the gallery.
- **ViewButtonTextTest.java** — Verifies the text of a button under Views > Buttons screen.
- **WifiSettingsTest.java** — Automates WiFi setup through the Preferences dependencies screen.

## Prerequisites
- Java 11 or higher installed
- Maven (optional, if you use it for dependencies)
- Appium installed and running on your local machine
- Android SDK set up with an emulator or physical device
- The API Demos APK included in the project under `/src/test/java/resources/`

## How to Run
1. Start the Appium server manually or allow the BaseTest to start it automatically.
2. Connect an Android emulator or device with the required capabilities.
3. Run the test classes via your IDE or using TestNG from the command line.

## Logging
All tests include detailed logs using SLF4J to trace step-by-step execution for easy debugging and reporting.

