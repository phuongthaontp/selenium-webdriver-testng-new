package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_20_Wait_Element_Condition_Status {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	
	String osName = System.getProperty("os.name").toLowerCase();

	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();

		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		//driver = new EdgeDriver();
		
		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		// Visible: Phải thấy được trên UI/ Presence: Phải có ở trong HTML
		expliciWait = new WebDriverWait(driver, 15);


		// Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	
	public void TC_01_Visible_Displayed_Visibility() {	
		driver.get("https://www.facebook.com/");
		// 1. Có trên UI (bắt buộc)
		// 1. Có trong HTML (bắt buộc)
		
		// Wait cho email address textbox hiển thị
		// Chờ cho email address textbox hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
		driver.findElement(By.id("email")).sendKeys("automationtest@gmail.com");
		
	}
	
			

	public void TC_02_Invisible_Undisplayed_Invisibility_I() {
		driver.get("https://www.facebook.com/");
		// 2. Không có trên UI
		// 1. Có trong HTML
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		// Chờ cho Re-enter Email Textbox không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));

	}
	

	public void TC_02_Invisible_Undisplayed_Invisibility_II() {
		driver.get("https://www.facebook.com/");
		// 2. Không có trên UI
		// 2. Không trong HTML

		
		// Chờ cho Re-enter Email Textbox không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.invisibilityOfElementLocated(By.name("reg_email_confirmation__")));

	}
	
	@Test
	public void TC_03_Present_I() {
		driver.get("https://www.facebook.com/");
		
		// 1. Có trên UI (bắt buộc)
		// 1. Có trong HTML (bắt buộc)
		
		// Chờ cho email address textbox presence trong HTML trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.id("email")));
	}
	
	

	public void TC_03_Present_II() {
		driver.get("https://www.facebook.com/");
		// 2. Không có trên UI
		// 1. Có trong HTML
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		// Chờ cho Re-enter Email Textbox không hiển thị trong vòng 10s
		expliciWait.until(ExpectedConditions.presenceOfElementLocated(By.name("reg_email_confirmation__")));
	}

	@Test
	public void TC_04_Staless() {
		driver.get("https://www.facebook.com/");
		// 2. Không có trên UI
		// 2. Không có trong HTML
		
		driver.findElement(By.xpath("//a[@data-testid='open-registration-form-button']")).click();
		
		
		// Phase 1: Element có trong cây HTML
		
		WebElement reEnterEmailAddressTextbox = driver.findElement(By.name("reg_email_confirmation__"));
		
		// Thao tác vs Element khác làm cho Element re-enter email ko còn trong DOM nữa
		// ... 
		// Close PU đi
		driver.findElement(By.cssSelector("img._8idr")).click();
		
		// Chờ cho Re-enter Email Textbox không còn trong DOM trong vòng 10s
		expliciWait.until(ExpectedConditions.stalenessOf(reEnterEmailAddressTextbox));
		
	
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
	public String getCurrentDateTime()
	{
		return new Date().toString();
	}
}


