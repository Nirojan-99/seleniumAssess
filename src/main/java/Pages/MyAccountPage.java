package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyAccountPage {
    private final WebDriver driver;
    private final By titleText = By.cssSelector("#content h2");
    private final By editBtn = By.xpath("//a[contains(@href,'edit')]");
    private final By updateSuccess = By.xpath("//div[contains(@class,'alert-success')]");

    public MyAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitleText() {
        return driver.findElement(titleText).getText();
    }

    public EditAccount clickEditBtn(){
        driver.findElement(editBtn).click();
        return new EditAccount(driver);
    }

    public boolean isSuccessMsgDisplayed(){
        return driver.findElement(updateSuccess).isDisplayed();
    }
}
