package webdriver;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Part_I {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01() {
		
		// Các hàm (function/ method) câu lệnh (command) 
		// Tương tác vs Browser
		// driver.
		
		// Tương tác vs Element
		// Nếu như Element chỉ dùng duy nhất 1 lần thì không cần khai báo biến
	
		driver.findElement(By.name("login")).click();
		
		// Nếu như Element đó dùng từ 2 lần trở lên thì nên khai báo biến để tái sử dụng
		driver.findElement(By.cssSelector("#email")).clear();
		driver.findElement(By.cssSelector("#email")).sendKeys("AutomationTesting@gmail.com");
		WebElement emailTextbox = driver.findElement(By.cssSelector("#email"));
		emailTextbox.clear();
		
		
		
	}
	
	@Test
	public void TC_02() {
		WebElement element = driver.findElement(By.xpath("https://vnext.vn"));
		
		// Xoá dữ liệu trong textbox/ textarea/ editable dropdown
		
		element.clear();
		
		// Nhập dữ liệu
		element.sendKeys("");
		
		// Click
		element.click();
		
		driver.getCurrentUrl();
		driver.getPageSource();
		driver.getTitle();
		driver.getWindowHandle();
		driver.getWindowHandles();
		
		// Trả về vị trí, size của cả browser
		driver.manage().window().getPosition();
		driver.manage().window().getSize();
		
		// Nếu text nằm trong Attribute thì dùng getAttribute, nếu không thì dùng hàm getText();
		element.getAttribute("placeholder");
		
		// Ví dụ
		driver.get("http://live.techpanda.org/index.php/");
		String searchPlaceholder =driver.findElement(By.id("search")).getAttribute("placeholder");
		System.out.println("searchPlaceholder");
		
		// getText()
		
		driver.get("http://live.live.techpanda.org/index/php/customer/account/create/");
		String instructionText = driver.findElement(By.cssSelector("p.form-instructions")).getText();
		System.out.println("instructionText");
		
		// Thường dùng để test GUI: font/ color/ size/ location/ position
		// Đều lấy ra các thuộc tính của Css đc
		element.getCssValue("");
		
		// Ví dụ
		WebElement loginButton = driver.findElement(By.name("login"));
		System.out.println(loginButton.getCssValue("font-size"));
		System.out.println(loginButton.getCssValue("background-color"));
		System.out.println(loginButton.getCssValue("width"));
		System.out.println(loginButton.getCssValue("font-family"));
		
		
		
		// Ít dùng
		element.getLocation();
		element.getRect();
		
		
		// Chụp hình của element lại lưu bằng 1 format nào đó
		String base64Image = element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BASE64);
		element.getScreenshotAs(OutputType.BYTES);
		element.getScreenshotAs(OutputType.FILE);
		
		
		// Trả về Size của Element
		// Ít dùng
		Dimension elementSize = element.getSize();
		
		
		// Lấy ra tên thẻ (html) của element
		element = driver.findElement(By.xpath("//input[@id='email']"));
		element = driver.findElement(By.cssSelector("input[id='email']"));
		
		
		// Đầu ra của step này là đầu vào của step kia
		// 1 ví dụ về cách ghép chuỗi		
		WebElement loginButton1 = driver.findElement(By.name("login"));
		String loginButton1Tagname = loginButton1.getTagName();
		element = driver.findElement(By.xpath("//"+loginButton1Tagname+"[@id='login button']"));
		
		//input
		element.getText();
		
		// Kiểm tra 1 element có hiển thị hay ko -tất cả các loại Element
		// Người dùng có thể nhìn thấy được
		
		// Mong đợi nó không hiển thị
		Assert.assertFalse(element.isDisplayed());
		
		// Mong đợi nó hiển thị
		Assert.assertTrue(element.isDisplayed());
		
		
		// Kiểm tra 1 element có thao tác được hay không
		// Read-only hoặc Disable element
		// Mong đợi nó bị Disable/ Read-Only/ Không thao tác được
		Assert.assertFalse(element.isEnabled());
		
		
		// Mong đợi nó thao tác được
		Assert.assertTrue(element.isEnabled());
		
		// Kiểm tra 1 element đã được chọn hay chưa
		// Radio button/ Checkbox/ Dropdown
		// Mong đợi nó chưa được chọn
		Assert.assertFalse(element.isSelected());
		
		
		// Mong đợi nó được chọn
		Assert.assertTrue(element.isSelected());
		
		// Submit = ENTER trên bàn phím, dùng cho Form hoặc element trong thẻ form
		// Submit bằng element bất kỳ nằm trong Form là đc
		// Action sẽ là send request lên server
		driver.findElement(By.id("email")).sendKeys("auto@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).submit();
		
		
		
		
		
		
}}