package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Part_II {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Khởi tạo browser lên
		driver = new FirefoxDriver();
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	
	@Test
	public void TC_01_Url() {
		// Mở link BT lên
		driver.get("http://live.techpanda.org/");
		
		//Click vào My Account ở dưới footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		//Click  vào Creat an Account button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click(); 
		
		String registerPage = driver.getCurrentUrl();
		Assert.assertEquals(registerPage,"http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test 
	public void TC_02_Titlle() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
	}
    
	@Test
	public void TC_03_Navigation() {
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
		String registerPage = driver.getTitle();
		Assert.assertEquals(registerPage,"Create New Customer Account");
		
	}
	
	@Test
	public void TC_04_Page_Source() {
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
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}


