package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.LoginPage;

public class LoginTest {

    LoginPage login;

    @BeforeTest
    public void setup() {

        DriverSetup.setupBrowser();
        login = new LoginPage(DriverSetup.driver);
    }

    @Test
    public void loginTest() {

        login.loginApplication("john", "demo");
    }

    @AfterTest
    public void tearDown() {

        DriverSetup.driver.quit();
    }
}