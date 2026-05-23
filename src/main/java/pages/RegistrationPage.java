package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {

    WebDriver driver;

    public RegistrationPage(WebDriver driver) {

        this.driver = driver;
    }

    By registerLink = By.linkText("Register");

    By firstName =
    By.id("customer.firstName");

    By lastName =
    By.id("customer.lastName");

    By address =
    By.id("customer.address.street");

    By city =
    By.id("customer.address.city");

    By state =
    By.id("customer.address.state");

    By zipCode =
    By.id("customer.address.zipCode");

    By phone =
    By.id("customer.phoneNumber");

    By ssn =
    By.id("customer.ssn");

    By username =
    By.id("customer.username");

    By password =
    By.id("customer.password");

    By confirmPassword =
    By.id("repeatedPassword");

    By registerBtn =
    By.xpath("//input[@value='Register']");

    public void registerUser() {

        driver.findElement(registerLink).click();

        driver.findElement(firstName)
        .sendKeys("Kommanapalli");

        driver.findElement(lastName)
        .sendKeys("Lakshmi Vara Praveen");

        driver.findElement(address)
        .sendKeys("Visakhapatnam");

        driver.findElement(city)
        .sendKeys("Visakhapatnam");

        driver.findElement(state)
        .sendKeys("Telangana");

        driver.findElement(zipCode)
        .sendKeys("500001");

        driver.findElement(phone)
        .sendKeys("9876543210");

        driver.findElement(ssn)
        .sendKeys("12345");

        driver.findElement(username)
        .sendKeys("praveen");

        driver.findElement(password)
        .sendKeys("admin123");

        driver.findElement(confirmPassword)
        .sendKeys("admin123");

        driver.findElement(registerBtn).click();
    }
}