package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final By email = By.name("email");
    private final By password = By.name("password");
    private final By submitBtn = By.xpath("//input[@type='submit']");
    private final By alertDiv = By.xpath("//div[contains(@class,'alert alert-danger')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String emailVal) {
        driver.findElement(email).sendKeys(emailVal);
    }

    public void enterPassword(String passwordVal) {
        driver.findElement(password).sendKeys(passwordVal);
    }

    public MyAccountPage clickSubmit() {
        driver.findElement(submitBtn).click();
        return new MyAccountPage(driver);
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(alertDiv).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(alertDiv).getText();
    }
}
