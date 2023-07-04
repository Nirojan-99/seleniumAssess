package ProductDetails;

import Base.BaseTest;
import Pages.ProductDetailsPage;
import Pages.ProductListPage;
import TestData.TestDescription;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductDetailsTest extends BaseTest {

    private ProductListPage productListPage;

    @BeforeMethod
    public void beforeMethod() {
        productListPage = homePage.clickHeadphones();
    }

    @Test
    public void testProductDetailsFlow() {
        ProductDetailsPage productDetailsPage = productListPage.clickProduct(2);
        Assert.assertNotNull(productDetailsPage.getProductTitle(), TestDescription.NO_PRODUCT_NAME_DISPLAYED_DES);
    }

    @Test
    public void testProductDetailsDisplayed() {
        int index = 2;
        String clickedProductName = productListPage.getProductName(productListPage.getProductLayouts().get(index));
        String clickedProductPrice = productListPage.getProductLayouts().get(index).findElement(productListPage.productPrice).getText();

        ProductDetailsPage productDetailsPage = productListPage.clickProduct(index);

        Assert.assertTrue(clickedProductName.contains(productDetailsPage.getProductTitle()), TestDescription.PRODUCT_NAME_MISMATCH_DES);
        Assert.assertTrue(clickedProductPrice.contains(productDetailsPage.getProductPrice()), TestDescription.PRODUCT_PRICE_MISMATCH_DES);
        Assert.assertFalse(productDetailsPage.getProductDescription().isEmpty(), TestDescription.PRODUCT_DESCRIPTION_NOT_FOUND_DES);
    }
}
