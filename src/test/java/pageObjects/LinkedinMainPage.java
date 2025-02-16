package pageObjects;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LinkedinMainPage {
    private static final By profileItem = AppiumBy.xpath("//*[@content-desc='My Profile and Communities']");
    private static final By settingsLink = AppiumBy.xpath("//*[@text='Settings']");
    private static final By signOutLink = AppiumBy.xpath("//*[@content-desc='Sign Out']");
    private static final By signOutButton = AppiumBy.xpath("//android.widget.Button[@text='Sign out']");

    private static final By closeButtononAllert = AppiumBy.xpath("//android.view.View[@content-desc='login?rmDisableAutoLogin=true&midToken=AQGIJgp1dgg0ug']");
    private static final By linkedinLabel = AppiumBy.xpath("//android.widget.TextView[@text='LinkedIn']");

    public LinkedinMainPage() {
        $(profileItem).shouldBe(visible);
    }

    public LinkedinLoginPage logout(){
        $(profileItem).click();
        $(settingsLink).scrollTo().click();
        $(signOutLink).scrollTo().click();
        $(signOutButton).click();
        $(linkedinLabel).shouldBe(visible);
        $(closeButtononAllert).click();
        return new LinkedinLoginPage();
    }
}
