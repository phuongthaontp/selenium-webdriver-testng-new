package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01() {
		
		// Các hàm (function/ method) câu lệnh (command) 
		// Tương tác vs Browser
		// driver.
		
		// Tương tác vs Element
		// Nếu như Element chỉ dùng duy nhất 1 lần thì không cần khai báo biến
	
		driver.findElement(By.name("login")).click();
		
		// Nếu như Element đó dùng từ 2 lần trở lên thì nên khai báo biến để tái sử dụng
		driver.findElement(By.cssSelector("#email")).clear();
		driver.findElement(By.cssSelector("#email")).sendKeys("AutomationTesting@gmail.com");
		WebElement emailTextbox = driver.findElement(By.cssSelector("#email"));
		emailTextbox.clear();

		
		
	}
}