package webdriver;

import java.util.Date;
import java.util.List;
import java.util.Set;
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

public class Topic_14_Windows_Tab {
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
	public void TC_01_Naukri_Tab_ID() {
		
		// Trang A
		driver.get("https://www.naukri.com/");
		String homePageWindowsID = driver.getWindowHandle();
		System.out.println("Tab A: "+homePageWindowsID);
		
		// Click vào Jobs link (Trang B)
		driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
		sleepInSecond(3);
		System.out.println(driver.getCurrentUrl());
		
		Set<String> listPageWindowsID = driver.getWindowHandles();
		System.out.println("Set size: "+listPageWindowsID.size());
		
		// Hành vi là nó tự nhảy qua trang Jobs
		// Nhưng cái driver vẫn ở trang trước đó
		// Trong trường hợp chỉ có duy nhất 2 tab/window thì có thể dùng ID của tab/window để switch qua
		// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
		for (String id : listPageWindowsID) {
			// Nếu như cái id nào mà khác vs id của homepage 
		if (!id.equals(homePageWindowsID)) {
			// Thì switch qua id của tab đó
			driver.switchTo().window(id);
		}
		// Hoặc gọi hàm sau
		// switchToWindowsByID(homePageWindowsID);
		}
		// Sau khi switch qua, kiểm tra xem mình đang ở page nào
		System.out.println("Tab B: "+driver.getCurrentUrl());
		
		// Hiện tại đang đứng ở B rồi
		switchToWindowsByID(homePageWindowsID);

	// Sau khi switch qua, kiểm tra xem mình đang ở page nào
	System.out.println("Tab B: "+driver.getCurrentUrl());
	
		String jobWindowsID = driver.getWindowHandle();
		switchToWindowsByID(jobWindowsID);
}
	
	public void switchToWindowsByID (String currentURL){
		Set<String> listPageWindowsID = driver.getWindowHandles();
		for (String id : listPageWindowsID) {
			// Nếu như cái id nào mà khác vs id của homepage 
		if (!id.equals(currentURL)) {
			// Thì switch qua id của tab đó
			driver.switchTo().window(id);
		}
		}
		
	}
	
	@Test
	public void TC_01_Naukri_Tab_Title() {
		// Trang A
		driver.get("https://www.naukri.com/");
		
		// Click vào Jobs link (Trang B)
		driver.findElement(By.xpath("//a[@title='Search Jobs']")).click();
		sleepInSecond(3);		
		switchToMultiplesWindowsByLink("browser-jobs");
		System.out.println("Browser-jobs: "+ driver.getCurrentUrl());
		
		// Về trang A
		switchToMultiplesWindowsByTitle("Jobs - Recruiment - Job Search - Employment - Job Vacancies - Naukri.com ");
		System.out.println("naukri.com: "+driver.getCurrentUrl());
		
		// Click vào Register button/link
		driver.findElement(By.cssSelector("a#register_Layer")).click();
		sleepInSecond(3);
		
		switchToMultiplesWindowsByLink("browser-jobs");
		System.out.println("Browser-jobs: "+driver.getCurrentUrl());
}
	// Dùng được cho cả 2 tab/windows hoặc nhiều hơn 2 đều được
	public void switchToMultiplesWindowsByTitle(String expectedTitle) {
		Set<String> listPageWindowsID = driver.getWindowHandles();
		
		// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
		for (String id : listPageWindowsID) {
			// Switch vào trc rồi mới kiểm tra điều kiện sau
			driver.switchTo().window(id);
			
			// Lấy cái title của page đó ra
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(expectedTitle))
			{
				// Thỏa mãn điều kiện là đúng cái page mình cần
				break;
				
			}

		}
	}

	public void switchToMultiplesWindowsByLink(String expectedRelativeLink) {
		Set<String> listPageWindowsLink = driver.getWindowHandles();
		
		// Dùng 1 biến tạm để duyệt qua các phần tử trong Set<String>
		for (String link : listPageWindowsLink) {
			// Switch vào trc rồi mới kiểm tra điều kiện sau
			driver.switchTo().window(link);
			
			// Lấy cái title của page đó ra
			String actualLink = driver.getCurrentUrl();
			if (actualLink.contains(expectedRelativeLink))
			{
				// Thỏa mãn điều kiện là đúng cái page mình cần
				break;
				
			}

		}
	}
	
	
	@Test
	public void TC_03_Cambridge_() {
		// Home
		driver.get("https://dictionary.cambridge.org/vi/");
		
		// Click vào Jobs link (Trang B)
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Đăng nhập']")).click();
		sleepInSecond(3);	
		
		switchToMultiplesWindowsByTitle("Login - Google Chrome");
		
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']/following-sibling::span")).getText(), "This field is required");
		Assert.assertEquals(driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']/following-sibling::span")).getText(), "This field is required");
		sleepInSecond(3);
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='username']")).sendKeys("automationfc@gmail.com");
		driver.findElement(By.xpath("//form[@id='gigya-login-form']//input[@name='password']")).sendKeys("Automation000***");
		sleepInSecond(3);
		driver.findElement(By.xpath("//input[@value='Log in']")).click();
		
		// Business nó tự close đi và nhảy về trang trước đó
		// Driver vẫn ở trang Login
		// Switch về trang Home
		switchToMultiplesWindowsByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
		
		// Verify Login thành công
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='tb'][contains(text(),'Đăng nhập')]")).getText(), "Đăng nhập");
		
	}

   public boolean closeAllWindowsWithoutParent(String parentID) {
	   Set<String> allWindows = driver.getWindowHandles();
	   for (String list:allWindows) {
		   if (!list.equals(parentID)) {
		   driver.switchTo().window(list);
		   driver.close();
		   }
	   }
	   driver.switchTo().window(parentID);
	   if (driver.getWindowHandles().size()==1)
		   return true;
	   else
		   return false;
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
}


