package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class TransferFundsPage {

    WebDriver driver;

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
    }

    By amountField = By.id("amount");
    By fromAccount = By.id("fromAccountId");
    By toAccount = By.id("toAccountId");
    By transferBtn = By.xpath("//input[@value='Transfer']");

    public void transferFunds(String amount) {

        // Enter valid amount
        driver.findElement(amountField).clear();
        driver.findElement(amountField).sendKeys(amount);

        // Select FROM account
        Select from = new Select(driver.findElement(fromAccount));
        from.selectByIndex(0);

        // Select TO account (IMPORTANT: must be different)
        Select to = new Select(driver.findElement(toAccount));

        if (to.getOptions().size() > 1) {
            to.selectByIndex(1);   // different account
        } else {
            to.selectByIndex(0);   // fallback
        }

        // Click transfer
        driver.findElement(transferBtn).click();
    }
}