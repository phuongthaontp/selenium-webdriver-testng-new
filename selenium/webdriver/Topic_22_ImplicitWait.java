package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_ImplicitWait {
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
		expliciWait = new WebDriverWait(driver, 15);


		// Wait cho việc tìm element (findElement) 10s
		// Apply cho tất cả các testcase cho việc findElement/findElements
		// Ngoại lệ
		// Implicit Wait set ở đâu nó sẽ apply từ đó trở xuống
		// Nếu bị gán lại thì sẽ dùng cái giá trị mới, ko dùng giá trị cũ
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// 4 Cách bên dưới như nhau
		// 1
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().window().maximize();

		// 2
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// 3
		driver.manage().window().maximize();
		driver.get("https://automationfc.github.io/dynamic-loading/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// 4 
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
	}
	@Test
	public void TC_01_Not_Enough_Time() {	
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Click vào Start button
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		// Loading icon mất 5s mới biến mất
		
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

		
	}
	@Test				
	public void TC_02_Enough_Time() {	
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Click vào Start button
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

	}
	@Test
	public void TC_03_More_Time() {	

		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Click vào Start button
		driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();
		
		// Get text và verify
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

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


