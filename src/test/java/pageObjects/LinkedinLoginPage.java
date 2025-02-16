package pageObjects;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LinkedinLoginPage {
    private static final By singInButton = AppiumBy.xpath("//*[@text='Sign in']");
    private static final By emailInput = AppiumBy.id("com.linkedin.android:id/growth_login_join_fragment_email_address");
    private static final By passwordInput = AppiumBy.id("com.linkedin.android:id/growth_login_join_fragment_password");
    private static final By continueButton = AppiumBy.xpath("//*[@text='Continue']");


    public LinkedinMainPage login(String email, String password) throws InterruptedException {
        $(singInButton).shouldBe(visible, Duration.ofSeconds(10)).click();
        $(emailInput).setValue(email);
        $(passwordInput).setValue(password);
        $(continueButton).click();
        Thread.sleep(10000);
        return new LinkedinMainPage();
    }

}
