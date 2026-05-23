package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

public class TransferFundsPage {

    WebDriver driver;
    WebDriverWait wait;

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }

    By transferLink = By.linkText("Transfer Funds");
    By amountField  = By.name("amount");
    By fromAccount  = By.id("fromAccountId");
    By toAccount    = By.id("toAccountId");
    By transferBtn  = By.xpath("//input[@value='Transfer']");

    public void transferFunds(String amount) {

        // STEP 1: Wait for page ready
        wait.until(d ->
            ((JavascriptExecutor) d)
                .executeScript("return document.readyState")
                .equals("complete")
        );

        // STEP 2: Click Transfer Funds link
        wait.until(ExpectedConditions.elementToBeClickable(transferLink)).click();

        // STEP 3: Wait for URL to confirm navigation
        wait.until(ExpectedConditions.urlContains("transfer"));
        System.out.println("Navigated to: " + driver.getCurrentUrl());

        // STEP 4: Wait for FROM dropdown to be populated via AJAX
        wait.until(d -> {
            try {
                Select from = new Select(d.findElement(fromAccount));
                return from.getOptions().size() > 1;
            } catch (Exception e) {
                return false;
            }
        });
        System.out.println("FROM dropdown loaded");

        // STEP 5: Wait for TO dropdown to be populated via AJAX
        wait.until(d -> {
            try {
                Select to = new Select(d.findElement(toAccount));
                return to.getOptions().size() > 1;
            } catch (Exception e) {
                return false;
            }
        });
        System.out.println("TO dropdown loaded");

        // STEP 6: Wait for amount field (page fully ready now)
        WebElement amountElement = wait.until(
            ExpectedConditions.visibilityOfElementLocated(amountField)
        );
        amountElement.clear();
        amountElement.sendKeys(amount);
        System.out.println("Amount entered: " + amount);

        // STEP 7: Select FROM account
        Select from = new Select(driver.findElement(fromAccount));
        from.selectByIndex(0);
        System.out.println("FROM account selected");

        // STEP 8: Select TO account (different from FROM)
        Select to = new Select(driver.findElement(toAccount));
        List<WebElement> toOptions = to.getOptions();
        if (toOptions.size() > 1) {
            to.selectByIndex(1);
        } else {
            to.selectByIndex(0);
        }
        System.out.println("TO account selected");

        // STEP 9: Click Transfer button
        wait.until(ExpectedConditions.elementToBeClickable(transferBtn)).click();
        System.out.println("Transfer submitted successfully");
    }
}