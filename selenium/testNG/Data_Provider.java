package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Data_Provider {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	}
	
	@Test(dataProvider = "login")
	public void TC_01_LoginToSystem(String username, String password) throws InterruptedException {
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
	
		driver.findElement(emailTextbox).sendKeys(username);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(loginButton).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		
		// Check thông tin nào đó
		// Action 1 task nào đó
		
		// Post-Condition: Logout ra để cho User tiếp theo nó chạy đc
		// Mỗi round chạy qua dataProvider này sẽ là 1 TC
		
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}
	
	@DataProvider(name = "login")
	public Object[][] loginDataProvider() {
		return new Object[][] { 
			{ "selenium_11_01@gmail.com", "111111" }, 
			{ "selenium_11_02@gmail.com", "111111" }, 
			{ "selenium_11_03@gmail.com", "111111" } };
	}
	@DataProvider(name = "register")
	public Object[][] registerDataProvider() {
		return new Object[][] { 
			{ "selenium_11_01@gmail.com", "111111" }, 
			{ "selenium_11_02@gmail.com", "111111" }, 
			{ "selenium_11_03@gmail.com", "111111" } };
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}