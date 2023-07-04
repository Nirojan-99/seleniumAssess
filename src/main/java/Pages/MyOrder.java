package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MyOrder {
    private final WebDriver driver;

    private final By pageTitle = By.className("page-title");
    private final By productData = By.tagName("td");
    private final By orderRow = By.xpath("//div[@class='table-responsive']//tbody/tr");

    public MyOrder(WebDriver driver) {
        this.driver = driver;
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public List<WebElement> getProducts() {
        return driver.findElements(orderRow);
    }

    public List<WebElement> getProductData(WebElement elm) {
        return elm.findElements(productData);
    }

    public OrderProductDetails clickViewBtn(WebElement elm) {
        elm.findElement(By.tagName("a")).click();
        return new OrderProductDetails(driver);
    }

}
