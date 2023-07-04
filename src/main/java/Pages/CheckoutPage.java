package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {
    private final WebDriver driver;
    private final By firstName = By.name("firstname");
    private final By lastName = By.name("lastname");
    private final By company = By.name("company");
    private final By address1 = By.name("address_1");
    private final By city = By.name("city");
    private final By country = By.name("country_id");
    private final By region = By.id("input-payment-zone");
    private final By invalidFeedback = By.xpath("//div[contains(@class,'invalid-feedback')]");
    private final By agreeBtn = By.xpath("//label[@class='custom-control-label' and @for='input-agree']");
    private final By continueBtn = By.xpath("//button[@type='submit' and contains(text(),'Continue')]");
    private final By selectNewAddress = By.xpath("//label[@for='input-payment-address-new']");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFirstname(String val) {
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(val);
    }

    public void enterCompany(String val) {
        driver.findElement(company).clear();
        driver.findElement(company).sendKeys(val);
    }

    public void enterAddress(String val) {
        driver.findElement(address1).clear();
        driver.findElement(address1).sendKeys(val);
    }

    public void enterCity(String val) {
        driver.findElement(city).clear();
        driver.findElement(city).sendKeys(val);
    }

    public void enterCountry(String val) {
        Select select = new Select(driver.findElement(country));
        select.selectByVisibleText(val);
    }

    public void clickAgreeBtn() {
        driver.findElement(agreeBtn).click();
    }

    public ConfirmOrder clickContinueBtn() {
        driver.findElement(continueBtn).click();
        driver.findElement(continueBtn).click();
        return new ConfirmOrder(driver);
    }

    public void selectRegion(String val){
        Select select = new Select(driver.findElement(region));
        select.selectByVisibleText(val);

    }

    public boolean isErrorMessageDisplayed(){
        return driver.findElement(invalidFeedback).isDisplayed();
    }
    public void selectNewAddress(){
        driver.findElement(selectNewAddress).click();
    }
}
