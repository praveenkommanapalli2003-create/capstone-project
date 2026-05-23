package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UpdateContactPage {

    WebDriver driver;
    WebDriverWait wait;

    public UpdateContactPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    String url = "https://parabank.parasoft.com/parabank/updateprofile.htm";

    By firstName = By.id("customer.firstName");
    By lastName = By.id("customer.lastName");
    By address = By.id("customer.address.street");
    By city = By.id("customer.address.city");
    By zip = By.id("customer.address.zipCode");
    By phone = By.id("customer.phoneNumber");
    By updateBtn = By.xpath("//input[@value='Update Profile']");

    public void openPage() {
        driver.get(url);
    }

    public void updateContact(String fn, String ln, String addr,
                              String cityName, String zipCode, String ph) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).clear();
        driver.findElement(firstName).sendKeys(fn);

        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(ln);

        driver.findElement(address).clear();
        driver.findElement(address).sendKeys(addr);

        driver.findElement(city).clear();
        driver.findElement(city).sendKeys(cityName);

        driver.findElement(zip).clear();
        driver.findElement(zip).sendKeys(zipCode);

        driver.findElement(phone).clear();
        driver.findElement(phone).sendKeys(ph);

        driver.findElement(updateBtn).click();
    }
}