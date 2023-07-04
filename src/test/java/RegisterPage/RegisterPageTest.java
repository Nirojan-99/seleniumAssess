package RegisterPage;

import Base.BaseTest;
import Pages.RegisterPage;
import Pages.RegisterSuccessPage;
import TestData.KeyConstants;
import TestData.TestDescription;
import Utils.JsonUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Date;

public class RegisterPageTest extends BaseTest {

    private String email;
    private RegisterPage registerPage;
    private String validFirstName;
    private String validLastName;
    private String validContactNumber;
    private String validPassword;
    private String validConfirmPassword;
    private String invalidFirstName;
    private String invalidLastName;
    private String invalidEmail;
    private String invalidContactNumber;
    private String invalidPassword;
    private String invalidConfirmPassword;

    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
        Date date = new Date();
        email = "nirojan" + date.getDate() + date.getMinutes() + date.getSeconds();

        registerPage = homePage.clickRegister();

        validFirstName = JsonUtil.getJsonValue(KeyConstants.VALID_FIRSTNAME);
        validLastName = JsonUtil.getJsonValue(KeyConstants.VALID_LASTNAME);
        validContactNumber = JsonUtil.getJsonValue(KeyConstants.VALID_CONTACT_NUMBER);
        validPassword = JsonUtil.getJsonValue(KeyConstants.VALID_PASSWORD);
        validConfirmPassword = JsonUtil.getJsonValue(KeyConstants.VALID_CONFIRM_PASSWORD);
        invalidFirstName = JsonUtil.getJsonValue(KeyConstants.INVALID_FIRSTNAME);
        invalidLastName = JsonUtil.getJsonValue(KeyConstants.INVALID_LASTNAME);
        invalidEmail = JsonUtil.getJsonValue(KeyConstants.INVALID_EMAIL);
        invalidContactNumber = JsonUtil.getJsonValue(KeyConstants.INVALID_CONTACT_NUMBER);
        invalidPassword = JsonUtil.getJsonValue(KeyConstants.INVALID_PASSWORD);
        invalidConfirmPassword = JsonUtil.getJsonValue(KeyConstants.INVALID_CONFIRM_PASSWORD);

    }


    @Test(priority = 2)
    public void testWithValidData() {

        registerPage.enterFirstName(validFirstName);
        registerPage.enterLastName(validLastName);
        registerPage.enterEmail(email + "@gmail.com");
        registerPage.enterPhoneNumber(validContactNumber);
        registerPage.enterPassword(validPassword);
        registerPage.enterConfirmPassword(validConfirmPassword);
        registerPage.setSubscription(true);
        registerPage.agreePrivacyPolicy();

        RegisterSuccessPage registerSuccessPage = registerPage.clickContinue();

        String title = registerSuccessPage.getTileText();
        Assert.assertTrue(title.contains(TestDescription.ACCOUNT_CREATED_SUCCESS_TITLE), TestDescription.ACCOUNT_NOT_CREATED);

        homePage.clickLogout();
    }

    @Test(priority = 0)
    public void testWithInvalidData() {
        registerPage.enterFirstName(invalidFirstName);
        registerPage.enterLastName(invalidLastName);
        registerPage.enterEmail(invalidEmail);
        registerPage.enterPhoneNumber(invalidContactNumber);
        registerPage.enterPassword(invalidPassword);
        registerPage.enterConfirmPassword(invalidConfirmPassword);
        registerPage.setSubscription(true);
        registerPage.agreePrivacyPolicy();

        registerPage.clickContinue();

        Assert.assertTrue(registerPage.isErrorDisplayed(), TestDescription.NOT_DISPLAY_ERROR_MESSAGE);
    }

    @Test(priority = 1)
    public void testErrorMessageWithInvalidData() {
        registerPage.enterFirstName(invalidFirstName);
        registerPage.enterLastName(invalidLastName);
        registerPage.enterEmail(invalidEmail);
        registerPage.enterPhoneNumber(invalidContactNumber);
        registerPage.enterPassword(invalidPassword);
        registerPage.enterConfirmPassword(invalidConfirmPassword);
        registerPage.setSubscription(true);
        registerPage.agreePrivacyPolicy();

        registerPage.clickContinue();

        List<String> errorText = registerPage.getErrorMessage();
        Assert.assertTrue(errorText.contains(TestDescription.MISSING_FIRST_NAME_ERROR_MSG), TestDescription.MISSING_FIRST_NAME_ERROR_DES);
        Assert.assertTrue(errorText.contains(TestDescription.MISSING_LAST_NAME_ERROR_MSG), TestDescription.MISSING_LAST_NAME_ERROR_DES);
        Assert.assertTrue(errorText.contains(TestDescription.MISSING_CONTACT_NUMBER_ERROR_MSG), TestDescription.MISSING_CONTACT_NUMBER_ERROR_DES);
        Assert.assertTrue(errorText.contains(TestDescription.MISSING_PASSWORD_ERROR_MSG), TestDescription.MISSING_PASSWORD_ERROR_DES);
//        Assert.assertTrue(errorText.contains(TestDescription.MISSING_EMAIL_ERROR_MSG), TestDescription.MISSING_EMAIL_ERROR_DES);
        Assert.assertTrue(errorText.contains(TestDescription.MISMATCH_CONFIRM_PASSWORD_ERROR_MSG), TestDescription.MISMATCH_CONFIRM_PASSWORD_ERROR_DES);
    }

    @Test(priority = 3)
    public void testNavigationWithValidData() {
        registerPage.enterFirstName(validFirstName);
        registerPage.enterLastName(validLastName);
        registerPage.enterEmail(email + "@gmail.com");
        registerPage.enterPhoneNumber(validContactNumber);
        registerPage.enterPassword(validPassword);
        registerPage.enterConfirmPassword(validConfirmPassword);
        registerPage.setSubscription(true);
        registerPage.agreePrivacyPolicy();

        RegisterSuccessPage registerSuccessPage = registerPage.clickContinue();

        String titleVal = registerSuccessPage.getTileText();
        Assert.assertTrue(titleVal.contains(TestDescription.ACCOUNT_CREATED_SUCCESS_TITLE), TestDescription.REDIRECT_ERROR_DES);

        homePage.clickLogout();
    }
}
