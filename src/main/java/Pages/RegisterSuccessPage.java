package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterSuccessPage {
    private final By continueBtn = By.xpath("//div[contains(@class,'buttons')]");
    private final By title = By.xpath("//h1[contains(@class,'page-title')]");
    private final WebDriver driver;

    public RegisterSuccessPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTileText() {
        return driver.findElement(title).getText();
    }

    public MyAccountPage clickContinue() {
        driver.findElement(continueBtn).click();
        return new MyAccountPage(driver);
    }
}
