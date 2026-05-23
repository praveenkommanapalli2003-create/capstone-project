package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver) {

        this.driver = driver;
    }

    By username = By.name("username");

    By password = By.name("password");

    By loginBtn =
    By.xpath("//input[@value='Log In']");

    public void loginApplication(String user, String pass) {

    	WaitUtils.waitForElement(driver, username);

    	driver.findElement(username).sendKeys(user);

        driver.findElement(password).sendKeys(pass);

        driver.findElement(loginBtn).click();
    }
}