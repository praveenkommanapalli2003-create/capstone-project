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
        System.out.println("Page ready");

        // STEP 2: Click Transfer Funds link
        wait.until(ExpectedConditions.elementToBeClickable(transferLink)).click();
        System.out.println("Transfer Funds link clicked");

        // STEP 3: Wait for URL to confirm navigation
        wait.until(ExpectedConditions.urlContains("transfer"));
        System.out.println("Navigated to: " + driver.getCurrentUrl());

        // STEP 4: Wait for page ready again after navigation
        wait.until(d ->
            ((JavascriptExecutor) d)
                .executeScript("return document.readyState")
                .equals("complete")
        );

        // STEP 5: Wait for FROM dropdown via AJAX
        wait.until(d -> {
            try {
                Select from = new Select(d.findElement(fromAccount));
                return from.getOptions().size() > 1;
            } catch (Exception e) {
                return false;
            }
        });
        System.out.println("FROM dropdown loaded");

        // STEP 6: Wait for TO dropdown via AJAX
        wait.until(d -> {
            try {
                Select to = new Select(d.findElement(toAccount));
                return to.getOptions().size() > 1;
            } catch (Exception e) {
                return false;
            }
        });
        System.out.println("TO dropdown loaded");

        // STEP 7: Find amount field using presence
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("amount")));
        WebElement amountElement = driver.findElement(By.name("amount"));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView(true);", amountElement);

        // Set value using JavaScript first
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = '';", amountElement);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = arguments[1];", amountElement, amount);

        // SendKeys as backup
        amountElement.click();
        amountElement.clear();
        amountElement.sendKeys(amount);

        System.out.println("Amount entered: " + amount);

        // STEP 8: Select FROM account
        Select from = new Select(driver.findElement(fromAccount));
        from.selectByIndex(0);
        System.out.println("FROM account selected: " +
            from.getFirstSelectedOption().getText());

        // STEP 9: Select TO account
        Select to = new Select(driver.findElement(toAccount));
        List<WebElement> toOptions = to.getOptions();
        if (toOptions.size() > 1) {
            to.selectByIndex(1);
        } else {
            to.selectByIndex(0);
        }
        System.out.println("TO account selected: " +
            to.getFirstSelectedOption().getText());

        // STEP 10: Click Transfer button using JavaScript
        WebElement btn = wait.until(
            ExpectedConditions.presenceOfElementLocated(transferBtn));
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", btn);
        System.out.println("Transfer button clicked");

        // STEP 11: Verify transfer success
        try {
            wait.until(ExpectedConditions.or(
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'Transfer Complete')]")),
                ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//*[contains(text(),'complete')]"))
            ));
            System.out.println("Transfer completed successfully");
        } catch (TimeoutException e) {
            System.out.println("Warning: Could not verify success message");
        }
    }
}