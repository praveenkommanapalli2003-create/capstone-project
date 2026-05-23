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
    public void setup() throws Exception {

        DriverSetup.setupBrowser();

        // STEP 1: Clean ParaBank database
        DriverSetup.driver.get(
            "https://parabank.parasoft.com/parabank/services/bank/cleanDB"
        );
        Thread.sleep(3000);
        System.out.println("DB cleaned");

        // STEP 2: Initialize fresh database
        DriverSetup.driver.get(
            "https://parabank.parasoft.com/parabank/services/bank/initializeDB"
        );
        Thread.sleep(3000);
        System.out.println("DB initialized");

        // STEP 3: Go to home page
        DriverSetup.driver.get(
            "https://parabank.parasoft.com/parabank/index.htm"
        );
        Thread.sleep(2000);

        // STEP 4: Login
        login = new LoginPage(DriverSetup.driver);
        transfer = new TransferFundsPage(DriverSetup.driver);
        login.loginApplication("john", "demo");
        Thread.sleep(3000);

        System.out.println("Setup complete - ready to test");
    }

    @Test
    public void transferTest() throws Exception {
        Thread.sleep(4000);
        transfer.transferFunds("10");
    }

    @AfterTest
    public void tearDown() {
        if (DriverSetup.driver != null) {
            DriverSetup.driver.quit();
        }
    }
}