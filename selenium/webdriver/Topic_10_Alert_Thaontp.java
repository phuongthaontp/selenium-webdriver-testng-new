package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert_Thaontp {
	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Khởi tạo browser lên
		driver = new FirefoxDriver();
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
	
		
		// Switch qua Alert
		alert = driver.switchTo().alert();
		
		// Verify text ( Phải verify trước khi ấn accept hoặc cancel)
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// Ấn accept
		alert.accept();
		sleepInSecond(3);
		
		// Verify sau khi ấn Ok
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked an alert successfully");
	}
	
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		// Switch to alert
		alert = driver.switchTo().alert();
		
		// Verify text khi click alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		
		// Ấn Cancel
		alert.dismiss();
		sleepInSecond(3);
		
		// Verify sau khi nhấn OK
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You clicked: Cancel");
			
		
	}
	
	
	public void TC_03_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		// Switch to alert
		alert = driver.switchTo().alert();
		
		// Verify text khi click alert
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		
		// Input data trước khi nhấn OK hay Cancel
		alert.sendKeys("Automation");
		
		// Ấn OK
		alert.accept();
		sleepInSecond(3);
		
		// Verify sau khi nhấn OK
		Assert.assertEquals(driver.findElement(By.cssSelector("p#result")).getText(), "You entered: Automation");
	}
	
	
	public void TC_04_Authetication_Alert() {
		String username = "admin";
		String password = "admin";
		driver.get("http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth" );
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),\"Congratulations! You must have the proper credentials.\")]")).isDisplayed());
		  
	}
	
	@Test
	public void TC_05_Authentication_Alert_II() {
		// Trường hợp này dành cho thao tác từ page A chuyển qua page B
		String username = "admin";
		String password = "admin";
		
		driver.get("http://the-internet.herokuapp.com");
		
		String basicAuthenLink = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		// DÙng split để tách chuỗi
		String[] basicAuthen = basicAuthenLink.split("//");
		
		basicAuthenLink = basicAuthen[0] + "//" + username + ":" + password + "@" + basicAuthen[1];
		driver.get(basicAuthenLink);
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),\"Congratulations! You must have the proper credentials.\")]")).isDisplayed());
		
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	

	public void sleepInSecond(long second) {

		try {
			Thread.sleep(second * 1000); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

