package webdriver;

import java.awt.AWTException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_26_Fluent_Wait {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	
	long allTime = 15; // Second
	long pollingTime = 100; // Milisecond
	
	String osName = System.getProperty("os.name").toLowerCase();

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		//System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		
		//System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
		//driver = new EdgeDriver();
		
		// Wait cho các trạng thái của element: visible/ presence/ invisible/ staleness
		// Visible: Phải thấy được trên UI/ Presence: Phải có ở trong HTML



		// Wait cho việc tìm element (findElement)
	
		driver.manage().window().maximize();
		
	}

	public void TC_01_Fluent() {	
		// ImplicitWait
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// ExplicitWait
		//expliciWait = new WebDriverWait(driver, 15);
		
		// FluentWait		
		driver.get("https://automationfc.github.io/dynamic-loading/");
		fluentDriver = new FluentWait<WebDriver>(driver);
		
		// Set tổng thời gian và tần số
		fluentDriver.withTimeout(Duration.ofSeconds(15))
		// 0.5s check 1 lần
		.pollingEvery(Duration.ofMillis(500))
		.ignoring(NoSuchElementException.class);
		
		// Apply điều kiện
		
		WebElement startButton = fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.cssSelector("div#start>button"));
			}
		});	
		startButton.click();
		
		
		// Dùng Implicit thì sẽ như sau, chức năng y hệt đoạn code Fluent trên
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.findElement(By.cssSelector("div#start>button"));
		
	}

	@Test
	public void TC_02_TC_01_Dungham_FindElement() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		WebElement startButton = findElement("//div[@id='start']/button");
		startButton.click();
		
		Assert.assertEquals(findElement("//div[@id='finish']/h4").getText(), "Hello World!");
	}
		
	public WebElement findElement(String xpathLocator) {
		
		fluentDriver = new FluentWait<WebDriver>(driver);
		

		fluentDriver.withTimeout(Duration.ofSeconds(allTime))

		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class);
		
		
		return fluentDriver.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath(xpathLocator));
			}
		});	
	
		
	}
	
	@Test
	public void TC_03_CountDown_Fluent() throws TimeoutException{	
		driver.get("https://automationfc.github.io/fluent-wait/");
		
		WebElement countdownTime = findElement("//div[@id='javascript_countdown_time']");
		
		fluentElement = new FluentWait<WebElement>(countdownTime);
		fluentElement.withTimeout(Duration.ofSeconds(allTime))
		.pollingEvery(Duration.ofMillis(pollingTime))
		.ignoring(NoSuchElementException.class);
		
		fluentElement.until(new Function<WebElement, Boolean>() {

			@Override
			public Boolean apply(WebElement element) {
				// TODO Auto-generated method stub
				return element.getText().endsWith("00");
			}
		});
		
	}




	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	}

		

