package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class EditAccount {
    private final WebDriver driver;
    private final By firstName = By.name("firstname");
    private final By lastName = By.name("lastname");
    private final By email = By.name("email");
    private final By mobileNumber = By.name("telephone");
    private final By submitBtn = By.xpath("//input[@type='submit']");
    private final By errorMsg = By.xpath("//div[@class='text-danger']");


    public EditAccount(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String val) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(val);
    }

    public void enterLastName(String val) {
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(val);
    }

    public void enterEmail(String val) {
        driver.findElement(email).clear();
        driver.findElement(email).sendKeys(val);
    }

    public void enterNumber(String val) {
        driver.findElement(mobileNumber).clear();
        driver.findElement(mobileNumber).sendKeys(val);
    }

    public String getFirstName() {
        return driver.findElement(firstName).getAttribute("value");
    }

    public String getLastName() {
        return driver.findElement(lastName).getAttribute("value");
    }

    public String getEmail() {
        return driver.findElement(email).getAttribute("value");
    }

    public String getNumber() {
        return driver.findElement(mobileNumber).getAttribute("value");
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }

    public List<WebElement> getErrorMessage() {
        return driver.findElements(errorMsg);
    }
}
