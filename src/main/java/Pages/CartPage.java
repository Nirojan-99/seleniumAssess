package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage {
    private final WebDriver driver;
    private final By productName = By.cssSelector(".text-left a");
    private final By incrementInput = By.xpath("//div[@class='table-responsive']//tbody/tr//div[contains(@class,'input-group')]/input");
    private final By updateBtn = By.xpath("//div[@class='table-responsive']//tbody/tr//button[@type='submit']");
    private final By removeBtn = By.xpath("//div[@class='table-responsive']//tbody/tr//button[@type='button']");
    private final By productRow = By.xpath("//div[@class='table-responsive']//tbody/tr");
    private final By checkoutBtn = By.xpath("//a[text()='Checkout']");
    private final By total = By.xpath("(//div[contains(@class,'col-md-4')]//tr)[2]//strong");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getProductCount() {
        return driver.findElements(productRow).size();
    }

    public List<WebElement> getProducts() {
        return driver.findElements(productRow);
    }

    public String getProductName(WebElement element) {
        return element.findElement(productName).getText();
    }

    public void enterCount(int index, String val) {
        driver.findElements(incrementInput).get(index).clear();
        driver.findElements(incrementInput).get(index).sendKeys(val);
    }

    public String getCount(int index) {
        return driver.findElements(incrementInput).get(index).getAttribute("value");
    }

    public void clickUpdate(int index) {
        driver.findElements(updateBtn).get(index).click();
    }

    public CheckoutPage clickCheckout() {
        driver.findElement(checkoutBtn).click();
        return new CheckoutPage(driver);
    }

    public CartPage clickRemove(int index) {
        driver.findElements(removeBtn).get(index).click();
        return new CartPage(driver);
    }

    public float getTotal() {
        String val = driver.findElement(total).getText();
        return Float.parseFloat(String.join("", val.split("\\$")[1].split(",")));
    }

    public float calTotal() {
        float total = 0;
        for (WebElement element : getProducts()) {
            String val = element.findElements(By.tagName("td")).get(5).getText();
            float rowTotal = Float.parseFloat(String.join("", val.split("\\$")[1].split(",")));
            total += rowTotal;
        }
        return total;
    }

}
