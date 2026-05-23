package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TransferFundsPage {

    WebDriver driver;
    WebDriverWait wait;

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    By amountField = By.id("amount"); // ⚠️ verify this in UI
    By fromAccount = By.id("fromAccountId");
    By toAccount = By.id("toAccountId");
    By transferBtn = By.xpath("//input[@value='Transfer']");

    public void transferFunds(String amount) {

        // Wait for page ready
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        // 🔥 FIX 1: wait for VISIBILITY instead of presence
        WebElement amountElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(amountField)
        );

        amountElement.clear();
        amountElement.sendKeys(amount);

        // FROM account
        WebElement fromElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromAccount)
        );
        Select from = new Select(fromElement);
        from.selectByIndex(0);

        // TO account
        WebElement toElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toAccount)
        );
        Select to = new Select(toElement);

        if (to.getOptions().size() > 1) {
            to.selectByIndex(1);
        } else {
            to.selectByIndex(0);
        }

        // FIX 2: safe click
        wait.until(ExpectedConditions.elementToBeClickable(transferBtn)).click();
    }
}