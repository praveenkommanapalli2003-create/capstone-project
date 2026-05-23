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

        login = new LoginPage(DriverSetup.driver);
        transfer = new TransferFundsPage(DriverSetup.driver);

        // MUST login first
        login.loginApplication("john", "demo");
    }

    @Test
    public void transferTest() throws Exception {

        Thread.sleep(2000); // allow accounts to load

        transfer.transferFunds("10"); // small valid amount
    }

    @AfterTest
    public void tearDown() {
        DriverSetup.driver.quit();
    }
}