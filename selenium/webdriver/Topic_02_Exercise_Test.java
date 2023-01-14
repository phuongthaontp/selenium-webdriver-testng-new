package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Exercise_Test {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Khởi tạo browser lên
		driver = new FirefoxDriver();
		
		// Mở trang FB lên
				driver.get("http://live.techpanda.org/index.php/customer/account/create/");
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01() {
		// Truy cập vào trang
		driver.get("http://live.techpanda.org/");
		
		// Click My acc dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		// Verify URL của login Page
		String loginPage = driver.getCurrentUrl();
		Assert.assertEquals(loginPage,"http://live.techpanda.org/index.php/customer/account/login/");
		
		//Click Create an Acc button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		// Verify lại URL của Create an Acc button
		String creatAcc = driver.getCurrentUrl();
		Assert.assertEquals(creatAcc, "http://live.techpanda.org/index.php/customer/account/create/");	
		
	}
	
	@Test 
	public void TC_02() {
		// Truy cập vào trang
		driver.get("http://live.techpanda.org/");
		
		// Click vào My Acc
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		// Verify Title
		String loginpageTilte = driver.getTitle();
		Assert.assertEquals(loginpageTilte, "Customer Login");
		
		// Click Creat An Account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		// Verify title
		String registerPage = driver.getTitle();
		Assert.assertEquals(registerPage, "Create New Customer Account");
			
	}
	@Test
	public void TC_03 () {
		driver.get("http://live.techpanda.org/");
		
		//Click vào My acc ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Click vào Create an Acc button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		// Back lại trang LoginPage
		driver.navigate().back();
		String loginPage = driver.getCurrentUrl();
		Assert.assertEquals(loginPage, "http://live.techpanda.org/index.php/customer/account/login/");
		
		// Forward tới trang Register
		driver.navigate().forward();
		String registerTitle = driver.getTitle();
		Assert.assertEquals(registerTitle,"Create New Customer Account");
	}
	
	@Test
	public void TC_04() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		//Verify Login Page có chứa text Login or Creat an Acc
		String homePageSource = driver.getPageSource();
		Assert.assertTrue(homePageSource.contains("Login or Create an Account"));
		
		//Click vào Creat an Acc
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		
		String resgisterPageSource = driver.getPageSource();
		Assert.assertTrue(resgisterPageSource.contains("Create an Account"));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
