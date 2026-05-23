package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.LoginPage;
import pages.LogoutPage;

public class LogoutTest {

    LoginPage login;

    LogoutPage logout;

    @BeforeTest

    public void setup() {

        DriverSetup.setupBrowser();

        login =
        new LoginPage(DriverSetup.driver);

        logout =
        new LogoutPage(DriverSetup.driver);
    }

    @Test

    public void logoutTest() {

        login.loginApplication("john", "demo");

        logout.logoutApplication();
    }

    @AfterTest

    public void closeBrowser() {

        DriverSetup.driver.quit();
    }
}