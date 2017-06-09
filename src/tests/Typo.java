package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Typo {
	private WebDriver driver;
	private static Logger Log = LogManager.getLogger(TestLogin.class.getName());

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\tandrara\\selenium\\chrome-driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		Log.debug("Web driver initialized to test typo");
	}

	@After
	public void tearDown() {
		driver.quit();
		Log.debug("Web driver closed");

	}

	@Test
	public void typoErrors() {
		driver.get("http://the-internet.herokuapp.com/typos");

		String s = driver.findElement(By.xpath(".//*[@id='content']/div/p[2]")).getText();
		System.out.println(s);
		if (driver.findElement(By.xpath(".//*[@id='content']/div/p[2]"))
				.equals("   Sometimes you'll see a typo, other times you won't. ")) {
			Log.debug("Probable typo error");
		}
	}
}
