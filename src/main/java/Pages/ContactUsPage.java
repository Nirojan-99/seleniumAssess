package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactUsPage {
    private final WebDriver driver;
    private final By name = By.name("name");
    private final By email = By.name("email");
    private final By enquiry = By.name("enquiry");
    private final By submitBtn = By.xpath("//input[@type='submit']");
    private final By errorMsg = By.xpath("//div[@class='invalid-feedback']");
    private final By successMsg = By.xpath("(//div[@id='content']/p)[2]");

    public ContactUsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String nameVal) {
        driver.findElement(name).sendKeys(nameVal);
    }

    public void enterEmail(String emailVal) {
        driver.findElement(email).sendKeys(emailVal);
    }

    public void enterEnquiry(String enquiryVal) {
        driver.findElement(enquiry).sendKeys(enquiryVal);
    }

    public void clickSubmit() {
        driver.findElement(submitBtn).click();
    }

    public List<WebElement> getErrorMessages() {
        return driver.findElements(errorMsg);
    }

    public String getSuccessMsg(){
        return driver.findElement(successMsg).getText();
    }
}
