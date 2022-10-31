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

public class Topic_11_Action_Part_II {

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
	public void TC_01_Double_Click() {	
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement doubleClickButton = driver.findElement(By.xpath("//button[text()='Double click me']"));
		
		// Scroll xuống đến cái Element này
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", doubleClickButton);
		sleepInSecond(3);
		
		action.doubleClick(doubleClickButton).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}
	@Test
	public void TC_02_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		action.contextClick(driver.findElement(By.cssSelector("span.context-menu-one"))).perform();
		sleepInSecond(3);
		
		// Hover vào Paste
		action.moveToElement(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		
		// Verify Paste đc hover thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon.context-menu-hover.context-menu-visible")).isDisplayed());
		
		// Click vào Paste
		action.click(driver.findElement(By.cssSelector("li.context-menu-icon-paste"))).perform();
		sleepInSecond(3);
		
		// Accept alert sau khi click vào Paste
		expliciWait.until(ExpectedConditions.alertIsPresent()).accept();
		
	}
	@Test
	public void TC_03_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement smallCircle = driver.findElement(By.id("draggable"));
		WebElement bigCircle = driver.findElement(By.id("droptarget"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(3);
		
		// Text
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		// Color
		Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase(), "#03a9f4");
		
		// Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase()
		
		// bigCircle.getCssValue("background-color") -> RGBA: rgba(0, 255, 0, 1)
		// Color.fromString() -> String của thư viện Color
		// asHex(): #03A9F4
		// toLowerCase(): #03a9f4
		
	}
	@Test
	public void TC_04_Drag_And_Drop_HTML5_Css() {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		String squareA = "#column-a";
		String squareB = "#column-b";
			
	
		sleepInSecond(3);
	}
	@Test
	public void TC_05_Drag_And_Drop_HTML5_Xpath() {

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
