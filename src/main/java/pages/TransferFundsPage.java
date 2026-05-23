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

    public TransferFundsPage(WebDriver driver) {
        this.driver = driver;
    }

    By amountField = By.id("amount");
    By fromAccount = By.id("fromAccountId");
    By toAccount = By.id("toAccountId");
    By transferBtn = By.xpath("//input[@value='Transfer']");

    public void transferFunds(String amount) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Wait for page load complete
        wait.until(webDriver ->
                ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState")
                        .equals("complete")
        );

        // Wait for amount field and enter value
        WebElement amountElement = wait.until(
                ExpectedConditions.presenceOfElementLocated(amountField)
        );
        amountElement.clear();
        amountElement.sendKeys(amount);

        // Select FROM account
        Select from = new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(fromAccount)
        ));
        from.selectByIndex(0);

        // Select TO account
        Select to = new Select(wait.until(
                ExpectedConditions.visibilityOfElementLocated(toAccount)
        ));

        if (to.getOptions().size() > 1) {
            to.selectByIndex(1);
        } else {
            to.selectByIndex(0);
        }

        // Click Transfer button
        wait.until(ExpectedConditions.elementToBeClickable(transferBtn)).click();
    }
}