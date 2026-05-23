package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.LoginPage;
import pages.TransferFundsPage;

public class TransferFundsTest {

    LoginPage login;

    TransferFundsPage transfer;

    @BeforeTest

    public void setup() {

        DriverSetup.setupBrowser();

        login =
        new LoginPage(DriverSetup.driver);

        transfer =
        new TransferFundsPage(DriverSetup.driver);
    }

    @Test

    public void transferTest() {

        login.loginApplication("john", "demo");

        transfer.transferMoney();
    }

    @AfterTest

    public void closeBrowser() {

        DriverSetup.driver.quit();
    }
}