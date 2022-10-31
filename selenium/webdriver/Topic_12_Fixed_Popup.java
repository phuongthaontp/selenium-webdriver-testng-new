package webdriver;

import static org.testng.Assert.assertEquals;

import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Fixed_Popup {

	WebDriver driver;
	Actions action; 
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	
	String osName = System.getProperty("os.name").toLowerCase();
	Alert alert;
	@BeforeClass
	public void beforeClass() {
		
		// WIN
		if (osName.startsWith("windows")) {
			
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		}

		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);

		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		// Visible: Phải thấy được trên UI/ Presence: Phải có ở trong HTML
		expliciWait = new WebDriverWait(driver, 15);


		// Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	@Test
	public void TC_01_Ngoaingu24h_By() {	
		driver.get("https://ngoaingu24h.vn/");
		By loginPopupBy = By.cssSelector("div#modal-login-v1");
		
		// Verify Login PU is not Displayed		
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		// Verify Login PU is Displayed	
		Assert.assertTrue(driver.findElement(loginPopupBy).isDisplayed());	
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("trangdth12");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("Dtht@1602");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);
		// Verify Login PU is not Displayed		
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
	
	}
	
	
	@Test
	public void TC_02_Ngoaingu24h_Element() {	
		driver.get("https://ngoaingu24h.vn/");
		WebElement loginPopup = driver.findElement(By.cssSelector("div#modal-login-v1"));
		
		// Verify Login PU is not Displayed		
		Assert.assertFalse(loginPopup.isDisplayed());
		
		driver.findElement(By.cssSelector("button.login_")).click();
		sleepInSecond(2);
		
		// Verify Login PU is Displayed	
		Assert.assertTrue(loginPopup.isDisplayed());	
		
		driver.findElement(By.cssSelector("input#account-input")).sendKeys("trangdth12");
		driver.findElement(By.cssSelector("input#password-input")).sendKeys("Dtht@1602");
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
		
		driver.findElement(By.cssSelector("div#modal-login-v1 button.close")).click();
		sleepInSecond(2);
		// Verify Login PU is not Displayed		
		Assert.assertFalse(loginPopup.isDisplayed());
		
	}
	
	@Test
	public void TC_03_JTExpress() {	
		
		driver.get("https://jtexpress.vn/");
		By homeSliderPopup = By.cssSelector("div#home-modal-slider");
		String billCode = "840000598444";
		
		// Verify Login PU is Displayed		
		Assert.assertTrue(driver.findElement(homeSliderPopup).isDisplayed());
		
		driver.findElement(By.cssSelector("button.close")).click();
		sleepInSecond(2);
		
		// Verify Login PU is Displayed	
		Assert.assertFalse(driver.findElement(homeSliderPopup).isDisplayed());
		
		driver.findElement(By.name("billcodes")).sendKeys(billCode);
		driver.findElement(By.xpath("//form[@id='formTrack']//button[text()='Tra cứu vận đơn']")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h5")).getText().contains(billCode));

	}
	@Test
	public void TC_03() {	

	}
	@Test
	public void TC_04() {	

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
