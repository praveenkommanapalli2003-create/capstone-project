package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.RegistrationPage;

public class RegistrationTest {

    RegistrationPage register;

    @BeforeTest

    public void setup() {

        DriverSetup.setupBrowser();

        register =
        new RegistrationPage(DriverSetup.driver);
    }

    @Test

    public void registrationTest() {

        register.registerUser();
    }

    @AfterTest

    public void closeBrowser() {

        DriverSetup.driver.quit();
    }
}