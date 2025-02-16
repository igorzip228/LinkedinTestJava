package pageObjects;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class InstagramPage {
    private static final By LOGIN_BUTTON = AppiumBy.xpath("//*[@text='Log in']");

    public InstagramPage() {
        $(LOGIN_BUTTON).shouldBe(visible);
    }
}