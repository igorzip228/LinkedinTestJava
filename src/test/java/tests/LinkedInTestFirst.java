package tests;

import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.WebDriverRunner;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LinkedInTestFirst {
    private AndroidDriver driver;
    private static final String LINKEDIN_PACKAGE = "com.linkedin.android";
    private static final String LINKEDIN_APK_PATH = System.getProperty("user.dir") + "/files/linkedin-4-1-1036.apk";
    private static final String EMAIL = "goprodigiphy@gmail.com";
    private static final String PASSWORD = "Ytrewq@1596";

    @BeforeClass
    public void setup() throws MalformedURLException, InterruptedException {
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

    @Test
    public void testLinkedInLoginFlow() throws InterruptedException {
        $(AppiumBy.xpath("//*[@text='Sign in']")).shouldBe(visible, Duration.ofSeconds(10)).click();
        $(AppiumBy.id("com.linkedin.android:id/growth_login_join_fragment_email_address")).setValue(EMAIL);
        $(AppiumBy.id("com.linkedin.android:id/growth_login_join_fragment_password")).setValue(PASSWORD);
        $(AppiumBy.xpath("//*[@text='Continue']")).click();
        $(AppiumBy.xpath("//*[@content-desc='My Profile and Communities']"))
                .shouldBe(visible, Duration.ofSeconds(10))
                .click();
        $(AppiumBy.xpath("//*[@text='Settings']")).scrollTo().click();
        $(AppiumBy.xpath("//*[@content-desc='Sign Out']"))
                .scrollTo()
                .click();
        $(AppiumBy.xpath("//android.widget.Button[@text='Sign out']")).click();
        $(AppiumBy.id("com.linkedin.android:id/growth_login_join_fragment_email_address")).shouldBe(visible);
    }

    @AfterMethod
    public void takeScreenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File destFile = new File("screenshots/screenshot_" + timestamp + ".png");
        FileUtils.copyFile(screenshot, destFile);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.terminateApp(LINKEDIN_PACKAGE);
            driver.removeApp(LINKEDIN_PACKAGE);
            driver.quit();
        }
    }
}