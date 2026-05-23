package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UpdateContactPage {

    WebDriver driver;

    public UpdateContactPage(WebDriver driver) {

        this.driver = driver;
    }

    By updateContact =
    By.linkText("Update Contact Info");

    By phone =
    By.id("customer.phoneNumber");

    By updateBtn =
    By.xpath("//input[@value='Update Profile']");

    public void updateInfo() {

        driver.findElement(updateContact)
        .click();

        driver.findElement(phone)
        .clear();

        driver.findElement(phone)
        .sendKeys("8888888888");

        driver.findElement(updateBtn)
        .click();
    }
}