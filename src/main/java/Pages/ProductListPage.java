package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.text.ParseException;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductListPage {
    public WebDriver driver;
    private final By sortDropdown = By.id("input-sort-212403");
    private final By productLayout = By.xpath("//div[contains(@class,'product-layout')]");
    public final By productName = By.className("text-ellipsis-2");
    public final By productPrice = By.className("price-new");
    private final By productImage = By.className("lazy-load");
    private final By priceMinFilter = By.name("mz_fp[min]");
    private final By priceMaxFilter = By.name("mz_fp[max]");
    private final By cartBtn = By.xpath("//button[@title='Add to Cart']/i[contains(@class,'fa-shopping-cart')]");
    private final By wishlistSuccessMsg = By.xpath("//div[@id='notification-box-top']");
    private final By nextPage = By.xpath("(//a[@class='page-link' and text()=\">\"])");
    private final By countDropdown = By.id("input-limit-212402");
    private final By paginationIndicator = By.xpath("//div[contains(@class,'col-sm-6 text-right')]");
    private final By inStockCheckbox = By.xpath("//label[@for='mz-fss-0--1']");
//    TODO

    public ProductListPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSortDropdown(String attribute) {
        Select dropdown = new Select(driver.findElement(sortDropdown));
        dropdown.selectByVisibleText(attribute);
    }

    public List<WebElement> getProductLayouts() {
        return driver.findElements(productLayout);
    }

    public String getProductName(WebElement element) {
        return element.findElement(productName).getText();
    }

    public Boolean isProductNameDisplayed(WebElement element) {
        return element.findElement(productName).isDisplayed();
    }

    public Float getProductPrice(WebElement element) throws ParseException {
        String numberString = element.findElement(productPrice).getText().split("\\$")[1];
        String val = String.join("", numberString.split(","));
        return Float.parseFloat(val);
    }

    public Boolean isProductPriceDisplayed(WebElement element) {
        return element.findElement(productPrice).isDisplayed();
    }

    public Boolean isProductImageDisplayed(WebElement element) {
        return element.findElement(productImage).isDisplayed();
    }

    public void enterMinFilterValue(String val) {
        driver.findElement(priceMinFilter).sendKeys(val);
    }

    public void enterMaxFilterValue(String val) {
        driver.findElement(priceMaxFilter).sendKeys(val);
    }

    public ProductDetailsPage clickProduct(int index) {
        WebElement element = driver.findElements(productLayout).get(index);
        element.click();
        return new ProductDetailsPage(driver);
    }


    public void hoverProduct(int index) {
        WebElement product = getProductLayouts().get(index);
        Actions actions = new Actions(driver);
        actions.moveToElement(product).perform();
    }

    public void clickAddToCart(int index) {
        WebElement button = driver.findElement(By.xpath("(//button[@title='Add to Cart'][i[contains(@class,'fa-shopping-cart')]])[" + (index + 1) + "]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", button);
        button.click();
    }

    public WebElement getCartBtnEle(int index) {
        return driver.findElement(By.xpath("(//button[@title='Add to Cart'][i[contains(@class,'fa-shopping-cart')]])[" + (index + 1) + "]"));
    }

    public void clickWishlistIcon(int index) {

        WebElement favIcon = driver.findElement(By.
                xpath("(//button[@title='Add to Wish List'][i[contains(@class,'fas fa-heart')]])[" + (index + 1) + "]"));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(favIcon));

        favIcon.click();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    public void clickNextPage() {
        driver.findElement(nextPage).click();
    }

    public String getSelectedItemCountPerPage() {
        Select dropdown = new Select(driver.findElement(countDropdown));
        List<WebElement> element = dropdown.getAllSelectedOptions();
        return element.get(0).getText();
    }

    public void selectItemCountPerPage(String count) {
        Select dropdown = new Select(driver.findElement(countDropdown));
        dropdown.selectByVisibleText(count);
    }

    public String getPaginationIndicatorText() {
        return driver.findElement(paginationIndicator).getText();
    }

    public void clickPage(int index) {
        By btn = By.xpath("//a[@class='page-link' and text()='" + index + "']");
        driver.findElement(btn).click();
    }

    public void clickInStock() {
        driver.findElement(inStockCheckbox).click();
    }
}
