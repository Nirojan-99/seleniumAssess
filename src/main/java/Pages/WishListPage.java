package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class WishListPage {

    private final WebDriver driver;
    private final By productName = By.xpath("//tr/td[@class='text-left']/a");
    private final By removeBtn = By.xpath("//tr/td[contains(@class,'text-right')]/a");
    private final By productRow = By.xpath("//div[@class='table-responsive']//tbody/tr");
    private final By toastMsg = By.xpath("//div[@id='notification-box-top']/div");
    private final By toastMsgContainer = By.xpath("//div[@id='notification-box-top']");
    private final By noContent = By.xpath("//div[@id='content']//p");
    private final By toastContainer = By.xpath("//div[contains(@id,'notification-box-top')]/div");

    public WishListPage(WebDriver driver) {
        this.driver = driver;
    }

    public List<String> getProducts() {
        List<String> names = new ArrayList<>();
        for (WebElement elm : driver.findElements(productName)) {
            names.add(elm.getText());
        }
        return names;
    }

    public boolean isToastDisplayed() {
        return driver.findElement(toastMsg).isDisplayed();
    }

    public WebElement getToastDiv() {
        return driver.findElement(toastContainer);
    }

    public void clickRemoveBtn(int index) {
        driver.findElements(removeBtn).get(index).click();
    }

    public int getProductCount() {
        return driver.findElements(productRow).size();
    }
    public WebElement getToastContainer(){
        return driver.findElement(toastMsgContainer);
    }

    public boolean isNoContent(){
        return driver.findElement(noContent).getText().contains("No results!");
    }

}
