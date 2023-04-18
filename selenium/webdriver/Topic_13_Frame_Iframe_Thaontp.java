package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Frame_Iframe_Thaontp {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		// Khởi tạo browser lên
		driver = new FirefoxDriver();
		
		// Set thời gian chờ để tìm được element
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	
	public void TC_01_Kyna() {
		
		// Page A
		driver.get("https://skills.kynaenglish.vn/");
		
		// Page A --> B
		// Switch vào frame/ iframe trước rồi mới thao tác lên element thuộc frame/ iframe đó 
		 driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,'facebook.com/kyna.vn')]")));
		
		// Page B
		// Verify FB số lượng hiển thị là 165k likes
		 Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "165K likes");
		
		// Trở về page A ban đầu trước khi thao tác tiếp vào iframe
		 driver.switchTo().defaultContent();
		 
		// Swithch vào iframe chat trước
		 driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		 
		// Click vào chat iframe page C ( Từ A --> C)
		 driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		 
		 driver.findElement(By.cssSelector("input.input_name")).sendKeys("Thaontp");
		 driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0123456");
		 driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Text area");
		 
		// Từ C --> page A ban đầu
		 driver.switchTo().defaultContent();
		 
		// Defind Key
		 String keyword = "Excel";
		 
		// Senkey từ khóa Excel và click Search
		 driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(keyword);
		 driver.findElement(By.cssSelector("button.search-button")).click();
		 
		// Verify title có chứa từ khóa " Excel"
		 
		 List<WebElement> courseName = driver.findElements(By.cssSelector("div.content>h4"));
		 for (WebElement course : courseName) {
			 System.out.println(course.getText());
			 Assert.assertTrue(course.getText().contains(keyword));
		 }
	}
	
	
	@Test 
	public void TC_02_Frame_Banking(){
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		// Switch vào frame
		driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
		
		driver.findElement(By.cssSelector("input.form-control")).sendKeys("123456");
		driver.findElement(By.cssSelector("a.btn-primary")).click();
		
		// Verify textbox hiển thị
		Assert.assertTrue(driver.findElement(By.cssSelector("a.btn-primary")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
