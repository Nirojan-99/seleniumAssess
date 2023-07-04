package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OrderSuccess {
    private final By title = By.xpath("//h1[contains(@class,'page-title')]");
    private final By continueBtn = By.xpath("//div[contains(@class,'buttons ')]/a");

    private final WebDriver driver;

    public OrderSuccess(WebDriver driver) {
        this.driver = driver;
    }

    public void clickContinue() {
        driver.findElement(continueBtn).click();
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }
}
