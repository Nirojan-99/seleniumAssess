package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderProductDetails {
    private final By detailsRow = By.xpath("//table[contains(@class,'table table-bordered table-hover')]//tbody/tr");
    private final By cell = By.xpath("//table[contains(@class,'table table-bordered table-hover')]//tbody/tr/td");
    private final By total = By.xpath("//table[contains(@class,'table table-bordered table-hover')]//tfoot/tr[3]/td[3]");

    private final WebDriver driver;

    public OrderProductDetails(WebDriver driver) {
        this.driver = driver;
    }

    public String getOrderID() {
        return driver.findElements(cell).get(0).getText();
    }

    public String getTotal() {
        return driver.findElement(total).getText();
    }
}
