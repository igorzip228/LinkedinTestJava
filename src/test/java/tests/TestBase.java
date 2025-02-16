package tests;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestBase {
    protected AndroidDriver driver;
    protected static final String LINKEDIN_PACKAGE = "com.linkedin.android";
    protected static final String INSTAGRAM_PACKAGE = "com.instagram.android";
    protected static final String LINKEDIN_APK_PATH = System.getProperty("user.dir") + "/files/linkedin-4-1-1036.apk";
    protected static final String INSTAGRAM_APK_PATH = System.getProperty("user.dir") + "/files/instagram.apk";

    @BeforeClass
    public void setUp() throws MalformedURLException {
        new File("screenshots").mkdirs();
        initDriver();
    }


    private void initDriver() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("emulator-5554")
                .setAutomationName("UiAutomator2")
                .setPlatformName("Android")
                .setApp(LINKEDIN_APK_PATH)
                .setAppPackage("com.linkedin.android")
                .setAppActivity("com.linkedin.android.authenticator.LaunchActivityDefault")
                .setAutoGrantPermissions(true)
                .setNewCommandTimeout(Duration.ofSeconds(60))
                .setNoReset(false);


        URL appiumServer = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver(appiumServer, options);
        WebDriverRunner.setWebDriver(driver);
    }


    public void switchToApp(String appPackage) {
        driver.activateApp(appPackage);
    }

    public void closeApp(String appPackage) {
        driver.terminateApp(appPackage);
    }

    public void installSecondApp(String apkPath) {
        driver.installApp(apkPath);
    }


    @AfterMethod
    public void afterMethod() throws IOException {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File screenshot = Screenshots.takeScreenShotAsFile();
        File destFile = new File("screenshots/screenshot_" + timestamp + ".png");
        FileUtils.copyFile(screenshot, destFile);
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) {
            closeApp(LINKEDIN_PACKAGE);
            closeApp(INSTAGRAM_PACKAGE);
            driver.removeApp(LINKEDIN_PACKAGE);
            driver.removeApp(INSTAGRAM_PACKAGE);
            driver.quit();
        }
    }
}
