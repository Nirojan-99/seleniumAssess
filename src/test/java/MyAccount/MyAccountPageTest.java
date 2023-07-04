package MyAccount;

import Base.BaseTest;
import Pages.EditAccount;
import Pages.LoginPage;
import Pages.MyAccountPage;
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

public class MyAccountPageTest extends BaseTest {

    private MyAccountPage myAccountPage;
    private String newFirstName;
    private String newLastName;
    private String newEmail;
    private String newContactNum;
    private String validPassword;
    private String validEmail;

    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
        newEmail = JsonUtil.getJsonValue(KeyConstants.NEW_EMAIL);
        newContactNum = JsonUtil.getJsonValue(KeyConstants.NEW_CONTACT_NUMBER);
        newLastName = JsonUtil.getJsonValue(KeyConstants.NEW_LAST_NAME);
        newFirstName = JsonUtil.getJsonValue(KeyConstants.NEW_FIRST_NAME);
        validPassword = JsonUtil.getJsonValue(KeyConstants.VALID_PASSWORD);
        validEmail = JsonUtil.getJsonValue(KeyConstants.VALID_EMAIL);
    }

    @AfterClass
    public void afterClass() {
        homePage.clickLogout();
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = homePage.clickLogin();
        loginPage.enterEmail(validEmail);
        loginPage.enterPassword(validPassword);

        myAccountPage = loginPage.clickSubmit();

        Assert.assertTrue(homePage.isLogoutButtonDisplayed(), TestDescription.LOGIN_FAILURE_DES);
    }

    @Test(dependsOnMethods = "testLogin")
    public void testNavigationToAccountSetting() {
        EditAccount editAccount = myAccountPage.clickEditBtn();

        editAccount.enterFirstName(newFirstName);
        editAccount.enterLastName(newLastName);
        editAccount.enterEmail(newEmail);
        editAccount.enterNumber(newContactNum);

        editAccount.clickSubmit();
        List<WebElement> elements = editAccount.getErrorMessage();

        Assert.assertEquals(elements.size(), 0, TestDescription.ERROR_MESSAGE_SHOWN_DES);
        Assert.assertTrue(myAccountPage.isSuccessMsgDisplayed(), TestDescription.SUCCESS_MSG_SHOWN_DES);
    }

    @Test(dependsOnMethods = {"testNavigationToAccountSetting", "testLogin"})
    public void testChangesReflect() {
        EditAccount editAccount = myAccountPage.clickEditBtn();

        Assert.assertEquals(editAccount.getFirstName(), newFirstName, TestDescription.MISMATCH_FIRSTNAME_DES);
        Assert.assertEquals(editAccount.getLastName(), newLastName, TestDescription.MISMATCH_LASTNAME_DES);
        Assert.assertEquals(editAccount.getEmail(), newEmail, TestDescription.MISMATCH_EMAIL_DES);
        Assert.assertEquals(editAccount.getNumber(), newContactNum, TestDescription.MISMATCH_NUMBER_DES);

    }
}
