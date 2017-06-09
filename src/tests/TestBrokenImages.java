package tests;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class TestBrokenImages {
	private WebDriver driver;
	private int invalidImageCount;
	private static Logger Log = LogManager.getLogger(TestLogin.class.getName());

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tandrara\\selenium\\chrome-driver\\chromedriver.exe");
		driver = new ChromeDriver();
		Log.debug("Web driver initialized to test broken images");
	}

	@After
	public void tearDown() {
		driver.quit();
		Log.debug("Web driver closed");

	}

	@Test
	public void brokenImages() {
		driver.get("http://the-internet.herokuapp.com/broken_images");

		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content']/div/h3")).isDisplayed(), true);

		if (driver.findElement(By.xpath(".//*[@id='content']/div/h3")).isDisplayed()) {
			Log.debug("Broken images header validated");
		}

		try {
			invalidImageCount = 0;
			List<WebElement> imagesList = driver.findElements(By.tagName("img"));
			System.out.println("Total no. of images are " + imagesList.size());
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) {
					verifyimageActive(imgElement);
				}
			}
			System.out.println("Total no. of invalid images are " + invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public void verifyimageActive(WebElement imgElement) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
