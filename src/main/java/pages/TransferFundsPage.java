package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait for amount field (THIS FIXES YOUR ERROR)
        WebElement amountBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(amountField)
        );

        amountBox.clear();
        amountBox.sendKeys(amount);

        // Wait for FROM dropdown
        WebElement fromDrop = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromAccount)
        );

        Select from = new Select(fromDrop);
        from.selectByIndex(0);

        // Wait for TO dropdown
        WebElement toDrop = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toAccount)
        );

        Select to = new Select(toDrop);

        if (to.getOptions().size() > 1) {
            to.selectByIndex(1);
        } else {
            to.selectByIndex(0);
        }

        // Wait and click transfer button
        WebElement transfer = wait.until(
                ExpectedConditions.elementToBeClickable(transferBtn)
        );

        transfer.click();
    }
}