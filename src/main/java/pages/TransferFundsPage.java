package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransferFundsPage {

    WebDriver driver;

    public TransferFundsPage(WebDriver driver) {

        this.driver = driver;
    }

    By transferFundsLink =
    By.linkText("Transfer Funds");

    By amount =
    By.id("amount");

    By transferBtn =
    By.xpath("//input[@value='Transfer']");

    public void transferMoney() {

        driver.findElement(transferFundsLink)
        .click();

        driver.findElement(amount)
        .sendKeys("500");

        driver.findElement(transferBtn)
        .click();
    }
}