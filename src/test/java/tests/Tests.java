package tests;

import org.testng.annotations.Test;
import pageObjects.InstagramPage;
import pageObjects.LinkedinLoginPage;


public class Tests extends TestBase{

    private static final String EMAIL = "goprodigiphy@gmail.com";
    private static final String PASSWORD = "Ytrewq@1596";

    @Test
    public void testLinkedInLoginFlow() throws InterruptedException {
        new LinkedinLoginPage()
                .login(EMAIL, PASSWORD)
                .logout();
    }

    @Test
    public void testSwitchingApps(){
        installSecondApp(INSTAGRAM_APK_PATH);
        switchToApp(INSTAGRAM_PACKAGE);

        new InstagramPage();

        closeApp(LINKEDIN_PACKAGE);
        closeApp(INSTAGRAM_PACKAGE);
    }
}