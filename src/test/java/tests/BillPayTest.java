package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.BillPayPage;
import pages.LoginPage;

public class BillPayTest {

    LoginPage login;

    BillPayPage bill;

    @BeforeTest

    public void setup() {

        DriverSetup.setupBrowser();

        login =
        new LoginPage(DriverSetup.driver);

        bill =
        new BillPayPage(DriverSetup.driver);
    }

    @Test

    public void billPayTest() {

        login.loginApplication("john", "demo");

        bill.payBill();
    }

    @AfterTest

    public void closeBrowser() {

        DriverSetup.driver.quit();
    }
}