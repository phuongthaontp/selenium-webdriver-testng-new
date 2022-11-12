package webdriver;

import java.awt.AWTException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

public class Topic_25_Mix_Implicit_Explicit {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait expliciWait;
	String projectPath = System.getProperty("user.dir");

	
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
	
	public void TC_01_Element_Found() {	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver, 15);
		driver.get("https://www.facebook.com/");
		
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		
		driver.findElement(By.cssSelector("input#email"));
		// Không cần chờ hết timeout của loại nào hết
	}

	public void TC_02_Element_Not_Found_Implicit() {	
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
		
		// Implicit
		System.out.println("Thời gian bắt đầu của implicit: ");
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Thời gian kết thúc của implicit: ");
		}
		
		// Output
		// Có cơ chế tìm lại sau mỗi 0.5s (interval time =500ms)
		// Hết timeout 10s sẽ đánh fail TC
		// Throw ra 1 exception: NoSuchElement
	}
			

	public void TC_03_Not_Found_Implicit_Explicit() throws TimeoutException{	
		// Implicit = Explicit

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		expliciWait = new WebDriverWait(driver, 5);
		driver.get("https://www.facebook.com/");
		
		try {
			expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			driver.findElement(By.cssSelector("input#selenium"));
		} catch (Exception e) {
			System.out.println("Thời gian kết thúc của explicit:");
		}
	}

	public void TC_03_Not_Found_Implicit_Lessthan_Explicit() 	{
	// Implicit < Explicit
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	expliciWait = new WebDriverWait(driver, 8);
	driver.get("https://www.facebook.com/");
	
	try {
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		driver.findElement(By.cssSelector("input#selenium"));
	} catch (Exception e) {
		System.out.println("Thời gian kết thúc của explicit:");
	}
}

	public void TC_03_Not_Found_Implicit_Greaterthan_Explicit() 	{
	// Implicit > Explicit
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	expliciWait = new WebDriverWait(driver, 3);
	driver.get("https://www.facebook.com/");
	
	try {
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	try {
		driver.findElement(By.cssSelector("input#selenium"));
	} catch (Exception e) {
		System.out.println("Thời gian kết thúc của explicit:");
	}
}
	public void TC_04_Not_Found_Explicit_By() 	{
	expliciWait = new WebDriverWait(driver, 5);
	driver.get("https://www.facebook.com/");
	
	// Explicit - By là tham số nhận vào của hàm explicit - visibilityOfElementLocated(By)
	// Implicit = 0
	try {
		expliciWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#selenium")));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	@Test
	public void TC_05_Not_Found_Explicit_Element() 	{
	expliciWait = new WebDriverWait(driver, 5);
	driver.get("https://www.facebook.com/");
	
	// Explicit - By là tham số nhận vào của hàm explicit - visibilityOfElementLocated(By)
	// Implicit = 0
	try {
		expliciWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#selenium"))));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	
	}

		

