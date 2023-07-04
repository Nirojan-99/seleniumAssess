package ProductList;

import Base.BaseTest;
import Pages.ProductDetailsPage;
import Pages.ProductListPage;
import TestData.TestDescription;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.ParseException;
import java.util.List;

public class ProductListTest extends BaseTest {

    private ProductListPage productListPage;

    @BeforeMethod
    public void beforeMethod() {
        productListPage = homePage.clickHeadphones();
    }

    @Test
    public void testProductDisplay() {
        Assert.assertTrue(productListPage.getProductLayouts().size() > 0 && productListPage.getProductLayouts().size() <= 15, TestDescription.NO_PRODUCT_DISPLAYED_DES);
    }

    @Test
    public void testProductDetailsDisplayed() {
        List<WebElement> layouts = productListPage.getProductLayouts();

        for (WebElement layout : layouts) {
            Assert.assertTrue(productListPage.isProductNameDisplayed(layout), TestDescription.NO_PRODUCT_NAME_DISPLAYED_DES);
            Assert.assertTrue(productListPage.isProductPriceDisplayed(layout), TestDescription.NO_PRODUCT_PRICE_DISPLAYED_DES);
            Assert.assertTrue(productListPage.isProductImageDisplayed(layout), TestDescription.NO_PRODUCT_IMAGE_DISPLAYED_DES);
        }
    }

    @Test
    public void testSortAscending() throws ParseException {
        productListPage.clickSortDropdown(TestDescription.SORT_ASCENDING);

        List<WebElement> layouts = productListPage.getProductLayouts();

        float prePrice = 0;
        for (WebElement layout : layouts) {
            Assert.assertTrue(productListPage.getProductPrice(layout) >= prePrice, TestDescription.PRODUCT_SORT_ERROR_DES);
        }

    }

    @Test
    public void testSortDescending() throws ParseException {
        productListPage.clickSortDropdown(TestDescription.SORT_DESCENDING);

        List<WebElement> layouts = productListPage.getProductLayouts();

        float prePrice = productListPage.getProductPrice(layouts.get(0));
        for (WebElement layout : layouts) {
            Assert.assertTrue(productListPage.getProductPrice(layout) <= prePrice, TestDescription.PRODUCT_SORT_ERROR_DES);
        }

    }

    @Test
    public void testPriceFilter() throws ParseException {
        productListPage.clickInStock();

        List<WebElement> layouts = productListPage.getProductLayouts();

        for (int i = 0; i < layouts.size() && i < 4; i++) {
            ProductDetailsPage productDetailsPage = productListPage.clickProduct(i);
            Assert.assertTrue(productDetailsPage.getAvailability().contains(TestDescription.IN_STOCK), TestDescription.FILTER_ERROR_DES);
            homePage.driver.navigate().back();
        }
    }
}
