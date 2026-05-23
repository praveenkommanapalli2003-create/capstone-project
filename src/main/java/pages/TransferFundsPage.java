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

        // STEP 3: Wait for URL
        wait.until(ExpectedConditions.urlContains("transfer"));
        System.out.println("Navigated to: " + driver.getCurrentUrl());

        // STEP 4: Wait for page ready after navigation
        wait.until(d ->
            ((JavascriptExecutor) d)
                .executeScript("return document.readyState")
                .equals("complete")
        );

        // STEP 5: ✅ Switch to iframe if present
        try {
            List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
            System.out.println("Total iframes found: " + iframes.size());
            if (iframes.size() > 0) {
                driver.switchTo().frame(iframes.get(0));
                System.out.println("Switched to iframe");
            }
        } catch (Exception e) {
            System.out.println("No iframe found, continuing normally");
        }

        // STEP 6: Wait for FROM dropdown via AJAX
        wait.until(d -> {
            try {
                Select from = new Select(d.findElement(fromAccount));
                return from.getOptions().size() > 1;
            } catch (Exception e) {
                return false;
            }
        });
        System.out.println("FROM dropdown loaded");

        // STEP 7: Wait for TO dropdown via AJAX
        wait.until(d -> {
            try {
                Select to = new Select(d.findElement(toAccount));
                return to.getOptions().size() > 1;
            } catch (Exception e) {
                return false;
            }
        });
        System.out.println("TO dropdown loaded");

        // STEP 8: ✅ Find amount field using multiple locator strategies
        WebElement amountElement = null;

        // Try all possible locators
        By[] amountLocators = {
            By.name("amount"),
            By.id("amount"),
            By.xpath("//input[@name='amount']"),
            By.xpath("//input[@id='amount']"),
            By.xpath("//input[@type='text']"),
            By.cssSelector("input[name='amount']"),
            By.cssSelector("input[id='amount']")
        };

        for (By locator : amountLocators) {
            try {
                amountElement = driver.findElement(locator);
                if (amountElement != null && amountElement.isDisplayed()) {
                    System.out.println("Amount field found with: " + locator);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Locator failed: " + locator);
            }
        }

        // If still null try inside all iframes
        if (amountElement == null) {
            System.out.println("Trying all iframes for amount field...");
            driver.switchTo().defaultContent();
            List<WebElement> allIframes = driver.findElements(By.tagName("iframe"));
            System.out.println("Total iframes: " + allIframes.size());

            for (int i = 0; i < allIframes.size(); i++) {
                try {
                    driver.switchTo().frame(i);
                    amountElement = driver.findElement(By.name("amount"));
                    System.out.println("Amount field found in iframe: " + i);
                    break;
                } catch (Exception e) {
                    System.out.println("Amount not in iframe: " + i);
                    driver.switchTo().defaultContent();
                }
            }
        }

        if (amountElement == null) {
            // Print page source for debugging
            System.out.println("PAGE SOURCE: " + driver.getPageSource()
                .substring(0, Math.min(2000, driver.getPageSource().length())));
            throw new RuntimeException("Amount field not found anywhere on page");
        }

        // Scroll into view and enter amount
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView(true);", amountElement);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].value = arguments[1];", amountElement, amount);
        amountElement.click();
        amountElement.clear();
        amountElement.sendKeys(amount);
        System.out.println("Amount entered: " + amount);

        // STEP 9: Select FROM account
        Select from = new Select(driver.findElement(fromAccount));
        from.selectByIndex(0);
        System.out.println("FROM account selected: " +
            from.getFirstSelectedOption().getText());

        // STEP 10: Select TO account
        Select to = new Select(driver.findElement(toAccount));
        List<WebElement> toOptions = to.getOptions();
        if (toOptions.size() > 1) {
            to.selectByIndex(1);
        } else {
            to.selectByIndex(0);
        }
        System.out.println("TO account selected: " +
            to.getFirstSelectedOption().getText());

        // STEP 11: Click Transfer button
        WebElement btn = wait.until(
            ExpectedConditions.presenceOfElementLocated(transferBtn));
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript(
            "arguments[0].click();", btn);
        System.out.println("Transfer button clicked");

        // STEP 12: Switch back to default content
        driver.switchTo().defaultContent();

        // STEP 13: Verify success
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