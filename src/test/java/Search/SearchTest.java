package Search;

import Base.BaseTest;
import Pages.ProductListPage;
import TestData.KeyConstants;
import TestData.TestDescription;
import Utils.JsonUtil;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class SearchTest extends BaseTest {
    private String validSearchQuery;
    private String invalidSearchQuery;

    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
        validSearchQuery = JsonUtil.getJsonValue(KeyConstants.VALID_SEARCH_QUERY);
        invalidSearchQuery = JsonUtil.getJsonValue(KeyConstants.INVALID_SEARCH_QUERY);
    }

    @Test
    public void testSearchWithValidData() {
        homePage.enterSearch(validSearchQuery);
        homePage.clickSearchBtn();
    }

    @Test(dependsOnMethods = "testSearchWithValidData")
    public void validateSearchResult() {
        ProductListPage productListPage = new ProductListPage(homePage.driver);

        for (WebElement element : productListPage.getProductLayouts()) {
            String name = productListPage.getProductName(element);
            System.out.println(name);
            Assert.assertTrue(name.toLowerCase().contains(validSearchQuery.toLowerCase()), TestDescription.SEARCH_RESULT_MISMATCH_DES);
        }
    }

    @Test()
    public void testSearchWithInvalidData() {
        ProductListPage productListPage = new ProductListPage(homePage.driver);

        homePage.enterSearch(invalidSearchQuery);
        homePage.clickSearchBtn();

        List<WebElement> elements = productListPage.getProductLayouts();

        Assert.assertEquals(elements.size(), 0, TestDescription.RESULT_FOR_INVALID_SEARCH_DES);
    }
}
