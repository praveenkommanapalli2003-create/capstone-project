package tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import base.DriverSetup;
import pages.LoginPage;
import pages.UpdateContactPage;

public class UpdateContactTest {

    LoginPage login;
    UpdateContactPage update;

    @BeforeClass
    public void setup() {

        DriverSetup.setupBrowser();

        login = new LoginPage(DriverSetup.driver);
        update = new UpdateContactPage(DriverSetup.driver);

        // LOGIN FIRST
        login.loginApplication("john", "demo");
    }

    @Test
    public void updateContactTest() throws Exception {

        System.out.println("Update Contact Test Started");

        Thread.sleep(3000); // wait for accounts page

        // OPEN UPDATE CONTACT DIRECTLY
        update.openPage();

        Thread.sleep(2000);

        update.updateContact(
                "Praveen",
                "Kumar",
                "Street 123",
                "Hyderabad",
                "500001",
                "9999999999"
        );

        System.out.println("Update Contact Completed");

        Thread.sleep(3000);
    }

    @AfterClass
    public void tearDown() {

        if (DriverSetup.driver != null) {
            DriverSetup.driver.quit();
        }
    }
}