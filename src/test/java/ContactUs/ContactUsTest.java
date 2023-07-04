package ContactUs;

import Base.BaseTest;
import Pages.ContactUsPage;
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

public class ContactUsTest extends BaseTest {

    private String validFirstName;
    private String validEnquiry;
    private String validEmail;
    private String invalidFirstName;
    private String invalidEnquiry;
    private ContactUsPage contactUsPage;

    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
        validEmail = JsonUtil.getJsonValue(KeyConstants.VALID_EMAIL);
        validEnquiry = JsonUtil.getJsonValue(KeyConstants.VALID_ENQUIRY);
        validFirstName = JsonUtil.getJsonValue(KeyConstants.VALID_FIRSTNAME);
        invalidEnquiry = JsonUtil.getJsonValue(KeyConstants.INVALID_ENQUIRY);
        invalidFirstName = JsonUtil.getJsonValue(KeyConstants.INVALID_FIRSTNAME);

        contactUsPage = homePage.clickContactUs();
    }

    @Test(priority = 0)
    public void testWithValidData() {
        contactUsPage.enterName(validFirstName);
        contactUsPage.enterEmail(validEmail);
        contactUsPage.enterEnquiry(validEnquiry);

        contactUsPage.clickSubmit();

        for (WebElement elm : contactUsPage.getErrorMessages()) {
            Assert.assertFalse(elm.isDisplayed(), TestDescription.ERROR_MESSAGE_SHOWN_DES);
        }
    }

    @Test(priority = 1)
    public void testConfirmationWithValidData() {
        ContactUsPage contactUsPage = homePage.clickContactUs();

        contactUsPage.enterName(validFirstName);
        contactUsPage.enterEmail(validEmail);
        contactUsPage.enterEnquiry(validEnquiry);

        contactUsPage.clickSubmit();

        String successMsg = contactUsPage.getSuccessMsg();

        Assert.assertTrue(successMsg.contains(TestDescription.ENQUIRY_SUCCESS_MSG), TestDescription.NOT_DISPLAY_CONFIRMATION_DES);
    }

    @Test(priority = 2)
    public void testErrorMessageWithInvalidData() {
        contactUsPage.enterName(invalidFirstName);
        contactUsPage.enterEmail("");
        contactUsPage.enterEnquiry(invalidEnquiry);

        contactUsPage.clickSubmit();

        List<WebElement> elements = contactUsPage.getErrorMessages();

        Assert.assertTrue(elements.get(0).getText().contains(TestDescription.ENQUIRY_NAME_ERROR), TestDescription.ENQUIRY_NAME_ERROR_DES);
        Assert.assertTrue(elements.get(1).getText().contains(TestDescription.ENQUIRY_EMAIL_ERROR), TestDescription.ENQUIRY_EMAIL_ERROR_DES);
        Assert.assertTrue(elements.get(2).getText().contains(TestDescription.ENQUIRY_ENQUIRY_ERROR), TestDescription.ENQUIRY_ENQUIRY_ERROR_DES);

    }
}
