package tests;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.DriverSetup;

import pages.LoginPage;

import utils.ExtentManager;
import utils.ScreenshotUtil;

public class LoginTest {

    LoginPage login;

    ExtentReports extent;

    ExtentTest test;

    @BeforeTest

    public void setup() {

        DriverSetup.setupBrowser();

        login =
        new LoginPage(DriverSetup.driver);

        extent =
        ExtentManager.getReport();

        test =
        extent.createTest("Login Test");
    }

    @Test

    public void loginTest() throws Exception {

        login.loginApplication
        ("praveen123", "admin123");

        test.pass("Login Successful");

        ScreenshotUtil.captureScreenshot
        (DriverSetup.driver, "LoginSuccess");
    }

    @AfterTest

    public void closeBrowser() {

        extent.flush();

        DriverSetup.driver.quit();
    }
}