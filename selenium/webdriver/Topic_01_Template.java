package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_Template {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Khởi tạo browser lên
		driver = new FirefoxDriver();
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		// Mở trang FB lên
		driver.get("http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_01() {
		// Selenium Locator (By class)  
		
		//ID
		driver.findElement(By.id("firstname")).sendKeys("Automation");
		
		//Name
		driver.findElement(By.name("lastname")).sendKeys("FC");
		
		//Class
		driver.findElement(By.className("customer-name-middlename")).isDisplayed();
		driver.findElement(By.className("name-middlename")).isDisplayed();
		
		//Tag name
		int inputNumber = driver.findElements(By.tagName("input")).size();
		System.out.print(inputNumber);
		
		//Link text
		driver.findElement(By.linkText("SEARCH TERMS")).click();
		
		// Partial LinkText
		driver.findElement(By.partialLinkText("ADVANCED SEARCH ")).click();
		
		//CSS
		driver.findElement(By.cssSelector("input[id='name']")).sendKeys("iPhone");
		
		//Xpath
		driver.findElement(By.xpath("//input[@id='description']")).sendKeys(" Iphone 15");
		
		driver.findElement(By.xpath("//input[@id='description']")).sendKeys(" HQ");

	}
	
	@Test 
	public void TC_02() {
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
