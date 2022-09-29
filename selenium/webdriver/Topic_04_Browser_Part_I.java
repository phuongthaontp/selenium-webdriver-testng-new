package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Browser_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://vnext.vn");
	}	

	@Test
	public void TC_01_Browser() {	
	// Các hàm/ method để thao tác với Browser là thông qua biển driver
	// Mở ra 1 URL
	driver.get("https://vnext.vn");
	// Đóng browser nếu chỉ có 1 tab
	// Đóng tab hiện tại nếu có nhiều tab
	// WebDriver API - Windows/ Tabs
	driver.close();
	
	// Đóng Browser không quan tâm bao nhiêu tab
	driver.quit();
	
	// Tìm 1 Element trên Page
	// Trả về DataType là WebElement
	WebElement emailTextbox = driver.findElement(By.id("email"));
	
	// Tìm nhiều hơn 1 Element trên Page
	// Trả về DataType là List<Element>
	List<WebElement> links = driver.findElements(By.xpath("//a"));
	
	// Trả về URL page hiện tại
	String homePageUrl = driver.getCurrentUrl();
	System.out.println(homePageUrl);
	
	// Verify dữ liệu đúng như mong đợi
	Assert.assertEquals(homePageUrl, "https://vnext.vn");
	Assert.assertEquals(driver.getCurrentUrl(), "https://vnext.vn");
	
	// Lấy ra Source của Page hiện tại
	String homePageSource = driver.getPageSource();
	Assert.assertTrue(homePageSource.contains("Welcome to out store"));
	
	// Lấy Title của page hiện tại
	String homePageTittle = driver.getTitle();
	Assert.assertEquals(homePageTittle, "VNEXT trang chủ");
	
	// WebDriver API - Windows/ Tabs
	// Trả về 1 ID của tab hiện tại (1)
	String signUpTabID = driver.getWindowHandle();
	
	// Trả về ID của tất cả các tab đang có (n)
	Set<String> allTabID = driver.getWindowHandles();
	
	// Xử lý cookies (Framework)
	driver.manage().getCookies();
	
	// Lấy log của browser ra (Framework)
	driver.manage().logs();
	
	// Time của việc findElemen/ findElements
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
	
	// Time page được load xong
	
	driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	
	// Time để 1 đoạn async script được thực thi thành công (JavascriptExecutor)
	driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
	
	// Fullscreen browser
	driver.manage().window().fullscreen();
	
	// Enduser hay dùng maximize
	driver.manage().window().maximize();
	
	//Lấy ra vị trí của Browser so với độ phân giải màn hình
	
	Point browserPosition = driver.manage().window().getPosition();
	
	//Set vị trí của browser tại điểm 0*250
	driver.manage().window().setPosition(new Point(250, 250));
	
	// Lấy ra chiều rộng/chiều cao của Browser
	Dimension browserSize = driver.manage().window().getSize();
	
	// Set browser với kích thước nào
	// Test Responsive
	driver.manage().window().setSize((new Dimension(1366, 768)));
	
	// Quay lại trang trước đó
	driver.navigate().back();
	
	// Chuyển tiếp trang trước đó
	driver.navigate().forward();
	
	// Tải lại trang
	driver.navigate().refresh();
	
	driver.navigate().to("https://vnext.vn");
	
	driver.switchTo().alert();

	
	
	
	}
	@Test
	public void TC_02_Element() {	
	
		
		
	}
	}
	// Đóng browser nếu chỉ có 1 tab
	// Đóng tab hiện tạ 
