package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BillPayPage {

    WebDriver driver;
    WebDriverWait wait;

    public BillPayPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    By billPayLink = By.linkText("Bill Pay");

    By payeeName = By.name("payee.name");
    By address = By.name("payee.address.street");
    By city = By.name("payee.address.city");
    By state = By.name("payee.address.state");
    By zipCode = By.name("payee.address.zipCode");
    By phone = By.name("payee.phoneNumber");
    By account = By.name("payee.accountNumber");
    By verifyAccount = By.name("verifyAccount");
    By amount = By.name("amount");
    By sendPayment = By.xpath("//input[@value='Send Payment']");

    public void payBill() {

        // ✅ FIX 1: wait for Bill Pay link (prevents stale element)
        wait.until(ExpectedConditions.elementToBeClickable(billPayLink)).click();

        // optional safety wait (page load after click)
        wait.until(ExpectedConditions.visibilityOfElementLocated(payeeName));

        driver.findElement(payeeName).sendKeys("Current Bill");
        driver.findElement(address).sendKeys("Hyderabad");
        driver.findElement(city).sendKeys("Hyderabad");
        driver.findElement(state).sendKeys("Telangana");
        driver.findElement(zipCode).sendKeys("500001");
        driver.findElement(phone).sendKeys("9999999999");
        driver.findElement(account).sendKeys("12345");
        driver.findElement(verifyAccount).sendKeys("12345");
        driver.findElement(amount).sendKeys("500");
        driver.findElement(sendPayment).click();
    }
}