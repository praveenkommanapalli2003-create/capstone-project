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

    // safer locators (ParaBank usually uses name attributes)
    By transferLink = By.linkText("Transfer Funds");

    By amountField = By.name("amount");   // FIXED (was id -> unstable)
    By fromAccount = By.id("fromAccountId");
    By toAccount = By.id("toAccountId");
    By transferBtn = By.xpath("//input[@value='Transfer']");

    public void transferFunds(String amount) {

        // STEP 1: Ensure page is fully loaded
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        // STEP 2: Navigate to Transfer Funds page safely
        wait.until(ExpectedConditions.elementToBeClickable(transferLink)).click();

        // STEP 3: Wait for correct page
        wait.until(ExpectedConditions.visibilityOfElementLocated(amountField));

        System.out.println("Current URL: " + driver.getCurrentUrl());

        // STEP 4: Enter amount
        WebElement amountElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(amountField)
        );
        amountElement.clear();
        amountElement.sendKeys(amount);

        // STEP 5: FROM account
        WebElement fromElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromAccount)
        );
        new Select(fromElement).selectByIndex(0);

        // STEP 6: TO account
        WebElement toElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(toAccount)
        );

        Select toSelect = new Select(toElement);
        if (toSelect.getOptions().size() > 1) {
            toSelect.selectByIndex(1);
        } else {
            toSelect.selectByIndex(0);
        }

        // STEP 7: Click transfer
        wait.until(ExpectedConditions.elementToBeClickable(transferBtn)).click();
    }
}