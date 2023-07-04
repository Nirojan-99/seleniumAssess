package Login;

import Base.BaseTest;
import Pages.LoginPage;
import Pages.MyAccountPage;
import TestData.KeyConstants;
import TestData.TestDescription;
import Utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.ParseException;

public class LoginPageTest extends BaseTest {

    private String invalidPassword;
    private String validPassword;
    private String validEmail;
    private LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
        loginPage = homePage.clickLogin();

        invalidPassword = JsonUtil.getJsonValue(KeyConstants.INVALID_PASSWORD);
        validPassword = JsonUtil.getJsonValue(KeyConstants.VALID_PASSWORD);
        validEmail = JsonUtil.getJsonValue(KeyConstants.VALID_EMAIL);
    }

    @Test(priority = 0)
    public void testWithValidData() {
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);

        loginPage.clickSubmit();

        Assert.assertTrue(homePage.isLogoutButtonDisplayed(), TestDescription.LOGIN_FAILURE_DES);

        homePage.clickLogout();
    }

    @Test(dependsOnMethods = "testWithValidData")
    public void testWithInvalidData() {
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(invalidPassword);

        loginPage.clickSubmit();

        Assert.assertTrue(loginPage.isErrorDisplayed(), TestDescription.ERROR_MESSAGE_NOT_DISPLAY_DES);
    }

    @Test(dependsOnMethods = "testWithInvalidData")
    public void testErrorMessageWithInvalidData() {
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(invalidPassword);

        loginPage.clickSubmit();

        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains(TestDescription.INVALID_CREDENTIALS_ERROR_MSG), TestDescription.ERROR_MESSAGE_NOT_DISPLAY_DES);
    }

    @Test(dependsOnMethods = "testErrorMessageWithInvalidData")
    public void testNavigationWithValidData() {
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);

        MyAccountPage myAccountPage = loginPage.clickSubmit();

        String titleText = myAccountPage.getTitleText();
        Assert.assertTrue(titleText.contains(TestDescription.MY_ACCOUNT_TITLE), TestDescription.INCORRECT_NAVIGATION);

        homePage.clickLogout();
    }


}
