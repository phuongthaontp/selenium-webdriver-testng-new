package webdriver;

import static org.testng.Assert.assertEquals;

import java.sql.Driver;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {


	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Rode() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// 1. Click vào 1 element cho nó xổ hết ra các item
		// 2. Chờ cho đến khi các item đc load hết
		// 3. Nếu item cần chọn trong vùng nhìn thấy thì ko cần scroll xuống tiế
		// 4. Nếu item cần chọn nằm ở dưới thì cần scroll đến item đó
		// 5. Click vào item cần chọn
		
		
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
