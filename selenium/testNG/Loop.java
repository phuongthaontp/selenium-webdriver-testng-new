package testNG;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Loop {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test(invocationCount = 5)
	public void TC_01_Register() {
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
	
		
		driver.findElement(By.id("firstname")).sendKeys("automationfc");
		driver.findElement(By.id("lastname")).sendKeys("FC");
		String emailAddress = "afc" + getRandomNumber() + "@gmail.com";
		System.out.println("Email Address: "+emailAddress);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("automationfc FC"));
		

		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();

	

	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(999999);
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}