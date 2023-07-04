package WishList;

import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;
import Pages.ProductListPage;
import Pages.WishListPage;
import TestData.KeyConstants;
import TestData.TestDescription;
import Utils.JsonUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.List;

public class WishListTest extends BaseTest {

    private String productName;
    private String validPassword;
    private String validEmail;
    private WishListPage wishListPage;

    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
        validPassword = JsonUtil.getJsonValue(KeyConstants.VALID_PASSWORD);
        validEmail = JsonUtil.getJsonValue(KeyConstants.VALID_EMAIL);
        wishListPage = new WishListPage(homePage.driver);
    }

    @AfterClass
    public void afterClass() {
        homePage.clickLogout();
    }

    @Test
    public void login() {
        LoginPage loginPage = homePage.clickLogin();

        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);

        loginPage.clickSubmit();
    }

    @Test(dependsOnMethods = "login", priority = -1)
    public void addToWishlist() {
        ProductListPage productListPage = homePage.clickHeadphones();

        int index = 5;
        productName = productListPage.getProductName(productListPage.getProductLayouts().get(index));

        productListPage.hoverProduct(index);

        productListPage.clickWishlistIcon(index);

        Assert.assertTrue(wishListPage.isToastDisplayed(), TestDescription.ERROR_MESSAGE_WISHLIST_DES);

    }

    @Test(dependsOnMethods = {"addToWishlist", "login"})
    public void testProductAddedToWishlist() {
        homePage.closeToast();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.invisibilityOf(wishListPage.getToastDiv()));

        wishListPage = homePage.clickWishListIcon();
        List<String> products = wishListPage.getProducts();

        Assert.assertTrue(products.contains(productName), TestDescription.ERROR_MESSAGE_WISHLIST_DES);
    }

    @Test(dependsOnMethods = {"testProductAddedToWishlist", "addToWishlist", "login", "testSession"})
    public void testRemoveProduct() {
        int index = 0;

        int count = wishListPage.getProductCount();

        if (count >= index + 1) {
            wishListPage.clickRemoveBtn(index);
            if (count == 1) {
                Assert.assertTrue(wishListPage.isNoContent(), TestDescription.REMOVE_PRODUCT_ERROR_DES);
            } else {
                int postCount = wishListPage.getProductCount();
                Assert.assertEquals(postCount, count - 1, TestDescription.REMOVE_PRODUCT_ERROR_DES);
            }

        }


    }

    @Test(dependsOnMethods = {"testProductAddedToWishlist", "addToWishlist", "login"})
    public void testSession() {
        WebDriver newDriver = homePage.driver.switchTo().newWindow(WindowType.TAB);
        newDriver.get("https://ecommerce-playground.lambdatest.io/");

        HomePage homePage1 = new HomePage(newDriver);

        WishListPage wishListPage1 = homePage1.clickWishListIcon();

        List<String> productsName = wishListPage1.getProducts();

        Assert.assertTrue(productsName.contains(productName), TestDescription.ERROR_MESSAGE_WISHLIST_DES);
    }
}
