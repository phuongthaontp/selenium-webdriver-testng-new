package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Action_I_Thaontp {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Khởi tạo browser lên
		driver = new FirefoxDriver();
		action = new Actions(driver);
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Hover() {
		// Hành động click đúp không nhả ngón tay ra : ClickAndHold
		// action.clickAndHold().moveToElement(driver.findElement(By.id(""))).build();
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		WebElement yourAgeTextbox = driver.findElement(By.id("age"));
		
		// Hover chuột vào textbox
		action.moveToElement(yourAgeTextbox).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.className("ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
		
	}
	
	 
	public void TC_02_Hover_II() {
		driver.get("http://www.myntra.com/");
		// Hover chuột vào Kid
		action.moveToElement(driver.findElement(By.xpath("//header//a[text()='Kids']"))).perform();
		sleepInSecond(3);
		
		action.click(driver.findElement(By.xpath("//header//a[text()='Home & Bath']"))).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
		
		// Các bước cần làm : 1. Gọi hàm cần dùng 2. Gọi cái perform() cuối cùng là xong.
	}
	
	
	public void TC_03_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// Khai báo tất cả 12 cái element ( dùng finElements)
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		WebElement firstNumber = allNumbers.get(0);
		WebElement secondNumber = allNumbers.get(1);
		WebElement thirdNumber = allNumbers.get(2);
		
		// Chọn từ 1- 4 dùng : ClickAndHold
		// Hover tới 4
		// Nhả chuột trái
		// Thực thi các câu lệnh
				
		action.clickAndHold(allNumbers.get(0)).moveToElement(allNumbers.get(3)).release().perform();
		sleepInSecond(5);
		
		// Kiểm tra chọn đúng chưa
		List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSelected.size(), 4);
		
	}

	@Test
	public void TC_04_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allNumbers = driver.findElements(By.cssSelector("ol#selectable>li"));
		
		Keys controlKey;
		if(osName.contains("WINDOWS")) {
			controlKey = Keys.CONTROL;
		} else {
			controlKey = Keys.COMMAND;
		}
		
		// Click ramdom 1-3-6-11
		action.keyDown(controlKey).perform();
		action.click(allNumbers.get(0)).click(allNumbers.get(2)).click(allNumbers.get(5)).click(allNumbers.get(10)).perform();
		action.keyUp(controlKey).build().perform();
		sleepInSecond(5);
		
		List<WebElement> allNumberSelected = driver.findElements(By.cssSelector("ol#selectable>li.ui-selected"));
		Assert.assertEquals(allNumberSelected.size(), 4);
		
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
