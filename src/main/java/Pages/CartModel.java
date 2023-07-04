package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class CartModel {
    private final WebDriver driver;
    private final By emptyText = By.xpath("//div[@id=\"entry_217847\"]/p[contains(@class,\"m-0 py-5 text-center\")]");
    private final By cartRow = By.xpath("//div/table[@class=\"table\"]//tr");
    private final By productTitle = By.xpath("//tr/td/a[@data-original-title]");
    private final By productRow = By.xpath("//div[contains(@class,'table-responsive')]//tbody/tr");
    private final By cartPageBtn = By.xpath("//div[@id='entry_217850']/a[contains(@href,'cart')]");

    public CartModel(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isCartEmpty() {
        return driver.findElement(emptyText).getText().contains("Your shopping cart is empty!");
    }

    public boolean isCartProductRowDisplayed() {
        return driver.findElement(cartRow).isDisplayed();
    }

    public List<WebElement> getCartRow(){
        return driver.findElements(productRow);
    }

    public List<String> getProductName() {
        List<String> products = new ArrayList<>();

        for (WebElement element : driver.findElements(productTitle)) {
            products.add(element.getAttribute("data-original-title"));
        }

        return products;
    }

    public CartPage clickCartPageBtn() {
        driver.findElement(cartPageBtn).click();
        return new CartPage(driver);
    }

    public WebElement getCartPageBtn() {
        return driver.findElement(cartPageBtn);
    }
}
