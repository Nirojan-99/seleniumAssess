package Pagination;

import Base.BaseTest;
import Pages.ProductListPage;
import TestData.TestDescription;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PaginationTest extends BaseTest {

    private ProductListPage productListPage;

    @BeforeMethod
    public void beforeMethod() {
        productListPage = homePage.clickHeadphones();
    }

    @Test
    public void testNextPageClick() {
        productListPage.clickNextPage();

        int countPerPage = Integer.parseInt(productListPage.getSelectedItemCountPerPage());

        String text = productListPage.getPaginationIndicatorText();

        Assert.assertTrue(text.contains(String.valueOf(countPerPage * 2)), TestDescription.PAGINATION_COUNT_ERROR_DES);
        Assert.assertTrue(text.contains(String.valueOf(countPerPage + 1)), TestDescription.PAGINATION_COUNT_ERROR_DES);
    }

    @Test
    public void testNoOfProductsPerPage() {
        productListPage.clickNextPage();

        productListPage.selectItemCountPerPage(String.valueOf(25));

        int countPerPage = Integer.parseInt(productListPage.getSelectedItemCountPerPage());

        int productCount = productListPage.getProductLayouts().size();

        Assert.assertEquals(productCount, countPerPage, TestDescription.PAGINATION_COUNT_ERROR_DES);
    }

    @Test
    public void textClickPageNumber() {
        int page = 3;
        productListPage.clickPage(page);

        int countPerPage = Integer.parseInt(productListPage.getSelectedItemCountPerPage());

        String text = productListPage.getPaginationIndicatorText();

        Assert.assertTrue(text.contains(String.valueOf(countPerPage * page)), TestDescription.PAGINATION_COUNT_ERROR_DES);
        Assert.assertTrue(text.contains(String.valueOf(countPerPage * (page - 1) + 1)), TestDescription.PAGINATION_COUNT_ERROR_DES);

    }

}
