package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.SwitchToFrame;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import net.bytebuddy.agent.builder.AgentBuilder.CircularityLock.Default;

public class Topic_13_Frame_Iframe {
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
	public void TC_07_Iframe_Kyna() {	
		// Step 1
		driver.get("https://skills.kynaenglish.vn/");

		// A -> B
		// Switch vào frame/iframe trước rồi mới thao tác lên element thuộc frame/iframe đó
		// iframe 1, index là 0
		// iframe n, index là n-1
		// Nên dùng xpath, css, nếu id, name có nghĩa có thể dùng id, name

		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'facebook.com/kyna.vn')]")));
		
		// B
		// Verify số lượng Like
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), "165K likes");
		
		// B -> A
		driver.switchTo().defaultContent();

		// A -> C
		// Click vào chat để bật PU lên
		// Dùng switch to frame by id
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("trangdth");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0976757850");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("0976757850");
		
		// C -> A
		driver.switchTo().defaultContent();
		
		
		String keyword = "Excel";
		// Nhập và search
		
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		// Verify course name chứa từ khóa vừa nhập
		List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		for(WebElement course: courseName) {
			System.out.println(course.getText());
			Assert.assertTrue(course.getText().contains(keyword));
			
		}
		
		
		
	}
	
	
	@Test
	public void TC_03_Frame_HDFC() {
		
		// A (hdfcbank.com)
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		// A -> B (Login)
		driver.switchTo().frame("login_page");
		driver.findElement(By.name("fldLoginUserId")).sendKeys("trangdth");
		driver.findElement(By.cssSelector("a.login-btn")).click();

		Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed());
		
		
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


