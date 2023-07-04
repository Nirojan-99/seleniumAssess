package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage {
    public WebDriver getDriver() {
        return driver;
    }

    private final WebDriver driver;
    private final By productTitle = By.xpath("//h1[@class='h3']");
    private final By productPrice = By.xpath("//div[@class='price']");
    private final By productDescription = By.className("tab-content");
    private final By addToCart = By.xpath("//div[@id='entry_216842']/button[@title='Add to Cart']");
    private final By availability = By.xpath("(//div[@id='entry_216826']/ul/li)[last()]/span[2]");

    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getProductTitle() {
        return driver.findElement(productTitle).getText();
    }

    public String getProductPrice() {
        return driver.findElement(productPrice).getText();
    }

    public String getProductDescription() {
        return driver.findElement(productDescription).getText();
    }

    public WebElement getProductDescriptionEle() {
        return driver.findElement(productDescription);
    }

    public void clickAddToCart() {
        driver.findElement(addToCart).click();
    }

    public String getAvailability() {
        return driver.findElement(availability).getText();
    }
}
