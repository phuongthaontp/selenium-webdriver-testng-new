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
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_FindElement_FindElements {
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


		// Wait cho việc tìm element (findElement) 10s
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	
	public void TC_01_FindElement() {	
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

		// Thao tác 1 lần		
		//driver.findElement(By.cssSelector("")).click();
		
		// Thao tác nhiều lần		
		// WebElement emailTextbox = driver.findElement(By.cssSelector(""));
		
		// Tìm thấy duy nhất 1 Element/node
		// Tìm thấy và thao tác trực tiếp lên node đó
		// Vì nó tìm thấy nên ko cần phải chờ hết timeout 10s
		driver.findElement(By.cssSelector("input#email"));
		
		// Tìm thấy nhiều hơn 1 Element/node
		// Thao tác với Node đầu tiên, ko quan tâm các node còn lại
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("trangdth@gmail.com");
			
		// Không tìm thấy element/node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy thì thỏa mãn điều kiện - Pass
		// Nếu hết thời gian (10s) mà vẫn ko thấy element thì
		// + Đánh fail test case tại step này, ko chạy step tiếp theo
		// + Throw ra 1 ngoại lệ: NoSuchElementException	
		driver.findElement(By.cssSelector("input#check"));
		

		
	}
				
	public void TC_02_FindElements() {	
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");

	
		// Tìm thấy duy nhất 1 Element/node
		// Vì nó tìm thấy nên ko cần phải chờ hết timeout 10s
		List<WebElement> elements = driver.findElements(By.cssSelector("input#email"));
		System.out.println("List element number= "+elements.size());
		
		
		// Tìm thấy nhiều hơn 1 Element/node
		// Tìm thấy và lưu nó vào list = element tương ứng
		// Thao tác với Node đầu tiên, ko quan tâm các node còn lại
		elements = driver.findElements(By.cssSelector("input[type='email']"));
		System.out.println("List element number= "+elements.size());

		
		// Không tìm thấy element/node nào
		// Có cơ chế tìm lại = 0.5s sẽ tìm lại 1 lần
		// Nếu trong thời gian đang tìm lại mà thấy thì thỏa mãn điều kiện - Pass
		// Nếu hết thời gian (10s) mà vẫn ko thấy element thì
		// + Ko đánh Fail testcase + vẫn chạy step tiếp theo 
		// + Trả về 1 list rỗng (empty) size() = 0
		driver.findElements(By.cssSelector("input#check"));
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


