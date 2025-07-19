import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCase1_RegisterUser {
    WebDriver driver;

    @BeforeTest
    public void launchBrowser() {
        WebDriverManager.edgedriver();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDownBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.quit();
    }

    @Test (priority = 0)
    public void navigateToWebsiteAndVerify(){
        System.out.println("Navigate to The Home Page of The Website.");
        driver.navigate().to("http://automationexercise.com/");

        System.out.println("Verifying Navigation to Home Page.");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Website for automation practice']")));

        WebElement logo = driver.findElement(By.xpath("//img[@alt='Website for automation practice']"));
        Assert.assertTrue(logo.isDisplayed(), "Home Page is not Visible.");
        System.out.println("Successfully Navigating to The Home Page.");

    }

    @Test (priority = 1, dependsOnMethods = "navigateToWebsiteAndVerify")
    public void signUp() {
        System.out.println("Navigate to The Signup/Login Page.");
        driver.findElement(By.linkText("Signup / Login")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='New User Signup!']")));

        String signupHeaderText = driver.findElement(By.xpath("//h2[text()='New User Signup!']")).getText();

        Assert.assertEquals(signupHeaderText, "New User Signup!", "Navigation to Signup / Login Page is Unsuccessful!");

        System.out.println("Signup.");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.btn.btn-default")));

        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("testUser1");
        driver.findElement(By.cssSelector("input[data-qa='signup-email']")).sendKeys("signupTest@gmail.com");
        driver.findElement(By.cssSelector("button[data-qa='signup-button']")).click();

        String signupInformationHeaderText = driver.findElement(By.xpath("//b[normalize-space()='Enter Account Information']")).getText();
        Assert.assertEquals(signupInformationHeaderText,"ENTER ACCOUNT INFORMATION", "Navigation to Signup / Login Page is Unsuccessful.");
    }

}
