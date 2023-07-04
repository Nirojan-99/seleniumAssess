package Base;

import Pages.HomePage;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    public static WebDriver driver;
    protected HomePage homePage;

    @BeforeSuite
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://ecommerce-playground.lambdatest.io/");
    }

    @BeforeClass
    public void beforeClass() {
        homePage = new HomePage(driver);
    }

    @AfterMethod
    public void checkStatus(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            takeScreenShot(result.getName());
        }
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
    }

    private void takeScreenShot(String testMethod) throws IOException {
        var cam = (TakesScreenshot) driver;
        File ss = cam.getScreenshotAs(OutputType.FILE);
        Files.move(ss.getAbsoluteFile(), new File("resources/screenshots/" + testMethod + ".png"));
    }
}
