package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage {

    WebDriver driver;

    public LogoutPage(WebDriver driver) {

        this.driver = driver;
    }

    By logoutLink =
    By.linkText("Log Out");

    public void logoutApplication() {

        driver.findElement(logoutLink)
        .click();
    }
}