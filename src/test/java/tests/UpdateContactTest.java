package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.LoginPage;
import pages.UpdateContactPage;

public class UpdateContactTest {

    LoginPage login;

    UpdateContactPage update;

    @BeforeTest

    public void setup() {

        DriverSetup.setupBrowser();

        login =
        new LoginPage(DriverSetup.driver);

        update =
        new UpdateContactPage(DriverSetup.driver);
    }

    @Test

    public void updateTest() {

        login.loginApplication("john", "demo");

        update.updateInfo();
    }

    @AfterTest

    public void closeBrowser() {

        DriverSetup.driver.quit();
    }
}