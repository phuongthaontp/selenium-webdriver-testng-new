package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Exercise_Unit14_Unit6 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://vnext.vn");
	}

	@Test
	public void TC_01_VerifyUrl() {
		String vnextUrl = driver.getCurrentUrl();
		Assert.assertEquals(vnextUrl, "https://vnext.vn/");
		WebElement serviceLabofooter = driver.findElement(By.xpath("//a[contains(text(),'Phát triển theo hình thức Labo')]"));
		serviceLabofooter.click();
		String serviceLaboUrl = driver.getCurrentUrl();
		Assert.assertEquals(serviceLaboUrl, "https://vnext.vn/vi-vn/service-labo.html");
		WebElement contactbtn = driver.findElement(By.xpath("//a[@class='skewed-button d-row download-doc-button']//p[contains(text(),'Liên hệ')]"));
		contactbtn.click();
		String contactUrl = driver.getCurrentUrl();
		Assert.assertEquals(contactUrl, "https://vnext.vn/vi-vn/intruction-contact.html");
}
	@Test
	public void TC_02_VerifyTitle() {
		driver.get("https://vnext.vn");;
		WebElement serviceLabofooter = driver.findElement(By.xpath("//a[contains(text(),'Phát triển theo hình thức Labo')]"));
		serviceLabofooter.click();
		String serviceLaboTitle = driver.getTitle();
		Assert.assertEquals(serviceLaboTitle, "VNEXT HOLDINGS | Dịch vụ | Phát triển theo hình thức Labo");
		WebElement contactbtn = driver.findElement(By.xpath("//a[@class='skewed-button d-row download-doc-button']//p[contains(text(),'Liên hệ')]"));
		contactbtn.click();
		String contactTitle = driver.getTitle();
		Assert.assertEquals(contactTitle, "VNEXT HOLDINGS | Liên hệ");
	}
	
	@Test
	public void TC_03_NavigateFuntion() {
		driver.get("https://vnext.vn");;
		WebElement serviceLabofooter = driver.findElement(By.xpath("//a[contains(text(),'Phát triển theo hình thức Labo')]"));
		serviceLabofooter.click();
		WebElement contactbtn = driver.findElement(By.xpath("//a[@class='skewed-button d-row download-doc-button']//p[contains(text(),'Liên hệ')]"));
		contactbtn.click();
		String contactUrl = driver.getCurrentUrl();
		Assert.assertEquals(contactUrl, "https://vnext.vn/vi-vn/intruction-contact.html");
		driver.navigate().back();
		String serviceLaboUrl = driver.getCurrentUrl();
		Assert.assertEquals(serviceLaboUrl, "https://vnext.vn/vi-vn/service-labo.html");
		driver.navigate().forward();
		String contactTitle = driver.getTitle();
		Assert.assertEquals(contactTitle, "VNEXT HOLDINGS | Liên hệ");
	}
	@Test
	public void TC_04_GetPageSource() {
		driver.get("https://vnext.vn");;
		WebElement serviceLabofooter = driver.findElement(By.xpath("//a[contains(text(),'Phát triển theo hình thức Labo')]"));
		serviceLabofooter.click();
		String laboPageSource = driver.getPageSource();
		Assert.assertTrue(laboPageSource.contains("cung cấp các loại hình phát triển đa dạng"));
		WebElement contactbtn = driver.findElement(By.xpath("//a[@class='skewed-button d-row download-doc-button']//p[contains(text(),'Liên hệ')]"));
		contactbtn.click();
		String contactPageSource = driver.getPageSource();
		Assert.assertTrue(contactPageSource.contains("Liên hệ về dịch vụ"));
		driver.quit();
	}
}
