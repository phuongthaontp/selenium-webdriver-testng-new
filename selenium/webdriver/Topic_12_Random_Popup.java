package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Random_Popup {
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
	public void TC_01_Random_InDOM() {	
		// Step 1
		driver.get("https://vnk.edu.vn/");
		
		// Step 2
		// Đây là Element luôn có trong DOM dù có hiển thị hay không
		// Case 1 - Nếu như PU hiển thì thì có thể thao tác với PU rồi close nó đi	

		if(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed()) {
		System.out.println("Case 1 - Nếu như PU hiển thì thì có thể thao tác với PU rồi close nó đi");
			
			// Close Popup trong này
		driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
		sleepInSecond(2);
		
		// Expected Popup không còn hiển thị nữa
		Assert.assertFalse(driver.findElement(By.cssSelector("div#tve_editor")).isDisplayed());
		}
		else
		{
			System.out.println("Case 2 - Nếu như không hiển thị thì qua step tiếp theo");
		}

		// Case 2 - Nếu như không hiển thị thì qua step tiếp theo
		// Step 3
		// Click vào trang Liên hệ
		driver.findElement(By.xpath("//a[@title='Liên hệ']")).click();		

		
		// Step 4
		// Verify qua trang Liên hệ thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content>h1")).isDisplayed());
		
	}
	
	
	@Test
	public void TC_02_Random_InDOM_KMP() {	
		// Step 1
		driver.get("https://www.kmplayer.com/home");
		
		// Step 2
		// Đây là Element luôn có trong DOM dù có hiển thị hay không
		// Case 1 - Nếu như PU hiển thì thì có thể thao tác với PU rồi close nó đi	

		if(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed()) {
		System.out.println("Case 1 - Nếu như PU hiển thì thì có thể thao tác với PU rồi close nó đi");
			
		// Thao tác vs PU
		// Enter/ Submit/ ...
		
		
		// Close Popup trong này
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("area#btn-r")));
		sleepInSecond(2);

		// Expected Popup không còn hiển thị nữa
		Assert.assertFalse(driver.findElement(By.cssSelector("div.pop-layer")).isDisplayed());
		}
		else
		{
			System.out.println("Case 2 - Nếu như không hiển thị thì qua step tiếp theo");
		}

		// Case 2 - Nếu như không hiển thị thì qua step tiếp theo
		// Step 3
		// Click vào trang Liên hệ
		driver.findElement(By.xpath("//a[contains(text(),'MOVIEBLOC')]")).click();		

		
		// Step 4
		// Verify qua trang Liên hệ thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("section.main_top_banner")).isDisplayed());
		
	}
	
	
	@Test
	public void TC_03_Random_NOTInDOM() {	
		// Step 1
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		
		System.out.println("Find PU - Start: "+getCurrentDateTime());
		List<WebElement> popupContent = driver.findElements(By.cssSelector("div.popup-content"));
		System.out.println("Find PU - End: "+getCurrentDateTime());
		// Step 2


		if(popupContent.size() >0) {
		System.out.println("Case 1 - Nếu như PU hiển thì thì có thể thao tác với PU rồi close nó đi");
			
		// Thao tác vs PU
		// Enter/ Submit/ ...
		driver.findElement(By.cssSelector("input#popup-name")).sendKeys("Trangdth");
		driver.findElement(By.cssSelector("input#popup-email")).sendKeys("Trangdth@vnext.com.vn");
		driver.findElement(By.cssSelector("input#popup-phone")).sendKeys("0987555666");
		
		// Close Popup trong này
		driver.findElement(By.cssSelector("button#close-popup")).click();
		sleepInSecond(2);

		// Verify Popup không còn hiển thị nữa
		// Phải refind popupContent vì lúc đầu khi PU hiển thị popupContent có giá trị = 1, và chưa đc khởi tạo lại
		System.out.println("Verify PU - Start: "+getCurrentDateTime());
		popupContent = driver.findElements(By.cssSelector("div.popup-content"));
		Assert.assertEquals(popupContent.size(), 0);
		System.out.println("Verify PU - End: "+getCurrentDateTime());
		}
		else
		{   // Nếu như setting cho app vào ngày xx/xx/xxxx nào đó sẽ hiển thị để chạy chương trình Marketing
			// Sau thời gian này thì setting nó không hiển thị PU nữa
			// (Ko có trong DOM ngay từ đầu luôn)
			System.out.println("Case 2 - Nếu như không hiển thị thì qua step tiếp theo");
		}

		// Case 2 - Nếu như không hiển thị thì qua step tiếp theo
		// Step 3
		driver.findElement(By.xpath("//a[text()= 'Tất cả khóa học']")).click();		

		
		// Step 4
		// Verify qua trang Search course thành công
		Assert.assertTrue(driver.findElement(By.cssSelector("input#search-courses")).isDisplayed());
		
		
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


