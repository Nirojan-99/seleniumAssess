package Checkout;

import Base.BaseTest;
import Pages.*;
import TestData.KeyConstants;
import TestData.TestDescription;
import Utils.JsonUtil;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;

public class CheckoutTest extends BaseTest {

    private String validPassword;
    private String validEmail;
    private String address;
    private String city;
    private String country;
    private String company;
    private String region;

    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
        validPassword = JsonUtil.getJsonValue(KeyConstants.VALID_PASSWORD);
        validEmail = JsonUtil.getJsonValue(KeyConstants.VALID_EMAIL);
        region = JsonUtil.getJsonValue(KeyConstants.REGION);
        company = JsonUtil.getJsonValue(KeyConstants.COMPANY);
        country = JsonUtil.getJsonValue(KeyConstants.COUNTRY);
        city = JsonUtil.getJsonValue(KeyConstants.CITY);
        address = JsonUtil.getJsonValue(KeyConstants.ADDRESS_1);

        LoginPage loginPage = homePage.clickLogin();

        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);

        loginPage.clickSubmit();

        Assert.assertTrue(homePage.isLogoutButtonDisplayed(), TestDescription.LOGIN_FAILURE_DES);
    }

    @AfterClass
    public void afterClass(){
        homePage.clickLogout();
    }

    @Test(enabled = false)
    public void testCheckoutFlow() {
        ProductListPage productListPage = homePage.clickHeadphones();
        ProductDetailsPage productDetailsPage = productListPage.clickProduct(1);
        productDetailsPage.clickAddToCart();
        productDetailsPage.clickAddToCart();

        CartModel cartModel = homePage.clickCart();

        WebDriverWait wait = new WebDriverWait(homePage.driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(cartModel.getCartPageBtn()));

        CartPage cartPage = cartModel.clickCartPageBtn();
        CheckoutPage checkoutPage = cartPage.clickCheckout();

        checkoutPage.selectNewAddress();

        checkoutPage.enterAddress(address);
        checkoutPage.enterCity(city);
        checkoutPage.enterCompany(company);
        checkoutPage.enterCountry(country);
        checkoutPage.selectRegion(region);

        checkoutPage.clickAgreeBtn();

        ConfirmOrder confirmOrder = checkoutPage.clickContinueBtn();

        WebDriverWait wait1 = new WebDriverWait(homePage.driver, Duration.ofSeconds(5));
        wait1.until(ExpectedConditions.elementToBeClickable(confirmOrder.getBtn()));

        OrderSuccess orderSuccess = confirmOrder.clickContinueBtn();

        Assert.assertEquals(orderSuccess.getTitle(), TestDescription.ORDER_PLACED, TestDescription.INCORRECT_NAVIGATION);

        orderSuccess.clickContinue();
    }
}
