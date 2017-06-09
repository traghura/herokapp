package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestLogin {
    private WebDriver driver;
    private static Logger Log = LogManager.getLogger(TestLogin.class.getName());

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\tandrara\\selenium\\chrome-driver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Log.debug("Web driver initialized to test login");
    }

    @After
    public void tearDown() {
        driver.quit();
        Log.debug("Web driver closed");

    }

    @Test
    public void withValidCredentials() {
        driver.get("http://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button")).click();

        Assert.assertEquals(driver.findElement(By.id("flash")).isDisplayed(),true);

        if(driver.findElement(By.id("flash")).isDisplayed()){
            Log.debug("User logged in sucessfull");
        }
    }
}
