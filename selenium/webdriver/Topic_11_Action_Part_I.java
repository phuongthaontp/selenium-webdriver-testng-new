package webdriver;

import static org.testng.Assert.assertEquals;

import java.security.PublicKey;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

public class Topic_11_Action_Part_I {

	WebDriver driver;
	Actions action; 
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
		action = new Actions(driver);

		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		// Visible: Phải thấy được trên UI/ Presence: Phải có ở trong HTML
		expliciWait = new WebDriverWait(driver, 15);


		// Wait cho việc tìm element (findElement)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@Test
	public void TC_01() {
		
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		WebElement yourAgeTextbox = driver.findElement(By.id("age"));
		
		// Hover chuột vào Textbox
		action.moveToElement(yourAgeTextbox).perform();
		sleepInSecond(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class= 'ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");
		

	}
	@Test
	public void TC_02() {
		
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
		sleepInSecond(3);
		
		action.click(driver.findElement(By.xpath("//header//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
		
	}
	@Test
	public void TC_03() {	

		
		
	}
	@Test
	public void TC_04_Click_And_Hold() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Khai báo tất cả 12 Elements
		List<WebElement> listElements = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		// 1->4 
		// Click and hold vào 1
		// Hover tới 4
		// Nhả chuột trái ra
		// Thực thi câu lệnh
		action.clickAndHold(listElements.get(0)).moveToElement(listElements.get(3)).release().perform();
		sleepInSecond(1);
		
		List<WebElement> listElementsSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listElementsSelected.size(), 4);
	}
	@Test
	public void TC_05_Click_And_Hold_Random() {
		
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Khai báo tất cả 12 Elements
		List<WebElement> listElements = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		Keys controlKey;
		if (osName.contains("win") || osName.contains("nux"))
		{
			controlKey = Keys.CONTROL;
		}		
		else
			{
			controlKey = Keys.COMMAND;
			}
			
		// 1, 5, 12
		// Nhấn phím Ctrl xuống, chưa nhả ra
		// Click vào 1
		// Click vào 5
		// Click 11
		// Thực thi các câu lệnh
		// Nhả phím Ctrl ra
		action.keyDown(controlKey).perform();
		action.click(listElements.get(0)).click(listElements.get(4)).click(listElements.get(10)).perform();
		action.keyUp(controlKey).perform();
		sleepInSecond(1);
		List<WebElement> listElementsSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(listElementsSelected.size(), 3);
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
