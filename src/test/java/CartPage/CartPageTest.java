package CartPage;

import Base.BaseTest;
import Pages.*;
import TestData.KeyConstants;
import TestData.TestDescription;
import Utils.JsonUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CartPageTest extends BaseTest {
    private String validEmail;
    private String validPassword;

    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
        validPassword = JsonUtil.getJsonValue(KeyConstants.VALID_PASSWORD);
        validEmail = JsonUtil.getJsonValue(KeyConstants.VALID_EMAIL);
    }

    @AfterClass
    public void afterClass() {
        homePage.clickLogout();
    }

    @Test(priority = -1)
    public void testCartEmptyInitially() {
        CartModel cartPage = homePage.clickCart();
        Assert.assertTrue(cartPage.isCartEmpty(), TestDescription.CART_NOT_EMPTY_DES);

        homePage.closeCart();
    }

    @Test
    public void login() {
        LoginPage loginPage = homePage.clickLogin();
        System.out.println(loginPage);
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);
        loginPage.clickSubmit();
    }

    @Test(dependsOnMethods = "login")
    public void testAddToCart() {
        ProductListPage productListPage = homePage.clickHeadphones();

        int index = 1;
        String productName = productListPage.getProductName(productListPage.getProductLayouts().get(index));

        ProductDetailsPage productDetailsPage = productListPage.clickProduct(index);
        productDetailsPage.clickAddToCart();

        homePage.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        homePage.closeCartToast();

        CartModel cartPage = homePage.clickCart();

        homePage.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        CartPage cartPage1 = cartPage.clickCartPageBtn();
        List<WebElement> elements = cartPage1.getProducts();
        List<String> titles = new ArrayList<>();

        for (WebElement element : elements) {
            String title = cartPage1.getProductName(element);
            titles.add(title);

        }

        Assert.assertTrue(titles.contains(productName), TestDescription.PRODUCT_ADDED_TO_CART_ERROR_DES);

    }

    @Test(dependsOnMethods = "login")
    public void testAddMultipleProductToCart() {
        ProductListPage productListPage = homePage.clickHeadphones();

        int[] index = new int[]{3, 5};

        for (int i : index) {
            String productName = productListPage.
                    getProductName(productListPage.getProductLayouts().get(i));

            ProductDetailsPage productDetailsPage = productListPage.clickProduct(i);
            String availability = productDetailsPage.getAvailability();

            if (!availability.toLowerCase().contains("in stock")) {
                continue;
            }
            productDetailsPage.clickAddToCart();

            homePage.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            homePage.closeCartToast();

            CartModel cartPage = homePage.clickCart();

            homePage.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            CartPage cartPage1 = cartPage.clickCartPageBtn();
            List<WebElement> elements = cartPage1.getProducts();
            List<String> titles = new ArrayList<>();

            for (WebElement element : elements) {
                String title = cartPage1.getProductName(element);
                titles.add(title);
            }

            System.out.println(titles);
            System.out.println(productName);

            Assert.assertTrue(titles.contains(productName), TestDescription.PRODUCT_ADDED_TO_CART_ERROR_DES);

            homePage.clickHeadphones();

        }

    }

    @Test(dependsOnMethods = {"login", "testAddToCart", "testAddMultipleProductToCart"})
    public void testIncrementCartItem() {
        CartModel cartModel = homePage.clickCart();

        WebDriverWait wait = new WebDriverWait(homePage.driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(cartModel.getCartPageBtn()));

        CartPage cartPage = cartModel.clickCartPageBtn();
        int index = 0;
        int increValue = 4;
        int decreValue = 1;
//        List<WebElement> elements = cartPage.getProducts();

        cartPage.enterCount(index, String.valueOf(increValue));
        cartPage.clickUpdate(index);

        Assert.assertEquals(cartPage.getCount(index), String.valueOf(increValue), TestDescription.CART_UPDATED_ERROR_DES);

        cartPage.enterCount(index, String.valueOf(decreValue));
        cartPage.clickUpdate(index);

        Assert.assertEquals(cartPage.getCount(index), String.valueOf(decreValue), TestDescription.CART_UPDATED_ERROR_DES);
    }

    @Test(dependsOnMethods = {"login", "testAddToCart", "testAddMultipleProductToCart", "testTotalAmount"})
    public void testRemoveProductFromCart() {
        CartModel cartModel = homePage.clickCart();

        WebDriverWait wait = new WebDriverWait(homePage.driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(cartModel.getCartPageBtn()));

        CartPage cartPage = cartModel.clickCartPageBtn();

        int count = cartPage.getProducts().size();
        int index = 0;

        cartPage.enterCount(index, String.valueOf(0));
        cartPage.clickUpdate(index);

        int countFinal = cartPage.getProducts().size();

        Assert.assertEquals(count - 1, countFinal, TestDescription.PRODUCT_NOT_REMOVED);
    }

    @Test(dependsOnMethods = {"login", "testAddToCart", "testAddMultipleProductToCart"})
    public void testTotalAmount() {
        CartModel cartModel = homePage.clickCart();

        WebDriverWait wait = new WebDriverWait(homePage.driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(cartModel.getCartPageBtn()));

        CartPage cartPage = cartModel.clickCartPageBtn();

        float total = cartPage.getTotal();
        float calTotal = cartPage.calTotal();

        Assert.assertEquals(total, calTotal, TestDescription.TOTAL_MISMATCH_DES);
    }

    @Test(dependsOnMethods = {"login", "testAddToCart", "testAddMultipleProductToCart"})
    public void testTotalUpdatesWhenProductIncrease() {
        CartModel cartModel = homePage.clickCart();

        WebDriverWait wait = new WebDriverWait(homePage.driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(cartModel.getCartPageBtn()));

        CartPage cartPage = cartModel.clickCartPageBtn();
        int index = 0;

        float preTotal = cartPage.getTotal();
        float preCalTotal = cartPage.calTotal();

        cartPage.enterCount(index, String.valueOf(10));

        float total = cartPage.getTotal();
        float calTotal = cartPage.calTotal();

        Assert.assertTrue(preTotal <= total, TestDescription.TOTAL_NOT_UPDATED);
        Assert.assertTrue(preCalTotal <= calTotal, TestDescription.CALCULATED_TOTAL_NOT_UPDATED);
    }


}
