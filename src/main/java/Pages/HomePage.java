package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final By dropDown = By.xpath("//a[contains(@href,'/account') and @role='button']");
    private final By menuDropDown = By.xpath("//a[contains(@href,'route=information/information&information_id=4') and @role='button']");
    private final By registerBtn = By.xpath("//ul[contains(@class,'mz-sub-menu-96')]/li/a[contains(@href,'/register')]");
    private final By headphonesBtn = By.xpath("//a[@title=\"Headphones\"]");
    private final By loginBtn = By.xpath("//ul[contains(@class,'mz-sub-menu-96')]/li/a[contains(@href,'/login')]");
    private final By logoutBtn = By.xpath("//ul[contains(@class,'mz-sub-menu-96')]/li/a[contains(@href,'/logout')]");
    private final By orderBtn = By.xpath("//ul[contains(@class,'mz-sub-menu-96')]/li/a[contains(@href,'/order')]");
    public WebDriver driver;
    private final By cartBtn = By.xpath("//div[@id=\"entry_217825\"]/a[@role=\"button\" and contains(@href,\"cart\")]/div/div");
    private final By wishlistBtn = By.xpath("//div[@id='entry_217824']/a[contains(@href,'wishlist')]");
    private final By toastClose = By.xpath("//span[@aria-hidden=\"true\"]");
    private final By search = By.name("search");
    private final By searchBtn = By.xpath("//button[@type='submit' and text()='Search']");
    private final By closeCartBtn = By.xpath("//div[contains(@class,'mz-pure-overlay')]");
    private final By closeCartToast = By.xpath("//div[contains(@id,'notification-box-top')]/div");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public CartModel clickCart() {
        driver.findElement(cartBtn).click();
        return new CartModel(driver);
    }

    public void hoverDropDown() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(dropDown)).perform();
    }

    public void hoverMenuDropDown() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(menuDropDown)).perform();
    }


    public RegisterPage clickRegister() {
        hoverDropDown();
        if (isRegisterButtonDisplayed()) {
            driver.findElement(registerBtn).click();
            return new RegisterPage(driver);
        } else {
            return null;
        }

    }

    public void clickLogout() {
        hoverDropDown();
        driver.findElement(logoutBtn).click();
    }

    public LoginPage clickLogin() {
        hoverDropDown();
        driver.findElement(loginBtn).click();
        return new LoginPage(driver);
    }

    public ProductListPage clickHeadphones() {
        hoverMenuDropDown();
        driver.findElement(headphonesBtn).click();
        return new ProductListPage(driver);
    }

    public boolean isRegisterButtonDisplayed() {
        return driver.findElement(registerBtn).isDisplayed();
    }

    public boolean isLogoutButtonDisplayed() {
        hoverDropDown();
        return driver.findElement(logoutBtn).isDisplayed();
    }

    public ContactUsPage clickContactUs() {
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=information/contact");
        return new ContactUsPage(driver);
    }

    public WishListPage clickWishListIcon() {
        driver.findElement(wishlistBtn).click();
        return new WishListPage(driver);
    }

    public void closeToast() {
        for (WebElement element : driver.findElements(toastClose)) {
            element.click();
        }
    }

    public void enterSearch(String val) {
        driver.findElement(search).clear();
        driver.findElement(search).sendKeys(val);
    }

    public void clickSearchBtn() {
        driver.findElement(searchBtn).click();
    }

    public void closeCart() {
        driver.findElement(closeCartBtn).click();
    }

    public MyOrder clickOrderBtn() {
        driver.findElement(orderBtn).click();
        return new MyOrder(driver);
    }

    public void closeCartToast() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(closeCartToast)));
    }


}
