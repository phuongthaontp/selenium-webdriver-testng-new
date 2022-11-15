package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_27_Page_Ready {

	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	
	long allTime = 15; // Second
	long pollingTime = 100; // Milisecond
	
	String osName = System.getProperty("os.name").toLowerCase();
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		action = new Actions(driver);
	}
	@Test
	public void TC_01_OrangeHRM() {	
		driver.get("https://opensource-demo.orangehrmlive.com");
		findElement("//input[@name='username']").sendKeys("Admin");
		findElement("//input[@name='password']").sendKeys("admin123");
		findElement("//button[@type='submit']").click();
		
		
		//Assert.assertTrue(isPageLoadSuccess());
		
		driver.findElement(By.xpath("//a[@class='oxd-main-menu-item' and @href='/web/index.php/pim/viewPimModule']")).click();
		
		//Assert.assertTrue(isPageLoadSuccess());

		driver.findElement(By.xpath("(//input[@placeholder='Type for hints...'])[1]")).sendKeys("Peter");
		driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
		
		//Assert.assertTrue(isPageLoadSuccess());
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Peter')]")).isDisplayed());
		
	}
	@Test
	public void TC_02() {	
		driver.get("https://blog.testproject.io/");
		//Assert.assertTrue(isPageLoadSuccess());
		
		String keyword = "Selenium";
		// Handle PU
		if (driver.findElement(By.cssSelector("div.mailch-wrap")).isDisplayed()) {
			
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		
		// Hover chuột vào field Search
		action.moveToElement(driver.findElement(By.xpath("//section[@id='search-2']//input[@placeholder='Search Articles']"))).perform();
		
		Assert.assertTrue(isPageLoadSuccess());
		
		driver.findElement(By.xpath("//section[@id='search-2']//input[@placeholder='Search Articles']")).sendKeys(keyword);
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='page-title']")).isDisplayed());
		Assert.assertTrue(isPageLoadSuccess());
		
		List<WebElement> elements =  driver.findElements(By.cssSelector("h3.post-title>a"));
		
		for (WebElement e:elements)
		{
			String postTitle = e.getText();
			Assert.assertTrue(postTitle.contains(keyword));
			
		}
		
	}


	public boolean isPageLoadSuccess() {
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery !=null) && (jQuery.active ===0);");			
		}
		};
		
		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return jsExecutor.executeScript("return document.readyState").toString().equals("complete");			
		}	
	};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	
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
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
		}
	


		

