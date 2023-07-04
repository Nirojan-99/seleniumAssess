package Order;

import Base.BaseTest;
import Login.LoginPageTest;
import Pages.LoginPage;
import Pages.MyOrder;
import Pages.OrderProductDetails;
import TestData.KeyConstants;
import TestData.TestDescription;
import Utils.JsonUtil;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class OrderTest extends BaseTest {

    private String validPassword;
    private String validEmail;
    private MyOrder myOrder;

    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
        validPassword = JsonUtil.getJsonValue(KeyConstants.VALID_PASSWORD);
        validEmail = JsonUtil.getJsonValue(KeyConstants.VALID_EMAIL);
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

        Assert.assertTrue(homePage.isLogoutButtonDisplayed(), TestDescription.LOGIN_FAILURE_DES);

        myOrder = homePage.clickOrderBtn();
    }

    @Test(dependsOnMethods = "login")
    public void testNavigateToMyOrder() {
        String title = myOrder.getPageTitle();

        Assert.assertEquals(title, TestDescription.MY_ORDER_TITLE, TestDescription.INCORRECT_NAVIGATION);
    }

    @Test(dependsOnMethods = "login")
    public void testOrderProductsDetails() {
        int index = 0;

        List<WebElement> elements = myOrder.getProducts();
        List<WebElement> elms = myOrder.getProductData(elements.get(index));

        for (int i = 0; i < 6; i++) {
            Assert.assertFalse(elms.get(i).getText().isEmpty(), TestDescription.PRODUCT_DETAILS_DISPLAY_ERROR_DES);
        }

    }

    @Test(dependsOnMethods = "login")
    public void testViewOrderProductDetails() {
        int index = 0;

        List<WebElement> elements = myOrder.getProducts();
        List<WebElement> elms = myOrder.getProductData(elements.get(index));

        String orderID = elms.get(0).getText();
        String total = elms.get(4).getText();

        OrderProductDetails orderProductDetails = myOrder.clickViewBtn(elms.get(6));

        String id = orderProductDetails.getOrderID();
        String totalAmount = orderProductDetails.getTotal();

        Assert.assertTrue(id.contains(orderID), TestDescription.ID_MISMATCH_DES);
        Assert.assertEquals(totalAmount, total, TestDescription.TOTAL_MISMATCH_DES);

    }

}
