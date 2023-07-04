package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmOrder {
    private final WebDriver driver;
    private final By title = By.xpath("//h1[contains(@class,'page-title')]");
    private final By continueBtn = By.xpath("//button[contains(text(),'Confirm Order')]");

    public ConfirmOrder(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(title).getText();
    }

    public OrderSuccess clickContinueBtn() {
        driver.findElement(continueBtn).click();
        return new OrderSuccess(driver);
    }

    public WebElement getBtn(){
        return driver.findElement(continueBtn);
    }
}
