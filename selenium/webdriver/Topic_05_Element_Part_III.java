package webdriver;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_III {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}
	
	@BeforeMethod
		public void beforeMethod() {
	driver.get("https://automationfc.github.io/basic-form/index.html");
	}
	
	@Test
	public void Register_01_Empty_Data() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed());
		WebElement elementEmail = driver.findElement(By.xpath("//input[@id='mail']"));
		System.out.println("elementEmail"+ "is Displayed");
		driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation Testing");
		Assert.assertTrue(driver.findElement(By.xpath("//label[@for='under_18']")).isDisplayed());
		WebElement elementAge = driver.findElement(By.xpath("//label[@for='under_18']"));
		System.out.println("elementAge"+"is Displayed");
		driver.findElement(By.xpath("//label[@for='under_18']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed());
		driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");
	}
	@Test
	public void Register_02_Invalid_Email()
	{
		
	}
	
	public void Register_03_Incorrect_Confirm_Email()
	{
		
	}
	
	public void Register_04_Invalid_Password()
	{
		
	}
	public void Register_05_Invalid_Phone()
	{
		
	}

	
	
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}



