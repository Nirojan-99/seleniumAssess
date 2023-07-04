package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class RegisterPage {
    private final WebDriver driver;
    private final By firstName = By.name("firstname");
    private final By lastName = By.name("lastname");
    private final By email = By.name("email");
    private final By telephone = By.name("telephone");
    private final By password = By.name("password");
    private final By confirmPassword = By.name("confirm");
    private final By radioYes = By.xpath("//label[@class=\"custom-control-label\" and @for=\"input-newsletter-yes\"]");
    private final By radioNo = By.xpath("//label[@class=\"custom-control-label\" and @for=\"input-newsletter-no\"]");
    private final By agreeBtn = By.xpath("//label[@class=\"custom-control-label\" and @for=\"input-agree\"]");
    private final By continueBtn = By.xpath("//input[@value='Continue']");
    private final By errorAlert = By.xpath("//div[@class='text-danger']");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstName(String firstNameVal) {
        driver.findElement(firstName).sendKeys(firstNameVal);
    }

    public void enterLastName(String lastNameVal) {
        driver.findElement(lastName).sendKeys(lastNameVal);
    }

    public void enterEmail(String emailVal) {
        driver.findElement(email).sendKeys(emailVal);
    }

    public void enterPhoneNumber(String mobileNo) {
        driver.findElement(telephone).sendKeys(mobileNo);
    }

    public void enterPassword(String passwordVal) {
        driver.findElement(password).sendKeys(passwordVal);
    }

    public void enterConfirmPassword(String confirmPasswordVal) {
        driver.findElement(confirmPassword).sendKeys(confirmPasswordVal);
    }

    public void setSubscription(Boolean val) {
        if (val) {
            driver.findElement(radioYes).click();
        } else {
            driver.findElement(radioNo).click();
        }

    }

    public void agreePrivacyPolicy() {
        driver.findElement(agreeBtn).click();
    }

    public RegisterSuccessPage clickContinue() {
        driver.findElement(continueBtn).click();
        return new RegisterSuccessPage(driver);
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(errorAlert).isDisplayed();
    }

    public List<String> getErrorMessage() {
        List<String> texts = new ArrayList<>();
        for (WebElement elems : driver.findElements(errorAlert)) {
            texts.add(elems.getText());
        }
        return texts;

    }
}
